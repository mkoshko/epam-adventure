<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" tagdir="/WEB-INF/tags" %>
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
        <div class="col-sm-12 col-lg-7">
            <h3>Overview</h3><br>
            <p>${team.overview}</p>
        </div>
        <div class="col-12 col-lg-5">
            <div class="card">
                <b><h2 class="card-title text-center">${team.name}</h2></b>
                <img src="<c:url value="${team.logoFile}"/>"
                     class="card-img-top" alt="${team.name}">
                <div class="card-body">
                    <b><h3 class="card-title text-center">TEAM INFO</h3></b>
                    <table class="table table-striped">
                        <tbody>
                        <tr>
                            <td class="td-right"><b>Location</b></td>
                            <td><img class="bordered"
                                     src="<c:url value="${team.country.flag}"/>">
                                <b>${team.country.name}</b></td>
                        </tr>
                        <tr>
                            <td class="td-right"><b>Captain</b></td>
                            <td><img class="bordered"
                                     src="<c:url value="${team.captain.country.flag}"/>"> ${team.captain.firstName} '${team.captain.nickname}' ${team.captain.lastName}</td>
                        </tr>
                        <tr>
                            <td class="td-right"><b>Coach</b></td>
                            <td><img class="bordered"
                                     src="<c:url value="${team.coach.country.flag}"/>"> ${team.coach.firstName} '${team.coach.nickname}' ${team.coach.lastName}</td>
                        </tr>
                        <tr>
                            <td class="td-right"><b>Game</b></td>
                            <td>${team.game.title}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <h3>Active squad</h3>
            <form action="join.html" method="post">
                <input type="hidden" value="${team.id}" name="id"/>
                <button class="btn btn-light" type="submit" />
            </form><br>
            <ct:activeplayers playerTeams="${team.players}"/>
        </div>
    </div>
    <div class="row" style="margin-top: 20px">
        <div class="col-sm-12 m">
            <h3>Former players</h3>
            <ct:formerplayers playerTeams="${team.players}"></ct:formerplayers>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <table class="table">
                <thead>
                <tr>
                    <td style="width: 130px" class="td-center">Date</td>
                    <td style="width: 130px" class="td-center">Placement</td>
                    <td class="td-center">Tournament</td>
                    <td class="td-center">Prize</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${team.tournaments}" var="tournament">
                    <c:choose>
                        <c:when test="${tournament.placement == 1}">
                            <tr style="background-color: #FFD739">
                        </c:when>
                        <c:when test="${tournament.placement == 2}">
                            <tr style="background-color: #BEBEBE">
                        </c:when>
                        <c:when test="${tournament.placement == 3}">
                            <tr style="background-color: #9a5a0d">
                        </c:when>
                        <c:otherwise>
                            <tr>
                        </c:otherwise>
                    </c:choose>
                    <td>${tournament.tournament.endDate}</td>
                    <td class="td-center"><b>${tournament.placement}</b></td>
                    <td>${tournament.tournament.name}</td>
                    <td class="td-center">$${tournament.tournament.prize}</td>
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
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</html>
