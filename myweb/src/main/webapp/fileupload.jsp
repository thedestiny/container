<%--
  Created by IntelliJ IDEA.
  User: xieyue
  Date: 2016/6/24
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FileUPload</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="js/webuploader/webuploader.css">
</head>
<body>

<nav class="nav navbar-nav ">
    <h1>FileUpload</h1>
</nav>

<div class="container">
    <div id="load">选择文件</div>
    <button id="btn" class="btn btn-primary">上传文件</button>
</div>
<script src="js/jquery-2.2.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/webuploader/webuploader.min.js"></script>
<script>
    $(function(){
       var uploader = WebUploader.create({
          swf:"js/webuploader/Uploader.swf",
           server:"/upload",
           pick:"#load",
           fileVal:"file",
           auto:false
       });



    });


</script>


</body>
</html>
