package com.shuxiajian.frame.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shuxiajian.frame.common.BaseController;
import com.shuxiajian.frame.common.ResultInfo;
import com.shuxiajian.frame.domain.Menu;
import com.shuxiajian.frame.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理控制器
 *
 * @author pengzx
 * @create 2018-07-30 10:37
 */

@Controller
@RequestMapping("/frame/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/gotoList")
    public String gotoMenuList(){
        return "frame/systemconfig/menu-list";
    }

    /**
     * 分页获取菜单数据
     * @param parameters 查询条件 {"limit":10,"offset":0,sort:"menuId","sortOrder":"asc",queryColumn:queryValue...}
     * @return 带总记录数的当前页数据 {total:100,rows:[{columnName:columnValue...}...]}
     */
    @RequestMapping("/getListData")
    @ResponseBody
    public String queryMenuListByPage(String parameters) {
        logger.info("查询菜单列表 parameters:"+ parameters);

        Map<String,Object> paramsMap = JSONObject.parseObject(parameters);

        //当前查询总记录条数
        int total = menuService.getCountSelective(paramsMap);

        //数据list
        List<Menu> menuList = menuService.queryMenuListSelectiveByPage(paramsMap);

        return listResultToPagedJsonString(total ,menuList);
    }

    /**
     * 删除菜单数据
     * @param parameters 待删除菜单的json字符串数组
     * @param model
     * @return 结果信息集
     */
    @RequestMapping("/deleteData")
    @ResponseBody
    public ResultInfo deleteMenu(String parameters, Model model){
        logger.info("删除菜单数据 parameters:"+ parameters);

        List<Menu> menuList = JSON.parseArray(parameters,Menu.class);
        ResultInfo resultInfo = menuService.deleteMenuByMenus(menuList);

        return resultInfo;
    }

    /**
     * 新增菜单数据
     * @param parameters 新增菜单的json字符串
     * @return 结果信息集
     */
    @RequestMapping("/addData")
    @ResponseBody
    public ResultInfo addMenu(String parameters) {
        logger.info("新增菜单数据 parameters:"+ parameters);

        Menu menu = JSON.parseObject(parameters,Menu.class);
        ResultInfo resultInfo = menuService.insertMenu(menu);

        return resultInfo;
    }

    /**
     * 更新菜单数据
     * @param parameters 待更新菜单的json字符串
     * @return 结果信息集
     */
    @RequestMapping("/editData")
    @ResponseBody
    public ResultInfo editMenu(String parameters) {
        logger.info("更新菜单数据 parameters:"+ parameters);

        Menu menu = JSON.parseObject(parameters,Menu.class);
        ResultInfo resultInfo = menuService.updateMenuByPrimaryKey(menu);

        return resultInfo;
    }
}
