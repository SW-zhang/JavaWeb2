package com.framework.security.configuration;

import com.framework.common.MD5Util;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

/**
 * 自定义用户权限
 *
 * @author wang
 * @create 2017-03-23 14:09
 **/
public class SecurityUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!StringUtils.isEmpty(username)) {
            //根据用户提交的用户名 去文件系统||关系型数据库||nosql数据库||...查询出密码和权限 组装User对象返回

//            DemoService demoService = (DemoService) ContextHolder.getBean("demoService");
//            System.out.println(Arrays.toString(demoService.findAll().toArray()));

            if ("admin".equals(username)) {
                return new User("admin", MD5Util.getMD5("admin123"), AuthorityUtils.createAuthorityList("ADMIN"));
            }
        }

        throw new UsernameNotFoundException(String.format("User:'%s' not found.", username));
    }
}
