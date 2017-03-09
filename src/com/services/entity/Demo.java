package com.services.entity;

import com.framework.id.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "demo")
public class Demo extends IdEntity {
    private static final long serialVersionUID = 1L;

    private String name; // 功能模块名称
    private Long parent_id; // 父功能模块id
    private Integer level; // 级别
    private String path; // 功能路径
    private Integer status; // 状态 0：不可用 1：可用
    private Date createTime; // 创建时间

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

        Demo function = (Demo) o;

        if (name != null ? !name.equals(function.name) : function.name != null) return false;
        if (parent_id != null ? !parent_id.equals(function.parent_id) : function.parent_id != null) return false;
        if (level != null ? !level.equals(function.level) : function.level != null) return false;
        if (path != null ? !path.equals(function.path) : function.path != null) return false;
        if (status != null ? !status.equals(function.status) : function.status != null) return false;
        return !(createTime != null ? !createTime.equals(function.createTime) : function.createTime != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (parent_id != null ? parent_id.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "name='" + name + '\'' +
                ", parent_id=" + parent_id +
                ", level=" + level +
                ", path='" + path + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                "} " + super.toString();
    }
}
