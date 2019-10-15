<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<tag:html localizedTitle="title.createplayer">
    <fmt:bundle basename="localization">
        <form class="mb-2" action="${savePlayerAction}" method="post"
              enctype="multipart/form-data">
            <c:if test="${errors != null}">
                <div class="row my-1">
                    <c:forEach items="${errors}" var="error">
                        <div class="alert alert-warning alert-dismissible w-100">
                            <button type="button" class="close"
                                    data-dismiss="alert">&times;
                            </button>
                            <fmt:message key="${error}"/>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
            <div class="row pt-2">
                <div class="col-12 col-xl-8">
                    <h3><fmt:message key="title.overview"/></h3>
                    <textarea class="w-100 form-control text-black" rows="20"
                              placeholder="<fmt:message key="editplayer.overview"/>"
                              name="overview">${player.overview}</textarea>
                </div>
                <div class="col-sm-12 col-md-8 col-xl-4 pl-md-0">
                    <input class="form-control mt-2 mb-2" type="text"
                           name="nickname"
                           placeholder="<fmt:message key="editplayer.nickname"/>"
                           value="${player.nickname}">
                    <div class="card">
                        <div>
                            <svg viewBox="0 0 24 24" width="250" height="250"
                                 stroke="currentColor" stroke-width="2"
                                 fill="none" stroke-linecap="round"
                                 stroke-linejoin="round" class="css-i6dzq1 mx-5">
                                <rect x="3" y="3" width="18" height="18" rx="2"
                                      ry="2"></rect>
                                <circle cx="8.5" cy="8.5" r="1.5"></circle>
                                <polyline points="21 15 16 10 5 21"></polyline>
                            </svg>
                        </div>
                        <div class="card-body p-1">
                            <input class="custom-file" type="file" datatype=""
                                   name="profilePhoto">
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
                                    <select class="custom-select mb-1"
                                            name="country">
                                        <c:forEach items="${countries}"
                                                   var="country">
                                            <option value="${country.id}">${country.name}</option>
                                        </c:forEach>
                                    </select>
                                </tr>
                                <tr>
                                    <input class="form-control" type="date"
                                           name="birth">
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