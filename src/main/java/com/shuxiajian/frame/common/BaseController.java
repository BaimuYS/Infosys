package com.shuxiajian.frame.common;

import com.alibaba.fastjson.JSONObject;
import com.shuxiajian.frame.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 基础Controller类
 *
 * @author pengzx
 * @create 2018-07-27 19:28
 */

public class BaseController {
    //log4j
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    /**
     * 查询结果list转带总记录数的json字符串
     * @param total 总记录数
     * @param domainList 数据列表
     * @return 带总记录数的json字符串 {total:100,rows:[{columnName:columnValue...}...]}
     */
    protected String listResultToPagedJsonString(int total, List domainList){
        JSONObject jo = new JSONObject();
        jo.put("total",total);
        jo.put("rows",domainList);
        logger.debug("返回查询数据：" + jo.toJSONString());
        return jo.toJSONString();
    }

    /**
     * 获取当前登录用户信息
     * @return
     */
    protected User getCurrentUser() {
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        User user = (User) securityContextImpl.getAuthentication().getPrincipal();
        return user;
    }
}
