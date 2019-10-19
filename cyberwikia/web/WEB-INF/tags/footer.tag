<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row">
    <div class="col-12 p-0">
        <footer class="footer">
            <div class="lang-switch">
                <form action="lang.html" id="lang" method="get">
                    <input type="hidden" value="from=${from}">
                    <select name="id">
                        <option value="ru"
                                <c:if test="${cookie.get('locale').value.toString().equals('ru_RU')}">selected</c:if>
                        >Russian</option>
                        <option value="en"
                                <c:if test="${cookie.get('locale').value.toString().equals('en_US')}">selected</c:if>
                        >English</option>
                    </select>
                </form>
            </div>
        </footer>
    </div>
</div>