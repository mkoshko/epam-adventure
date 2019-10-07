<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="func" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="page" rtexprvalue="true" type="java.lang.Integer" required="true" %>
<%@ attribute name="lastPage" rtexprvalue="true" type="java.lang.Integer" required="true" %>
<%@ attribute name="pageURL" rtexprvalue="true" type="java.lang.String" required="true" %>

<c:choose>
    <c:when test="${page == 1}">
        <c:set var="prevPage" value="1"/>
    </c:when>
    <c:otherwise>
        <c:set var="prevPage" value="${page - 1}"/>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${page < lastPage}">
        <c:set var="nextPage" value="${page + 1}"/>
    </c:when>
    <c:otherwise>
        <c:set var="nextPage" value="${page}"/>
    </c:otherwise>
</c:choose>

<div class="row">
    <div class="col-sm-12 mx-auto">
        <ul class="pagination" style="justify-content: center">
            <li class="page-item">
                <a class="page-link" href="${pageURL}.html?page=1">First</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="${pageURL}.html?page=${prevPage}">Previous</a>
            </li>
            <span class="text-primary" style="margin: 6px 10px">${page} of ${lastPage}</span>
            <li class="page-item">
                <a class="page-link" href="${pageURL}.html?page=${nextPage}">Next</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="${pageURL}.html?page=${lastPage}">Last</a>
            </li>
        </ul>
    </div>
</div>