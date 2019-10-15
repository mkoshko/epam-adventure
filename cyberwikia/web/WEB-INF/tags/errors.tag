<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="text" required="false" rtexprvalue="true" %>

<c:if test="${errors != null}">
    <div class="row m-3" id="err">
        <c:forEach items="${errors}" var="error">
            <div class="alert alert-warning alert-dismissible w-100">
                <button type="button" class="close" data-dismiss="alert" id="btn">&times;</button>
                <fmt:message key="${error}"/>
            </div>
        </c:forEach>
    </div>
    ${sessionScope.remove('errors')}
</c:if>