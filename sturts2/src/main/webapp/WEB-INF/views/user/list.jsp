<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List</title>
</head>
<body>

    <c:forEach items="${names}" var="name">
        <div>${name}</div>
    </c:forEach>


</body>
</html>
