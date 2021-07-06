<%--
  Created by IntelliJ IDEA.
  User: guanxiaokai
  Date: 2021/7/3
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<dev name="login-ui" style="text-align: center">
    <h2>登录</h2>
    <form action="${pageContext.request.contextPath}/login">
        用户名：<input type="text" name="username"><br/>
        密码：<input type="password" name="password"><br/>
        爱好：
        <input type="checkbox" name = "hobby" value="java">java
        <input type="checkbox" name = "hobby" value="linux">linux
        <input type="checkbox" name = "hobby" value="python">python <br/>
        <input type="submit" name="提交">
    </form>

</dev>
</body>
</html>
