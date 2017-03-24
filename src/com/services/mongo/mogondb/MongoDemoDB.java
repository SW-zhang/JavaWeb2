package com.services.mongo.mogondb;

import com.services.mongo.entity.MongoDemoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoDemoDB extends MongoRepository<MongoDemoEntity, String>, MongoDemoSweeper {

    MongoDemoEntity findByName(String name);

    @Query("{'name' : ?0, 'age' : ?1}")
    MongoDemoEntity findByNameAndAge(String name, Integer age);
}
