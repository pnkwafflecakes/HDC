

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
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
                        <li class="nav-item">
                            <a class="nav-link" href="manageorders">Orders</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="managecakes">Cakes</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="managecustomers">Users</a>
                        </li>
                        <li class="nav-item active">
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
                        <p id="headertitle"><strong>Manage Pickups</strong></p>
                    </div>
                    <div class="col-md-4 text-md-center">${notification}</div>


                    <div class="col-md-4 text-md-right">

                        <div class="col-md-12">

                            <form action="managepickups" method="post" >
                                <input type="submit" value="Undo Delete">
                                <input type="hidden" name="action" value="undo">
                            </form>
                        </div>
                    </div>
                </div>

                <hr>



                <div class="row">
                    <div class = "col-md-4">

                        <c:if test="${selectedPickup == null}">
                            <h3>New Pickup</h3>
                            <form action="managepickups" method="POST">
                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <div id="inputHeader">Pickup Description</div>
                                        <textarea type="text" row="3" class="form-control" name="selectedPickupName"></textarea>
                                    </div>
                                </div>

                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <div id="inputHeader">Pickup Address</div>
                                        <input type="text" class="form-control" name="selectedPickupAddress">
                                    </div>
                                </div>
                                <div class="form-row text-md-right">
                                    <div class="form-group col-md-12">
                                        <input type="hidden" name="action" value="add">
                                        <input type="submit" value="Save">
                                    </div>
                                </div>
                            </c:if>
                        </form>

                        <c:if test="${selectedPickup != null}">
                            <h3>New Pickup</h3>
                            <form action="managepickups" method="POST">
                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <div id="inputHeader">Pickup Description</div>
                                        <textarea type="text" row="3" class="form-control" name="pickupName">${selectedPickup.pickupName}</textarea>
                                    </div>
                                </div>

                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <div id="inputHeader">Pickup Address</div>
                                        <input type="text" class="form-control" name="pickupAddress" value="${selectedPickup.pickupAddress}">
                                    </div>
                                </div>
                                <div class="form-row text-md-right">
                                    <div class="form-group col-md-12">
                                        <input type="hidden" name="selectedPickupId" value="${selectedPickup.pickupId}">
                                        <input type="hidden" name="action" value="edit">
                                        <input type="submit" value="Save">
                                    </div>
                                </div>
                            </c:if>
                        </form>
                    </div>

                    <div class="col-md-8">
                        <h3>Pickup Locations</h3>


                        <table class="table table-bordered">

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
                </div>
            </div>


    </body>
</html>
