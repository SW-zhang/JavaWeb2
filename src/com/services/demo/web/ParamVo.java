package com.services.demo.web;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author wang
 * @create 2017-03-17 14:35
 **/
public class ParamVo {
    @NotEmpty
    private String name;

    @NotNull
    @Size(min = 6, max = 20)
    private String password;

    @Email
    private String email;

    @Pattern(regexp = "1[34578]\\d{9}", message = "不是一个正确的手机号")
    private String tel;

    @URL
    private String url;

    @Past(message = "必须在当前时间之前")
    private Date start_time;

    @Future(message = "必须在当前时间之后")
    private Date end_time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
