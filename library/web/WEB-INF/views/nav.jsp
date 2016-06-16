<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Library</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li id="idhome"><a href="/home">Home</a></li>
					<li class="dropdown">
                        <a id="idbook" data-toggle="dropdown" class="dropdown-toggle" href="/home">Book<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                           <li><a href="/bookshow">Show all</a></li>
                           <li><a href="/bookadd">Add book</a></li>
                        <li><a href="#">ABC</a></li>
                         </ul>
                    </li>
					<li class="dropdown">
                        <a id="idborrow" data-toggle="dropdown" class="dropdown-toggle" href="/cardhome">Card<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                           <li><a href="/cardinsert">Cardinsert</a></li>
                           <li><a href="#">Cardedit</a></li>
                           <li><a href="#">More</a></li>
                         </ul>
                    </li>
					<li class="dropdown">
                        <a id="idborrow"  data-toggle="dropdown" class="dropdown-toggle" href="/borrow">B&Rbook<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                           <li><a href="/bookborrow">Borrow</a></li>
                           <li><a href="#">Return</a></li>
                           <li><a href="#">ABC</a></li>
                         </ul>
                    </li>
					<li class="dropdown">
                        <a id="idinfor" data-toggle="dropdown" class="dropdown-toggle" href="#">Information<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                           <li><a href="/inforcard">Inforcard</a></li>
                           <li><a href="#">Inforbook</a></li>
                           <li class = "separator"><a href="#">more</a></li>
                           <li><a href="#">more</a></li>
                         </ul>
                    </li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<!--<li ><a href="#"><%=session.getAttribute("name")%>,欢迎登陆！</a></li>-->
					<li ><a href="#">${sessionScope.name},欢迎登陆！</a></li>
					<li id = "quit"><a href="#" class="btn btn-defalut btn-sm" >安全退出</a></li>
					<li ><a href="#" class="btn btn-defalut btn-sm" >当前在线人数:${applicationScope.count} </a></li>
					<!-- [${applicationScope.count}] -->
				</ul>>
			</div>
		</div>
	</nav>

	
