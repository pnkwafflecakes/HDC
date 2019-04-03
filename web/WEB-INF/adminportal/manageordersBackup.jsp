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
                                <tr><td>Total</td><td><input type="text" name="orderDatetime" value="" readonly></td></tr>
                                <tr><td>deliveryNo</td><td><input type="text" name="orderDatetime" value="" readonly></td></tr>
                                <tr><td>User Name</td><td><input type="text" name="userId" value="" readonly></td></tr>
                                <tr><td>PhoneNo</td><td><input type="text" name="userId" value="" readonly></td></tr>
                                <tr><td>Email</td><td><input type="text" name="userId" value="" readonly></td></tr>
                                <tr><td>Del Method</td><td><input type="text" name="deliveryMethod" value="" readonly></td></tr>
                                <tr><td>Address</td><td><input type="text" name="deliveryAddress" value="" readonly></td></tr>
                                <tr><td>Phone No.</td><td><input type="text" name="deliveryPhoneNo" value="" readonly></td></tr>
                                <tr><td>Notes</td><td><input type="text" name="deliveryNotes" value="" readonly></td></tr>
                                <tr><td>Active</td><td><input type="checkbox"  name="active"></td></tr>
                                <tr><td>Confirmed</td><td><input type="checkbox"  name="confirmed"></td></tr>
                                <tr><td>Paid</td><td><input type="checkbox"  name="paid"></td></tr>
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

                                <tr><th>Order No</th><td><input type="text" name="orderNo" value="${selectedOrder.orderNo}" readonly></td></tr>
                                <tr><th>Order Time</th><td><input type="text" name="orderDatetime" value="${selectedOrder.orderDatetime}" readonly></td></tr>
                                <tr><th>Due Time</th><td><input type="text" name="dueDatetime" value="${selectedOrder.dueDatetime}"></td></tr>
                                <tr><th>Total</th><td><input type="text" name="totalPrice" value="${selectedOrder.totalPrice}"></td></tr>
                                <tr><th>deliveryNo</th><td><input type="text" name="deliveryNo" value="${selectedOrder.deliveryNo.deliveryNo}" readonly></td></tr>
                                <tr><th>User Name</th><td><input type="text" name="userId" value="${user.username}" readonly></td></tr>
                                <tr><th>PhoneNo</th><td><input type="text" name="userId" value="${user.phoneNo}" readonly></td></tr>
                                <tr><th>Email</th><td><input type="text" name="userId" value="${user.email}" readonly></td></tr>
                                <tr><th>Del Method</th><td><input type="text" name="method" value="${delivery.method}" readonly></td></tr>
                                <tr><th>Address</th><td><input type="text" name="address" value="${delivery.address}"></td></tr>
                                <tr><th>Phone No.</th><td><input type="text" name="phoneNo" value="${delivery.phoneNo}"></td></tr>
                                <tr><th>Notes</th><td><input type="text" name="notes" value="${delivery.notes}"></td></tr>
                                <tr><th>Active</th><td><input type="checkbox" <c:if test="${selectedOrder.active==true}">checked</c:if> name="active"></td></tr>
                                <tr><th>Confirmed</th><td><input type="checkbox" <c:if test="${selectedOrder.confirmed==true}">checked</c:if> name="confirmed"></td></tr>
                                <tr><th>Paid</th><td><input type="checkbox" <c:if test="${selectedOrder.paid==true}">checked</c:if> name="paid"></td></tr>
                                <tr><td></td><td></td><td></td></tr>
                                <input type="hidden" name="selectedOrderId" value="${selectedOrder.orderNo}">
                                <input type="hidden" name="action" value="edit">
                       
                                <tr><td>     </td><td><input type="submit" value="Save"></td></tr>
                    <!--table to show cakes info in this order-->
                        </table>
                                <table>
                                    <thead>
                                    <th>Image</th>
                                    <th>Name</th>
                                    <!--<th>Description</th>-->
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    </thead>
                            <c:forEach var="cakeOrder" items="${cakeOrders}"> 
                                <tr>
                                    <td><img src="<c:url value='${cakeOrder.cake.image}'/>" alt="Cake Picture" width="80dp" height="80dp"/></td>
                                    <td width="20%">${cakeOrder.cake.name}</td>
                                    <!--<td width="40%">${cakeOrder.cake.description}</td>-->
                                    <td width="15%">${cakeOrder.cake.price}</td>
                                    <td width="5%"><c:out value="${cakeOrder.quantity}"/></td>
                                </tr>

                            </c:forEach>
                                <td colspan="3">SubTotal</td><td width="5%"><c:out value="${selectedOrder.totalPrice}"/></td>
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
                   <th>deliveryNo</th>
                   <th>userName</th>
                   <th>Active</th>
                   <th>Confirmed</th>
                   <th>Paid</th>
                   <th>Delete</th>
                   <th>Edit</th>

               <c:forEach var="order" items="${orders}">               
                   <tr>
                       <td>${order.orderNo}</td>
                       <td>${order.orderDatetime}</td>
                       <td>${order.dueDatetime}</td>
                       <td>${order.orderItems}</td>
                       <td>${order.totalPrice}</td>
                       <td>${order.deliveryNo.deliveryNo}</td>
                       <td>${order.userId.username}</td>
                       <td><input type="checkbox" <c:if test="${order.active==true}">checked</c:if> name="active"></td>
                       <td><input type="checkbox" <c:if test="${order.confirmed==true}">checked</c:if> name="active"></td>
                       <td><input type="checkbox" <c:if test="${order.paid==true}">checked</c:if> name="active"></td>
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
