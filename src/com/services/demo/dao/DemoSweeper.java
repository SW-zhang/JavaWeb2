package com.services.demo.dao;

import java.util.List;

/**
 * Spring data jpa 的扩展
 * 注：被扩展的接口必须继承本接口
 **/
public interface DemoSweeper {

    List findAllTest();
}
