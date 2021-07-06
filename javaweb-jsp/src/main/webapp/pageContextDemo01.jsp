<%--
  Created by IntelliJ IDEA.
  User: guanxiaokai
  Date: 2021/7/4
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    pageContext.setAttribute("name1","小凯1");
    request.setAttribute("name2","小凯2");
    session.setAttribute("name3","小凯3");
    application.setAttribute("name4","小凯4");
%>
<%
    String  name1 = (String) pageContext.findAttribute("name1");
    String  name2 = (String) pageContext.findAttribute("name2");
    String  name3 = (String) pageContext.findAttribute("name3");
    String  name4 = (String) pageContext.findAttribute("name4");
    String  name5 = (String) pageContext.findAttribute("name5");
%>
<%=name1%>
<%=name2%>
<%=name3%>
<%=name4%>
<%=name5%>
</body>
</html>
