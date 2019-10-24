<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<tag:html>
    <fmt:bundle basename="localization">
        <div class="row">
            <div class="col-12">
                <h3 class="ml-2 mt-2"><fmt:message key="title.searchresult"/>: <c:out value="${searchString}"/></h3>
                <c:if test="${players != null}">
                    <tag:foundPlayers players="${players}"/>
                </c:if>
                <c:if test="${tournaments != null}">
                    <tag:foundTournaments tournaments="${tournaments}"/>
                </c:if>
                <c:if test="${teams != null}">
                    <tag:foundTeams teams="${teams}"/>
                </c:if>
            </div>
        </div>
    </fmt:bundle>
</tag:html>