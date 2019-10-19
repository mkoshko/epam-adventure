<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<c:set var="from" value="createteamform.html"/>

<tag:html>
    <fmt:bundle basename="localization">
        <div class="row pt-2">
            <%-- Overview area --%>
            <div class="col-12 col-xl-8">
                <textarea class="w-100 form-control mb-2 mt-2"
                          name="overview"
                          rows="23"
                          placeholder="<fmt:message key="teamform.overview"/> (<fmt:message key="form.optional"/>)"></textarea>
            </div>
            <%-- Card area --%>
            <div class="col-sm-12 col-md-8 col-xl-4 pl-md-0">
                <input class="w-100 form-control mt-2 mb-2"
                       type="text"
                       name="name"
                       placeholder="<fmt:message key="teamform.name"/> (<fmt:message key="form.required"/>)">
                <div class="card mb-2">
                    <div class="mx-auto">
                        <svg viewBox="0 0 24 24" width="250" height="250"
                             stroke="currentColor" stroke-width="2"
                             fill="none" stroke-linecap="round"
                             stroke-linejoin="round" class="css-i6dzq1 ">
                            <rect x="3" y="3" width="18" height="18" rx="2"
                                  ry="2"></rect>
                            <circle cx="8.5" cy="8.5" r="1.5"></circle>
                            <polyline points="21 15 16 10 5 21"></polyline>
                        </svg>
                    </div>
                    <div class="card-body p-3">
                        <input class="custom-file" type="file"
                               name="teamLogo"/>
                        <table class="table">
                            <tr>
                                <label class="m-0 mt-1" for="location"><fmt:message key="teamform.location"/></label>
                                <select class="custom-select mb-1"
                                        name="country" id="location">
                                    <c:forEach items="${countries}"
                                               var="country">
                                        <option value="${country.id}">${country.name}</option>
                                    </c:forEach>
                                </select>
                            </tr>
                            <tr>
                                <label class="m-0 mt-1" for="game"><fmt:message key="teamform.game"/></label>
                                <select class="custom-select mb-1"
                                        name="game" id="game">
                                    <c:forEach items="${games}"
                                               var="game">
                                        <option value="${game.id}">${game.title}</option>
                                    </c:forEach>
                                </select>
                            </tr>
                        </table>
                        <input type="hidden" name="from"
                               value="${from}">
                        <button type="submit" class="btn btn-dark w-100">
                            <fmt:message key="form.button.create"/></button>
                    </div>
                </div>
            </div>
        </div>
    </fmt:bundle>
</tag:html>