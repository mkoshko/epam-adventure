<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<c:set var="overview" value="tournamentform.overview" scope="page"/>
<c:set var="name" value="tournamentform.name" scope="page"/>
<c:set var="startDate" value="tournament.startdate" scope="page"/>
<c:set var="endDate" value="tournament.enddate" scope="page"/>
<c:set var="prize" value="tournament.prize" scope="page"/>
<c:set var="create" value="form.button.create" scope="page"/>
<c:set var="optional" value="form.optional" scope="page"/>
<c:set var="required" value="form.required" scope="page"/>
<c:set var="save" value="form.button.save" scope="page"/>

<c:set var="saveAction" value="savetournament.html" scope="page"/>

<tag:html localizedTitle="title.edittournament">
    <fmt:bundle basename="localization">
        <jsp:useBean id="tournament" scope="request" type="by.koshko.cyberwikia.bean.Tournament"/>
        <form action="${saveAction}" method="post"
              enctype="multipart/form-data">
            <tag:errors/>
            <div class="row pt-2">
                <div class="col-12 col-xl-8">
                    <label for="overview"><fmt:message
                            key="${overview}"/></label>
                    <textarea class="w-100" rows="25" id="overview"
                              name="overview"
                              placeholder="(<fmt:message key="${optional}"/>)">${tournament.overview}</textarea>
                </div>
                <div class="col-sm-12 col-md-8 col-xl-4 pl-md-0">
                    <label for="name"><fmt:message
                            key="${name}"/></label>
                    <input class="form-control mb-2" id="name" type="text"
                           name="name"
                           placeholder="(<fmt:message key="${required}"/>)"
                           value="${tournament.name}">
                    <div class="card mb-3">
                        <div class="mx-auto">
                            <img class="card-img-top" src="<c:url value="${tournament.logoFile}"/>" alt="${tournament.name}"/>
                        </div>
                        <div class="card-body p-3">
                            <input class="custom-file" type="file"
                                   name="tournament-logo"/>
                            <table class="table">
                                <tr>
                                    <label class="mb-0"
                                           for="start-date"><fmt:message
                                           key="${startDate}"/>:</label>
                                    <input class="form-control mb-1" type="date"
                                           id="start-date"
                                           placeholder="(<fmt:message key="${required}"/>)"
                                           name="startDate"
                                           value="${tournament.startDate}">
                                </tr>
                                <tr>
                                    <label class="mb-0"
                                           for="end-date"><fmt:message
                                            key="${endDate}"/>:</label>
                                    <input class="form-control mb-1" type="date"
                                           id="end-date"
                                           placeholder="(<fmt:message key="${required}"/>)"
                                           name="endDate"
                                           value="${tournament.endDate}">
                                </tr>
                                <tr>
                                    <label class="mb-0" for="prize"><fmt:message
                                            key="${prize}"/></label>
                                    <input class="form-control mb-1"
                                           type="number" id="prize"
                                           placeholder="(<fmt:message key="${optional}"/>)"
                                           name="prize"
                                           value="${tournament.prize}">
                                </tr>
                            </table>
                            <input type="hidden" name="from"
                                   value="edittournament.html?id=${tournament.id}">
                            <input type="hidden" name="id" value="${tournament.id}">
                            <button type="submit" class="btn btn-dark w-100">
                                <fmt:message key="${save}"/></button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </fmt:bundle>
</tag:html>