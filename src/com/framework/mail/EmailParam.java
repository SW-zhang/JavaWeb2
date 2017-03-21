package com.framework.mail;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

/**
 * @author wang
 * @create 2017-03-21 12:03
 **/
public class EmailParam {

    /**
     * 接收者 **必填
     */
    private String[] receivers;

    /**
     * 邮件抄送人
     */
    private String[] cc;

    /**
     * 邮件密送人
     */
    private String[] bcc;

    /**
     * 主题
     */
    private String subject;

    /**
     * 模板 **必填
     */
    private String template;

    /**
     * 模板参数
     */
    private Map<String, Object> templateParams;

    /**
     * 模板里面的图片 key为模板中的sourceId
     * 例：<img src='cid:file'/> key为file
     */
    private Map<String, File> images;

    /**
     * 附件
     */
    private File[] files;

    public String[] getReceivers() {
        return receivers;
    }

    public void setReceivers(String[] receivers) {
        this.receivers = receivers;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Map<String, Object> getTemplateParams() {
        return templateParams;
    }

    public void setTemplateParams(Map<String, Object> templateParams) {
        this.templateParams = templateParams;
    }

    public Map<String, File> getImages() {
        return images;
    }

    public void setImages(Map<String, File> images) {
        this.images = images;
    }

    public File[] getFiles() {
        return files;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "EmailParam{" +
                "receivers=" + Arrays.toString(receivers) +
                ", cc=" + Arrays.toString(cc) +
                ", bcc=" + Arrays.toString(bcc) +
                ", subject='" + subject + '\'' +
                ", template='" + template + '\'' +
                ", templateParams=" + templateParams +
                ", images=" + images +
                ", files=" + Arrays.toString(files) +
                '}';
    }
}
