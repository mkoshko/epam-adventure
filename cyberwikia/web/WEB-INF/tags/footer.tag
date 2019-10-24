<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<div class="row">
    <div class="col-12 p-0">
        <footer class="footer">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-4 p-3">
                        <a class="text-white font-weight-bold" href="lang.html?id=ru&from=${from}">RU</a>
                        <span class="text-white">|</span>
                        <a class="text-white font-weight-bold" href="lang.html?id=en&from=${from}">EN</a>
                    </div>
                    <div class="col-4">
                        <p class="text-white text-center my-3"><c:out value="© 2000-2019 CyberWikia"/></p>
                    </div>
                    <div class="col-4">
                    </div>
                </div>
            </div>
        </footer>
    </div>
</div>