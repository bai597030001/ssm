<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
</head>

<body>
<h2>请注册账号</h2>
<form action="<c:url value="/home"/>" method="post">
    <input id="username" name="username" type="text" placeholder="用户名"/>
    <input id="password" name="password" type="password" placeholder="密码"/>
    <button type="submit" name="register" value="注册"></button>
</form>
</body>

</html>
