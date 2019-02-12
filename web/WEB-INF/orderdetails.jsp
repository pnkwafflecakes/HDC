<%--
     Document   : cakeinfo
    Created on : Feb 4, 2019, 3:37:48 PM
    Author     : 744916
    `delivery_no` int(4) NOT NULL,
    `method` VARCHAR(30) NOT NULL,
    `address` VARCHAR(80) NOT NULL,
    `phone_no` VARCHAR(12) NOT NULL,
    `notes` VARCHAR(99) NOT NULL,
    PRIMARY KEY (`delivery_no`)
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="input" method="post">
        Address: <input type="text" name="address" value="${address}"</input><br>
        Phone Number: <input type="password" name="phone_no" value="${phone_no}"</input><br>
        Notes: <input type="text" name="notes" value="${notes}"</input><br>
        Delivery Method: <select name = "dropdown">
            <%--
            <option value = "Computer Architecture" selected>Pickup</option>
            --%>
            <option value = "Java">Delivery</option>
        </select>
        <input type="submit" value="Input" name="data">
    </body>
</html>
