package com.xiaokai.dao.bill;

import com.mysql.jdbc.StringUtils;
import com.xiaokai.dao.BaseDao;
import com.xiaokai.pojo.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BillDaoImpl implements BillDao {
    @Override
    public int add(Connection connection, Bill bill) throws Exception {
        PreparedStatement preparedStatement = null;
        int execute = 0;
        if (connection!=null){
            String sql = "insert into smbms_bill(billCode, productName, productDesc, productUnit," +
                    "productCount, totalPrice, isPayment, createdBy, creationDate,  providerId) " +
                    "values (?,?,?,?,?,?,?,?,?,?);";
            Object[] Params = {bill.getBillCode(),bill.getProductName(),bill.getProductDesc(),
                    bill.getProductUnit(),bill.getProductCount(),bill.getTotalPrice(),bill.getIsPayment(),
                    bill.getCreatedBy(),bill.getCreationDate(),bill.getProviderId()};
            execute = BaseDao.executeUpdate(connection,preparedStatement,sql,Params);
            BaseDao.closeResource(null,preparedStatement,null);
        }
        return execute;
    }

    @Override
    public List<Bill> getBillList(Connection connection, Bill bill) throws Exception {
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        List<Bill> billList = new ArrayList<Bill>();//结果集
//        StringBuffer sql = new StringBuffer();//可以动态变化的sql查询语句
//        if (connection!=null){
//            //拼接sql条件
//            sql.append("select b.*,p.proName as providerName from smbms_bill as b ,smbms_provider as p where b.providerId = p.id ");
//            List<Object> list = new ArrayList<>();//存params
//            if (!StringUtils.isNullOrEmpty(bill.getProductName())){
//                sql.append(" and b.productName like ? ");
//                list.add("%"+bill.getProductName()+"%");
//            }
//            if (bill.getProviderId() > 0){
//                sql.append(" and b.providerId = ? ");
//                list.add(bill.getProviderId());
//            }
//            if (bill.getIsPayment() > 0){
//                sql.append(" and b.isPayment = ? ");
//                list.add(bill.getIsPayment());
//            }
////            sql.append(";");
//            System.out.println("sql------>"+sql.toString());
//
//            Object[] params = list.toArray();
//            resultSet = BaseDao.execute(connection, preparedStatement, sql.toString(), params, resultSet);
//            //使用多个Bill对象存储查询结果，每次将Bill存入billList
//            while (resultSet.next()){
//                Bill _bill = new Bill();
//                _bill.setId(resultSet.getInt("id"));
//                _bill.setBillCode(resultSet.getString("billCode"));
//                _bill.setProductName(resultSet.getString("productName"));
//                _bill.setProductDesc(resultSet.getString("productDesc"));
//                _bill.setProductUnit(resultSet.getString("productUnit"));
//                _bill.setProductCount(resultSet.getBigDecimal("productCount"));
//                _bill.setTotalPrice(resultSet.getBigDecimal("totalPrice"));
//                _bill.setIsPayment(resultSet.getInt("isPayment"));
//                _bill.setProviderId(resultSet.getInt("providerId"));
//                _bill.setProviderName(resultSet.getString("providerName"));
//                _bill.setCreationDate(resultSet.getTimestamp("creationDate"));
//                _bill.setCreatedBy(resultSet.getInt("createdBy"));
//                billList.add(_bill);
//            }
//            BaseDao.closeResource(null,preparedStatement,resultSet);
//        }
//        return billList;

        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Bill> billList = new ArrayList<Bill>();
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select b.*,p.proName as providerName from smbms_bill b, smbms_provider p where b.providerId = p.id");
            List<Object> list = new ArrayList<Object>();
            if(!StringUtils.isNullOrEmpty(bill.getProductName())){
                sql.append(" and productName like ?");
                list.add("%"+bill.getProductName()+"%");
            }
            if(bill.getProviderId() > 0){
                sql.append(" and providerId = ?");
                list.add(bill.getProviderId());
            }
            if(bill.getIsPayment() > 0){
                sql.append(" and isPayment = ?");
                list.add(bill.getIsPayment());
            }
            Object[] params = list.toArray();
            System.out.println("sql --------- > " + sql.toString());
            rs = BaseDao.execute(connection,pstm,sql.toString(),params,rs);
            while(rs.next()){
                Bill _bill = new Bill();
                _bill.setId(rs.getInt("id"));
                _bill.setBillCode(rs.getString("billCode"));
                _bill.setProductName(rs.getString("productName"));
                _bill.setProductDesc(rs.getString("productDesc"));
                _bill.setProductUnit(rs.getString("productUnit"));
                _bill.setProductCount(rs.getBigDecimal("productCount"));
                _bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
                _bill.setIsPayment(rs.getInt("isPayment"));
                _bill.setProviderId(rs.getInt("providerId"));
                _bill.setProviderName(rs.getString("providerName"));
                _bill.setCreationDate(rs.getTimestamp("creationDate"));
                _bill.setCreatedBy(rs.getInt("createdBy"));
                billList.add(_bill);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return billList;
    }

    @Override
    public int deleteBillById(Connection connection, String delId) throws Exception {
        PreparedStatement preparedStatement = null;
        int execute = 0;
        if (connection!=null){
            String sql = "delete from smbms_bill where id = ? ;";
            //注意delId是int类型，要转换
            Object[] params = {Integer.valueOf(delId)};
            execute = BaseDao.executeUpdate(connection,preparedStatement,sql,params);
        }
        return execute;
    }

    @Override
    public Bill getBillById(Connection connection, String id) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Bill bill = null;
        if (connection!=null){
            String sql = "select * from smbms_bill where id = ? ;";
            Object[] params = {Integer.valueOf(id)};
            resultSet = BaseDao.execute(connection,preparedStatement,sql,params,resultSet);
            if (resultSet.next()){
                bill.setId(resultSet.getInt("id"));
                bill.setBillCode(resultSet.getString("billCode"));
                bill.setProductName(resultSet.getString("productName"));
                bill.setProductDesc(resultSet.getString("productDesc"));
                bill.setProductUnit(resultSet.getString("productUnit"));
                bill.setProductCount(resultSet.getBigDecimal("productCount"));
                bill.setTotalPrice(resultSet.getBigDecimal("totalPrice"));
                bill.setIsPayment(resultSet.getInt("isPayment"));
                bill.setProviderId(resultSet.getInt("providerId"));
                bill.setProviderName(resultSet.getString("providerName"));
                bill.setModifyBy(resultSet.getInt("modifyBy"));
                bill.setModifyDate(resultSet.getTimestamp("modifyDate"));
            }
            BaseDao.closeResource(null,preparedStatement,resultSet);
        }
        return bill;
    }

    @Override
    public int modify(Connection connection, Bill bill) throws Exception {
        PreparedStatement preparedStatement = null;
        int execute = 0;
        if (connection!=null){
            String sql = "update smbms_bill set productName = ?,productDesc=?,productUnit=?,productCount=?," +
                    "totalPrice=?,isPayment=?,providerId=?,modifyBy=?,modifyDate=? where id = ?;";
            Object[] params = {bill.getProductName(),bill.getProductDesc(),bill.getProductUnit(),bill.getProductCount(),
            bill.getTotalPrice(),bill.getIsPayment(),bill.getProviderId(),bill.getModifyBy(),bill.getModifyDate(),bill.getId()};
            execute = BaseDao.executeUpdate(connection,preparedStatement,sql,params);
        }
        return execute;
    }

    @Override
    public int getBillCountByProviderId(Connection connection, String providerId) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int billCount = 0;
        if (connection!=null){
            String sql = "select count(1) as billCount from smbms_bill where providerId = ?;";
            Object[] params = {Integer.parseInt(providerId)};
            resultSet = BaseDao.execute(connection,preparedStatement,sql,params,resultSet);
            billCount = resultSet.getInt("billCount");
        }
        return billCount;
    }
}
