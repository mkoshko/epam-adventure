<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="players" rtexprvalue="true" type="java.util.List<by.koshko.cyberwikia.bean.Player>" %>
<table class="table mt-3">
    <thead>
        <tr>
            <td><fmt:message key="title.found"/>: ${players.size()}</td>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${players}" var="player">
            <tr><td><a href="player.html?id=${player.id}">${player.nickname}</a></td></tr>
        </c:forEach>
    </tbody>
</table>