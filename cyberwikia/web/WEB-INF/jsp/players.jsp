<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ct" uri="cybertag" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<c:url value="../../css/styles.css"/> ">
    <link rel="stylesheet" href="<c:url value="../../css/bootstrap.min.css"/> ">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Players</title>
</head>
<body style="background-image: url('<c:url value="../../images/background/default.jpg"/>')">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <ct:player-list players="${players}"/>
<%--            <table class="table">--%>
<%--                <c:forEach items="${players}" var="player">--%>
<%--                    <tr>--%>
<%--                        <td><a href="profile.html?id=${player.id}">${player.firstName} ${player.nickname} ${player.lastName}</a></td>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
<%--            </table>--%>
            </div>
        </div>
    </div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</html>
