package com.framework.security.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * 初始化spring security 相当于在web.xml 添加 org.springframework.web.filter.DelegatingFilterProxy  的filter
 *
 * @author wang
 * @create 2017-03-23 15:02
 **/
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

}