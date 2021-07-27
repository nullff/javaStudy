package com.xiaokai.dao.role;

import com.xiaokai.dao.BaseDao;
import com.xiaokai.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {
    @Override
    public List<Role> getRoleList(Connection connection) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Role> roleList = new ArrayList<Role>();
        if (connection != null){
            String sql = "select * from smbms_role ; ";
            Object[] params = {};
            resultSet = BaseDao.execute(connection,preparedStatement,sql,params,resultSet);
            while (resultSet.next()){
                Role role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setRoleCode(resultSet.getString("roleCode"));
                role.setRoleName(resultSet.getString("roleName"));
                roleList.add(role);
            }
            BaseDao.closeResource(null,preparedStatement,resultSet);
        }
        return roleList;
    }
}
