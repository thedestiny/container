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
    <title>统计表</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/static/adminlte/bootstrap/css/bootstrap.min.css">

    <%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">--%>
    <link rel="stylesheet" href="/static/fonts/font-awesome.min.css">
    <%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">--%>

    <link rel="stylesheet" href="/static/adminlte/dist/css/AdminLTE.min.css">

    <link rel="stylesheet" href="/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="/static/adminlte/plugins/pace/pace.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/mainHeader.jsp" %>
    <jsp:include page="../include/leftSider.jsp">
        <jsp:param name="menu" value="chart"/>
    </jsp:include>

    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                Blank page
                <small>it all starts here</small>
            </h1>
        </section>
        <section class="content">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">统计图</h3>
                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                                title="Collapse">
                            <i class="fa fa-minus"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    Start creating your amazing application!
                </div>
                <div class="box-footer">
                    请注意保护资料
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <div class="info-box">
                        <span class="info-box-icon bg-aqua">
                            <i class="fa fa-user"></i>
                        </span>
                        <div class="info-box-content">
                            <span class="info-box-text">
                                本月用户总量
                            </span>
                            <span class="info-box-text">
                                ${customNum}
                            </span>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="info-box">
                        <span class="info-box-icon bg-aqua">
                            <i class="fa fa-circle-o-notch"></i>
                        </span>
                        <div class="info-box-content">
                            <span class="info-box-text">
                                本月交易完成量
                            </span>
                            <span class="info-box-text">
                                ${dealSaleNum}
                            </span>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="info-box">
                        <span class="info-box-icon bg-aqua">
                            <i class="fa fa-dollar"></i>
                        </span>
                        <div class="info-box-content">
                            <span class="info-box-text">
                                本月销售额
                            </span>
                            <span class="info-box-text">
                               <fmt:formatNumber value="${saleTotal}" type="currency"/>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="content">
            <div class="row">
                <div class="col-sm-6">
                    <div class="box box-primary ">
                        <div class="box-header">
                            <h3 class="box-title">销售统计</h3>
                        </div>
                        <div class="box-body">
                            <div id="pieChart" style="width: 400px;height: 300px"></div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="box box-primary ">
                        <div class="box-header">
                            <h3 class="box-title">员工业绩图</h3>
                        </div>
                        <div class="box-body">
                            <div id="barChart" style="width: 400px;height: 300px"></div>
                        </div>
                    </div>
                </div>

                <div class="col-sm-6">
                    <div class="box box-primary ">
                        <div class="box-header">
                            <h3 class="box-title">客户订单图</h3>
                        </div>
                        <div class="box-body">
                            <div id="custChart" style="width: 400px;height: 300px"></div>
                        </div>
                    </div>
                </div>

            </div>
        </section>
    </div>

</div>


<script src="/static/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/static/adminlte/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/js/echarts.min.js"></script>
<script src="/static/adminlte/plugins/pace/pace.min.js"></script>
<script>
    $(function () {

        // 进展图
        var pieChart = echarts.init($("#pieChart")[0]);
        var pieOption = {
            tooltip: {
                trigger: 'item',
                formatter: '{a}<br/>{b} : {c} ({d}%)'
            },
            series: [{
                name: "销售详情",
                type: 'pie',
                data: [],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0,0,0,0.6)'
                    },
                    width : 400
                }
            }]
        };
        pieChart.setOption(pieOption);
        $.get("/chart/process/data", function (result) {
            if (result.state == 'success') {
                pieChart.setOption({
                    series: [{
                        data: result.data
                    }]
                });
            }
        });
        // 员工销售额
        var barChart = echarts.init($("#barChart")[0]);
        var barOption = {
            tooltip: {},
            xAxis: {
                data: {}
            },
            yAxis: {},
            series: [{
                name: "销售详情",
                type: 'bar',
                data: []
            }]
        };
        barChart.setOption(barOption);
        $.get("/chart/employee/data", function (result) {
            if (result.state == 'success') {
                barChart.setOption({
                    xAxis: [{
                        data: result.data.names
                    }],
                    series: [{
                        data: result.data.value
                    }]
                });
            }


        })
        // 客户订单额
        var custChart = echarts.init($("#custChart")[0]);
        var custOption = {
            tooltip: {},
            xAxis: {
                data: {}
            },
            yAxis: {},
            series: [{
                name: "客户订单",
                type: 'bar',
                data: []
            }]
        };
        custChart.setOption(custOption);
        $.get("/chart/custom/data", function (result) {
            if (result.state == 'success') {
                custChart.setOption({
                    xAxis: [{
                        data: result.data.names
                    }],
                    series: [{
                        data: result.data.value
                    }]
                });
            }


        })


    });
</script>


</body>
</html>

