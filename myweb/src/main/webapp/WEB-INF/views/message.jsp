<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xieyue
  Date: 2016/6/24
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Online</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <nav class="nav navbar-nav">
        <div class="navbar">
            <h1>The new message</h1>
        </div>
    </nav>
    <div class="panel">
        <div class="panel-heading">
            <h3>Today is Germ VS UK</h3>
            <a type="button" class="btn btn-block btn-primary" id="btn" style="display: none">
                你有<span class="badge" id="num"></span>条新消息未读
            </a>
        </div>
        <div class="panel-body" id="msg">
            <c:forEach var="message" items="${messageList}">
                <div class="panel panel-default">
                    <div class="panel-heading">
                            ${message.author}
                    </div>
                    <div class="panel-body">
                            ${message.msg}
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<script src="../../js/jquery-2.2.3.min.js"></script>
<script type="qteng/template" id="temp">
    <div class="panel panel-default">
        <div class="panel-heading">
            {{author}}
        </div>
        <div class="panel-body">
            {{message}}
        </div>
    </div>


</script>
<script>
    $(function () {
        var idMax = ${idMax};
        var $msg = $("#msg");
        var newData = null;
        var $btn = $("#btn");
        var $num = $("#num");
        function update() {
            $.post("/message", {"id": idMax}, function (data) {
                //  data ? update() : data;
                newData = data;
                if (data.length > 0) {
                    console.log(data);
                    $btn.show();
                    $num.html(data.length);
                }
            });
        }
        $btn.bind("click", function () {
            for (var i = newData.length - 1; i >= 0; i--) {
                var item = newData[i];
                var temp = $("#temp").html();
                temp = temp.replace("{{author}}",item.author);
                temp = temp.replace("{{message}}",item.msg);
                $msg.prepend(temp);
            }
            idMax = newData[0].id;
            newData = null;
            $btn.fadeOut();
        });
        setInterval(update, 10000);
    });
</script>

</body>
</html>
