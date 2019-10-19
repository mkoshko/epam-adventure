<%@ tag language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="localization">
    <h2 class="mt-5"><fmt:message key="team.former"/></h2>
    <table class="table">
        <thead>
        <tr>
            <td><fmt:message key="label.nickname"/></td>
            <td><fmt:message key="label.fullName"/></td>
            <td><fmt:message key="label.joinDate"/></td>
            <td><fmt:message key="label.leaveDate"/></td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${team.players}" var="player">
            <c:if test="${!player.active}">
                <tr>
                    <td>
                        <img class="flag"
                             src="<c:url value="${player.player.country.flag}"/>"
                             alt="flag"/>
                        <a href="player.html?id=${player.player.id}">${player.player.nickname}</a>
                    </td>
                    <td>${player.player.firstName} ${player.player.lastName}</td>
                    <td>${player.joinDate}</td>
                    <td>${player.leaveDate}</td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
</fmt:bundle>