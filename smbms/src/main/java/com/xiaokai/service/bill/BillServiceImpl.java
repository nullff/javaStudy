package com.xiaokai.service.bill;

import com.xiaokai.dao.BaseDao;
import com.xiaokai.dao.bill.BillDao;
import com.xiaokai.dao.bill.BillDaoImpl;
import com.xiaokai.pojo.Bill;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillServiceImpl implements BillService {
    private BillDao billDao = new BillDaoImpl();

    @Override
    public boolean add(Bill bill) {
        Connection connection = null;
        connection = BaseDao.getConnection();
        boolean flag = false;
        try {
            connection.setAutoCommit(false);
            int add = billDao.add(connection, bill);
            if (add > 0){
                flag = true;
            }
            connection.commit();
        }  catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
                System.out.println("rollback==================");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }

    @Override
    public List<Bill> getBillList(Bill bill) {
//        Connection connection = null;
//        List<Bill> billList = null;
//
//        System.out.println("query productName ---- > " + bill.getProductName());
//        System.out.println("query providerId ---- > " + bill.getProviderId());
//        System.out.println("query isPayment ---- > " + bill.getIsPayment());
//
//        try {
//            connection = BaseDao.getConnection();
//            System.out.println(bill.toString());
//            billList = billDao.getBillList(connection,bill);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            BaseDao.closeResource(connection,null,null);
//        }
//        return billList;

        Connection connection = null;
        List<Bill> billList = null;
        System.out.println("query productName ---- > " + bill.getProductName());
        System.out.println("query providerId ---- > " + bill.getProviderId());
        System.out.println("query isPayment ---- > " + bill.getIsPayment());

        try {
            connection = BaseDao.getConnection();
            System.out.println(bill.toString());
            billList = billDao.getBillList(connection, bill);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            BaseDao.closeResource(connection, null, null);
        }
        return billList;
    }

    @Override
    public boolean deleteBillById(String delId) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            int i = billDao.deleteBillById(connection, delId);
            if (i>0){
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }

    @Override
    public Bill getBillById(String id) {
        Connection connection = null;
        Bill bill = null;
        try {
            connection = BaseDao.getConnection();
            bill = billDao.getBillById(connection, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection,null,null);
        }
        return bill;
    }

    @Override
    public boolean modify(Bill bill) {
        Connection connection = null;
        boolean flag = false;

        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            int i = billDao.modify(connection, bill);
            if (i>0){
                flag = true;
            }
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }
}
