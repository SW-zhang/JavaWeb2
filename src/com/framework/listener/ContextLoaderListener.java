package com.framework.listener;

import com.framework.context.ContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.util.Locale;

public class ContextLoaderListener extends org.springframework.web.context.ContextLoaderListener {
    private static final Logger log = LoggerFactory.getLogger(ContextLoaderListener.class);

    public ContextLoaderListener() {
    }

    public ContextLoaderListener(WebApplicationContext context) {
        super(context);
    }

    /**
     * Initialize the root web application context.
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        initWebApplicationContext(event.getServletContext());

        ServletContext context = event.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        ContextHolder.setApplicationContext(ctx);
        ContextHolder.setLocal(Locale.getDefault());

    }

    /**
     * Close the root web application context.
     */
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        super.contextDestroyed(event);
    }

}
