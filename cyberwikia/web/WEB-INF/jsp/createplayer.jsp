<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<c:set var="createPlayerAction" value="createplayer.html" scope="page"/>

<tag:html localizedTitle="title.createplayer">
    <fmt:bundle basename="localization">
        <tag:errors/>
        <form class="mb-2" action="${createPlayerAction}" method="POST"
              enctype="multipart/form-data">
            <div class="row pt-2">
                <div class="col-12 col-xl-8">
                    <textarea class="w-100 form-control text-black mt-2" rows="26"
                              placeholder="<fmt:message key="editplayer.overview"/>"
                              name="overview">${player.overview}</textarea>
                </div>
                <div class="col-sm-12 col-md-8 col-xl-4 pl-md-0">
                    <input class="form-control mt-2 mb-2" type="text"
                           name="nickname"
                           placeholder="<fmt:message key="editplayer.nickname"/>"
                           value="${player.nickname}">
                    <div class="card">
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
                                   name="profilePhoto"/>
                            <table class="table">
                                <tr>
                                    <input class="form-control mb-1" type="text"
                                           placeholder="<fmt:message key="editplayer.firstname"/>"
                                           name="firstname">
                                </tr>
                                <tr>
                                    <input class="form-control mb-1" type="text"
                                           placeholder="<fmt:message key="editplayer.lastname"/>"
                                           name="lastname">
                                </tr>
                                <tr>
                                    <label class="m-0 mt-1" for="nationality"><fmt:message key="player.nationality"/></label>
                                    <select class="custom-select mb-1"
                                            name="country" id="nationality">
                                        <c:forEach items="${countries}"
                                                   var="country">
                                            <option value="${country.id}">${country.name}</option>
                                        </c:forEach>
                                    </select>
                                </tr>
                                <tr>
                                    <label class="m-0 mt-1" for="birthday"><fmt:message key="player.birth"/></label>
                                    <input class="form-control" type="date"
                                           placeholder="date of birth"
                                           name="birth" id="birthday">
                                </tr>
                            </table>
                            <input type="hidden" name="from"
                                   value="player.html?id=${user.id}">
                            <button type="submit" class="btn btn-dark w-100">
                                <fmt:message key="player.save"/></button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </fmt:bundle>
</tag:html>