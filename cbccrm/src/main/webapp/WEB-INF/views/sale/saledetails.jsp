<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>销售|交易详情</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/static/adminlte/bootstrap/css/bootstrap.min.css">

    <%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">--%>
    <link rel="stylesheet" href="/static/fonts/font-awesome.min.css">
    <%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">--%>

    <link rel="stylesheet" href="/static/adminlte/dist/css/AdminLTE.min.css">

    <link rel="stylesheet" href="/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="/static/webuploader/webuploader.css">
    <link rel="stylesheet" href="/static/adminlte/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" href="/static/adminlte/plugins/fullcalendar/fullcalendar.min.css">
    <link rel="stylesheet" href="/static/adminlte/plugins/fullcalendar/fullcalendar.print.css" media="print">
    <link rel="stylesheet" href="/static/adminlte/plugins/colorpicker/bootstrap-colorpicker.css">

    <style>
        #upload {
            width: 50px;
            height: 25px;
            overflow: hidden;
        }

        #upload .webuploader-pick{
            background-color: #00bbfa;
            border: 1px solid #999;
            margin-top: -9px;
            margin-left: -15px ;
        }
        .timeline>li>.timeline-item{
            box-shadow:none;
            -webkit-box-shadow:none;
        }
        .files li{
            padding: 5px;
        }
        #task {
            width: 402px;
            height: 238px;
            z-index: 999;
            /*attr("z-index",999)*/
        }
    </style>
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
                销售详情
                <small>it all starts here</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/sale"><i class="fa fa-dashboard"></i>销售总览</a></li>
                <li><a href="#">${saleRecord.salename}</a></li>
            </ol>
        </section>
        <section class="content">
            <%--销售总览--%>
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">${saleRecord.salename}</h3>
                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                                title="Collapse">
                            <i class="fa fa-minus"></i></button>
                    </div>
                    <span class="time"><i class="fa fa-clock-o"></i><span class="timeago" title="2016-06-12 12:29:26"></span></span>
                    <shiro:hasRole name="manager">
                        <a href="javascript:;" class="btn btn-danger btn-sm pull-right" rel="${saleRecord.id}"
                           style="margin-right: 40px" id="del"><i class="fa fa-trash"></i>删除</a>
                    </shiro:hasRole>
                </div>
                <div class="box-body">

                    <table class="table table-responsive table-bordered">
                        <tr>
                            <td>关联客户</td>
                            <td><a href="/custom/detail/${saleRecord.customerid}">${saleRecord.customer}</a></td>
                            <td>涉项金额</td>
                            <td><fmt:formatNumber value="${saleRecord.price}" type="currency"/></td>
                        </tr>
                        <tr>
                            <td>当前进度</td>
                            <td>${saleRecord.process}
                                <c:if test="${saleRecord.process != '完成交易'}">
                                    <small><a href="javascript:;" id="editProcess">修改进度</a></small>
                                </c:if>
                            </td>
                            <td>最后联系时间</td>
                            <td>${saleRecord.lasttime}</td>
                        </tr>
                        <tr>
                            <td><span class="time"><i class="fa fa-clock-o"></i><span class="timeago" title="${saleRecord.lasttime}"></span></span>
                            </td>
                        </tr>
                    </table>

                </div>
                <div class="box-footer">
                    进度：
                    <div class="progress">
                        <c:forEach var="saleLog" items="${saleLogList}">
                            <c:set var="flag" value="${saleLog.context}"/>
                            <c:choose>
                                <c:when test="${fn:contains(flag,'将当前进度修改为')}">
                                    <c:if test="${saleLog.type == '确认意向'}">
                                        <div class="progress-bar progress-bar-primary" style="width: 25%">
                                            <span>确认意向</span>
                                        </div>
                                    </c:if>
                                    <c:if test="${saleLog.type == '提供合同'}">
                                        <div class="progress-bar progress-bar-success" style="width: 25%">
                                            <span>提供合同</span>
                                        </div>
                                    </c:if>
                                    <c:if test="${saleLog.type == '完成交易'}">
                                        <div class="progress-bar progress-bar-warning" style="width: 25%">
                                            <span>完成交易</span>
                                        </div>
                                    </c:if>
                                    <c:if test="${saleLog.type == '交易搁置'}">
                                        <div class="progress-bar progress-bar-danger" style="width: 25%">
                                            <span>交易搁置</span>
                                        </div>
                                    </c:if>
                                </c:when>
                                <c:when test="${fn:contains(flag,'创建')}">
                                    <c:if test="${saleLog.type == '初次接触'}">
                                        <div class="progress-bar progress-bar-info" style="width: 25%">
                                            <span>初次接触</span>
                                        </div>
                                    </c:if>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </div>
                </div>

            </div>
            <div class="row">
                <div class="col-sm-8">
                    <%--备忘日志 collapsed-box --%>
                    <div class="box  box-success">
                        <div class="box-header with-border ">
                            <h1 class="box-title"><i class="fa fa-list"></i>跟进记录</h1>
                            <div class="box-tools pull-right">
                                <button id="logBtn" type="button" class="btn btn-box-tool" data-widget="collapse"
                                        data-toggle="tooltip"
                                        title="Collapse">
                                    <i class="fa fa-minus"></i>
                                </button>
                            </div>
                            <c:if test="${saleRecord.process != '完成交易'}">
                                <button type="button" class="btn btn-primary" id="addLog">新增记录</button>
                            </c:if>
                        </div>
                        <div class="box-body">
                            <ul class="timeline" id="timeline">
                            </ul>
                            <ul class="timeline">
                                <%--<c:forEach var="saleLog" items="${saleLogList}">--%>
                                    <%--<c:set var="flag" value="${saleLog.context}"/>--%>
                                    <%--<c:choose>--%>
                                        <%--<c:when test="${fn:contains(flag,'将当前进度修改为')}">--%>
                                            <%--<li>--%>
                                                <%--<i class='fa fa-history bg-yellow'></i>--%>
                                                <%--<div class='timeline-item'>--%>
                                                    <%--<span class='time'><i--%>
                                                            <%--class='fa fa-clock-o'></i>${saleLog.createtime}</span>--%>
                                                    <%--<h3 class='timeline-header no-border'>${saleLog.context}</h3>--%>
                                                <%--</div>--%>
                                            <%--</li>--%>
                                        <%--</c:when>--%>
                                        <%--<c:when test="${fn:contains(flag,'创建')}">--%>
                                            <%--<li>--%>
                                                <%--<i class='fa fa-history bg-yellow'></i>--%>
                                                <%--<div class='timeline-item'>--%>
                                                    <%--<span class='time'><i--%>
                                                            <%--class='fa fa-clock-o'></i>${saleLog.createtime}</span>--%>
                                                    <%--<h3 class='timeline-header no-border'>${saleLog.context}</h3>--%>
                                                <%--</div>--%>
                                            <%--</li>--%>
                                        <%--</c:when>--%>
                                        <%--<c:otherwise>--%>
                                            <%--<li>--%>
                                            <%--<i class='fa fa-commenting bg-aqua'></i>--%>
                                            <%--<div class='timeline-item'>--%>
                                            <%--<span class='time'><i class='fa fa-clock-o'></i>${saleLog.createtime}</span>--%>
                                            <%--<div class='timeline-body'><p>${saleLog.context}</p></div>--%>
                                            <%--</div></li>--%>
                                        <%--</c:otherwise>--%>
                                    <%--</c:choose>--%>
                                <%--</c:forEach>--%>
                                <%--<li><i class="fa fa-clock-o bg-gray"></i></li>--%>
                            </ul>
                        </div>
                        <div class="box-footer">
                            Footer
                        </div>
                    </div>
                </div>
                <div class="col-sm-4">
                    <%--相关文件--%>
                    <div class="box box-primary">
                        <div class="box-header with-border ">
                            <h1 class="box-title"><i class="fa fa-file-o"></i>相关文件</h1>
                            <div class="btn btn-default btn-xs" id="upload"><i class="fa fa-upload"></i>上传</div>
                            <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse"
                                        data-toggle="tooltip"
                                        title="Collapse">
                                    <i class="fa fa-minus"></i></button>
                            </div>
                        </div>
                        <div class="box-body">
                            <ul style="list-style: none">
                                <c:if test="${ empty saleFileList}">
                                    <small>暂无文件</small>
                                </c:if>
                                <c:forEach var="saleFile" items="${saleFileList}" varStatus="ss">
                                    <li>${ss.count}.&nbsp;<a href="/sale/down/${saleFile.id}">${saleFile.filename}</a>
                                    </li>
                                </c:forEach>

                            </ul>


                        </div>
                        <div class="box-footer">
                            注意保护资料
                        </div>
                    </div>
                    <%--待办事项--%>
                    <div class="box  box-info">
                        <div class="box-header with-border">
                            <h1 class="box-title"><i class="fa fa-list-alt"></i>代办事项</h1>
                            <button class="btn btn-xs btn-primary" id="addTask">新增事项</button>
                            <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse"
                                        data-toggle="tooltip"
                                        title="Collapse">
                                    <i class="fa fa-minus"></i></button>
                            </div>
                        </div>
                        <div class="box-body">
                            <ul style="list-style: none">
                                <c:forEach items="${taskList}" var="task" varStatus="SS">
                                    <li>
                                        <i>${SS.count} .</i>
                                        <input type="checkbox" class="done" value="${task.id}">
                                        <span class="text">${task.title}</span>
                                        <a href="javascript:;" rel="${task.id}" class="del"><i class="fa fa-trash-o"></i></a>
                                    </li>
                                </c:forEach>
                            </ul>

                        </div>
                        <div class="box-footer">
                            注意保护资料安全
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>


    <%--交易日志--%>
    <div id="add" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">新增备忘录</h4>
                </div>
                <div class="modal-body">
                    <form id="addForm">
                        <input type="text" class="form-control hide" name="saleid"
                               value="${saleRecord.id}">
                        <div class="form-group">
                            <label class=" control-label">*备忘</label>
                            <div>
                                <input type="text" class="form-control" name="context"
                                       placeholder="请输入内容">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label">进展情况</label>
                            <select name="type" id="process" class="form-control">
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
    <%--修改进度--%>
    <div id="edit" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">修改进度</h4>
                </div>
                <div class="modal-body">
                    <form id="editForm">
                        <input type="text" class="form-control hide" name="id"
                               value="${saleRecord.id}">
                        <div class="form-group">
                            <label class="control-label">进展情况</label>
                            <select name="process" id="editpro" class="form-control">
                                <option value="">请选择进展情况</option>
                                <option value="初次接触" selected>初次接触</option>
                                <option value="确认意向" <c:if test="${saleRecord.process == '确认意向'}">selected</c:if>>确认意向
                                </option>
                                <option value="提供合同" <c:if test="${saleRecord.process == '提供合同'}">selected</c:if>>提供合同
                                </option>
                                <option value="完成交易" <c:if test="${saleRecord.process == '完成交易'}">selected</c:if>>完成交易
                                </option>
                                <option value="交易搁置" <c:if test="${saleRecord.process == '交易搁置'}">selected</c:if>>交易搁置
                                </option>
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

<div id="task" style="visibility: hidden">
    <section class="content">
        <div class="box box-primary" style="border: 1px solid cyan;position: absolute;">
            <div class="box-header with-border" style="background-color:#3c8dbc">
                <h3 class="box-title">新增待办事项</h3>
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
                <form id="taskForm">
                     <input type="text" name="saleid" value="${saleRecord.id}" hidden >
                    <div class="form-group">
                        <label>事项内容</label>
                        <input autofocus type="text" id="title" name="title" class="form-control"
                               placeholder="例如:晚上一起吃饭">
                    </div>
                    <div class="form-group">
                        <label>起始时间</label>
                        <div class="form-inline">
                            <input type="text" id="start_time" name="start" style="width: 120px;height: 25px" >
                            <label>-</label>
                            <input type="text" id="end_time" name="end" style="width: 120px;height: 25px" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label>提醒时间</label>
                        <div>
                            <select name="hour" style="width: 120px;height: 25px">
                                <option value=""></option>
                                <option value="0">0</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                                <option value="8">8</option>
                                <option value="9">9</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                                <option value="13">13</option>
                                <option value="14">14</option>
                                <option value="15">15</option>
                                <option value="16">16</option>
                                <option value="17">17</option>
                                <option value="18">18</option>
                                <option value="19">19</option>
                                <option value="20">20</option>
                                <option value="21">21</option>
                                <option value="22">22</option>
                                <option value="23">23</option>
                            </select>时
                            :
                            <select name="min" style="width: 120px;height: 25px">
                                <option value=""></option>
                                <option value="0">0</option>
                                <option value="5">5</option>
                                <option value="10">10</option>
                                <option value="15">15</option>
                                <option value="20">20</option>
                                <option value="25">25</option>
                                <option value="30">30</option>
                                <option value="35">35</option>
                                <option value="40">40</option>
                                <option value="45">45</option>
                                <option value="50">50</option>
                                <option value="55">55</option>
                            </select>分
                        </div>
                    </div>
                    <div class="form-group">
                        <label>显示颜色</label>
                        <input type="text" class="form-control" name="color" id="color" value="#61a5e8">
                    </div>
                </form>
            </div>
            <div class="box-footer">
                <button id="addBtn" class="btn btn-sm btn-primary pull-right">创建</button>
            </div>
        </div>
    </section>
</div>


<!-- jQuery 2.2.3 -->
<script src="/static/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/adminlte/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/js/jquery-ui.min.js"></script>
<script src="/static/adminlte/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="/static/adminlte/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="/static/adminlte/dist/js/app.min.js"></script>
<script src="/static/moment/moment.min.js"></script>
<script src="/static/adminlte/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/adminlte/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="/static/adminlte/dist/js/demo.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script src="/static/webuploader/webuploader.min.js"></script>
<script src="/static/timeago/timeago.js"></script>
<script src="/static/timeago/timeago_zh_cn.js"></script>
<script src="/static/adminlte/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>

<script>
    $(function () {

        //相对时间
        $(".timeago").timeago();

        $(document).ready(function () {
            $("time.timeago").timeago();
        });

        $("#addLog").click(function () {
            console.log("123456");
            // 重置表单
            $("#addForm")[0].reset();
            $("#process").val("${saleRecord.process}");
            // 弹出对话框
            $("#add").modal({
                "show": true,
                "bakdrop": "static",
                "keyboard": false
            });
        });

        // 保存备忘
        $("#saveAddBtn").click(function () {
            $("#addForm").submit();
        });

        // 验证
        $("#addForm").validate({
            errorClass: "text-danger",
            errorElement: "span",
            rules: {
                context: {
                    required: true
                },

                process: {
                    required: true
                }
            },
            messages: {
                context: {
                    required: "请输入内容"
                },

                process: {
                    required: "请选择进度"
                }
            },
            submitHandler: function (form) {
                $.post("/sale/log/add", $(form).serialize())
                        .done(function (data) {
                            $("#add").modal("hide");
                            //  dataTable.ajax.reload();
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

        $.get("/sale/log/" + ${saleRecord.id})
                .done(function (result) {
                    if (result) {
                        var $timeline = $("#timeline");
                        $timeline.html("");
                        $timeline.prepend("<li><i class='fa fa-clock-o bg-gray'></i></li>");
                        for (var i = 0; i < result.length; i++) {
                            var saleLog = result[i];
                            console.log(saleLog.context);
                            var content;
                            if (saleLog.context.match("将当前进度修改为") || saleLog.context.match("创建")) {
                                content = "<li>" +
                                        "<i class='fa fa-history bg-yellow'></i>" +
                                        "<div class='timeline-item'>" +
                                        "<span class='time' ><i class='fa fa-clock-o'></i>" + saleLog.createtime + "</span>" +
                                        "<h3 class='timeline-header no-border'>" + saleLog.context + "</h3>" +
                                        "</div></li>";
                            } else {
                                content = "<li>" +
                                        "<i class='fa fa-commenting bg-aqua'></i>" +
                                        "<div class='timeline-item'>" +
                                        "<span class='time'><i class='fa fa-clock-o'></i>" + saleLog.createtime + "</span>" +
                                        "<div class='timeline-body'><p>" + saleLog.context + "</p></div>" +
                                        "</div></li>";
                            }
                            $timeline.prepend(content);
                        }
                    }

                })
                .fail(function () {
                    alert("服务器异常");
                });

        // 加载销售记录
        $("#logBt").click(function () {
            $.get("/sale/log/" + ${saleRecord.id})
                    .done(function (result) {
                        if (result) {
                            var $timeline = $("#timeline");
                            $timeline.html("");
                            $timeline.prepend("<li><i class='fa fa-clock-o bg-gray'></i></li>");
                            for (var i = 0; i < result.length; i++) {
                                var saleLog = result[i];
                                console.log(saleLog.context);
                                var content;
                                if (saleLog.context.match("将当前进度修改为") || saleLog.context.match("创建")) {
                                    content = "<li>" +
                                            "<i class='fa fa-history bg-yellow'></i>" +
                                            "<div class='timeline-item'>" +
                                            "<span class='time'><i class='fa fa-clock-o'></i>" + saleLog.createtime + "</span>" +
                                            "<h3 class='timeline-header no-border'>" + saleLog.context + "</h3>" +
                                            "</div></li>";
                                } else {
                                    content = "<li>" +
                                            "<i class='fa fa-commenting bg-aqua'></i>" +
                                            "<div class='timeline-item'>" +
                                            "<span class='time'><i class='fa fa-clock-o'></i>" + saleLog.createtime + "</span>" +
                                            "<div class='timeline-body'><p>" + saleLog.context + "</p></div>" +
                                            "</div></li>";
                                }
                                $timeline.prepend(content);
                            }
                        }

                    })
                    .fail(function () {
                        alert("服务器异常");
                    })


        });

        // 弹出修改进度对话框
        $("#editProcess").click(function () {
            $("#editForm")[0].reset();
            // 弹出对话框
            $("#edit").modal({
                "show": true,
                "bakdrop": "static",
                "keyboard": false
            });
        });

        // 提交进度修改
        $("#saveeditBtn").click(function () {
            if ($("#editpro")) {
                $.post("/sale/edit", $("#editForm").serialize())
                        .done(function (data) {
                            $("#edit").modal("hide");
                            //  dataTable.ajax.reload();
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


        // 上传文档
        var uploader = WebUploader.create({
            swf: "/static/webuploader/Uploader.swf",
            pick: "#upload",
            server: "/sale/file/upload",
            fileValL: "file",
            formData: {"saleid": "${saleRecord.id}"},
            auto: true
        });
        uploader.on("startUpload", function () {
            $("#upload").after('<i class="fa fa-spinner fa-spin"></i> 上传中...');
        });
        uploader.on("uploadSuccess", function (file, data) {
            console.log(data);
            console.log(arguments);
            if (data._raw == "success") {
                window.history.go(0);
            }
        });
        uploader.on("uploadError", function (file) {
            console.log("文件上传失败");
        });
        uploader.on("uploadComplete", function () {
            $("#upload").html('<i class="fa fa-upload"></i>');
        });
    });

    // 删除销售记录
    $("#del").click(function () {
        alert("确认删除记录吗？");
        if (confirm("再次挽留一下")) {
            var $id = $(this).attr("rel");
            $.get("/sale/del/" + $id)
                    .done(function (data) {
                        $("#edit").modal("hide");
                        //  dataTable.ajax.reload();
                        $("#header").after("<div class='alert alert-success alert-dismissible'>" +
                                "<button type='button' class='close' data-dismiss='alert' >" +
                                "<span aria-hidden='true'>&times;</span>" +
                                "</button><strong>Tips:</strong>删除" + data + "</div>");
                    })
                    .fail(function () {
                        alert("服务器异常");
                    });
        }

    });
    // 背景颜色
    $("#color").colorpicker({
        color:'#33FFFF'
    });
    $("#start_time,#end_time").datepicker({
        format: 'yyyy-mm-dd',
        autoclose:true,
        language:'zh-CN',
        todayHighlight:true
    });

    // 添加待办事项弹出对话框
    $("#addTask").click(function(){
        var date = moment(new Date());
        $("#taskForm")[0].reset();
        $("#start_time").val(date.format("YYYY-MM-DD"));
        $("#end_time").val(date.format("YYYY-MM-DD"));
        $("#task").css({"visibility": "visible"});
        $("#task").css({"position": "absolute", "left": 500  + "px", "top": 200  + "px"});
    });

    // 保存事项
    $("#addBtn").click(function(){
        // 事项不为空
        if(!$("#title").val()){
            $("#title").focus();
            return;
        }
        if(moment($("#start_time").val()).isAfter(moment($("#end_time").val()))){
            alert("结束时间需要大于开始时间");
            return;
        }
        $.post("/sale/detail/task",$("#taskForm").serialize())
                .done(function(result){
                    if(result.state == 'success'){
                        // 隐藏窗口
                        $("#task").css({"visibility": "hidden"});
                        // 显示结果
                        $("#header").after("<div class='alert alert-success alert-dismissible'>" +
                                "<button type='button' class='close' data-dismiss='alert' >" +
                                "<span aria-hidden='true'>&times;</span>" +
                                "</button><strong>Tips:</strong>新增" + result.state + "</div>");
                    }

                })
                .fail(function(){
                    alert("服务器异常");
                });
    });

    // 删除事项
    $(".del").click(function(){
        var $id = $(this).attr("rel");
        if(confirm("真的要删除该事项吗？")){
            $.get("/schedule/del/"+$id)
                    .done(function(data){
                        $calendar.fullCalendar('removeEvents',$id);
                        $("#edit").modal("hide");
                        // 显示结果
                        $("#header").after("<div class='alert alert-success alert-dismissible'>" +
                                "<button type='button' class='close' data-dismiss='alert' >" +
                                "<span aria-hidden='true'>&times;</span>" +
                                "</button><strong>Tips:</strong>删除" + data + "</div>");
                    })
                    .fail(function(){
                        alert("服务器异常");
                    });
        }
    });

    // 标记已完成
    $(".done").click(function(){
        var $id = $(this).val();
        if(!confirm("确定做完了吗？")){
            return;
        }
        $.get("/schedule/done/"+ $id)
                .done(function(data){
                    $("#edit").modal("hide");
                    _event.color = "#cccccc";
                    $calendar.fullCalendar('updateEvents',_event);
                    // 显示结果
                    $("#header").after("<div class='alert alert-success alert-dismissible'>" +
                            "<button type='button' class='close' data-dismiss='alert' >" +
                            "<span aria-hidden='true'>&times;</span>" +
                            "</button><strong>Tips:</strong>修改" + data + "</div>");
                })
                .fail(function(){
                    alert("服务器异常");
                });
    });



</script>


</body>
</html>

