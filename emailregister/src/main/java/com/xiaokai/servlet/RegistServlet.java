package com.xiaokai.servlet;

import com.xiaokai.pojo.User;
import com.xiaokai.utils.Sendmail;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        System.out.println("username="+username+"\n"+"password="+password+"\n"+"email="+email+"\n");
        User user = new User(username, password, email);

        //用户注册成功后给用户发送一封邮件
        //使用线程来自动发送邮件，防止出现耗时和网站注册人数过多的情况；
        Sendmail send = new Sendmail(user);
        //启动线程，启动后执行run()方法发送邮件
        send.start();

        //注册用户，
        req.setAttribute("message","注册成功，我们已经向您的注册邮箱发送了带有注册信息的电子邮件，请查收！");
        req.getRequestDispatcher("info.jsp").forward(req,resp);

    }
}
