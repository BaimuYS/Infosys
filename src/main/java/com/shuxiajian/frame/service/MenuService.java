package com.shuxiajian.frame.service;

import com.shuxiajian.frame.common.Attribute;
import com.shuxiajian.frame.common.AttributeProcessor;
import com.shuxiajian.frame.common.ResultInfo;
import com.shuxiajian.frame.domain.Menu;
import com.shuxiajian.frame.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 菜单服务
 *
 * @author pengzx
 * @create 2018-07-29 18:52
 */

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    private Map<String,Attribute> attrAnnotationMap = AttributeProcessor.processAnnotations(Menu.class);

    /**
     * 根据输入对象条件获取分页数据
     * @param map 条件map对象  {"limit":10,"offset":0,sort:"menuId","sortOrder":"asc",queryColumn:queryValue...}
     * @return 菜单list
     */
    public List<Menu> queryMenuListSelectiveByPage(Map<String,Object> map) {
        map.put("sort",attrAnnotationMap.get(map.get("sort")).DBColumn());
        return menuMapper.queryMenuListSelectiveByPage(map);
    }

    /**
     * 根据输入对象条件获取数据
     * @param map 条件map对象  {queryColumn:queryValue...}
     * @return 菜单list
     */
    public List<Menu> queryMenuListSelective(Map<String,Object> map) {
        return menuMapper.queryMenuListSelective(map);
    }

    /**
     * 根据输入对象条件获取数据条数
     * @param map 条件map对象  {queryColumn:queryValue...}
     * @return
     */
    public int getCountSelective(Map<String,Object> map){
        return menuMapper.getCountSelective(map);
    }

    /**
     * 根据菜单List删除菜单数据
     * @param menus 菜单List
     * @return ResultInfo 操作结果信息
     */
    public ResultInfo deleteMenuByMenus(List<Menu> menus){
        ResultInfo resultInfo = new ResultInfo();
        try {
            for (Menu menu : menus) {
                menuMapper.deleteByPrimaryKey(menu.getMenuId());
            }
        }catch (Exception e){
            e.printStackTrace();
            resultInfo.setError(e.getMessage());
            return resultInfo;
        }

        resultInfo.setSuccess("操作成功");
        return resultInfo;
    }

    /**
     * 插入菜单数据
     * @param menu 菜单对象
     * @return ResultInfo 操作结果信息
     */
    public ResultInfo insertMenu(Menu menu){
        ResultInfo resultInfo = new ResultInfo();
        try {
            Menu temp = menuMapper.selectByPrimaryKey(menu.getMenuId());
            if (temp != null){
                resultInfo.setError("数据已存在！");
                return resultInfo;
            }
            menuMapper.insert(menu);
        }catch (Exception e){
            e.printStackTrace();
            resultInfo.setError(e.getMessage());
            return resultInfo;
        }

        resultInfo.setSuccess("数据插入成功");
        return resultInfo;
    }

    /**
     * 更新菜单数据
     * @param menu 菜单对象
     * @return ResultInfo 操作结果信息
     */
    public ResultInfo updateMenuByPrimaryKey(Menu menu) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            menuMapper.updateByPrimaryKey(menu);
        }catch (Exception e){
            e.printStackTrace();
            resultInfo.setError(e.getMessage());
            return resultInfo;
        }

        resultInfo.setSuccess("数据更新成功");
        return resultInfo;
    }

    /**
     * 根据角色ID从中间表查询菜单List
     * @param roleId 角色ID
     * @return 菜单List
     */
    public List<Menu> selectMenuListByRoleId(String roleId){
        return menuMapper.selectMenuListByRoleId(roleId);
    }

    /**
     * 根据用户名获取菜单list
     * @param username 用户名
     * @return 菜单list
     */
    public List<Menu> selectMenuListByUsername(String username){
        return menuMapper.selectMenuListByUsername(username);
    }
}
