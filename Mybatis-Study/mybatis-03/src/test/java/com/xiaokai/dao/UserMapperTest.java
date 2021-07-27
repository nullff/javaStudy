package com.xiaokai.dao;

import com.xiaokai.pojo.User;
import com.xiaokai.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserMapperTest {
    @Test
    public void testGetUserById(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.getSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUserById(4);
            System.out.println(user);
        } finally {
            sqlSession.close();
        }
    }
}
