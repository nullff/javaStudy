package com.xiaokai.service.user;

import com.xiaokai.dao.BaseDao;
import com.xiaokai.dao.user.UserDao;
import com.xiaokai.dao.user.UserDaoImpl;
import com.xiaokai.pojo.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    //业务层都会调用Dao层，所以引入Dao层
    private UserDao userDao;
    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

        public User login(String userCode, String password) {
            Connection connection = null;
            User user = null;
            try {
                connection = BaseDao.getConnection();
                //通过业务层调用具体的数据库操作
                user = userDao.getUserLogin(connection,userCode);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                BaseDao.closeResource(connection,null,null);
            }
            if (password.equals(user.getUserPassword())){
                return user;
            }else {
                return null;
            }


        }
    //根据用户id修改密码
    @Override
    public boolean updatePwd( int id, String password)  {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            if (userDao.updatePwd(connection, id, password) > 0){
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }

    //增加用户
    @Override
    public boolean add(User user) {
        Connection connection = null;
        boolean flag = false;

        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            int updateRows =userDao.add(connection,user);
            if (updateRows > 0){
                flag = true;
                System.out.println("### add success!");
            }else {
                System.out.println("### add failed!");
            }

        } catch (SQLException throwables) {
            try {
                //sql执行错误回滚
                connection.rollback();
                System.out.println("### rollback========>");
            } catch (SQLException e) {
                e.printStackTrace();

            }
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //在service层进行connection的连接控制
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }

    @Override
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize) {
        Connection connection = null;
        List<User> userList = null;
        System.out.println("### queryUserName======>"+queryUserName);
        System.out.println("### queryUserRole======>"+queryUserRole);
        System.out.println("### currentPageNo======>"+currentPageNo);
        System.out.println("### pageSize======>"+pageSize);

        try {
            connection = BaseDao.getConnection();
            userList = userDao.getUserList(connection, queryUserName, queryUserRole, currentPageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection,null,null);
        }
        return userList;
    }

    @Override
    public int getUserCount(String queryUserName, int queryUserRole) {
        int userCount = 0;
        Connection connection = null;

        try {
            connection = BaseDao.getConnection();
            userCount = userDao.getUserCount(connection,queryUserName,queryUserRole);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection,null,null);
        }
        return userCount;
    }

    @Override
    public User selectUserCodeExist(String userCode) {
        User user = null;
        Connection connection = null;

        try {
            connection = BaseDao.getConnection();
            user = userDao.getUserById(connection,userCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection,null,null);
        }
        return user;
    }

    @Override
    public boolean       deleteUserById(Integer delId) {
        boolean flag = false;
        Connection connection = null;

        try {
            connection = BaseDao.getConnection();
            int delRows = userDao.deleteUserById(connection,delId);
            if (delRows > 0){
                flag = true;
            }
        } catch (Exception e) {
            System.out.println("### delete failed!");
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }

    @Override
    public User getUserById(String id) {
        User user = null;
        Connection connection = null;

        try {
            connection = BaseDao.getConnection();
            user = userDao.getUserById(connection,id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection,null,null);
        }
        return user;
    }

    @Override
    public boolean modify(User user) {
        boolean flag = false;
        Connection connection = null;

        try {
            connection = BaseDao.getConnection();
            int modify = userDao.modify(connection, user);
            if (modify > 0){
                flag = true;
            }
        } catch (Exception e) {
            System.out.println("### modify failed!");
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }

}
