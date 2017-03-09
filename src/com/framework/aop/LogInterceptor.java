package com.framework.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author wang
 * @create 2017-03-08 17:45
 **/
@Component
@Order(1)//值越小 执行权限越高
@Aspect
public class LogInterceptor {
    private static final Logger log = LoggerFactory.getLogger(LogInterceptor.class);

    /**
     * 调用service方法前后加日志
     *
     * @param point 切入点
     * @return object 返回值
     * @throws Throwable
     */
    @Around("execution (* com.services..*Service*.*(..))")
    public Object serviceProcess(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        String methodName = point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName();
        log.info(String.format("%s, method: %s, param: %s", Thread.currentThread(), methodName, JSON.toJSONString(args)));
        long start_time = System.currentTimeMillis();

        Object returnValue = point.proceed(args);

        long end_time = System.currentTimeMillis();
        log.info(String.format("%s, use_time: %d ms, method: %s, return:{%s}", Thread.currentThread(), end_time - start_time, methodName, JSON.toJSONString(returnValue)));
        return returnValue;
    }

}
