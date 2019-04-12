<%-- 
    Document   : adminhome
    Created on : Feb 21, 2019, 1:57:55 PM
    Author     : Adam Schlinker
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" href="<c:url value='/images/hdclogo.png'/>">

        <style><%@include file="/WEB-INF/styles/adminhome.css"%></style>
        <style><%@include file="/WEB-INF/styles/navbar.css"%></style>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clH="TMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">


        <title>Admin Portal</title>
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
                <p id="headertitle">Dashboard</p>
                <hr>
                <div class="container">
                    <div class ="row">
                        <div class="col-md-6">
                            <table class="table table-bordered">
                                <thead>
                                    <tr class="thead-light">
                                        <th colspan="2"><a href="managecakes">Manage Cakes</a></th> 
                                    </tr>
                                    <tr>
                                        <th scope="col">Cakes</th>
                                        <th scope="col">Last Added</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>${cakeNumber}</td>
                                        <td>${lastAdded}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        
                        <div class="col-md-6">
                            <table class="table table-bordered">
                                <thead>
                                    <tr class="thead-light">
                                        <th colspan="3"><a href="manageorders">Manage Orders</a></th> 
                                    </tr>
                                    <tr>
                                        <th scope="col">Total Orders</th>
                                        <th scope="col">Unconfirmed Orders</th>
                                        <th scope="col">Orders to be delivered</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>${allOrders}</td>
                                        <td>${currOrders}</td>
                                        <td>${notDelivered}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>            
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <table class="table table-bordered">
                                <thead>
                                    <tr class="thead-light">
                                        <th colspan="3"><a href="managecustomers">Manage Customers</a></th> 
                                    </tr>
                                    <tr>
                                        <th scope="col">Customers</th>
                                        <th scope="col">Staff</th>
                                        <th scope="col">Total Users</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>${custNumber}</td>
                                        <td>${staffNumber}</td>
                                        <td>${totalNumber}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
            </div>
        </div>

</body>
</html>
