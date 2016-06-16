<%--
  Created by IntelliJ IDEA.
  User: xieyue
  Date: 2016/6/13
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>success</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
</head>

<style>
    #jumb {
        margin-top: 70px;
    }
</style>
<body>

<nav class="nav navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand">CBC</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="#">Home</a></li>
                <li class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">Query <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Account</a></li>
                        <li><a href="#">Operation</a></li>
                        <li><a href="#">Manage</a></li>
                        <li class="divider"><a href="#">Account</a></li>
                        <li><a href="#">Account</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="navbar-nav navbar-right">
                <li><a href="#" class="btn">${requestScope.user}</a></li>
                <li><a href="#" class="btn" id="time">now is : ${pageScope.name} </a></li>
            </ul>
        </div>

    </div>
</nav>

<div class="container" id="jumb">
    <div class="jumbotron ">
        <h1>login success!</h1>
    </div>
</div>



<script src="../../js/jquery-2.2.3.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
<script>
    $(function () {

        $("#time").bind("click",function(){
            var date = new Date();
            $(this).html(date.toDateString());
        })
    })

</script>
</body>
</html>
