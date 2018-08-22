package com.shuxiajian.frame.mapper;

import com.shuxiajian.frame.domain.Menu;

import java.util.List;
import java.util.Map;

public interface MenuMapper {
    int deleteByPrimaryKey(String menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(String menuId);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    //根据查询条件分页获取菜单列表
    List<Menu> queryMenuListSelectiveByPage(Map<String,Object> map);

    //根据查询条件获取所有菜单列表
    List<Menu> queryMenuListSelective(Map<String,Object> map);

    //根据查询条件获取记录数
    int getCountSelective(Map<String,Object> map);

    //根据角色ID获取菜单列表
    List<Menu> selectMenuListByRoleId(String roleId);

    //根据用户名获取菜单列表
    List<Menu> selectMenuListByUsername(String username);
}