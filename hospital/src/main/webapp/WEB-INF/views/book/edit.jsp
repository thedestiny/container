<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑书籍</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <div class="label-primary">
        <h2>Spring-Hibernate-SpringMVC</h2>
    </div>
    <div class="panel">
        <form class="form-group " method="post" action="/book/edit">
            <input type="text" class="form-control hide" name="id" value="${book.id}" >
            <div class="form-group">
                <label>书名</label>
                <input type="text" class="form-control" name="title" value="${book.title}">
            </div>
            <div class="form-group">
                <label>作者</label>
                <input type="text" class="form-control" name="author" value="${book.author}">
            </div>
            <div class="form-group">
                <label>价格</label>
                <input type="text" class="form-control" name="price" value="${book.price}">
            </div>
            <div class="form-group">
                <label>数量</label>
                <input type="text" class="form-control" name="total" value="${book.total}">
            </div>
            <div class="form-group">
                <label>书号</label>
                <input type="text" class="form-control" name="code" value="${book.code}">
            </div>
            <div class="form-group">
                <label>类型</label>
                <select name="type.id" id="type" class="form-control">
                    <option value="">请选择类型</option>
                    <c:forEach items="${typeList}" var="type">
                        <option value="${type.id}" <c:if test="${type.id == book.type.id}" >selected</c:if> >${type.typename}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label>出版社</label>
                <select name="publisher.id" id="press" class="form-control">
                    <option value="">请选择出版社</option>
                    <c:forEach items="${publisherList}" var="publisher">
                        <option value="${publisher.id}" <c:if test="${publisher.id == book.publisher.id}" >selected</c:if>>${publisher.press}</option>
                    </c:forEach>
                </select>
            </div>
            <button class="btn btn-primary">提交</button>
            <button class="btn btn-danger">重置</button>
        </form>
    </div>


</div>


</body>
</html>
