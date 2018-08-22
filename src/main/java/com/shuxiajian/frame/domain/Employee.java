package com.shuxiajian.frame.domain;

import com.shuxiajian.frame.common.Attribute;

import java.util.Date;

public class Employee {
    @Attribute(DBColumn = "EMP_ID")
    private String empId;   //员工ID，主键

    @Attribute(DBColumn = "DEPT_ID")
    private String deptId;

    @Attribute(DBColumn = "EMP_NAME")
    private String empName;

    @Attribute(DBColumn = "CUST_ID")
    private String custId;

    @Attribute(DBColumn = "BRANCH_ID")
    private String branchId;

    @Attribute(DBColumn = "EMAIL")
    private String email;

    @Attribute(DBColumn = "MOBILE")
    private String mobile;

    @Attribute(DBColumn = "TEL")
    private String tel;

    @Attribute(DBColumn = "ADDRESS")
    private String address;

    @Attribute(DBColumn = "ID_CARD")
    private String idCard;

    @Attribute(DBColumn = "SEX")
    private String sex;

    @Attribute(DBColumn = "IN_DT")
    private Date inDt;

    @Attribute(DBColumn = "OUT_DT")
    private Date outDt;

    private Department department; //部门

    private User user;    //登录信息

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId == null ? null : empId.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId == null ? null : custId.trim();
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId == null ? null : branchId.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getInDt() {
        return inDt;
    }

    public void setInDt(Date inDt) {
        this.inDt = inDt;
    }

    public Date getOutDt() {
        return outDt;
    }

    public void setOutDt(Date outDt) {
        this.outDt = outDt;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}