package com.xiaokai.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineCountListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext ctx = se.getSession().getServletContext();

        System.out.println(se.getSession().getId());


        Integer onlinecount = (Integer) ctx.getAttribute("onlinecount");
        if (onlinecount == null){
            onlinecount = new Integer(1);

        }else {
            int count = onlinecount.intValue();
            onlinecount = new Integer(count+1);
        }
        ctx.setAttribute("onlinecount",onlinecount);
        System.out.println(onlinecount.intValue());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext ctx = se.getSession().getServletContext();

        System.out.println(se.getSession().getId());

        Integer onlinecount = (Integer) ctx.getAttribute("onlinecount");
        if (onlinecount == null){
            onlinecount = new Integer(0);

        }else {
            int count = onlinecount.intValue();
            onlinecount = new Integer(count-1);
        }
        ctx.setAttribute("onlinecount",onlinecount);
    }
}
