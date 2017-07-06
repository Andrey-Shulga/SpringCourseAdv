<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking</title>
</head>

<body>
<a href="/">Booking page</a>
<a href="getTicketList">Tickets list</a>
<a href="freemarker">Freemarker</a>
<a href="openPdf">Pdf</a>
<a href="openFileForm">Batch upload</a>
<hr>
<table>
    <th>
        <table border='1'>
            <b>List of register users:</b><br>
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
    </th>
    <th>
        <table border='1'>
            <b>List of Auditoriums:</b><br>
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

    </th>
    <th>
        <table border='1'>
            <b>List of Events:</b><br>
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
    </th>
</table>
<br>
<form action="price" method="get">
    <table>
        <th>
            <label><b>Event Name</b></label>
            <br>
            <input type="text" name="eventName" value="Great Show">
        </th>
        <th>
            <label><b>Auditorium Name</b></label>
            <br>
            <input type="text" name="audName" value="Blue Hall">
        </th>

        <th>
            <label><b>Date</b></label>
            <br>
            <input type="text" name="date" value="2017-02-12T12:13">
        </th>
        <th>
            <label><b>Seats(comma separator)</b></label>
            <br>
            <input type="text" name="seats" value="25,26,27">
        </th>
        <th>
            <label><b>Registered user email</b></label>
            <br>
            <input type="text" name="email" value="test1@email.com">
        </th>

    </table>
    <button type="submit">Get ticket's price</button>
</form>
<br>
<form action="bookTicket" method="post">
    <table>
        <th>
            <label><b>Event Name</b></label>
            <br>
            <input type="text" name="eventName" value="Great Show">
        </th>
        <th>
            <label><b>Auditorium Name</b></label>
            <br>
            <input type="text" name="audName" value="Blue Hall">
        </th>

        <th>
            <label><b>Date</b></label>
            <br>
            <input type="text" name="date" value="2017-02-12T12:13">
        </th>
        <th>
            <label><b>Seats(comma separator)</b></label>
            <br>
            <input type="text" name="seats" value="25,26,27">
        </th>
        <th>
            <label><b>Registered user email</b></label>
            <br>
            <input type="text" name="email" value="test1@email.com">
        </th>

    </table>
    <button type="submit">Book ticket</button>

    ${result}

</form>

<form action="ticketList" method="get">
    <table>
        <th>
            <label><b>Event Name</b></label>
            <br>
            <input type="text" name="eventName" value="Great Show">
        </th>
        <th>
            <label><b>Auditorium Name</b></label>
            <br>
            <input type="text" name="audName" value="Blue Hall">
        </th>

        <th>
            <label><b>Date</b></label>
            <br>
            <input type="text" name="date" value="2017-02-12T12:13">
        </th>


    </table>
    <button type="submit">Get event tickets</button>
</form>

</body>
</html>
