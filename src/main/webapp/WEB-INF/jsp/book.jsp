<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking</title>
</head>
<body>
<a href="/">Booking page</a>

<hr>
<br>
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
        <th scope='colgroup' width="130px">Seats number</th>
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
        <th scope='colgroup' width="130px">Price per ticket</th>
        <th scope='colgroup' width="120px">Date</th>
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
<br>
<form name="registerForm" action="/price" method="get">
    <label for="eventName"><b>Event Name</b></label>
    <br>
    <input type="text" name="eventName" id="eventName" value="Great Show"><br>

    <label for="audName"><b>Auditorium Name</b></label>
    <br>
    <input type="text" name="audName" id="audName" value="Blue Hall"><br>

    <label for="date"><b>Date</b></label>
    <br>
    <input type="text" name="date" id="date" value="2017-02-12T12:13"><br>

    <label for="seats"><b>Seats(comma separator)</b></label>
    <br>
    <input type="text" name="seats" id="seats" value="25,26,27"><br>

    <label for="email"><b>Registered user email</b></label>
    <br>
    <input type="text" name="email" id="email" value="test1@email.com">
    <br>
    <button type="submit">Get ticket's price</button>

</form>
</body>
</html>
