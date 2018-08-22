package com.shuxiajian.frame.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.shuxiajian.frame.common.*;
import com.shuxiajian.frame.domain.Menu;
import com.shuxiajian.frame.domain.Role;
import com.shuxiajian.frame.domain.User;
import com.shuxiajian.frame.mapper.MenuMapper;
import com.shuxiajian.frame.mapper.RoleMapper;
import com.shuxiajian.frame.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 角色服务类
 *
 * @author pengzx
 * @create 2018-08-06 18:23
 */

@Service
public class RoleService extends BaseService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private UserMapper userMapper;

    private Map<String,Attribute> attrAnnotationMap = AttributeProcessor.processAnnotations(Role.class);

    /**
     * 根据输入对象条件获取分页数据
     * @param map 条件map对象  {"limit":10,"offset":0,sort:"menuId","sortOrder":"asc",queryColumn:queryValue...}
     * @return 角色list
     */
    public List<Role> queryRoleListSelectiveByPage(Map<String,Object> map) {
        map.put("sort",attrAnnotationMap.get(map.get("sort")).DBColumn());
        return roleMapper.queryRoleListSelectiveByPage(map);
    }

    /**
     * 根据输入对象条件获取数据条数
     * @param map 条件map对象  {queryColumn:queryValue...}
     * @return
     */
    public int getCountSelective(Map<String,Object> map){
        return roleMapper.getCountSelective(map);
    }

    /**
     * 根据输入对象条件获取中间表用户数据条数
     * @param map 条件map对象  {queryColumn:queryValue...} 需包含参数roleId
     * @return
     */
    public int getUserCountOfRoleId(Map<String,Object> map){
        return roleMapper.getUserCountOfRoleId(map);
    }


    /**
     * 根据员工List删除数据
     * @param roles 角色List
     * @return ResultInfo 操作结果信息
     */
    public ResultInfo deleteRoleByMenus(List<Role> roles){
        ResultInfo resultInfo = new ResultInfo();
        try {
            for (Role role : roles) {
                roleMapper.deleteByPrimaryKey(role.getRoleId());
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
     * 插入数据
     * @param role 角色对象
     * @return ResultInfo 操作结果信息
     */
    public ResultInfo insertRole(Role role){
        ResultInfo resultInfo = new ResultInfo();
        try {
            Role temp = roleMapper.selectByPrimaryKey(role.getRoleId());
            if (temp != null){
                resultInfo.setError("数据已存在！");
                return resultInfo;
            }
            roleMapper.insert(role);
        }catch (Exception e){
            e.printStackTrace();
            resultInfo.setError(e.getMessage());
            return resultInfo;
        }

        resultInfo.setSuccess("数据插入成功");
        return resultInfo;
    }

    /**
     * 更新数据
     * @param role 角色对象
     * @return ResultInfo 操作结果信息
     */
    public ResultInfo updateRoleByPrimaryKey(Role role) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            roleMapper.updateByPrimaryKey(role);
        }catch (Exception e){
            e.printStackTrace();
            resultInfo.setError(e.getMessage());
            return resultInfo;
        }

        resultInfo.setSuccess("数据更新成功");
        return resultInfo;
    }

    /**
     * 根据角色id获取菜单树信息
     * @param roleId 角色id
     * @return 菜单树节点json字符串
     */
    public String getMenuListByRoleId(String roleId){
        Role role = roleMapper.selectByPrimaryKey(roleId);

        List<Menu> roleMenuList = role.getMenuList();   //获取本角色已有菜单列表
        List<Menu> allMenuList = menuMapper.queryMenuListSelective(null);   //获取所有菜单列表
        List<BSTreeNode> bsTreeNodes = new ArrayList<BSTreeNode>();
        for (Menu menu: allMenuList) {
            BSTreeNode bsTreeNode = null;
            if (!checkIfMenuBeIncluded(menu.getMenuId(),roleMenuList)){
                //未选中
                bsTreeNode = new BSTreeNode(menu.getMenuId(),menu.getParMenuId(),menu.getName());
            }else {
                //已选中
                bsTreeNode = new BSTreeNode(menu.getMenuId(),menu.getParMenuId(),menu.getName(),new BSTreeNodeState(true,null,null,null));
            }
            bsTreeNodes.add(bsTreeNode);
        }

        BSTree bsTree = new BSTree(bsTreeNodes);
        System.out.println(bsTree.getNodeListJsonString());

        return bsTree.getNodeListJsonString();
    }

    /**
     * 判断菜单是否被选中
     * @param menuId 菜单ID
     * @param menuList 选中菜单列表
     * @return true or false
     */
    private boolean checkIfMenuBeIncluded(String menuId,List<Menu> menuList){
        for (Menu menu:menuList) {
            if (menu.getMenuId().equals(menuId)){
                return true;
            }
        }
        return false;
    }

    /**
     * 保存角色-菜单中间表数据
     * @param roleId 角色id
     * @param arrMenuIdList 菜单ID list
     * @return 结果信息集
     */
    @Transactional
    public ResultInfo saveMenuListOfRoleId(String roleId,List<String> arrMenuIdList){
        ResultInfo resultInfo = new ResultInfo();
        try {
            roleMapper.deleteMenuListOfRoleId(roleId);  //删除中间表数据
            if (arrMenuIdList.size() > 0) {
                roleMapper.saveMenuListOfRoleId(roleId,arrMenuIdList);  //保存中间表数据
            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  //事务回滚
            resultInfo.setError(e.getMessage());
            return resultInfo;
        }

        resultInfo.setSuccess("保存成功");
        return resultInfo;
    }

    /**
     * 保存角色分配的用户列表
     * @param roleId 角色id
     * @param arrUserIdList  用户列表
     * @return 结果信息集
     */
    @Transactional
    public ResultInfo saveUserListOfRoleId(String roleId,List<String> arrUserIdList){
        ResultInfo resultInfo = new ResultInfo();
        try {
            roleMapper.deleteUserListOfRoleId(roleId);  //删除中间表数据
            if (arrUserIdList.size() > 0) {
                roleMapper.saveUserListOfRoleId(roleId,arrUserIdList);  //保存中间表数据
            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  //事务回滚
            resultInfo.setError(e.getMessage());
            return resultInfo;
        }

        resultInfo.setSuccess("保存成功");
        return resultInfo;
    }

    /**
     * 获取角色用户信息
     * @param roleId 角色Id
     * @return {allUser:[user1,user2...],selUser:string[]}
     */
    public String getUserList(String roleId) {
        //全部用户信息
        List<User> allUserList = userMapper.queryUserListSelective(null);
        //本角色用户信息
        List<User> roleUserList = roleMapper.selectByPrimaryKey(roleId).getUserList();

        String selectedDataStr = "";
        for (User user: roleUserList) {
            selectedDataStr = selectedDataStr + user.getUsername() + ",";
        }
        if (selectedDataStr != ""){
            selectedDataStr = selectedDataStr.substring(0,selectedDataStr.length() - 1);    //去除逗号
        }

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter("username","employee","empName");
        //System.out.println(JSONObject.toJSONString(allUserList,filter));

        JSONObject jo = new JSONObject();
        jo.put("allUser",JSONObject.toJSONString(allUserList,filter));
        jo.put("selUser",selectedDataStr);

        return jo.toJSONString();
    }

    /**
     * 根据输入对象条件从中间表获取分页用户数据
     * @param map 条件map对象  {"limit":10,"offset":0,sort:"username","sortOrder":"asc",queryColumn:queryValue...}
     * @return 用户list
     */
    public List<User> queryUserListOfRoleIdByPage(Map<String,Object> map) {
        map.put("sort",AttributeProcessor.processAnnotations(User.class).get(map.get("sort")).DBColumn());
        return roleMapper.queryUserListOfRoleIdByPage(map);
    }

    /**
     * 根据输入查的查询条件获取所有角色列表
     * @param map 查询条件map
     * @return list-role
     */
    public List<Role> queryRoleListSelective(Map<String,Object> map) {
        return roleMapper.queryRoleListSelective(map);
    }
}
