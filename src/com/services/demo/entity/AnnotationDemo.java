package com.services.demo.entity;

import com.framework.base.IdEntity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "annotation_demo",
        //唯一索引
        uniqueConstraints = @UniqueConstraint(name = "unique_code", columnNames = {"code"}),
        //普通索引
        indexes = {
                @Index(name = "i_name", columnList = "name", unique = false)})
public class AnnotationDemo extends IdEntity {
    private static final long serialVersionUID = 1587600973904646855L;

    private String code;
    private String name;
    private String reason;
    private Date time;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(columnDefinition = "TEXT")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Transient
    public String getTimeFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnnotationDemo that = (AnnotationDemo) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (reason != null ? !reason.equals(that.reason) : that.reason != null) return false;
        return !(time != null ? !time.equals(that.time) : that.time != null);

    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AnnotationDemo{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", reason='" + reason + '\'' +
                ", time=" + time +
                "} " + super.toString();
    }
}
