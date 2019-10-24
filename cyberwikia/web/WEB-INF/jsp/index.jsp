<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<tag:html localizedTitle="project.cyberwikia">
    <fmt:bundle basename="localization">
        <div class="row top">
            <div class="col-12 p-0">
                <h2 class="text-white text-center mb-0 mt-4">Welcome to
                    CyberWikia</h2>
                <p class="text-muted text-center mb-5">Technologies used in this
                    project deprecated since 2000.</p>
                <div class="">
                    <form action="search.html" class="text-center d-block" method="get">
                        <select class="content">
                            <option value="players"><fmt:message
                                    key="title.players"/></option>
                            <option value="teams"><fmt:message
                                    key="title.teams"/></option>
                            <option value="tournament"><fmt:message
                                    key="title.tournaments"/></option>
                        </select>
                        <input class="search ml-0" type="text" placeholder="Search...">
                        <button type="submit" class="content">
                            Search
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </fmt:bundle>
</tag:html>
