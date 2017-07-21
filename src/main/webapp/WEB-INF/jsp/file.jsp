<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File</title>
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
<form method="POST" action="uploadFile" enctype="multipart/form-data">
    Valid JSON file to upload: <input type="file" name="file"><br />

    <input type="submit" value="Upload">

</form>
</body>
</html>
