package com.framework.mail.base;

import javax.mail.MessagingException;
import java.util.Map;

/**
 * Email通知消息发送
 *
 * @author wang
 * @create 2017-03-20 18:01
 **/
public interface MailService {
    /**
     * 发送简单文本Email消息
     *
     * @param emailVo
     */
    void sendEmailMessageOfSimpleText(EmailVo emailVo);

    /**
     * 带附件邮件发送
     *
     * @param emailVo
     */
    void sendEmailMessage(EmailVo emailVo) throws MessagingException;

    /**
     * 获取模板
     *
     * @param template
     * @param model
     * @return
     */
    String geFreeMarkerTemplateContent(String template, Map<String, Object> model);

}
