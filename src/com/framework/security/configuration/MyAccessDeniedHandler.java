package com.framework.security.configuration;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义权限验证失败处理  ajax 请求返回json  其他请求返回指定页面
 *
 * @author wang
 * @create 2017-04-01 14:38
 **/
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    private String accessDeniedUrl;

    public MyAccessDeniedHandler(String accessDeniedUrl) {
        this.accessDeniedUrl = accessDeniedUrl;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
        if (isAjax) {
            String jsonObject = "{\"success\":false,\"error\":\"You are not privileged to request this resource.\"}";
            String contentType = "application/json";
            response.setContentType(contentType);
            PrintWriter out = response.getWriter();
            out.print(jsonObject);
            out.flush();
            out.close();
        } else {
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
            response.sendRedirect(basePath + accessDeniedUrl);
        }
    }

}
