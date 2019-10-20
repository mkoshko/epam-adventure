<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%--Team actions--%>
<c:set var="createTeamAction" value="createteamform.html"/>
<c:set var="viewTeamAction" value="team.html?id="/>
<c:set var="editTeamAction" value="editteam.html"/>
<c:set var="deleteTeamAction" value="deleteteam.html"/>
<%--Player actions--%>
<c:set var="createPlayerAction" value="createplayerform.html"/>
<c:set var="viewProfileAction" value="player.html?id="/>
<c:set var="editProfileAction" value="editplayer.html"/>
<c:set var="deleteProfileAction" value="deleteplayer.html"/>
<%--card styles--%>
<c:set var="colClass" value="col-12 col-sm-6 py-3"/>
<c:set var="cardClass" value="card p-0 p-lg-0 smaller-card"/>
<tag:html localizedTitle="title.mypages">
    <fmt:bundle basename="localization">
        <div class="row">
<%--            Team card--%>
            <div class="${colClass}">
                <c:choose>
<%--                    Empty team card--%>
                    <c:when test="${team == null}">
                        <div class="${cardClass} mr-lg-1">
                            <div class="card-header text-center text-white bg-dark">
                                <h3 class="m-0">Team</h3>
                            </div>
                            <a href="${createTeamAction}" class="btn-light">
                                <svg viewBox="0 0 24 24" width="200" height="200" stroke="currentColor"
                                     stroke-width="2" fill="none" stroke-linecap="round" stroke-linejoin="round"
                                     class="css-i6dzq1 card-img-top my-4"><circle cx="12" cy="12" r="10"></circle>
                                    <line x1="12" y1="8" x2="12" y2="16"></line><line x1="8" y1="12" x2="16" y2="12"></line>
                                </svg>
                            </a>
                        </div>
                    </c:when>
                    <c:otherwise>
<%--                        Team card--%>
                        <jsp:useBean id="team" scope="request" type="by.koshko.cyberwikia.bean.Team"/>
                        <div class="${cardClass} mr-lg-1">
                            <c:choose>
                                <c:when test="${team.logoFile != null}">
                                    <img class="card-img-top mx-auto smaller-card-img" src="<c:url value="${team.logoFile}"/>" alt="${team.name}">
                                </c:when>
                                <c:otherwise>
                                    <tag:emptyImage/>
                                </c:otherwise>
                            </c:choose>
                            <h3 class="card-title text-center my-1">${team.name}</h3>
                            <div class="p-1">
                                <a class="btn btn-outline-dark w-100 mb-1" href="${viewTeamAction}${team.id}"><fmt:message key="userpage.button.viewprofile"/></a>
                                <a class="btn btn-outline-dark w-100 mb-1" href="${editTeamAction}"><fmt:message key="userpage.button.editpage"/></a>
                                <button type="button" class="btn btn-danger w-100" data-toggle="modal" data-target="#deleteTeam"><fmt:message key="userpage.button.deletepage"/></button>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        <%--    Player card        --%>
            <div class="${colClass}">
                <c:choose>
<%--                    Empty player card--%>
                    <c:when test="${player==null}">
                        <div class="${cardClass} ml-lg-1">
                            <div class="card-header text-center text-white bg-dark">
                                <h3 class="m-0">Player profile</h3>
                            </div>
                            <a href="${createPlayerAction}" class="btn-light">
                                <svg viewBox="0 0 24 24" width="200" height="200" stroke="currentColor"
                                     stroke-width="2" fill="none" stroke-linecap="round" stroke-linejoin="round"
                                     class="css-i6dzq1 card-img-top my-4"><circle cx="12" cy="12" r="10"></circle>
                                    <line x1="12" y1="8" x2="12" y2="16"></line><line x1="8" y1="12" x2="16" y2="12"></line>
                                </svg>
                            </a>
                        </div>
                    </c:when>
                    <c:otherwise>
<%--                        Player card--%>
                        <jsp:useBean id="player" scope="request" type="by.koshko.cyberwikia.bean.Player"/>
                        <div class="${cardClass} ml-lg-1">
                            <c:choose>
                                <c:when test="${player.profilePhoto != null}">
                                    <img class="card-img-top mx-auto smaller-card-img" src="<c:url value="${player.profilePhoto}"/>" alt="${player.nickname}">
                                </c:when>
                                <c:otherwise>
                                    <tag:emptyImage/>
                                </c:otherwise>
                            </c:choose>
                            <h3 class="card-title text-center my-1">${player.nickname}</h3>
                            <div class="p-1">
                                <a class="btn btn-outline-dark w-100 mb-1" href="${viewProfileAction}${player.id}"><fmt:message key="userpage.button.viewprofile"/></a>
                                <a class="btn btn-outline-dark w-100 mb-1" href="${editProfileAction}"><fmt:message key="userpage.button.editpage"/></a>
                                <button type="button" class="btn btn-danger w-100" data-toggle="modal" data-target="#deletePlayer"><fmt:message key="userpage.button.deletepage"/></button>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
<%--        Modal window--%>
        <div class="modal fade" id="deleteTeam">
            <div class="modal-dialog modal-sm modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h4><fmt:message key="userpage.dialog.delete"/></h4>
                    </div>
                    <div class="modal-body text-center px-0">
                        <form action="${deleteTeamAction}" method="post">
                            <input type="hidden" name="from" value="${from}">
                            <button type="submit" class="btn btn-danger w-40"><fmt:message key="dialog.yes"/></button>
                            <button type="button" class="btn btn-dark w-40" data-dismiss="modal"><fmt:message key="dialog.no"/></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%--        Modal window--%>
        <div class="modal fade" id="deletePlayer">
            <div class="modal-dialog modal-sm modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h4>Are you sure?</h4>
                    </div>
                    <div class="modal-body text-center px-0">
                        <form action="${deleteProfileAction}" method="post">
                            <input type="hidden" name="from" value="${from}">
                            <button type="submit" class="btn btn-danger w-40"><fmt:message key="dialog.yes"/></button>
                            <button type="button" class="btn btn-dark w-40" data-dismiss="modal"><fmt:message key="dialog.no"/></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </fmt:bundle>
</tag:html>