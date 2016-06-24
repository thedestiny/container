<%--
  Created by IntelliJ IDEA.
  User: xieyue
  Date: 2016/6/23
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dictonary</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">

</head>
<body>
<div class="container">
    <input type="text" class="form-control col-sm-4 col-sm-2" id="search" placeholder="search...">
    <div class="panel">
        <ul id="show"></ul>
    </div>
</div>
<div class="container ">
    <input type="text" class="form-control col-sm-4 col-sm-2 bg-primary" id="key" placeholder="search...">
    <div class="panel">
        <ul id="detail"></ul>
    </div>
</div>

<script src="js/jquery-2.2.3.min.js"></script>
<script>
    $(function () {
        $("#search").on("keyup", function (event) {
            // doctype xml json jsonp
            var url = "http://fanyi.youdao.com/openapi.do?keyfrom=kaishengit&" +
                    "key=1587754017&type=data&doctype=jsonp&callback=?&version=1.1";
            var word = $("#search").val();
            if (event.keyCode != 13) {
                return;
            }
            var $show = $("#show");
            // 使用代理模式或者jsonp
            // getJSON 传入url,参数，callback函数，参数为json 这里必须使用getJSON
            $.getJSON(url, {"q": word}, function (json) {
                console.log(json);
                var array = json.basic.explains;
                for (var i = 0; i < array.length; i++) {
                    var item = array[i];
                    $show.append("<li>" + item + "</li>");
                }
            });

        });


    });

    $(function () {
        var $detail = $("#detail");
        var $key = $("#key");
        $key.bind("keyup", function (event) {
            if (event.keyCode != 13) {
                return;
            }
            var word = $key.val();
            console.log(word);
            $.get("/wordjson", {q: word}, function (json) {
                console.log(json);
                var array = json.basic.explains;
                for (var i = 0; i < array.length; i++) {
                    var item = array[i];
                    $detail.append("<li>" + item + "</li>");
                }
            });
        });


    });

</script>


</body>
</html>
