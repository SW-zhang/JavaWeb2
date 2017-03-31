package com.framework.base.impl;

import com.framework.base.MongoCRUDService;
import com.mongodb.CommandResult;
import com.mongodb.DBObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mongoCrudService")
public class MongoCRUDServiceImpl implements MongoCRUDService {

//    @Autowired
    private MongoOperations mongo;

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        return mongo.findAll(entityClass);
    }

    @Override
    public <T> List<T> find(Class<T> entityClass, Criteria query) {
        return mongo.find(Query.query(query), entityClass);
    }

    @Override
    public <T> T findOne(Class<T> entityClass, Criteria query) {
        return mongo.findOne(Query.query(query), entityClass);
    }

    @Override
    public <T> T findById(Class<T> entityClass, Object id) {
        return mongo.findById(id, entityClass);
    }

    @Override
    public <T> long count(Class<T> entityClass) {
        return mongo.count(null, entityClass);
    }

    @Override
    public <T> long count(Class<T> entityClass, Criteria query) {
        return mongo.count(Query.query(query), entityClass);
    }

    @Override
    public <T> void save(T entity) {
        mongo.insert(entity);
    }

    @Override
    public <T> void save(List<T> entitys) {
        mongo.insertAll(entitys);
    }

    @Override
    public <T> void delete(T entity) {
        mongo.remove(entity);
    }

    @Override
    public <T> void delete(Class<T> entityClass, Criteria query) {
        mongo.remove(Query.query(query), entityClass);
    }


    @Override
    public <T> void updateFirst(Class<T> entityClass, Criteria query, Update update) {
        mongo.updateFirst(Query.query(query), update, entityClass);
    }

    @Override
    public <T> void update(Class<T> entityClass, Criteria query, Update update) {
        mongo.updateMulti(Query.query(query), update, entityClass);
    }

    @Override
    public <T> void saveOrUpdate(Class<T> entityClass, Criteria query, Update update) {
        mongo.upsert(Query.query(query), update, entityClass);
    }

    @Override
    public <T> List<T> pager(Class<T> entityClass, Integer page, Integer pageSize) {
        return pager(entityClass, page, pageSize, null);
    }

    @Override
    public <T> List<T> pager(Class<T> entityClass, Integer page, Integer pageSize, Sort sort) {
        return pager(entityClass, new Criteria(), page, pageSize, sort);
    }

    @Override
    public <T> List<T> pager(Class<T> entityClass, Criteria criteria, Integer page, Integer pageSize, Sort sort) {
        Assert.assertNotNull(page);
        Assert.assertNotNull(pageSize);
        Query query = Query.query(criteria);
        if (sort != null) {
            query.with(sort);
        }
        return mongo.find(query.skip(page * pageSize).limit(pageSize), entityClass);
    }

    @Override
    public <T> GroupByResults<T> group(Class<T> entityClass, GroupBy groupBy) {
        return mongo.group(collectionName(entityClass), groupBy, entityClass);
    }

    @Override
    public <T> GroupByResults<T> group(Class entityClass, Class<T> returnType, GroupBy groupBy) {
        return mongo.group(collectionName(entityClass), groupBy, returnType);
    }

    @Override
    public <T> GroupByResults<T> group(Class<T> entityClass, Criteria criteria, GroupBy groupBy) {
        return mongo.group(criteria, collectionName(entityClass), groupBy, entityClass);
    }

    @Override
    public <T> GroupByResults<T> group(Class entityClass, Class<T> returnType, Criteria criteria, GroupBy groupBy) {
        return mongo.group(criteria, collectionName(entityClass), groupBy, returnType);
    }

    @Override
    public <T> MapReduceResults<T> mapReduce(Class entityClass, String mapFunction, String reduceFunction, Class<T> returnType) {
        return mongo.mapReduce(collectionName(entityClass), mapFunction, reduceFunction, returnType);
    }

    @Override
    public CommandResult executeCommand(String json) {
        return mongo.executeCommand(json);
    }

    @Override
    public CommandResult executeCommand(DBObject command) {
        return mongo.executeCommand(command);
    }

    private <T> String collectionName(Class<T> entityClass) {
        return mongo.getCollectionName(entityClass);
    }
}
