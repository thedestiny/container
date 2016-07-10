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

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/static/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">

    <link rel="stylesheet" href="/static/adminlte/dist/css/AdminLTE.min.css">

    <link rel="stylesheet" href="/static/adminlte/dist/css/skins/_all-skins.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="include/mainHeader.jsp"%>
    <%@include file="include/leftSider.jsp"%>

    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                Blank page
                <small>it all starts here</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">Examples</a></li>
                <li class="active">Blank page</li>
            </ol>
        </section>
        <section class="content">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">Title</h3>
                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                                title="Collapse">
                            <i class="fa fa-minus"></i></button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip"
                                title="Remove">
                            <i class="fa fa-times"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    Start creating your amazing application!
                </div>
                <div class="box-footer">
                    Footer
                </div>

            </div>
        </section>
        <section class="content col-sm-8 col-sm-push-2">
            <div class="box box-primary">
                <div class="box-header with-border">
                    修改密码
                </div>
            </div>
            <div class="box-body">
                <form id="pwdForm" class="form" style="line-height: 30px">
                    <div class="form-group">
                        <label for="orignalpwd">原始密码</label>
                        <div>
                            <input id="orignalpwd" type="password" class="form-control" name="password" placeholder="请输入旧密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="newpwd">新密码</label>
                        <div>
                            <input id="newpwd" type="password" class="form-control" name="newpwd" placeholder="请输入新密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="confirmpwd">确认新密码</label>
                        <div>
                            <input id="confirmpwd" type="password" class="form-control" name="confirmpwd"
                                   placeholder="请再次确认新密码">
                        </div>
                    </div>
                    <div class="form-group pull-right">
                        <button id="confirmBtn" class="btn btn-success" type="button">确认修改</button>
                        <button class="btn btn-danger" type="reset">重新设置</button>
                    </div>


                </form>
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
</body>
</html>

