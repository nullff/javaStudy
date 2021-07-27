package com.xiaokai.servlet.bill;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;
import com.xiaokai.pojo.Bill;
import com.xiaokai.pojo.Provider;
import com.xiaokai.pojo.User;
import com.xiaokai.service.bill.BillService;
import com.xiaokai.service.bill.BillServiceImpl;
import com.xiaokai.service.provider.ProviderService;
import com.xiaokai.service.provider.ProviderServiceImpl;
import com.xiaokai.util.Constants;

import javax.print.DocFlavor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BillServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");
        if(method != null && method.equals("query")){
            this.query(req,resp);
        }else if(method != null && method.equals("add")){
            this.add(req,resp);
        }else if(method != null && method.equals("view")){
            this.getBillById(req,resp,"billview.jsp");
        }else if(method != null && method.equals("modify")){
            this.getBillById(req,resp,"billmodify.jsp");
        }else if(method != null && method.equals("modifysave")){
            this.modify(req,resp);
        }else if(method != null && method.equals("delbill")){
            this.delBill(req,resp);
        }else if(method != null && method.equals("getproviderlist")){
            this.getProviderlist(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String queryProductName = req.getParameter("queryProductName");
//        String queryProviderId = req.getParameter("queryProviderId");
//        String queryIsPayment = req.getParameter("queryIsPayment");
//
//        Bill bill = new Bill();
//        List<Bill> billList = new ArrayList<>();
//        BillService billService = new BillServiceImpl();
//
//        if (StringUtils.isNullOrEmpty(queryProductName)){
//            queryProductName = "";
//            bill.setProviderName("");
//        }
//        if (StringUtils.isNullOrEmpty(queryProviderId)){
//            bill.setProviderId(0);
//        }else {
//            bill.setProviderId(Integer.parseInt(queryProviderId));
//        }
//        if (StringUtils.isNullOrEmpty(queryIsPayment)){
//            bill.setIsPayment(0);
//        }else {
//            bill.setIsPayment(Integer.parseInt(queryIsPayment));
//        }
//        bill.setProductName(queryProductName);
//        billList = billService.getBillList(bill);
//
//        req.setAttribute("billList",billList);
//        req.setAttribute("queryProductName",queryProductName);
//        req.setAttribute("queryProviderId",queryProviderId);
//        req.setAttribute("queryIsPayment",queryIsPayment);
//
//        req.getRequestDispatcher("billlist.jsp").forward(req,resp);

        List<Provider> providerList = new ArrayList<Provider>();
        ProviderService providerService = new ProviderServiceImpl();
        providerList = providerService.getProviderList("","");
        req.setAttribute("providerList", providerList);

        String queryProductName = req.getParameter("queryProductName");
        String queryProviderId = req.getParameter("queryProviderId");
        String queryIsPayment = req.getParameter("queryIsPayment");
        if(StringUtils.isNullOrEmpty(queryProductName)){
            queryProductName = "";
        }

        List<Bill> billList = new ArrayList<Bill>();
        BillService billService = new BillServiceImpl();
        Bill bill = new Bill();
        if (StringUtils.isNullOrEmpty(queryProductName)){
            queryProductName = "";
            bill.setProviderName("");
        }else {
            bill.setProductName(queryProductName);
        }
        if(StringUtils.isNullOrEmpty(queryIsPayment)){
            bill.setIsPayment(0);
        }else{
            bill.setIsPayment(Integer.parseInt(queryIsPayment));
        }

        if(StringUtils.isNullOrEmpty(queryProviderId)){
            bill.setProviderId(0);
        }else{
            bill.setProviderId(Integer.parseInt(queryProviderId));
        }

        bill.setProductName(queryProductName);
        billList = billService.getBillList(bill);
        req.setAttribute("billList", billList);
        req.setAttribute("queryProductName", queryProductName);
        req.setAttribute("queryProviderId", queryProviderId);
        req.setAttribute("queryIsPayment", queryIsPayment);
        req.getRequestDispatcher("billlist.jsp").forward(req, resp);

    };

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Bill bill = new Bill();
        boolean flag = false;
        BillService billService = new BillServiceImpl();

        String billCode = req.getParameter("billCode");
        String productName = req.getParameter("productName");
        String productUnit = req.getParameter("productUnit");
        String productCount = req.getParameter("productCount");
        String totalPrice = req.getParameter("totalPrice");
        String providerId = req.getParameter("providerId");
        String isPayment = req.getParameter("isPayment");

        bill.setBillCode(billCode);
        bill.setProductName(productName);
        bill.setProductUnit(productUnit);
        bill.setProductCount(new BigDecimal(productCount).setScale(2,BigDecimal.ROUND_DOWN));
        bill.setTotalPrice(new BigDecimal(totalPrice).setScale(2,BigDecimal.ROUND_DOWN));
        bill.setProviderId(Integer.parseInt(providerId));
        bill.setIsPayment(Integer.parseInt(isPayment));
        bill.setCreatedBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());
        bill.setCreationDate(new Date());

        flag = billService.add(bill);
        if (flag){
            resp.sendRedirect(req.getContextPath()+"/jsp/bill.do?method=query");
        }else {
            req.getRequestDispatcher("billadd.jsp").forward(req,resp);
        }
    };

    private void getBillById(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
        String billid = req.getParameter("billid");
        Bill bill = new Bill();
        BillServiceImpl billService = new BillServiceImpl();
        bill = billService.getBillById(billid);
        req.setAttribute("bill",bill);
        req.getRequestDispatcher(url).forward(req,resp);
    };

    private void modify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String billCode = req.getParameter("billCode");
        String productName = req.getParameter("productName");
        String productUnit = req.getParameter("productUnit");
        String productCount = req.getParameter("productCount");
        String totalPrice = req.getParameter("totalPrice");
        String providerId = req.getParameter("providerId");
        String isPayment = req.getParameter("isPayment");

        Bill bill = new Bill();
        BillServiceImpl billService = new BillServiceImpl();
        boolean flag = false;

        bill.setBillCode(billCode);
        bill.setProductName(productName);
        bill.setProductUnit(productUnit);
        bill.setProductCount(new BigDecimal(productCount).setScale(2,BigDecimal.ROUND_DOWN));
        bill.setTotalPrice(new BigDecimal(totalPrice).setScale(2,BigDecimal.ROUND_DOWN));
        bill.setProviderId(Integer.parseInt(providerId));
        bill.setIsPayment(Integer.parseInt(isPayment));
        bill.setModifyBy(((User)req.getSession().getAttribute(Constants.USER_SESSION)).getId());
        bill.setModifyDate(new Date());

        flag = billService.modify(bill);

        if (flag){
            resp.sendRedirect(req.getContextPath()+"/bill.do?method=query");
        }else {
            req.getRequestDispatcher("billmodify.jsp").forward(req,resp);
        }

    };

    private void delBill(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String billid = req.getParameter("billid");
        HashMap<String,String> resultMap = new HashMap<>();
        if (!StringUtils.isNullOrEmpty(billid)){
            BillServiceImpl billService = new BillServiceImpl();
            boolean flag = billService.deleteBillById(billid);
            if (flag){
                resultMap.put("delResult","true");
            }else {
                resultMap.put("delResult","false");
            }
        }else {
            resultMap.put("delResult","notexist");
        }

        //转化为json
        resp.setContentType("application/json");
        PrintWriter respWriter = resp.getWriter();
        respWriter.write(JSONArray.toJSONString(resultMap));
        respWriter.flush();
        respWriter.close();
    };

    private void getProviderlist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProviderServiceImpl providerService = new ProviderServiceImpl();
        List<Provider> providerList= new ArrayList<Provider>();
        System.out.println("getproviderlist =========================");
        providerList = providerService.getProviderList("", "");
        System.out.println(providerList.get(0).toString());


        resp.setContentType("application/json");
        PrintWriter respWriter = resp.getWriter();
        respWriter.write(JSONArray.toJSONString(providerList));
        respWriter.flush();
        respWriter.close();

    };

}
