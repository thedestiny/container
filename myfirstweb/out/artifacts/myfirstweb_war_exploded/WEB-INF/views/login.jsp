<%--
  Created by IntelliJ IDEA.
  User: xieyue
  Date: 2016/6/13
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <%--<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">--%>
    <style>
        .panel {
            width: 50%;
            height: 50%;
            position: absolute;
            top: 10%;
            left: 25%;
        }

        .form-inline .form-control {
            width: 80%;
        }

        .form-group {
            text-align: center;
        }

        .control-label {
            letter-spacing: 4px;
            font-size: 20px;
        }

        .btn-block {
            width: 80%;
        }

        .page-header {
            text-align: center;
        }


    </style>
</head>
<body>

<div class="container">
    <div class="form-horizontal panel">
        <div class="page-header">
            <h2>welcome for login</h2>
        </div>
        <c:choose>
            <c:when test="${requestScope.err == '1001'}">
                <div class="alert alert-danger">
                    <button class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <p>用户名或密码错误</p>
                </div>
            </c:when>
            <c:when test="${requestScope.err == '1002'}">
                <button class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <div class="alert alert-danger">
                    <p>验证码错误</p>
                </div>
            </c:when>
            <c:when test="${requestScope.err == '1003'}">
                <div class="alert alert-danger">
                    <button class="close" data-dismiss="alert" aria-label="close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <p>账户不存在</p>
                </div>
            </c:when>
        </c:choose>
        <div class="panel-body">
            <form action="/login" method="post">
                <div class="form-group form-inline">
                    <label for="count" class="control-label">账号&nbsp;&nbsp;</label>
                    <input type="text" id="count" class="form-control" name="count">
                </div>
                <div class="form-group form-inline">
                    <label for="pwd" class="control-label">密码&nbsp;&nbsp;</label>
                    <input type="password" id="pwd" class="form-control" name="pwd">
                </div>

                <div class="form-group form-inline">
                    <label for="pat" class="control-label">验证码</label>
                    <input type="text" id="pat" class="form-control" name="pat">
                </div>
                <a href="#" id="patchc"><img src="/patchca.png" alt="patchca" id="patch"></a>
                <div class="form-group ">
                    <button class="btn btn-group-lg btn-primary btn-block " id="btn">提交</button>
                </div>
            </form>
        </div>

    </div>
</div>
<script src="../../js/jquery-2.2.3.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
<script src="../../js/core-min.js"></script>
<script src="../../js/md5-min.js"></script>
<script>
    $(function () {
        $("#patchc").bind("click", function () {
            console.log("asdfgf");
            $("#patch").attr("src", "/patchca.png?t=" + new Date().getTime());
        });

        $("#btn").bind("click", function () {
            var pwd = $("#pwd").val();
            var salt = "asdfa23nadsvdafdfg";
            $("#pwd").val(CryptoJS.MD5(pwd + salt).toString());
        })
    })

</script>
</body>
</html>
