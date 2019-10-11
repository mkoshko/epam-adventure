<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<tag:html>
    <fmt:bundle basename="localization">
        <div class="row after-header">
            <div class="col-12">
                <form action="signup.html" class="needs-validation" novalidate method="post">
                    <div class="form-group">
                        <label><fmt:message key="form.login"/>:</label>
                        <input type="text" class="form-control" placeholder="<fmt:message key="form.login.hint"/>" name="login" required>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill out this field.</div>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="form.email"/>:</label>
                        <input type="email" class="form-control" placeholder="<fmt:message key="form.email.hint"/>" name="email" required>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill out this field.</div>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="form.password"/>:</label>
                        <input type="password" class="form-control" placeholder="<fmt:message key="form.password.hint"/>" name="password" required>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill out this field.</div>
                    </div>
                    <button type="submit" class="btn btn-dark btn-full-width"><fmt:message key="header.registration"/></button>
                </form>
            </div>
        </div>
    </fmt:bundle>
    <script src="<c:url value="/js/form-validation.js"/>"></script>
</tag:html>