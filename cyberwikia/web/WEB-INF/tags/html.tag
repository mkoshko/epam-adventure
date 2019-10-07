<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="func" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="localizedTitle" required="false" rtexprvalue="true" %>
<%@ attribute name="title" required="false" rtexprvalue="true" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<c:if test="${cookie.get('locale') != null}">
    <fmt:setLocale value="${cookie.get('locale').value}" scope="session"/>
</c:if>
<fmt:bundle basename="localization">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <c:choose>
        <c:when test="${localizedTitle != null}">
            <title><fmt:message key="${localizedTitle}"/></title>
        </c:when>
        <c:otherwise>
            <title>${title}</title>
        </c:otherwise>
    </c:choose>
    <title>${title}</title>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-12 col-xl-9 mx-auto content">
            <tag:header/>
            <jsp:doBody/>
        </div>
    </div>
    <div class="row">
        <div class="col-12 col-xl-9 mx-auto">
            <tag:footer/>
        </div>
    </div>
</div>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/js/loading.js"/>"></script>
</body>
</html>
</fmt:bundle>