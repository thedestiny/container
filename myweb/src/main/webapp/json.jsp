<%--
  Created by IntelliJ IDEA.
  User: xieyue
  Date: 2016/6/23
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Json</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
<div class="container col-sm-6 col-sm-push-3">
    <button id="json" class="btn btn-primary btn-sm">JSON</button>
    <div id="show" class="panel"></div>

</div>
<script src="js/jquery-2.2.3.min.js"></script>
<script src="js/ajaxtest.js"></script>
<script>
    $(function () {
        $("#json").click(function () {
            var show = $("#show");
            $.get("/json", function (json) {
                for (var i = 0; i < json.length; i++) {
                    var user = json[i];
                    // foreach 循环 json[user].id
                    var id = user.id;
                    var name = user.name;
                    var hobby = user.hobby;
                    var major = user.major;
                    show.append("<p>" + id + name + hobby + major+"</p>");
                }
            });
        });
    });
    document.getElementById("json").onclick = function () {
        var xmlHttp = Ajax.getxmlHttp();
        xmlHttp.open("get", "/json", true);
        xmlHttp.onreadystatechange = function () {
            var state = this.readyState;
            var status = this.status;
            if (state == 4) {
                if (status == 200) {
                    var doc = this.responseText;
                    var json = JSON.parse(doc);
                    //console.log(json);
                    for (var i = 0; i < json.length; i++) {
                        var user = json[i];
                        // foreach 循环 json[user].id
                        var id = user.id;
                        var name = user.name;
                        var hobby = user.hobby;
                        var major = user.major;
                        console.log(id + " : " + name + " : " + hobby + " : " + major);
                        var p = document.createElement("p");
                        var text = document.createTextNode(id + " : " + name + " : " + hobby + " : " + major);
                        p.appendChild(text);
                        document.querySelector("#show").appendChild(p);
                    }
                } else {
                    console.log("server encounter exception")
                }
            }
        };
        xmlHttp.send();
    }


</script>

</body>
</html>
