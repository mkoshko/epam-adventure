<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="tournaments" rtexprvalue="true" type="java.util.List<by.koshko.cyberwikia.bean.Tournament>" %>
<table class="table mt-3">
    <thead>
    <tr>
        <td><fmt:message key="title.found"/>: ${tournaments.size()}</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${tournaments}" var="tournament">
        <tr><td><a href="tournament.html?id=${tournament.id}">${tournament.name}</a></td></tr>
    </c:forEach>
    </tbody>
</table>