package com.xiaokai.dao;

import com.xiaokai.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    @Select("select * from user")
    List<User> getUserList();

    @Select("select * from user where id = #{id}")
    User getUserById(int id);

    @Insert("insert into user (id,name,pwd) values (#{id},#{name},#{pwd})")
    int addUser(User user);

    @Delete("delete from user where id = ${id}")
    int deleteById(int id);

    @Update("update user set name=#{name}, pwd=#{pwd} where id = #{id}")
    int updateUser(User user);

    List<User> getUserListLimit(Map<String ,Object> map);

    List<User> getUserListRowBounds();

}
