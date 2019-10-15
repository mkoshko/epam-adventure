<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<c:set var="savePlayerAction" value="saveplayer.html" scope="page"/>

<tag:html title="${player.nickname}">
    <fmt:bundle basename="localization">
        <jsp:useBean id="player" scope="request" type="by.koshko.cyberwikia.bean.Player"/>
        <form class="mb-2" action="${savePlayerAction}" method="post" enctype="multipart/form-data">
            <c:if test="${errors != null}">
                <div class="row my-1">
                    <c:forEach items="${errors}" var="error">
                        <div class="alert alert-warning alert-dismissible w-100">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <fmt:message key="${error}"/>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        <div class="row pt-2">
            <div class="col-12 col-xl-8">
                <h3><fmt:message key="title.overview"/></h3>
                <textarea class="w-100 form-control text-black" rows="20" placeholder="<fmt:message key="editplayer.overview"/>" name="overview">${player.overview}</textarea>
            </div>
            <div class="col-sm-12 col-md-8 col-xl-4 pl-md-0">
                <input class="form-control mt-2 mb-2" type="text" name="nickname" placeholder="<fmt:message key="editplayer.nickname"/>" value="${player.nickname}">
                <div class="card">
                    <div>
                        <img class="card-img-top" src="<c:choose>
                        <c:when test="${player.profilePhoto != null}"><c:url value="${player.profilePhoto}"/></c:when>
                        <c:otherwise><c:url value="/images/upload/_default.png"/></c:otherwise>
                        </c:choose>" alt="${player.nickname}">
                    </div>
                    <div class="card-body p-1">
                        <input class="custom-file" type="file" datatype="" name="profilePhoto">
                        <table class="table">
                            <tr>
                                <input class="form-control mb-1" type="text" placeholder="<fmt:message key="editplayer.firstname"/>"
                                       name="firstname" value="${player.firstName}">
                            </tr>
                            <tr>
                                <input class="form-control mb-1" type="text" placeholder="<fmt:message key="editplayer.lastname"/>"
                                       name="lastname" value="${player.lastName}">
                            </tr>
                            <tr>
                                <select class="custom-select mb-1" name="country">
                                    <c:forEach items="${countries}" var="country">
                                        <option value="${country.id}" <c:if test="${player.country.id == country.id}">selected</c:if>>${country.name}</option>
                                    </c:forEach>
                                </select>
                            </tr>
                            <tr>
                                <input class="form-control" type="date" name="birth" value="${player.birth}">
                            </tr>
                        </table>
                        <input type="hidden" name="from" value="player.html?id=${player.id}">
                        <button type="submit" class="btn btn-dark w-100"><fmt:message key="player.save"/></button>
                    </div>
                </div>
            </div>
        </div>
        </form>
    </fmt:bundle>
</tag:html>