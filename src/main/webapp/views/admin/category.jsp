<%@include file="/views/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
    <link href="${contextPath}/views/assets/css/bootstrap.min.css" rel="stylesheet">
    <script src="${contextPath}/views/assets/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    <link href="${contextPath}/views/assets/css/css-web/book_list.scss" rel="stylesheet"/>
</head>
<body>
<div class="book_list">
    <%@include file="/views/common/dash-board.jsp" %>
    <div class="main-book">
        <div class="main-title">
            <h1> Category Working</h1>
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#add-book">
                Add Category
            </button>

            <div class="modal fade" id="add-book" tabindex="-1" aria-labelledby="add-book-modal" aria-hidden="true">
                <div class="modal-dialog">
                    <form class="modal-content" method="POST" action="categories">
                        <div class="modal-header">
                            <h5 class="modal-title" id="add-book-modal">Add Book</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="mb-3">
                                <label for="name" class="form-label">Name</label>
                                <input name="name" type="text" class="form-control" id="name" placeholder="Name...">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button name="_method" type="submit" class="btn btn-primary">Add</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div>
            <c:if test="${not empty responseCategory}">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Ngày tạo</th>
                        <th scope="col">Ngày cập nhật</th>
                        <td colspan="2"></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="category" items="${responseCategory}">
                    <tr>
                        <th scope="row">${category.id}</th>
                        <td><span> ${category.name} </span></td>
                        <td><span> ${category.created_at}</span></td>
                        <td><span> ${category.updated_at} </span></td>
                        <td>
                            <button type="button" data-bs-toggle="modal" data-bs-target="#edit-book"
                                    class="btn btn-info">Edit
                            </button>
                        </td>
                        <td>
                            <button type="button" data-bs-toggle="modal" data-bs-target="#delete-book"
                                    class="btn btn-danger">Delete
                            </button>

                            <div class="modal fade" id="delete-book" tabindex="-1" aria-labelledby="delete-label"
                                 aria-hidden="true">
                                <form method="POST" action="categories?action=delete&slug=${category.slug}"
                                      class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="delete-label">Modal title</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            Are you sure to delete that ?
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                Close
                                            </button>
                                            <button type="submit" class="btn btn-danger">Delete</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </div>


    <!-- Modal delete start-->

    <!-- Modal delete end-->
</div>


</body>
</html>
