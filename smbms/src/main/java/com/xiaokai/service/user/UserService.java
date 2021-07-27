package com.xiaokai.service.user;

import com.xiaokai.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserService {
    //用户登录
    public User login(String userCode, String password);
    //根据用户ID修改密码
    public boolean updatePwd(int id, String password) ;
    //增加用户
    public boolean add(User user);
    //根据条件分页查询用户列表
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize);
    //根据条件查询用户表记录数
    public int getUserCount(String queryUserName, int queryUserRole);
    //根据userCode查询用户
    public User selectUserCodeExist(String userCode);
    //根据id删除User
    public boolean deleteUserById(Integer delId);
    //根据id查找User
    public User getUserById(String id);
    //更新修改用户信息
    public boolean modify(User user);

}
