package com.shuxiajian.frame.mapper;

import com.shuxiajian.frame.domain.Role;
import com.shuxiajian.frame.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    //根据查询条件分页获取列表
    List<Role> queryRoleListSelectiveByPage(Map<String,Object> map);

    //根据查询条件获取所有角色列表
    List<Role> queryRoleListSelective(Map<String,Object> map);

    //根据查询条件获取记录数
    int getCountSelective(Map<String,Object> map);

    //根据查询条件获取中间表用户记录数
    int getUserCountOfRoleId(Map<String,Object> map);

    //根据角色id，菜单列表，更新中间表
    int saveMenuListOfRoleId(@Param("roleId") String roleId, @Param("arrMenuIdList") List<String> arrMenuIdList);

    //根据角色id，删除菜单列表
    int deleteMenuListOfRoleId(@Param("roleId") String roleId);

    //根据角色id，用户列表，更新中间表
    int saveUserListOfRoleId(@Param("roleId") String roleId, @Param("arrUserIdList") List<String> arrUserIdList);

    //根据角色id，删除用户列表
    int deleteUserListOfRoleId(@Param("roleId") String roleId);

    //根据角色id分页获取用户列表
    List<User> queryUserListOfRoleIdByPage(Map<String,Object> map);

    //根据角色ID获取用户列表
    List<User> selectUserListByRoleId(String roleId);
}