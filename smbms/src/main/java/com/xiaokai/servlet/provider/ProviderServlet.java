package com.xiaokai.servlet.provider;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;
import com.xiaokai.pojo.Provider;
import com.xiaokai.pojo.User;
import com.xiaokai.service.provider.ProviderService;
import com.xiaokai.service.provider.ProviderServiceImpl;
import com.xiaokai.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ProviderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if(method != null && method.equals("query")){
            this.query(req,resp);
        }else if(method != null && method.equals("add")){
            try {
                this.add(req,resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else if(method != null && method.equals("view")){
            this.getProviderById(req,resp,"providerview.jsp");
        }else if(method != null && method.equals("modify")){
            this.getProviderById(req,resp,"providermodify.jsp");
        }else if(method != null && method.equals("modifysave")){
            this.modify(req,resp);
        }else if(method != null && method.equals("delprovider")){
            this.delProvider(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String queryProCode = req.getParameter("queryProCode");
        String queryProName = req.getParameter("queryProName");
        if (StringUtils.isNullOrEmpty(queryProCode)){
            queryProCode = "";
        }
        if (StringUtils.isNullOrEmpty(queryProName)){
            queryProName = "";
        }

        List<Provider> providerList = new ArrayList<>();
        ProviderServiceImpl providerService = new ProviderServiceImpl();
        providerList = providerService.getProviderList(queryProName, queryProCode);

        req.setAttribute("providerList",providerList);
        req.setAttribute("queryProCode",queryProCode);
        req.setAttribute("queryProName",queryProName);

        req.getRequestDispatcher("providerlist.jsp").forward(req,resp);

    };

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String proCode = req.getParameter("proCode");
        String proName = req.getParameter("proName");
        String proContact = req.getParameter("proContact");
        String proPhone = req.getParameter("proPhone");
        String proAddress = req.getParameter("proAddress");
        String proFax = req.getParameter("proFax");
        String proDesc = req.getParameter("proDesc");

        Provider provider = new Provider();
        provider.setProCode(proCode);
        provider.setProName(proName);
        provider.setProContact(proContact);
        provider.setProPhone(proPhone);
        provider.setProAddress(proAddress);
        provider.setProFax(proFax);
        provider.setProDesc(proDesc);
        provider.setCreatedBy(((User)(req.getSession().getAttribute(Constants.USER_SESSION))).getId());
        provider.setCreationDate(new Date());

        ProviderServiceImpl providerService = new ProviderServiceImpl();
        boolean flag = false;
        flag = providerService.add(provider);
        if (flag){
            resp.sendRedirect(req.getContextPath()+"/jsp/provider.do?method=query");
        }else {
            req.getRequestDispatcher("provideradd.jsp").forward(req,resp);
        }
    };

    private void getProviderById(HttpServletRequest req, HttpServletResponse resp,String url) throws ServletException, IOException {
        String proid = req.getParameter("proid");
        ProviderServiceImpl providerService = new ProviderServiceImpl();
        Provider provider = null;
        provider = providerService.getProviderById(proid);
        req.setAttribute("provider",provider);
        req.getRequestDispatcher(url).forward(req,resp);
    };

    private void modify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String proCode = req.getParameter("proCode");
        String proName = req.getParameter("proName");
        String proContact = req.getParameter("proContact");
        String proPhone = req.getParameter("proPhone");
        String proAddress = req.getParameter("proAddress");
        String proFax = req.getParameter("proFax");
        String proDesc = req.getParameter("proDesc");

        Provider provider = new Provider();
        provider.setProCode(proCode);
        provider.setProName(proName);
        provider.setProContact(proContact);
        provider.setProPhone(proPhone);
        provider.setProAddress(proAddress);
        provider.setProFax(proFax);
        provider.setProDesc(proDesc);
        provider.setModifyBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());
        provider.setModifyDate(new Date());

        ProviderService providerService = new ProviderServiceImpl();
        boolean flag = providerService.modify(provider);
        if (flag){
            resp.sendRedirect(req.getContextPath()+"/jsp/provider.do?method=query");
        }else {
            req.getRequestDispatcher("providermodify.jsp").forward(req,resp);
        }
    };

    private void delProvider(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String proid = req.getParameter("proid");
        ProviderService providerService = new ProviderServiceImpl();
        HashMap<String,String> resultMap = new HashMap<>();
        if (StringUtils.isNullOrEmpty(proid)) {
            int flag = providerService.deleteProviderById(proid);
            if (flag > 0) {
                resultMap.put("delResult", "true");
            }else {
                resultMap.put("delResult","false");
            }
        }else {
            resultMap.put("delResult","notexist");
        }

        resp.setContentType("application/json");
        PrintWriter respWriter = resp.getWriter();
        respWriter.write(JSONArray.toJSONString(resultMap));
        respWriter.flush();
        respWriter.close();
    };
}
