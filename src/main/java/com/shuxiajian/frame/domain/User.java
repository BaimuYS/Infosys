package com.shuxiajian.frame.domain;

import com.shuxiajian.frame.common.Attribute;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

public class User implements Serializable,UserDetails {
    @Attribute(DBColumn = "USERNAME")
    private String username;

    @Attribute(DBColumn = "EMP_ID")
    private String empId;

    @Attribute(DBColumn = "PASSWORD")
    private String password;

    @Attribute(DBColumn = "LOGIN_COUNT")
    private Integer loginCount;

    @Attribute(DBColumn = "LAST_TIME")
    private Timestamp lastTime;

    private Employee employee;

    private String role;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    public User(){}

    public User(String username, String empId, String password, Integer loginCount, Timestamp lastTime) {
        this.username = username;
        this.empId = empId;
        this.password = password;
        this.loginCount = loginCount;
        this.lastTime = lastTime;
    }

    public User(String username, String password, String role, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId == null ? null : empId.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Timestamp getLastTime() {
        return lastTime;
    }

    public void setLastTime(Timestamp lastTime) {
        this.lastTime = lastTime;
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}