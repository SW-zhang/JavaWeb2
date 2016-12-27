package com.services.dao;

import com.framework.dao.BaseDao;
import com.services.entity.Function;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("JpaQlInspection")
public interface DemoDao extends BaseDao<Function, Long> {

    @Query(value = "select count(id) from function where path = ?1", nativeQuery = true)
    long count(String path);

    @Query("select count(id) from Function where level = ?1")
    long count(Integer level);

    @Query("select id,name,level from Function where name = ?1")
    Object[] findbyName(String name);

    @Modifying
    @Transactional
    @Query("update Function f set status = 0 where id = ?1")
    void delete(Long id);

    @Query("select id from Function where path = ?1")
    List<Long> findIdByPath(String path);

    @Query(value = "select d.* from function d join user_func uf on d.id = uf.func_id where uf.user_id = ?1", nativeQuery = true)
    List<Function> findByUserId(Long user_id);

}
