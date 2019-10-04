<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CyberWikia</title>
  </head>
  <body>
  <t:Footer></t:Footer>
    <div class="container">
      <div class="row">
        <div class="col-md">
          <a href="players.html">Players</a>
        </div>
        <div class="col-md">
          <a href="teams.html">Teams</a>
        </div>
        <div class="col-md">
          <a href="tournaments.html">Tournaments</a>
        </div>
        <div class="col-md">
          <a href="registration.html">Registration</a>
        </div>
        <div class="col-md">
          <a href="create.html">Create team</a>
        </div>
      </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="<c:url value="../../js/bootstrap.min.js"/>"></script>
  </body>
</html>
