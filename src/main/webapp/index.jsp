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
        <div class="col-lg-6 well">
            <a href="${prevUrl}" class="btn btn-block btn-lg btn-success">Prev Month</a>
        </div>

        <div class="col-lg-6 well">
            <a href="${nexUrl}" class="btn btn-block btn-lg btn-success">Next Month</a>
        </div>
    </div>

    <div class="row">
        <table class="table table-bordered">
            <tr>
                <c:forEach var="dayOfWeek" items="${monthHeader}">
                    <th class="${dayOfWeek.isWeekendDay() ? "success" : ""}">
                        ${dayOfWeek}
                    </th>
                </c:forEach>
            </tr>
            <c:forEach var="week" items="${monthCalendar.getWeeks()}">
                <tr>
                    <c:forEach var="weekDay" items="${week.getDays()}">
                        <c:if test="${weekDay.isTheCurrentDay()}">
                            <td class="info">
                        </c:if>
                        <c:if test="${weekDay.isOtherMonth()}">
                            <td class="active">
                        </c:if>
                        <c:if test="${weekDay.isWeekDay()}">
                            <td class="success">
                        </c:if>
                        <c:if test="${weekDay.isHolidays()}">
                            <td class="info">
                        </c:if>
                                ${weekDay}
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
