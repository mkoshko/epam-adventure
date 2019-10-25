<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table class="table table-bordered table-striped">
    <tbody>
    <c:forEach items="${tournaments}" var="tournament">
        <tr>
            <td class="text-center"><a href="tournament.html?id=${tournament.id}">${tournament.name}</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>