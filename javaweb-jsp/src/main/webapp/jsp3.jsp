<%--
  Created by IntelliJ IDEA.
  User: guanxiaokai
  Date: 2021/7/4
  Time: 0:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        拼接JSP指令
    </title>
</head>
<body>
<%--<%@include file="common/header.jsp"%>--%>
<%--<h1>网页主体</h1>--%>
<%--<%@include file="common/footer.jsp"%>--%>
<%--<hr/>--%>

<%--jsp标签
    jsp:include:拼接页面，但本质上还是三个，避免对象冲突--%>
<jsp:include page="common/header.jsp"/>
<h1>网页主体</h1>
<jsp:include page="common/footer.jsp"/>
<hr/>

</body>
</html>
