<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Card edit</title>
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
			<div class="panel panel-default">
				<div class="panel-heading">
					<h2 class="panel-title">Card edit</h2>
				</div>
				<div class="panel-body">
					<form id="signup" method="post" class="form-horizontal"
						action="/cardedit" novalidate="novalidate">
						<div class="form-group">
							<label class="col-sm-4 control-label" for="cardcode">Card
								code</label>
							<div class="col-sm-5">
								<input type="text" class="form-control  " id="cardcode" value = "${card.code}"
									name="code" placeholder="card code" readonly>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="carduser">User
								</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="carduser" value = "${card.user}"
									name="user" placeholder="card user">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-4 control-label" for="cardauthor">
								Tel</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="tel" value = "${card.tel}"
									name="tel" placeholder="tel">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-4 control-label" for="cardctime">
								times</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="cardctime" value = "${card.ctime}"
									name="ctime" placeholder="ctime" readonly>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="count">
								count</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="count" value = "${card.count}"
									name="count" placeholder="count" readonly>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-9 col-sm-offset-4">
								<button type="submit" class="btn btn-primary" name="edit"
									value="edit">Confirm</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="/js/jquery.validate.min.js"></script>
	<script>		
		$(function() {
			$("#idcard").addClass("active");
			$("#signup").validate({
				rules : {
					code : "required",
					user : "required",
					tel : "required",
					ctime : "required",
					count : "required"
				},
				messages : {
					code : "",
					user : "",
					tel : "",
					ctime : "",
					count : ""
				},
				errorElement: "em",
				errorPlacement: function ( error, element ) {
					// Add the `help-block` class to the error element
					error.addClass( "help-block" );
					// Add `has-feedback` class to the parent div.form-group
					// in order to add icons to inputs
					element.parents( ".col-sm-5" ).addClass( "has-feedback" );
					if ( element.prop( "type" ) === "checkbox" ) {
						error.insertAfter( element.parent( "label" ) );
					} else {
						error.insertAfter( element );
					}
					// Add the span element, if doesn't exists, and apply the icon classes to it.
					if ( !element.next( "span" )[ 0 ] ) {
						$( "<span class='glyphicon glyphicon-remove form-control-feedback'></span>" ).insertAfter( element );
					}
				},
				success: function ( label, element ) {
					// Add the span element, if doesn't exists, and apply the icon classes to it.
					if ( !$( element ).next( "span" )[ 0 ] ) {
						$( "<span class='glyphicon glyphicon-ok form-control-feedback'></span>" ).insertAfter( $( element ) );
					}
				},
				highlight: function ( element, errorClass, validClass ) {
					$( element ).parents( ".col-sm-5" ).addClass( "has-error" ).removeClass( "has-success" );
					$( element ).next( "span" ).addClass( "glyphicon-remove" ).removeClass( "glyphicon-ok" );
				},
				unhighlight: function ( element, errorClass, validClass ) {
					$( element ).parents( ".col-sm-5" ).addClass( "has-success" ).removeClass( "has-error" );
					$( element ).next( "span" ).addClass( "glyphicon-ok" ).removeClass( "glyphicon-remove" );
				}
			});
		});
	</script>
</body>
</html>
