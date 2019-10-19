<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="text" required="false" rtexprvalue="true" %>

<fmt:bundle basename="localization">
    <h2><fmt:message key="title.overview"/></h2>
    <c:out value="${text}"/>
</fmt:bundle>