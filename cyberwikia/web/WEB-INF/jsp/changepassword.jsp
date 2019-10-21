<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<c:set var="editPasswordAction" value="changepassword.html" scope="page"/>

<tag:html localizedTitle="title.changepassword">
    <fmt:bundle basename="localization">
        <div class="row pt-2">
            <div class="col-12 mx-auto p-3">
                <tag:errors/>
                <div class="text-center">
                    <form action="${editPasswordAction}" id="form" class="needs-validation form-signup mb-4" method="post" novalidate>
                        <h3 class="text-center"><fmt:message key="title.changepassword"/></h3>
                        <div class="form-group">
                            <label class="sr-only" for="oldPassword">Old password</label>
                            <input type="password" class="form-control" id="oldPassword" placeholder="<fmt:message key="form.oldpassword.hint"/>" name="oldPassword">
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="newPassword">Password</label>
                            <input type="password" class="form-control" id="newPassword" placeholder="<fmt:message key="form.newpassword.hint"/>" name="newPassword" required>
                            <div class="invalid-feedback"><fmt:message key="registration.password.feedback"/></div>
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="repeat-password">Repeat password</label>
                            <input type="password" class="form-control" id="repeat-password" placeholder="<fmt:message key="form.repeat-password.hint"/>" required>
                            <div class="invalid-feedback"><fmt:message key="registration.repeat-password.feedback"/></div>
                        </div>
                        <input type="hidden" name="from" value="${from}">
                        <button type="submit" class="btn btn-dark w-100"><fmt:message key="form.button.save"/></button>
                    </form>
                </div>
            </div>
        </div>
    </fmt:bundle>
</tag:html>