

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <style><%@include file="/WEB-INF/styles/adminhome.css"%></style>
        <style><%@include file="/WEB-INF/styles/navbar.css"%></style>
        <style><%@include file="/WEB-INF/styles/manageorders.css"%></style>

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
                <a class="navbar-brand mb-0 h1">Helen's Delicious Cakes</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fas fa-bars"></i>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
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
                        <li class="nav-item"><a class="nav-link" href="login"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
                    </ul>
                </div>
            </div>
        </nav>



        <h2>${notification}</h2>

        <div class = "col-sm-3">
            <!--can admin add cake--> 
            <c:if test="${selectedPickup == null}">
                <h3>New Pickup</h3>
                <form action="managepickups" method="POST">
                    <table>
                        <tr><td>Pickup Description</td><td><input type="text" name="selectedPickupName" value=""></td></tr>
                        <tr><td>Pickup Address</td><td><input type="text" name="selectedPickupAddress" value=""></td></tr>   
                        <input type="hidden" name="action" value="add">
                    </table>
                    <input type="submit" value="Save">
                </form>
            </c:if>
            <c:if test="${selectedPickup != null}">
                <h3>Edit Pickup</h3>
                <form action="managepickups" method="POST">

                    <table>

                        <tr><td>Pickup Description</td><td><input type="text" name="pickupName" value="${selectedPickup.pickupName}"></td></tr>
                        <tr><td>Pickup Address</td><td><input type="text" name="pickupAddress" value="${selectedPickup.pickupAddress}"></td></tr>  
                        <input type="hidden" name="selectedPickupId" value="${selectedPickup.pickupId}">
                        <input type="hidden" name="action" value="edit">
                        <!--table to show cakes info in this order-->
                    </table>
                    <input type="submit" value="Save">
                </form>  
            </c:if> 
        </div>

        <div class="col-sm-9">
            <h3>Pickup Locations</h3>

            <form action="managepickups" method="post" >
                <input type="submit" value="Undo">
                <input type="hidden" name="action" value="undo">
            </form>
            <table>

                <th>Pickup Id</th>
                <th>Pickup Notes</th>
                <th>Pickup Address</th>
                <th>Delete</th>
                <th>Edit</th>

                <c:forEach var="pickups" items="${pickups}">               
                    <tr>
                        <td>${pickups.pickupId}</td>
                        <td>${pickups.pickupName}</td>
                        <td>${pickups.pickupAddress}</td>
                        <td>
                            <form action="managepickups" method="post" >
                                <input type="submit" value="Delete">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="selectedPickupId" value="${pickups.pickupId}">
                            </form>
                        </td>
                        <td>
                            <form action="managepickups" method="post">
                                <input type="submit" value="Edit">
                                <input type="hidden" name="action" value="view">
                                <input type="hidden" name="selectedPickupId" value="${pickups.pickupId}">
                            </form>
                        </td>
                    </tr>

                </c:forEach>
            </table>

        </div>




    </body>
</html>
