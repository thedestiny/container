<%--
  Created by IntelliJ IDEA.
  User: xieyue
  Date: 2016/6/21
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Word</title>

    <link rel="stylesheet" href="../../css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <input type="text" class="form-control col-sm-4 col-sm-2" id="btn" placeholder="search...">
</div>
<p id="trans" class="col-sm-4 col-sm-2"></p>
<script src="../../js/ajaxtest.js"></script>
<script src="../../js/jquery-2.2.3.min.js"></script>
<script>
//    (function () {
//        document.getElementById("btn").onkeyup = function (event) {
//            if (event.keyCode != 13) {
//                return;
//            }
//            var xmlHttp = Ajax.getxmlHttp();
//            xmlHttp.open("get", "/trans?q=" + this.value, true);
//            xmlHttp.onreadystatechange = function () {
//                var state = this.readyState;
//                var status = this.status;
//                if (state == 4) {
//                    if (status == 200) {
//                        var xmlDoc = this.responseXML;
//                        var basic = xmlDoc.getElementsByTagName("basic")[0];
//                        var explain = basic.getElementsByTagName("explains")[0];
//                        var ex = explain.getElementsByTagName("ex")[0].childNodes[0].nodeValue;
//                        console.log(ex);
//                        document.getElementById("trans").innerHTML = ex;
//                    } else {
//                        console.log("server is busy (encounter exception)");
//                    }
//                }
//            };
//            xmlHttp.send();
//        }
//    })();
    $(function () {
        $("#btn").on("keyup",function (event) {
            if (event.keyCode != 13) {
                return;
            }
            var key = this.value;
            $.get("trans", {q: key}, function (result) {
                var basic = $(result).find("basic");
                // console.log(basic);
                var explain = $(basic).find("explains");
                var ex = $(explain).find("ex");
//                for(var i = 0; i < ex.length;i++){
//
//                }
                console.log(ex.lenth);
                // console.log(explain);
                // console.log(ex);
                $("#trans").html(ex);
            });
        });
    });

</script>
</body>
</html>
