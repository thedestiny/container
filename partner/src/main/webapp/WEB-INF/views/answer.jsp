<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xieyue
  Date: 2016/6/26
  Time: 0:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Answer</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
</head>
<body>
<div class="page-header" id="user">
    <h1>${user.username} ,欢迎回答</h1>
</div>
<div class="container">

    <div id="show">
        <div class="panel">
            <div>
                <div>
                    <a style="font-size: 20px">${issue.questioner}</a>&nbsp;&nbsp;于
                    <small>${issue.time}</small>
                    提问：
                </div>
                <div>
                    <a href="#" style="font-size: 20px">${issue.content}</a>
                </div>
            </div>
        </div>
    </div>
    <c:forEach var="answer" items="${answerList}">
        <div>
            <div>
                <a style="font-size: 18px">${answer.user}</a>&nbsp;&nbsp;于
                <small>${answer.time}</small>
                回答：
            </div>
            <div>
                <a href="#" style="font-size: 20px">${answer.content}</a>
            </div>
        </div>
    </c:forEach>
    <hr>
    <form class="form-inline row" id="queForm">
        <div class="form-group ">
            <label for="username">用户名</label>
            <div class="input-group ">
                <input type="text" class="form-control" id="username" name="username">
            </div>
        </div>
        <div class="form-group">
            <label for="answer">回答</label>
            <div class="input-group">
                <input type="text" class="form-control col-sm-6" id="answer" name="answer">
                <input type="text" class="form-control col-sm-6 hide" name="question" value="${issue.question}">
            </div>
        </div>
        <button class="btn btn-primary" id="btn" type="button">回答</button>
    </form>
</div>
<script src="../../js/jquery-1.11.3.min.js"></script>
<script src="../../js/handlebars-v4.0.5.js"></script>
<script src="../../js/bootstrap.min.js"></script>
<script>
    $(function () {
        var $btn = $("#btn");
        $btn.click(function () {
            $.ajax({
                url: "/issue/ans",
                type: 'post',
                data: $("#queForm").serialize(),
                timeout: 6000,
                beforeSend: function () {
                    $btn.text("提交中").attr("disabled", "disabled");
                },
                success: function (data) {
                    console.log(data);

                    $("#user").prepend("<h2>" + data + "</h2>");
                },
                error: function () {
                    alert("提交失败");
                },
                complete: function () {
                    console.log("execute....");
                    $btn.text("提交").removeAttr("disabled");
                }
            });
        });


    });

</script>
</body>
</html>
