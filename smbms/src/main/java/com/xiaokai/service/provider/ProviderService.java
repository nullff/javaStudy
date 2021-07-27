package com.xiaokai.service.provider;

import com.xiaokai.pojo.Provider;

import java.sql.SQLException;
import java.util.List;

public interface ProviderService {
    //增加供应商
    public boolean add(Provider provider) throws SQLException;

    //通过供应商名称或者编码获取供应商列表（模糊查询）
    public List<Provider> getProviderList(String proName, String proCode);

    //通过proId删除供应商
    public int deleteProviderById(String delId);

    //通过proId查询供应商
    public Provider getProviderById(String id);

    //修改供应商信息
    public boolean modify(Provider provider);
}
