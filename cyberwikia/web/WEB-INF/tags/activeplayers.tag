<%@ tag language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="playerTeams" required="true" rtexprvalue="true"
              type="java.util.List<by.koshko.cyberwikia.bean.PlayerTeam>" %>
<f:setLocale value="${cookie.get('locale').value}"/>
<f:setBundle basename="label"/>
<table class="table table-bordered table-responsive">
    <thead class="table-light">
    <tr>
        <td><f:message key="label.nickname"/></td>
        <td><f:message key="label.fullName"/></td>
        <td><f:message key="label.joinDate"/></td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${playerTeams}" var="playerTeam">
        <c:if test="${playerTeam.active == true}">
            <tr>
                <td><img class="bordered" src="<c:url value="${playerTeam.player.country.flag}"/>"
                         alt="${playerTeam.player.country.name}">
                        ${playerTeam.player.nickname}</td>
                <td>${playerTeam.player.firstName} ${playerTeam.player.lastName}</td>
                <td>${playerTeam.joinDate}</td>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>
