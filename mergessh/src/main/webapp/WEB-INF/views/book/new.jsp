<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加书籍</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <div class="label-primary">
        <h2>Spring-Hibernate-SpringMVC</h2>
    </div>
    <div class="panel">
        <form class="form-group " method="post">
            <div class="form-group">
                <label>书名</label>
                <input type="text" class="form-control" name="title" placeholder="输入书名">
            </div>
            <div class="form-group">
                <label>作者</label>
                <input type="text" class="form-control" name="author" placeholder="输入作者">
            </div>
            <div class="form-group">
                <label>价格</label>
                <input type="text" class="form-control" name="price" placeholder="输入价格">
            </div>
            <div class="form-group">
                <label>数量</label>
                <input type="text" class="form-control" name="total" placeholder="输入数量">
            </div>
            <div class="form-group">
                <label>书号</label>
                <input type="text" class="form-control" name="code" placeholder="输入书号">
            </div>
            <div class="form-group">
                <label>类型</label>
                <select name="type.id" id="type" class="form-control">
                    <option value="">请选择类型</option>
                    <c:forEach items="${typeList}" var="type">
                        <option value="${type.id}">${type.typename}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label>出版社</label>
                <select name="publisher.id" id="press" class="form-control">
                    <option value="">请选择出版社</option>
                    <c:forEach items="${publisherList}" var="publisher">
                        <option value="${publisher.id}">${publisher.press}</option>
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
