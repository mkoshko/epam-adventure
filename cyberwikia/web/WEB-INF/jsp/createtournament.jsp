<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<tag:html>
    <fmt:bundle basename="localization">
        <form action="createtournament.html" method="post"
              enctype="multipart/form-data">
            <tag:errors/>
            <div class="row pt-2">
                <div class="col-12 col-xl-8">
                    <label for="overview"><fmt:message
                            key="tournamentform.overview"/></label>
                    <textarea class="w-100" rows="26" id="overview"
                              name="overview"
                              placeholder="(<fmt:message key="form.optional"/>)"></textarea>
                </div>
                <div class="col-sm-12 col-md-8 col-xl-4 pl-md-0">
                    <label for="name"><fmt:message
                            key="tournamentform.name"/></label>
                    <input class="form-control mb-2" id="name" type="text"
                           name="name"
                           placeholder="(<fmt:message key="form.required"/>)"
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
                                   name="tournament-logo"/>
                            <table class="table">
                                <tr>
                                    <label class="mb-0"
                                           for="start-date"><fmt:message
                                            key="tournament.startdate"/>:</label>
                                    <input class="form-control mb-1" type="date"
                                           id="start-date"
                                           placeholder="(<fmt:message key="form.required"/>)"
                                           name="startDate">
                                </tr>
                                <tr>
                                    <label class="mb-0"
                                           for="end-date"><fmt:message
                                            key="tournament.enddate"/>:</label>
                                    <input class="form-control mb-1" type="date"
                                           id="end-date"
                                           placeholder="(<fmt:message key="form.required"/>)"
                                           name="endDate">
                                </tr>
                                <tr>
                                    <label class="mb-0" for="prize"><fmt:message
                                            key="tournamentform.prize"/></label>
                                    <input class="form-control mb-1"
                                           type="number" id="prize"
                                           placeholder="(<fmt:message key="form.required"/>)"
                                           name="prize">
                                </tr>
                            </table>
                            <input type="hidden" name="from"
                                   value="tournaments.html">
                            <button type="submit" class="btn btn-dark w-100">
                                <fmt:message key="form.button.create"/></button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </fmt:bundle>
</tag:html>