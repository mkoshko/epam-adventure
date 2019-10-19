<%@ tag language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="localization">
    <h2 class="mt-5"><fmt:message key="team.active"/></h2>
    <table class="table table-responsive-sm">
        <thead>
        <tr>
            <td><fmt:message key="label.nickname"/></td>
            <td><fmt:message key="label.fullName"/></td>
            <td><fmt:message key="label.joinDate"/></td>
            <c:if test="${user.id == team.creator.id}">
                <td></td>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${team.players}" var="player">
            <jsp:useBean id="player"
                         type="by.koshko.cyberwikia.bean.PlayerTeam"/>
            <c:if test="${player.active}">
                <tr>
                    <td>
                        <img class="flag"
                             src="<c:url value="${player.player.country.flag}"/>"
                             alt="flag"/>
                        <a href="player.html?id=${player.player.id}"> ${player.player.nickname}</a>
                    </td>
                    <td>${player.player.firstName} ${player.player.lastName}</td>
                    <td>${player.joinDate}</td>
                    <c:if test="${user.id == team.creator.id}">
                        <form action="${kickPlayer}" method="post">
                            <input type="hidden" value="${player.player.id}"
                                   name="id">
                            <input type="hidden" value="${from}" name="from">
                            <td class="p-lg-0 m-auto">
                                <button class="btn" type="submit">
                                    <svg viewBox="0 0 24 24" width="24"
                                         height="24" stroke="red"
                                         stroke-width="2"
                                         fill="none" stroke-linecap="round"
                                         stroke-linejoin="round"
                                         class="css-i6dzq1">
                                        <rect x="3" y="3" width="18" height="18"
                                              rx="2" ry="2"></rect>
                                        <line x1="9" y1="9" x2="15"
                                              y2="15"></line>
                                        <line x1="15" y1="9" x2="9"
                                              y2="15"></line>
                                    </svg>
                                </button>
                            </td>
                        </form>
                    </c:if>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
</fmt:bundle>