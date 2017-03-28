package com.framework.base;

import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

/**
 * 实体管理器，直接对Entity对象进行持久操作
 * 此管理器仅用于对不确定类型entity操作使用，
 * 已明确类型情况，即已知对应entity对应dao的情况禁用此类
 */
public interface BaseCRUDService {

    /**
     * 查询所有
     *
     * @param entityClass entity class
     * @return
     */

    <T> List<T> list(Class<T> entityClass);

    /**
     * 根据entity对应类及主键值直接获取entity对象
     *
     * @param entityClass entity class
     * @param primaryKey  对象主键值
     * @return
     */

    <T> T get(Class<T> entityClass, Object primaryKey);

    /**
     * 删除对象
     *
     * @param entity entity类
     */

    <T> void delete(T entity);

    /**
     * 删除对象
     *
     * @param entityClass entity class
     * @param primaryKey  对象主键值
     */
    <T> void delete(Class<T> entityClass, Object primaryKey);

    /**
     * 保存entity或修改对象
     *
     * @param entity entity类
     * @return
     */
    <T> T saveOrUpdate(T entity);

    /**
     * 统计全表记录数
     *
     * @param entityClass entity class
     * @return
     */

    <T> long count(Class<T> entityClass);

    /**
     * 统计全表记录数
     *
     * @param entityClass entity class
     * @return
     */

    <T> long count(Class<T> entityClass, Object... args);

    /**
     * 统计全表记录数
     *
     * @param entityClass entity class
     * @return
     */

    <T> long count(Class<T> entityClass, Map<String, Object> params);

    /**
     * 统计全表记录数
     *
     * @param hql entity class
     * @return
     */

    long countHql(String hql, Object... args);

    /**
     * 统计全表记录数
     *
     * @param sql entity class
     * @return
     */

    long countSql(String sql, Object... args);

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

    List<?> hql(String hql, Object... args);

    /**
     * 数据库HQL查询.
     *
     * @param <T>        返回结果类型泛参
     * @param expectType 期望返回结果集类型
     * @see #hql(String, Object...)
     */

    <T> List<T> hql(Class<T> expectType, String hql, Object... args);

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

    List<?> sql(String sql, Object... args);

    /**
     * 数据库原生SQL查询.
     *
     * @param <T>        返回结果类型泛参
     * @param expectType 期望返回结果集类型
     * @see #sql(String, Object...)
     */

    <T> List<T> sql(Class<T> expectType, String sql, Object... args);

    /**
     * 单一结果HQL查询.
     *
     * @return 结果
     * @throws javax.persistence.NonUniqueResultException 返回结果数量不是1(expect 1)
     * @throws javax.persistence.NoResultException        返回结果为0记录
     * @see #hql(String, Object...)
     */

    Object uniqueResultHql(String hql, Object... args);

    /**
     * 单一结果HQL查询.
     *
     * @return 结果
     * @throws javax.persistence.NonUniqueResultException 返回结果数量不是1(expect 1)
     * @throws javax.persistence.NoResultException        返回结果为0记录
     * @see #hql(Class, String, Object...)
     */

    <T> T uniqueResultHql(Class<T> expectType, String hql, Object... args);

    /**
     * 单一结果SQL查询.
     *
     * @return 结果
     * @throws javax.persistence.NonUniqueResultException 返回结果数量不是1(expect 1)
     * @throws javax.persistence.NoResultException        返回结果为0记录
     * @see #sql(String, Object...)
     */

    Object uniqueResultSql(String sql, Object... args);

    /**
     * 单一结果SQL查询.
     *
     * @return 结果
     * @throws javax.persistence.NonUniqueResultException 返回结果数量不是1(expect 1)
     * @throws javax.persistence.NoResultException        返回结果为0记录
     * @see #sql(Class, String, Object...)
     */

    <T> T uniqueResultSql(Class<T> expectType, String sql, Object... args);

    /**
     * 查询第一条数据
     *
     * @param hql  hql语句
     * @param args hql查询参数
     * @return
     */

    Object topResultHql(String hql, Object... args);

    /**
     * 查询第一条数据
     *
     * @param expectType 期望返回的结果集类型
     * @param hql        hql语句
     * @param args       hql查询参数
     * @return
     */

    <T> T topResultHql(Class<T> expectType, String hql, Object... args);

    /**
     * 查询第一条数据
     *
     * @param sql  sql语句
     * @param args sql查询参数
     * @return
     */

    Object topResultSql(String sql, Object... args);

    /**
     * 查询第一条数据
     *
     * @param expectType 期望返回的结果集类型
     * @param sql        sql语句
     * @param args       sql查询参数
     * @return
     */

    <T> T topResultSql(Class<T> expectType, String sql, Object... args);

    /**
     * 查询前几条数据
     *
     * @param expectType 期望返回的结果集类型
     * @param top        期望查询的前几条数据
     * @param sql        sql语句
     * @param args       sql查询参数
     * @return
     */

    <T> List<T> topResultSql(Class<T> expectType, int top, String sql, Object... args);

    /**
     * 查询前几条数据
     *
     * @param expectType 期望返回的结果集类型
     * @param top        期望查询的前几条数据
     * @param hql        hql语句
     * @param args       hql查询参数
     * @return
     */

    <T> List<T> topResultHql(Class<T> expectType, int top, String hql, Object... args);

    /**
     * hql 语句更新
     *
     * @param hql  hql语句
     * @param args hql参数
     * @return
     */

    int updateHql(String hql, Object... args);

    /**
     * hql 语句更新
     *
     * @param sql  sql语句
     * @param args sql参数
     * @return
     */

    int updateSql(String sql, Object... args);

    /**
     * hql 分页查询
     *
     * @param hql      hql语句
     * @param page     页码 （从0开始）
     * @param pageSize 每页大小
     * @param args     查询参数
     * @return
     */

    List<?> hqlPager(String hql, int page, int pageSize, Object... args);

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

    <T> List<T> hqlPager(Class<T> expectType, String hql, int page, int pageSize, Object... args);

    /**
     * sql 分页查询
     *
     * @param sql      sql语句
     * @param page     页码 （从0开始）
     * @param pageSize 每页大小
     * @param args     查询参数
     * @return
     */

    List<?> sqlPager(String sql, int page, int pageSize, Object... args);

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

    <T> List<T> sqlPager(Class<T> expectType, String sql, int page, int pageSize, Object... args);

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

    <T> List<T> hqlPager(Class<T> expectType, Map<String, Object> params, List<Sort.Order> orders, int page, int pageSize);
}
