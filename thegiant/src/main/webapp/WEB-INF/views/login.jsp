<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xieyue
  Date: 2016/7/4
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/static/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/webuploader/webuploader.css"/>">

    <style>
        #upForm {
            margin-top: 100px;
        }
    </style>
</head>
<body>
<div class="container">


    <div class="alert alert-info alert-dismissible">
        <button class="close" data-dismiss="alert" type="button">
            <span aria-hidden="true">&times;</span>
        </button>
        <p class="lead"><strong>tips:</strong></p>
    </div>

    <div id="load">选择文件</div>
    <ul id="list"></ul>
    <input id="put" type="text" class="form-control" name="image" placeholder="文件描述....">
    <button id="btn" class="btn btn-success">上传</button>


</div>

<script src="/static/js/jquery-2.2.3.min.js"></script>
<script src="/static/webuploader/webuploader.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>

<script>
    $(function () {
        var uploader = WebUploader.create({
            swf: "static/webuploader/Uploader.swf",
            server: "/login",
            pick: "#load",
            fileVal: "file",
            auto: false,
            formData: {"image": $("#put").val()}
        });
        $("#btn").bind("click", function () {
            uploader.upload();
        });
    });


</script>
</body>
</html>
