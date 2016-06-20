<%--
  Created by IntelliJ IDEA.
  User: xieyue
  Date: 2016/6/20
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="../../css/bootstrap-responsive.min.css">
    <link rel="stylesheet" href="../../css/bootstrap.min.css">

    <style>
        .container {
            margin-top: 20px;
            line-height: 25px;
        }
    </style>
</head>
<body>

<form action="/login" class="form-group" id="form" method="post" enctype="application/x-www-form-urlencoded">

</form>
<div class="container col-sm-4 col-sm-push-3">
    <div id="username" class="form-group form-inline ">
        <label for="user" class="control-label col-sm-4">Account</label>
        <input type="text" id="user" class="form-control col-sm-8 col-sm-pull-1" name="user"
               placeholder="input username"><span id="res"></span>
    </div>
    <div id="userpwd" class="form-group form-inline ">
        <label for="pwd" class="control-label col-sm-4">Password</label>
        <input type="text" id="pwd" class="form-control col-sm-8 col-sm-pull-1" name="pwd" placeholder="input password">
    </div>
    <button class="btn btn-primary col-sm-3 col-sm-push-1" id="post">POST</button>
    <button class="btn btn-success col-sm-3 col-sm-push-2" id="get">GET</button>
</div>


<script src="../../js/ajaxtest.js"></script>
<script>
    document.querySelector("#post").onclick = function () {
        // this 意义 指的是调用者
        var obj = {
            name: document.querySelector("#user").value,
            address: document.querySelector("#pwd").value,
            tel: 13032884462
        };
        Ajax.post("/loginajax", function (result) {
            alert(result);
        });
    };
    document.querySelector("#get").onclick = function () {
        // this 意义 指的是调用者
        var obj = {
            name: document.querySelector("#user").value,
            address: "China",
            tel: 13032884462
        };
        var str = " ";
        console.log(str);
        Ajax.get("/loginajax", obj, function (result) {
            alert(result);
        });
    };


</script>
</body>
</html>
