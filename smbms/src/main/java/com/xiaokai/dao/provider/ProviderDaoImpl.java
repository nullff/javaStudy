package com.xiaokai.dao.provider;

import com.mysql.jdbc.StringUtils;
import com.xiaokai.dao.BaseDao;
import com.xiaokai.pojo.Provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProviderDaoImpl implements ProviderDao {
    @Override
    public int add(Connection connection, Provider provider) throws Exception {
        PreparedStatement preparedStatement = null;
        int execute = 0;
        if (connection!=null){
            String sql = "insert into  smbms_provider(proCode, proName, proDesc, proContact, proPhone, " +
                    " proAddress, proFax, createdBy, creationDate) values (?,?,?,?,?,?,?,?,?);";
            Object[] params = {provider.getProCode(),provider.getProName(),provider.getProDesc(),provider.getProContact(),
            provider.getProPhone(),provider.getProAddress(),provider.getProFax(),provider.getCreatedBy(),provider.getCreationDate()};
            try {
                execute = BaseDao.executeUpdate(connection,preparedStatement,sql,params);
                System.out.println("### ------> add provider success!......");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.out.println("### ------> add provider filed!......");
            }
            BaseDao.closeResource(null,preparedStatement,null);
        }
        return execute;
    }

    @Override
    public List<Provider> getProviderList(Connection connection, String proName, String proCode) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Provider> providerList = new ArrayList<>();

        if (connection!=null){
            StringBuffer sql = new StringBuffer();
            List<Object> list = new ArrayList<>();
            sql.append("select * from smbms_provider where 1=1 ");
            if (!StringUtils.isNullOrEmpty(proName)){
                sql.append(" and proName like ? ");
                list.add("%"+proName+"%");
            }
            if (!StringUtils.isNullOrEmpty(proCode)){
                sql.append(" and proCode like ? ;");
                list.add("%"+proCode+"%");
            }
            sql.append(";");
            System.out.println("sql------>"+sql.toString());
            Object[] params = list.toArray();

            resultSet = BaseDao.execute(connection,preparedStatement,sql.toString(),params,resultSet);

            while (resultSet.next()){
                Provider _provider = new Provider();
                _provider.setId(resultSet.getInt("id"));
                _provider.setProCode(resultSet.getString("proCode"));
                _provider.setProName(resultSet.getString("proName"));
                _provider.setProDesc(resultSet.getString("proDesc"));
                _provider.setProContact(resultSet.getString("proContact"));
                _provider.setProPhone(resultSet.getString("proPhone"));
                _provider.setProAddress(resultSet.getString("proAddress"));
                _provider.setProFax(resultSet.getString("proFax"));
                _provider.setCreationDate(resultSet.getTimestamp("creationDate"));
                providerList.add(_provider);
            }
            BaseDao.closeResource(null,preparedStatement,resultSet);
        }
        return providerList;
    }

    @Override
    public int deleteProviderById(Connection connection, String delId) throws Exception {
        PreparedStatement preparedStatement = null;
        int execute = 0;
        if (connection!=null){
            String sql = "delete from smbms_provider where id = ? ;";
            Object[] params = {Integer.parseInt(delId)};

            execute = BaseDao.executeUpdate(null,preparedStatement,sql,params);

        }
        return execute;
    }

    @Override
    public Provider getProviderById(Connection connection, String id) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Provider provider = null;
        if (connection!=null){
            String sql = "select * from smbms_provider where id = ? ;";
            Object[] params = {Integer.parseInt(id)};
            resultSet = BaseDao.execute(connection,preparedStatement,sql,params,resultSet);
            while (resultSet.next()){
                provider = new Provider();
                provider.setId(resultSet.getInt("id"));
                provider.setProCode(resultSet.getString("proCode"));
                provider.setProName(resultSet.getString("proName"));
                provider.setProDesc(resultSet.getString("proDesc"));
                provider.setProContact(resultSet.getString("proContact"));
                provider.setProPhone(resultSet.getString("proPhone"));
                provider.setProAddress(resultSet.getString("proAddress"));
                provider.setProFax(resultSet.getString("proFax"));
                provider.setCreatedBy(resultSet.getInt("createdBy"));
                provider.setCreationDate(resultSet.getTimestamp("creationDate"));
                provider.setModifyBy(resultSet.getInt("modifyBy"));
                provider.setModifyDate(resultSet.getTimestamp("modifyDate"));
            }
            BaseDao.closeResource(null,preparedStatement,resultSet);
        }
        return provider;
    }

    @Override
    public int modify(Connection connection, Provider provider) throws Exception {
        PreparedStatement preparedStatement = null;
        int execute = 0;
        if (connection!=null){
            String sql = "update smbms_provider set proName=?,proDesc=?,proContact=?," +
                    "proPhone=?,proAddress=?,proFax=?,modifyBy=?,modifyDate=? where id = ? ";
            Object[] params = {provider.getProName(),provider.getProDesc(),provider.getProContact(),provider.getProPhone(),provider.getProAddress(),
                    provider.getProFax(),provider.getModifyBy(),provider.getModifyDate(),provider.getId()};
            execute = BaseDao.executeUpdate(connection,preparedStatement,sql,params);
            BaseDao.closeResource(null,preparedStatement,null);
        }
        return execute;
    }
}
