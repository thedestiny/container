<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <div class="panel panel-primary">
        <h2>Spring-Hibernate-SpringMVC</h2>
        <a href="/book/new" class="btn btn-primary pull-right">添加书籍</a>
    </div>
    <div class="well well-sm">
        <form method="get" class="form-inline">
            <input type="text" name="p" class="hide" value="${p}">
            <div class="form-group">
                <input type="text" placeholder="书籍名称" name="q_like_title" value="${q_like_title}" class="form-control">
            </div>
            <div class="form-group">
                <input type="text" placeholder="书籍名称 或作者" name="q_like_key" value="${q_like_key}" class="form-control">
            </div>
            <div class="form-group">
                <input type="text" placeholder="最低价格" name="q_ge_price" class="form-control" value="${q_ge_price}"> -
                <input type="text" placeholder="最高价格" name="q_le_price" class="form-control" value="${q_le_price}" >
            </div>
            <div class="form-group">
                <select name="q_eq_type.id" class="form-control" >
                    <option value="">请选择类型</option>
                    <c:forEach items="${typeList}" var="type">
                        <option value="${type.id}" <c:if test="${type.id == requestScope['q_eq_type.id']}" >selected</c:if> >${type.typename}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <select name="q_eq_publisher.id" class="form-control">
                    <option value="">请选择出版社</option>
                    <c:forEach items="${publisherList}" var="pub">
                        <option value="${pub.id}" ${pub.id == requestScope['q_eq_publisher.id'] ? 'selected' : ''} >${pub.press}</option>
                    </c:forEach>
                </select>
            </div>
            <button class="btn btn-info">搜索</button>
            <a href="/book" class="btn btn-primary">重置</a>
        </form>
    </div>

    <div class="box">
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>书号</th>
                <th>书名</th>
                <th>作者</th>
                <th>价格</th>
                <th>数量</th>
                <th>出版社</th>
                <th>类型</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="book" items="${page.items}">
                    <tr>
                        <td>${book.code}</td>
                        <td>${book.title}</td>
                        <td>${book.author}</td>
                        <td>${book.price}</td>
                        <td>${book.total}</td>
                        <td>${book.publisher.press}</td>
                        <td>${book.type.typename}</td>
                        <td>
                            <a class="btn btn-danger"  href="/book/${book.id}/del">删除</a>
                            <a class="btn btn-primary"  href="/book/${book.id}/edit">编辑</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <ul class="pagination pull-right" id="page"></ul>
    </div>
</div>

<script src="/static/js/jquery-2.2.3.min.js"></script>
<script src="/static/js/jquery.twbsPagination.min.js"></script>
<script>
    $(function(){

        $("#page").twbsPagination({
            totalPages:${page.totalPages},
            visiblePages:5,
            first:'首页',
            prev:'上一页',
            next:'下一页',
            last:'末页',
            href:'?p={{number}}'+"&q_like_title="+
            encodeURIComponent('${q_like_title}')+"&q_ge_price="+
            encodeURIComponent('${q_ge_price}')+"&q_le_price="+
            encodeURIComponent('${q_le_price}') +"&q_eq_publisher.id="+
            encodeURIComponent('${q_eq_publisher.id}') +"&q_eq_type.id="+
            encodeURIComponent('${q_eq_type.id}') +"&q_like_key="+
            encodeURIComponent('${q_like_key}')
        });



    });

</script>

</body>
</html>
