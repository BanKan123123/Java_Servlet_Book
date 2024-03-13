<%@include file="/views/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Books List</title>
</head>
<body>
<h1>API Data</h1>
<c:if test="${not empty responseData}">
    <ul>
        <c:forEach var="book" items="${responseData}">
            <a href="">${book.title}</a>
        </c:forEach>
    </ul>
</c:if>


</body>
</html>
