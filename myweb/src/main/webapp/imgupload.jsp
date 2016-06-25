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
    <title>ImageUPload</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="js/webuploader/webuploader.css">
    <style>
        ul {
            list-style: none;
        }
    </style>
</head>
<body>

<div>
    <h2 class="page-header">Ajax ImageUpload</h2>
</div>
<div class="container">
    <div id="load">选择image</div>
    <h2>img上传队列</h2>
    <ul id="list" class="list-unstyled list-inline"></ul>
    <button id="btn" class="btn btn-primary">上传文件</button>
</div>
<script src="js/jquery-2.2.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/webuploader/webuploader.min.js"></script>
<script type="mytemplete" id="progressBar">
    <div class="alert">
    <a type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </a>
    <div class="progress">
        <div id="bar" class="progress-bar progress-bar-info " style="width: 80%">
            <span id="num" class="text-info" ></span>
        </div>
    </div>

</div>


</script>

<script>
    $(function () {
        var uploader = WebUploader.create({
            swf: "js/webuploader/Uploader.swf",
            server: "/upload",
            pick: "#load",
            fileVal: "file",
            auto: false,
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });
        var $list = $("#list");
        uploader.on("fileQueued", function (file) {
            var html = '<li id = "' + file.id + '"><img class="img-thumbnail"></li>';
            console.log(html);
            $list.append(html);
            uploader.makeThumb(file, function (error, src) {
                if (error) {
                    return;
                }
                $("#" + file.id).find("img").attr("src", src);
            }, 100, 100);
        });
        uploader.on("uploadProgress", function (file, percentage) {
            percentage = parseInt(percentage * 100);
            var $li = $("#" + file.id);
            var n = $li.find(".progress").length;
            if (n > 0) {
                $li.find("#bar").css("width", percentage + "%");
                $li.find("#num").html(percentage + "%");
            } else {
                var temp = $("#progressBar").html();
                $li.append(temp);
            }
        });
        uploader.on("uploadSuccess", function (file) {
            $("#" + file.id).css("color", "cyan");
        });
        uploader.on("uploadError", function (file) {
            $("#" + file.id).css("color", "red");
        });
        uploader.on("uploadComplete", function (file) {
            console.log(file.name + "is execute !");
        });
        $("#btn").bind("click", function () {
            uploader.upload();
        });


    });


</script>


</body>
</html>
