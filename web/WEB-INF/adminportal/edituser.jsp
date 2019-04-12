<%-- 
    Document   : edituser
    Created on : Nov 29, 2018, 12:40:35 PM
    Author     : Adam Schlinker, Kim Nguyen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile</title>


        <style><%@include file="/WEB-INF/styles/adminhome.css"%></style>
        <style><%@include file="/WEB-INF/styles/navbar.css"%></style>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clH="TMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

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
                        <li class="nav-item active">
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
                        <p id="headertitle"><strong>Edit Profile: ${editUser.username}</strong></p>
                    </div>
                    <div class="col-md-4 text-md-center">${errorMessage}${notification}</div>

                    <div class="col-md-4 text-md-right">
                        <div class="col-md-12">


                        </div>
                    </div>
                </div>

                <hr>

                <form action="edituser" method="POST">
                    <div class="row">
                        <div class="col-md-4">
                        </div>
                        <div class="col-md-4">

                            <table class="table table-border">

                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <div id="inputHeader">Name</div>
                                        <input type="text" class="form-control" name="name" value="${editUser.name}">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <div id="inputHeader">Address</div>
                                        <input type="text" class="form-control" name="address" value="${editUser.address}">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <div id="inputHeader">Postal Code</div>
                                        <input type="text" class="form-control" name="postalcode" value="${editUser.postalCode}">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <div id="inputHeader">Email</div>
                                        <input type="text" class="form-control" name="email" value="${editUser.email}">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <div id="inputHeader">Phone Number</div>
                                        <input type="text" class="form-control" name="phoneNumber" value="${editUser.phoneNo}">
                                    </div>
                                </div>

                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <div id="inputHeader">Username</div>
                                        <input type="text" class="form-control" name="username" value="${editUser.username}">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <div id="inputHeader">Password</div>
                                        <input type="password" class="form-control" name="password" value="${editUser.password}">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <div id="inputHeader">Account Type</div>

                                        <select name="accountType" class="form-control" id="accounttype">
                                            <option value="1">Regular</option>
                                            <option value="2">Administrator</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <div id="inputHeader">Account Status</div>

                                        <select name="active" id="active" class="form-control">
                                            <option value="true">Active</option>
                                            <option value="false">Inactive</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">

                                        <form action="managecustomers" method="GET">
                                            <button type="button submit" class="btn btn-outline-secondary btn-sm">Cancel</button>
                                        </form>
                                    </div>
                                    <div class="form-group col-md-6 text-md-right">
                                        <button type="button submit" class="btn btn-success btn-sm">Save Profile</button>
                                        <input type="hidden" name="action" value="save">
                                        <input type="hidden" name="selectedCustomer" value="${editUser.userId}">

                                    </div>

                                </div>
                            </table>
                        </div>


                </form>
                <div class="col-md-4">

                </div>
                <br>

            </div>
        </div>
    </div>
    <br><br>


</body>
</html>
