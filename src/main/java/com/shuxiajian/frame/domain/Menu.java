package com.shuxiajian.frame.domain;

import com.shuxiajian.frame.common.Attribute;

import java.util.List;

public class Menu {
    @Attribute(DBColumn = "MENU_ID")
    private String menuId;

    @Attribute(DBColumn = "NAME")
    private String name;

    @Attribute(DBColumn = "URL")
    private String url;

    @Attribute(DBColumn = "PAR_MENU_ID")
    private String parMenuId;

    @Attribute(DBColumn = "MENU_ORDER")
    private Integer menuOrder;

    @Attribute(DBColumn = "ICON_CLASS")
    private String iconClass;

    private List<Role> roleList;    //一个菜单可属于多个角色

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getParMenuId() {
        return parMenuId;
    }

    public void setParMenuId(String parMenuId) {
        this.parMenuId = parMenuId == null ? null : parMenuId.trim();
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass == null ? null : iconClass.trim();
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}