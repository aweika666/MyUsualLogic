package com.aweika.common.sys.entitySys;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class SysUser extends BaseEntity<Long>{

    // 用户名/账号
    private String username;
    // 密码
    @JsonIgnore
    private String password;
    // 姓名
    private String realName;
    //单位级别 1-市 2-区县 3-乡镇街道 4-企业 5-小微园区(可能用不到)
    private Integer unitLevel;
    //区县
    private String county;
    //乡镇街道
    private String town;
    //小微园区 (可能用不到)
    private String plat;
    //手机
    private String mobile;
    //
    @JsonIgnore
    private String entName;
    //公司统代
    private String creditCode;
    //单位 政府账号使用
    private String units;
    //处室
    private String office;
    //状态:1可用,0禁用 默认1
    private Integer status;
    //首次登陆 0 首次 默认0
    private Integer firstLogin;
    //最后一次登陆
    private Date lastLoginTime;
    //是否是管理员 1-是
    private Integer isAdmin;
    //创建者id
    @JsonIgnore
    private Integer createId;
    //对应角色表的角色id
    private Integer roleId;

    public String getOffice() {
        return office;
    }

    public SysUser setOffice(String office) {
        this.office = office;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Integer getUnitLevel() {
        return unitLevel;
    }

    public void setUnitLevel(Integer unitLevel) {
        this.unitLevel = unitLevel;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town == null ? null : town.trim();
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat == null ? null : plat.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName == null ? null : entName.trim();
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode == null ? null : creditCode.trim();
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units == null ? null : units.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public SysUser setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Integer firstLogin) {
        this.firstLogin = firstLogin;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
