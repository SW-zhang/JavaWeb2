package com.framework.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wang
 * @create 2017-03-17 17:40
 * controller 异常统一处理
 **/
public class ControllerExceptionHandler extends SimpleMappingExceptionResolver {

    private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @Override
    public ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String requestType = request.getHeader("X-Requested-With");
        Map<String, Object> model = new HashMap<>();
        model.put("ex", ex.getClass().getSimpleName());
        model.put("error", ex.getMessage());
        log.error("====================Controller-ERROR-[" + request.getRequestURI() + "]-BEGIN====================");
        log.error(ex.toString());
        ex.printStackTrace();
        log.error("====================Controller-ERROR-[" + request.getRequestURI() + "]-END====================");
        //如果是ajax请求，response回写类型的异常处理，将异常写入response中提供前台处理
        if ("XMLHttpRequest".equals(requestType)) {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out;
            try {
                out = response.getWriter();
                out.println(ex.toString());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {    //跳转类异常处理，直接跳转至异常提示页面
            return new ModelAndView("/error", model);
        }
    }
}
