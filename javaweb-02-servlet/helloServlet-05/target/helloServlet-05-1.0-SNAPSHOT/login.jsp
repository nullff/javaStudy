<%--
  Created by IntelliJ IDEA.
  User: guanxiaokai
  Date: 2021/7/3
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="get">
    用户名：<input type="text" name = "username"><br/>
    密 码：<input type="password" name = "password"><br/>
    <input type="submit" name="提交">
</form>
</body>
</html>
