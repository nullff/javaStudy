package com.xiaokai.dao;

import com.xiaokai.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    @Select("select * from user")
    List<User> getUserList();

    User getUserById(int id);

    int addUser(User user);

    int deleteById(int id);

    int updateUser(User user);

    List<User> getUserListLimit(Map<String ,Object> map);

    List<User> getUserListRowBounds();

}
