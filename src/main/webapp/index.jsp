<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>注册/登录</title>

    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <script src="https://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
    <script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    <script>
        jQuery.validator.addMethod("checkImgFromat", function (value, element, param) {
            var filepath = value;
            //获得上传文件名
            var fileArr = filepath.split("\\");
            var fileTArr = fileArr[fileArr.length - 1].toLowerCase().split(".");
            //切割出后缀文件名
            var filetype = fileTArr[fileTArr.length - 1];
            // 判断文件类型是否包含在 checkImgFromat属性中
            return arguments[2].includes(filetype);
        }, "上传图片格式错误，请上传 JEPG/PNG/JPG 文件");

        $().ready(function () {
            // 在键盘按下并释放及提交后验证提交表单
            $("#register_form").validate({
                rules: {
                    username: {
                        required: true,
                        minlength: 2,
                        maxlength: 20
                    },
                    password: {
                        required: true,
                        minlength: 6
                    },
                    confirm_password: {
                        required: true,
                        minlength: 6,
                        equalTo: "#password"
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    uploadImg: {
                        required: true,
                        // accept: "JPEG|PNG|JPG"
                        checkImgFromat: "jepg,png,jpg"
                    }
                },
                messages: {
                    username: {
                        required: "请输入用户名",
                        minlength: "用户名必需由2-20位组成"
                    },
                    password: {
                        required: "请输入密码",
                        minlength: "密码长度不能小于 6 位"
                    },
                    confirm_password: {
                        required: "请输入密码",
                        minlength: "密码长度不能小于 6 位",
                        equalTo: "两次密码输入不一致"
                    },
                    email: "请输入一个正确的邮箱",
                    uploadImg: {
                        required: "必须上传头像",
                        checkImgFromat: "格式必须为jpg"
                    }
                }
            });

            $("#login_form").validate({
                rules: {
                    login_username: {
                        required: true,
                        minlength: 2,
                        maxlength: 20
                    },
                    passwd: {
                        required: true,
                        minlength: 6
                    }
                },
                messages: {
                    login_username: {
                        required: "请输入用户名",
                        minlength: "用户名必需由2-20位字母或数字组成"
                    },
                    passwd: {
                        required: "请输入密码",
                        minlength: "密码长度不能小于 6 位"
                    }
                }
            });
        });

        if (typeof FileReader == 'undefined') {
            document.getElementById("xmTanDiv").InnerHTML = "<h1>当前浏览器不支持FileReader接口</h1>";
            document.getElementById("xdaTanFileImg").setAttribute("disabled", "disabled");
        }

        // 头像预览选择图片，马上预览
        function xmTanUploadImg(obj) {
            var file = obj.files[0];
            console.log(obj);
            console.log(file);
            console.log("file.size = " + file.size);
            var reader = new FileReader();
            reader.onloadstart = function (e) {
                console.log("开始读取....");
            }
            reader.onprogress = function (e) {
                console.log("正在读取中....");
            }
            reader.onabort = function (e) {
                console.log("中断读取....");
            }
            reader.onerror = function (e) {
                console.log("读取异常....");
            }
            reader.onload = function (e) {
                console.log("成功读取....");
                var img = document.getElementById("avarimgs");
                img.src = e.target.result;
                //或者 img.src = this.result;  //e.target == this
            }
            reader.readAsDataURL(file)
        }
    </script>
    <style>
        .nav-tabs > li {
            width: 50%;
            text-align: center
        }

        .nav-tabs > li.active > a,
        .nav-tabs > li.active > a:focus,
        .nav-tabs > li.active > a:hover {
            color: #FFF !important;
            cursor: default;
            background-color: #2e6da4 !important;
            border: 1px solid #ddd !important;
            border-bottom-color: transparent;
        }

        .tab-pane {
            margin-top: 20px;
        }

        .requisiteTip {
            color: red;
        }

        .error {
            color: red;
        }

        .div-img {
            width: 100px;
            height: 100px;
            border: 3px solid #FFFFFF;
            float: contour;
            margin-right: 20px;
        }

        .div-img img {
            object-fit: contain;
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
<div class="container">
    <!--登录/注册 选项标签-->
    <ul id="myTab" class="nav nav-tabs">
        <li class="active"><a href="#login" data-toggle="tab">账号登录</a></li>
        <li><a href="#register" data-toggle="tab">账号注册</a></li>
    </ul>
    <!--登录/注册 内容-->
    <div id="myTabContent" class="tab-content">
        <!--登陆项-->
        <div id="login" class="tab-pane fade in active">
            <form role="form" id="login_form" class="form-horizontal" action="<c:url value="login"/>"
                  method="GET">
                <div class="form-group">
                    <label for="login_username" class="col-sm-2 control-label pull-left">&nbsp;<span
                            class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;用户名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" id="login_username" name="login_username"
                               placeholder="请输入用户名">
                    </div>
                </div>
                <div class="form-group">
                    <label for="passwd" class="col-sm-2 control-label">&nbsp;
                        <span class="glyphicon glyphicon-lock"></span>&nbsp;&nbsp;密码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                    <div class="col-md-10">
                        <input class="form-control" id="passwd" name="passwd" type="password" placeholder="请输入密码">
                    </div>
                </div>
                <div class="form-group">
                    <div class="">
                        <div class="checkbox pull-left" style="display:inline-block;margin-left:5%">
                            &nbsp;&nbsp;<label><input type="checkbox" id="remember">请记住我 </label>
                        </div>
                        <div class="checkbox pull-right" style="display:inline-block;margin-right:8%">
                            <a href="#">忘记密码</a>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <input type="submit" id="loginBtn" class="btn btn-primary btn-lg" value="登录"
                           style="width:92%;margin-left:4%">
                </div>
            </form>
        </div>
        <!--注册项-->
        <div id="register" class="tab-pane fade">
            <form role="form" id="register_form" class="form-horizontal" enctype="multipart/form-data"
                  action=
                  <c:url value="/register"/> method="POST">
                <!--用户名-->
                <div class="form-group">
                    <label for="username" class="col-sm-2 control-label pull-left">&nbsp;
                        <span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;用户名&nbsp;
                        <span class="requisiteTip">*</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名">
                    </div>
                </div>
                <!--密码-->
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label pull-left">&nbsp;
                        <span class="glyphicon glyphicon-lock"></span>&nbsp;&nbsp;密码&nbsp;
                        <span class="requisiteTip">*</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </label>
                    <div class="col-md-10">
                        <input class="form-control" id="password" name="password" type="password" placeholder="请输入密码 ">
                    </div>
                </div>
                <!--确认密码-->
                <div class="form-group">
                    <label for="confirm_password" class="col-sm-2 control-label pull-left">&nbsp;
                        <span class="glyphicon glyphicon-lock"></span>&nbsp;&nbsp;确认密码&nbsp;
                        <span class="requisiteTip">*</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </label>
                    <div class="col-md-10">
                        <input class="form-control" id="confirm_password" name="confirm_password"
                               type="password" placeholder="请再次输入密码 ">
                    </div>
                </div>
                <!--邮箱-->
                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label pull-left">&nbsp;
                        <span class="glyphicon glyphicon-exclamation-sign"></span>&nbsp;&nbsp;邮箱&nbsp;
                        <span class="requisiteTip">*</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" id="email" name="email" placeholder="请输入邮箱 ">
                    </div>
                </div>
                <!--头像上传-->
                <div align="center" class="form-group">
                    <label for="uploadImg" class="col-sm-2 control-label pull-left">&nbsp;
                        <span class="glyphicon glyphicon-exclamation-sign"></span>&nbsp;&nbsp;头像&nbsp;
                        <span class="requisiteTip">*</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </label>
                    <div class="col-md-10">
                        <input type="file" name="uploadImg" id="uploadImg" accept="image/*"
                               onchange="xmTanUploadImg(this)">
                        <div class="div-img">
                            <img src="${pageContext.request.contextPath}/imgs/defaultUser.png" id="avarimgs"
                                 class="img-circle img-thumbnail img-responsive" align="center">
                        </div>
                    </div>
                </div>
                <!--注册-->
                <div class="form-group ">
                    <input type="submit" id="registerBtn" class="btn btn-primary btn-lg"
                           style="width:92%;margin-left:4%" value="注册">
                </div>
            </form>
        </div>
    </div>
    <div style="height:200px "></div>
    <!--底部 版权所有-->
    <nav class="navbar navbar-default navbar-fixed-bottom " style="background-color:inherit ">
        <div style="text-align:center ">
            版权所有 <span class="glyphicon glyphicon-copyright-mark "></span> glodon
        </div>
    </nav>
</div>

</body>
</html>

<script type="text/javascript">

    function md5Encrypt() {
        var password = loginForm.password.value;
        var md5pwd = $.md5(password);
        loginForm.password.value = md5pwd
    }

    function loginValid() {
        // var username = document.getElementById("username").value
        // var password = document.getElementById("password").value
        var username = formLogin.username.value;
        var password = formLogin.password.value;
        if (username === "admin" && password === "admin") {
            return true
        } else {
            return false
        }
    }
</script>
