<%--
  Created by IntelliJ IDEA.
  User: guanxiaokai
  Date: 2021/7/4
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String  name1 = (String) pageContext.findAttribute("name1");
    String  name2 = (String) pageContext.findAttribute("name2");
    String  name3 = (String) pageContext.findAttribute("name3");
    String  name4 = (String) pageContext.findAttribute("name4");

%>
<%=name1%>
<%=name2%>
<%=name3%>
<%=name4%>

</body>
</html>
