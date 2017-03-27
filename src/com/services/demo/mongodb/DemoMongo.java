package com.services.demo.mongodb;

import com.services.demo.entity.MongoDemoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DemoMongo extends MongoRepository<MongoDemoEntity, String>, DemoMongoSweeper {

    MongoDemoEntity findByName(String name);

    @Query("{'name' : { '$regex' : ?0}}")
    List<MongoDemoEntity> findByNameLike(String name);

    @Query("{'name' : ?0, 'age' : ?1}")
    List<MongoDemoEntity> findByNameAndAge(String name, Integer age);

    @Query("{'age' : { '$gte': ?0 }}")
    List<MongoDemoEntity> findByGteAge(Integer age);

    @Query("{'id' : { '$in': ?0 }}")
    List<MongoDemoEntity> findByAgeIn(List ids);
}
