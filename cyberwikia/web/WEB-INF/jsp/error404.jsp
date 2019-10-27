<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<tag:html>
    <fmt:bundle basename="localization">
        <div class="row">
            <div class="col-12" style="min-height: 450px">
                <div class="text-center my-5">
                    <h1 class="text-center">Page not found</h1>
                    <a href="index.html" class="text-center"><fmt:message key="error.500.tryagain"/></a>
                </div>
            </div>
        </div>
    </fmt:bundle>
</tag:html>