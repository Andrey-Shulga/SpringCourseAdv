<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tickets list</title>
</head>
<body>
<security:authorize access="hasAnyRole('REGISTERED_USER','BOOKING_MANAGER')" var="isUSer"/>

<div align="left">

    <c:if test="${isUSer}">
        User:
        <div class="user"><b><security:authentication property="principal.username"/></b><br></div>
        <a href="<c:url value= "/logout"/>">Logout</a>
    </c:if>

</div>
<hr>
<a href="/">Booking page</a>
<a href="getTicketList">Tickets list</a>
<a href="freemarker">Freemarker</a>
<a href="openPdf">Pdf</a>
<a href="openFileForm">Batch upload</a>
<a href="money">Add money</a>
<hr>

<br>
<b>Booked tickets</b><br>
<table border='1'>

    <thead>
    <tr>
        <th scope='colgroup' width="80px">Ticket number</th>
        <th scope='colgroup' width="80px">Booked date</th>
        <th scope='colgroup' width="80px">Event</th>
        <th scope='colgroup' width="80px">Event date</th>
        <th scope='colgroup' width="130px">Seats number</th>
        <th scope='colgroup' width="50px">Username</th>
        <th scope='colgroup' width="50px">Price</th>

    </tr>
    </thead>
    <c:if test="${not empty ticketList}">
        <c:forEach var="ticket" items="${ticketList}">
            <tr align="center">
                <td>
                        ${ticket.id}
                </td>
                <td>
                        ${ticket.dateTime}
                </td>
                <td>
                        ${ticket.event.name}
                </td>
                <td>
                        ${ticket.event.dateTime}
                </td>
                <td>
                        ${ticket.seats}
                </td>
                <td>
                        ${ticket.user.name}
                </td>
                <td>
                        ${ticket.price}
                </td>

            </tr>

        </c:forEach>
    </c:if>
</table>
</body>
</html>
