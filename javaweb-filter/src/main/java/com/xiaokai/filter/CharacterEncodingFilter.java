package com.xiaokai.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CharacterEncodingFilter初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html");

        System.out.println("CharacterEncodingFilter执行前......");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("CharacterEncodingFilter执行后......");
    }

    @Override
    public void destroy() {

        System.out.println("CharacterEncodingFilter销毁......");
    }
}
