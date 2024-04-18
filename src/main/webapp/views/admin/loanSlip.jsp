<%@include file="/views/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:useBean id="date" class="java.util.Date"/>
<html>
<head>
    <title>Loan Slip List</title>
    <link href="${contextPath}/views/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/views/assets/css/css-web/loan_slip.scss" rel="stylesheet"/>
    <script src="${contextPath}/views/assets/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="loan_slip_list">
    <%@include file="/views/common/dash-board.jsp" %>
    <div class="container">
        <div class="main-loan-slip">
            <div class="main-title">
                <h1 style="text-align: center">List of book borrowing cards</h1>

                <form method="GET" action="loan-slip">
                    <div class="mb-3">
                        <label for="search"></label>
                        <input name="search" placeholder="Search..." type="text" class="form-control" id="search">
                    </div>

                    <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                </form>

                <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#add-loan-slip">
                    Add
                </button>
                <div class="modal fade" id="add-loan-slip" tabindex="-1" aria-labelledby="add-loan-slip-modal"
                     aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <form class="modal-content" method="POST" action="loan-slip">
                            <div class="modal-header">
                                <h5 class="modal-title" id="add-loan-slip-modal">Add Loan Slip</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="mb-3">
                                    <%
                                        // Tạo số ngẫu nhiên từ 1 đến 999
                                        java.util.Random random = new java.util.Random();
                                        int randomValue = random.nextInt(999) + 1;

                                        // Lấy thời gian hiện tại
                                        java.util.Date now = new java.util.Date();

                                        // Trích xuất giờ, phút và giây
                                        int hours = now.getHours();
                                        int minutes = now.getMinutes();
                                        int seconds = now.getSeconds();

                                        int totalSum = hours + minutes + seconds + randomValue;

                                        // Tạo mã với định dạng LSHHMMSS
                                        String lsFormat = "LS" + totalSum;
                                    %>
                                    <%--                                    <input name="code" value="<%= lsFormat %>">--%>
                                    <input name="code" value="<%= lsFormat %>" hidden="hidden">
                                </div>
                                <div class="mb-3">
                                    <select name="account" class="width form-select">
                                        <option value="">Select username</option>
                                        <c:if test="${not empty responseAccount}">
                                            <c:forEach var="account" items="${responseAccount}">
                                                <option value="${account.id}">${account.username}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <select name="book" class="width form-select">
                                        <option value="">Choose the book title</option>
                                        <c:if test="${not empty responseBook}">
                                            <c:forEach var="book" items="${responseBook}">
                                                <option value="${book.id}">${book.title}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
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
        </div>
        <div>
            <c:if test="${not empty responseLoanSlip}">
                <table id="tblLoanSlip" class="table table-striped table-hove">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Code</th>
                        <th scope="col">Username</th>
                        <th scope="col">Number phone</th>
                        <th scope="col">Title book</th>
                        <th scope="col">Created at</th>
                        <td colspan="2"></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="loanSlip" items="${responseLoanSlip}" varStatus="loopStatus">
                    <tr>
                        <th scope="row">${loopStatus.index + 1}</th>
                        <td><span>${loanSlip.code}</span></td>
                        <td><span>${loanSlip.userName}</span></td>
                        <td><span>${loanSlip.numberPhone}</span></td>
                        <td><span>${loanSlip.title}</span></td>
                        <td><span>${loanSlip.created}</span></td>
                        <td>
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                                    data-bs-target="#edit-loan-slip-${loanSlip.id}">Edit
                            </button>
                            <div class="modal fade" id="edit-loan-slip-${loanSlip.id}" tabindex="-1"
                                 aria-labelledby="edit-loan-slip-modal" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered">
                                    <form class="modal-content" method="POST"
                                          action="loan-slip?action=update&id=${loanSlip.id}">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="edit-loan-modal">Edit Loan
                                                Slip ${loanSlip.code}</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="mb-3">
                                                    <%--                                                    <input name="code" value="<%= lsFormat %>" hidden="hidden">--%>
                                                <input name="code" value="${loanSlip.code}" hidden="hidden">
                                            </div>
                                            <div class="mb-3">
                                                <select name="account" class="width form-select" hidden="hidden">
                                                    <c:if test="${not empty responseAccount}">
                                                        <c:forEach var="account" items="${responseAccount}">
                                                            <option ${loanSlip.userName.equals(account.username) ? 'selected' : ''}
                                                                    value="${account.id}">${account.username}</option>
                                                        </c:forEach>
                                                    </c:if>
                                                </select>
                                            </div>
                                            <div class="mb-3">
                                                <select name="book" class="width form-select">
                                                    <option value="">Choose the book title</option>
                                                    <c:if test="${not empty responseBook}">
                                                        <c:forEach var="book" items="${responseBook}">
                                                            <option ${loanSlip.title.equals(book.title) ? 'selected' : ''}
                                                                    value="${book.id}">${book.title}</option>
                                                        </c:forEach>
                                                    </c:if>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                Close
                                            </button>
                                            <button name="_method" type="submit" class="btn btn-primary">Save</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </td>
                        <td>
                            <button type="button" class="btn btn-danger" data-bs-toggle="modal"
                                    data-bs-target="#delete-loan-slip-${loanSlip.id}">Delete
                            </button>
                            <div class="modal fade" id="delete-loan-slip-${loanSlip.id}" tabindex="-1"
                                 aria-labelledby="delete-label"
                                 aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered">
                                    <form class="modal-content" method="POST"
                                          action="loan-slip?action=delete&id=${loanSlip.id}">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="delete-loan-slip-modal">Confirm deletion</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <p>Do you want to delete this record?</p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                Close
                                            </button>
                                            <button name="_method" type="submit" class="btn btn-danger">Delete</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                    </c:forEach>
                </table>
            </c:if>
            <c:if test="${empty responseLoanSlip}">
                <%@include file="/views/common/not-found-data.jsp" %>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
