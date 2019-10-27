<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<c:set var="editTournamentLink" value="edittournament.html?id=" scope="page"/>
<c:set var="edit" value="button.edit" scope="page"/>
<c:set var="addParticipantAction" value="addparticipant.html" scope="page"/>
<c:set var="setPlacementAction" value="setplacement.html" scope="page"/>
<c:set var="removeParticipantAction" value="removeparticipant.html" scope="page"/>

<jsp:useBean id="tournament" scope="request"
             type="by.koshko.cyberwikia.bean.Tournament"/>
<c:set var="partisipants" value="${tournament.participants}"/>
<c:set var="moderator"
       value="${user.role.toString().equals('EVENT_MODERATOR')}"/>

<tag:html localizedTitle="title.tournaments">
    <fmt:bundle basename="localization">
        <tag:errors/>
        <div class="row pt-2">
            <div class="col-12 col-xl-8">
                <tag:overview text="${tournament.overview}"/>
                <h2 class="mt-4">
                    <fmt:message key="tournament.participants"/>
                    <c:if test="${moderator}">
                        <button class="btn btn-dark" data-toggle="modal" data-target="#addParticipant">
                            <fmt:message key="tournament.button.addteam"/>
                        </button>
                    </c:if>
                </h2>
                <table class="table">
                    <thead>
                    <tr>
                        <td><fmt:message key="label.team"/></td>
                        <td><fmt:message key="team.tournament.placement"/></td>
                        <c:if test="${moderator}"><td></td></c:if>
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
                            <td>${participant.placement != 0 ? participant.placement : "TBD"}</td>
                            <c:if test="${moderator}">
                                <td class="text-right">
                                    <div class="dropdown dropleft">
                                        <button type="button" class="btn p-0" data-toggle="dropdown">
                                            <tag:menuIcon/>
                                        </button>
                                        <div class="dropdown-menu">
                                            <button class="btn btn-link placement" for="${participant.team.id}" type="submit" data-toggle="modal" data-target="#setPlacement">
                                                <fmt:message key="tournament.title.setPlacement"/>
                                            </button>
                                            <form action="${removeParticipantAction}" method="post">
                                                <input type="hidden" name="tournament" value="${tournament.id}">
                                                <input type="hidden" name="from" value="${from}">
                                                <input type="hidden" name="team" value="${participant.team.id}">
                                                <button class="btn btn-link" type="submit"><fmt:message key="tournament.title.removeparticipant"/></button>
                                            </form>

                                        </div>
                                    </div>
                                </td>
                            </c:if>
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

        <c:if test="${moderator}">
            <div class="modal fade" id="addParticipant">
                <div class="modal-dialog modal-sm modal-dialog-centered">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title"><fmt:message key="tournament.title.addparticipant"/></h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">
                            <form action="${addParticipantAction}" method="post" id="addForm">
                                <input type="hidden" name="tournament" value="${tournament.id}">
                                <input type="hidden" name="from" value="${from}">
                                <input class="form-control" type="text" name="teamName" placeholder="<fmt:message key="tournament.addparticipant.hint"/>">
                            </form>
                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer text-center">
                            <button form="addForm" type="submit" class="btn btn-dark w-100"><fmt:message key="tournament.button.addteam"/></button>
                        </div>

                    </div>
                </div>
            </div>

            <div class="modal fade" id="setPlacement">
                <div class="modal-dialog modal-sm modal-dialog-centered">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title"><fmt:message key="tournament.title.setPlacement"/></h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">
                            <form action="${setPlacementAction}" method="post" id="setPlacementForm">
                                <input type="hidden" name="tournament" value="${tournament.id}">
                                <input type="hidden" name="team" id="team" value="">
                                <input type="hidden" name="from" value="${from}">
                                <input class="form-control" id="input-placement" type="number" min="0" name="placement">
                            </form>
                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer text-center">
                            <button form="setPlacementForm" type="submit" class="btn btn-dark w-100"><fmt:message key="tournament.button.addteam"/></button>
                        </div>

                    </div>
                </div>
            </div>
        </c:if>
    </fmt:bundle>
</tag:html>