<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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

</div>
<hr>
<div id="body">
    <h2 align="center">Access Denied!</h2>

</div>
</body>
</html>
