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
    <title>Home</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/static/adminlte/bootstrap/css/bootstrap.min.css">

    <%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">--%>
    <link rel="stylesheet" href="/static/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">

    <link rel="stylesheet" href="/static/adminlte/dist/css/AdminLTE.min.css">

    <link rel="stylesheet" href="/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="/static/simditor/styles/simditor.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/mainHeader.jsp"%>
    <jsp:include page="../include/leftSider.jsp">
        <jsp:param name="menu" value="notice"/>
    </jsp:include>

    <div class="content-wrapper">
        <section class="content-header">
            <section class="content-header">
                <h1>
                    Construction Bank Of China
                    <small>CRM</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="/notice"><i class="fa fa-list"></i>公告首页</a></li>
                </ol>
            </section>
        </section>
        <section class="content">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">欢迎你发布公告</h3>
                    <div class="box-tools pull-right"></div>
                </div>
                <div class="box-body">
                    <form id="newNoticeForm" method="post">
                        <div class="form-group">
                            <label for="title">标题</label>
                            <input type="text" class="form-control" id="title" name="title" autofocus>
                        </div>
                        <div class="form-group">
                            <label for="content">公告正文</label>
                            <textarea name="content" id="content" class="form-control" rows="18"></textarea>
                        </div>
                    </form>
                </div>
                <div class="box-footer">
                    <button id="saveBtn" class="btn btn-primary pull-right" type="button">发布</button>
                </div>
            </div>
        </section>
    </div>

</div>


<script src="/static/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/static/adminlte/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/adminlte/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="/static/adminlte/plugins/fastclick/fastclick.js"></script>
<script src="/static/adminlte/dist/js/app.min.js"></script>
<script src="/static/adminlte/dist/js/demo.js"></script>
<script src="/static/simditor/scripts/module.min.js"></script>
<script src="/static/simditor/scripts/hotkeys.min.js"></script>
<script src="/static/simditor/scripts/uploader.min.js"></script>
<script src="/static/simditor/scripts/simditor.min.js"></script>
<script>

    $(function(){

        var edit = new Simditor({
            textarea:$("#content"),
            placeholder:"请输入正文....",
            upload:{
                url:"/notice/image/upload",
                fileKey:"file"
            }
        });

        $("#saveBtn").click(function(){
            var $title = $("#title");
            var $content = $("#content");
            if(!$content.val()){
                $content.focus();
                return;
            }
            if(!$title.val()){
                $title.focus();
                return;
            }
            var $form =  $("#newNoticeForm");
            $form.submit();

        });



    });




</script>


</body>
</html>

