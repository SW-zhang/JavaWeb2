package com.framework.upload.entity;

import com.framework.base.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

@Entity
@Table(name = "upload_files",
        //唯一索引
        uniqueConstraints = @UniqueConstraint(name = "unique_entry_code", columnNames = {"entryCode"}))
public class UploadFile extends IdEntity {
    private static final long serialVersionUID = -5077519718073266357L;

    private String entryCode;
    private String realFileName;//真实文件名
    private String saveFilePath;//服务器保持文件夹
    private String saveFileName;//服务器保持文件名
    private Integer state;//状态  1：可用  2：不可用（可删除）
    private Date createTime;

    public String getEntryCode() {
        return entryCode;
    }

    public void setEntryCode(String entryCode) {
        this.entryCode = entryCode;
    }

    public String getRealFileName() {
        return realFileName;
    }

    public void setRealFileName(String realFileName) {
        this.realFileName = realFileName;
    }

    public String getSaveFilePath() {
        return saveFilePath;
    }

    public void setSaveFilePath(String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

    public String getSaveFileName() {
        return saveFileName;
    }

    public void setSaveFileName(String saveFileName) {
        this.saveFileName = saveFileName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UploadFile that = (UploadFile) o;

        if (entryCode != null ? !entryCode.equals(that.entryCode) : that.entryCode != null) return false;
        if (realFileName != null ? !realFileName.equals(that.realFileName) : that.realFileName != null) return false;
        if (saveFilePath != null ? !saveFilePath.equals(that.saveFilePath) : that.saveFilePath != null) return false;
        if (saveFileName != null ? !saveFileName.equals(that.saveFileName) : that.saveFileName != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        return !(createTime != null ? !createTime.equals(that.createTime) : that.createTime != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (entryCode != null ? entryCode.hashCode() : 0);
        result = 31 * result + (realFileName != null ? realFileName.hashCode() : 0);
        result = 31 * result + (saveFilePath != null ? saveFilePath.hashCode() : 0);
        result = 31 * result + (saveFileName != null ? saveFileName.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UploadFile{" +
                "entryCode='" + entryCode + '\'' +
                ", realFileName='" + realFileName + '\'' +
                ", saveFilePath='" + saveFilePath + '\'' +
                ", saveFileName='" + saveFileName + '\'' +
                ", state=" + state +
                ", createTime=" + createTime +
                "} " + super.toString();
    }
}
