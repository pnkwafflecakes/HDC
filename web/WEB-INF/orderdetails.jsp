

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
        <title>JSP Page</title>
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
            <h1>Order Details   2/3</h1>
            <form action="orderdetails" method="post">
            Address: <input type="text" name="address" value="${address}"</input><br>
            Phone Number: <input type="text" name="phoneNo" value="${phoneNo}"</input><br>
            Notes: <textarea rows="2" cols="25" name="notes" value="${notes}" height="200"></textarea><br>
            Delivery Method: <select name = "method">
                <option value = "delivery">Delivery</option>
            </select>
            <input type="submit" value="Submit" name="data">
        </div>
    </body>
</html>
