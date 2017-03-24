package com.services.dao.impl;

import com.services.dao.DemoSweeper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Spring data jpa 的扩展
 * 注：此类的类名必须是被扩展类名+Impl的方式
 *
 * @author wang
 * @create 2017-03-24 15:28
 **/
public class DemoDaoImpl implements DemoSweeper {

    @PersistenceContext
    private EntityManager em;

    public List findAllTest() {
        return em.createQuery("select name, path from Demo").getResultList();
    }


}
