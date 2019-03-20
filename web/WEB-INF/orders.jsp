<%-- 
    Document   : orders
    Created on : 1-Mar-2019, 1:52:41 PM
    Author     : Knyfe
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <c:forEach items="${orderList}" var="order">
                <tr>
                    <th>Order Number:</th>
                    <td><c:out value="${order.orderNo}"/></td>
                </tr>
                <tr>
                    <th>Date Placed:</th>
                    <td><c:out value="${order.orderDatetime}"/></td>
                </tr>
                <tr>
                    <th>Order Due:</th>
                    <td><c:out value="${order.dueDatetime}"/></td>
                </tr>
                <tr>
                    <th>Items:</th>
                    <td><c:out value="${order.orderItems}"/></td>
                </tr>
                <tr>
                    <th>Total Price:</th>
                    <td><c:out value="$:${order.totalPrice}"/></td>
                </tr>
                <tr>
                    <th>Delivery Number:</th>
                    <td><c:out value="${order.deliveryNo.deliveryNo}"/></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
