<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2 class="mt-2 mt-lg-4"><fmt:message key="player.teamhistory"/></h2>
<table class="table">
    <thead>
    <tr>
        <td><fmt:message key="player.team"/></td>
        <td><fmt:message key="label.joinDate"/></td>
        <td><fmt:message key="label.leaveDate"/></td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${player.playerTeams}" var="playerTeam">
        <c:if test="${!playerTeam.active}">
            <tr>
                <td><img class="small-icon"
                         src="<c:url value="${playerTeam.team.logoFile}"/>"
                         alt="${playerTeam.team.country.name}"><a
                        href="team.html?id=${playerTeam.team.id}"><c:out
                        value="${playerTeam.team.name}"/></a>
                </td>
                <td>${playerTeam.joinDate}</td>
                <td>${playerTeam.leaveDate}</td>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>