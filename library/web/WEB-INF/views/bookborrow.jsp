<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--  page import="BookDAO"    -->
<!--   page import="Book"   -->
<!--   page import="java.util.List"  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Borrow</title>
<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style>
body {
	padding-top: 50px;
}

.starter-template {
	padding: 40px 15px;
	text-align: center;
} 
form>ipput#id{
	width:70%
}
</style>

</head>
<body>
<%@ include file="nav.jsp" %>
	<div class="container">
		<div class="starter-template">

<!--  Book book = (Book)request.getAttribute("book");   -->
<c:choose>
	<c:when test="${requestScope.err == '1004' }">
	<div class="alert alert-warning text-center ">
		借书失败！
	</div>
	</c:when>
</c:choose>

	<div class="panel panel-default">
				<div class="panel-heading">
					<h2 class="panel-title">Book Borrow</h2>
				</div>
				<div class="panel-body">
					<form id="signup" method="post" class="form-horizontal"
						 novalidate="novalidate">
						 <!--action="/bookborrow" -->
						<div class="form-group">
							<label class="col-sm-4 control-label" for="bookcode">Book
								code</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="bookcode" value = "${book.code}"
									name="code" placeholder="Book code" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="booktitle">Book
								title</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="booktitle" value = "${book.title}"
									name="title" placeholder="Book title" >
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-4 control-label" for="bookauthor">Book
								author</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="bookauthor" value = "${book.author}"
									name="author" placeholder="Book author" >
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-4 control-label" for="bookpress">Book
								press</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="bookpress" value = "${book.press}"
									name="press" placeholder="press" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="bookstation">Book
								station</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="bookstation" value = "${book.station}"
									name="station" placeholder="station">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="bookstation">card
								code</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="bookstation" 
									name="ccode" placeholder="please input card code">
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-9 col-sm-offset-4">
								<button type="submit" class="btn btn-primary" name="add"
									value="add">Borrow</button>
							</div>
						</div>
					</form>
				</div>
			</div>
   </div>	
</div>
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>  
<script>  
	$(function(){
		$("#idborrow").addClass("active");	
	})
</script> 	
</body>
</html>

