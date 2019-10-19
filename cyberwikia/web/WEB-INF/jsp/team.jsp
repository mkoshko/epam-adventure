<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<c:set var="viewPlayerAction" value="player.html?id="/>
<c:set var="leaveTeamAction" value="leave.html"/>
<c:set var="joinTeamAction" value="join.html"/>
<c:set var="kickPlayer" value="kickplayer.html"/>

<tag:html title="${team.name}">
    <fmt:bundle basename="localization">
        <tag:errors/>
        <div class="row pt-2">
            <div class="col-12 col-xl-8">
                <jsp:useBean id="team" scope="request" type="by.koshko.cyberwikia.bean.Team"/>
                <tag:overview text="${team.overview}"/>
                <tag:activeplayers/>
                <tag:formerplayers/>
            </div>
            <div class="col-sm-12 col-md-8 col-xl-4 mx-auto">
                <div class="card">
                    <h1 class="card-header text-center">${team.name}</h1>
                    <img class="card-img-top p-1" src="<c:url value="${team.logoFile}"/>" alt="${team.name}">
                    <div class="card-body p-1">
                        <table class="table table-striped">
                            <tbody>
                                <tr>
                                    <td class="text-right"><fmt:message key="team.location"/></td>
                                    <td><img class="flag" src="<c:url value="${team.country.flag}"/>" alt="flag">${team.country.name}</td>
                                </tr>
                                <tr>
                                    <td class="text-right"><fmt:message key="team.captain"/></td>
                                        <c:choose>
                                            <c:when test="${team.captain != null}">
                                                <td>
                                                    <img class="flag" src="<c:url value="${team.captain.country.flag}"/>" alt="flag"/>
                                                    <a href="${viewPlayerAction}${team.captain.id}">${team.captain.nickname}</a>
                                                </td>
                                            </c:when>
                                            <c:otherwise><td></td></c:otherwise>
                                        </c:choose>
                                </tr>
                                <tr>
                                    <td class="text-right"><fmt:message key="team.coach"/></td>
                                    <c:choose>
                                        <c:when test="${team.coach != null}">
                                            <td>
                                                <img class="flag" src="<c:url value="${team.coach.country.flag}"/>" alt="flag"/>
                                                <a href="${viewPlayerAction}${team.coach.id}">${team.coach.nickname}</a>
                                            </td>
                                        </c:when>
                                        <c:otherwise><td></td></c:otherwise>
                                    </c:choose>
                                </tr>
                                <tr>
                                    <td class="text-right"><fmt:message key="team.label.game"/></td>
                                    <td>${team.game.title}</td>
                                </tr>
                            </tbody>
                        </table>
                        <c:if test="${user != null}">
                            <jsp:useBean id="activeTeamId" scope="request" type="java.lang.Long"/>
                            <div class="row">
                                <c:choose>
                                    <c:when test="${activeTeamId == team.id}">
                                        <div class="col-12 mx-auto">
                                            <form action="${leaveTeamAction}" method="post">
                                                <input type="hidden" name="team" value="${team.id}">
                                                <button class="btn btn-dark w-100" type="submit"><fmt:message key="team.button.leave"/></button>
                                            </form>
                                        </div>
                                    </c:when>
                                    <c:when test="${activeTeamId > 0 && activeTeamId != team.id}">
                                        <div class="col-12 mx-auto">
                                            <form action="${joinTeamAction}" method="post">
                                                <input type="hidden" name="team" value="${team.id}">
                                                <button class="btn btn-dark w-100" disabled><fmt:message key="team.button.join"/></button>
                                            </form>
                                        </div>
                                    </c:when>
                                    <c:when test="${activeTeamId == 0}">
                                        <div class="col-12">
                                            <form action="${joinTeamAction}" method="post">
                                                <input type="hidden" name="team" value="${team.id}">
                                                <button class="btn btn-dark w-100" type="submit"><fmt:message key="team.button.join"/></button>
                                            </form>
                                        </div>
                                    </c:when>
                                </c:choose>
                            </div>
                        </c:if>
                    </div>
                </div>
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
                        <td><fmt:message key="team.tournament.prize"/></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${team.tournaments}" var="tournament">
                        <tr class="<c:choose>
                            <c:when test="${tournament.placement == 1}">gold</c:when>
                            <c:when test="${tournament.placement == 2}">silver</c:when>
                            <c:when test="${tournament.placement == 3}">bronze</c:when>
                        </c:choose>">
                            <td>${tournament.tournament.endDate}</td>
                            <td>${tournament.placement}</td>
                            <td>${tournament.tournament.name}</td>
                            <td>$<fmt:formatNumber value="${tournament.tournament.prize}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </fmt:bundle>
</tag:html>