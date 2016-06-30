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
    <title>Issue</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
</head>
<body>
<div class="page-header" id="head">
    <h1>${user.username}, 欢迎提问</h1>
</div>

<div class="container">
    <form class="form-inline row" id="queForm">
        <%--<div class="form-group ">--%>
        <%--<label for="username">用户名</label>--%>
        <%--<div class="input-group ">--%>
        <%--<input type="text" class="form-control" id="username" name="username">--%>
        <%--</div>--%>
        <%--</div>--%>
        <div class="form-group">
            <label for="question">问大家：</label>
            <div class="input-group">
                <input type="text" class="form-control col-sm-6" id="question" name="question">
            </div>
        </div>
        <button class="btn btn-primary" id="btn" type="button">提问</button>
    </form>
    <hr>
    <div id="show">
        <c:forEach var="issue" items="${issueList}">
            <div class="panel">
                <div class="btn-toolbar col-sm-3">
                    <a href="/issue/ans?question=${issue.question}" class="btn btn-md btn-primary btn-group">
                        <small>${issue.like}</small>
                        点赞</a>
                    <a href="/issue/ans?question=${issue.question}" class="btn btn-md  btn-default btn-group">
                        <small>${issue.answer}</small>
                        回答</a>
                    <a href="/issue/ans?question=${issue.question}" class="btn btn-md btn-success btn-group">
                        <small>${issue.skim}</small>
                        浏览</a>
                </div>
                <div>
                    <div>
                        <a style="font-size: 18px">${issue.questioner}</a>&nbsp;&nbsp;于
                        <small>${issue.time}</small>
                        提问：
                    </div>
                    <div>
                        <a href="/issue/ans?question=${issue.question}" style="font-size: 20px">${issue.content}</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script src="../../js/jquery-1.11.3.min.js"></script>
<script src="../../js/handlebars-v4.0.5.js"></script>
<script src="../../js/bootstrap.min.js"></script>
<script>
    $(function () {

        var $btn = $("#btn");
        $btn.click(function () {
            $.ajax({
                url: "/issue",
                type: "post",
                data: {
                    username:"${user.username}",
                    question:$("#question").val()
                },
                timeout: 6000,
                beforeSend: function () {
                    $btn.text("提交中").attr("disabled", "disabled");
                },
                success: function (data) {
                    console.log(data);
                    $("#head").prepend("<h1>" + data + "</h1>");
                },
                error: function (data) {
                    $("#head").prepend("<h1>" + data + "</h1>");
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
