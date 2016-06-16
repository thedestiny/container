<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book show</title>
<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">

<style>
body {
	padding-top: 50px;
}

.starter-template {
	padding: 40px 15px;
	text-align: center;
} 
form>input#id{
	width:70%
}
</style>

</head>
<body>
<%@ include file="nav.jsp" %>
	<div class="container">
		<div class="starter-template">
<c:choose>
	<c:when test="${requestScope.err == '1002'}">
		<div class="alert alert-warning text-center ">
		书籍尚未归还！
	</div>
	</c:when>
</c:choose>
	
<form class="form form-group-lg form-group " action="/bookshow" method="get">
    <label for="search"></label>
    <input id="search" name = "search" type="text" class="text-center form-control"  placeholder="search for : title /author /press /code"/>
    <button class="btn btn-lg btn-primary">Search</button>
</form>

<a href = "/bookadd" style= "float:right"  class="btn btn-success">添加图书</a>
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
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach var = "book" items = "${requestScope.list}" varStatus = "s">
      	<tr>
          <th scope="row">${s.count}</th>
          <td>${book.code}</td>
          <td>${book.title}</td>
          <td>${book.author}</td>
          <td>${book.press}</td>
          <td >${book.station}</td>
          <td>
          	<button data ="${book.code}" class = "btn btn-danger btn-xs">删除</button>
          	<button data ="${book.code}" class = "btn btn-primary btn-xs">编辑</button>
          	<button data ="${book.code}" class = "btn btn-inf btn-xs" <c:if test="${book.station == '借出'}">disabled</c:if> >借阅</button>
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
<script>  
	$(function(){
		$("#idbook").addClass("active");
		$("td>button.btn-danger").bind('click',detail);
		$("td>button.btn-primary").bind('click',detail1);
		$("td>button.btn-inf").bind('click',detail2); 
	})
	
	function detail(event){
		// console.log(event.target.parentElement.parentElement.first);
		if(confirm("确认删除吗？")){
			var i = $(this).attr("data");
			// alert("删除id:" + i);
			location.href="/bookdel?id=" + i;
		}
	}
	function detail1(event){
		// console.log(event.target.parentElement.parentElement.first);
			var i = $(this).attr("data");
			// alert("删除id:" + i);
			location.href="/bookedit?id=" + i;
	}
	function detail2(event){
		// console.log(event.target.parentElement.parentElement.first);
			var i = $(this).attr("data");
			// alert("删除id:" + i);
			location.href="/bookborrow?id=" + i;
	} 

	
</script>
</body>
</html>

