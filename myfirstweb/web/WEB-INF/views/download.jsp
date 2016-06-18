<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xieyue
  Date: 2016/6/16
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Download</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
</head>
<body> 

<div class="container">
    <div class="row"></div>
    <div class="col-sm-push-2 col-lg-8">
        <div class="panel">
            <div class="panel-heading">
                <div class="panel-title">
                    <h1>Resource Home For Download</h1>
                </div>
            </div>
            <div class="panel-body">
                <a href="/upload" class="btn btn-sm btn-primary pull-right">上传</a>
                <div class="table table-responsive">
                    <table class="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>name</th>
                                <th>size</th>
                                <th>type</th>
                                <th>operation</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="document" items="${requestScope.documentlist}" varStatus="num">
                            <tr>
                                <td>${num.count}</td>
                                <td>${document.filename}</td>
                                <td>${document.displaysize}</td>
                                <td>${document.filetype}</td>
                                <td>
                                    <c:if test="${document.preview}">
                                        <%--预览另外弹框，下载在本页--%>
                                        <a href="/preview?file=${document.md5}" target="_blank" class="btn btn-sm btn-primary">preview</a>
                                    </c:if>
                                    <a href="/preview?file=${document.md5}&down=down" class="btn btn-sm btn-success">download</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>




</body>
</html>
