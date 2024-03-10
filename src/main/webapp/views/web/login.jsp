<%@include file="/views/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title>Đăng nhập</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style>
        body {
            margin: 0;
            padding: 0;
            background-color: #17a2b8;
            height: 100vh;
        }

        #login .container #login-row #login-column #login-box {
            margin: 0 auto;
            margin-top: 120px;
            max-width: 400px;

            border-radius: 8px;
            border: 1px solid #9C9C9C;
            background-color: #EAEAEA;
        }

        #login .container #login-row #login-column #login-box #login-form {
            padding: 20px;
        }

        #login .container #login-row #login-column #login-box #login-form #register-link {
            margin-top: -85px;
        }

        .form-custom {
            display: flex;
            justify-content: space-between;
        }

        #register-link {
            margin-top: 25px !important;
        }

        .btn-custom {
            width: 100%;
        }

        .text {
            margin-bottom: 16px;
        }
    </style>
</head>

<body>
<div id="login">
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">
                    <form id="login-form" class="form" action="<c:url value='/login?action=login'/>" method="post">

                        <h3 class="text-center text-info text">Đăng nhập</h3>
                        <c:if test="${not empty message}">
                            <div class="alert alert-${alert}">
                                    ${message}
                            </div>
                        </c:if>
                        <div class="form-group">
                            <input type="text" placeholder="Email hoặc số điện thoại" name="username"
                                   id="username" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <input name="password" type="password" placeholder="Mật khẩu" id="password"
                                   class="form-control" required>
                        </div>
                        <div class="form-group form-custom">
                            <span>Chưa có tài khoản ?</span> <a href="register" class="text-info">Đăng ký </a>
                        </div>
                        <div id="register-link">
                            <input type="submit" name="submit" class="btn btn-info btn-md btn-custom"
                                   value="Đăng nhập">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>