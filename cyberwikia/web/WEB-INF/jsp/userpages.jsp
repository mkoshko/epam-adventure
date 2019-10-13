<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<c:set var="createTeamAction" value="createteam.html"/>
<tag:html>
    <fmt:bundle basename="localization">
        <div class="row">
            <div class="col-12 col-sm-6">
                <c:choose>
                    <c:when test="${team == null}">
                        <div class="card">
                            <img class="card-img-top" src="">
                        </div>
                        <div class="card-body">
                            <a href="${createTeamAction}" class="btn btn-dark m-auto"></a>
                        </div>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </fmt:bundle>
</tag:html>