<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<c:set var="editPlayerAction" value="editplayer.html"/>
<c:set var="defaultImage" value="/images/upload/_default.png"/>

<jsp:useBean id="player" scope="request"
             type="by.koshko.cyberwikia.bean.Player"/>

<tag:html title="${player.nickname}">
    <fmt:bundle basename="localization">
        <div class="row pt-2">
            <div class="col-12 col-xl-8">
                <tag:overview text="${player.overview}"/>
                <tag:recentteam/>
                <tag:achievements/>
            </div>
            <div class="col-12 col-xl-4">
                <div class="card mb-2">
                    <h1 class="card-header text-center">${player.nickname}</h1>
                    <img class="card-img-top p-1" src="
                    <c:choose>
                        <c:when test="${player.profilePhoto != null}"><c:url value="${player.profilePhoto}"/></c:when>
                        <c:otherwise><c:url value="${defaultImage}"/></c:otherwise>
                    </c:choose>
                    " alt="${player.nickname}">
                    <div class="card-body p-1">
                        <table class="table table-striped">
                            <tr>
                                <td class="text-right"><fmt:message key="player.name"/></td>
                                <td><c:out value="${player.firstName} ${player.lastName}"/></td>
                            </tr>
                            <tr>
                                <td class="text-right"><fmt:message key="player.nationality"/></td>
                                <td><img class="flag" src="<c:url value="${player.country.flag}"/>"
                                         alt="${player.country.name}">${player.country.name}
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right"><fmt:message key="player.birth"/></td>
                                <td>${player.birth}</td>
                            </tr>
                            <tr>
                                <td class="text-right"><fmt:message key="player.team"/></td>
                                <c:forEach items="${player.playerTeams}" var="playerTeam">
                                    <c:if test="${playerTeam.active}">
                                        <td><img class="small-icon"
                                                 src="<c:url value="${playerTeam.team.logoFile}"/>"
                                                 alt="${playerTeam.team.country.name}"><a
                                                href="team.html?id=${playerTeam.team.id}">${playerTeam.team.name}</a>
                                        </td>
                                    </c:if>
                                </c:forEach>
                            </tr>
                        </table>
                        <c:if test="${sessionScope.get('user') != null && user.id == player.id}">
                            <a href="${editPlayerAction}"
                               class="btn btn-dark w-100"><fmt:message
                                    key="player.edit"/></a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </fmt:bundle>
</tag:html>