package com.services.demo.servcie;

import com.framework.querycore.PageParam;
import com.services.demo.entity.Demo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DemoService {

    /**
     * 分页对象用法
     *
     * @param demo
     * @param pageParam
     * @return
     */
    Page<Demo> findAll(Demo demo, PageParam pageParam);

    /**
     * or的用法
     *
     * @param keys
     * @return
     */
    @Cacheable("allDemosCache")
    List<Demo> findAll(List<String> keys);

    /**
     * 使用扩展接口的方法
     *
     * @return
     */
    List findAll();

    @CachePut(value = "demoCache", key = "#result.id")
    Demo add(Demo demo);

    List<Demo> list();

    @Cacheable("demoCache")
    Demo findOne(Long id);

    @CacheEvict("demoCache")
    void remove(Long id);
}
