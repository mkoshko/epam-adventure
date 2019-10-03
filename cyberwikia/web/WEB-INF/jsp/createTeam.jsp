<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<c:url value="../../css/bootstrap.min.css"/>">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Create team</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <form action="teamcreate.html" id="team-creation-form" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="team-name">Team name:</label>
                    <input type="text" id="team-name" class="form-control" name="team-name">
                </div>
                <div class="form-group">
                    <label for="team-name">Team location:</label>
                    <select id="country" name="country">
                        <c:forEach items="${countries}" var="country">
                            <option value="${country.id}">${country.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="team-logo">Logo file:</label>
                    <input type="file" id="team-logo" class="form-control" name="team-logo">
                </div>
                <button type="submit" class="btn btn-primary">CREATE</button>
            </form>
        </div>
    </div>
</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="<c:url value="../../js/bootstrap.min.js"/>"></script>
</html>
