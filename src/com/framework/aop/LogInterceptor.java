package com.framework.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author wang
 * @create 2017-03-08 17:45
 **/

/**
 * 切面配置Controller时需要让SpringMVC-servlet.xml 扫描到本类，并且配置<aop:aspectj-autoproxy proxy-target-class="true" />
 * 切面配置Service时需要让applicationContext.xml 扫描到本类，并且配置<aop:aspectj-autoproxy />
 * <p>
 * execution表达式含义：
 * 例： execution (* com.services..*Service*.*(..))
 * 第一个 * 表示返回值为任意类型
 * 第一个.. 表示当前包以及其子包
 * 最后一个* 表示当前类任何方法
 * （..） 表示方法任何参数类型
 */
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

    @Before("execution (* com.services..*Service*.*(..))")
    public void beforMethod(JoinPoint point) {
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
        System.out.println("调用前连接点方法为：" + methodName + ",参数为：" + args);
    }

    @After("execution (* com.services..*Service*.*(..))")
    public void afterMethod(JoinPoint point) {
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
        System.out.println("调用后连接点方法为：" + methodName + ",参数为：" + args);
    }

    /*通过returning属性指定连接点方法返回的结果放置在result变量中，在返回通知方法中可以从result变量中获取连接点方法的返回结果了。*/
    @AfterReturning(value = "execution (* com.services..*Service*.*(..))", returning = "result")
    public void afterReturning(JoinPoint point, Object result) {
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
        System.out.println("连接点方法为：" + methodName + ",参数为：" + args + ",目标方法执行结果为：" + result);
    }

    /*通过throwing属性指定连接点方法出现异常信息存储在ex变量中，在异常通知方法中就可以从ex变量中获取异常信息了*/
    @AfterThrowing(value = "execution (* com.services..*Service*.*(..))", throwing = "ex")
    public void afterThrowing(JoinPoint point, Exception ex) {
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
        System.out.println("连接点方法为：" + methodName + ",参数为：" + args + ",异常为：" + ex);
    }
}
