package com.framework.mail;

import com.framework.mail.base.EmailVo;
import com.framework.mail.base.MailService;
import com.framework.properties.Properties;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.File;
import java.util.Map;

/**
 * @author wang
 * @create 2017-03-21 9:35
 **/
@Service("sendEmailService")
public class SendEmailServiceImpl implements SendEmailService {
    private final Logger logger = LoggerFactory.getLogger(SendEmailServiceImpl.class);

    @Autowired
    private MailService mailService;

    @Autowired
    private Properties properties;

    /**
     * @param receivers 接受者
     * @param content   内容
     */
    @Override
    public void sendMessage(String[] receivers, String content) {
        sendMessage(receivers, "", content);
    }

    /**
     * @param receivers 接受者
     * @param template  模板名称
     * @param params    参数
     */
    @Override
    public void sendMessage(String[] receivers, String template, Map<String, Object> params) {
        sendMessage(receivers, "", template, params);
    }

    /**
     * 发送简单邮件
     *
     * @param receivers 接受者
     * @param subject   主题
     * @param content   内容
     */
    @Override
    public void sendMessage(String[] receivers, String subject, String content) {
        EmailVo emailVo = new EmailVo(getSender(), receivers, subject);
        emailVo.setEmailContent(content);
        mailService.sendEmailMessageOfSimpleText(emailVo);
    }

    /**
     * 发送邮件
     *
     * @param receivers 接受者
     * @param content   内容
     * @param files     附件
     */
    @Override
    public void sendMessage(String[] receivers, String content, File... files) {
        sendMessage(receivers, "", content, files);
    }

    /**
     * 发送带附件的邮件
     *
     * @param receivers 接受者
     * @param subject   主题
     * @param content   内容
     * @param files     附件
     */
    @Override
    public void sendMessage(String[] receivers, String subject, String content, File... files) {
        EmailVo emailVo = new EmailVo(getSender(), receivers, subject);
        emailVo.setEmailContent(content);
        emailVo.setAttachFile(files);
        try {
            mailService.sendEmailMessage(emailVo);
        } catch (MessagingException e) {
            e.printStackTrace();

        }
    }

    /**
     * 模板发送邮件
     *
     * @param receivers 接受者
     * @param subject   主题
     * @param template  模板名称
     * @param params    参数
     * @param files     附件
     */
    @Override
    public void sendMessage(String[] receivers, String subject, String template, Map<String, Object> params, File... files) {
        sendMessage(receivers, subject, template, params, null, files);
    }

    /**
     * 模板发送邮件
     *
     * @param receivers 接受者
     * @param subject   主题
     * @param template  模板名称
     * @param params    参数
     * @param images    图片
     * @param files     附件
     */
    @Override
    public void sendMessage(String[] receivers, String subject, String template, Map<String, Object> params, Map<String, File> images, File... files) {
        EmailVo emailVo = new EmailVo(getSender(), receivers, subject);
        emailVo.setEmailContent(mailService.geFreeMarkerTemplateContent(template, params));
        emailVo.setAttachFile(files);
        emailVo.setImages(images);
        try {
            mailService.sendEmailMessage(emailVo);
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error("send message exception:" + e.getMessage(), e);
        }

    }

    /**
     * 发送带附件的邮件 && 图片 && 使用模板
     *
     * @param param
     */
    @Override
    public void sendMessage(EmailParam param) {
        EmailVo emailVo = new EmailVo();
        try {
            BeanUtils.copyProperties(emailVo, param);
            emailVo.setSender(getSender());
            emailVo.setEmailContent(mailService.geFreeMarkerTemplateContent(param.getTemplate(), param.getTemplateParams()));
            emailVo.setAttachFile(param.getFiles());
            mailService.sendEmailMessage(emailVo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("send message exception:" + e.getMessage(), e);
        }
    }

    /**
     * 获取发件人
     *
     * @return 发件人邮箱
     */
    private String getSender() {
        return properties.getValue("mail.sender");
    }
}
