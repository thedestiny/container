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
    <title>修改该密码</title>
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

    <div id="content" class="content-wrapper">

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
<script src="/static/js/jquery.validate.min.js"></script>
<script>

    $("#pwdForm").validate({
        errorElement: "span",
        errorClass: "text-danger",
        rules: {
            password: {
                required: true,
                remote: "/user/identify"
            },
            newpwd: {
                required: true,
                rangelength: [6, 18]
            },
            confirmpwd: {
                required: true,
                equalTo: "#newpwd"

            }
        },
        messages: {
            password: {
                required: "请输入旧密码",
                remote: "密码错误"
            },
            newpwd: {
                required: "请输入新密码",
                rangelength: "密码长度范围{0}到{0}"

            },
            confirmpwd: {
                required: "请确认新密码",
                equalTo: "两次输入不一致"
            }
        },
        submitHandler: function (form) {
            $.post("/user/password", {"password": $("#newpwd").val()})
                    .done(function(data){
                        console.log("修改成功！");
                        $("#content").prepend("<div class='alert alert-success alert-dismissible'>" +
                                "<button type='button' class='close' data-dismiss='alert' >" +
                                "<span aria-hidden='true'>&times;</span>" +
                                "</button><strong>Tips:</strong>state:" + data + "</div>");
                    })
                    .fail(function(){

                    });
        }


    });
    $("#confirmBtn").click(function () {
        $("#pwdForm").submit();
    });


</script>
</body>
</html>

