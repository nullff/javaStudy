
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入对应的taglib--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="coreif.jsp" method="get">
    <input type="text" name="username" value="${param.username}">
    <input type="submit" value="提交">
</form>


<c:if test="${param.username=='admin'}" var="isAdmin">
    <c:out value="管理员欢迎您！"/>
</c:if>
<%--自闭合标签--%>
<c:out value="${isAdmin}"/>
</body>
</html>
