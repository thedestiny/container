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
<div class="page-header">
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
                    <a href="/issue/ans?question=${issue.question}" style="font-size: 20px">${issue.content}</a>
                </div>
            </div>
        </div>
    </div>
    <c:forEach var ="answer" items="${answerList}">

    </c:forEach>
    <hr>
    <form class="form-inline row" action="/issue" method="post">
        <div class="form-group ">
            <label for="username">用户名</label>
            <div class="input-group ">
                <input type="text" class="form-control" id="username" name="username">
            </div>
        </div>
        <div class="form-group">
            <label for="answer">回答</label>
            <div class="input-group">
                <input type="text"  class="form-control col-sm-6" id="answer" name="answer">
            </div>
        </div>
        <button class="btn btn-primary" id="btn" type="submit">回答</button>
    </form>
</div>
<script src="../../js/jquery-1.11.3.min.js"></script>
<script src="../../js/handlebars-v4.0.5.js"></script>
<script src="../../js/bootstrap.min.js"></script>
<script>
    $(function () {
        var $btn = $("#btn");
//        $btn.click(function () {
//            $("#queForm").submit();
//            $.post();
//            $.ajax({
//                url: "/issue",
//                type: 'post',
//                data: $("#queForm").serialize(),
//                timeout: 6000,
//                beforeSend: function () {
//                    $btn.text("提交中").attr("disabled", "disabled");
//                },
//                success: function () {
//                    alert("提交成功");
//                },
//                error: function () {
//                    alert("提交失败");
//                },
//                complete: function () {
//                    console.log("execute....");
//                    $btn.text("提交").removeAttr("disabled");
//                }
//            });
//        });


    });

</script>
</body>
</html>
