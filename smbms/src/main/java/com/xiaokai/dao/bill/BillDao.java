package com.xiaokai.dao.bill;

import com.xiaokai.pojo.Bill;

import java.sql.Connection;
import java.util.List;

public interface BillDao {
    //增加订单
    public int add(Connection connection, Bill bill)throws Exception;

    //通过条件查询获取供应商列表
    public List<Bill> getBillList(Connection connection, Bill bill)throws Exception;

    //通过delId删除Bill
    public int deleteBillById(Connection connection, String delId)throws Exception;

    //通过billId获取Bill
    public Bill getBillById(Connection connection, String id)throws Exception;

    //修改订单信息
    public int modify(Connection connection, Bill bill)throws Exception;

    //根据供应商ID查询订单数量
    public int getBillCountByProviderId(Connection connection, String providerId)throws Exception;
}
