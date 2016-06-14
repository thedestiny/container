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
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <%--<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">--%>
</head>
<body>

    <div class="container">
        <div class="form-horizontal">
            <c:choose>
                <c:when test="${requestScope.err == '1001'}">
                    <div class="alert alert-danger">
                        <p>用户名或密码错误</p>
                    </div>
                </c:when>
                <c:when test="${requestScope.err == '1002'}">
                    <div class="alert alert-danger">
                        <p>验证码错误</p>
                    </div>
                </c:when>
            </c:choose>


            <form action="/login" method="post">
                <div class="form-group">
                    <label for="count" class="control-label">账号</label>
                    <input type="text" id="count" class="form-control" name="count">
                </div>
                <div class="form-group">
                    <label for="pwd" class="control-label">密码</label>
                    <input type="password" id="pwd" class="form-control" name="pwd">
                </div>
                <div class="form-group">
                    <label for="pat" class="control-label">验证码</label>
                    <input type="text" id="pat" class="form-control" name="pat">
                    <a href="#" id="patchc"><img src="/patchca.png" alt="patchca" id="patch"></a>
                </div>
                <div class="form-group">
                    <button class="btn btn-group-lg btn-primary">提交</button>
                </div>
            </form>
        </div>
    </div>
    <script src="../js/jquery-2.2.3.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script>
        $(function(){
            $("#patchc").bind("click",function(){
                console.log("asdfgf");
                $("#patch").attr("src","/patchca.png?t=" + new Date().getTime());
            })

        })


    </script>
</body>
</html>
