package com.xiaokai.dao.user;

import com.xiaokai.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    //获取登录用户信息
    public User getUserLogin(Connection connection, String userCode) throws SQLException;
    //修改密码
    public int updatePwd(Connection connection, int id, String password) throws SQLException;
    //增加用户
    public int add(Connection connection, User user)throws Exception;
    //通过条件查询userlist
    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize)throws Exception;
    //通过条件查询用户表记录数
    public int getUserCount(Connection connection, String userName, int userRole)throws Exception;
    //通过userid删除user
    public int deleteUserById(Connection connection, Integer delId)throws Exception;
    //通过userId获取user
    public User getUserById(Connection connection, String id)throws Exception;
    //修改用户user
    public int modify(Connection connection, User user)throws Exception;


}
