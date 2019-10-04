<%@tag language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="players" rtexprvalue="true" required="true"
              type="java.util.List<by.koshko.cyberwikia.bean.Player>" %>
<c:forEach items="${players}" var="player">
    <tr>
        <td>
            <a href="profile.html?id=${player.id}"><img class="bordered" src="<c:url value="${player.country.flag}"/>"/>${player.firstName} ${player.nickname} ${player.lastName}</a>
        </td>
    </tr>
</c:forEach>
