<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Title</title>

    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>

<body>
<div style="text-align:center">
    <div class="panel-heading text-center">
        <h2>角色列表</h2>
    </div>
    <div>
        <img src="http://www.w3cplus.com/sites/default/files/blogs/2013/1312/object-test.jpg" alt="Object-fit Example"/>
    </div>
    <div class="panel-body">
        <table class="table table-striped" border="1" width="50%" align="center" valign="middle">
            <thead>
            <tr align="center">
                <td>id</td>
                <td>角色</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${roleInfo}" var="v">
                <tr align="center">
                    <td>${v.id}</td>
                    <td>${v.name}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
<style type="text/css">
    body {
        background: url("../imgs/defaultUser.png") no-repeat center center;
        background-size: cover;
    }

    img {
        margin: 20px;
        text-align: center;
    }
</style>
