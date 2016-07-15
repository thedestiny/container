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
    <%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">--%>

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
                客户详情
                <small>这是${custom.customer}的详细资料</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/custom"><i class="fa fa-backward"></i>客户管理</a></li>
            </ol>
        </section>
        <section class="content">
            <div class="box">
                <div class="box-header with-border">
                    <h2 class="text-capitalize">
                        <c:choose>
                            <c:when test="${custom.type == 'company'}">
                                <i class="fa fa-bank" style="font-size: 30px"></i>
                            </c:when>
                            <c:when test="${custom.type == 'person'}">
                                <i class="fa fa-user" style="font-size: 30px"></i>
                            </c:when>
                        </c:choose>
                        ${custom.customer}</h2>
                    <c:choose>
                        <c:when test="${ not empty custom.userid}">
                            <button id="open" class="btn btn-danger" type="button">公开客户</button>
                            &nbsp;&nbsp;
                            <button id="move" class="btn btn-primary" type="button">转移客户</button>
                        </c:when>
                        <c:otherwise>
                            <button id="open" class="btn btn-info" type="button">私有客户</button>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="box-body">
                    <table class="table table-responsive table-bordered">
                        <tr>
                            <td style="width: 100px">联系电话</td>
                            <td style="width: 200px">${custom.tel}</td>
                            <td style="width: 100px">微信</td>
                            <td style="width: 200px">${custom.weixin}</td>
                            <td style="width: 100px">电子邮件</td>
                            <td>${custom.email}</td>
                        </tr>
                        <tr>
                            <td>等级</td>
                            <td style="color: #ff7400">${custom.level}</td>
                            <td>地址</td>
                            <td colspan="3">${custom.address}</td>
                        </tr>
                        <c:if test="${not empty custom.dependid}">
                            <tr>
                                <td>所属公司</td>
                                <td colspan="5"><a href="/custom/detail/${custom.dependid}">${custom.company}</a></td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty customList}">
                            <tr>
                                <td>关联客户</td>
                                <td colspan="5">
                                    <c:forEach items="${customList}" var="cust">
                                        <a href="/custom/detail/${cust.id}"> ${cust.customer} </a>
                                    </c:forEach>
                                </td>
                            </tr>
                        </c:if>
                    </table>
                </div>
                <div class="box-footer">
                    <div class="row">
                        <div class="col-md-8">
                            <div class="box box-info collapsed-box">
                                <div class="box-header with-border ">
                                    <h3 class="box-title"><i class="fa fa-list"></i> 项目列表</h3>
                                    <div class="box-tools">
                                        <button class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"><i
                                                class="fa fa-plus"></i></button>
                                    </div>
                                </div>
                                <div class="box-body">
                                    <h5>暂无项目</h5>
                                </div>
                            </div>
                        </div>
                        <%--col-md-8 end--%>
                        <div class="col-md-4">
                            <div class="box box-success collapsed-box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><i class="fa fa-qrcode"></i> 电子名片</h3>
                                    <div class="box-tools">
                                        <button class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"><i
                                                class="fa fa-plus"></i></button>
                                    </div>
                                </div>
                                <div class="box-body" style="text-align: center">
                                    <img src="/custom/qrcode/${custom.id}.png" alt="">
                                </div>
                            </div>
                            <div class="box box-primary collapsed-box">
                                <div class="box-header with-border">
                                    <h3 class="box-title"><i class="fa fa-calendar-check-o"></i> 代办事项</h3>
                                    <div class="box-tools">
                                        <button class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"><i
                                                class="fa fa-plus"></i></button>
                                    </div>
                                </div>
                                <div class="box-body">
                                    <h5>暂无代办事项</h5>
                                </div>
                            </div>
                        </div>
                        <%--col-md-4 end--%>
                    </div>
                    请注意保护资料
                </div>

            </div>
        </section>
    </div>
</div>

<div id="moveCustom" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true"></span>&times;
                </button>
                <h4 class="modal-title">转移客户</h4>
            </div>
            <div class="modal-body">
                <form id="moveto">
                    <input type="text" value="${custom.id}" name="id" class="hide">
                    <label>转移至:</label>
                    <select name="userid" id="select" class="form-control">
                        <option></option>
                    </select>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button id="save" type="button" class="btn btn-primary">Save</button>
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

<script>
    $(function () {

        // 公开客户/私有
        $("#open").click(function () {
            var $content = $(this).html();
            alert("你确定" + $content + "吗？");
            alert("请考虑清楚。。。");
            if ($content == "公开客户") {
                $.get("/custom/open/" +${custom.id})
                        .done(function (data) {
                            $("#header").after("<div class='alert alert-success alert-dismissible'>" +
                                    "<button type='button' class='close' data-dismiss='alert' >" +
                                    "<span aria-hidden='true'>&times;</span>" +
                                    "</button><strong>Tips:</strong>" + $content + data + "</div>");

                        })
                        .fail(function () {
                            alert("服务器异常");
                        });
            } else {
                console.log("私有");
                $.get("/custom/private/" +${custom.id})
                        .done(function (data) {
                            window.history.go(0);
                            $("#header").after("<div class='alert alert-success alert-dismissible'>" +
                                    "<button type='button' class='close' data-dismiss='alert' >" +
                                    "<span aria-hidden='true'>&times;</span>" +
                                    "</button><strong>Tips:</strong>" + $content + data + "</div>");
                        })
                        .fail(function () {
                            alert("服务器异常");
                        });
            }


        });
        // 转移客户
        $("#move").click(function () {
            $("#moveCustom").modal({
                "show": true,
                "backdrop": "static",
                "keyboard": false
            });
            var $select = $("#select");
            $.get("/custom/allusers")
                    .done(function (data) {
                        for (var i = 0; i < data.length; i++) {
                            var user = data[i];
                            $select.append("<option value='" + user.id + "'>" + user.realname + "</option>");
                        }
                    })
                    .fail(function () {
                        alert("服务器异常");
                    });
            $("#moveCustom").modal({
                "show": true,
                "backdrop": "static",
                "keyboard": false
            });
        });

        // 提交资料
        $("#save").click(function () {
            var $form = $("#moveto");
            $.post("/custom/move", $form.serialize())
                    .done(function (data) {
                        if (data == 'success') {
                            window.history.go(0);
                            $("#move").modal("hide");
                            window.location.href = "/custom";
                        }
                    })
                    .fail(function () {
                        alert("服务器异常");
                    });

        })


    });


</script>
</body>
</html>

