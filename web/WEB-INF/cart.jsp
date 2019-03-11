<%-- 
    Document   : cakeinfo
    Created on : Feb 4, 2019, 3:37:48 PM
    Author     : 744916
--%>


<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

        <style><%@include file="/WEB-INF/styles/sidebar.css"%></style>


        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <div class="sidenav">
            <a href="mainmenu"><i class="material-icons">home</i>  Home</a>
            <a href="#explore"><i class="material-icons">explore</i>  Explore</a>
            <a href="#favorite"><i class="material-icons">favorite</i>  Favourite</a>

            <a href="cart"><i class="material-icons">shopping_cart</i><i class="material-icons" style="font-size: 0.30em;">wb_sunny</i>  Cart</a>

            <a href="#contact"><i class="material-icons">help</i>  Contact</a>
        </div>

        <div class="main">
            <h1>Cart    (1/3)</h1>
            <br>${errorMessage}
            <table cellspacing="0">
                <tr>
                    <th>Image</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    
                </tr>
                <c:forEach var="cake" items="${cakes}">
                    <tr>
                        <td><img src="<c:url value='${cake.image}'/>" alt="Cake Picture" width="50%" height="50%"/></td>
                        <td width="100">${cake.name}</td>
                        <td width="350">${cake.description}</td>
                        <td width="100">${cake.price}</td>
                        <td><c:out value="${counter[cake.cakeId]}"/></td>
                        <td>
                            <form action="cart" method="post" >
                                <input type="submit" value="Delete">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="selectedCake" value="${cake.cakeId}">
                            </form>
                        </td>
                    </tr>
                </c:forEach>

            </table>
            <form action="cart" method="post">
                <input type="submit" value="Checkout" name="data">

                &nbsp

                <text align="right"> Total Price: ${totalPrice} </text>
        </div>
    </body>
</html>