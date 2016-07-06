<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit book</title>
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/static/css/bootstrap.min.css"/>">

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
<div class="container">
    <div class="starter-template">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2 class="panel-title">Edit book</h2>
            </div>
            <div class="panel-body">
                <form id="signup" method="post" class="form-horizontal"
                      novalidate="novalidate">
                    <!--action="/bookedit" 表单默认提交自身控制器  -->
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="bookcode">Book
                            code</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control  " id="bookcode" value="${book.code}"
                                   name="code" placeholder="Book code" readonly>
                            <!--${requestScope.book.code} 和 ${book.code}  作用一样-->
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="booktitle">Book
                            title</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="booktitle" value="${book.title}"
                                   name="title" placeholder="Book title">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="bookauthor">Book
                            author</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="bookauthor" value="${book.author}"
                                   name="author" placeholder="Book author">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="bookpress">Book
                            press</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="bookpress" value="${book.press}"
                                   name="press" placeholder="press">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="bookstation">Book
                            station</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="bookstation"
                                   value="${book.station}"
                                   name="station" placeholder="station">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="booksbtime">Book
                            station</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="booksbtime"
                                   value="${book.btime}"
                                   name="btime" placeholder="broww times">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-4">
                            <button type="submit" class="btn btn-primary" name="add"
                                    value="add">Confirm
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


<script src="../../../static/js/jquery-2.2.3.min.js"></script>
<script src="../../../static/js/jquery.twbsPagination.min.js"></script>
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
                station: {
                    required: true,
                    range: [在库, 借出]
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
                station: {
                    required: "请输入数量",
                    range: "状态为在库或借出"
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
