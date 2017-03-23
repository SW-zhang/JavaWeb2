package com.services.dao;

import com.framework.base.BaseDao;
import com.services.entity.Demo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("JpaQlInspection")
public interface DemoDao extends BaseDao<Demo, Long> {

    @Query(value = "select count(id) from demo where path = ?1", nativeQuery = true)
    long count(String path);

    @Query("select count(id) from Demo where level = ?1")
    long count(Integer level);

    @Query("select id,name,level from Demo where name = ?1")
    Object[] findbyName(String name);

    @Modifying
    @Transactional
    @Query("update Demo f set status = 0 where id = ?1")
    void delete(Long id);

    @Query("select id from Demo where path = ?1")
    List<Long> findIdByPath(String path);

    @Query(value = "select d.* from demo d join user_func uf on d.id = uf.func_id where uf.user_id = ?1", nativeQuery = true)
    List<Demo> findByUserId(Long user_id);

}
