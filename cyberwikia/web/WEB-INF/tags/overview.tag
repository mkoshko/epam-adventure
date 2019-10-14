<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="text" required="false" rtexprvalue="true" %>

<fmt:bundle basename="localization">
    <div class="col-12 col-xl-8">
        <h3><fmt:message key="title.overview"/></h3>
    <c:out value="${text}"/>
    </div>
</fmt:bundle>