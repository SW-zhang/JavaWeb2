package com.services.mongo.mogondb;

import com.services.mongo.entity.MongoDemoEntity;

import java.util.List;

/**
 * Spring data mongo 的扩展
 * 注：被扩展的接口必须继承本接口
 **/
public interface MongoDemoSweeper {

    List<MongoDemoEntity> findList(String name, Integer age);
}
