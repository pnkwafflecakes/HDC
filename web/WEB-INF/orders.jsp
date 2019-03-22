<%-- 
    Document   : orders
    Created on : 1-Mar-2019, 1:52:41 PM
    Author     : Knyfe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Orders</title>
    </head>
    <body>
        <h1 align="center">Orders</h1>
        <table align="center">
            <tr>
                <th>Order Number:</th>
                <td>${order.order_no}</td>
            </tr>
            <tr>
                <th>Date Placed:</th>
                <td>${order.order_datetime}</td>
            </tr>
            <tr>
                <th>Order Due:</th>
                <td>${order.due_datetime}</td>
            </tr>
            <tr>
                <th>Items:</th>
                <td>${order.order_items}</td>
            </tr>
            <tr>
                <th>Total Price:</th>
                <td>${order.total_price}</td>
            </tr>
            <tr>
                <th>Delivery Number:</th>
                <td>${order.delivery_no}</td>
            </tr>
        </table>
    </body>
</html>
