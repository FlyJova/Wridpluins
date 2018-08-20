package com.jova.wrid.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class TAaaUsers {
    private Long id;

    private String eMail;

    private String name;

    private String password;
    private String oldPassword;
    private Integer status;

    private String phoneNo;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;
    //统计该用户所有的代账公司数目
    private Integer number;
    //用户角色
    private List<Long> roleIds;
    //groupId:groupName
    private Map<Long,String> idGroupName;

    public Map<Long, String> getIdGroupName() {
        return idGroupName;
    }

    public void setIdGroupName(Map<Long, String> idGroupName) {
        this.idGroupName = idGroupName;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }


    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail == null ? null : eMail.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    @Override
    public String toString() {
        return "TAaaUsers{" +
                "id=" + id +
                ", eMail='" + eMail + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", status=" + status +
                ", phoneNo='" + phoneNo + '\'' +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                ", number=" + number +
                ", roleIds=" + roleIds +
                ", idGroupName=" + idGroupName +
                '}';
    }
}