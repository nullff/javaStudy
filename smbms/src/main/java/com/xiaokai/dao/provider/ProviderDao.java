package com.xiaokai.dao.provider;

import com.xiaokai.pojo.Provider;

import java.sql.Connection;
import java.util.List;

public interface ProviderDao {
    //增加供应商
    public int add(Connection connection, Provider provider)throws Exception;

    //通过供应商的名称和编码获取供应商信息
    public List<Provider> getProviderList(Connection connection, String proName, String proCode)throws Exception;

    //通过proId删除Provider
    public int deleteProviderById(Connection connection, String delId)throws Exception;

    //通过proId获取Provider
    public Provider getProviderById(Connection connection, String id)throws Exception;

    //修改供应商信息
    public int modify(Connection connection, Provider provider)throws Exception;
}
