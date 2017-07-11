<html>
<head>
    <title>Exception</title>
</head>
<body>

<a href="/">Booking page</a>
<a href="getTicketList">Tickets list</a>
<a href="freemarker">Freemarker</a>
<a href="openPdf">Pdf</a>
<a href="openFileForm">Batch upload</a>
<hr>
<#if exception??>

Failed URL: ${url} <br>
Exception:  ${exception}<br>

StackTrace:<br>
<#list exception.stackTrace  as ste>
${ste}<br>
</#list>



</#if>

</body>