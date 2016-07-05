<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xieyue
  Date: 2016/7/5
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>

    <link rel="stylesheet" href="<c:url value="/static/css/bootstrap.min.css"/>">
</head>
<body>



<div class="container">
    <div class="page-header">
        <h1>图书馆</h1>
    </div>
    <div class="panel">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>#</th>
                <th>书号</th>
                <th>书名</th>
                <th>作者</th>
                <th>出版社</th>
                <th>状态</th>
                <th>借阅次数</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.bookList}" var="book" varStatus="num">
                <tr>
                    <td>${num.count}</td>
                    <td>${book.code}</td>
                    <td>${book.title}</td>
                    <td>${book.author}</td>
                    <td>${book.press}</td>
                    <td>${book.station}</td>
                    <td>${book.btime}</td>
                    <td>
                        <button rel="${book.id}" class="btn btn-primary" >修改</button>
                        <button rel="${book.id}" class="btn btn-danger"  >删除</button>
                    </td>
                </tr>
            </c:forEach>


            </tbody>




        </table>
    </div>


</div>
<script src="../../../static/js/jquery-2.2.3.min.js"></script>
<script src="../../../static/js/jquery.twbsPagination.min.js"></script>
<script>
    $(function(){





    });
</script>


</body>
</html>
