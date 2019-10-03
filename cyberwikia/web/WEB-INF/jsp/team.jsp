<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Team - ${team.name}</title>
</head>
<body>
    <div class="container" style="background-color: white">
        <div class="row">
            <div class="col-md-8">
                <h3>Overview</h3><br>
                <p>${team.overview}</p>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <img src="<c:url value="${team.logoFile}"/>"
                         class="card-img-top" alt="${team.name}">
                    <div class="card-body">
                        <h3 class="card-title">Team info</h3>
                        <p class="card-text">Team name: ${team.name}</p>
                        <p class="card-text">Team location: <img
                                src="<c:url value="${team.country.flag}"/>"> ${team.country.name}</p>
                        <p class="card-text">Team captain: <img src="<c:url value="${team.captain.country.flag}"/>"> ${team.captain.firstName} ${team.captain.nickname} ${team.captain.lastName}</p>
                        <p class="card-text">Team coach: <img src="<c:url value="${team.coach.country.flag}"/>"> ${team.coach.firstName} ${team.coach.nickname} ${team.coach.lastName}</p>
                        <p class="card-text">Team game: ${team.game.title}</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <h3>Active squad</h3><br>
                <table class="table">
                    <thead>
                        <tr>
                            <td>ID</td>
                            <td>Name</td>
                            <td>Join Date</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${team.players}" var="playerTeam">
                            <tr>
                                <td>${playerTeam.player.nickname}</td>
                                <td>${playerTeam.player.firstName} ${playerTeam.player.lastName}</td>
                                <td>${playerTeam.joinDate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="<c:url value="../../js/bootstrap.min.js"/>"></script>
</html>
