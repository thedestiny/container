<%--
  Created by IntelliJ IDEA.
  User: xieyue
  Date: 2016/6/25
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="col-sm-6 col-sm-push-3">
        <h1>Welcome</h1>
        <form id="regForm" class="form">
            <div class=" form-group">
                <label for="user">账户</label>
                <input type="text" class="form-control " name="username" id="user">
            </div>
            <div class=" form-group">
                <label for="pwd">密码</label>
                <input type="password" class="form-control " name="password" id="pwd">
            </div>
            <div class=" form-group">
<<<<<<< HEAD
                <label for="email">email</label>
                <input type="email" class="form-control " name="email" id="email">
=======
                <label for="email">邮箱</label>
                <input type="text" class="form-control " name="email" id="email">
>>>>>>> open
            </div>
            <div class=" form-group">
                <button style="display: block" id="btn" type="button" class="btn btn-primary">注册</button>
                <img src="../../img/ajax-loader.gif" alt="waitting" style="display: none" id="img">
            </div>
        </form>
    </div>
</div>
<script src="../../js/jquery-2.2.3.min.js"></script>
<script src="../../js/jquery.validate.min.js"></script>
<script src="../../js/messages_zh.min.js"></script>
<script>
    $(function () {
        var $btn = $("#btn");
        var $img = $("#img");
        var $form = $("#regForm");
        $form.validate({
            rules: {
                username: {
                    required: true,
                    minlength: 2,
                    remote: "/register"
                },
                password: {
                    required: true,
                    minlength: 6
                },
                email: {
                    required: true,
                    minlength: 6

                }
            },
            messages: {
                username: {
                    required: "请输入账户名",
                    minlength: "最少{0}个",
                    remote: "该账户已存在"
                },
                password: {
                    required: "请输入密码",
                    minlength: "最少{0}个"
                },
                email: {
                    required: "请输入简介",
                    minlength: "最少{0}个"
                }
            },
            errorElement: "span",
            errorClass: "text-danger",
            submitHandler: function ($form) {
                $.ajax({
                    url: "/register",
                    type: "post",
                    data: $($form).serialize(),
                    timeout: 60000,
                    beforeSend: function () {
                        $btn.text("注册中").attr("disabled", "disabled");
                        $img.show();
                    },
                    success: function () {
                        alert("注册成功");
                    },
                    error: function () {
                        alert("注册失败");
                    },
                    complete: function () {
                        console.log("execute....");
                        $btn.text("注册").removeAttr("disabled");
                        $img.hide();
                    }
                });
            }
        });
        $btn.click(function () {
            $("#regForm").submit();
        });
    });
</script>
</body>
</html>
