package com.framework.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * @author wang
 * @create 2017-03-15 15:30
 **/
@Configuration
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:mongodb.properties"),
})
@Service
public class Properties {

    @Autowired
    Environment env;

    private final String envConfigPath_default = "MYENV_CONFIG";

    public String getValue(String key) {
        return env.getProperty(key);
    }

    public String getValue(String key, String defaultValue) {
        return env.getProperty(key, defaultValue);
    }

    public <T> T getValue(String key, Class<T> targetType) {
        return env.getProperty(key, targetType);
    }

    public <T> T getValue(String key, Class<T> targetType, T defaultValue) {
        return env.getProperty(key, targetType, defaultValue);
    }

}
