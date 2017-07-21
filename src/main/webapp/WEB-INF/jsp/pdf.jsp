<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pdf reports</title>
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
<b>Get booked tickets by event in PDF format.</b>
<form action="getPdf" method="get" content="application/pdf" >
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
    <button type="submit">Get pdf</button>
</form>
</body>
</html>
