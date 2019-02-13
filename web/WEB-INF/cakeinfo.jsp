<%-- 
    Document   : cakeinfo
    Created on : Feb 4, 2019, 3:37:48 PM
    Author     : Adam Schlinker
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <style><%@include file="/WEB-INF/styles/sidebar.css"%></style>
        <style><%@include file="/WEB-INF/styles/cakeinfo.css"%></style>
        <title>Cake Info</title>
    </head>
    <body>
        <div class="sidenav">
            <a href="#home"><i class="material-icons">home</i>  Home</a>
            <a href="#explore"><i class="material-icons">explore</i>  Explore</a>
            <a href="#favorite"><i class="material-icons">favorite</i>  Favourite</a>
            <a href="#cart"><i class="material-icons">shopping_cart</i><i class="material-icons" style="font-size: 0.30em;">wb_sunny</i>  Cart</a>
            <a href="#contact"><i class="material-icons">help</i>  Contact</a>
        </div>

        <div class="content">
            <div class="title">
                <h1>Helen's Delicious Cakes</h1>
            </div>

            <div class="cakeinfo">
                <div class="image">
                    <img src="<c:url value='${currCake.image}'/>" alt="Cake Picture">
                </div>

                <div class="info">
                    <ul>
                        <li>${currCake.name}</li>
                        <li>$${currCake.price}</li>
                        <li>${currCake.size}"</li>
                    </ul>

                    <p>Description: ${currCake.description}</p>

                    <div class="button">
                        <form action="cakeinfo" method="POST">
                            <input type="hidden" name="cakeId" value="${currCake.cakeId}">
                            <input type="submit" value="Add To Cart">
                        </form>
                    </div>
                </div>
            </div>

            <div class="clearfix"></div>

        </div>
    </body>
</html>
