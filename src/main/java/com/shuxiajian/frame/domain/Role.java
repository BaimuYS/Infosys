package com.shuxiajian.frame.domain;

import com.shuxiajian.frame.common.Attribute;

import java.util.List;

public class Role {
    @Attribute(DBColumn = "ROLE_ID")
    private String roleId;

    @Attribute(DBColumn = "ROLE_NAME")
    private String roleName;

    @Attribute(DBColumn = "ROLE_DESC")
    private String roleDesc;

    private List<Menu> menuList; //角色与订单是多对多关系

    private List<User> userList;    //角色与用户的多对多关系

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }


    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}