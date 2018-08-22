package com.shuxiajian.frame.service;

import com.shuxiajian.frame.common.BaseService;
import com.shuxiajian.frame.common.ResultInfo;
import com.shuxiajian.frame.domain.User;
import com.shuxiajian.frame.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 登录信息Service
 *
 * @author pengzx
 * @create 2018-07-27 19:15
 */

@Service
public class UserService extends BaseService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 校验用户信息
     * @param userName 登录名
     * @param passWord 密码
     * @return boolean 是否成功
     */
    public User checkUser(String userName, String passWord){
        return userMapper.selectByUserAndPwd(userName, passWord);
    }

    /**
     * 根据登录名获取用户
     * @param userName 用户名
     * @return
     */
    public User selectByPrimaryKey(String userName){
        return userMapper.selectByPrimaryKey(userName);
    }

    /**
     * 更新用户信息
     * @param record User 实体
     * @return 结果集
     */
    public ResultInfo updateByPrimaryKey(User record){
        ResultInfo resultInfo = new ResultInfo();
        try {
            userMapper.updateByPrimaryKey(record);
        }catch (Exception e){
            e.printStackTrace();
            resultInfo.setError(e.getMessage());
            return resultInfo;
        }

        resultInfo.setSuccess("数据更新成功");
        return resultInfo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectByPrimaryKey(username);    //查询用户信息
        return user;
    }
}
