package com.framework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * spring secrity 配置文件(相当于application-secrity.xml 文件)
 *
 * @author wang
 * @create 2017-03-23 10:39
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * 给用户分配权限
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new SecurityUserDetailsService())
                .passwordEncoder(new SecrityPasswordEncoder());
    }

    /**
     * url拦截 进行权限验证
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()//无条件允许访问
                .antMatchers("/configuration/**").hasAnyAuthority("ADMIN", "MANAGER")//必须有ADMIN || MANAGER 的权限才能访问
                .antMatchers("/admin/**").hasAuthority("ADMIN")//必须有ADMIN权限才能访问
                .antMatchers("/demo/**").hasAuthority("ADMIN")//必须有ADMIN权限才能访问

                //修改登陆页面 以及 默认登陆成功跳转的页面
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/index")
                //添加rememberMe功能（会在cookie中存储一个token 这个token 被设置4周内有效 2419200秒）
                .and().rememberMe().tokenValiditySeconds(2419200).key("securityKey")
                //权限验证失败跳转到403 forbidden 页面
                .and().exceptionHandling().accessDeniedPage("/403")
                .and().csrf();
    }
}
