<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="localization">
<div class="row">
    <div class="col-12 col-sm-12" style="padding: 0">
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="index.html"><fmt:message key="title.cyberwikia"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="players.html"><fmt:message key="title.players"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="teams.html"><fmt:message key="title.teams"/></a>
                </li>
                <c:choose>
                    <c:when test="${sessionScope.get('user') != null}">
                        <div class="nav-user">
                            <span class="navbar-text">${user.login}</span>
                        <li class="nav-item">
                            <form action="signout.html" method="post">
                                <input type="hidden" name="from" value="${from}">
                                <button type="submit" class="btn btn-link"><fmt:message key="label.logout"/></button>
                            </form>
                        </li>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="registration.html"><fmt:message key="header.registration"/></a>
                        </li>
                        <li class="nav-item nav-form">
                            <form class="form-inline" id="form" action="signin.html" method="post">
                                <label for="login" class="mr-sm-2 mb-sm-2 text-white"><fmt:message key="label.login"/>:</label>
                                <input type="text" class="form-control mb-2 mr-sm-2" id="login" name="login">
                                <label for="pwd" class="mr-sm-2 mb-sm-2 text-white"><fmt:message key="label.password"/>:</label>
                                <input type="password" class="form-control mb-2 mr-sm-2" id="pwd" name="password">
                                <input type="hidden" name="from" value="${from}">
                                <button type="submit" id="submit-button" class="btn btn-primary mb-2 login-btn">
                                    <span class="spinner-grow login-spinner" id="btn-spinner"></span>
                                    <span id="btn-text" class="text-center" style="display: block"><fmt:message key="label.signin"/></span>
                                </button>
                            </form>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
    </div>
</div>
</fmt:bundle>