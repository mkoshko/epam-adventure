<%@ tag language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<c:set var="kickPlayer" value="kickplayer.html"/>
<c:set var="makeCaptain" value="makecaptain.html"/>
<c:set var="makeCoach" value="makecoach.html"/>

<fmt:bundle basename="localization">
    <h2 class="mt-5"><fmt:message key="team.active"/></h2>
    <table class="table table-responsive-sm table-hover">
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
                        <td class="text-right">
                            <div class="dropdown dropleft">
                                <button type="button" class="btn p-0" data-toggle="dropdown">
                                    <tag:menuIcon/>
                                </button>
                                <div class="dropdown-menu">
                                    <form action="${makeCaptain}" method="post">
                                        <input type="hidden" name="id" value="${player.player.id}">
                                        <input type="hidden" name="from" value="${from}">
                                        <button class="btn btn-link" type="submit">Make captain</button>
                                    </form>
                                    <form action="${makeCoach}" method="post">
                                        <input type="hidden" name="id" value="${player.player.id}">
                                        <input type="hidden" name="from" value="${from}">
                                        <button class="btn btn-link" type="submit">Make coach</button>
                                    </form>
                                    <form action="${kickPlayer}" method="post">
                                        <input type="hidden" name="id" value="${player.player.id}">
                                        <input type="hidden" name="from" value="${from}">
                                        <button class="btn btn-link" type="submit">Kick player</button>
                                    </form>
                                </div>
                            </div>
                        </td>
                    </c:if>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
</fmt:bundle>