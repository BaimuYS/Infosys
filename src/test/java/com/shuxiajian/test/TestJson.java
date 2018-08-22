package com.shuxiajian.test;

import com.alibaba.fastjson.JSON;
import com.shuxiajian.frame.domain.Menu;

/**
 * @author pengzx
 * @create 2018-07-30 18:38
 */

public class TestJson {
    public static void main(String[] args){
        Menu menu = new Menu();
        menu.setMenuId("1");
        menu.setName("2");
        menu.setUrl("3");
        menu.setParMenuId("4");
        menu.setMenuOrder(5);
        menu.setIconClass("6");

        System.out.println(JSON.toJSON(menu));


        String jsonStr = "{\"iconClass\":\"6\",\"menuId\":\"1\",\"menuOrder\":5,\"name\":\"2\",\"parMenuId\":\"4\",\"url\":\"3\"}";

        Menu menu1 = JSON.parseObject(jsonStr,Menu.class);
        System.out.println(menu1.getMenuId());
    }
}
