package com.shuxiajian.frame.controller;

import com.shuxiajian.frame.common.BaseController;
import com.shuxiajian.frame.domain.Menu;
import com.shuxiajian.frame.domain.User;
import com.shuxiajian.frame.service.MenuService;
import com.shuxiajian.frame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 登录控制器
 *
 * @author pengzx
 * @create 2018-07-27 11:45
 */

@Controller
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @RequestMapping("/infosys")
    public String login(){
        logger.info("访问登录页面");
        return "frame/Login";
    }


    @RequestMapping("/Login/index")
    public String goToIndex(HttpServletRequest request, Model model) {
        User user = this.getCurrentUser();

        //获取当前用户页面权限
        List<Menu> menuList = menuService.selectMenuListByUsername(user.getUsername());

        model.addAttribute("menuList",menuList);
        model.addAttribute("empName",user.getEmployee().getEmpName());
        return "frame/Index-amazeui";
    }
}
