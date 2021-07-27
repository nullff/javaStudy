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
    public void selectUserTest(){
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
}
