package com.framework.response;

import java.util.HashMap;
import java.util.Map;

/**
 * AJAX请求返回值
 * 为统一返回值，所有ajax请求全部使用此类对象返回
 *
 * @Class Name AjaxResult
 */
public class AjaxResult extends HashMap<String, Object> {

    /**
     * 获取成功返回值
     *
     * @return AjaxResult
     * @Methods Name successReslut
     */
    public static AjaxResult success() {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("success", true);
        return ajaxResult;
    }

    /**
     * 获取成功返回值及提示信息
     *
     * @return AjaxResult
     * @Methods Name successReslut
     */
    public static AjaxResult success(String msg) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("success", true);
        ajaxResult.put("msg", msg);
        return ajaxResult;
    }

    /**
     * 返回成功对象
     *
     * @param obj
     * @return AjaxResult
     * @Methods Name objectReslut
     */
    public static AjaxResult successObject(Object obj) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("success", true);
        ajaxResult.put("data", obj);
        return ajaxResult;
    }

    /**
     * 返回多个成功对象
     *
     * @param map
     * @return AjaxResult
     * @Methods Name objectReslut
     */
    public static AjaxResult successMap(Map<String, Object> map) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("success", true);
        ajaxResult.putAll(map);
        return ajaxResult;
    }

    /**
     * 失败
     *
     * @return AjaxResult
     * @Methods Name errorReslut
     */
    public static AjaxResult error() {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("success", false);
        return ajaxResult;
    }

    /**
     * 返回失败信息
     *
     * @param error
     * @return AjaxResult
     * @Methods Name errorReslut
     */
    public static AjaxResult error(String error) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("success", false);
        ajaxResult.put("error", error);
        return ajaxResult;
    }

    /**
     * 返回失败对象
     *
     * @param object
     * @return AjaxResult
     * @Methods Name errorReslut
     */
    public static AjaxResult errorObject(Object object) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("success", false);
        ajaxResult.put("data", object);
        return ajaxResult;
    }

    /**
     * 返回多条失败信息
     *
     * @param map
     * @return AjaxResult
     * @Methods Name errorReslut
     */
    public static AjaxResult errorObject(Map<String, Object> map) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("success", false);
        ajaxResult.putAll(map);
        return ajaxResult;
    }

}
