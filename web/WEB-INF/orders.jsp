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
        <p align="center">${error}</p>
         <p style="color:red" align="center">${notdeleted}</p>
        <p style="color:green" align="center">${deleted}</p>
        <table align="center">
            <c:forEach items="${orderList}" var="order">
                <form action="Orders" method="POST">
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
                </form>
            </c:forEach>
        </table>
    </body>
</html>
