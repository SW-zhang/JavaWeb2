package com.framework.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wang
 * @create 2017-03-23 12:30
 **/
@Controller
public class DefaultController {

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/403")
    public String error_403() {
        return "error/403";
    }

    @RequestMapping(value = "/500")
    public String error_500() {
        return "error/500";
    }
}
