<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 9/23/19
  Time: 4:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Players list</title>
</head>
<body>
    <c:forEach items="${players}" var="player">
        <table>
            <tr>
                <td>

                </td>
            </tr>
        </table>
    </c:forEach>
</body>
</html>
