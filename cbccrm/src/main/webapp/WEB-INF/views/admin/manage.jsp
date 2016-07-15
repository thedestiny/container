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
    <%@include file="../include/leftSider.jsp" %>

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
                    <div class="well well-sm">
                        <form class="form form-inline" id="searchForm">
                            <div class="form-group">
                                <input type="text" name="username" id="username" class="form-control"
                                       placeholder="请输入昵称">
                            </div>
                            <div class="form-group">
                                <input type="text" name="realname" id="realname" class="form-control"
                                       placeholder="真实姓名">
                            </div>
                            <div class="form-group">
                                <select name="roleid" id="roleid" class="form-control">
                                    <option value="">请选择角色</option>
                                    <c:forEach items="${roleList}" var="role">
                                        <option value="${role.id}">${role.rolename}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button id="seaBtn" class="btn btn-info" type="button">搜索</button>
                            <button type="reset" id="reset" class="btn btn-default">重置</button>
                            <a id="addBtn" class="btn btn-success pull-right">添加员工</a>
                        </form>
                    </div>
                </div>
                <div class="box-footer">
                    <section class="content">
                        <div class="box box-primary">
                            <div class="box-header with-border" id="msg">
                                员工管理
                            </div>
                        </div>
                        <div class="box-body">
                            <table class="table table-bordered" id="manageTab" style="text-align: center">
                                <thead>
                                <tr>
                                    <th style="text-align: center">员工号</th>
                                    <th style="text-align: center">昵称</th>
                                    <th style="text-align: center">真实姓名</th>
                                    <th style="text-align: center">创建时间</th>
                                    <th style="text-align: center">角色</th>
                                    <th style="text-align: center">状态</th>
                                    <th style="text-align: center">微信号</th>
                                    <th style="text-align: center">操作</th>
                                </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </div>
                    </section>
                </div>

            </div>
        </section>

    </div>
</div>
<div id="add" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加员工</h4>
            </div>
            <div class="modal-body">
                <form id="addForm">
                    <div class="form-group">
                        <label class="control-label" for="username">员工姓名</label>
                        <div>
                            <input type="text" class="form-control" id="addusername" name="username" placeholder="员工姓名"
                                   autofocus>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class=" control-label" for="addpassword">密码</label>
                        <div class="">
                            <input type="text" class="form-control" id="addpassword" name="password"
                                   placeholder="请输入密码">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class=" control-label" for="realname">真实姓名</label>
                        <div class="">
                            <input type="text" class="form-control" id="addrealname" name="realname"
                                   placeholder="请输入真实姓名">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class=" control-label" for="addweixin">微信号</label>
                        <div class="">
                            <input type="text" class="form-control" id="addweixin" name="weixin" placeholder="请输入微信号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class=" control-label" for="addroleid">选择角色</label>
                        <select name="roleid" id="addroleid" class="form-control">
                            <option value="">请选择角色</option>
                            <c:forEach items="${roleList}" var="role">
                                <option value="${role.id}">${role.rolename}</option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button id="saveAddBtn" type="button" class="btn btn-primary">Save</button>
            </div>
        </div>
    </div>
</div>
<div id="edit" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑员工信息</h4>
            </div>
            <div class="modal-body">
                <form id="editForm">
                    <input type="text" class="form-control sr-only" id="editid" name="id">
                    <div class="form-group">
                        <label class="control-label" for="username">员工姓名</label>
                        <div>
                            <input type="text" class="form-control" id="editusername" name="username" placeholder="员工姓名"
                                   readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class=" control-label" for="realname">真实姓名</label>
                        <div class="">
                            <input type="text" class="form-control" id="editrealname" name="realname"
                                   placeholder="请输入真实姓名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class=" control-label" for="addweixin">微信号</label>
                        <div class="">
                            <input type="text" class="form-control" id="editweixin" name="weixin" placeholder="请输入微信号">
                        </div>
                    </div>
                    <div class="form-group">
                        <select name="roleid" class="form-control" value="" id="editroleid">
                            <option value="">请选择角色</option>
                            <c:forEach items="${roleList}" var="role">
                                <option value="${role.id}">${role.rolename}</option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button id="saveEditBtn" type="button" class="btn btn-primary">Save</button>
            </div>
        </div>
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
<script src="/static/js/jquery.validate.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="/static/adminlte/dist/js/demo.js"></script>
<script src="/static/datatable/js/jquery.dataTables.min.js"></script>
<script src="/static/datatable/js/dataTables.bootstrap.min.js"></script>
<script>
    $(function () {
        var dataTable = $("#manageTab").DataTable({
            searching: false,
            serverSide: true,
            ajax: {
                url: "/admin/manage/load",
                data: function (dataSource) {
                    dataSource.roleid = $("#roleid").val();
                    dataSource.username = $("#username").val();
                    dataSource.realname = $("#realname").val();
                }
            },
            // ordering: false,
            columns: [
                {"data": "id"},
                {"data": "username"},
                {"data": "realname"},
                {"data": "createtime"},
                {"data": "role.rolename"},
                {
                    "data": function (row) {
                        return row.enable ? "<label class='label-primary'>正常</label>" : "<label class='label-danger'>禁用</label>";
                    }
                },
                {"data": "weixin"},
                {
                    "data": function (row) {
                        if (row.enable) {
                            return "<td>" +
                                    "<button rel='" + row.id + "' class='btn btn-info'>编辑</button>" +
                                    "<button rel='" + row.id + "' class='btn btn-danger'>禁用</button>" +
                                    "<button rel='" + row.id + "' class='btn btn-default'>重置密码</button>" +
                                    "</td>";
                        } else {
                            return "<td>" +
                                    "<button rel='" + row.id + "' class='btn btn-info'>编辑</button>" +
                                    "<button rel='" + row.id + "' class='btn btn-danger'>激活</button>" +
                                    "<button rel='" + row.id + "' class='btn btn-default'>重置密码</button>" +
                                    "</td>";
                        }
                    }
                }
            ],
            "language": { //定义中文
                "search": "请输入新员工名称:",
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
                {targets: [0], visible: true},
                {targets: [2, 3], orderable: false}
            ]
        });

        //添加新员工 展示模态框
        $("#addBtn").click(function () {
            // 重置表单
            $("#addForm")[0].reset();
            // 展示模态框
            $("#add").modal({
                "show": true,
                backdrop: "static",
                keyboard: false
            });
        });

        // 提交新员工数据
        $("#saveAddBtn").click(function () {
            $("#addForm").submit();
        });

        // 添加新员工表单验证
        $("#addForm").validate({
            errorElement: "span",
            errorClass: "text-danger",
            rules: {
                username: {
                    required: true,
                    rangelength: [3, 10],
                    remote: "/admin/manage/identify"
                },
                realname: {
                    required: true
                }
            },
            messages: {
                username: {
                    required: "请输入用户名",
                    rangelength: "长度范围为{0}到{1}",
                    remote: "该用户名已经被占用"
                },
                realname: {
                    required: "请输入真实姓名"
                }
            },
            submitHandler: function (form) {
                $.post("/admin/manage/add", $(form).serialize())
                        .done(function (data) {
                            $("#add").modal("hide");
                            dataTable.ajax.reload();
                            $("#msg").prepend("<div class='alert alert-success alert-dismissible'>" +
                                    "<button type='button' class='close' data-dismiss='alert' >" +
                                    "<span aria-hidden='true'>&times;</span>" +
                                    "</button><strong>Tips:</strong>" + data + "</div>");
                        })
                        .fail(function (data) {
                            alert("添加失败")
                        })
                        .always(function () {
                        });
            }
        });

        // 冻结员工
        $(document).delegate(".btn-danger", "click", function () {
            var id = $(this).attr("rel");
            var content = $(this).html();
            if (confirm("确认" + content + "吗?")) {
                $.get("/admin/manage/block/" + id)
                        .done(function (data) {
                            if (data == "success") {
                                dataTable.ajax.reload();
                                $("#msg").prepend("<div class='alert alert-success alert-dismissible'>" +
                                        "<button type='button' class='close' data-dismiss='alert' >" +
                                        "<span aria-hidden='true'>&times;</span>" +
                                        "</button><strong>Tips:</strong>" + content + data + "</div>");
                            }
                        })
                        .fail(function () {
                            alert("请求服务器异常");
                        });
            }
        });

        // 重置密码
        $(document).delegate(".btn-default", "click", function () {
            var id = $(this).attr("rel");
            if (id == null) {
                return;
            }
            $.get("/admin/manage/resetpwd/"+id)
                    .done(function(data){
                        $("#msg").prepend("<div class='alert alert-success alert-dismissible'>" +
                                "<button type='button' class='close' data-dismiss='alert' >" +
                                "<span aria-hidden='true'>&times;</span>" +
                                "</button><strong>Tips:</strong>密码重置" + data + "</div>");
                    })
                    .fail(function(){
                        alert("请求服务器异常");
                    });

        });


        // 编辑员工信息
        $(document).delegate(".btn-info", "click", function () {
            var id = $(this).attr("rel");
            console.log(id);
            if (id == null) {
                return;
            }
            $.get("/admin/manage/edit/" + id)
                    .done(function (data) {
                        $("#editid").val(data.id);
                        $("#editusername").val(data.username);
                        $("#editrealname").val(data.realname);
                        $("#editweixin").val(data.weixin);
                        $("#editroleid").val(data.roleid);
                        $("#edit").modal({
                            show: true,
                            drapback: 'static',
                            keyboard: false
                        });
                    })
                    .fail(function () {
                        alert("请求服务器异常");
                    });

        });

        // 提交数据
        $("#saveEditBtn").click(function () {
            console.log(" exexute !");
            $("#editForm").submit();
        });

        // 添加验证
        $("#editForm").validate({
            errorElement: "span",
            errorClass: "text-danger",
            submitHandler: function (form) {
                $.post("/admin/manage/edit", $(form).serialize())
                        .done(function (data) {
                            $("#edit").modal("hide");
                            dataTable.ajax.reload();
                            $("#msg").prepend("<div class='alert alert-success alert-dismissible'>" +
                                    "<button type='button' class='close' data-dismiss='alert' >" +
                                    "<span aria-hidden='true'>&times;</span>" +
                                    "</button><strong>Tips:</strong>" + data + "</div>");
                            console.log(data);

                        })
                        .fail(function (data) {
                            alert("添加失败")
                        });
            }
        });

        // 搜索
        $("#seaBtn").click(function () {
            console.log(" execute...");
            dataTable.ajax.reload();
        });
    });


</script>
</body>
</html>

