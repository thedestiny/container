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
        <jsp:param name="menu" value="sale"/>
    </jsp:include>

    <div class="content-wrapper">
        <section class="content-header" id="header">
            <h1>
                销售列表
                <small>it all starts here</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/sale"><i class="fa fa-dashboard"></i>销售列表</a></li>
                <li><a href="#">Examples</a></li>
                <li class="active">Blank page</li>
            </ol>
        </section>
        <section class="content">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title"><shiro:principal property="realname"/></h3>
                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                                title="Collapse">
                            <i class="fa fa-minus"></i></button>
                    </div>
                    <button type="button" class="btn btn-primary pull-right" id="addBtn">新增销售</button>


                </div>
                <div class="box-body">
                    Start creating your amazing application!
                </div>
                <div class="box-footer">
                    Footer
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
                <h4 class="modal-title">新增销售</h4>
            </div>
            <div class="modal-body">
                <form id="addForm">
                    <div class="form-group">
                        <label class="control-label">*客户名称</label>
                        <select name="customerid" id="select" class="form-control">
                            <option value="">请选择客户</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class=" control-label">*项目</label>
                        <div>
                            <input type="text" class="form-control" name="salename"
                                   placeholder="请输入项目名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class=" control-label">*涉项金额</label>
                        <div class="">
                            <input type="text" class="form-control" name="price" placeholder="请输入金额">
                        </div>
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
<script src="/static/js/jquery.validate.min.js"></script>


<script>
    $(function () {
        // 弹出新增销售模态框
        $("#addBtn").click(function () {
            $("#addForm")[0].reset();
            $.get("/custom/customers")
                    .done(function (data) {
                        var $select = $("#select");
                        for (var i = 0; i < data.length; i++) {
                            var custom = data[i];
                            $select.append("<option value='" + custom.id + "'>" + custom.customer + "</option>")
                        }
                    })
                    .fail(function () {
                        alert("获取客户资料失败,服务器忙")
                    });
            $("#add").modal({
                "show": true,
                "backdrop": "static",
                "keyboard": false
            });
        });

        // 提交表单
        $("#saveAddBtn").click(function () {
            $("#addForm").submit();
        });

        // 表单验证
        $("#addForm").validate({
            errorClass: "text-danger",
            errorElement: "span",
            rules: {
                customerid: {
                    required: true
                },
                salename: {
                    required: true
                },
                price: {
                    required: true,
                    number : true
                }

            },
            messages: {
                customerid: {
                    required: "请选择客户"
                },
                salename: {
                    required: "请输入项目名称"
                },
                price: {
                    required: "请输入金额",
                    number : "必须是数字"
                }
            },
            submitHandler: function(form){
                $.post("/sale/add",$(form).serialize())
                        .done(function(data){
                            $("#add").modal("hide");
                            $("#header").after("<div class='alert alert-success alert-dismissible'>" +
                                    "<button type='button' class='close' data-dismiss='alert' >" +
                                    "<span aria-hidden='true'>&times;</span>" +
                                    "</button><strong>Tips:</strong>新增" + data + "</div>");
                        })
                        .fail(function(){
                            alert("服务器异常");
                        });
            }
        });


    });


</script>


</body>
</html>

