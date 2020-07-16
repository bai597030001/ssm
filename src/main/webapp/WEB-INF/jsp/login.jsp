<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>欢迎登陆</title>
</head>

<body>
<form id="formLogin" method="post">
    <input id="username" name="username" type="text" placeholder="用户名"/>
    <input id="password" name="password" type="password" placeholder="密码"/>
    <button type="button" name="login" value="登录"></button>
</form>
</body>
</html>

<script>
    function loginValid() {
        // var username = document.getElementById("username").value
        // var password = document.getElementById("password").value
        var username = formLogin.username.value;
        var password = formLogin.password.value;
        // alert(username + ": " + password);
        if (username === "admin" && password === "admin") {
            return true
        } else {
            return false
        }
    }
</script>
