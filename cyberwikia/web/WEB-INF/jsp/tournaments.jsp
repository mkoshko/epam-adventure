<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<c:set var="tournamentPageAction" value="tournament.html?id="/>
<c:if test="${tournaments != null}">
    <jsp:useBean id="tournaments" scope="request" type="java.util.List<by.koshko.cyberwikia.bean.Tournament>"/>
</c:if>

<tag:html localizedTitle="title.tournaments">
    <fmt:bundle basename="localization">
        <div class="row">
            <div class="col-12 p-1">
                <h2><fmt:message key="title.tournaments"/></h2>
            </div>
            <div class="col-12">
                <table class="table p-1">
                    <thead>
                        <tr>
                            <td><fmt:message key="title.tournament"/></td>
                            <td><fmt:message key="tournament.startdate"/></td>
                            <td><fmt:message key="tournament.enddate"/></td>
                            <td><fmt:message key="tournament.prize"/></td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${tournaments}" var="tournament">
                            <tr>
                                <td><a href="${tournamentPageAction}${tournament.id}"><c:out value="${tournament.name}"/></a></td>
                                <td>${tournament.startDate}</td>
                                <td>${tournament.endDate}</td>
                                <td>$ <fmt:formatNumber value="${tournament.prize}"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <tag:pagination page="${page}" lastPage="${lastPage}" pageURL="tournaments"/>
    </fmt:bundle>
</tag:html>