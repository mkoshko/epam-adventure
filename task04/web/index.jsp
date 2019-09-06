<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home page</title>
    <link rel="stylesheet"
          href="css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <form action="home" method="get">
                <div class="row">
                    <div class="col-12 col-sm-6">
                        <div class="form-group">
                            <label for="pwd">Path to file:</label>
                            <input type="text" name="path" class="form-control"
                                   id="pwd">
                        </div>
                    </div>
                    <div class="col-12 col-sm-6">
                        <div class="form-group">
                            <label for="parser">Select parser:</label>
                            <select class="form-control" id="parser"
                                    name="parser">
                                <option value="dom">DOM</option>
                                <option value="sax">SAX</option>
                            </select><br>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <button id="btn" type="submit"
                                class="btn btn-outline-info btn-block">
                            PARSE
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-12 table-responsive">
        <c:if test="${banks != null}">
            <table class="table table-hover">
                <thead class="thead-dark">
                <tr>
                    <th>Банк</th>
                    <th>Тип депозита</th>
                    <th>iban</th>
                    <th>Вкладчик</th>
                    <th>Сумма вклада</th>
                    <th>Валюта</th>
                    <th>Доходность</th>
                    <th>Срок</th>
                    <th>Пополнение</th>
                    <th>Капитализация</th>
                    <th>Выплаты</th>
                    <th>Минимальный баланс</th>
                </tr>
                </thead>
                <c:forEach items="${banks}" var="bank">
                    <tr>
                        <td>${bank.name}</td>
                        <td>${bank.deposits[0].type}</td>
                        <td>${bank.deposits[0].iban}</td>
                        <td>${bank.deposits[0].depositor.firstName} ${bank.deposits[0].depositor.middleName} ${bank.deposits[0].depositor.lastName}</td>
                        <td>${bank.deposits[0].amount}</td>
                        <td>${bank.deposits[0].currency}</td>
                        <td>${bank.deposits[0].profitability}%</td>
                        <td>${bank.deposits[0].term}</td>
                        <td>
                            <c:choose>
                                <c:when test="${bank.deposits[0].refill == true}">
                                    Да
                                </c:when>
                                <c:otherwise>
                                    Нет
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${bank.deposits[0].capitalization == true}">
                                    Да
                                </c:when>
                                <c:otherwise>
                                    Нет
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <c:choose>
                            <c:when test="${bank.deposits[0].getClass().simpleName == 'SavingDeposit'}">
                                <td>${bank.deposits[0].payout}</td>
                                <td>-</td>
                            </c:when>
                            <c:when test="${bank.deposits[0].getClass().simpleName == 'SettlementDeposit'}">
                                <td>-</td>
                                <td>${bank.deposits[0].minBalance}</td>
                            </c:when>
                            <c:otherwise>
                                <td>-</td>
                                <td>-</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>