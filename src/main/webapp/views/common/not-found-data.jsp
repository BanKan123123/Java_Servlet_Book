<%@include file="/views/common/taglib.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
    <link href = "${contextPath}/views/assets/css/css-web/empty-notfound.scss" rel = "stylesheet">
</head>
<body>
<div class="empty-state">
    <div class="empty-state__content">
        <div class="empty-state__icon">
            <img src="https://static.vecteezy.com/ti/vecteur-libre/p3/5006031-no-result-data-document-or-file-not-found-concept-illustration-flat-design-vector-eps10-modern-graphic-element-for-landing-page-empty-state-ui-infographic-icon-etc-vectoriel.jpg"
                 alt="">
        </div>
        <div class="empty-state__message">No Data.</div>
    </div>
</div>
</body>
</html>
