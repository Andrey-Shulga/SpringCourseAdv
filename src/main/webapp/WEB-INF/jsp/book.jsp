<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking</title>
</head>
<body>
<b>List of register users:</b><br>
<table border='1'>
    <thead>
    <tr>
        <th scope='colgroup' width="60px">Email</th>
        <th scope='colgroup' width="60px">Name</th>
        <th scope='colgroup' width="80px">Birth date</th>

    </tr>
    </thead>
    <c:forEach var="user" items="${userList}">
        <tr align="center">
            <td>
                    ${user.email}
            </td>
            <td>
                    ${user.name}
            </td>
            <td>
                    ${user.birthday}
            </td>

        </tr>

    </c:forEach>
</table>
<b>List of Auditoriums:</b><br>
<table border='1'>
    <thead>
    <tr>
        <th scope='colgroup' width="80px">Name</th>
        <th scope='colgroup' width="80px">Seats number</th>
        <th scope='colgroup' width="50px">VIP seats</th>

    </tr>
    </thead>
    <c:forEach var="aud" items="${auditoriumList}">
        <tr align="center">
            <td>
                    ${aud.name}
            </td>
            <td>
                    ${aud.seatsNumber}
            </td>
            <td>
                    ${aud.vipSeats}
            </td>

        </tr>

    </c:forEach>
</table>

<b>List of Events:</b><br>
<table border='1'>
    <thead>
    <tr>
        <th scope='colgroup' width="90px">Name</th>
        <th scope='colgroup' width="50px">Rate</th>
        <th scope='colgroup' width="90px">Price per ticket</th>
        <th scope='colgroup' width="80px">Date</th>
        <th scope='colgroup' width="90px">Hall</th>

    </tr>
    </thead>
    <c:forEach var="event" items="${eventList}">
        <tr align="center">
            <td>
                    ${event.name}
            </td>
            <td>
                    ${event.rate}
            </td>
            <td>
                    ${event.basePrice}
            </td>
            <td>
                    ${event.dateTime}
            </td>
            <td>
                    ${event.auditorium.name}
            </td>

        </tr>

    </c:forEach>
</table>
</body>
</html>
