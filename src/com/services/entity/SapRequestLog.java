package com.services.entity;

import com.framework.id.IdEntity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "sap_request_log", indexes = {
        @Index(name = "i_address", columnList = "origin_address", unique = false),
        @Index(name = "i_target_sap", columnList = "target_sap", unique = false),
        @Index(name = "i_target_sap_method", columnList = "target_sap_method", unique = false),
        @Index(name = "i_request_time", columnList = "requestTime", unique = false)})
public class SapRequestLog extends IdEntity {
    private static final long serialVersionUID = 1L;

    private String origin_address;
    private String target_sap;
    private String target_sap_method;
    private String request_content;
    private String response_content;
    private Boolean result;
    private String fail_reason;
    private Boolean business_result;
    private String business_fail_reason;
    private String fail_reason_remark;
    private Long requestTime;
    private Long responseTime;

    private String request_startTime;
    private String request_endTime;

    public String getFail_reason_remark() {
        return fail_reason_remark;
    }

    public void setFail_reason_remark(String fail_reason_remark) {
        this.fail_reason_remark = fail_reason_remark;
    }

    public Boolean getBusiness_result() {
        return business_result;
    }

    public void setBusiness_result(Boolean business_result) {
        this.business_result = business_result;
    }

    public String getBusiness_fail_reason() {
        return business_fail_reason;
    }

    public void setBusiness_fail_reason(String business_fail_reason) {
        this.business_fail_reason = business_fail_reason;
    }

    public String getOrigin_address() {
        return origin_address;
    }

    public void setOrigin_address(String origin_address) {
        this.origin_address = origin_address;
    }

    public String getTarget_sap() {
        return target_sap;
    }

    public void setTarget_sap(String target_sap) {
        this.target_sap = target_sap;
    }

    public String getTarget_sap_method() {
        return target_sap_method;
    }

    public void setTarget_sap_method(String target_sap_method) {
        this.target_sap_method = target_sap_method;
    }

    @Column(columnDefinition = "TEXT")
    public String getRequest_content() {
        return request_content;
    }

    public void setRequest_content(String request_content) {
        this.request_content = request_content;
    }

    @Column(columnDefinition = "TEXT")
    public String getResponse_content() {
        return response_content;
    }

    public void setResponse_content(String response_content) {
        this.response_content = response_content;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    @Column(columnDefinition = "TEXT")
    public String getFail_reason() {
        return fail_reason;
    }

    public void setFail_reason(String fail_reason) {
        this.fail_reason = fail_reason;
    }

    public Long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = requestTime;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Transient
    public String getRequest_startTime() {
        return request_startTime;
    }

    public void setRequest_startTime(String request_startTime) {
        this.request_startTime = request_startTime;
    }

    @Transient
    public String getRequest_endTime() {
        return request_endTime;
    }

    public void setRequest_endTime(String request_endTime) {
        this.request_endTime = request_endTime;
    }

    @Transient
    public String getUseTime() {
        return String.valueOf((responseTime - requestTime));
    }

    @Transient
    public String getRequestTimeFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(requestTime));
    }
}
