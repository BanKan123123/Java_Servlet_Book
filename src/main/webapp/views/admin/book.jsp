<%@include file="/views/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Books List</title>
    <link href="${contextPath}/views/assets/css/bootstrap.min.css" rel="stylesheet">
    <script src="${contextPath}/views/assets/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
</head>
<body>
<h1>API Data</h1>
<c:if test="${not empty responseData}">
    <table class="table table-dark table-hover">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Image Thumbnail</th>
            <th scope="col">Title</th>
            <th scope="col">Description</th>
            <th scope="col">Author</th>
            <th scope="col">Categories</th>
            <th scope="col">Rate</th>
            <th scope="col">Liked</th>
            <th scope="col">Quantity</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${responseData}">
        <tr>
            <th scope="row">1</th>
            <td>Mark</td>
            <td>Otto</td>
            <td>@mdo</td>
        </tr>
        </tbody>
        </c:forEach>
    </table>

    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#add-book">
        Add Book
    </button>

    <div class="modal fade" id="add-book" tabindex="-1" aria-labelledby="add-book-modal" aria-hidden="true">
        <div class="modal-dialog">
            <form class="modal-content" method = "POST" action = "book">
                <div class="modal-header">
                    <h5 class="modal-title" id="add-book-modal">Add Book</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="title" class="form-label">Title</label>
                        <input name="title" type="text" class="form-control" id="title" placeholder="Title...">
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Description</label>
                        <input name="description" type="text" class="form-control" id="description">
                    </div>
                    <div class="mb-3 form-check">
                        <input name="image-thumbnail" type="text" class="form-control" id="image-thumbnail">
                        <label class="form-label" for="image-thumbnail">Image Thumbnail</label>
                    </div>
                    <div class="mb-3">
                        <label for="rate" class="form-label">Rate</label>
                        <input name="rate" type="text" class="form-control" id="rate">
                    </div>
                    <div class="mb-3">
                        <label for="liked" class="form-label">Liked</label>
                        <input name="liked" type="text" class="form-control" id="liked">
                    </div>
                    <div class="mb-3">
                        <label for="author" class="form-label">Author</label>
                        <input name="author-slug" type="text" class="form-control" id="author">
                    </div>
                    <div class="mb-3">
                        <label for="categories" class="form-label">Categories</label>
                        <input name="categories" type="text" class="form-control" id="categories">
                    </div>
                    <div class="mb-3">
                        <label for="quantity" class="form-label">Quantity</label>
                        <input name="quantity" type="text" class="form-control" id="quantity">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Add</button>
                </div>
            </form>
        </div>
    </div>

</c:if>
</body>
</html>
