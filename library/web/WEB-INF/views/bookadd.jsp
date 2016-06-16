<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book Add</title>
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
<%@ include file="nav.jsp" %>

<c:choose>
    <c:when test="${requestScope.err == '1001'}">
        <div class="alert alert-warning text-center ">
            书籍编号已经存在！
        </div>
    </c:when>
    <c:when test="${requestScore.err == '1002' }">
        <div class="alert alert-warning text-center ">
            书籍尚未归还！
        </div>
    </c:when>
</c:choose>
<div class="container">
    <div class="starter-template">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2 class="panel-title">Add new book</h2>
            </div>
            <div class="panel-body">
                <form id="signup" method="post" class="form-horizontal" action="/bookadd" novalidate="novalidate">
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="bookcode">Bookcode</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="bookcode" name="code" placeholder="Book code">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="booktitle">Booktitle</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="booktitle" name="title" placeholder="Book title">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="bookauthor">Bookauthor</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="bookauthor" name="author" placeholder="Book author">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="bookpress">Bookpress</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="bookpress" name="press" placeholder="press">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="booknum">Booknumber</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="booknum" name="num" placeholder="Book number">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-4">
                            <button type="submit" class="btn btn-primary" name="add" value="add">Add
                            </button>
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
    $(function () {
        $("#idbook").addClass("active");
        $("#signup").validate({
            rules: {
                code: "required",
                title: "required",
                author: {
                    required: true,
                    minlength: 2

                },
                press: {
                    required: true
                },
                num: {
                    required: true,
                    digits: true,
                    range: [1, 9]
                }
            },
            messages: {
                code: "请输入书号",
                title: "请输入书名",
                author: {
                    required: "请输入作者",
                    minlength: "至少两个字"

                },
                press: {
                    required: "请输入出版社"
                },
                num: {
                    required: "请输入数量",
                    digits: "必须是整数",
                    range: "数量在0-9之间"
                }
            },
            errorElement: "em",
            errorPlacement: function (error, element) {
                // Add the `help-block` class to the error element
                error.addClass("help-block");
                // Add `has-feedback` class to the parent div.form-group
                // in order to add icons to inputs
                element.parents(".col-sm-5").addClass("has-feedback");
                if (element.prop("type") === "checkbox") {
                    error.insertAfter(element.parent("label"));
                } else {
                    error.insertAfter(element);
                }
                // Add the span element, if doesn't exists, and apply the icon classes to it.
                if (!element.next("span")[0]) {
                    $("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter(element);
                }
            },
            success: function (label, element) {
                // Add the span element, if doesn't exists, and apply the icon classes to it.
                if (!$(element).next("span")[0]) {
                    $("<span class='glyphicon glyphicon-ok form-control-feedback'></span>").insertAfter($(element));
                }
            },
            highlight: function (element, errorClass, validClass) {
                $(element).parents(".col-sm-5").addClass("has-error").removeClass("has-success");
                $(element).next("span").addClass("glyphicon-remove").removeClass("glyphicon-ok");
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).parents(".col-sm-5").addClass("has-success").removeClass("has-error");
                $(element).next("span").addClass("glyphicon-ok").removeClass("glyphicon-remove");
            }
        });
    });
</script>
</body>
</html>
