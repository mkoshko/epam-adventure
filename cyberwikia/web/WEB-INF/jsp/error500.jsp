<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<tag:html>
    <fmt:bundle basename="localization">
        <div class="row">
            <div class="col-12 text-center">
                <p class="text-center mt-5" style="font-size: 35pt"><fmt:message key="error.500"/></p>
                <p class="text-center mb-5"><fmt:message key="error.500.motivationmessage"/> <a href="index.html"><fmt:message key="error.500.tryagain"/></a></p>
                <img class="image-container- image-container-sm image-container-md" src="<c:url value="/images/upload/sadpepe.png"/>"/>
            </div>
        </div>
    </fmt:bundle>
</tag:html>