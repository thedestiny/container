<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Information for card</title>
<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">

<style>
body {
	padding-top: 50px;
}

.starter-template {
	padding: 40px 15px;
	text-align: center;
}

form>ipput#id {
	width: 70%
}

.panel {
	padding-top: 9px;
	height: 40px;
}
</style>

</head>
<body>
	<%@ include file="nav.jsp"%>
	<div class="container">
		<div class="starter-template">
			<c:choose>
				<c:when test="${requestScope.result == 'bs'}">
					<div class="alert alert-success text-center ">
					<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">	<span aria-hidden="true">&times;</span>
						</button>
					<strong>borrow success！</strong> 
					</div>
				</c:when>
				<c:when test="${requestScope.result == 'bf'}">
					<div class="alert alert-warning text-center ">
					<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">	<span aria-hidden="true">&times;</span>
						</button>
					<strong>borrow faliure！</strong> 
					</div>
				</c:when>
				<c:when test="${requestScope.result == 'rs'}">
					<div class="alert alert-success text-center ">
					<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">	<span aria-hidden="true">&times;</span>
						</button>
					<strong>return success！</strong> 
					</div>
				</c:when>
				<c:when test="${requestScope.result == 'rf'}">
					<div class="alert alert-success text-center ">
					<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">	<span aria-hidden="true">&times;</span>
						</button>
					<strong>return failure！</strong> 
					</div>
				</c:when>

			</c:choose>
			<!--<form class="form form-group-lg form-group " action="/bookshow"
				method="get">
				<label for="search"></label> <input id="search" name="search"
					type="text" class="text-center form-control"
					placeholder="search for : title /author /press /code" />
				<button class="btn btn-lg btn-primary">Search</button>
			</form> -->
			<div class="panel panel-primary bg-info "
				style="background-color: #2aabd2">
				<p class="lead text-center" style="line-height: 100%">This is
					detail record information <c:if test="${requestScope.user != null}">of</c:if> ${requestScope.user}</p>
			</div>
			<div id="ccode" class="sr-only">${requestScope.ccode}</div>
			<div class="bs-example" data-example-id="bordered-table">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>序号</th>
							<th>书号</th>
							<th>书名</th>
							<th>作者</th>
							<th>出版社</th>
							<th>状态</th>
							<th>借出时间</th>
							<th>归还时间</th>
							<th>借阅天数</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="realation" items="${requestScope.list}"
							varStatus="sss">
							<tr>
								<th scope="row">${sss.count}</th>
								<td>${realation.bcode}</td>
								<td>${realation.title}</td>
								<td>${realation.author}</td>
								<td>${realation.press}</td>
								<td>${realation.station}</td>
								<td>${realation.brtime}</td>
								<td>${realation.retime}</td>
								<td>${realation.bday}</td>
								<td>
									<button data="${realation.bcode}" class="btn btn-danger btn-xs">续借</button>
									<button data="${realation.bcode}"
										class="btn btn-primary btn-xs">归还</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>
	</div>
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#idfor").addClass("active");
			$("td>button.btn-primary").bind('click', detail);
		});
		function detail() {
			var i = $(this).attr("data");
			var j = $("#ccode").html().trim();
			// alert("删除id:" + i);
			location.href = "/bookreturn?bcode=" + i + "&ccode=" + j;
		}
	</script>
</body>
</html>

