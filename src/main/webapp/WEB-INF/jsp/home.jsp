<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
</head>
<body>
<h1>
    <h2 align="center">欢迎页面 首页
        <a href="${pageContext.request.contextPath}/userInfo?startIndex=0&endIndex=10"><h3>查看用戶信息</h3></a>
        <a href="${pageContext.request.contextPath}/roleInfo?startIndex=0&endIndex=10"><h3>查看角色信息</h3></a>
    </h2>
</h1>
</body>
</html>
