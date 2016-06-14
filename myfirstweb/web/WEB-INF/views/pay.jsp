<%--
  Created by IntelliJ IDEA.
  User: xieyue
  Date: 2016/6/13
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>pay</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
</head>
<body>

<div class="container">

    <div class="form-horizontal">
        <form action="/pay" method="post">
            <input name="token" class="hidden sr-only" value="${requestScope.token}">
            <div class="form-group">
                <label for="money" class="control-label">金额</label>
                <input type="text" id="money" class="form-control" name="money">
            </div>
            <div class="form-group">
                <button class="btn btn-group-lg btn-primary">支付</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>
