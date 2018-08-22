package com.shuxiajian.frame.mapper;

import com.shuxiajian.frame.domain.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(String username);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserAndPwd(String username, String password);

    //根据条件获取用户列表
    List<User> queryUserListSelective(Map<String,Object> map);

}
