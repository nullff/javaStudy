package com.xiaokai.dao;

import com.xiaokai.pojo.User;

import java.util.List;

public interface UserMapper {
    List<User> getUserList();

    User getUserById(int id);

    int addUser(User user);

    int deleteById(int id);

    int updateUser(User user);

}
