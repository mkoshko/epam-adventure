<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="text" required="false" rtexprvalue="true" %>

<fmt:bundle basename="localization">
    <div class="col-sm-12 col-md-8 col-xl-4 mx-auto">
        <div class="card">
            <jsp:doBody/>
        </div>
    </div>
</fmt:bundle>