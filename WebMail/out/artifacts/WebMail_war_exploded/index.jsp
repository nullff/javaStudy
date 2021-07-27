
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>注册</title>
  </head>
  <body>
  <form action="${pageContext.request.contextPath}/RegisterServlet.do" method="post">
    用户名:<input type="text" name="username"> <br/>
    密 码: <input type="password" name="password"> <br/>
    邮 箱: <input type="text" name="email"> <br/>
    <input type="submit" value="注册"> | <input type="reset" value="重置">

  </form>
  </body>
</html>
