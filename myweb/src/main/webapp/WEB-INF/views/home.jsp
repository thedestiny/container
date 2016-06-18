<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: xieyue
  Date: 2016/6/17
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Pagination</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <%--<link rel="stylesheet" href="../../css/bootstrap-responsive.min.css">--%>
    <style>
        #in {
            line-height: 30px;
            width: 40px;
        }
        #search{
            width: 600px;
        }


    </style>


</head>


<body>

<nav class="nav navbar-fixed-top navbar-inverse"></nav>
<div class="container">
    <%--<div class="jumbotron"></div>--%>
    <div class="panel">
        <div class="panel-heading">
            <div class="panel-title">
                <h2>The Hot Film For Download</h2>
            </div>
            <form class="form form-group-lg form-inline col-lg-10 col-lg-push-2" action="/home" method="get">
                <label for="search"></label>
                <input id="search" name = "search" type="text" class="text-center form-control"  placeholder="search for : film /director /rate "/>
                <button id="find" class="btn btn-lg btn-primary">Go!</button>
                <input id="temple" class="hidden"  value="${movie.search}">
            </form>
        </div>
        <div class="panel-body">
            <table class="table-responsive table table-bordered">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Film</th>
                    <th>Rate</th>
                    <th>Issue</th>
                    <th>Director</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="movie" items="${movie.items}" varStatus="S">
                    <tr>
                        <td>${S.count}</td>
                        <td>${movie.film}</td>
                        <td>${movie.rate}</td>
                        <td>${movie.issue}</td>
                        <td>${movie.director}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="panel-footer text-right">
            <ul class="pagination" id="page">
            </ul>
        </div>
        <div class="panel-footer text-right">
            <ul class="pagination pull-right">
                <%--相对路径会有背景色--%>
                <li><a href="/home">Home</a></li>
                <li class="active"><a href="/home?page=${movie.pageNum -1}">Previous</a></li>
                <li><a href="/home?page=${movie.pageNum +1}">Next</a></li>
                <li><a href="/home?page=${movie.totalPage +1}">End</a></li>
                <li>当前页${movie.pageNum}/共${movie.totalPage}</li>
                <li>跳转至<input id="in" type="text" name="num">页</li>
                <li>
                    <button id="btn" class="btn btn-primary">Confirm</button>
                </li>
            </ul>
        </div>
    </div>
</div>
<script src="../../js/jquery-2.2.3.min.js"></script>
<script src="../../js/jquery.twbsPagination.js"></script>
<script>
    $(function () {
        $("#page").twbsPagination({
                    totalPages:${movie.totalPage},
                    visiblePages: 8,
                    prev: '上一页',
                    href: '?page={{number}}&search=' +  $("#temple").val()
                }
        );
        console.log("123456");
        $("#btn").click(function () {
            var num = $("#in").val();
            location.href = "/home?page=" + num;
        });
    });


</script>

</body>
</html>
