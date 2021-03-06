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

        .panel {
            position: absolute;
            top: 120px;
        }
    </style>
</head>
<body>

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
    <div class="form-group">
        <button class="btn btn-primary col-sm-3 col-sm-push-1" id="post">POST</button>
        <button class="btn btn-success col-sm-3 col-sm-push-2" id="get">GET</button>
        <button class="btn btn-success col-sm-3 col-sm-push-3" id="read">READ</button>
    </div>
    <div class="panel form-group">
        <ul id="show"></ul>
    </div>

</div>


<script src="../../js/ajaxtest.js"></script>
<script>

    //    document.getElementById("read").onclick = function () {
    //        console.log(123);
    //        var xmlHttp = Ajax.getxmlHttp();
    //        xmlHttp.open("get", "/read.xml", true);
    //        xmlHttp.onreadystatechange = function () {
    //            var state = xmlHttp.readyState;
    //            var status = xmlHttp.status;
    //            if (state == 4) {
    //                if (status == 200) {
    //                    document.getElementById("show").innerText = "";
    //                    var xmlDoc = xmlHttp.responseXML;
    //                    var array = xmlDoc.getElementsByTagName("user");
    //                    for (var i = 0; i < array.length; i++) {
    //                        var user = array[i];
    //                        var id = user.getAttribute("id");
    //                        var name = user.getElementsByTagName("name")[0].childNodes[0].nodeValue;
    //                        var age = user.getElementsByTagName("age")[0].childNodes[0].nodeValue;
    //                        var nation = user.getElementsByTagName("nation")[0].childNodes[0].nodeValue;
    //                        var hobby = user.getElementsByTagName("hobby")[0].childNodes[0].nodeValue;
    //                        console.log("the content is :" + id + name + age + nation + hobby);
    //                        show(id, name, age, nation, hobby);
    //                    }
    //                } else {
    //                    console.log("server is busy");
    //                }
    //            }
    //        };
    //        xmlHttp.send();
    //    };
    (function () {
        document.getElementById("read").onclick = function (){
            get("/read.xml", fun);
        };
        function fun (xmlDoc) {
            var array = xmlDoc.getElementsByTagName("user");
            for (var i = 0; i < array.length; i++) {
                var user = array[i];
                var id = user.getAttribute("id");
                var name = user.getElementsByTagName("name")[0].childNodes[0].nodeValue;
                var age = user.getElementsByTagName("age")[0].childNodes[0].nodeValue;
                var nation = user.getElementsByTagName("nation")[0].childNodes[0].nodeValue;
                var hobby = user.getElementsByTagName("hobby")[0].childNodes[0].nodeValue;
                console.log("the content is :" + id + name + age + nation + hobby);
                show(id, name, age, nation, hobby);
            }
        }
        function get(url, fun) {
            console.log("execute log!");
            var xmlHttp = Ajax.getxmlHttp();
            xmlHttp.open("get", url, true);
            xmlHttp.onreadystatechange = function () {
                var state = xmlHttp.readyState;
                var status = xmlHttp.status;
                if (state == 4) {
                    if (status == 200) {
                        document.getElementById("show").innerText = "";
                        var xmlDoc = xmlHttp.responseXML;
                        fun(xmlDoc);
                    } else {
                        console.log("server is busy");
                    }
                }
            };
            xmlHttp.send();
        }
    })();


    document.querySelector("#post").onclick = function () {
        // this 意义 指的是调用者
        var obj = {
            name: document.querySelector("#user").value,
            address: document.querySelector("#pwd").value,
            tel: 13032884462
        };
        Ajax.post("/loginajax", obj, function (result) {
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
        console.log(str);
        Ajax.get("/loginajax", obj, function (result) {
            alert(result);
        });
    };

    function show(id, name, age, nation, hobby) {
        var li = document.createElement("li");
        var idp = document.createElement("p");
        var nameh3 = document.createElement("h3");
        var agesmall = document.createElement("small");
        var nationp = document.createElement("p");
        var hobbyp = document.createElement("p");
        idp.innerHTML = id;
        nameh3.innerHTML = name;
        agesmall.innerHTML = age;
        nationp.innerHTML = nation;
        hobbyp.innerHTML = hobby;
        li.appendChild(idp);
        nameh3.appendChild(agesmall);
        li.appendChild(nameh3);
        li.appendChild(nationp);
        li.appendChild(hobbyp);
        var con = document.getElementById("show");
        con.setAttribute("class", "container");
        con.appendChild(li);
    }

</script>
</body>
</html>
