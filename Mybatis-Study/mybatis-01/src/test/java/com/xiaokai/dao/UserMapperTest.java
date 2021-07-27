package com.xiaokai.dao;

import com.xiaokai.pojo.User;
import com.xiaokai.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMapperTest {
    @Test
    public void selectUser(){
        //第一步：获取sqlSession对象
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSession();
            //执行sql
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
    public void getUserById(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUserById(1);
            System.out.println(user);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void addUser(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int add = mapper.addUser(new User(6, "abcd", "123123"));
            sqlSession.commit();
            System.out.println(add);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void deleteById(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int delete = mapper.deleteById(5);
            sqlSession.commit();
            System.out.println(delete);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void updateUser(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int flag = mapper.updateUser(new User(4, "dadada", "111111"));
            sqlSession.commit();
            System.out.println(flag);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void getUserByIdName(){
        SqlSession sqlSession = null;
        Map<String, Object> map = new HashMap<>();
        try {
            sqlSession = MybatisUtils.getSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            map.put("name","dadada");
            map.put("pwd","111111");
            User user = mapper.getUserByIdName(map);
            System.out.println(user);
        } finally {
            sqlSession.close();
        }

    }
}
