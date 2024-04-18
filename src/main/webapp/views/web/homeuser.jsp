<%@include file="/views/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%--<jsp:useBean id="responseCategory" scope="request"/>--%>

<html>
<head>
    <title>Home User</title>
    <link href="${contextPath}/views/assets/css/bootstrap.min.css" rel="stylesheet">
    <script src="${contextPath}/views/assets/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <link href="${contextPath}/views/assets/css/css-web/book_list.scss" rel="stylesheet"/>
    <link href="${contextPath}/views/assets/css/css-web/home_user.scss" rel="stylesheet"/>
</head>
<body>

<jsp:include page="/views/common/navbar.jsp"/>
<div class="container">
    <form style="display: flex; align-items: center" method="GET" action="user-home">
        <div>
            <label for="search"></label>
            <input style="width: 90%; " name="search" placeholder="Search..." type="text" class="form-control"
                   id="search">
        </div>
        <button type="submit" class="btn btn-primary">Tìm kiếm</button>
    </form>
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <c:if test="${not empty responseBook}">
            <c:forEach var="book" items="${responseBook}">
                <a style="text-decoration: none" href="user-home?slug=${book.slug}">
                    <div class="col">
                        <div class="card h-100">
                            <img src="${book.imageThumbnail}" class="card-img-top" alt="${book.title}">
                            <div class="card-body">
                                <h5 class="card-title">${book.title}</h5>

                                <div class="d-flex justify-content-between">
                                    <p class="card-text">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                             fill="currentColor"
                                             class="bi bi-star-half" viewBox="0 0 16 16">
                                            <path d="M5.354 5.119 7.538.792A.52.52 0 0 1 8 .5c.183 0 .366.097.465.292l2.184 4.327 4.898.696A.54.54 0 0 1 16 6.32a.55.55 0 0 1-.17.445l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256a.5.5 0 0 1-.146.05c-.342.06-.668-.254-.6-.642l.83-4.73L.173 6.765a.55.55 0 0 1-.172-.403.6.6 0 0 1 .085-.302.51.51 0 0 1 .37-.245zM8 12.027a.5.5 0 0 1 .232.056l3.686 1.894-.694-3.957a.56.56 0 0 1 .162-.505l2.907-2.77-4.052-.576a.53.53 0 0 1-.393-.288L8.001 2.223 8 2.226z"></path>
                                        </svg>
                                            ${book.rate}
                                    </p>
                                    <p class="card-text">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                             fill="currentColor"
                                             class="bi bi-heart-fill" viewBox="0 0 16 16">
                                            <path fill-rule="evenodd"
                                                  d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314"></path>
                                        </svg>
                                            ${book.liked}</p>
                                </div>
                                <p class="card-text">
                                    <c:forEach var="category" items="${book.categories}" varStatus="loop">
                                        <span>${category.name}</span>
                                        <c:if test="${!loop.last}">
                                            <span>,</span>
                                        </c:if>
                                    </c:forEach>
                                </p>
                            </div>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </c:if>
    </div>
</div>

</body>
</html>
