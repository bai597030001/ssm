package com.sample.ssm.mapper;

import com.sample.ssm.model.User;

import java.util.List;

/**
 * @program: springmvc-demo
 * @description: 权限mapper
 * @author: baijd-a
 * @create: 2020-07-15 16:59
 **/
public interface UserMapper {

    List<User> getUsers();

    User getUserById(long id);

    User getUserByName(String userName);

    long addUser(User user);
}
