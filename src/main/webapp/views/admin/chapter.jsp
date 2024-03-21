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
            <h1> Chapter API</h1>
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#add-chapter">
                Add Chapter
            </button>

            <div class="modal fade" id="add-chapter" tabindex="-1" aria-labelledby="add-book-modal" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <form class="modal-content" method="POST" action="chapter">
                        <div class="modal-header">
                            <h5 class="modal-title" id="add-book-modal">Add Chapter</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="mb-3">
                                <label for="title" class="form-label">Title</label>
                                <input name="title" type="text" class="form-control" id="title" placeholder="Title...">
                            </div>
                            <div class="mb-3">
                                <label for="data" class="form-label">Name</label>
                                <input name="data" type="text" class="form-control" id="data" placeholder="Data...">
                            </div>
                            <div class="mb-3">
                                <label for="select-book" class="form-label">Book</label>
                                <select name="slug-book" id="select-book">
                                    <c:forEach var="book" items="${responseBook}">--%>
                                        <option value="${book.slug}">${book.title}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="chapter-index" class="form-label">Chapter Index</label>
                                <input name="chapter-index" type="text" class="form-control" id="chapter-index"
                                       placeholder="Chapter Index...">
                            </div>
                            <div class="mb-3">
                                <label for="audio-url" class="form-label">Audio Url</label>
                                <input name="audio-url" type="text" class="form-control" id="audio-url"
                                       placeholder="Audio Url...">
                            </div>
                            <div class="mb-3">
                                <label for="summary" class="form-label">Summary</label>
                                <input name="summary" type="text" class="form-control" id="summary"
                                       placeholder="Summary...">
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
            <c:if test="${not empty responseChapter}">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Title</th>
                        <th scope="col">Data</th>
                        <th scope="col">Book</th>
                        <th scope="col">Chapter Index</th>
                        <th scope="col">Audio Url</th>
                        <th scope="col">Summary</th>
                        <th scope="col">Date created</th>
                        <td colspan="2"></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="chapter" items="${responseChapter}">
                    <tr>
                        <th scope="row">${chapter.id}</th>
                        <td><span> ${chapter.title} </span></td>
                        <td><span> ${chapter.data}</span></td>
                        <td><span> ${chapter.book.title} </span></td>
                        <td><span> ${chapter.chapterIndex} </span></td>
                        <td><span> ${chapter.summary} </span></td>
                        <td><span> ${chapter.audioUrl} </span></td>
                        <td><span> ${chapter.created_at} </span></td>
                        <td>
                            <button type="button" data-bs-toggle="modal" data-bs-target="#edit-chapter-${chapter.slug}"
                                    class="btn btn-info">Edit
                            </button>

                            <div class="modal fade" id="edit-chapter-${chapter.slug}" tabindex="-1"
                                 aria-labelledby="add-book-modal" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered">
                                    <form class="modal-content" method="POST"
                                          action="chapter?action=update&slug=${chapter.slug}">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="edit-book-modal">Edit
                                                chapter ${chapter.name}</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="mb-3">
                                                <label for="titleEdit" class="form-label">Title</label>
                                                <input value="${chapter.title}" name="title" type="text"
                                                       class="form-control" id="titleEdit"
                                                       placeholder="Title...">
                                            </div>
                                            <div class="mb-3">
                                                <label for="dataEdit" class="form-label">Name</label>
                                                <input value="${chapter.data}" name="data" type="text"
                                                       class="form-control" id="dataEdit"
                                                       placeholder="Data...">
                                            </div>
                                            <div class="mb-3">
                                                <label for="select-book-edit" class="form-label">Book</label>
                                                <select name="slug-book" id="select-book-edit">
                                                    <c:forEach var="book" items="${responseBook}">--%>
                                                        <option ${chapter.book.slug == book.slug ? 'selected' : ''}
                                                                value="${book.slug}">${book.title}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <label for="chapter-index-edit" class="form-label">Chapter Index</label>
                                                <input value="${chapter.chapterIndex}" name="chapter-index" type="text"
                                                       class="form-control"
                                                       id="chapter-index-edit"
                                                       placeholder="Chapter Index...">
                                            </div>
                                            <div class="mb-3">
                                                <label for="audio-url-edit" class="form-label">Audio Url</label>
                                                <input value="${chapter.audioUrl}" name="audio-url" type="text"
                                                       class="form-control"
                                                       id="audio-url-edit"
                                                       placeholder="Audio Url...">
                                            </div>
                                            <div class="mb-3">
                                                <label for="summary-edit" class="form-label">Summary</label>
                                                <input value=${chapter.summary} name="summary" type="text"
                                                       class="form-control" id="summary-edit"
                                                       placeholder="Summary...">
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
                            <button type="button" data-bs-toggle="modal"
                                    data-bs-target="#delete-chapter-${chapter.slug}"
                                    class="btn btn-danger">Delete
                            </button>

                            <div class="modal fade" id="delete-chapter-${chapter.slug}" tabindex="-1"
                                 aria-labelledby="delete-label"
                                 aria-hidden="true">
                                <form method="POST" action="chapter?action=delete&slug=${chapter.slug}"
                                      class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="delete-label">Delete
                                                chapter ${chapter.name}</h5>
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
