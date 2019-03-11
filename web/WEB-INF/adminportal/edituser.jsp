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

        <h1>Edit Profile: ${user.username}</h1>
        <h3>${notification}</h3>
        <form action="managecustomers" method="GET">
            <table align="center">
                <tr>
                    <th>Name:</th>
                    <td><input type="text" name="name" value="${user.name}"></td>
                </tr>
                <tr>
                    <th>Address:</th>
                    <td><input type="text" name="address" value="${user.address}"></td>
                </tr>
                <tr>
                    <th>Postal Code:</th>
                    <td><input type="text" name="postalcode" value="${user.postalCode}"></td>
                </tr>
                <tr>
                    <th>Email:</th>
                    <td><input type="email" name="email" value="${user.email}"></td>
                </tr>
                <tr>
                    <th>Phone Number:</th>
                    <td><input type="text" name="phonenumber" value="${user.phoneNo}"></td>
                </tr>
                <tr>
                    <th>Account Type:</th>
                    <td><select name="accounttype" id="accounttype">
                            <option value="1">Regular</option>
                            <option value="2">Administrator</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>Username:</th>
                    <td><input type="text" name="username" value="${user.username}"></td>
                </tr>
                <tr>
                    <th>Password:</th>
                    <td><input type="password" name="password" placeholder="Enter new password" value="${user.password}"></td>
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
            <input type="submit" value="Save">  
        </form>
        <div class="c">
            <form action="managecustomers" method="GET">
                <input type="submit" value="Cancel" align="center">
            </form>
        </div>
    </body>
</html>
