package com.xiaokai.service.provider;

import com.xiaokai.dao.BaseDao;
import com.xiaokai.dao.provider.ProviderDao;
import com.xiaokai.dao.provider.ProviderDaoImpl;
import com.xiaokai.pojo.Provider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProviderServiceImpl implements ProviderService {
    private ProviderDao providerDao = new ProviderDaoImpl();
    @Override
    public boolean add(Provider provider) throws SQLException {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            int add = providerDao.add(connection, provider);
            if (add > 0){
                flag = true;
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }

    @Override
    public List<Provider> getProviderList(String proName, String proCode) {
        Connection connection = null;
        List<Provider> providerList = null;

        System.out.println("query proName ---- > " + proName);
        System.out.println("query proCode ---- > " + proCode);

        try {
            connection = BaseDao.getConnection();
            providerList = providerDao.getProviderList(connection, proName, proCode);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection,null,null);
        }
        return providerList;
    }

    @Override
    public int deleteProviderById(String delId) {
        Connection connection = null;
        int del = 0;

        try {
            connection = BaseDao.getConnection();
            del = providerDao.deleteProviderById(connection,delId);
            if (del > 0){

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return del;
    }

    @Override
    public Provider getProviderById(String id) {
        Connection connection = null;
        Provider provider = null;
        try {
            connection = BaseDao.getConnection();
            provider = providerDao.getProviderById(connection, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection,null,null);
        }
        return provider;
    }

    @Override
    public boolean modify(Provider provider) {
        Connection connection = null;
        boolean flag = false;

        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            int modify = providerDao.modify(connection, provider);
            if (modify > 0){
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
