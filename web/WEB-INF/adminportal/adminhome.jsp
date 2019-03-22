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
            table
            {
                border-collapse: collapse;
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

        <div class="portal">
            <a href="managecakes">Manage Cakes</a><br>
            <table>
                <tr>
                    <th>Cakes</th>
                    <th>Last Added</th>
                </tr>
                <tr>
                    <td>${cakeNumber}</td>
                    <td>${lastAdded}</td>
                </tr>
            </table>
        </div>

        <div class="portal">
            <a href="managecustomers">Manage Users</a><br>
            <table>
                <tr>
                    <th>Customers</th>
                    <th>Staff</th>
                    <th>Total Users</th>
                </tr>
                <tr>
                    <td>${custNumber}</td>
                    <td>${staffNumber}</td>
                    <td>${totalNumber}</td>
                </tr>
            </table>
        </div>

        <div class="portal">
            <a href="manageorders">Manage Orders</a><br>
            <table>
                <tr>
                    <th>Current Orders</th>
                    <th>All Orders</th>
                </tr>
                <tr>
                    <td>${currOrders}</td>
                    <td>${allOrders}</td>
                </tr>
            </table>
        </div>

        <div class="portal">
            <a href="managepickups">Manage Pickup Locations</a><br>
            <table>
                <tr>
                    <th># of Locations</th>
                    <th>Last Added</th>
                </tr>
                <tr>
                    <td>${locationNumber}</td>
                    <td>${lastAdded}</td>
                </tr>
            </table>
        </div>

        <div class="portal">
            <a href="managefeedback">Manage Feedback</a><br>
            <table>
                <tr>
                    <th># of Cakes</th>
                    <th>Last Added</th>
                </tr>
                <tr>
                    <td>${cakeNumber}</td>
                    <td>${lastAdded}</td>
                </tr>
            </table>
        </div>
    </body>
</html>
