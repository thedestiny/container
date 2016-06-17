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
                    <c:forEach var="movie" items="${requestScope.movieList}" varStatus="S">
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
        <ul class="pagination pull-right">
            <%--相对路径会有背景色--%>
            <li><a href="/home">Home</a></li>
            <li class="active"><a href="/home?page=${requestScope.p -1}">Previous</a></li>
            <li><a href="/home?page=${requestScope.p +1}">Next</a></li>
            <li><a href="#">End</a></li>
        </ul>
    </div>
</div>
<script src="../../js/jquery-2.2.3.min.js"></script>
<script src="../../js/jquery.twbsPagination.js"></script>
<script>


</script>

</body>
</html>
