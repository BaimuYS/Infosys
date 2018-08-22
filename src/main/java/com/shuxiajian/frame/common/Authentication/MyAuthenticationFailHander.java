package com.shuxiajian.frame.common.Authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录失败处理
 *
 * @author pengzx
 * @create 2018-08-16 9:29
 */

@Component("myAuthenticationFailHander")
public class MyAuthenticationFailHander extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Map<String,String> map = new HashMap<>();

        map.put("code","201");
        map.put("msg",exception.getMessage());
        //response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(map));

//        new DefaultRedirectStrategy().sendRedirect(request, response, "/hello");
    }
}
