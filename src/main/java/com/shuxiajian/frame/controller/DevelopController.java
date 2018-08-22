package com.shuxiajian.frame.controller;

import com.shuxiajian.frame.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 开发工具
 *
 * @author pengzx
 * @create 2018-08-22 15:21
 */

@Controller
@RequestMapping("/frame/develop")
public class DevelopController extends BaseController {

    /**
     * 图标列表页面
     * @return
     */
    @RequestMapping("/gotoIconList")
    public String gotoIconList(){
        return "frame/develop/icon-list";
    }
}
