<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="teams" rtexprvalue="true" type="java.util.List<by.koshko.cyberwikia.bean.Team>" %>
<table class="table mt-3">
    <thead>
    <tr>
        <td><fmt:message key="title.found"/>: ${teams.size()}</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${teams}" var="team">
        <tr><td><a href="team.html?id=${team.id}">${team.name}</a></td></tr>
    </c:forEach>
    </tbody>
</table>