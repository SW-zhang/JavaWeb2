package com.framework.context;

import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ContextLoader;

import java.util.Locale;
import java.util.Map;

public class ContextHolder {
    private static ApplicationContext ac;
    private static Locale local;

    private ContextHolder() {
    }

    public synchronized static void setApplicationContext(ApplicationContext act) {
        ac = act;
    }

    public static ApplicationContext getApplicationContext() {
        return ac;
    }

    public static Locale getLocal() {
        return local;
    }

    public static void setLocal(Locale local) {
        ContextHolder.local = local;
    }


    public static Object getBean(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        return ContextLoader.getCurrentWebApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return ContextLoader.getCurrentWebApplicationContext().getBean(clazz);
    }

    /**
     * 提供接口或类，返回其spring管理的bean的实现
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return ContextLoader.getCurrentWebApplicationContext().getBeansOfType(clazz);
    }

    /**
     * 查询是否有指定beanName的spring管理bean
     */
    public static boolean containsBean(String beanName) {
        return ContextLoader.getCurrentWebApplicationContext().containsBean(beanName);
    }
}
