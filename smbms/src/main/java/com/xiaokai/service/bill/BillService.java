package com.xiaokai.service.bill;

import com.xiaokai.pojo.Bill;

import java.util.List;

public interface BillService {
    //增加订单
    public boolean add(Bill bill);

    //通过条件获取订单列表
    public List<Bill> getBillList(Bill bill);

    //通过billId删除Bill
    public boolean deleteBillById(String delId);

    //通过billId获取Bill
    public Bill getBillById(String id);

    //修改订单信息
    public boolean modify(Bill bill);
}
