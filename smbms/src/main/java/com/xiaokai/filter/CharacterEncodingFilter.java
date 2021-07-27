package com.xiaokai.filter;

import javax.servlet.*;
import java.io.IOException;
//字符编码过滤器，设置编码UTF-8
public class CharacterEncodingFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        //过滤器放行chain链
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public void destroy() {

    }
}
