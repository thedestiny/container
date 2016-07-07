<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xieyue
  Date: 2016/7/6
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BookList</title>
    <link rel="stylesheet" href="<c:url value="/static/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="/static/css/font-awesome.min.css">
    <link rel="stylesheet" href="/static/datatable/media/css/dataTables.bootstrap.min.css">
    <style>
        #ta {
            margin-top: 150px;
        }
    </style>
</head>
<body>


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
                <li id="idhome"><a href="#">Home</a></li>
                <li class="dropdown">
                    <a id="idbook" data-toggle="dropdown" class="dropdown-toggle" href="#">Book<span
                            class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Show all</a></li>
                        <li><a href="#">Add book</a></li>
                        <li><a href="#">ABC</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a id="idcard" data-toggle="dropdown" class="dropdown-toggle" href="#">Card<span
                            class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Cardinsert</a></li>
                        <li><a href="#">Cardedit</a></li>
                        <li><a href="#">More</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a id="idborrow" data-toggle="dropdown" class="dropdown-toggle" href="#">B&Rbook<span
                            class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Borrow</a></li>
                        <li><a href="#">Return</a></li>
                        <li><a href="#">ABC</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a id="idinfor" data-toggle="dropdown" class="dropdown-toggle" href="#">Information<span
                            class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Inforcard</a></li>
                        <li><a href="#">Inforbook</a></li>
                        <li class="separator"><a href="#">more</a></li>
                        <li><a href="#">more</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <!--<li ><a href="#"><%=session.getAttribute("name")%>,欢迎登陆！</a></li>-->
                <li><a href="#">${sessionScope.name}欢迎登陆！</a></li>
                <li id="quit"><a href="#" class="btn btn-defalut btn-sm">安全退出</a></li>
                <!-- [${applicationScope.count}] -->
            </ul>
            >
        </div>
    </div>
</nav>


<div id="ta" class="container">
    <div class="well well-sm">
        <form class="form form-inline" id="searchForm">
            <div class="form-group">
                <input type="text" name="title" id="title" class="form-control" placeholder="输入书籍名" value="${rtitle}">
            </div>
            <div class="form-group">
                <input type="text" name="author" id="author" class="form-control" placeholder="输入作者名"
                       value="${rauthor}">
            </div>
            <div class="form-group">
                <input type="text" name="press" id="press" class="form-control" placeholder="输入出版社名" value="${rpress}">
            </div>
            <button class="btn btn-info">搜索</button>
            <button type="reset" id="reset" class="btn btn-default">重置</button>
            <a id="addBtn" class="btn btn-success pull-right">添加书籍</a>
        </form>
    </div>
    <div class="panel">
        <table id="tab" class="table table-bordered">
            <thead>
            <tr>
                <th>id</th>
                <th>书号</th>
                <th>书名</th>
                <th>作者</th>
                <th>出版社</th>
                <th>状态</th>
                <th>借阅次数</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
</div>
<div id="add" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Add Book</h4>
            </div>
            <div class="modal-body">
                <form id="addForm">
                    <div class="form-group">
                        <label class="control-label" for="bookcode">Bookcode</label>
                        <div>
                            <input type="text" class="form-control" id="bookcode" name="code" placeholder="Book code">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class=" control-label" for="booktitle">Booktitle</label>
                        <div class="">
                            <input type="text" class="form-control" id="booktitle" name="title"
                                   placeholder="Book title">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class=" control-label" for="bookauthor">Bookauthor</label>
                        <div class="">
                            <input type="text" class="form-control" id="bookauthor" name="author"
                                   placeholder="Book author">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class=" control-label" for="bookpress">Bookpress</label>
                        <div class="">
                            <input type="text" class="form-control" id="bookpress" name="press" placeholder="press">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button id="saveAddBtn" type="button" class="btn btn-primary">Save</button>
            </div>
        </div>
    </div>
</div>
<div id="edit" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Edit Book</h4>
            </div>
            <div class="modal-body">
                <form id="editForm">
                    <div class="form-group">
                        <label class="control-label" for="bookcode">Bookcode</label>
                        <div>
                            <input type="text" class="form-control " id="editcode" name="code"
                                   placeholder="Book code" readonly >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class=" control-label" for="booktitle">Booktitle</label>
                        <div class="">
                            <input type="text" class="form-control" id="edittitle" name="title"
                                   placeholder="Book title">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class=" control-label" for="bookauthor">Bookauthor</label>
                        <div class="">
                            <input type="text" class="form-control" id="editauthor" name="author"
                                   placeholder="Book author">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class=" control-label" for="bookpress">Bookpress</label>
                        <div class="">
                            <input type="text" class="form-control" id="editpress" name="press" placeholder="press">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class=" control-label" for="bookpress">station</label>
                        <div class="">
                            <input type="text" class="form-control" id="editstation" name="station"
                                   placeholder="station">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class=" control-label" for="bookpress">borrow time</label>
                        <div class="">
                            <input type="text" class="form-control" id="editbtime" name="btime"
                                   placeholder="borrowtime">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button id="saveEditBtn" type="button" class="btn btn-primary">Save</button>
            </div>
        </div>
    </div>
</div>

<script src="../../../static/js/jquery-2.2.3.min.js"></script>
<script src="../../../static/js/bootstrap.min.js"></script>
<script src="../../../static/js/jquery.validate.min.js"></script>
<script src="../../../static/datatable/media/js/jquery.dataTables.min.js"></script>
<script src="../../../static/datatable/media/js/dataTables.bootstrap.min.js"></script>
<script>
    var dataTable = $("#tab").DataTable({
        "lengthMenu": [5, 10, 25, 50, 75, 100], //定义每页显示的数量列表
        "serverSide": true, //表示所有的操作都在服务端进行
        "ajax": {
            url:"/table/data.json", //服务端URL地址
            data:function(dataSource){
                datasource.title= $("#title").val();
                dataSource.press=$("#press").val();
                dataSource.author=$("#author").val();
            }
        },
        "order": [0, 'desc'],
        "searching": false,
        "columns": [ //配置返回的JSON中[data]属性中数据key和表格列的对应关系
            {"data": "id", "name": "id"},
            {"data": "code", "name": "code"},
            {"data": "title"},
            {"data": "author"},
            {"data": "press", "name": "press"},
            {"data": "station", "name": "station"},
            {"data": "btime"},
            {
                "data": function (row) {
                    return "<td>" +
                            "<button rel='" + row.id + "' class='btn btn-primary'>修改</button>" +
                            "<button rel='" + row.id + "' class='btn btn-danger'>删除</button>" +
                            "</td>";
                }
            }
        ],
        "columnDefs": [ //定义列的特征
            {targets: [0], visible: true},
            {targets: [2, 3, 6], orderable: false}
        ],
        "language": { //定义中文
            "search": "请输入书籍名称:",
            "zeroRecords": "没有找到",
            "lengthMenu": "显示 _MENU_ 条数据",
            "info": "从 _START_ 到 _END_ 条/共 _TOTAL_ 条",
            "infoFiltered": "(查阅 _MAX_ 条数据)",
            "loadingRecords": "加载中...",
            "processing": "处理中...",
            "paginate": {
                "first": "首页",
                "last": "末页",
                "next": "下一页",
                "previous": "上一页"
            }
        }
    });

    //添加书籍
    $("#addBtn").click(function () {
        // 重置表单
        $("#addForm")[0].reset();
        // 展示模态框
        $("#add").modal({
            "show": true,
            backdrop: "static",
            keyboard: false
        });
    });
    // 提交新书数据
    $("#saveAddBtn").click(function () {
        $("#addForm").submit();
    });
    // 添加书籍表单验证
    $("#addForm").validate({
        errorElement: "span",
        errorClass: "text-danger",
        rules: {
            code: {
                required: true,
                range: ["100000", "999999"],
                remote: "/table/code"
            },
            title: {
                required: true
            },
            author: {
                required: true
            },
            press: {
                required: true
            }
        },
        messages: {
            code: {
                required: "请输入书号",
                range: "书号范围为100000-999999",
                remote: "该书号已经存在"
            },
            title: {
                required: "输入书名"
            },
            author: {
                required: "输入作者"
            },
            press: {
                required: "输入出版社"
            }
        },
        submitHandler: function (form) {
            $.post("/table/add", $(form).serialize())
                    .done(function (data) {
                        $("#ta").prepend("<div class='alert alert-success alert-dismissible'>" +
                                "<button type='button' class='close' data-dismiss='alert' >" +
                                "<span aria-hidden='true'>&times;</span>" +
                                "</button><strong>Tips:</strong>" + data + "</div>");
                        console.log(data);
                        $("#add").modal("hide");
                        dataTable.ajax.reload();
                    })
                    .fail(function (data) {
                        alert("添加失败")
                    })
                    .always(function () {

                    });
        },
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

    // 删除书籍
    $(document).delegate(".btn-danger", "click", function () {
        var id = $(this).attr("rel");
        if (confirm("确认删除吗?")) {
            $.get("/table/del/" + id)
                    .done(function (data) {
                        if (data == "success") {
                            dataTable.ajax.reload();
                        }
                    })
                    .fail(function () {
                        alert("请求服务器异常");
                    });
        }
    });

    // 编辑书籍
    $(document).delegate(".btn-primary", "click", function () {
        var id = $(this).attr("rel");
        console.log(id);
        if(id == null){
            return;
        }
        $.get("/table/edit/" + id)
                .done(function (data) {
                    $("#editid").val(data.id);
                    $("#editcode").val(data.code);
                    $("#editauthor").val(data.author);
                    $("#edittitle").val(data.title);
                    $("#editpress").val(data.press);
                    $("#editstation").val(data.station);
                    $("#editbtime").val(data.btime);
                    $("#edit").modal({
                        show: true,
                        drapback: 'static',
                        keyboard: false
                    });
                })
                .fail(function () {
                    alert("请求服务器异常");
                });

    });

    // 提交数据
    $("#saveEditBtn").click(function () {
        console.log(" exexute !")
        $("#editForm").submit();
    });

    // 添加验证
    $("#editForm").validate({
        rules: {
            code: {
                required: true
            },
            title: {
                required: true
            },
            author: {
                required: true
            },
            press: {
                required: true
            }
        },
        messages: {
            code: {
                required: "请输入书号",
            },
            title: {
                required: "输入书名"
            },
            author: {
                required: "输入作者"
            },
            press: {
                required: "输入出版社"
            }
        },
        errorElement:"span",
        errorClass:"text-danger",
        submitHandler: function (form) {
            $.post("/table/edit",$(form).serialize())
                    .done(function (data) {
                        $("#ta").prepend("<div class='alert alert-success alert-dismissible'>" +
                                "<button type='button' class='close' data-dismiss='alert' >" +
                                "<span aria-hidden='true'>&times;</span>" +
                                "</button><strong>Tips:</strong>" + data + "</div>");
                        console.log(data);
                        $("#edit").modal("hide");
                        dataTable.ajax.reload();
                    })
                    .fail(function (data) {
                        alert("添加失败")
                    });
        }
    });

</script>


</body>
</html>
