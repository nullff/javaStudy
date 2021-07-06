<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: guanxiaokai
  Date: 2021/7/5
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="score" value="85"/>

<c:choose>
    <c:when test="${score>=90}">
        成绩优秀
    </c:when>
    <c:when test="${score>=80}">
        成绩良好
    </c:when>
    <c:when test="${score>=70}">
        成绩一般
    </c:when>
    <c:when test="${score>=60}">
        成绩及格
    </c:when>
    <c:when test="${score<60}">
        继续努力
    </c:when>
</c:choose>
</body>
</html>
