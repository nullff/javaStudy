package com.xiaokai.dao.user;

import com.mysql.jdbc.StringUtils;
import com.xiaokai.dao.BaseDao;
import com.xiaokai.pojo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    //根据userCode获取User
    public User getUserLogin(Connection connection, String userCode) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        User user = null;
        if (connection!=null){
         String sql = "select * from smbms_user where userCode = ?";
         Object[] params = {userCode};
        //查询userCode对应的数据库信息
        resultSet = BaseDao.execute(connection,preparedStatement, sql, params,resultSet);
        //获取User
        if (resultSet.next()){
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUserCode(resultSet.getString("userCode"));
            user.setUserName(resultSet.getString("userName"));
            user.setUserPassword(resultSet.getString("userPassword"));
            user.setGender(resultSet.getInt("gender"));
            user.setBirthday(resultSet.getDate("birthday"));
            user.setPhone(resultSet.getString("phone"));
            user.setAddress(resultSet.getString("address"));
            user.setUserRole(resultSet.getInt("userRole"));
            user.setCreatedBy(resultSet.getInt("createdBy"));
            user.setCreationDate(resultSet.getTimestamp("creationDate"));
            user.setModifyBy(resultSet.getInt("modifyBy"));
            user.setModifyDate(resultSet.getTimestamp("modifyDate"));
        }
        //关闭资源
        BaseDao.closeResource(connection,preparedStatement,resultSet);

        }
        return user;
    }

    //修改密码
    @Override
    public int updatePwd(Connection connection, int id, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        int execute = 0;
        if (connection!=null){
            String sql = "update smbms_user set userPassword = ? where id = ?";
            Object[] Params = {password,id};
            execute = BaseDao.executeUpdate(connection, preparedStatement, sql, Params);
        }
        return execute;
    }

    @Override
    public int add(Connection connection, User user) throws Exception {
        PreparedStatement preparedStatement = null;
        int execute = 0;
        if (connection != null){
            String sql ="insert into smbms_user(userCode, userName, userPassword, gender, birthday,"+
                        "phone, address, userRole,createdBy, creationDate)"+
                        "VALUES (?,?,?,?,?,?,?,?,?,?);";
            Object[] params = {user.getUserCode(),user.getUserName(),user.getUserPassword(),user.getGender(),
                               user.getBirthday(),user.getPhone(),user.getAddress(),user.getUserRole(),
                               user.getCreatedBy(),user.getCreationDate()};
            execute = BaseDao.executeUpdate(connection, preparedStatement, sql, params);
            BaseDao.closeResource(null,preparedStatement,null);
        }
        return execute;
    }

    @Override
    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<User> userList = new ArrayList<>();//结果集
        StringBuffer sql = new StringBuffer(); //可以动态变化的sql查询语句，便于增加查询条件
        List<Object> list = new ArrayList<>();
        if (connection != null){
            sql.append(" select u.*,r.roleName as userRoleName from smbms_user as u,smbms_role as r where u.userRole = r.id ");
            if (!StringUtils.isNullOrEmpty(userName)){
                sql.append(" and u.userName like ? ");
                list.add("%"+userName+"%");
            }
            if (userRole > 0){
                sql.append(" and u.userRole = ? ");
                list.add(userRole);
            }
            sql.append(" order by creationDate DESC limit ?,? ;");
            list.add((currentPageNo-1)*pageSize);
            list.add(pageSize);

            Object[] params = list.toArray();
            System.out.println("sql------>"+sql.toString());
            resultSet  = BaseDao.execute(connection, preparedStatement, sql.toString(), params, resultSet);
            while (resultSet.next()){
                User _user = new User();
                _user.setId(resultSet.getInt("id"));
                _user.setUserCode(resultSet.getString("userCode"));
                _user.setUserName(resultSet.getString("userName"));
                _user.setGender(resultSet.getInt("gender"));
                _user.setBirthday(resultSet.getDate("birthday"));
                _user.setPhone(resultSet.getString("phone"));
                _user.setUserRole(resultSet.getInt("userRole"));
                _user.setUserRoleName(resultSet.getString("userRoleName"));
                userList.add(_user);
            }
            BaseDao.closeResource(null,preparedStatement,resultSet);
        }
        return userList;
    }

    @Override
    public int getUserCount(Connection connection, String userName, int userRole) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuffer sql = new StringBuffer();//拼接sql条件
        ArrayList<Object> list = new ArrayList<>();//查询条件暂存
        int count = 0;

        if (connection!=null){
            sql.append("select count(1) as count from smbms_user as u ,smbms_role as r where u.userRole = r.id");
            if (StringUtils.isNullOrEmpty(userName)){
                sql.append(" and u.userName like ? ");
                list.add("%"+userName+"%");
            }
            if (userRole > 0){
                sql.append(" and u.userRole = ? ");
                list.add(userRole);
            }
            sql.append(";");
            System.out.println("------>"+sql.toString());
            Object[] params = list.toArray();
            resultSet = BaseDao.execute(connection, preparedStatement, sql.toString(), params, resultSet);
            if (resultSet.next()){
                count = resultSet.getInt("count");
            }
            BaseDao.closeResource(null,preparedStatement,resultSet);
        }
        return count;
    }

    @Override
    public int deleteUserById(Connection connection, Integer delId) throws Exception {
        PreparedStatement preparedStatement = null;
        int flag = 0;
        if (connection != null){
            String sql = "delete from smbms_user where `id` = ? ;";
            Object[] params = {delId};
            flag = BaseDao.executeUpdate(connection, preparedStatement, sql, params);
            BaseDao.closeResource(null,preparedStatement,null);
        }
        return flag;
    }

    @Override
    public User getUserById(Connection connection, String id) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        if (connection!=null){
            String sql = "select u.*,r.roleName as userRoleName from smbms_user u,smbms_role r where u.id=? and u.userRole = r.id";
            Object[] params = {id};
            resultSet = BaseDao.execute(connection, preparedStatement, sql, params, resultSet);
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setUserCode(resultSet.getString("userCode"));
                user.setUserName(resultSet.getString("userName"));
                user.setGender(resultSet.getInt("gender"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setPhone(resultSet.getString("phone"));
                user.setUserRole(resultSet.getInt("userRole"));
                user.setUserRoleName(resultSet.getString("userRoleName"));
            }
            BaseDao.closeResource(null,preparedStatement,resultSet);
        }
        return user;
    }

    @Override
    public int modify(Connection connection, User user) throws Exception {
        PreparedStatement preparedStatement = null;
        int flag = 0;
        if(null != connection) {
            String sql = "update smbms_user set userName=?,gender=?,birthday=?,phone=?,address=?,userRole=?,modifyBy=?,modifyDate=? where id = ? ";
            Object[] params = {user.getUserName(),user.getGender(),user.getBirthday(),user.getPhone(),user.getAddress(),user.getUserRole(),user.getModifyBy(),user.getModifyDate(),user.getId()};
            flag = BaseDao.executeUpdate(connection, preparedStatement, sql, params);
            BaseDao.closeResource(null,preparedStatement,null);
        }
        return flag;
    }

}
