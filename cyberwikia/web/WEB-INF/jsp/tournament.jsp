<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<tag:html localizedTitle="title.tournaments">
    <fmt:bundle basename="localization">
        <div class="row">
            <jsp:useBean id="tournament" scope="request" type="by.koshko.cyberwikia.bean.Tournament"/>
            <tag:overview text="${tournament.overview}"/>
            <tag:card>
                <div class="card-header text-center"><h3>${tournament.name}</h3></div>
                <img class="card-img-top p-1" src="<c:url value="${tournament.logoFile}"/>" alt="${tournament.name}"/>
                <div class="card-body p-1">
                    <tag:card-table>
                        <tr>
                            <td class="text-right"><fmt:message key="tournament.startdate"/></td>
                            <td>${tournament.startDate}</td>
                        </tr>
                        <tr>
                            <td class="text-right"><fmt:message key="tournament.enddate"/></td>
                            <td>${tournament.endDate}</td>
                        </tr>
                        <tr>
                            <td class="text-right"><fmt:message key="tournament.prize"/></td>
                            <td>${tournament.prize}</td>
                        </tr>
                    </tag:card-table>
                </div>
            </tag:card>
        </div>
        <c:set var="partisipants" value="${tournament.participants}"/>
        <div class="row">
            <div class="col-12">
                <h2><fmt:message key="tournament.participants"/></h2>
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
                                <img class="small-icon" src="<c:url value="${participant.team.logoFile}"/>" alt="${participant.team.name}"/>
                                <c:out value="${participant.team.name}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </fmt:bundle>
</tag:html>