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
    <div class="panel" id="show"></div>
</div>

<script src="js/jquery-2.2.3.min.js"></script>
<script>
    $(function(){
       $("#search").on("keyup",function(event){
           if(event.keyCode != 13){
               return;
           }
           // 使用代理模式或者jsonp
           $.get();

       });



    });


</script>


</body>
</html>
