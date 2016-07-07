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
    <link rel="stylesheet" href="/static/css/font-awesome.min.css">
    <style>
        #search{
            width: 40px;
        }
        #group{
            margin-top: -65px;
        }
    </style>
</head>
<body>


<div class="container">
    <div class="page-header">
        <h1>图书馆</h1> <a href="#"><i class="fa fa-user"></i></a>
    </div>
    <c:if test="${not empty message}">
        <div class="alert alert-info alert-dismissible">
            <button class="close" data-dismiss="alert" type="button">
                <span aria-hidden="true">&times;</span>
            </button>
            <p class="lead"><strong>tips:</strong>${message}</p>
        </div>
    </c:if>

    <div class="well well-sm">
        <form class="form form-inline" method="get">
            <div class="form-group">
                <input type="text" name="title" id="title" class="form-control" placeholder="输入书籍名" value="${rtitle}">
            </div>
            <div class="form-group">
                <input type="text" name="author" id="author" class="form-control" placeholder="输入作者名" value="${rauthor}">
            </div>
            <div class="form-group">
                <input type="text" name="press" id="press" class="form-control" placeholder="输入出版社名" value="${rpress}">
            </div>
            <button class="btn btn-info">搜索</button>
            <button type="reset" id="reset" class="btn btn-default">重置</button>
            <a href="/books/add"  class="btn btn-success pull-right">添加书籍</a>
        </form>
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
                <c:if test="${empty page.bookList}">
                    <h3>没有找到相关书籍</h3>
                </c:if>
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
        <div class="form-inline" style="display: inline-block; vertical-align:middle">
            <ul class="pagination" id="page" style="display: inline-block"></ul>
            <div id="group" class="form-group">
                <input type="text" id="search" class="form-control">
                <button class="btn btn-success" id="btn">Go</button>
            </div>
        </div>
    </div>


</div>
<script src="/static/js/jquery-2.2.3.min.js"></script>
<script src="/static/js/jquery.twbsPagination.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script>
    $(function(){
        $("#page").twbsPagination({
            totalPages:${page.totalPages},
            visiblePages:6,
            first:'首页',
            prev:'上一页',
            next:'下一页',
            last:'末页',
            href:'?page={{number}}'+"&title="+
            encodeURIComponent('${rtitle}')+"&press="+
            encodeURIComponent('${rpress}')+"&author="+
            encodeURIComponent('${rauthor}')
        });
        // 修改
        $(".btn-primary").click(function(){
            var id = $(this).attr("rel");
            window.location.href="books/update/"+id;
        });
        //删除
        $(".btn-danger").click(function(){
            if(confirm("确认删除吗？")){
                var id = $(this).attr("rel");
                alert(id);
                window.location.href="books/del/"+id;
            }
        });
        // 重置
        $("#reset").click(function(){
            window.location.href="/books";
        });
        // 跳转指定页
        $("#btn").click(function(){
            window.location.href="/books?page="+$("#search").val()+"&title="+
                    encodeURIComponent('${rtitle}')+"&press="+
                    encodeURIComponent('${rpress}')+"&author="+
                    encodeURIComponent('${rauthor}')
        });
    });
</script>


</body>
</html>
