package com.shuxiajian.frame.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shuxiajian.frame.common.BaseController;
import com.shuxiajian.frame.common.ResultInfo;
import com.shuxiajian.frame.domain.Role;
import com.shuxiajian.frame.domain.User;
import com.shuxiajian.frame.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 角色信息控制类
 *
 * @author pengzx
 * @create 2018-08-06 18:17
 */

@Controller
@RequestMapping("/frame/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/gotoList")
    public String gotoRoleList(){
        return "frame/systemconfig/role-list";
    }

    /**
     * 分页获取角色数据
     * @param parameters 查询条件 {"limit":10,"offset":0,sort:"menuId","sortOrder":"asc",queryColumn:queryValue...}
     * @return 带总记录数的当前页数据 {total:100,rows:[{columnName:columnValue...}...]}
     */
    @RequestMapping("/getListData")
    @ResponseBody
    public String queryRoleListByPage(String parameters) {
        logger.info("查询角色列表 parameters:"+ parameters);

        Map<String,Object> paramsMap = JSONObject.parseObject(parameters);

        //当前查询总记录条数
        int total = roleService.getCountSelective(paramsMap);

        //数据list
        List<Role> roleList = roleService.queryRoleListSelectiveByPage(paramsMap);

        return listResultToPagedJsonString(total ,roleList);
    }

    /**
     * 删除菜单数据
     * @param parameters 待删除角色的json字符串数组
     * @return 结果信息集
     */
    @RequestMapping("/deleteData")
    @ResponseBody
    public ResultInfo deleteRole(String parameters){
        logger.info("删除角色数据 parameters:"+ parameters);

        List<Role> roleList = JSON.parseArray(parameters,Role.class);
        ResultInfo resultInfo = roleService.deleteRoleByMenus(roleList);

        return resultInfo;
    }

    /**
     * 新增菜单数据
     * @param parameters 新增角色的json字符串
     * @return 结果信息集
     */
    @RequestMapping("/addData")
    @ResponseBody
    public ResultInfo addRole(String parameters) {
        logger.info("新增角色数据 parameters:"+ parameters);

        Role role = JSON.parseObject(parameters,Role.class);
        ResultInfo resultInfo = roleService.insertRole(role);

        return resultInfo;
    }

    /**
     * 更新角色数据
     * @param parameters 待更新角色的json字符串
     * @return 结果信息集
     */
    @RequestMapping("/editData")
    @ResponseBody
    public ResultInfo editRole(String parameters) {
        logger.info("更新角色数据 parameters:"+ parameters);

        Role role = JSON.parseObject(parameters,Role.class);
        ResultInfo resultInfo = roleService.updateRoleByPrimaryKey(role);

        return resultInfo;
    }

    /**
     *  根据角色id获取菜单列表
     * @param roleId 角色id
     * @return 菜单树节点json字符串
     */
    @RequestMapping("/getMenuList")
    @ResponseBody
    public String getMenuListByRoleId(String roleId){
        logger.info("获取角色菜单列表 roleId:"+ roleId);
        return roleService.getMenuListByRoleId(roleId);
    }

    /**
     * 保存角色-菜单中间表
     * @param roleId 角色id
     * @param arrMenuId 菜单id list
     * @return 结果信息集
     */
    @RequestMapping("/saveMenuList")
    @ResponseBody
    public ResultInfo saveMenuListOfRoleId(String roleId,String arrMenuId){
        logger.info("保存角色菜单列表 roleId:" + roleId + "; arrMenuId:" + arrMenuId);

        List<String> arrMenuIdList = JSONObject.parseArray(arrMenuId,String.class);

        ResultInfo resultInfo = roleService.saveMenuListOfRoleId(roleId,arrMenuIdList);

        return resultInfo;
    }

    /**
     * 保存角色-用户中间表
     * @param roleId 角色id
     * @param arrSelectedUserId 用户id list
     * @return 结果信息集
     */
    @RequestMapping("/saveUserList")
    @ResponseBody
    public ResultInfo saveUserListOfRoleId(String roleId,String arrSelectedUserId){
        logger.info("保存角色用户列表 roleId:" + roleId + "; arrSelectedUserId:" + arrSelectedUserId);
        List<String> arrUserIdList = JSONObject.parseArray(arrSelectedUserId,String.class);

        ResultInfo resultInfo = roleService.saveUserListOfRoleId(roleId,arrUserIdList);

        return resultInfo;
    }

    /**
     * 根据角色id，获取所有用户列表及已分配用户数组
     * @param roleId
     * @return {allUser:[user1,user2...],selUser:string[]}
     */
    @RequestMapping("/getUserList")
    @ResponseBody
    public String getUserListByRoleId(String roleId) {
        logger.info("获取角色已分配用户 roleId:" + roleId);

        return roleService.getUserList(roleId);
    }

    /**
     * 根据角色id，获取用户分页数据
     * @param parameters {"limit":10,"offset":0,sort:"menuId","sortOrder":"asc",queryColumn:queryValue...}
     * @return 带总记录数的当前页数据 {total:100,rows:[{columnName:columnValue...}...]}
     */
    @RequestMapping("/getUserListByPage")
    @ResponseBody
    public String getUserListOfRoleIdByPage(String parameters) {
        logger.info("分页获取角色用户 parameters:" + parameters);

        Map<String,Object> paramsMap = JSONObject.parseObject(parameters);

        //当前查询总记录条数
        int total = roleService.getUserCountOfRoleId(paramsMap);

        List<User> userList = roleService.queryUserListOfRoleIdByPage(paramsMap);

        System.out.println(listResultToPagedJsonString(total,userList));
        return listResultToPagedJsonString(total,userList);
    }
}
