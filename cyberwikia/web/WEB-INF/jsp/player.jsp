<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<tag:html title="${player.nickname}">
    <fmt:bundle basename="localization">
        <jsp:useBean id="player" scope="request" type="by.koshko.cyberwikia.bean.Player"/>
        <div class="row pt-2">
            <div class="col-12 col-xl-8">
                <h3><fmt:message key="title.overview"/></h3>
                ${player.overview}
            </div>
            <div class="col-sm-12 col-md-8 col-xl-4 mx-auto">
                <div class="card">
                    <h1 class="card-title text-center">${player.nickname}</h1>
                    <img class="card-img-top" src="
                    <c:choose>
                        <c:when test="${player.profilePhoto != null}"><c:url value="${player.profilePhoto}"/></c:when>
                        <c:otherwise><c:url value="/images/upload/_default.png"/></c:otherwise>
                    </c:choose>
                    " alt="${player.nickname}">
                    <div class="card-body">
                        <table class="table table-striped">
                            <tr>
                                <td class="td-right"><fmt:message key="player.name"/></td>
                                <td>${player.firstName} ${player.lastName}</td>
                            </tr>
                            <tr>
                                <td class="td-right"><fmt:message key="player.nationality"/></td>
                                <td><img class="flag" src="<c:url value="${player.country.flag}"/>" alt="${player.country.name}">${player.country.name}</td>
                            </tr>
                            <tr>
                                <td class="td-right"><fmt:message key="player.birth"/></td>
                                <td>${player.birth}</td>
                            </tr>
                            <tr>
                                <td class="td-right"><fmt:message key="player.team"/></td>
                                <c:forEach items="${player.playerTeams}" var="playerTeam">
                                    <c:if test="${playerTeam.active}">
                                        <td><img class="small-icon" src="<c:url value="${playerTeam.team.logoFile}"/>" alt="${playerTeam.team.country.name}"><a href="team.html?id=${playerTeam.team.id}">${playerTeam.team.name}</a></td>
                                    </c:if>
                                </c:forEach>
                            </tr>
                        </table>
                        <c:if test="${sessionScope.get('user') != null && user.id == player.id}">
                            <a href="editprofile.html?id=${player.id}" class="btn btn-dark btn-full-width"><fmt:message key="player.edit"/></a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <h2><fmt:message key="player.teamhistory"/></h2>
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
                                <td><img class="small-icon" src="<c:url value="${playerTeam.team.logoFile}"/>" alt="${playerTeam.team.country.name}"><a href="team.html?id=${playerTeam.team.id}">${playerTeam.team.name}</a></td>
                                <td>${playerTeam.joinDate}</td>
                                <td>${playerTeam.leaveDate}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <h2><fmt:message key="team.achievements"/></h2>
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
                            <td>${tournament.tournament.name}</td>
                            <td><img class="small-icon" src="<c:url value="${tournament.team.logoFile}"/>" alt="${tournament.team.country.name}"><a href="team.html?id=${tournament.team.id}">${tournament.team.name}</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </fmt:bundle>
</tag:html>