package com.xiaokai.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;
import com.xiaokai.pojo.Role;
import com.xiaokai.pojo.User;
import com.xiaokai.service.role.RoleService;
import com.xiaokai.service.role.RoleServiceImpl;
import com.xiaokai.service.user.UserService;
import com.xiaokai.service.user.UserServiceImpl;
import com.xiaokai.util.Constants;
import com.xiaokai.util.PageSupport;
import org.apache.taglibs.standard.tlv.JstlSqlTLV;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method!=null && method.equals("savepwd")){
            this.updatePwd(req,resp);
        }else if (method!=null && method.equals("pwdmodify")){
            this.pwdmodify(req,resp);
        }else if(method != null && method.equals("add")){
            this.add(req,resp);
        }else if(method != null && method.equals("query")){
            this.query(req,resp);
        }else if(method != null && method.equals("getrolelist")){
            this.getRoleList(req,resp);
        }else if(method != null && method.equals("ucexist")){
            this.userCodeExist(req,resp);
        }else if(method != null && method.equals("deluser")){
            this.delUser(req,resp);
        }else if(method != null && method.equals("view")){
            this.getUserById(req,resp,"userview.jsp");
        }else if(method != null && method.equals("modify")){
            this.getUserById(req,resp,"usermodify.jsp");
        }else if(method != null && method.equals("modifyexe")){
            this.modify(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
    //???????????????????????????
    public void updatePwd(HttpServletRequest req, HttpServletResponse resp){
        //???session?????????ID
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = req.getParameter("newpassword");

        boolean flag = false;

        if (o!=null && !StringUtils.isNullOrEmpty(newpassword)){
            UserService userService = new UserServiceImpl();
            flag = userService.updatePwd(((User) o).getId(),newpassword);

            if (flag){
                req.setAttribute("message","???????????????????????????????????????????????????");
                //????????????????????????????????????session
                req.getSession().removeAttribute(Constants.USER_SESSION);
            }else {
                req.setAttribute("message","??????????????????");
            }
        }else {
            req.setAttribute("message","??????????????????");
        }
        try {
            req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Ajax?????????????????
    public void pwdmodify(HttpServletRequest request,HttpServletResponse response){
        Object o = request.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = request.getParameter("oldpassword");

        //?????????resultMap
        HashMap <String ,String > resultMap = new HashMap<String,String>();

        //??????????????????session????????????????????????
        if (o == null){
            //??????session??????????????????session
            resultMap.put("result","sessionerror");
        } else if (StringUtils.isNullOrEmpty(oldpassword)){
            //?????????????????????
            resultMap.put("result","error");
        }else if (!((User)o).getUserPassword().equals(oldpassword)){
            //??????????????????
            resultMap.put("result","false");
        }else if (((User)o).getUserPassword().equals(oldpassword)){
            resultMap.put("result","true");
        }

        response.setContentType("application/json");
        try {
            //???HashMap?????????JSON????????????js
            PrintWriter writer = response.getWriter();
            writer.write(JSONArray.toJSONString(resultMap));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //????????????
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("add()================");
        String userCode = req.getParameter("userCode");
        String userName = req.getParameter("userName");
        String userPassword = req.getParameter("userPassword");
        String gender = req.getParameter("gender");
        String birthday = req.getParameter("birthday");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String userRole = req.getParameter("userRole");

        User user = new User();
        user.setUserCode(userCode);
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setGender(Integer.valueOf(gender));
        try {
            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(Integer.valueOf(userRole));

        UserServiceImpl userService = new UserServiceImpl();
        if (userService.add(user)){
            resp.setContentType(req.getContextPath()+"/jsp/user.do?method=query");
        }else {
            req.getRequestDispatcher("useradd.jsp").forward(req,resp);
        }
    }

    //????????????????????????
    public void query(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //?????????????????????
        String queryUserName = req.getParameter("queryname");
        String temp = req.getParameter("queryUserRole");
        String pageIndex = req.getParameter("pageIndex");
        int queryUserRole = 0;

        //??????????????????
        UserServiceImpl userService = new UserServiceImpl();
        List<User> userList = null;
        //??????????????????
        int pageSize = Constants.pageSize;
        //??????????????????????????????????????????????????????1
        int currentPageNo = 1;
        System.out.println("### queryname servlet======>"+queryUserName);
        System.out.println("### queryUserRole servlet======>"+temp);
        System.out.println("### pageIndex servlet======>"+pageIndex);

        //?????????????????????????????????
        if (queryUserName == null){
            queryUserName = "";
        }
        if (temp != null && !temp.equals("")){
            queryUserRole = Integer.parseInt(temp);
        }
        if (pageIndex != null){
            try {
                currentPageNo = Integer.valueOf(pageIndex);
            } catch (NumberFormatException e) {
                resp.sendRedirect("error.jsp");
            }
        }

        //????????????
        int totalCount = userService.getUserCount(queryUserName,queryUserRole);
        //?????????
        PageSupport pageSupport = new PageSupport();
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setPageSize(pageSize);
        pageSupport.setTotalPageCount(totalCount);

        int totalPageCount = pageSupport.getTotalPageCount();

        //??????????????????
        if (currentPageNo < 1){
            currentPageNo = 1;
        }else if (currentPageNo > totalPageCount){
            currentPageNo = totalPageCount;
        }

        //??????
        userList = userService.getUserList(queryUserName,queryUserRole,currentPageNo,pageSize);
        req.setAttribute("userList",userList);
        List<Role> roleList = null;
        RoleService roleService = new RoleServiceImpl();
        roleList = roleService.getRoleList();
        req.setAttribute("roleList",roleList);
        req.setAttribute("queryUserName",queryUserName);
        req.setAttribute("queryUserRole",queryUserRole);
        req.setAttribute("totalPageCount",totalPageCount);
        req.setAttribute("totalCount",totalCount);
        req.setAttribute("currentPageNo",currentPageNo);
        req.getRequestDispatcher("userlist.jsp").forward(req,resp);
    }

    //???add??????modify???????????????ajax????????????????????????list
    public void getRoleList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Role> roleList = null;
        RoleService roleService = new RoleServiceImpl();
        roleList = roleService.getRoleList();
        //???roleList?????????json????????????
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(JSONArray.toJSONString(roleList));
        writer.flush();
        writer.close();
    }

    //ajax????????????--userCode???????????????
    public void userCodeExist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //??????????????????????????????
        //??????????????????
        String userCode = req.getParameter("userCode");
        //Ajax?????????
        HashMap<String,String> resultMap = new HashMap<String ,String >();

        //??????????????????
        if (StringUtils.isNullOrEmpty(userCode)){
            resultMap.put("result","exist");
        } else {
            UserService userService = new UserServiceImpl();
            User user = userService.selectUserCodeExist(userCode);
            if (user != null){
                resultMap.put("result","exist");
            }else {
                resultMap.put("result","notexist");
            }
        }

        //?????????HashMap?????????json;
        //????????????????????????
        resp.setContentType("application/json");
        //??????????????????
        PrintWriter respWriter = resp.getWriter();
        respWriter.write(JSONArray.toJSONString(resultMap));
        respWriter.flush();
        respWriter.close();
    }

    //????????????
    public void delUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //??????????????????id????????????
        String userid = req.getParameter("userid");
        int delId = 0;
        try {
            delId = Integer.parseInt(userid);
        } catch (NumberFormatException e) {
            delId = 0;
        }
        //ajax?????????
        HashMap<String, String> resultMap = new HashMap<String, String>();
        if (delId <= 0 ){
            resultMap.put("result","notexist");
        }else {
            UserService userService = new UserServiceImpl();
            boolean flag = userService.deleteUserById(delId);
            if (!flag){
                resultMap.put("result","false");
            }else {
                resultMap.put("result","true");
            }
        }
        //???HashMap?????????Json
        resp.setContentType("application/json");
        PrintWriter respWriter = resp.getWriter();
        respWriter.write(JSONArray.toJSONString(resultMap));
        respWriter.flush();
        respWriter.close();
    }

    //??????id????????????????????????
    public void getUserById(HttpServletRequest req, HttpServletResponse resp,String url) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        if (StringUtils.isNullOrEmpty(uid)){
            UserService userService = new UserServiceImpl();
            User user = userService.getUserById(uid);
            req.setAttribute("user",user);
            req.getRequestDispatcher(url).forward(req,resp);
        }
    }

    //??????????????????
    public void modify(HttpServletRequest req, HttpServletResponse resp){
        String id = req.getParameter("uid");
        String userName = req.getParameter("userName");
        String gender = req.getParameter("gender");
        String birthday = req.getParameter("birthday");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String userRole = req.getParameter("userRole");

        UserServiceImpl userService = new UserServiceImpl();
        User user = new User();

        user.setId(Integer.valueOf(id));
        user.setUserName(userName);
        user.setGender(Integer.valueOf(gender));
        try {
            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(Integer.valueOf(userRole));
        user.setModifyBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());
        user.setModifyDate(new Date());

        if (userService.modify(user)){
            resp.setContentType(req.getContextPath()+"/jsp/user.do?method=query");
        } else {
            try {
                req.getRequestDispatcher("usermodify.jsp").forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
