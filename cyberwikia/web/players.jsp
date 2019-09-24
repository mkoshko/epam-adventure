<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 9/23/19
  Time: 4:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Players list</title>
</head>
<body>
<table>
    <c:forEach items="${players}" var="player">

        <tr>
            <td><c:url value="images/${player.id}"></c:url></td>
                <%--            <td><a href="player/${player.id}.html">${player.nickname}--%>
                <%--                - ${player.firstName} ${player.lastName}</a></td>--%>
        </tr>

    </c:forEach>
</table>
</body>
</html>
