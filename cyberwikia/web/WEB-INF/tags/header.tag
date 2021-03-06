<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="porjectName" value="project.cyberwikia" scope="page"/>
<c:set var="players" value="header.players" scope="page"/>
<c:set var="teams" value="header.teams" scope="page"/>
<c:set var="tournaments" value="header.tournaments" scope="page"/>
<c:set var="registration" value="header.registration" scope="page"/>
<c:set var="profiles" value="header.profiles" scope="page"/>
<c:set var="createtournament" value="header.createtournament" scope="page"/>
<c:set var="editPassword" value="user.editpassword" scope="page"/>

<c:set var="playersLink" value="players.html" scope="page"/>
<c:set var="teamsLink" value="teams.html" scope="page"/>
<c:set var="tournamentsLink" value="tournaments.html" scope="page"/>
<c:set var="registrationLink" value="registration.html" scope="page"/>
<c:set var="profilesLink" value="mypages.html" scope="page"/>
<c:set var="createtournamentLink" value="createtournamentform.html"
       scope="page"/>


<c:set var="signInAction" value="signin.html" scope="page"/>
<c:set var="signOutAction" value="signout.html" scope="page"/>
<c:set var="editPasswordAction" value="changepasswordform.html" scope="page"/>

<fmt:bundle basename="localization">
    <div class="row sticky-top">
        <div class="col-12 px-0">
            <nav class="navbar navbar-expand-xl navbar-dark bg-dark">
                <a class="navbar-brand" href="index.html"><fmt:message
                        key="${porjectName}"/></a>
                <button class="navbar-toggler" type="button"
                        data-toggle="collapse"
                        data-target="#header-menu" aria-controls="header-menu"
                        aria-expanded="false"
                        aria-label="Toggle navigation">
                    <svg width="24" height="24" viewBox="0 0 24 24"
                         fill="none" stroke="currentColor" stroke-width="2"
                         stroke-linecap="round"
                         stroke-linejoin="round"
                         class="feather feather-chevrons-down">
                        <polyline points="7 13 12 18 17 13"></polyline>
                        <polyline points="7 6 12 11 17 6"></polyline>
                    </svg>
                </button>
                <div class="collapse navbar-collapse" id="header-menu">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a class="nav-link"
                               href="${playersLink}"><fmt:message
                                    key="${players}"/></a>
                            <span class="sr-only">(current)</span>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${teamsLink}"><fmt:message
                                    key="${teams}"/></a>
                            <span class="sr-only">(current)</span>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link"
                               href="${tournamentsLink}"><fmt:message
                                    key="${tournaments}"/></a>
                            <span class="sr-only">(current)</span>
                        </li>
                        <c:if test="${user == null}">
                            <li class="nav-item">
                                <a class="nav-link"
                                   href="${registrationLink}"><fmt:message
                                        key="${registration}"/></a>
                                <span class="sr-only">(current)</span>
                            </li>
                        </c:if>
                        <c:if test="${user.role.toString().equals('USER')}">
                            <li class="nav-item">
                                <a class="nav-link"
                                   href="${profilesLink}"><fmt:message
                                        key="${profiles}"/></a>
                            </li>
                        </c:if>
                        <c:if test="${user.role.toString().equals('EVENT_MODERATOR')}">
                            <li class="nav-item">
                                <a class="nav-link"
                                   href="${createtournamentLink}"><fmt:message
                                        key="${createtournament}"/></a>
                            </li>
                        </c:if>
                    </ul>
                    <c:if test="${user != null}">
                        <div>
                            <button class="btn btn-outline-light btn-full-width mb-2 mr-2 mb-sm-2 mb-md-2 mb-lg-0"
                                    data-toggle="modal"
                                    data-target="#user-modal">
                                <svg viewBox="0 0 24 24" width="24" height="24"
                                     stroke="currentColor" stroke-width="2"
                                     fill="none" stroke-linecap="round"
                                     stroke-linejoin="round"
                                     class="css-i6dzq1 icon">
                                    <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                                    <circle cx="12" cy="7" r="4"></circle>
                                </svg>
                            </button>
                        </div>
                        <div>
                            <form action="${signOutAction}" method="post">
                                <input type="hidden" name="from"
                                       value="${from}">
                                <button class="btn btn-outline-light btn-full-width"
                                        type="submit">
                                    <svg width="24" height="24"
                                         viewBox="0 0 24 24" fill="none"
                                         stroke="white"
                                         stroke-width="2" stroke-linecap="round"
                                         stroke-linejoin="round"
                                         class="feather feather-log-out icon">
                                        <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path>
                                        <polyline
                                                points="16 17 21 12 16 7"></polyline>
                                        <line x1="21" y1="12" x2="9"
                                              y2="12"></line>
                                    </svg>
                                </button>
                            </form>
                        </div>
                    </c:if>
                    <c:if test="${user == null}">
                        <form class="form-inline" id="signup-form"
                              action="${signInAction}" method="post">
                            <input class="form-control mb-1 mr-sm-2 mb-sm-2 mb-md-2 mr-md-2 mb-lg-0 mr-lg-1"
                                   type="text" name="login"
                                   placeholder="<fmt:message key="form.login.hint"/>">
                            <input class="form-control mb-1 mr-sm-2 mb-sm-2 mb-md-2 mr-md-2 mb-lg-0 mr-lg-1"
                                   type="password" name="password"
                                   placeholder="<fmt:message key="form.password.hint"/>">
                            <input type="hidden" name="from" value="${from}">
                            <button class="btn btn-outline-light mb-2 mb-md-2 mb-lg-0 btn-full-width"
                                    type="submit" id="submit-btn">
                                <span class="spinner-border spinner-border-sm"
                                      id="btn-spinner"
                                      style="display: none"></span>
                                <svg width="24" height="24" viewBox="0 0 24 24"
                                     fill="none" stroke="white"
                                     stroke-width="2" stroke-linecap="round"
                                     stroke-linejoin="round"
                                     class="feather feather-log-in icon">
                                    <path d="M15 3h4a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2h-4"></path>
                                    <polyline
                                            points="10 17 15 12 10 7"></polyline>
                                    <line x1="15" y1="12" x2="3" y2="12"></line>
                                </svg>
                            </button>
                        </form>
                    </c:if>
                </div>
            </nav>
        </div>
    </div>
    <c:if test="${user != null}">
        <div class="modal fade" id="user-modal">
            <div class="modal-dialog modal-sm modal-dialog-centered">
                <div class="modal-content">

                    <div class="modal-header">
                        <h4 class="modal-title mr-1">${user.login}</h4>
                        <span class="text-muted pt-2">(${user.email})</span>
                    </div>
                    <div class="modal-body">
                        <a href="${editPasswordAction}"><fmt:message
                                key="${editPassword}"/></a>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary"
                                data-dismiss="modal">Close
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</fmt:bundle>