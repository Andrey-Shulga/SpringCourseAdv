<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/css/signin.css" rel="stylesheet"/>
    <title>Login</title>
</head>
<body>
<div id="body">

    <form name="form" action="/login" method="post" class="form-signin">


        <h2 class="form-signin-heading">Please login</h2>

        <label for="username" class="sr-only">Login</label>
        <input id="username" class="form-control" name="username" value="test1@email.com" required autofocus/>

        <label for="password" class="sr-only">Password</label>
        <input type="password" id="password" class="form-control" name="password" value="test1" required/>

        <div class="checkbox">
            <label>
                <input type="checkbox" id="rememberme" name="_spring_security_remember_me"/>Remember me
            </label>
        </div>
        <input type="submit" value="Sign in" class="btn btn-lg btn-primary btn-block"><br>
        <c:if test="${not empty param.error}">
            <span class="error_login" style="font-size: small; color: red; "><b>Incorrect login or password</b></span>
        </c:if>

        <br>



    </form>
    <br>
    With role BOOKING_MANAGER:<br>
    Login: test2@email.com<br>
    Password: test2

</div>
</body>
</html>
