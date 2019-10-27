<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="text" required="false" rtexprvalue="true" %>

<c:if test="${errors != null}">
        <c:forEach items="${errors}" var="error">
            <div class="alert alert-warning alert-dismissible m-4">
                <button type="button" class="close" data-dismiss="alert" id="btn">&times;</button>
                <fmt:message key="${error}"/>
            </div>
        </c:forEach>
    ${sessionScope.remove('errors')}
</c:if>