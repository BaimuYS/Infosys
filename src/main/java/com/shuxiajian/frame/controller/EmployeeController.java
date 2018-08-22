package com.shuxiajian.frame.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shuxiajian.frame.common.BaseController;
import com.shuxiajian.frame.common.ResultInfo;
import com.shuxiajian.frame.domain.Employee;
import com.shuxiajian.frame.domain.Role;
import com.shuxiajian.frame.domain.User;
import com.shuxiajian.frame.service.EmployeeService;
import com.shuxiajian.frame.service.RoleService;
import com.shuxiajian.frame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 用户管理控制器
 *
 * @author pengzx
 * @create 2018-08-03 10:37
 */

@Controller
@RequestMapping("/frame/employee")
public class EmployeeController extends BaseController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @RequestMapping("/gotoList")
    public String gotoEmployeeList(){
        return "frame/systemconfig/emp-list";
    }

    /**
     * 分页获取员工数据
     * @param parameters 查询条件 {"limit":10,"offset":0,sort:"empId","sortOrder":"asc",queryColumn:queryValue...}
     * @return 带总记录数的当前页数据 {total:100,rows:[{columnName:columnValue...}...]}
     */
    @RequestMapping("/getListData")
    @ResponseBody
    public String queryEmployeeListByPage(String parameters) {

        logger.info("查询员工列表 parameters:"+ parameters);

        Map<String,Object> paramsMap = JSONObject.parseObject(parameters);

        //当前查询总记录条数
        int total = employeeService.getCountSelective(paramsMap);

        //数据list
        List<Employee> employeeList = employeeService.queryEmployeeListSelectiveByPage(paramsMap);

        return listResultToPagedJsonString(total ,employeeList);
    }

    /**
     * 删除员工数据
     * @param parameters 待删除员工的json字符串数组
     * @param model
     * @return 结果信息集
     */
    @RequestMapping("/deleteData")
    @ResponseBody
    public ResultInfo deleteEmployee(String parameters, Model model){
        logger.info("删除员工数据 parameters:"+ parameters);

        List<Employee> employeeList = JSON.parseArray(parameters,Employee.class);
        ResultInfo resultInfo = employeeService.deleteMenuByMenus(employeeList);

        return resultInfo;
    }

    /**
     * 新增员工数据
     * @param parameters 新增员工的json字符串
     * @return 结果信息集
     */
    @RequestMapping("/addData")
    @ResponseBody
    public ResultInfo addEmployee(String parameters) {
        logger.info("新增员工数据 parameters:"+ parameters);

        Employee employee = JSON.parseObject(parameters,Employee.class);
        ResultInfo resultInfo = employeeService.insertMenu(employee);

        return resultInfo;
    }

    /**
     * 更新员工数据
     * @param parameters 待更新员工的json字符串
     * @return 结果信息集
     */
    @RequestMapping("/editData")
    @ResponseBody
    public ResultInfo editEmployee(String parameters) {
        logger.info("更新员工数据 parameters:"+ parameters);

        //这里选择在控制器中分别反射出对象及子对象然后再关联，也可在前台构造参数时，将子对象构造在父对象中
        //但是因为我前台获取输入框的特殊写法，此处采用这种方式
        Employee employee = JSON.parseObject(parameters,Employee.class);
        User user = JSON.parseObject(parameters,User.class);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        employee.setUser(user);

        ResultInfo resultInfo = employeeService.updateEmployeeByPrimaryKey(employee);

        return resultInfo;
    }

    /**
     * 获取员工角色信息，供多选对话框使用
     * @param username 员工id
     * @return
     */
    @RequestMapping("/getRoleList")
    @ResponseBody
    public JSONObject getRoleListByUsername(String username){
        logger.info("获取员工对应角色信息：" + username);
        //获取用户已有角色列表
        List<Role> roleList = employeeService.queryRoleListByUsername(username);

        String selectedDataStr = "";
        for (Role role: roleList) {
            selectedDataStr = selectedDataStr + role.getRoleId() + ",";
        }
        if (selectedDataStr != ""){
            selectedDataStr = selectedDataStr.substring(0,selectedDataStr.length() - 1);    //去除逗号
        }

        //获取所有角色列表
        List<Role> allRoleList = roleService.queryRoleListSelective(null);

        JSONObject jo = new JSONObject();
        jo.put("roleList",selectedDataStr);
        jo.put("allRoleList",allRoleList);

        return jo;
    }

    /**
     * 保存用户角色信息
     * @param username 用户名
     * @param arrSelectedRoleId 选取的角色id数组
     * @return 结果信息集
     */
    @RequestMapping("/saveRoleList")
    @ResponseBody
    public ResultInfo saveRoleListOfUsername(String username,String arrSelectedRoleId){
        logger.info("保存用户的角色username:" + username + ";arrSelectedRoleId:" + arrSelectedRoleId);
        List<String> arrRoleIdList = JSONObject.parseArray(arrSelectedRoleId,String.class);

        ResultInfo resultInfo = employeeService.saveRoleListOfUsername(username,arrRoleIdList);

        return resultInfo;
    }
}
