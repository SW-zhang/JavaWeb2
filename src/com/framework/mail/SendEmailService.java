package com.framework.mail;

import java.io.File;
import java.util.Map;

/**
 * @author wang
 * @create 2017-03-21 9:27
 **/
public interface SendEmailService {
    /**
     * 发送简单的邮件
     *
     * @param receivers 接受者
     * @param content   内容
     */
    void sendMessage(String[] receivers, String content);

    /**
     * 发送带附件的邮件 && 图片 && 使用模板
     *
     * @param receivers 接受者
     * @param template  模板名称
     * @param params    参数
     */
    void sendMessage(String[] receivers, String template, Map<String, Object> params);

    /**
     * 发送简单的邮件
     *
     * @param receivers 接受者
     * @param subject   主题
     * @param content   内容
     */
    void sendMessage(String[] receivers, String subject, String content);

    /**
     * 发送带附件的邮件
     *
     * @param receivers 接受者
     * @param content   内容
     * @param files     附件
     */
    void sendMessage(String[] receivers, String content, File... files);

    /**
     * 发送带附件的邮件
     *
     * @param receivers 接受者
     * @param subject   主题
     * @param content   内容
     * @param files     附件
     */
    void sendMessage(String[] receivers, String subject, String content, File... files);

    /**
     * 发送带附件的邮件 && 使用模板
     *
     * @param receivers 接受者
     * @param subject   主题
     * @param template  模板名称
     * @param params    参数
     * @param files     附件
     */
    void sendMessage(String[] receivers, String subject, String template, Map<String, Object> params, File... files);

    /**
     * 发送带附件的邮件 && 图片 && 使用模板
     *
     * @param receivers 接受者
     * @param subject   主题
     * @param template  模板名称
     * @param params    参数
     * @param images    图片
     * @param files     附件
     */
    void sendMessage(String[] receivers, String subject, String template, Map<String, Object> params, Map<String, File> images, File... files);

    /**
     * 发送带附件的邮件 && 图片 && 使用模板
     *
     * @param param
     */
    void sendMessage(EmailParam param);
}
