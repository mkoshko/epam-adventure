<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<tag:html title="Players">
    <fmt:setLocale value="${cookie.get('locale').value}"/>
    <fmt:bundle basename="localization">
        <div class="row after-header">
            <div class="col-sm-12">
                <h2><fmt:message key="title.players"/></h2>
                <table class="table table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <td><fmt:message key="label.nickname"/></td>
                        <td><fmt:message key="label.fullName"/></td>
                        <td><fmt:message key="label.team"/></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${players}" var="player">
                        <jsp:useBean id="player"
                                     type="by.koshko.cyberwikia.bean.Player"/>
                        <c:set var="country" value="${player.country}"/>
                        <c:set var="team"
                               value="${player.playerTeams[0].team}"/>
                        <tr>
                            <td>
                                <a href="profile.html?id=${player.id}">
                                    <img class="flag"
                                         src="<c:url value="${country.flag}"/>"
                                         alt="${country.name}"/>${player.nickname}
                                </a>
                            </td>
                            <td>${player.firstName} ${player.lastName}</td>
                            <td><c:if test="${team != null}">
                                <img class="small-icon"
                                     src="<c:url value="${team.logoFile}"/>"/>
                                <a href="team.html?id=${team.id}"/>
                                ${team.name}
                            </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12 mx-auto">
                <ul class="pagination">
                    <li class="page-item">
                        <a class="page-link" href="players.html?page=">Previous</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="players.html?page=1">1</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="players.html?page=${lastPage}">${lastPage}</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="players.html?page=${page + 1}">Next</a>
                    </li>
                </ul>
            </div>
        </div>
    </fmt:bundle>
</tag:html>
