package com.framework.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * 实体管理器，直接对Entity对象进行持久操作
 * 此管理器仅用于对不确定类型entity操作使用，
 * 已明确类型情况，即已知对应entity对应dao的情况禁用此类
 *
 * @ClassName: NativeEntityManager
 * @Description:
 */
@Service("entityManager")
@Transactional(rollbackFor = {Exception.class})
public class NativeEntityManager {

    @PersistenceContext
    private EntityManager em;

    public List<Object[]> find(String sql) {
        Query query = em.createNativeQuery(sql);
        return query.getResultList();
    }


    /**
     * 根据entity对应类及主键值直接获取entity对象
     *
     * @param entityClass entity类
     * @param primaryKey  对象主键值
     * @return
     * @Methods Name find
     */
    public <T> T find(Class<T> entityClass, Object primaryKey) {
        return em.find(entityClass, primaryKey);
    }

    /**
     * 根据entity对应类名及主键值直接获取entity对象
     *
     * @param entityClassName entity类名
     * @param primaryKey      对象主键值
     * @return
     * @Methods Name find
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Object find(String entityClassName, Long primaryKey) {
        Class clazz;
        try {
            clazz = Class.forName(entityClassName);
            return find(clazz, primaryKey);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存entity对象
     *
     * @param entity
     * @return
     * @Methods Name save
     */
    public <T> T save(T entity) {
        return em.merge(entity);
    }

    /**
     * 删除对象
     *
     * @param entity
     * @Methods Name remove
     */
    public void remove(Object entity) {
        em.remove(entity);
    }
}
