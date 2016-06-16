<%--
  Created by IntelliJ IDEA.
  User: xieyue
  Date: 2016/6/16
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<link rel="stylesheet" href="../../css/bootstrap.min.css">
<body>
<div class="container">
    <div class="col-sm-push-3 col-sm-4">
        <form action="/upload" method="post" enctype="multipart/form-data">
            <div class="form-group form-inline">
                <label for="desc">文件描述</label>
                <input type="text" id="desc" class="form-control" name="desc">
            </div>
            <div class="form-group form-inline">
                <label for="file">选择文件</label>
                <input type="file" id="file" class="form-control" name="file">
            </div>
            <div class="form-group">
                <button class="btn btn-primary btn-sm">上传</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
