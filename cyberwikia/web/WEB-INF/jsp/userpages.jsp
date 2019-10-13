<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%--Team actions--%>
<c:set var="createTeamAction" value="createteam.html"/>
<c:set var="viewTeamAction" value="team.html?id="/>
<c:set var="editTeamAction" value="editteam.html"/>
<c:set var="deleteTeamAction" value="deleteteam.html"/>
<%--Player actions--%>
<c:set var="createPlayerAction" value="createplayer.html"/>
<c:set var="viewProfileAction" value="player.html?id="/>
<c:set var="editProfileAction" value="editplayer.html"/>
<c:set var="deleteProfileAction" value="deleteprofile.html"/>
<%--card styles--%>
<c:set var="colClass" value="col-12 col-sm-6 p-2 p-lg-5"/>
<c:set var="cardClass" value="card p-0 p-lg-0"/>
<tag:html>
    <fmt:bundle basename="localization">
        <div class="row">
<%--            Team card--%>
            <div class="${colClass}">
                <c:choose>
<%--                    Empty team card--%>
                    <c:when test="${team == null}">
                        <div class="${cardClass}">
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
                        <div class="${cardClass}">
                            <img class="card-img-top p-1 m-auto" src="<c:url value="${team.logoFile}"/>" alt="${team.name}">
                            <h3 class="card-title text-center my-1">${team.name}</h3>
                            <div class="p-1">
                                <a class="btn btn-outline-dark w-100 mb-1" href="${viewTeamAction}${team.id}">View team</a>
                                <a class="btn btn-outline-dark w-100 mb-1" href="${editProfileAction}">Edit team</a>
                                <button type="button" class="btn btn-danger w-100" data-toggle="modal" data-target="#myModal">Delete team</button>
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
                        <div class="${cardClass}">
                            <div class="card-header text-center text-white bg-dark">
                                <h3 class="m-0">Player profile</h3>
                            </div>
                            <a href="${create}" class="btn-light">
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
                        <div class="${cardClass}">
                            <img class="card-img-top p-1 m-auto" src="<c:url value="${player.profilePhoto}"/>" alt="${player.nickname}">
                            <h3 class="card-title text-center my-1">${player.nickname}</h3>
                            <div class="p-1">
                                <a class="btn btn-outline-dark w-100 mb-1" href="${viewProfileAction}${player.id}">View profile</a>
                                <a class="btn btn-outline-dark w-100 mb-1" href="${editProfileAction}">Edit profile</a>
                                <button type="button" class="btn btn-danger w-100" data-toggle="modal" data-target="#myModal">Delete profile</button>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
<%--        Modal window--%>
        <div class="modal fade" id="myModal">
            <div class="modal-dialog modal-sm modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h4>Are you sure?</h4>
                    </div>
                    <div class="modal-body text-center px-0">
                        <a class="btn btn-danger w-40" href="${deleteProfileAction}">Yes</a>
                        <button type="button" class="btn btn-dark w-40" data-dismiss="modal">No</button>
                    </div>
                </div>
            </div>
        </div>
    </fmt:bundle>
</tag:html>