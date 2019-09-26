<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 9/26/19
  Time: 1:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="WEB-INF/css/styles.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${player.nickname} - Profile</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <h3>Overview</h3><br>
            <p>${player.overview}</p>
        </div>
        <div class="col-md-4">
            <div class="card">
                <img src="<c:url value="../${player.profilePhoto}"/>"
                     class="card-img-top" alt="${player.nickname}">
                <div class="card-body">
                    <h3 class="card-title">Player information</h3>
                    <p class="card-text"><b>Full
                        name:</b> ${player.firstName} ${player.lastName}</p>
                    <p class="card-text"><b>Nickname:</b> ${player.nickname}</p>
                    <p class="card-text">
                        <b>Birth:</b> ${player.birth.toString()}</p>
                    <p class="card-text"><b>Nationality:</b> <img
                            src="<c:url value="../${player.country.flag}"/>">${player.country.name}
                    </p>
                    <p class="card-text">
                        <b>Games:</b>
                        <c:forEach items="${player.playerTeams}" var="playerTeam">
                            ${playerTeam.team.game.title}
                        </c:forEach>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8">
            <table class="table">
                <thead>
                <tr>
                    <td>
                        Active team
                    </td>
                    <td>
                        Join date
                    </td>
                    <td>
                        Leave date
                    </td>
                </tr>
                </thead>
                <c:forEach items="${player.playerTeams}" var="team">
                    <c:if test="${team.active == true}">
                        <tr>
                            <td>
                                <a href="/team/${team.team.id}.html">${team.team.name}</a>
                            </td>
                            <td>
                                    ${team.joinDate.toString()}
                            </td>
                            <td>
                                    ${team.leaveDate.toString()}
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8">
            <table class="table">
                <thead>
                <tr>
                    <td>
                        Team history
                    </td>
                    <td>
                        Join date
                    </td>
                    <td>
                        Leave date
                    </td>
                </tr>
                </thead>
                <c:forEach items="${player.playerTeams}" var="team">
                    <c:if test="${team.active == false}">
                        <tr>
                            <td>
                                    <a href="../team/${team.team.id}.html">${team.team.name}</a>
                            </td>
                            <td>
                                    ${team.joinDate.toString()}
                            </td>
                            <td>
                                    ${team.leaveDate.toString()}
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</html>