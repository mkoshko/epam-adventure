<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2 class="mt-2 mt-lg-4"><fmt:message key="team.achievements"/></h2>
<table class="table">
    <thead>
    <tr>
        <td><fmt:message key="team.tournament.date"/></td>
        <td><fmt:message key="team.tournament.placement"/></td>
        <td><fmt:message key="team.tournament.name"/></td>
        <td><fmt:message key="player.team"/></td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${player.tournaments}" var="tournament">
        <tr class="<c:choose>
                            <c:when test="${tournament.placement == 1}">gold</c:when>
                            <c:when test="${tournament.placement == 2}">silver</c:when>
                            <c:when test="${tournament.placement == 3}">bronze</c:when>
                            </c:choose>">
            <td>${tournament.tournament.endDate}</td>
            <td>${tournament.placement}</td>
            <td><c:out
                    value="${tournament.tournament.name}"/></td>
            <td><img class="small-icon"
                     src="<c:url value="${tournament.team.logoFile}"/>"
                     alt="${tournament.team.country.name}"><a
                    href="team.html?id=${tournament.team.id}">${tournament.team.name}</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>