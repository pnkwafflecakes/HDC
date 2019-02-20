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
        <title>Login</title>
    </head>
    <body>

        <div style="text-align:center;border:3px black">
            <h1>Login to Helen's Delicious Cakes</h1>

            <form action="login" method="post">
                Username: <input type="text" name="user" value="${user}"><br>
                Password: <input type="password" name="pass" value="${pass}"><br>

                <input type="submit" value="Login" name="log">
                <a href="register.jsp">    ..Register..</a>

                <br>
                ${errorMessage}
            </form>
        </div>
    </body>
</html>
