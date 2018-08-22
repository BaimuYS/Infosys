package com.shuxiajian.frame.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author pengzx
 * @create 2018-08-15 17:01
 */


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProvider provider;

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/infosys").loginProcessingUrl("/login/formSubmit")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .permitAll();  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
        http.logout()
                .logoutUrl("/logout")   //注销的请求地址
                .logoutSuccessUrl("/frame/Login.html")  //注销成功的页面
                .invalidateHttpSession(true);   //注销后使session失效
        http.authorizeRequests()
                .antMatchers("/Asset/**","/other/**").permitAll()   //允许用户访问静态资源
                .anyRequest().authenticated()
                .and().headers().frameOptions().disable()
                //.and().rememberMe().key("authorition")//.rememberMeServices(rememberMeServices()).key("INTERNAL_SECRET_KEY")
                //.userDetailsService(myUserDetailsService)
                .and().csrf().disable();
    }

    /**
     * 使用自定义实现的密码校验
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
        //auth.userDetailsService(myUserDetailsService);
    }
}
