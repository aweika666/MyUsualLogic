package com.aweika.common.sys.entitySys;

import java.util.List;

/**
 * @Author: Fangqizhe
 * @Date: 2019/9/8 6:19 PM
 * @Description: 权限
 */
public class SysPermission extends  BaseEntity<Long> {
    // 权限名称
    private String name;

    private String permission;
    // 页面的url
    private String url;
    // 关联子页面url
    private String cUrls;
    // 图片地址
    private String icon;
    // 权限类型 1-页面 2-模块 3-按
    private String type;
    //级别 一级 1 二级 2 三级 3  四级 4
    private Integer level;
    //父级id
    private Integer parentId;
    //权限在当前模块下的顺序，由小到大
    private Integer seq;
    //是否禁用 0-禁用（隐藏） 1---使用 默认1
    private Integer isUse;
    //描述
    private String description;
    //客户端 1-政府 2- 企业
    private Integer rank;
    //下级 按level区分
    private List<SysPermission> child;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getcUrls() {
        return cUrls;
    }

    public void setcUrls(String cUrls) {
        this.cUrls = cUrls == null ? null : cUrls.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public List<SysPermission> getChild() {
        return child;
    }

    public void setChild(List<SysPermission> child) {
        this.child = child;
    }
}
