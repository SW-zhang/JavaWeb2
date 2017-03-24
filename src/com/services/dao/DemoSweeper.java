package com.services.dao;

import java.util.List;

/**
 * Spring data jpa 的扩展
 * 注：被扩展的接口必须继承本接口
 *
 * @author wang
 * @create 2017-03-24 15:28
 **/
public interface DemoSweeper {

    List findAllTest();
}
