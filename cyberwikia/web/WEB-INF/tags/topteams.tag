<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table class="table table-bordered table-striped">
    <tbody>
        <c:forEach items="${teams}" var="team">
            <tr>
                <td class="text-center"><img class="small-icon" src="<c:url value="${team.logoFile}"/>">
                        <a href="team.html?id=${team.id}">${team.name}</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>