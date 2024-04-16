<%@include file="/views/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="/views/common/dash-board.jsp" %>
<c:if test="${not empty sessionScope.username}">
    <div class="alert alert-success">
        <h1>Hello World from to Home in Web Account ${sessionScope.username}</h1>
    </div>
</c:if>

</body>
</html>


