<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<tag:html localizedTitle="title.teams">
    <fmt:bundle basename="localization">
        <div class="row pt-2">
            <div class="col-12">
                <h2><fmt:message key="header.teams"/></h2>
                <table class="table table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <td><fmt:message key="label.team"/></td>
                        <td><fmt:message key="label.captain"/></td>
                        <td><fmt:message key="label.country"/></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${teams}" var="team">
                        <jsp:useBean id="team"
                                     type="by.koshko.cyberwikia.bean.Team"/>
                        <tr>
                            <td>
                                <a href="team.html?id=${team.id}">
                                    <img class="small-icon"
                                         src="<c:url value="${team.logoFile}"/>"
                                         alt="${team.name}"/>${team.name}
                                </a>
                            </td>
                            <td>
                                <c:if test="${team.captain != null}">
                                    <a href="player.html?id=${team.captain.id}">
                                        <img class="flag"
                                             src="<c:url value="${team.captain.country.flag}"/>"
                                             alt="${team.captain.country.name}"/>${team.captain.nickname}
                                    </a>
                                </c:if>
                                </td>
                            <td>
                                <img class="flag"
                                     src="<c:url value="${team.country.flag}"/>"
                                     alt="${team.country.name}"/>${team.country.name}
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <tag:pagination page="${page}" lastPage="${lastPage}" pageURL="teams"/>
    </fmt:bundle>
</tag:html>


