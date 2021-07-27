package com.xiaokai.dao;

import com.xiaokai.pojo.User;
import com.xiaokai.utils.MybatisUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMapperTest {
    static Logger logger = Logger.getLogger(UserMapperTest.class);
    @Test
    public void testGetUserById(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSession();
            logger.info("info:"+sqlSession);
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUserById(4);
            System.out.println(user);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testLog4j(){
        logger.info("info:xiaokai");
        logger.debug("debug:xiaokai");
        logger.error("error:xiaokai");
    }

    @Test
    public void getUserListLimit(){
        SqlSession sqlSession = null;
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = MybatisUtils.getSession();
            logger.info("获得sqlsession连接");
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            logger.info("mapper:"+mapper);
            int startIndex = 1;//第几页
            int pageSize = 2;//每页显示几个
            map.put("pageCount",(startIndex-1)*pageSize);
            map.put("pageSize",pageSize);
            List<User> userList = mapper.getUserListLimit(map);
            for (User user : userList) {
                System.out.println(user);
            }
        } finally {
            sqlSession.close();
            logger.info("数据库连接关闭");
        }
    }

    @Test
    public void getUserListRowBounds(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSession();

            int currentPage = 2;
            int pageSize = 2;
            RowBounds rowBounds = new RowBounds((currentPage - 1) * pageSize, pageSize);

            //通过session方法传递（不推荐）
            List<Object> users = sqlSession.selectList("com.xiaokai.dao.UserMapper.getUserListRowBounds", null, rowBounds);
            for (Object user : users) {
                System.out.println(user);
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testGetUsers(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = mapper.getUserList();
            for (User user : userList) {
                System.out.println(user);
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserById(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User userById = mapper.getUserById(1);
            System.out.println(userById);
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void testAddUser(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = new User(5, "adad", "111222");
            int i = mapper.addUser(user);
            System.out.println(i);
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void testUpdateById(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = new User(5, "kakak", "222111");
            int i = mapper.updateUser(user);
            System.out.println(i);
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void testDeleteById(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int i = mapper.deleteById(5);
            System.out.println(i);
        } finally {
            sqlSession.close();
        }
    }
}
