<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Card home </title>
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
</style>

</head>
<body>
<%@ include file="nav.jsp" %>
	<div class="container">
		<div class="starter-template">
<a href = "/cardinsert" style= "float:right"  class="btn btn-success">添加借书卡</a>
<div class="bs-example" data-example-id="bordered-table">
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>序号</th>
          <th>卡号</th>
          <th>姓名</th>
          <th>电话</th>
          <th>借书次数</th>
          <th>图书</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach var = "card" items ="${requestScope.list}" varStatus ="s">
      	 <tr>
          <th scope="row">${s.count}</th>
          <td>${card.code}</td>
          <td>${card.user}</td>
          <td>${card.tel}</td>
          <td>${card.ctime}</td>
          <td>${card.count}</td>
          <td>
          <button data ="${card.code}" class = "btn btn-danger btn-xs">删除</button>
          <button data ="${card.code}"class = "btn btn-primary btn-xs">编辑</button>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div> 
  
	</div>
</div>  
  
<script>
	$("#idcard").addClass("active");
	$("td>button.btn-danger").bind('click',detail);
	$("td>button.btn-primary").bind('click',detail1);
	function detail(event){
		// console.log(event.target.parentElement.parentElement.first);
		if(confirm("确认删除吗？")){
			var i = $(this).attr("data");
			// alert("删除id:" + i);
			location.href="/carddel?id=" + i;
		}
	}
	
	function detail1(event){
		// console.log(event.target.parentElement.parentElement.first);
			var i = $(this).attr("data");
			// alert("删除id:" + i);
			location.href="/cardedit?id=" + i;
	}
	
</script>
</body>
</html>

