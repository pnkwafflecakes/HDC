<%-- 
    Document   : summary
    Created on : Apr 3, 2019, 3:05:33 PM
    Author     : 651218
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>

                                <tr><th>Order No</th><td><input type="text" name="orderNo" value="${selectedOrder.orderNo}" readonly></td></tr>
                                <tr><th>Order Time</th><td><input type="text" name="orderDatetime" value="${selectedOrder.orderDatetime}" readonly></td></tr>
                                <tr><th>Due Time</th><td><input type="text" name="dueDatetime" value="${selectedOrder.dueDatetime}" readonly></td></tr>
                                <tr><th>Total</th><td><input type="text" name="totalPrice" value="${selectedOrder.totalPrice}" readonly></td></tr>
                                <tr><th>deliveryNo</th><td><input type="text" name="deliveryNo" value="${selectedOrder.deliveryNo.deliveryNo}" readonly></td></tr>
                                <tr><th>User Name</th><td><input type="text" name="userId" value="${user.username}" readonly></td></tr>
                                <tr><th>PhoneNo</th><td><input type="text" name="userId" value="${user.phoneNo}" readonly></td></tr>
                                <tr><th>Email</th><td><input type="text" name="userId" value="${user.email}" readonly></td></tr>
                                <tr><th>Del Method</th><td><input type="text" name="method" value="${delivery.method}" readonly></td></tr>
                                <tr><th>Address</th><td><input type="text" name="address" value="${delivery.address}" readonly></td></tr>
                                <tr><th>Phone No.</th><td><input type="text" name="phoneNo" value="${delivery.phoneNo}" readonly></td></tr>
                                <tr><th>Notes</th><td><input type="text" name="notes" value="${delivery.notes}" readonly></td></tr>
                                
                                <tr><td></td><td></td><td></td></tr>
         
                    <!--table to show cakes info in this order-->
                        </table>
                                
                        <table>
                                    <thead>
                                    <th>Image</th>
                                    <th>Name</th>
                                    <!--<th>Description</th>-->
                                    <th>Price</th>
                                    <th>Qty</th>
                                    </thead>
                                    <c:forEach var="cakeOrder" items="${cakeOrders}" > 
                                        
                                            <tr>
                                                <td><img src="<c:url value='${cakeOrder.cake.image}'/>" alt="Cake Picture" width="80dp" height="80dp"/></td>
                                                <td width="20%">${cakeOrder.cake.name}</td>
                                                <!--<td width="40%">${cakeOrder.cake.description}</td>-->
                                                <td >${cakeOrder.cake.price}</td>
                                                <td>${cakeOrder.quantity}</td>
                                            </tr>

                                    </c:forEach>
                                <td colspan="4">SubTotal</td><td width="5%"><c:out value="${selectedOrder.totalPrice}"/></td>
                            </table> 
                            
                            <h2>Payment</h2>
                            <table>
                                <tr>
                                    <td>Pay by PayPal</td>
                                    <td>
<!--                                         <!--paypal button-->
                                        <form action="https://www.paypal.com/cgi-bin/webscr" method="post" target="_top">
                                        <input type="hidden" name="cmd" value="_xclick">
                                        <input type="hidden" name="business" value="YMPFXKHT6YJTC">
                                        <input type="hidden" name="lc" value="CA">
                                        <input type="hidden" name="item_name" value="Cake">
                                        <input type="hidden" name="button_subtype" value="services">
                                        <input type="hidden" name="no_note" value="1">
                                        <input type="hidden" name="no_shipping" value="1">
                                        <input type="hidden" name="rm" value="1">
                                        <input type="hidden" name="return" value="http://localhost:8084/HDCProject/payment?payment=success">
                                        <input type="hidden" name="cancel_return" value="http://localhost:8084/HDCProject/payment?payment=fail">
                                        <input type="hidden" name="currency_code" value="CAD">
                                        <input type="hidden" name="bn" value="PP-BuyNowBF:btn_buynowCC_LG.gif:NonHosted">
                                        <input type="hidden" name="amount" value="${totalPrice}">
                                        <input type="image" src="https://www.paypalobjects.com/en_US/i/btn/btn_buynowCC_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
                                        <img alt="" border="0" src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1">
                                        </form>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Pay by INTERAC e-Transfer To:helenbkf@gmail.com</td>
                                    <td>
                                        <form action="payment" method="GET">                                       <form action="payment" method="GET">
                                            <input type="submit" value="Place Your Order">
                                            <input type="hidden" name="payment" value="etrasfer">  
                                        </form> 
                                    </td>
                                </tr>
                                <tr>
                                    <td>Pay by Delivery</td>
                                    <td>
                                         <form action="payment" method="GET">                                       <form action="payment" method="GET">
                                            <input type="submit" value="Place Your Order">
                                            <input type="hidden" name="payment" value="cash">  
                                        </form> 
                                    </td>
                                </tr>
                            </table>
    </body>             
</html>
