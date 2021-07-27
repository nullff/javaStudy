package com.xiaokai.dao.role;

import com.xiaokai.pojo.Role;

import java.sql.Connection;
import java.util.List;

public interface RoleDao {
    //获取Role角色信息
    public List<Role> getRoleList(Connection connection)throws Exception;

}
