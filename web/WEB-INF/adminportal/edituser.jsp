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
        <style>
            body
            {
                text-align: center;
                align-content: center;
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
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-custom">
            <div class="container">
                <a class="navbar-brand mb-0 h1" href="adminhome">Helen's Delicious Cakes</a>
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

        <h1>Edit Profile: ${editUser.username}</h1>
        <h3>${notification}</h3>
        <form action="edituser" method="POST">
            <table align="center">
                <tr>
                    <th>Name:</th>
                    <td><input type="text" name="name" value="${editUser.name}"></td>
                </tr>
                <tr>
                    <th>Address:</th>
                    <td><input type="text" name="address" value="${editUser.address}"></td>
                </tr>
                <tr>
                    <th>Postal Code:</th>
                    <td><input type="text" name="postalcode" value="${editUser.postalCode}"></td>
                </tr>
                <tr>
                    <th>Email:</th>
                    <td><input type="email" name="email" value="${editUser.email}"></td>
                </tr>
                <tr>
                    <th>Phone Number:</th>
                    <td><input type="text" name="phoneNumber" value="${editUser.phoneNo}"></td>
                </tr>
                <tr>
                    <th>Account Type:</th>
                    <td><select name="accountType" id="accounttype">
                            <option value="1">Regular</option>
                            <option value="2">Administrator</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>Username:</th>
                    <td><input type="text" name="username" value="${editUser.username}"></td>
                </tr>
                <tr>
                    <th>Password:</th>
                    <td><input type="password" name="password" placeholder="Enter new password" value="${editUser.password}"></td>
                </tr>
                <tr>
                    <th>Account Status:</th>
                    <td><select name="active" id="active">
                            <option value="true">Active</option>
                            <option value="false">Inactive</option>
                        </select>
                    </td>
                </tr>
            </table>

            <br>
            <input type="hidden" name="action" value="save">
            <input type="hidden" name="selectedCustomer" value="${editUser.userId}">
            <input type="submit" value="Save">  
        </form>
        <div class="c">
            <form action="managecustomers" method="GET">
                <input type="submit" value="Cancel" align="center">
            </form>
        </div>
    </body>
</html>
