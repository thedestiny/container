<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <style>
       .form-inline .form-control {
            width: 800px;
        }
        #list{
            list-style: none;
        }
    </style>
</head>
<body>
<h1>Welcome for home!</h1>

<div class="container form-inline">
    <h1>RSS</h1>
    <input type="text" class="form-control  col-xs-5" placeholder="please input url" id="url">
    <button id="btn" class="btn btn-success btn-sm col-sm-push-3">Search!</button>
</div>
<ul id="list" class="col-sm-push-4" ></ul>

<script src="js/ajaxtest.js"></script>
<script>
    (function () {
        // 这里写方法名
        document.getElementById("btn").onclick = search;
        function search(event) {
            var xmlHttp = Ajax.getxmlHttp();
            var url = document.querySelector("#url").value;
            xmlHttp.open("get", "/search?url=" + url, true);
            xmlHttp.onreadystatechange = show;
            xmlHttp.send();
        }
        function show() {
            console.log("execute show");
            var state = this.readyState;
            var status = this.status;
            if (state == 4) {
                if (status == 200) {
                    var xmlDoc = this.responseXML;
                    document.querySelector("#list").innerHTML = "";
                    var itemArray = xmlDoc.getElementsByTagName("item");
                    for (var i = 0; i < itemArray.length; i++) {
                        var item = itemArray[i];
                        var title = item.getElementsByTagName("title")[0].childNodes[0].nodeValue;
                        var link = item.getElementsByTagName("link")[0].childNodes[0].nodeValue;
                        createLi(link, title);
                    }
                } else {
                    console.log("server is busy: " + status);
                }
            }
        }
        function createLi(link, title) {
            var li = document.createElement("li");
            var a = document.createElement("a");
            var text = document.createTextNode(title);
            a.setAttribute("href", link);
            a.setAttribute("target", "_blank");
            a.appendChild(text);
            li.appendChild(a);
            document.querySelector("#list").appendChild(li);
        }
    })()

</script>
</body>
</html>