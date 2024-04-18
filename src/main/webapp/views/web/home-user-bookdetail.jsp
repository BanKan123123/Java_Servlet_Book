<%@include file="/views/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Book Detail</title>
    <link href="${contextPath}/views/assets/css/bootstrap.min.css" rel="stylesheet">
    <script src="${contextPath}/views/assets/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <link href="${contextPath}/views/assets/css/css-web/home_book_detail.scss" rel="stylesheet"/>

</head>
<body>

<jsp:include page="/views/common/navbar.jsp"/>

<c:if test="${not empty responseBookDetail}">
    <div class="container d-flex">
        <div class="description_book">
            <h1>${responseBookDetail.title}</h1>

            <div class="book d-flex align-items-center">
                <div class="ill">
                    <img src="${responseBookDetail.imageThumbnail}" alt="${responseBookDetail.title}"/>
                </div>
                <div class="info">
                    <p class="text-info"> Author: ${responseBookDetail.authors.name} </p>
                    <p class="text-info"> Tình trạng: Đang hoàn thành </p>
                    <p class="text-info"> Thể loại:
                        <c:forEach var="category" items="${book.categories}"
                                   varStatus="loop">
                            <span>${category.name}</span>
                            <c:if test="${!loop.last}">
                                <span>,</span>
                            </c:if>
                        </c:forEach>
                    </p>
                    <p class="text-info"> Lượt thích: ${responseBookDetail.liked} </p>
                    <p class="text-info"> Đánh giá: ${responseBookDetail.rate} </p>
                </div>
            </div>
            <button class="btn btn-outline-info"> Mượn</button>
            <h3>
                Nội dung: ${responseBookDetail.description}
            </h3>
        </div>

        <div class="range_book">
            <h3> Top Tháng </h3>

            <c:if test="${not empty responseBookRange}">
                <c:forEach var="bookRange" items="${responseBookRange}">
                    <div class="card mb-3">
                        <div class="row g-0">
                            <div class="col-md-4">
                                <img src="${bookRange.imageThumbnail}" class="img-fluid rounded-start"
                                     alt="${bookRange.title}">
                            </div>
                            <div class="col-md-8">
                                <div class="card-body">
                                    <h5 class="card-title">${bookRange.title}</h5>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <p class="card-text text-description"> ${bookRange.description} </p>
                                        <p class="card-text"><small
                                                class="text-body-secondary">${bookRange.liked}</small></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
</c:if>

</body>
</html>
