<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="func" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="title" required="true" rtexprvalue="true" type="java.lang.String" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title}</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-12 col-lg-10 mx-auto content">
            <jsp:doBody/>
        </div>
    </div>
</div>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</body>
</html>