<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<c:set var="signUpAction" value="signup.html"/>

<tag:html>
    <fmt:bundle basename="localization">
            <div class="row pt-2">
                <div class="col-12 mx-auto p-3">
                    <tag:errors/>
                    <div class="text-center">
                        <form action="${signUpAction}" id="form" class="needs-validation form-signup mb-4" method="post" novalidate>
                            <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="header.registration"/></h1>
                            <div class="form-group">
                                <label class="sr-only" for="login">Login</label>
                                <input type="text" class="form-control" id="login" placeholder="<fmt:message key="form.login.hint"/>" name="login" required>
                                <div class="invalid-feedback"><fmt:message key="registration.login.feedback"/></div>
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="login">Email</label>
                                <input type="email" class="form-control" id="email" placeholder="<fmt:message key="form.email.hint"/>" name="email" required>
                                <div class="invalid-feedback"><fmt:message key="registration.email.feedback"/></div>
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="password">Password</label>
                                <input type="password" class="form-control" id="password" placeholder="<fmt:message key="form.password.hint"/>" name="password" required>
                                <div class="invalid-feedback"><fmt:message key="registration.password.feedback"/></div>
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="repeat-password">Repeat password</label>
                                <input type="password" class="form-control" id="repeat-password" placeholder="<fmt:message key="form.repeat-password.hint"/>" required>
                                <div class="invalid-feedback"><fmt:message key="registration.repeat-password.feedback"/></div>
                            </div>
                            <button type="submit" class="btn btn-dark w-100"><fmt:message key="button.register"/></button>
                        </form>
                    </div>
                </div>
            </div>
    </fmt:bundle>
    <script src="<c:url value="/js/form-validation.js"/>"></script>
</tag:html>