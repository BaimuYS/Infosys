package com.shuxiajian.frame.mapper;

import com.shuxiajian.frame.domain.Employee;
import com.shuxiajian.frame.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    int deleteByPrimaryKey(String empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(String empId);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    Employee selectByDeptId(String deptId);

    //根据查询条件分页获取用户列表
    List<Employee> queryEmployeeListSelectiveByPage(Map<String,Object> map);

    //根据查询条件获取记录数
    int getCountSelective(Map<String,Object> map);

    //根据员工号获取角色列表
    List<Role> queryRoleListByUsername(String username);

    //根据用户id，删除用户-角色中间表数据
    int deleteRoleListOfUsername(String username);

    //根据用户id，保存用户-角色中间表数据
    int saveRoleListOfUsername(@Param("username") String username, @Param("arrRoleIdList") List<String> arrRoleIdList);
}