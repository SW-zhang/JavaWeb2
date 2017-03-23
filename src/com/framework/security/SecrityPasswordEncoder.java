package com.framework.security;

import com.framework.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义登陆密码验证
 *
 * @author wang
 * @create 2017-03-23 14:38
 **/
public class SecrityPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return MD5Util.getMD5(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return StringUtils.equals(MD5Util.getMD5(rawPassword.toString()), encodedPassword);
    }
}
