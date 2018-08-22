package com.shuxiajian.frame.domain;

import java.util.List;

public class Department {
    private String deptId;  //部门ID，主键

    private String deptName;

    private String deptCode;

    private String parDeptCode;

    //员工清单，一个部门有多个员工
    private List<Employee> employees;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    public String getParDeptCode() {
        return parDeptCode;
    }

    public void setParDeptCode(String parDeptCode) {
        this.parDeptCode = parDeptCode == null ? null : parDeptCode.trim();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}