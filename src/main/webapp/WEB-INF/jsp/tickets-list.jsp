<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tickets list</title>
</head>
<body>
<a href="/">Booking page</a>
<a href="getTicketList">Tickets list</a>

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
