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
        <style><%@include file="/WEB-INF/styles/adminhome.css"%></style>
        <style>
            panel-default
            {

            }
            h1
            {
            	text-align:center;
            }
            table
            {
                border-collapse: collapse;
                margin: 0 -15px; 

            }

            div
            {
                border: 0px solid black;
            }

            table, td, th
            {
                border: 1px solid black;

            }
            div.c
            {
                text-align: center;
            } 
            h1
            {
                color: #ba7823;
            }
        </style>
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        
        <title>Admin Portal</title>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="mainmenu">Helen's Delicious Cakes</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="manageorders">Orders</a></li>
                    <li><a href="managecakes">Cakes</a></li>
                    <li><a href="managecustomers">Customers</a></li>
                    <li><a href="managepickups">Pickups</a></li>
                    <li><a href="managefeedback">Feedback</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="adminhome"><span class="glyphicon glyphicon-user"></span> Admin Home</a></li>
                    <li><a href="login"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
                </ul>
            </div>
        </nav>

        <h1>Administration</h1>
        <div class="container">
            <div class="row">
                <div class="col-md-6">

                    <table class="table table-bordered">
                        <tr>
                            <th scope="col"><a href="managecakes"><center>Manage Cakes</center></a></th> 
                        </tr>
                    </table>

                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">Cakes</th>
                                <th scope="col">Last Added</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th>${cakeNumber}</th>
                                <td>${lastAdded}</td>
                            </tr>
                        </tbody>
                    </table>

                </div>

                <div class="col-md-6">

                    <table class="table table-bordered">

                        <tr>
                            <th scope="col"><a href="managecustomers"><center>Manage Users</center></a></th> 
                        </tr>

                    </table>


                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">Customers</th>
                                <th scope="col">Staff</th>
                                <th scope="col">Total Users</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row">${custNumber}</th>
                                <td>${staffNumber}</td>
                                <td>${totalNumber}</td>
                            </tr>
                        </tbody>
                    </table>

                </div>
            </div>

            <div class="row">
                <div class="col-md-6">



                    <table class="table table-bordered">

                        <tr>
                            <th scope="col"><a href="manageorders"><center>Manage Orders</center></a></th> 
                        </tr>

                    </table>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">Current Orders</th>
                                <th scope="col">Total Orders</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th>${currOrders}</th>
                                <td>${allOrders}</td>
                            </tr>
                        </tbody>
                    </table>

                </div>

                <div class="col-md-6">


                    
                    
                </div>
            </div>
        </div>

    </body>
</html>
