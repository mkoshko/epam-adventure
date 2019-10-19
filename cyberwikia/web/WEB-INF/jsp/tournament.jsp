<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<c:set var="editTournamentLink" value="edittournament.html?id=" scope="page"/>
<c:set var="edit" value="button.edit" scope="page"/>

<jsp:useBean id="tournament" scope="request"
             type="by.koshko.cyberwikia.bean.Tournament"/>
<c:set var="partisipants" value="${tournament.participants}"/>
<c:set var="moderator"
       value="${user.role.toString().equals('EVENT_MODERATOR')}"/>

<tag:html localizedTitle="title.tournaments">
    <fmt:bundle basename="localization">
        <div class="row pt-2">
            <div class="col-12 col-xl-8">
                <tag:overview text="${tournament.overview}"/>
                <h2 class="mt-4"><fmt:message
                        key="tournament.participants"/></h2>
                <table class="table">
                    <thead>
                    <tr>
                        <td><fmt:message key="label.team"/></td>
                        <td><fmt:message key="team.tournament.placement"/></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${partisipants}" var="participant">
                        <tr>
                            <td>
                                <img class="small-icon"
                                     src="<c:url value="${participant.team.logoFile}"/>"
                                     alt="${participant.team.name}"/>
                                <a href="team.html?id=${participant.team.id}"><c:out
                                        value="${participant.team.name}"/></a>
                            </td>
                            <td>${participant.placement}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-sm-12 col-md-8 col-xl-4">
                <div class="card mb-2">
                    <div class="card-header text-center"><h3><c:out
                            value="${tournament.name}"/></h3></div>
                    <c:choose>
                        <c:when test="${tournament.logoFile != null}">
                            <img class="card-img-top p-1"
                                 src="<c:url value="${tournament.logoFile}"/>"
                                 alt="${tournament.name}"/>
                        </c:when>
                        <c:otherwise>
                            <tag:emptyImage/>
                        </c:otherwise>
                    </c:choose>
                    <div class="card-body p-1">
                        <table class="table table-striped">
                            <tr>
                                <td class="text-right"><fmt:message
                                        key="tournament.startdate"/></td>
                                <td>${tournament.startDate}</td>
                            </tr>
                            <tr>
                                <td class="text-right"><fmt:message
                                        key="tournament.enddate"/></td>
                                <td>${tournament.endDate}</td>
                            </tr>
                            <tr>
                                <td class="text-right"><fmt:message
                                        key="tournament.prize"/></td>
                                <td>$ <fmt:formatNumber
                                        value="${tournament.prize}"/></td>
                            </tr>
                        </table>
                        <c:if test="${moderator}">
                            <a class="btn btn-dark w-100"
                               href="${editTournamentLink}${tournament.id}"><fmt:message
                                    key="${edit}"/></a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </fmt:bundle>
</tag:html>