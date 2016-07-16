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
        <jsp:param name="menu" value="custom"/>
    </jsp:include>

    <div class="content-wrapper">
        <section class="content-header" id="header">
            <h1>
                客户管理
                <small>中国建设银行</small>
            </h1>
        </section>
        <c:if test="${not empty message}">
            <div class="alert alert-success alert-dismissible">
                <button type="button" class="close" data-dismiss="alert">
                    <span aria-hidden="true">&times;</span>
                </button>
                <strong>${message}</strong>
            </div>
        </c:if>
        <section class="content">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">客户列表</h3>
                    <button id="addBtn" type="button" class="btn btn-success pull-right "><i class="fa fa-user">
                        &nbsp;</i>添加客户
                    </button>
                </div>
                <div class="box-body">
                    <table class="table table-bordered table-responsive" id="customTable">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>#</th>
                            <th>客户名称</th>
                            <th>联系电话</th>
                            <th>地址</th>
                            <th>微信号</th>
                            <th>创建时间</th>
                            <th>等级</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody id="tableBody"></tbody>
                    </table>
                </div>
                <div class="box-footer">
                    Footer
                </div>
            </div>
        </section>
    </div>

    <div id="add" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">新增客户</h4>
                </div>
                <div class="modal-body">
                    <form id="addForm">
                        <div class="form-group">
                            <label class="control-label">类型</label>
                            <div>
                                <label class="radio-inline">
                                    <input type="radio" name="type" value="person" id="radioPerson" checked>个人
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="type" value="company" id="radioCompany">公司
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="addcustomer">*客户名称</label>
                            <div>
                                <input type="text" class="form-control" id="addcustomer" name="customer"
                                       placeholder="客户全称"
                                       autofocus>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class=" control-label" for="addtel">*电话</label>
                            <div>
                                <input type="text" class="form-control" id="addtel" name="tel"
                                       placeholder="请输入电话">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class=" control-label" for="addemail">邮箱</label>
                            <div class="">
                                <input type="text" class="form-control" id="addemail" name="email" placeholder="邮箱">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class=" control-label" for="addaddress">地址</label>
                            <div class="">
                                <input type="text" class="form-control" id="addaddress" name="address"
                                       placeholder="请输入地址">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class=" control-label" for="addweixin">微信号</label>
                            <div class="">
                                <input type="text" class="form-control" id="addweixin" name="weixin"
                                       placeholder="请输入微信号">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class=" control-label">等级</label>
                            <select name="level" id="addlevel" class="form-control">
                                <option value=""></option>
                                <option value="☆" selected>☆</option>
                                <option value="☆☆">☆☆</option>
                                <option value="☆☆☆">☆☆☆</option>
                                <option value="☆☆☆☆">☆☆☆☆</option>
                                <option value="☆☆☆☆☆">☆☆☆☆☆</option>
                                <option value="☆☆☆☆☆☆">☆☆☆☆☆☆</option>
                            </select>
                        </div>
                        <div class="form-group" id="companyList">
                            <label>所属公司</label>
                            <select name="dependid" id="adddependid" class="form-control">
                                <option value=""></option>
                                <c:forEach var="company" items="${companyList}">
                                    <option value="${company.id}">${company.name}</option>
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
                    <h4 class="modal-title">编辑客户</h4>
                </div>
                <div class="modal-body">
                    <form id="editForm">
                        <input type="text" name="id" id="editid" class="hide">
                        <input type="text" name="type" id="edittype" class="hide">
                        <div class="form-group">
                            <label class="control-label" for="editcustomer">*客户名称</label>
                            <div>
                                <input type="text" class="form-control" id="editcustomer" name="customer"
                                       placeholder="客户全称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class=" control-label" for="edittel">*电话</label>
                            <div>
                                <input type="text" class="form-control" id="edittel" name="tel" placeholder="请输入电话">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class=" control-label" for="editemail">邮箱</label>
                            <div class="">
                                <input type="text" class="form-control" id="editemail" name="email" placeholder="邮箱">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class=" control-label" for="editaddress">地址</label>
                            <div class="">
                                <input type="text" class="form-control" id="editaddress" name="address"
                                       placeholder="请输入地址">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class=" control-label" for="editweixin">微信号</label>
                            <div class="">
                                <input type="text" class="form-control" id="editweixin" name="weixin"
                                       placeholder="请输入微信号">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class=" control-label">等级</label>
                            <select name="level" id="editlevel" class="form-control">
                                <option value=""></option>
                                <option value="☆" selected>☆</option>
                                <option value="☆☆">☆☆</option>
                                <option value="☆☆☆">☆☆☆</option>
                                <option value="☆☆☆☆">☆☆☆☆</option>
                                <option value="☆☆☆☆☆">☆☆☆☆☆</option>
                                <option value="☆☆☆☆☆☆">☆☆☆☆☆☆</option>
                            </select>
                        </div>
                        <div class="form-group" id="editcompanyList">
                            <label>所属公司</label>
                            <select name="dependid" id="editdependid" class="form-control">
                                <option value=""></option>
                            </select>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button id="saveeditBtn" type="button" class="btn btn-primary">Save</button>
                </div>
            </div>
        </div>
    </div>


</div>


<!-- jQuery 2.2.3 -->
<script src="/static/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></script>
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
<script src="/static/js/jquery.validate.min.js"></script>

<script>
    $(function () {
        var dataTable = $("#customTable").DataTable({
            searching: true,
            "autoWidth": false,
            serverSide: true,
            ordering: false,
            ajax: {
                url: "/custom/load"
            },
            // ordering: false,
            columns: [
                {"data": "id"},
                {
                    "data": function (row) {
                        if (row.type == 'company') {
                            if (row.userid) {
                                return "<i class='fa fa-bank'></i>    <i class='fa fa-lock'></i>"
                            } else {
                                return "<i class='fa fa-bank'></i>    <i class='fa fa-unlock'></i>"
                            }

                        } else {
                            if (row.userid) {
                                return "<i class='fa fa-user'></i>    <i class='fa fa-lock'></i>"
                            } else {
                                return "<i class='fa fa-user'></i>    <i class='fa fa-unlock'></i>"
                            }
                        }

                    }
                },
                {
                    "data": function (row) {
                        if (!row.company) {
                            return "<a href='/custom/detail/" + row.id + "'>" + row.customer + "</a>";
                        } else {
                            return "<a href='/custom/detail/" + row.id + "'>" + row.customer + "</a>" + "-<small><a href='/custom/detail/" + row.dependid + "'>" + row.company + "</a></small>";
                        }

                    }
                },
                {"data": "tel"},
                {"data": "address"},
                {"data": "weixin"},
                {"data": "createtime"},
                {"data": "level"},
                {
                    "data": function (row) {
                        <shiro:hasRole name="manager">
                        return "<a  class='btn btn-primary editLink' rel='" + row.id + "' href='javascript:;'>编辑</a><a  class='btn btn-danger delLink' rel='" + row.id + "' href='javascript:;'>删除</a>";
                        </shiro:hasRole>
                        <shiro:hasRole name="employee">
                        return "<a  class='btn btn-primary editLink' rel='" + row.id + "' href='javascript:;'>编辑</a>";
                        </shiro:hasRole>
                    }
                }
            ],
            "language": { //定义中文
                "search": "输入关键词(客户):",
                "zeroRecords": "没有匹配的数据",
                "lengthMenu": "显示 _MENU_ 条数据",
                "info": "显示从 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
                "infoFiltered": "(过滤了 _MAX_ 条数据)",
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

        // 弹出新增模态框
        $("#addBtn").click(function () {
            // 重置表单
            $("#addForm")[0].reset();
            // 获取公司列表
            $.get("/custom/company")
                    .done(function (data) {
                        var $adddependid = $("#adddependid");
                        $adddependid.html("");
                        console.log(data);
                        for (var i = 0; i < data.length; i++) {
                            var custom = data[i];
                            $adddependid.append("<option value='" + custom.id + "'>" + custom.customer + "</option>");
                        }
                    })
                    .fail(function () {
                        alert("服务器异常");
                    });
            // 展示模态框
            $("#add").modal({
                "show": true,
                backdrop: "static",
                keyboard: false
            });
        });
        $("#companyList").show();
        // 是否显示所属公司
        $("#radioCompany").click(function () {
            if ($(this)[0].checked) {
                $("#companyList").hide();
            }
        });
        $("#radioPerson").click(function () {
            if ($(this)[0].checked) {
                $("#companyList").show();
            }
        });

        // 提交新客户数据
        $("#saveAddBtn").click(function () {
            $("#addForm").submit();
        });

        // 新客户的验证
        $("#addForm").validate({
            errorElement: "span",
            errorClass: "text-danger",
            rules: {
                customer: {
                    required: true
                },
                tel: {
                    required: true
                }
            },
            messages: {
                customer: {
                    required: "请输入客户名称"
                },
                tel: {
                    required: "请输入电话"
                }
            },
            submitHandler: function (form) {
                $.post("custom/add", $(form).serialize())
                        .done(function (data) {
                            dataTable.ajax.reload();
                            $("#add").modal("hide");
                            $("#header").after("<div class='alert alert-success alert-dismissible'>" +
                                    "<button type='button' class='close' data-dismiss='alert' >" +
                                    "<span aria-hidden='true'>&times;</span>" +
                                    "</button><strong>Tips:</strong>新增" + data + "</div>");
                        })
                        .fail(function () {
                            alert("服务器异常")
                        });
            }
        });

        // 获取客户资料 填写表单并弹出模态框
        $("#tableBody").delegate(".editLink", "click", function () {
            var $id = $(this).attr("rel");
            // 异步获取公司列表及用户资料弹出模态框
            dataTable.ajax.reload();
            $.get("/custom/edit/" + $id)
                    .done(function (data) {
                        // 重置表单
                        $("#addForm")[0].reset();
                        var $editdependid = $("#editdependid");
                        $editdependid.html("");
                        if (data.state == "success") {
                            var list = data.companyList;
                            // 填写公司列表
                            for (var i = 0; i < list.length; i++) {
                                var custom = list[i];
                                $editdependid.append("<option value='" + custom.id + "'>" + custom.customer + "</option>");
                            }
                            // 给表单填值
                            var cust = data.custom;

                            // 判断是否是公司
                            if (cust.type == 'company') {
                                $("#editcompanyList").hide();
                            } else {
                                $("#editcompanyList").show();
                            }
                            $("#editid").val(cust.id);
                            $("#editcustomer").val(cust.customer);
                            $("#edittel").val(cust.tel);
                            $("#editemail").val(cust.email);
                            $("#editaddress").val(cust.address);
                            $("#editweixin").val(cust.weixin);
                            $("#editlevel").val(cust.level);
                            $("#editdependid").val(cust.dependid);
                            $("#edittype").val(cust.type);
                            // 展示模态框
                            $("#edit").modal({
                                "show": true,
                                backdrop: "static",
                                keyboard: false
                            });
                        } else {
                            alert(data.state);
                            alert(data.msg);
                        }

                    })
                    .fail(function () {
                        alert("服务器异常");
                    });
        });

        // 提交编辑资料
        $("#saveeditBtn").click(function () {
            $("#editForm").submit();
        });

        // 编辑资料验证
        $("#editForm").validate({
            errorElement: "span",
            errorClass: "text-danger",
            rules: {
                customer: {
                    required: true
                },
                tel: {
                    required: true
                }
            },
            messages: {
                customer: {
                    required: "请输入客户名称"
                },
                tel: {
                    required: "请输入电话"
                }
            },
            submitHandler: function (form) {
                $.post("custom/edit", $(form).serialize())
                        .done(function (data) {
                            dataTable.ajax.reload();
                            $("#edit").modal("hide");
                            $("#header").after("<div class='alert alert-success alert-dismissible'>" +
                                    "<button type='button' class='close' data-dismiss='alert' >" +
                                    "<span aria-hidden='true'>&times;</span>" +
                                    "</button><strong>Tips:</strong>编辑" + data + "</div>");
                        })
                        .fail(function () {
                            alert("服务器异常")
                        });
            }
        });


        // 删除用户资料
        $("#tableBody").delegate(".delLink", "click", function () {
            var $id = $(this).attr("rel");
            if (confirm("确认删除该客户,会删除该客户的关联信息，继续吗？")) {
                $.get("/custom/del/" + $id)
                        .done(function (data) {
                            dataTable.ajax.reload();
                            $("#header").after("<div class='alert alert-success alert-dismissible'>" +
                                    "<button type='button' class='close' data-dismiss='alert' >" +
                                    "<span aria-hidden='true'>&times;</span>" +
                                    "</button><strong>Tips:</strong>删除" + data + "</div>");
                        })
                        .fail(function () {
                            alert("服务器异常");
                        })
            }
        });


    });
</script>
</body>
</html>

