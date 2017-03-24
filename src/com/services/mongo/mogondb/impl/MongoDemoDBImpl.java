package com.services.mongo.mogondb.impl;

import com.services.mongo.entity.MongoDemoEntity;
import com.services.mongo.mogondb.MongoDemoSweeper;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Spring data mongo 的扩展
 * 注：此类的类名必须是被扩展类名+Impl的方式
 **/
public class MongoDemoDBImpl implements MongoDemoSweeper {

    @PersistenceContext
    private MongoOperations mongo;

    @Override
    public List<MongoDemoEntity> findList(String name, Integer age) {
        Criteria where = Criteria.where("name").is(name)
                .and("age").is(age);
        Query query = Query.query(where);
        return mongo.find(query, MongoDemoEntity.class);
    }


}
