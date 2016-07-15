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
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/mainHeader.jsp" %>
    <jsp:include page="../include/leftSider.jsp">
        <jsp:param name="menu" value="notice"/>
    </jsp:include>

    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                Construction Bank Of China
                <small>CRM</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/notice"><i class="fa fa-list"></i>公告</a></li>
            </ol>
        </section>
        <section class="content">
            <div class="box">
                <div class="box-header with-border">
                    <h3>公告列表</h3>
                    <shiro:hasRole name="manager">
                        <div class="box-tools pull-right">
                            <a href="/notice/new"><i class="fa fa-pencil"></i>
                                <h3 class="box-title">发布公告</h3></a>
                        </div>
                    </shiro:hasRole>
                </div>
                <div class="box-body">
                    <table id="noticeTable" class="table table-responsive">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>标题</th>
                            <th>发布者</th>
                            <th>发布时间</th>
                        </tr>
                        </thead>
                        <tbody>
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


<script src="/static/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/static/adminlte/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/adminlte/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="/static/adminlte/plugins/fastclick/fastclick.js"></script>
<script src="/static/adminlte/dist/js/app.min.js"></script>
<script src="/static/adminlte/dist/js/demo.js"></script>
<script src="/static/datatable/js/jquery.dataTables.min.js"></script>
<script src="/static/datatable/js/dataTables.bootstrap.min.js"></script>

<script>
    $(function () {
        var dataTable = $("#noticeTable").DataTable({
            searching: true,
            "autoWidth": false,
            serverSide: true,
            ordering:false,
            ajax: {
                url: "/notice/load"
            },
            // ordering: false,
            columns: [
                {"data":"id"},
                {"data": function(row){
                    return "<a href= '/notice/"+ row.id+"' >" + row.title+"</a>";
                }},
                {"data": "publisher"},
                {"data": function(row){
                    return row.publishtime.substr(0,19);
                }}
            ],
            "language": { //定义中文
                "search": "请输入查找关键词(标题/发布者/时间):",
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
            },
            "columnDefs": [ //定义列的特征
                {targets: [0], visible: false}
//                {targets: [2, 3], orderable: false}
            ]
        });
    });
</script>


</body>
</html>

