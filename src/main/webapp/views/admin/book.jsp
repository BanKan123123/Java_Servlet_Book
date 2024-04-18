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
    <link href="${contextPath}/views/assets/css/css-web/book_list.scss" rel="stylesheet"/>

</head>
<body>
<div class="book_list">
    <%@include file="/views/common/dash-board.jsp" %>

    <div class="main-book">
        <div class="main-title">
            <h1>Books List</h1>

            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#add-book">
                Add Book
            </button>

            <div class="modal fade" id="add-book" tabindex="-1" aria-labelledby="add-book-modal" aria-hidden="true">
                <div class="modal-dialog">
                    <form class="modal-content" method="POST" action="book">
                        <div class="modal-header">
                            <h5 class="modal-title" id="add-book-modal">Add Book</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="mb-3">
                                <label for="title" class="form-label">Title</label>
                                <input required name="title" type="text" class="form-control" id="title"
                                       placeholder="Title...">
                            </div>
                            <div class="mb-3">
                                <label for="description" class="form-label">Description</label>
                                <input required name="description" type="text" class="form-control" id="description">
                            </div>

                            <div class="mb-3">
                                <label for="author" class="form-label">Author</label>
                                <select required name="author" id="author" data-placeholder="Choose author"
                                        class="form-select"
                                        aria-label="Default select example">
                                    <c:forEach var="author" items="${responseAuthor}">
                                        <option value="${author.slug}">${author.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="select-categories" class="form-label">Categories</label>
                                <select required name="category" id="select-categories" multiple>
                                    <c:forEach var="category" items="${responseCategory}">--%>
                                        <option value="${category.slug}">${category.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="quantity" class="form-label">Quantity</label>
                                <input required name="quantity" type="text" class="form-control" id="quantity">
                            </div>

                            <div class="mb-3">
                                <label for="rate" class="form-label">Rate</label>
                                <input required name="rate" type="text" class="form-control" id="rate">
                            </div>
                            <div class="mb-3">
                                <label for="liked" class="form-label">Liked</label>
                                <input required name="liked" type="text" class="form-control" id="liked">
                            </div>
                            <div class="mb-3">
                                <label class="form-label" for="image-thumbnail">Image Thumbnail</label>
                                <input required name="image-thumbnail" type="file" class="form-control"
                                       id="image-thumbnail">
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
            <form style="display: flex; align-items: center" method="GET" action="book">
                <div>
                    <label for="search"></label>
                    <input style="width: 90%; " name="search" placeholder="Search..." type="text" class="form-control"
                           id="search">
                </div>
                <button type="submit" class="btn btn-primary">Tìm kiếm</button>
            </form>
            <c:if test="${not empty responseBook}">
                <table class="table table-striped table-hover">
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
                        <th colspan="2"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="book" items="${responseBook}">
                    <tr>
                        <th scope="row">1</th>
                        <td><img alt="image-book" src="${book.imageThumbnail}"></td>
                        <td><span> ${book.title} </span></td>
                        <td><span> ${book.description}</span></td>
                        <td><span> ${book.authors.name}</span></td>
                        <td>
                            <c:forEach var="category" items="${book.categories}" varStatus="loop">
                                <span>${category.name}</span>
                                <c:if test="${!loop.last}">
                                    <span>,</span>
                                </c:if>
                            </c:forEach>
                        </td>
                        <td><span> ${book.rate} </span></td>
                        <td><span> ${book.liked} </span></td>
                        <td><span> ${book.quantity} </span></td>
                        <td>
                            <button type="button" data-bs-toggle="modal" data-bs-target="#edit-book-${book.slug}"
                                    class="btn btn-info">Edit
                            </button>

                            <div class="modal fade" id="edit-book-${book.slug}" tabindex="-1"
                                 aria-labelledby="add-book-modal" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered">
                                    <form class="modal-content" method="POST"
                                          action="book?action=update&slug=${book.slug}">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="edit-book-modal">Edit
                                                category ${book.title}</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="mb-3">
                                                <label for="titleEdit" class="form-label">Title</label>
                                                <input required value="${book.title}" name="title" type="text"
                                                       class="form-control" id="titleEdit"
                                                       placeholder="Title...">
                                            </div>
                                            <div class="mb-3">
                                                <label for="descriptionEdit" class="form-label">Description</label>
                                                <input required value="${book.description}" name="description"
                                                       type="text"
                                                       class="form-control"
                                                       id="descriptionEdit">
                                            </div>

                                            <div class="mb-3">
                                                <label for="authorEdit" class="form-label">Author</label>
                                                <select required name="author" id="authorEdit"
                                                        data-placeholder="Choose author"
                                                        class="form-select"
                                                        aria-label="Default select example">
                                                    <c:forEach var="author" items="${responseAuthor}">
                                                        <option ${book.authors.name == author.name ? 'selected' : ''}
                                                                value="${author.slug}">${author.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label for="select-categories-edit"
                                                       class="form-label">Categories</label>
                                                <select required name="category" id="select-categories-edit" multiple>
                                                    <c:forEach var="category" items="${responseCategory}">--%>
                                                        <option ${book.categories.contains(category) ? 'selected' : ''}
                                                                value="${category.slug}">${category.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label for="quantity-edit" class="form-label">Quantity</label>
                                                <input required value="${book.quantity}" name="quantity" type="text"
                                                       class="form-control"
                                                       id="quantity-edit">
                                            </div>

                                            <div class="mb-3">
                                                <label for="rate-edit" class="form-label">Rate</label>
                                                <input required value="${book.rate}" name="rate" type="text"
                                                       class="form-control"
                                                       id="rate-edit">
                                            </div>
                                            <div class="mb-3">
                                                <label for="liked-edit" class="form-label">Liked</label>
                                                <input required value="${book.liked}" name="liked" type="text"
                                                       class="form-control" id="liked-edit">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label" for="image-thumbnail">Image Thumbnail</label>

                                                <input value="${book.imageThumbnail}" name="image-thumbnail"
                                                       type="file"
                                                       class="form-control"
                                                       id="image-thumbnail-edit">
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                Close
                                            </button>
                                            <button name="_method" type="submit" class="btn btn-primary">Edit</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </td>
                        <td>
                            <button type="button" data-bs-toggle="modal" data-bs-target="#delete-book-${book.slug}"
                                    class="btn btn-danger">Delete
                            </button>

                            <div class="modal fade" id="delete-book-${book.slug}" tabindex="-1"
                                 aria-labelledby="delete-label"
                                 aria-hidden="true">
                                <form method="POST" action="book?action=delete&slug=${book.slug}"
                                      class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="delete-label">Delete
                                                category ${book.title}</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            Are you sure want to delete that ?
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
</div>


</body>

</html>
