<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Orders</title>
       <style><%@include file="/WEB-INF/styles/manageorders.css"%></style>
        <!-- Bootstrap - Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!--  Bootstrap - Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

        <!--  Bootstrap - Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </head>
    <body>
        
        
        <!--start header-->
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="mainmenu">Helen's Delicious Cakes</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="manageorders">Orders</a></li>
                    <li><a href="managecakes">Cakes</a></li>
                    <li class="active"><a href="managecustomers">Customers</a></li>
                    <li><a href="managepickups">Pickups</a></li>
                    <li><a href="managefeedback">Feedback</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="adminhome"><span class="glyphicon glyphicon-user"></span> Admin Home</a></li>
                    <li><a href="login"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
                </ul>
            </div>
        </nav> 
        <!--end header-->
       
        
    
        <p>${errorMessage}</p>
        
         <div class = "col-sm-3">
             <!--can admin add cake--> 
                <c:if test="${selectedOrder == null}">
                    <h3>Edit Order</h3>
                    <form action="manageorders" method="POST">
                        <table>

                                <tr><td>Order No</td><td><input type="text" name="orderNo" value="" readonly></td></tr>
                                <tr><td>Order Time</td><td><input type="text" name="orderDatetime" value="" readonly></td></tr>
                                <tr><td>Due Time</td><td><input type="text" name="orderDatetime" value="" readonly></td></tr>
                                <tr><td>Order Items</td><td><input type="text" name="orderDatetime" value="" readonly></td></tr>
                                <tr><td>Total</td><td><input type="text" name="orderDatetime" value="" readonly></td></tr>
                                <tr><td>CakeCollection</td><td><input type="text" name="orderDatetime" value="" readonly></td></tr>
                                <tr><td>deliveryNo</td><td><input type="text" name="orderDatetime" value="" readonly></td></tr>
                                <tr><td>userId</td><td><input type="text" name="orderDatetime" value="" readonly></td></tr>
                                <tr><td>Del Method</td><td><input type="text" name="deliveryMethod" value="" readonly></td></tr>
                                <tr><td>Address</td><td><input type="text" name="deliveryAddress" value="" readonly></td></tr>
                                <tr><td>Phone No.</td><td><input type="text" name="deliveryPhoneNo" value="" readonly></td></tr>
                                <tr><td>Notes</td><td><input type="text" name="deliveryNotes" value="" readonly></td></tr>
                         <input type="hidden" name="selectedOrderId" value="${selectedOrder.orderNo}">     
                        <input type="hidden" name="action" value="add">
                        </table>
                        <input type="submit" value="Save">
                    </form>
                </c:if>
                <c:if test="${selectedOrder != null}">
                    <h3>Edit Order</h3>
                    <form action="manageorders" method="POST">

                        <table>

                                <tr><td>Order No</td><td><input type="text" name="orderNo" value=${selectedOrder.orderNo} readonly></td></tr>
                                <tr><td>Order Time</td><td><input type="text" name="orderDatetime" value=${selectedOrder.orderDatetime} readonly></td></tr>
                                <tr><td>Due Time</td><td><input type="text" name="dueDatetime" value=${selectedOrder.dueDatetime}></td></tr>
                                <tr><td>Order Items</td><td><input type="text" name="orderItems" value=${selectedOrder.orderItems}></td></tr>
                                <tr><td>Total</td><td><input type="text" name="totalPrice" value=${selectedOrder.totalPrice}></td></tr>
                                <tr><td>CakeCollection</td><td><input type="text" name="cakeCollection" value=${selectedOrder.cakeCollection} readonly></td></tr>
                                <tr><td>deliveryNo</td><td><input type="text" name="deliveryNo" value=${selectedOrder.deliveryNo} readonly></td></tr>
                                <tr><td>userId</td><td><input type="text" name="userId" value=${selectedOrder.userId} readonly></td></tr>
                                <tr><td>Del Method</td><td><input type="text" name="method" value=${delivery.method} readonly></td></tr>
                                <tr><td>Address</td><td><input type="text" name="address" value=${delivery.address}></td></tr>
                                <tr><td>Phone No.</td><td><input type="text" name="phoneNo" value=${delivery.phoneNo}></td></tr>
                                <tr><td>Notes</td><td><input type="text" name="notes" value=${delivery.notes}></td></tr>

                                <input type="hidden" name="selectedOrderId" value="${selectedOrder.orderNo}">
                                <input type="hidden" name="action" value="edit">
                       
                                <tr><td>     </td><td><input type="submit" value="Save"></td></tr>
                     </table>
                    </form>
            </c:if> 
         </div>
             
        <div class="col-sm-9">
            <h3>Orders</h3>
           <table>
           
                   <th>Order No</th>
                   <th>Order Time</th>
                   <th>Due Time</th>
                   <th>Order Items</th>
                   <th>Total</th>
                   <th>CakeCollection</th>
                   <th>deliveryNo</th>
                   <th>userId</th>
                   <th>Delete</th>
                   <th>Edit</th>

               <c:forEach var="order" items="${orders}">               
                   <tr>
                       <td>${order.orderNo}</td>
                       <td>${order.orderDatetime}</td>
                       <td>${order.dueDatetime}</td>
                       <td>${order.orderItems}</td>
                       <td>${order.totalPrice}</td>
                       <td>${order.cakeCollection}</td>
                       <td>${order.deliveryNo}</td>
                       <td>${order.userId}</td>
                       <td>
                           <form action="manageorders" method="post" >
                               <input type="submit" value="Delete">
                               <input type="hidden" name="action" value="delete">
                               <input type="hidden" name="selectedOrderId" value="${order.orderNo}">
                           </form>
                       </td>
                       <td>
                           <form action="manageorders" method="get">
                               <input type="submit" value="Edit">
                               <input type="hidden" name="action" value="view">
                               <input type="hidden" name="selectedOrderId" value="${order.orderNo}">
                           </form>
                       </td>
                   </tr>
                   
               </c:forEach>
           </table>
            
        </div>
           
        

       
    </body>
</html>
