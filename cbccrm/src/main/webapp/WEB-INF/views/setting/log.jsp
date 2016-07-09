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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="/static/adminlte/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="/static/datatable/css/dataTables.bootstrap.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/mainHeader.jsp" %>
    <%@include file="../include/leftSider.jsp" %>

    <div class="content-wrapper">
        <section class="content col-sm-8 col-sm-push-2">
            <div class="box box-primary">
                <div class="box-header with-border">
                    登录日志
                </div>
            </div>
            <div class="box-body">
                <table class="table" id="logTab">
                    <thead>
                    <tr>
                        <th>登录时间</th>
                        <th>登录ip</th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                </table>
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
<script src="/static/datatable/js/jquery.dataTables.min.js"></script>
<script src="/static/datatable/js/dataTables.bootstrap.min.js"></script>
<script>
    $(function () {
        var dataTable = $("#logTab").DataTable({
            searching: false,
            serverSide: true,
            ajax: "/user/log/load",
            ordering: false,
            columns: [
                {"data": "logintime"},
                {"data": "loginip"}
            ],
            "language": { //定义中文
                "search": "请输入书籍名称:",
                "zeroRecords": "没有匹配的数据",
                "lengthMenu": "显示 _MENU_ 条数据",
                "info": "显示从 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
                "infoFiltered": "(从 _MAX_ 条数据中过滤得来)",
                "loadingRecords": "加载中...",
                "processing": "处理中...",
                "paginate": {
                    "first": "首页",
                    "last": "末页",
                    "next": "下一页",
                    "previous": "上一页"
                }
            }


        });


    });


</script>
</body>
</html>

