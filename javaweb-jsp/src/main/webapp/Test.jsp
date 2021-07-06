<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: guanxiaokai
  Date: 2021/7/3
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%=new Date()%>
<br/>
<%--jsp脚本片段--%>
<%
  int sum = 0;
  for (int i = 0; i <= 100; i++) {
    sum += i;
  }
  out.write("<h1>Sum="+sum+"</h1>");
%>
<br/>
<%--脚本片段的再实现--%>
<%
  int x = 10;
  out.print(x);
%>
<p>这是一个JSP文档</p>
<%
  int y = 2;
  out.print(y);
%>
<hr/>
<%--在代码中嵌入html元素--%>
<%for (int i = 0; i < 10; i++) {%>
<h1>HelloWorld <%=i%></h1>
<%}%>

<%--jsp声明--%>
<%!
  static {
    System.out.println("Loading Servlet!");
  }
  private int globalVar = 0;
  public void test(){
    System.out.println("进入了方法！");
  }
%>
</body>
</html>
