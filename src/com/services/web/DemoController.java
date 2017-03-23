package com.services.web;

import com.framework.querycore.PageParam;
import com.framework.common.Properties;
import com.framework.common.AjaxResult;
import com.services.entity.Demo;
import com.services.servcie.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @Autowired
    private Properties properties;

    @RequestMapping(value = "/list")
    @ResponseBody
    public AjaxResult list() {
        List<Demo> result = demoService.list();
        return AjaxResult.successObject(result);
    }

    @RequestMapping(value = "/list_page")
    public ModelAndView list(WebRequest request, Demo param, PageParam pageParam) {
        ModelAndView view = new ModelAndView();
        view.addObject("page", demoService.findAll(param, pageParam));
        view.addObject("pageParam", request.getParameterMap());
        view.setViewName("/demo/pager_demo");
        return view;
    }

    @RequestMapping(value = "/detail/{id}")
    @ResponseBody
    public AjaxResult detail(@PathVariable Long id) {
        Demo demo = demoService.findOne(id);
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
    public AjaxResult add(@Valid Demo demo, Errors errors) {
        try {
            if (errors.hasErrors()) {
                return error(errors);
            }
            demoService.add(demo);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("异常");
        }
        return AjaxResult.success();
    }

    @RequestMapping(value = "/paramValid")
    @ResponseBody
    public AjaxResult paramValid(@Valid ParamVo param, Errors errors) {
        if (errors.hasErrors()) {
            return error(errors);
        }
        return AjaxResult.success();
    }

    /**
     * 返回表单验证失败的信息
     *
     * @param errors
     * @return
     */
    private AjaxResult error(Errors errors) {
        if (errors.hasErrors()) {
            Map<String, String> errorMessages = new HashMap<>();
            List<FieldError> errorInfos = errors.getFieldErrors();
            for (FieldError errorInfo : errorInfos) {
                errorMessages.put(errorInfo.getField(), errorInfo.getDefaultMessage());
            }
            return AjaxResult.errorObject(errorMessages);
        }
        return AjaxResult.success();
    }
}
