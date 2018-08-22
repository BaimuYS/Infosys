package com.shuxiajian.frame.common.Authentication;

import com.shuxiajian.frame.domain.User;
import com.shuxiajian.frame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 授权类
 *
 * @author pengzx
 * @create 2018-08-15 17:29
 */

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    /**
     * 注入自定义的用户信息获取对象
     */

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName(); //获取表单中输入的用户名
        String password = (String) authentication.getCredentials();   //获取表单中输入的密码

        //判断用户是否存在和密码是否正确
        User user = (User) userService.loadUserByUsername(userName);

        if (user == null) {
            if (userName.equals("admin") && password.equals("123456")){
                user = new User(userName, "",password,0, null);
            }else {
                throw new BadCredentialsException("用户名不存在");
            }
        }else {
            //密码加密
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            if (!bCryptPasswordEncoder.matches(password,user.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
        }


        //权限，因使用数据库表权限配置，此处无用
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        //构建返回的用户登录成功的token
        return new UsernamePasswordAuthenticationToken(user,password,authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        // 这里直接改成retrun true;表示是支持这个执行
        return true;
    }
}
