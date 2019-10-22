<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row">
    <div class="col-12 p-0">
        <footer class="footer">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-4">
                        <form action="lang.html" id="lang" method="GET">
                            <input type="hidden" name="from" value="${from}">
                            <select name="id" class="mt-3">
                                <option value="ru"
                                        <c:if test="${cookie.get('locale').value.toString().equals('ru_RU')}">selected</c:if>
                                >Russian</option>
                                <option value="en"
                                        <c:if test="${cookie.get('locale').value.toString().equals('en_US')}">selected</c:if>
                                >English</option>
                            </select>
                        </form>
                    </div>
                    <div class="col-4">
                        <p class="text-white text-center my-3"><%out.write("© 2000-2019 CyberWikia");%></p>
                    </div>
                    <div class="col-4">

                    </div>
                </div>
            </div>
        </footer>
    </div>
</div>