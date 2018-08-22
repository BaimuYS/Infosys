package com.shuxiajian.frame.service;

import com.shuxiajian.frame.common.Attribute;
import com.shuxiajian.frame.common.AttributeProcessor;
import com.shuxiajian.frame.common.BaseService;
import com.shuxiajian.frame.common.ResultInfo;
import com.shuxiajian.frame.domain.Employee;
import com.shuxiajian.frame.domain.Role;
import com.shuxiajian.frame.domain.User;
import com.shuxiajian.frame.mapper.EmployeeMapper;
import com.shuxiajian.frame.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Map;

/**
 * 员工服务类
 *
 * @author pengzx
 * @create 2018-08-03 11:36
 */

@Service
public class EmployeeService extends BaseService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private UserMapper userMapper;

    private Map<String,Attribute> attrAnnotationMap = AttributeProcessor.processAnnotations(Employee.class);

    /**
     * 根据输入对象条件获取分页数据
     * @param map 条件map对象  {"limit":10,"offset":0,sort:"menuId","sortOrder":"asc",queryColumn:queryValue...}
     * @return 员工list
     */
    public List<Employee> queryEmployeeListSelectiveByPage(Map<String,Object> map) {
        map.put("sort",attrAnnotationMap.get(map.get("sort")).DBColumn());
        return employeeMapper.queryEmployeeListSelectiveByPage(map);
    }

    /**
     * 根据输入对象条件获取数据条数
     * @param map 条件map对象  {queryColumn:queryValue...}
     * @return
     */
    public int getCountSelective(Map<String,Object> map){
        return employeeMapper.getCountSelective(map);
    }

    /**
     * 根据员工List删除员工数据
     * @param employees 员工List
     * @return ResultInfo 操作结果信息
     */
    public ResultInfo deleteMenuByMenus(List<Employee> employees){
        ResultInfo resultInfo = new ResultInfo();
        try {
            for (Employee employee : employees) {
                employeeMapper.deleteByPrimaryKey(employee.getEmpId());
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
     * 插入员工数据
     * @param employee 员工对象
     * @return ResultInfo 操作结果信息
     */
    public ResultInfo insertMenu(Employee employee){
        ResultInfo resultInfo = new ResultInfo();
        try {
            Employee temp = employeeMapper.selectByPrimaryKey(employee.getEmpId());
            if (temp != null){
                resultInfo.setError("数据已存在！");
                return resultInfo;
            }
            employeeMapper.insert(employee);
        }catch (Exception e){
            e.printStackTrace();
            resultInfo.setError(e.getMessage());
            return resultInfo;
        }

        resultInfo.setSuccess("数据插入成功");
        return resultInfo;
    }

    /**
     * 更新员工数据
     * @param employee 员工对象
     * @return ResultInfo 操作结果信息
     */
    @Transactional
    public ResultInfo updateEmployeeByPrimaryKey(Employee employee) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            employeeMapper.updateByPrimaryKey(employee);
            User empUser = employee.getUser();
            if (empUser != null){   //员工含登录信息
                User temp = userMapper.selectByPrimaryKey(empUser.getUsername());
                if (temp == null) {
                    userMapper.insert(empUser); //插入用户信息
                }else {
                    userMapper.updateByPrimaryKey(employee.getUser());    //更新登录表信息
                }

            }

        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  //事务回滚
            resultInfo.setError(e.getMessage());
            return resultInfo;
        }

        resultInfo.setSuccess("数据更新成功");
        return resultInfo;
    }

    /**
     * 根据员工号从中间表获取角色列表
     * @param username 员工号
     * @return
     */
    public List<Role> queryRoleListByUsername(String username){
        return employeeMapper.queryRoleListByUsername(username);
    }

    /**
     * 保存用户角色信息
     * @param username 用户名
     * @param arrRoleIdList 角色id数组
     * @return 结果信息集
     */
    public ResultInfo saveRoleListOfUsername(String username,List<String> arrRoleIdList){
        ResultInfo resultInfo = new ResultInfo();
        try {
            employeeMapper.deleteRoleListOfUsername(username);  //删除中间表数据
            if (arrRoleIdList.size() > 0) {
                employeeMapper.saveRoleListOfUsername(username,arrRoleIdList);  //保存中间表数据
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
}
