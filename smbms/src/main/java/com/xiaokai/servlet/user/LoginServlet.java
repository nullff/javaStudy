package com.xiaokai.servlet.user;

import com.xiaokai.pojo.User;
import com.xiaokai.service.user.UserService;
import com.xiaokai.service.user.UserServiceImpl;
import com.xiaokai.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    //控制层调用业务层的代码
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("###LoginServlet--start...");
        //获取用户名和密码
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");

        //使用业务层代码，查询数据库，比对登录信息
        UserService userService = new UserServiceImpl();
        User user = userService.login(userCode, userPassword);

        //如果查询不为空，说明用户存在，
        if (user!=null){
            //将用户信息放到session
            req.getSession().setAttribute(Constants.USER_SESSION,user);
            //跳转到内部主页
            resp.sendRedirect("/jsp/frame.jsp");
        }else {
            //查无此人，返回登录，提示错误
            req.setAttribute("error","用户名或者密码错误");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
