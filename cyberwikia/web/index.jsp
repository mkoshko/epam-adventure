<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<c:url value="css/bootstrap.min.css"/>">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CyberWikia</title>
    <style>
      .border {
        display: inline-block;
        width: 70px;
        height: 70px;
        margin: 6px;
      }
      .content {
        margin: auto;
        width: 60%;
        border: 3px solid #73AD21;
        padding: 10px;
      }
    </style>
  </head>
  <body>
    <div class="container">
      <div class="row">
        <div class="col-md-3">
          <a href="players.html">Players</a>
        </div>
        <div class="col-md-3">
          <a href="teams.html">Teams</a>
        </div>
        <div class="col-md-3">
          <a href="tournaments.html">Tournaments</a>
        </div>
        <div class="col-md-3">
          <a href="registration.html">Registration</a>
        </div>
      </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="<c:url value="js/bootstrap.min.js"/>"></script>
  </body>
</html>
