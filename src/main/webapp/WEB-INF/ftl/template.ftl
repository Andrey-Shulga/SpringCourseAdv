<html>
<head>
    <title>User Service</title>
</head>
<body>
<a href="/">Booking page</a>
<a href="getTicketList">Tickets list</a>
<a href="freemarker">Freemarker</a>

<hr>

<fieldset>
    <legend>Search user by email</legend>
    <form action="/getUserByEmail" method="get">
        <label><b>Enter email</b></label>
        <br>
        <input type="text" name="email" value="test1@email.com">
        <input type="submit" value="Get user"/>
    </form>

</fieldset>

Found user: <br>
<#if user??>
id: ${user.id} <br>
name: ${user.name} <br>
birthday: ${user.birthday}<br>
</#if>

<br>
<fieldset>
    <legend>Search all users by name</legend>
    <form action="/getUsersWithName" method="get">
        <label><b>Enter email</b></label>
        <br>
        <input type="text" name="name" value="Ivanov">
        <input type="submit" value="Get users"/>
    </form>

</fieldset>

Found users: <br>
<#if userList??>


    <table border='1'>
        <thead>
        <tr>
            <th scope='colgroup' width="30px">Id</th>
            <th scope='colgroup' width="60px">Email</th>
            <th scope='colgroup' width="60px">Name</th>
            <th scope='colgroup' width="80px">Birth date</th>

        </tr>
        </thead>

        <#list userList as user>
            <tr align="center">
                <td>
                ${user.id}
                </td>
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

        </#list>
    </table>
</#if>
</body>