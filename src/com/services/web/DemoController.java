package com.services.web;

import com.services.entity.Function;
import com.services.servcie.DemoService;
import com.framework.response.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public AjaxResult list(Function function, WebRequest request) {
        List<Function> result = demoService.list();
        return AjaxResult.successObject(result);
    }

    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public AjaxResult detail(@PathVariable Long id) {
        Function demo = demoService.findOne(id);
        return AjaxResult.successObject(demo);
    }

    @RequestMapping(value = "/add_init")
    public ModelAndView addInit() {
        ModelAndView view = new ModelAndView();
        view.setViewName("/demo/add_page");
        view.addObject("init_data", "");
        return view;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public AjaxResult add() {
        try {
            demoService.add(new Function());
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("异常");
        }
        return AjaxResult.success();
    }
}
