<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<tag:html title="${team.name}">
    <fmt:bundle basename="localization">
        <div class="row after-header">
            <div class="col-12 col-xl-8">
                <c:set var="team" value="${requestScope.get('team')}"/>
                <jsp:useBean id="team" type="by.koshko.cyberwikia.bean.Team"/>
                <h3><fmt:message key="title.overview"/></h3>
                ${team.overview}
            </div>
            <div class="col-sm-12 col-md-8 col-xl-4 mx-auto">
                <div class="card">
                    <h1 class="card-title text-center">${team.name}</h1>
                    <img class="card-img-top" src="<c:url value="${team.logoFile}"/>" alt="${team.name}">
                    <div class="card-body">
                        <table class="table table-striped">
                            <tbody>
                                <tr>
                                    <td class="td-right"><fmt:message key="team.location"/></td>
                                    <td><img class="flag" src="<c:url value="${team.country.flag}"/>">${team.country.name}</td>
                                </tr>
                                <tr>
                                    <td class="td-right"><fmt:message key="team.captain"/></td>
                                    <td><img class="flag" src="<c:url value="${team.captain.country.flag}"/> "/>${team.captain.nickname}</td>
                                </tr>
                                <tr>
                                    <td class="td-right"><fmt:message key="team.coach"/></td>
                                    <td><img class="flag" src="<c:url value="${team.coach.country.flag}"/> "/>${team.coach.nickname}</td>
                                </tr>
                                <tr>
                                    <td class="td-right"><fmt:message key="team.label.game"/></td>
                                    <td>${team.game.title}</td>
                                </tr>
                            </tbody>
                        </table>
                        <c:if test="${sessionScope.get('user') != null}">
                            <div class="row">
                                <div class="col-4">
                                    <form action="join.html" method="post">
                                        <input type="hidden" name="team" value="${team.id}">
                                        <button type="submit"><fmt:message key="team.button.join"/></button>
                                    </form>
                                </div>
                                <div class="col-5">
                                    <form action="leave.html" method="post">
                                        <input type="hidden" name="team" value="${team.id}">
                                        <button type="submit"><fmt:message key="team.button.leave"/></button>
                                    </form>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 30px">
            <div class="col-12">
                <h2><fmt:message key="team.active"/></h2>
                <table class="table">
                    <thead>
                    <tr>
                        <td><fmt:message key="label.nickname"/></td>
                        <td><fmt:message key="label.fullName"/></td>
                        <td><fmt:message key="label.joinDate"/></td>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${team.players}" var="player">
                            <jsp:useBean id="player" type="by.koshko.cyberwikia.bean.PlayerTeam"/>
                            <c:if test="${player.active}">
                                <tr>
                                    <td><img class="flag" src="<c:url value="${player.player.country.flag}"/> "/>${player.player.nickname}</td>
                                    <td>${player.player.firstName} ${player.player.lastName}</td>
                                    <td>${player.joinDate}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <h2><fmt:message key="team.former"/></h2>
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
                                    <td><img class="flag" src="<c:url value="${player.player.country.flag}"/> "/>${player.player.nickname}</td>
                                    <td>${player.player.firstName} ${player.player.lastName}</td>
                                    <td>${player.joinDate}</td>
                                    <td>${player.leaveDate}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </fmt:bundle>
</tag:html>