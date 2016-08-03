
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>

<form action="/file/upload" method="post" enctype="multipart/form-data">
    <input type="text" name="name">
    <input type="file" name="files">
    <input type="file" name="files">
    <input type="file" name="files">
    <button>Upload</button>
</form>

<p>
    <a href="/file/download">点此下载文件</a>
</p>


</body>
</html>
