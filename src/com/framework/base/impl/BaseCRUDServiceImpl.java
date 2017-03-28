package com.framework.base.impl;

import com.framework.base.BaseCRUDService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * 实体管理器，直接对Entity对象进行持久操作
 * 此管理器仅用于对不确定类型entity操作使用，
 * 已明确类型情况，即已知对应entity对应dao的情况禁用此类
 *
 * @ClassName: BaseCRUDServiceImpl
 * @Description:
 */
@Service("entityManager")
@Transactional(rollbackFor = {Exception.class})
public class BaseCRUDServiceImpl implements BaseCRUDService {

    @PersistenceContext
    private EntityManager em;

    /**
     * 查询所有
     *
     * @param entityClass entity class
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public <T> List<T> list(Class<T> entityClass) {
        return em.createQuery("from " + entityClass.getName(), entityClass).getResultList();
    }

    /**
     * 根据entity对应类及主键值直接获取entity对象
     *
     * @param entityClass entity class
     * @param primaryKey  对象主键值
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public <T> T get(Class<T> entityClass, Object primaryKey) {
        return em.find(entityClass, primaryKey);
    }

    /**
     * 删除对象
     *
     * @param entity entity类
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public <T> void delete(T entity) {
        if (entity != null) {
            em.remove(em.merge(entity));
        }
    }

    /**
     * 删除对象
     *
     * @param entityClass entity class
     * @param primaryKey  对象主键值
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public <T> void delete(Class<T> entityClass, Object primaryKey) {
        String sql = "delete from " + entityClass.getName() + " where " + getPkname(entityClass) + " = " + primaryKey;
        em.createQuery(sql).executeUpdate();
    }

    /**
     * 保存entity或修改对象
     *
     * @param entity entity类
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public <T> T saveOrUpdate(T entity) {
        if (entity != null) {
            return em.merge(entity);
        }
        return null;
    }

    /**
     * 统计全表记录数
     *
     * @param entityClass entity class
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public <T> long count(Class<T> entityClass) {
        return em.createQuery("select count(*) from " + entityClass.getName(), Long.class).getSingleResult();
    }

    /**
     * 统计全表记录数
     *
     * @param entityClass entity class
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public <T> long count(Class<T> entityClass, Object... args) {
        Query query = em.createQuery("select count(*) from " + entityClass.getName(), Long.class);
        queryHqlParams(query, args);
        return (long) query.getSingleResult();
    }

    /**
     * 统计全表记录数
     *
     * @param entityClass entity class
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public <T> long count(Class<T> entityClass, Map<String, Object> params) {
        StringBuilder hql = new StringBuilder("select count(*) from ").append(entityClass.getName()).append(" where 1=1 ");
        Object[] args = assemble(hql, params, null, null);
        Query query = em.createQuery(hql.toString(), Long.class);
        if (args != null) {
            queryHqlParams(query, args);
        }
        return (long) query.getSingleResult();
    }

    /**
     * 统计全表记录数
     *
     * @param hql entity class
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public long countHql(String hql, Object... args) {
        Query query = em.createQuery(hql, Long.class);
        queryHqlParams(query, args);
        return (long) query.getSingleResult();
    }

    /**
     * 统计全表记录数
     *
     * @param sql entity class
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public long countSql(String sql, Object... args) {
        Query query = em.createNativeQuery(sql);
        querySqlParams(query, args);
        return ((BigInteger) query.getSingleResult()).longValue();
    }

    /**
     * 数据库HQL查询.
     * <p>
     * eg: hql: from ENTITY where property1 = ? and property2 = ?
     * args: property1 value,property2 value
     *
     * @param hql  HQL查询语句
     * @param args HQL查询参数
     * @return 查询结果集
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<?> hql(String hql, Object... args) {
        Query query = em.createQuery(hql);
        queryHqlParams(query, args);
        return query.getResultList();
    }

    /**
     * 数据库HQL查询.
     *
     * @param <T>        返回结果类型泛参
     * @param expectType 期望返回结果集类型
     * @see #hql(String, Object...)
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public <T> List<T> hql(Class<T> expectType, String hql, Object... args) {
        Query query = em.createQuery(hql, expectType);
        queryHqlParams(query, args);
        return query.getResultList();
    }

    /**
     * 数据库原生SQL查询.
     * <p>
     * eg: SQL:select * from TABLE where col1 = ? and col2 = ?
     * args: col1 value, col2 value
     *
     * @param sql  SQL语句
     * @param args SQL查询参数
     * @return 查询结果集
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<?> sql(String sql, Object... args) {
        Query query = em.createNativeQuery(sql);
        querySqlParams(query, args);
        return query.getResultList();
    }

    /**
     * 数据库原生SQL查询.
     *
     * @param <T>        返回结果类型泛参
     * @param expectType 期望返回结果集类型
     * @see #sql(String, Object...)
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public <T> List<T> sql(Class<T> expectType, String sql, Object... args) {
        Query query = em.createNativeQuery(sql, expectType);
        querySqlParams(query, args);
        return query.getResultList();
    }

    /**
     * 单一结果HQL查询.
     *
     * @return 结果
     * @throws javax.persistence.NonUniqueResultException 返回结果数量不是1(expect 1)
     * @throws javax.persistence.NoResultException        返回结果为0记录
     * @see #hql(String, Object...)
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Object uniqueResultHql(String hql, Object... args) {
        Query query = em.createQuery(hql);
        queryHqlParams(query, args);
        return query.getSingleResult();
    }

    /**
     * 单一结果HQL查询.
     *
     * @return 结果
     * @throws javax.persistence.NonUniqueResultException 返回结果数量不是1(expect 1)
     * @throws javax.persistence.NoResultException        返回结果为0记录
     * @see #hql(Class, String, Object...)
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public <T> T uniqueResultHql(Class<T> expectType, String hql, Object... args) {
        Query query = em.createQuery(hql, expectType);
        queryHqlParams(query, args);
        return (T) query.getSingleResult();
    }

    /**
     * 单一结果SQL查询.
     *
     * @return 结果
     * @throws javax.persistence.NonUniqueResultException 返回结果数量不是1(expect 1)
     * @throws javax.persistence.NoResultException        返回结果为0记录
     * @see #sql(String, Object...)
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Object uniqueResultSql(String sql, Object... args) {
        Query query = em.createNativeQuery(sql);
        querySqlParams(query, args);
        return query.getSingleResult();
    }

    /**
     * 单一结果SQL查询.
     *
     * @return 结果
     * @throws javax.persistence.NonUniqueResultException 返回结果数量不是1(expect 1)
     * @throws javax.persistence.NoResultException        返回结果为0记录
     * @see #sql(Class, String, Object...)
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public <T> T uniqueResultSql(Class<T> expectType, String sql, Object... args) {
        Query query = em.createNativeQuery(sql, expectType);
        querySqlParams(query, args);
        return (T) query.getSingleResult();
    }

    /**
     * 查询第一条数据
     *
     * @param hql  hql语句
     * @param args hql查询参数
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Object topResultHql(String hql, Object... args) {
        Query query = em.createQuery(hql);
        queryHqlParams(query, args);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    /**
     * 查询第一条数据
     *
     * @param expectType 期望返回的结果集类型
     * @param hql        hql语句
     * @param args       hql查询参数
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public <T> T topResultHql(Class<T> expectType, String hql, Object... args) {
        Query query = em.createQuery(hql, expectType);
        queryHqlParams(query, args);
        query.setMaxResults(1);
        return (T) query.getSingleResult();
    }

    /**
     * 查询第一条数据
     *
     * @param sql  sql语句
     * @param args sql查询参数
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Object topResultSql(String sql, Object... args) {
        Query query = em.createNativeQuery(sql);
        querySqlParams(query, args);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    /**
     * 查询第一条数据
     *
     * @param expectType 期望返回的结果集类型
     * @param sql        sql语句
     * @param args       sql查询参数
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public <T> T topResultSql(Class<T> expectType, String sql, Object... args) {
        Query query = em.createNativeQuery(sql, expectType);
        querySqlParams(query, args);
        query.setMaxResults(1);
        return (T) query.getSingleResult();
    }

    /**
     * 查询前几条数据
     *
     * @param expectType 期望返回的结果集类型
     * @param top        期望查询的前几条数据
     * @param sql        sql语句
     * @param args       sql查询参数
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public <T> List<T> topResultSql(Class<T> expectType, int top, String sql, Object... args) {
        Query query = em.createNativeQuery(sql, expectType);
        querySqlParams(query, args);
        query.setMaxResults(top);
        return query.getResultList();
    }

    /**
     * 查询前几条数据
     *
     * @param expectType 期望返回的结果集类型
     * @param top        期望查询的前几条数据
     * @param hql        hql语句
     * @param args       hql查询参数
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public <T> List<T> topResultHql(Class<T> expectType, int top, String hql, Object... args) {
        Query query = em.createQuery(hql, expectType);
        queryHqlParams(query, args);
        query.setMaxResults(top);
        return query.getResultList();
    }

    /**
     * hql 语句更新
     *
     * @param hql  hql语句
     * @param args hql参数
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int updateHql(String hql, Object... args) {
        Query query = em.createQuery(hql);
        queryHqlParams(query, args);
        return query.executeUpdate();
    }

    /**
     * hql 语句更新
     *
     * @param sql  sql语句
     * @param args sql参数
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int updateSql(String sql, Object... args) {
        Query query = em.createNativeQuery(sql);
        querySqlParams(query, args);
        return query.executeUpdate();
    }

    /**
     * hql 分页查询
     *
     * @param hql      hql语句
     * @param page     页码 （从0开始）
     * @param pageSize 每页大小
     * @param args     查询参数
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<?> hqlPager(String hql, int page, int pageSize, Object... args) {
        Query query = em.createQuery(hql);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        queryHqlParams(query, args);
        return query.getResultList();
    }

    /**
     * hql 分页查询
     *
     * @param expectType 结果类型
     * @param hql        hql语句
     * @param page       页码 （从0开始）
     * @param pageSize   每页大小
     * @param args       查询参数
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public <T> List<T> hqlPager(Class<T> expectType, String hql, int page, int pageSize, Object... args) {
        Query query = em.createQuery(hql, expectType);
        queryHqlParams(query, args);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    /**
     * sql 分页查询
     *
     * @param sql      sql语句
     * @param page     页码 （从0开始）
     * @param pageSize 每页大小
     * @param args     查询参数
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<?> sqlPager(String sql, int page, int pageSize, Object... args) {
        Query query = em.createNativeQuery(sql);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        querySqlParams(query, args);
        return query.getResultList();
    }

    /**
     * sql 分页查询
     *
     * @param expectType 结果类型
     * @param sql        sql语句
     * @param page       页码 （从0开始）
     * @param pageSize   每页大小
     * @param args       查询参数
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public <T> List<T> sqlPager(Class<T> expectType, String sql, int page, int pageSize, Object... args) {
        Query query = em.createNativeQuery(sql, expectType);
        querySqlParams(query, args);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    /**
     * hql 分页查询
     *
     * @param expectType 查询entity实体类型
     * @param params     参数集合
     * @param orders     排序集合
     * @param page       页码 （从0开始）
     * @param pageSize   每页大小
     * @param <T>        返回集合类型
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public <T> List<T> hqlPager(Class<T> expectType, Map<String, Object> params, List<Sort.Order> orders, int page, int pageSize) {
        StringBuilder hql = new StringBuilder("from ").append(expectType.getName()).append(" where 1=1 ");
        Object[] args = assemble(hql, params, null, orders);
        Query query = em.createQuery(hql.toString());
        if (args != null) {
            queryHqlParams(query, args);
        }
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    /**
     * 拼装sql | hql语句
     *
     * @param sb     sql|hql语句
     * @param params 参数集合
     * @param groups 分组集合
     * @param orders 排序字段集合
     * @return
     */
    private Object[] assemble(StringBuilder sb, Map<String, Object> params, List<String> groups, List<Sort.Order> orders) {
        Object[] args = null;
        //***********参数拼装*************
        if (!CollectionUtils.isEmpty(params)) {
            args = new Object[params.size()];
            int i = 0;
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                sb.append(" and ").append(entry.getKey()).append(" = ? ");
                args[i++] = entry.getValue();
            }
        }
        //***********group拼装*************
        if (!CollectionUtils.isEmpty(groups)) {
            sb.append(" group by ");
            for (int i = 0; i < groups.size(); i++) {
                if (i != 0) {
                    sb.append(", ");
                }
                sb.append(groups.get(i)).append(" ");
            }
        }
        //***********order拼装*************
        if (!CollectionUtils.isEmpty(orders)) {
            sb.append(" order by ");
            for (int i = 0; i < orders.size(); i++) {
                if (i != 0) {
                    sb.append(", ");
                }
                Sort.Order order = orders.get(i);
                sb.append(order.getProperty()).append(" ").append(order.getDirection().name());
            }
        }
        return args;
    }

    /**
     * query 填充参数值
     *
     * @param query Query对象
     * @param args  参数集合
     */
    private void queryHqlParams(Query query, Object... args) {
        for (int i = 0; i < args.length; i++) {
            query.setParameter(i, args[i]);
        }
    }

    /**
     * query 填充参数值
     *
     * @param query Query对象
     * @param args  参数集合
     */
    private void querySqlParams(Query query, Object... args) {
        for (int i = 0; i < args.length; i++) {
            query.setParameter(i + 1, args[i]);
        }
    }

    /**
     * 获取主键名称
     *
     * @param entityClass entity class
     * @return
     */
    private String getPkname(Class entityClass) {
        Class superClass = entityClass.getSuperclass();
        if (superClass != Object.class && superClass != null) {
            String pk = getPkname(superClass);
            if (pk != null) {
                return pk;
            }
        }
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(Id.class) != null) {
                return field.getName();
            }
        }

        return null;
    }

}
