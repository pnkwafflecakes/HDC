

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <style>
            #paidBox{
                padding-left: 20px;
            }
        </style>
        <link rel="shortcut icon" href="<c:url value='/images/hdclogo.png'/>">

        <style><%@include file="/WEB-INF/styles/adminhome.css"%></style>
        <style><%@include file="/WEB-INF/styles/navbar.css"%></style>
        <!--<style><%@include file="/WEB-INF/styles/manageorders.css"%></style>-->

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clH="TMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">



        <title>HDC - Manage Orders</title> 
    </head>
    <body>


        <nav class="navbar navbar-expand-lg navbar-custom">
            <div class="container">
                <a class="navbar-brand mb-0 h1" href="adminhome"><img class="icon" src="<c:url value='/images/hdclogo.png'/>" /></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fas fa-bars"></i>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="manageorders">Orders</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="managecakes">Cakes</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="managecustomers">Users</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="managepickups">Pickups</a>
                        </li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <li class="nav-item"><a class="nav-link" href="login?act=logout"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <br>
        <div class="card">

            <div class="card-body"> 
                <div class="row">
                    <div class="col-md-4">
                        <p id="headertitle"><strong>Manage Orders</strong></p>
                    </div>
                    <div class="col-md-4 text-md-center">${errorMessage}</div>

                    <div class="col-md-4 text-md-right">
                        <div class="col-md-12">

                            <form action="manageorders" method="post">
                                <input type="submit" value="Undo Delete">
                                <input type="hidden" name="action" value="undo">
                            </form>
                        </div>
                    </div>
                </div>

                <hr>
                <div class="row">
                    <div class="col-md-5">
                        <c:if test="${selectedOrder != null}">
                            <h3>Edit Order</h3>
                            <hr>
                            <form action="manageorders" method="POST">
                                <div class="form-row">
                                    <div class="form-group col-md-4">
                                        <div id="inputHeader">Order #</div>
                                        <input type="text" class="form-control" name="orderNo" value="${selectedOrder.orderNo}">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <div id="inputHeader">Delivery #</div>
                                        <input type="text" class="form-control" name="deliveryNo" value="${selectedOrder.deliveryNo.deliveryNo}">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <div id="inputHeader">Price ($)</div>
                                        <input type="text" class="form-control" name="totalPrice" value="${selectedOrder.totalPrice}">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <div id="inputHeader">Order Date</div>
                                        <input type="text" class="form-control" name="orderDatetime" value="${selectedOrder.orderDatetime}">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <div id="inputHeader">Due Date</div>
                                        <input type="text" class="form-control" name="dueDateTime" value="${selectedOrder.dueDatetime}">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <div id="inputHeader">User Name</div>
                                        <input type="text" class="form-control" id="userId" value="${user.username}">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <div id="inputHeader">Phone #</div>
                                        <input type="text" class="form-control" id="userId" value="${user.phoneNo}">
                                    </div>
                                </div>

                                <div class="form-row">
                                    <div class="form-group col-md 6">
                                        <div id="inputHeader">Email</div>
                                        <input type="text" class="form-control" id="userId" value="${user.email}">
                                    </div>



                                    <div class="form-group col-md 6">
                                        <div id="inputHeader">Delivery Method</div>
                                        <input type="text" class="form-control" id="method" value="${delivery.method}">

                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md 6">
                                        <div id="inputHeader">Address</div>
                                        <input type="text" class="form-control" id="address" value="${user.address}">
                                    </div>
                                    <div class="form-group col-md 6">
                                        <div id="inputHeader">Delivery Phone #</div>
                                        <input type="text" class="form-control" id="address" value="${delivery.phoneNo}">
                                    </div>
                                </div>

                                <div class="form-row">
                                    <div class="form-check col-md-1">
                                    </div>
                                    <div class="form-check col-md-2">
                                        <div class="row">
                                            <div id="inputHeader">Active</div>
                                            <input class="form-check-input" type="checkbox" class="form-control" <c:if test="${selectedOrder.active==true}">checked</c:if> name="active">
                                            </div>
                                        </div>

                                        <div class="form-check col-md-2">
                                            <div class="row">
                                                <div id="inputHeader">Confirmed</div>
                                                <input class="form-check-input" type="checkbox" class="form-control" <c:if test="${selectedOrder.confirmed==true}">checked</c:if> name="confirmed">
                                            </div>
                                        </div>
                                        <div class="form-check col-md-2" id="paidBox">
                                            <div class="row">
                                                <div id="inputHeader">Paid</div>
                                                <input class="form-check-input" type="checkbox" <c:if test="${selectedOrder.paid==true}">checked</c:if> name="paid">
                                            </div>
                                        </div>
                                        <div class="form-check col-md-2">
                                            <div class="row">
                                                <div id="inputHeader">Delivered</div>
                                                <input class="form-check-input" type="checkbox" <c:if test="${selectedOrder.delivered==true}">checked</c:if> name="delivered">
                                            </div>
                                        </div>
                                        <div class="form-group col-md-3 text-md-right">

                                            <input type="hidden" name="selectedOrderId" value="${selectedOrder.orderNo}">
                                        <input type="hidden" name="action" value="edit">
                                        <input type="submit" value="Save">
                                    </div>


                                </div>
                            </form>  
                        </div>

                        <div class="col-md-7">
                            <br>
                            <table class = "table table-bordered">
                                <thead>
                                <th></th>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th></th>
                                </thead>
                                <c:forEach var="cakeOrder" items="${cakeOrders}" varStatus="cakeOrderIndex"> 
                                    <form action="manageorders" method="post" >
                                        <tr>
                                            <td><img src="<c:url value='${cakeOrder.cake.image}'/>" alt="Cake Picture" width="80dp" height="80dp"/></td>
                                            <td width="20%">${cakeOrder.cake.name}</td>
                                            <td >${cakeOrder.cake.price}</td>
                                            <td><input type="number" name="quantity" value="${cakeOrder.quantity}" min="0" max="20" ></td>
                                            <td>
                                                <input type="submit" value="Chg.Qty">
                                                <input type="hidden" name="action" value="changeQuantity">
                                                <input type="hidden" name="selectedCakeOrder" value="${cakeOrderIndex.index}">
                                                <input type="hidden" name="selectedOrderId" value="${cakeOrder.orders.orderNo}">
                                            </td>
                                        </tr>
                                    </form>
                                </c:forEach>
                                <td colspan="4">SubTotal</td><td width="5%"><c:out value="${selectedOrder.totalPrice}"/></td>
                            </table>
                        </div>
                    </c:if> 

                </div>
            </div>






            <c:if test="${selectedOrder == null}">

                <div class="col-sm-12">
                    <table class="table table-bordered">

                        <th>Order #</th>
                        <th>Order Time</th>
                        <th>Due Time</th>
                        <th>Order Items</th>
                        <th>Total</th>
                        <th>Delivery #</th>
                        <th>User Name</th>
                        <th>Active</th>
                        <th>Confirmed</th>
                        <th>Paid</th>
                        <th>Delivered</th>
                        <th></th>
                        <th></th>

                        <c:forEach var="order" items="${orders}">               
                            <tr>
                                <td>${order.orderNo}</td>
                                <td>${order.orderDatetime}</td>
                                <td>${order.dueDatetime}</td>
                                <td>${order.orderItems}</td>
                                <td>${order.totalPrice}</td>
                                <td>${order.deliveryNo.deliveryNo}</td>
                                <td>${order.userId.username}</td>
                                <td><input type="checkbox" <c:if test="${order.active==true}">checked</c:if> name="active" disabled></td>
                                <td><input type="checkbox" <c:if test="${order.confirmed==true}">checked</c:if> name="active" disabled></td>
                                <td><input type="checkbox" <c:if test="${order.paid==true}">checked</c:if> name="active" disabled></td>
                                <td><input type="checkbox" <c:if test="${order.delivered==true}">checked=</c:if> name="active" disabled></td>
                                    <td>
                                        <form action="manageorders" method="post">
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
            </c:if>



        </div>
    </body>
</html>
