<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<tag:html localizedTitle="project.cyberwikia">
    <div class="row">
        <div class="col-12 p-0">
            <div>
                <span class="position-absolute mx-5 my-3"><h1 class="text-white"><c:out value="Welcome to CyberWikia"/></h1></span>
                <span class="position-absolute mx-2"><h6 class="text-white"><c:out value="Technologies used in this project deprecated since 2000 year."/></h6></span>
                <span class="position-absolute mx-auto" style="right: 50%; top: 200px"><input type="text"></span>
                <img class="img-fluid w-100" style="height: 250px" src="<c:url value="/images/upload/background.jpg"/>"/>
            </div>
        </div>
    </div>
</tag:html>
