<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>销售详情</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/static/adminlte/bootstrap/css/bootstrap.min.css">

    <%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">--%>
    <link rel="stylesheet" href="/static/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="/static/adminlte/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="/static/daterangepicker/daterangepicker-bs3.css">
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
            </ol>
        </section>
        <section class="content">
            <div class="box">
                <div class="box-header with-border box-primary">
                    <h1 class="box-title">搜一搜</h1>
                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                                title="Collapse">
                            <i class="fa fa-minus"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <form class="form-inline" id="searchForm">
                        <input id="salename" type="text" placeholder="事项名称" class="form-control" name="salename">
                        <select id="proce" name="process" class="form-control">
                            <option selected value="">进度情况</option>
                            <option value="初次接触">初次接触</option>
                            <option value="确认意向">确认意向</option>
                            <option value="提供合同">提供合同</option>
                            <option value="完成交易">完成交易</option>
                            <option value="交易搁置">交易搁置</option>
                        </select>
                        <input type="text" id="rangepicker" class="form-control" placeholder="跟进时间">
                        <button id="searchBtn" type="button" class="btn btn-info"><i class="fa fa-search"></i>Search
                        </button>
                    </form>
                </div>
            </div>
            <div class="box">
                <div class="box-header with-border box-success">
                    <h1 class="box-title">销售列表</h1>
                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                                title="Collapse">
                            <i class="fa fa-minus"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <button type="button" class="btn btn-primary pull-right" id="addBtn">新增销售</button>
                    <table class="table table-responsive table-striped table-bordered" id="showTable">
                        <thead>
                        <tr>
                            <th>#</th>
                            <shiro:hasRole name="manager">
                                <th>创建人</th>
                            </shiro:hasRole>
                            <th>交易事项</th>
                            <th>关联客户</th>
                            <th>进展情况</th>
                            <th>涉项金额</th>
                            <th>创建时间</th>
                            <th>最近联系</th>
                            <th>完成时间</th>
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
                    <div class="form-group">
                        <label class="control-label">进展情况</label>
                        <select name="process" id="process" class="form-control">
                            <option value="">请选择进展情况</option>
                            <option value="初次接触" selected>初次接触</option>
                            <option value="确认意向">确认意向</option>
                            <option value="提供合同">提供合同</option>
                            <option value="完成交易">完成交易</option>
                            <option value="交易搁置">交易搁置</option>
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
<script src="/static/js/jquery.validate.min.js"></script>
<script src="/static/moment/moment.min.js"></script>
<script src="/static/daterangepicker/daterangepicker.js"></script>

<script>
    $(function () {

        var begin = '';
        var end = '';
        // daterangepicker
        $("#rangepicker").daterangepicker({
            format: "YYYY-MM-DD",
            separator: " ",
            locale: {
                "applyLabel": "选择",
                "cancelLabel": "取消",
                "fromLabel": "从",
                "toLabel": "到",
                "customRangeLabel": "自定义",
                "weekLabel": "周",
                "dayOfWeek": [
                    "星期一",
                    "星期二",
                    "星期三",
                    "星期四",
                    "星期五",
                    "星期六",
                    "星期日"
                ],
                "monthNames": [
                    "一月",
                    "二月",
                    "三月",
                    "四月",
                    "五月",
                    "六月",
                    "七月",
                    "八月",
                    "九月",
                    "十月",
                    "葭月",
                    "腊月"
                ],
                "firstDay": 1
            },
            ranges: {
                '今天': [moment(), moment()],
                '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                '最近7天': [moment().subtract(6, 'days'), moment()],
                '最近30天': [moment().subtract(29, 'days'), moment()],
                '本月': [moment().startOf('month'), moment().endOf('month')],
                '上个月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
            }
        });

        $("#rangepicker").on('apply.daterangepicker', function (ev, picker) {
            console.log("start is " + picker.startDate.format('YYYY-MM-DD'));
            console.log("end is " + picker.endDate.format('YYYY-MM-DD'));
            begin = picker.startDate.format('YYYY-MM-DD');
            end = picker.endDate.format('YYYY-MM-DD');
        });


        // 展示销售信息
        var dataTable = $("#showTable").DataTable({
            searching: false,
            "autoWidth": false,
            serverSide: true,
            ordering: true,
            ajax: {
                url: "/sale/load",
                data: function (dataSource) {
                    dataSource.salename = $("#salename").val();
                    dataSource.proce = $("#proce").val();
                    dataSource.starts = begin;
                    dataSource.ends = end;
                }
            },
            columns: [
                {"data": "id"},
                <shiro:hasRole name="manager">
                {"data": "realname"},
                </shiro:hasRole>
                {
                    "data": function (row) {
                        return "<a href='/sale/detail/" + row.id + "'>" + row.salename + "</a>";
                    }
                },
                {
                    "data": function (row) {
                        return "<a href='/custom/detail/" + row.customerid + "'>" + row.customer + "</a>";
                    }
                },
                {
                    "data": function (row) {
                        if (row.process == '完成交易') {
                            return "<p class='label label-success' >" + row.process + "</p>";
                        } else if (row.process == '交易搁置') {
                            return "<p class='label label-danger' >" + row.process + "</p>";
                        } else if (row.process == '确认意向') {
                            return "<p class='label label-primary' >" + row.process + "</p>";
                        } else {
                            return "<p class='label label-default' >" + row.process + "</p>";
                        }
                    }
                },
                {
                    "data": function (row) {
                        console.log(arguments);
                        var dollar = "<fmt:formatNumber value='${data.row.price}' type='currency'/>";
                        console.log("dollar is " + dollar);
                        return "<p>￥" + row.price + "</p>";
                    }
                },
                {"data": "createtime"},
                {"data": "lasttime"},
                {"data": "successtime"}
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

        // 弹出新增销售模态框
        $("#addBtn").click(function () {
            $("#addForm")[0].reset();
            $.get("/custom/customers")
                    .done(function (data) {
                        var $select = $("#select");
                        $select.html("");
                        console.log(data);
                        for (var i = 0; i < data.length; i++) {
                            var custom = data[i];
                            var option = "<option value='" + custom.id + "'>" + custom.customer + "</option>";
                            $select.append(option);
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
                    number: true
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
                    number: "必须是数字"
                }
            },
            submitHandler: function (form) {
                $.post("/sale/add", $(form).serialize())
                        .done(function (data) {
                            $("#add").modal("hide");
                            dataTable.ajax.reload();
                            $("#header").after("<div class='alert alert-success alert-dismissible'>" +
                                    "<button type='button' class='close' data-dismiss='alert' >" +
                                    "<span aria-hidden='true'>&times;</span>" +
                                    "</button><strong>Tips:</strong>新增" + data + "</div>");
                        })
                        .fail(function () {
                            alert("服务器异常");
                        });
            }
        });


        // 搜索
        $("#searchBtn").click(function () {
            dataTable.ajax.reload();
        });

    });


</script>


</body>
</html>

