<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File</title>
</head>
<body>
<a href="/">Booking page</a>
<a href="getTicketList">Tickets list</a>
<a href="freemarker">Freemarker</a>
<a href="openPdf">Pdf</a>
<a href="openFileForm">Batch upload</a>

<hr>
<form method="POST" action="uploadFile" enctype="multipart/form-data">
    Valid JSON file to upload: <input type="file" name="file"><br />

    <input type="submit" value="Upload">

</form>
</body>
</html>
