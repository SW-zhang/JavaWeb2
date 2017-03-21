package com.framework.mail.base;

import com.alibaba.fastjson.JSON;
import freemarker.template.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;
import java.util.Map;

/**
 * @author wang
 * @create 2017-03-20 17:53
 **/
@Service("mailService")
public class MailServiceImpl implements MailService {
    private final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    Configuration freemarkerConfiguration;

    /**
     * 发送简单文本Email消息
     *
     * @param emailVo
     */
    @Override
    public void sendEmailMessageOfSimpleText(EmailVo emailVo) {
        logger.debug(String.format("send simple message param:{%s}", JSON.toJSONString(emailVo)));
        SimpleMailMessage simpleTextMessage = new SimpleMailMessage();
        simpleTextMessage.setFrom(emailVo.getSender());
        simpleTextMessage.setTo(emailVo.getReceivers());
        if (emailVo.getBcc() != null) {
            simpleTextMessage.setBcc(emailVo.getBcc());
        }
        if (emailVo.getCc() != null) {
            simpleTextMessage.setCc(emailVo.getCc());
        }
        simpleTextMessage.setSubject(StringUtils.trimToEmpty(emailVo.getSubject()));
        simpleTextMessage.setText(StringUtils.trimToEmpty(emailVo.getEmailContent()));

        simpleTextMessage.setSentDate(new Date());

        mailSender.send(simpleTextMessage);
        logger.debug("send simple message success");
    }

    /**
     * 带附件邮件发送
     *
     * @param emailVo
     */
    @Override
    public void sendEmailMessageOFFiles(EmailVo emailVo) throws MessagingException {
        logger.debug(String.format("send message param:{%s}", JSON.toJSONString(emailVo)));
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(emailVo.getSender());
        helper.setTo(emailVo.getReceivers());
        helper.setSubject(StringUtils.trimToEmpty(emailVo.getSubject()));

        if (StringUtils.isNotBlank(emailVo.getEmailContent())) {
            helper.setText(emailVo.getEmailContent(), true);
        }
        if (emailVo.getBcc() != null) {
            helper.setBcc(emailVo.getBcc());
        }
        if (emailVo.getCc() != null) {
            helper.setCc(emailVo.getCc());
        }
        if (emailVo.getAttachFile() != null && emailVo.getAttachFile().length > 0) {
            for (File file : emailVo.getAttachFile()) {
                FileSystemResource fileSystemResource = new FileSystemResource(file);
                helper.addAttachment(file.getName(), fileSystemResource);
            }
        }
        if (emailVo.getImages() != null && !emailVo.getImages().isEmpty()) {
            for (Map.Entry<String, File> entry : emailVo.getImages().entrySet()) {
                helper.addInline(entry.getKey(), entry.getValue());
            }
        }
        helper.setSentDate(new Date());

        mailSender.send(message);
        logger.debug("send simple message success");
    }

    /**
     * 获取模板并填充参数
     *
     * @param template
     * @param model
     * @return
     */
    @Override
    public String geFreeMarkerTemplateContent(String template, Map<String, Object> model) {
        logger.debug(String.format("get message template: %s, param:{%s}", template, model.toString()));
        StringBuilder content = new StringBuilder();
        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfiguration.getTemplate(template), model));
            return content.toString();
        } catch (Exception e) {
            logger.error("Exception occured while processing fmtemplate:" + e.getMessage(), e);
        }
        return "";
    }
}
