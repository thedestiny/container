<%--
  Created by IntelliJ IDEA.
  User: xieyue
  Date: 2016/7/7
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>文档管理</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/static/adminlte/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/static/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="/static/adminlte/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="/static/webuploader/webuploader.css">
    <style>
       #addNewDoc{
           width: 99px;
           height: 40px;
           margin-top: -10px;
       }
    </style>


</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/mainHeader.jsp" %>
    <jsp:include page="../include/leftSider.jsp">
        <jsp:param name="menu" value="document"/>
    </jsp:include>

    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                文档管理
                <small>你可以创建、下载文件和文件夹</small>
            </h1>
            <ol class="breadcrumb">
                <c:if test="${ empty crumbList}">
                    <li><a href="/document"><i class="fa fa-file-text"></i>文档管理</a></li>
                </c:if>

                <c:forEach var ="crumb" items="${crumbList}">
                    <c:choose >
                        <c:when test="${crumb.faid == 0}">
                            <li><a href="/document"><i class="fa fa-file-text"></i>文档管理</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a  href="/document?faid=${crumb.id}">${crumb.filename}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ol>
        </section>
        <section class="content">
            <div class="box">
                <div class="box-header with-border inline">
                    <p id="picker"><span class="text"><i class="fa fa-upload"></i>上传文件</span></p>
                    <button id="addNewDoc"  class="btn btn-success"><i class="fa fa-folder-o"></i>新文件夹</button>
                </div>
                <div class="box-body">
                    <table class=" table table-bordered ">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>名称</th>
                                <th>创建日期</th>
                                <th>类型</th>
                                <th>大小</th>
                            </tr>
                        </thead>
                        <tbody id="tbody">
                        <c:if test="${ empty documentList}">
                            <tr>
                                <td colspan="5"><h4>暂时没有数据</h4></td>
                            </tr>
                        </c:if>

                        <c:forEach var="doc" items="${documentList}">
                            <tr>
                                <c:choose>
                                    <c:when test="${doc.type}">
                                        <td><i class='fa fa-file-o'></i></td>
                                        <td><a href="/document/down/${doc.id}">${doc.filename}</a></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><i class='fa fa-folder-o'></i></td>
                                        <td><a href="/document?faid=${doc.id}">${doc.filename}</a></td>
                                    </c:otherwise>
                                </c:choose>

                                <td>${doc.createtime}</td>
                                <td><c:choose>
                                    <c:when test="${doc.type}">
                                        文件
                                    </c:when>
                                    <c:otherwise>
                                        文件夹
                                    </c:otherwise>
                                </c:choose></td>
                                <td>${doc.formatsize}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="box-footer">
                    Footer
                </div>
            </div>
        </section>
    </div>
</div>


<!-- jQuery 2.2.3 -->
<script src="/static/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/adminlte/bootstrap/js/bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="/static/adminlte/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="/static/adminlte/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="/static/adminlte/dist/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="/static/adminlte/dist/js/demo.js"></script>
<script src="/static/webuploader/webuploader.min.js"></script>

<script>
    $(function(){
        var $addNewDoc = $("#addNewDoc");
        var $tbody = $("#tbody");
        $addNewDoc.on("click",function(){
            $tbody.append("<tr><td colspan='5'>" +
                    "<i class='fa fa-folder-o'>" +
                    "</i><input type='text' value='新建文件夹' id='docname' autofocus >" +
                    "</td></tr>");
        });
        $tbody.delegate($("#docname"),"keyup",function(event){
            if(event.keyCode != 13){
                return;
            }
            console.log("execute...");
            $.post("/document/adddoc",{"docname":$("#docname").val(),"faid":${faid}})
                    .done(function(data){
                        console.log(data);
                        window.history.go(0);
                    })
                    .fail(function(){
                        alert("服务器异常");
                    });
        });

        var uploader = WebUploader.create({
            swf:"/static/webuploader/Uploader.swf",
            pick:"#picker",
            server:"/document/file/upload",
            fileValL:"file",
            formData:{"faid":"${faid}"},
            auto:true
        });

        uploader.on("startUpload",function(){
            $("#picker .text").html('<i class="fa fa-spinner fa-spin"></i> 上传中...');
        });
        uploader.on("uploadSuccess",function(file,data){
            console.log(data);
            console.log(arguments);
            if(data._raw == "success") {
                 window.history.go(0);
            }
        });
        uploader.on("uploadError",function(file){
            console.log("文件上传失败");
        });

        uploader.on("uploadComplete",function(){
            $("#picker .text").html('<i class="fa fa-upload"></i> 上传文件');
        });
    });
</script>



</body>







</html>

