package com.services.rmi.impl;

import com.services.rmi.RmiDemoService;

/**
 * @author wang
 * @create 2017-03-31 15:15
 **/
public class RmiDemoServiceImpl implements RmiDemoService {
    @Override
    public String getName() {
        return "demo-test";
    }
}
