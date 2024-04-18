<%@include file="/views/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="user-home">Home</a>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-bs-toggle="dropdown">
                        Categories
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <c:if test="${not empty responseCategory}">
                            <c:forEach var="category" items="${responseCategory}">
                                <li><a class="dropdown-item" href="#">${category.name}</a></li>
                            </c:forEach>
                        </c:if>
                    </ul>
                </li>
            </ul>
            <div class="d-flex">
                <% String username = (String) session.getAttribute("username"); %>
                <c:if test="${not empty username}">
                    <button class="btn btn-outline-info" type="button">Hello ${username}</button>
                </c:if>
                <c:if test="${empty username}">
                    <button class="btn btn-outline-info" type="button"><a href="">Login</a></button>
                </c:if>
                <c:if test="${not empty username}">
                    <form style="margin: 0 0 0 20px; " action="logout" method="post">
                        <button class="btn btn-outline-danger" type="submit"> Logout</button>
                    </form>
                </c:if>
            </div>
        </div>
    </div>
</nav>

</body>
</html>
