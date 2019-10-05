<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<tag:html title="Players">
    <fmt:setLocale value="${cookie.get('locale')}"/>
    <fmt:bundle basename="label">
        <div class="row">
            <h2><fmt:message key="title.players"/></h2>
            <hr>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <td><fmt:message key="label.nickname"/></td>
                        <td><fmt:message key="label.fullName"/></td>
                        <td><fmt:message key="label.team"/></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${players}" var="player">

                        <tr>
                            <td>
                                <a href="profile.html?id=${player.id}">
                                    <img class="flag"
                                         src="<c:url value="${player.country.flag}"/>"
                                         alt="${player.country.name}"/>${player.nickname}
                                </a>
                            </td>
                            <td>${player.firstName} ${player.lastName}</td>
                            <td><c:if test="${player.playerTeams[0] != null}">
                                <img class="small-icon"
                                     src="<c:url value="${player.playerTeams[0].team.logoFile}"/>"/>
                                <a href="team.html?id=${player.playerTeams[0].team.id}"/>
                                ${player.playerTeams[0].team.name}
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
                    <li class="page-item"><a class="page-link"
                                             href="players.html?page=">Previous</a>
                    </li>
                    <li class="page-item"><a class="page-link"
                                             href="players.html?page=">Next</a>
                    </li>
                </ul>
            </div>
        </div>
    </fmt:bundle>
</tag:html>
