<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">

<style>
body {
	padding-top: 50px;
}

.starter-template {
	padding: 40px 15px;
	text-align: center;
}
</style>
</head>
<body>
<%@ include file="nav.jsp"%> 
	<div class="container">
		<div class="starter-template">
			<h1>Resume:</h1>

			<p class="lead">
				We library have 1000,000,000 books, contains science,chemical<br>
				社会主义接班人
			</p>					
		</div>
	</div>
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
	$(function(){
		$("#idhome").addClass("active");
		$("#quit").bind("click",function(){
			console.log("123");
			if(confirm("确认退出？")){
				location.href="/quit";
			}	
		})
		
	})
	
</script>
</body>
</html>
