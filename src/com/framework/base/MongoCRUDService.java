package com.framework.base;

import com.mongodb.CommandResult;
import com.mongodb.DBObject;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface MongoCRUDService {

    <T> List<T> findAll(Class<T> entityClass);

    <T> List<T> find(Class<T> entityClass, Criteria query);

    <T> T findOne(Class<T> entityClass, Criteria query);


    <T> T findById(Class<T> entityClass, Object id);

    <T> long count(Class<T> entityClass);

    <T> long count(Class<T> entityClass, Criteria query);

    <T> void save(T entity);

    <T> void save(List<T> entitys);

    <T> void delete(T entity);

    <T> void delete(Class<T> entityClass, Criteria query);

    <T> void updateFirst(Class<T> entityClass, Criteria query, Update update);

    <T> void update(Class<T> entityClass, Criteria query, Update update);

    <T> void saveOrUpdate(Class<T> entityClass, Criteria query, Update update);

    <T> List<T> pager(Class<T> entityClass, Integer page, Integer pageSize);

    <T> List<T> pager(Class<T> entityClass, Integer page, Integer pageSize, Sort sort);

    <T> List<T> pager(Class<T> entityClass, Criteria query, Integer page, Integer pageSize, Sort sort);

    <T> GroupByResults<T> group(Class<T> entityClass, GroupBy groupBy);

    <T> GroupByResults<T> group(Class entityClass, Class<T> returnType, GroupBy groupBy);

    <T> GroupByResults<T> group(Class<T> entityClass, Criteria criteria, GroupBy groupBy);

    <T> GroupByResults<T> group(Class entityClass, Class<T> returnType, Criteria criteria, GroupBy groupBy);

    <T> MapReduceResults<T> mapReduce(Class entityClass, String mapFunction, String reduceFunction, Class<T> returnType);

    CommandResult executeCommand(String json);

    CommandResult executeCommand(DBObject command);
}
