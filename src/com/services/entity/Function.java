package com.services.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "function")
public class Function implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name; // 功能模块名称
    private Long parent_id; // 父功能模块id
    private Integer level; // 级别
    private String path; // 功能路径
    private Integer status; // 状态 0：不可用 1：可用
    private Date createTime; // 创建时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

        Function demo = (Function) o;

        if (id != null ? !id.equals(demo.id) : demo.id != null) return false;
        if (name != null ? !name.equals(demo.name) : demo.name != null) return false;
        if (parent_id != null ? !parent_id.equals(demo.parent_id) : demo.parent_id != null) return false;
        if (level != null ? !level.equals(demo.level) : demo.level != null) return false;
        if (path != null ? !path.equals(demo.path) : demo.path != null) return false;
        if (status != null ? !status.equals(demo.status) : demo.status != null) return false;
        return !(createTime != null ? !createTime.equals(demo.createTime) : demo.createTime != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (parent_id != null ? parent_id.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Function{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent_id=" + parent_id +
                ", level=" + level +
                ", path='" + path + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
