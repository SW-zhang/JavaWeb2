package com.framework.interceptor;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局拦截器
 */
public class SystemInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(SystemInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        log.info("[" + url + "]---[params:" + JSON.toJSONString(request.getParameterMap()) + "]");

        System.out.println("you can do something");

//        if (request.getSession().getAttribute("user") == null) {
//            response.sendRedirect(request.getContextPath() + "/index");
//            return false;
//        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        //用于拦截ModelAndView的返回值
    }
}
