<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登陆</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <style>
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #eee;
        }
        .regForm input[type="username"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }

        .regForm input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }</style>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-xs-3">

            <form id="regForm">
                <h2 style="color: red">用户登陆界面</h2>
                <div class="form-group">
                    <label>账号</label>
                    <input type="text" class="form-control" name="username">
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <input type="text" class="form-control" name="password">
                </div>
                <button type="button" id="subBtn" class="btn btn-primary">登陆</button>
            </form>
        </div>
    </div>
</div>


<script src="js/jquery-2.2.3.min.js"></script>
<script src="js/jquery.validate.min.js"></script>

<script>
    $(function () {
        $("#regForm").validate({
            errorElement: "span",
            errorClass: "text-danger",
            rules: {
                username: {
                    required: true,
                    minlength: 3
                },
                password: {
                    required: true,
                    rangelength: [6, 18]
                }
            },

            messages: {
                username: {
                    required: "请输入账号",
                    minlength: "最少输入三个字"
                },
                password: {
                    required: "请输入密码",
                    rangelength: "密码长度为6-18位"
                }
            },
            submitHandler: function (form) {
                var $btn = $("#subBtn");
                $.ajax({
                    url: "/login",
                    type: "post",
                    data: $(form).serialize(),
//                    beforeSend: function () {
//                        $btn.text("登陆中...").attr("disabled", "disabled");
//                    },
//                    success: function () {
//                        alert("登陆成功");
//                    },
//                    error: function () {
//                        alert("服务器忙，请稍后再试");
//                    },
//                    complete: function () {
//                        $btn.text("登陆").removeAttr("disabled");
//                    }
                });
            }

        });
        $("#subBtn").click(function () {
            $("#regForm").submit();
        });
    });

</script>
</body>
</html>
