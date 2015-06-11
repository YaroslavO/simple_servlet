<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: employee
  Date: 6/11/15
  Time: 1:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>"${dateTitle}"</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <div class="row">
        <h1 style="text-align:center">${dateTitle}</h1>
    </div>

    <div class="row">
        <a href="${nexLink}" class="btn btn-block btn-lg btn-success">Next Month</a>
        <a href="${predLink}" class="btn btn-block btn-lg btn-success">Pred Month</a>
    </div>

    <div class="row">
        <table class="table">
            <tr>
                <c:forEach var="item" items="${monthTitle}">
                    <c:if test="${item.isWeekendDay()}">
                        <th style="color: green">
                    </c:if>

                    <c:if test="${!item.isWeekendDay()}">
                        <th>
                    </c:if>

                    ${item.toString()}</th>

                </c:forEach>
            </tr>
            <c:forEach var="week" items="${monthCalendar.getWeeks()}">
                <tr>
                    <c:forEach var="weekDay" items="${week.getDays()}">
                        <c:if test="${weekDay.isTheCurrentDay() && !weekDay.isOtherMonth()}">
                            <td style="color: red">
                        </c:if>
                        <c:if test="${weekDay.isOtherMonth()}">
                            <td style="color: grey">
                        </c:if>
                        <c:if test="${weekDay.getType().isWeekendDay() && !weekDay.isOtherMonth()}">
                            <td style="color: green">
                        </c:if>
                        <c:if test="${!weekDay.getType().isWeekendDay() && !weekDay.isOtherMonth() && !weekDay.isTheCurrentDay()}">
                            <td>
                        </c:if>
                                ${weekDay.toString()}
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
