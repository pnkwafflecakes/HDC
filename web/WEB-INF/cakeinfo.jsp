<%-- 
    Document   : cakeinfo
    Created on : Feb 4, 2019, 3:37:48 PM
    Author     : Adam Schlinker
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cake Info</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="/WEB-INF/styles/styles.css">
    </head>
    <body>
        <a href="mainmenu">BACK</a>
        <h1>Helen's Delicious Cakes</h1>

        <img src="/WEB-INF/images/mangocake.jpeg" alt="Mango Cake">

        <ul>
            <li>${currCake.name}</li>
            <li>$${currCake.price}</li>
            <li>${currCake.size}"</li>
        </ul>

        <p>Description: ${currCake.description}</p>

        <form action="cakeinfo" method="POST" >
            <input type="submit" value="Add To Cart">
            <input type="hidden" name="cakeToAdd" value="${currCake}">
        </form>

        <div class="nav">
            <a href="contact">phone</a>
            <a href="account">person</a>
            <a href="cart">cart</a>
        </div>
    </body>
</html>
