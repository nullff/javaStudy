<%--
  Created by IntelliJ IDEA.
  User: guanxiaokai
  Date: 2021/7/5
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
<h2>总共有 <span style="color: blue"> <%=this.getServletConfig().getServletContext().getAttribute("onlinecount")%> </span> 个session</h2>
  </body>
</html>
