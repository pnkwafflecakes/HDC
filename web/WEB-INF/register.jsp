<%-- 
    Document   : cakeinfo
    Created on : Feb 4, 2019, 3:37:48 PM
    Author     : 744916
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register User</title>
    </head>
    <body>
        <h1>Register Here</h1>
        <form method="post">
            <p>Username: <input type="text" name="username"/><br>
            Password: <input type="password" name="password"/><br>
            Full Name: <input type="text" name="name"/><br>
            Address: <input type="text" name="address"/><br>
            Postal Code: <input type="text" name="postal"/><br>
            Email Address: <input type="email" name="email"/><br>
            Phone Number: <input type="text" name="phone"/><br>
            <input type="submit"/></p>
        </form>
        <p style="color:red">${error}</p>
        <p style="color:green">${status}</p>
        <a href="login">Login</a>
    </body>
</html>
