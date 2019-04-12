<%-- 
    Document   : viewuser
    Created on : Nov 29, 2018, 12:40:28 PM
    Author     : Adam Schlinker, Kim Nguyen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Profile</title>
        <style>
            h1
            {
                color: #ba7823;
            }
        </style>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand mb-0 h1" href="adminhome"><img class="icon" src="<c:url value='/images/hdclogo.png'/>" /></a>
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

        <h1 align="center">User Profile</h1>
        <table align="center">
            <tr>
                <th>User ID:</th>
                <td>${viewUser.userId}</td>
            </tr>
            <tr>
                <th>Name:</th>
                <td>${viewUser.name}</td>
            </tr>
            <tr>
                <th>Address:</th>
                <td>${viewUser.address}</td>
            </tr>
            <tr>
                <th>Postal Code:</th>
                <td>${viewUser.postalCode}</td>
            </tr>
            <tr>
                <th>Email:</th>
                <td>${viewUser.email}</td>
            </tr>
            <tr>
                <th>Phone Number:</th>
                <td>${viewUser.phoneNo}</td>
            </tr>
            <tr>
                <th>Account Type:</th>
                <td>
                    <c:if test = "${viewUser.getAccountType().getAccountType() == 1}">
                        Regular
                    </c:if>
                    <c:if test = "${viewUser.getAccountType().getAccountType() == 2}">
                        Administrator
                    </c:if>
                </td>
            </tr>
            <tr>
                <th>Username:</th>
                <td>${viewUser.username}</td>
            </tr>
            <tr>
                <th>Password:</th>
                <td>${viewUser.password}</td>
            </tr>
            <tr>
                <th>Status:</th>
                <td>${viewUser.accountStatus}</td>
            </tr>
        </table>
        <br>
        <div align="center">
            <form action="managecustomers" method="POST">
                <input type="submit" value="Edit Profile">
                <input type="hidden" name="action" value="edit">
                <input type="hidden" name="selectedCustomer" value="${viewUser.userId}">
            </form>
            <form action="managecustomers" method="GET">
                <input type="submit" value="Back" align="center">
            </form>
        </div>
    </body>
</html>
