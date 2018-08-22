package com.shuxiajian.frame.mapper;

import com.shuxiajian.frame.domain.Department;

public interface DepartmentMapper {
    int deleteByPrimaryKey(String deptId);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String deptId);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}