<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="func" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
    <div class="col-12" style="padding: 0">
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="index.html">CyberWikia</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="players.html">Players</a>
                </li>
                <c:choose>
                    <c:when test="${sessionScope.get('user') != null}">
                        <span class="navbar-text">${user.login}</span>
                        <li class="nav-item">
                            <a class="nav-link" href="signout.html">log out</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item nav-form">
                            <form class="form-inline" id="form" action="signin.html" method="post">
                                <label for="login" class="mr-sm-2 text-white">Login:</label>
                                <input type="text" class="form-control mb-2 mr-sm-2" id="login" name="login">
                                <label for="pwd" class="mr-sm-2 text-white">Password:</label>
                                <input type="password" class="form-control mb-2 mr-sm-2" id="pwd" name="password">
                                <button type="submit" id="submit-button" class="btn btn-primary mb-2" style="width: 100px">
                                    <span class="spinner-grow spinner-grow-sm" id="btn-spinner" style="display: none; align-content: center"></span>
                                    <span id="btn-text" class="text-center" style="display: block">Sign In</span>
                                </button>
                            </form>
                        </li>

                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
    </div>
</div>