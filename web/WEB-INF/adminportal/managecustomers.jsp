<%-- 
    Document   : managecustomers
    Created on : March 1st, 2019
    Author     : Adam Schlinker, Kim Nguyen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <title>Administration</title>
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

        <div class="c">
            <h1>Manage Users</h1>
            <h3>${notification}</h3>
        </div>

        <div class="row">
            <div class="col-sm-3">
                <h3>Add Customer</h3>
                <form action="managecustomers" method="POST">
                    <table>
                        <tr>
                            <th>Name</th>
                            <td><input type="text" name="name"></td>
                        </tr>
                        <tr>
                            <th>Address</th>
                            <td><input type="text" name="address"></td>
                        </tr>
                        <tr>
                            <th>Postal Code</th>
                            <td><input type="text" name="postal"></td>
                        </tr>
                        <tr>
                            <th>Email</th>
                            <td><input type="email" name="email"></td>
                        </tr>
                        <tr>
                            <th>Phone Number</th>
                            <td><input type="text" name="phone"></td>
                        </tr>
                        <tr>
                            <th>Username</th>
                            <td><input type="text" name="username"></td>
                        </tr>
                        <tr>
                            <th>Password</th>
                            <td><input type="password" name="password"></td>
                        </tr>
                    </table>
                    <br>
                    <input type="hidden" name="action" value="add">
                    <input type="submit" value="Register" name="register">
                </form>
            </div>

            <div class="col-sm-6">
                <form action="managecustomers" method="POST">
                    <select name="action" onchange="this.form.submit()">
                        <option value="default">--choose an option--</option>
                        <option value="allusers">All Users</option>
                        <option value="client">Customers</option>
                        <option value="staff">Staff</option>
                    </select>
                </form>

                <h3>Users</h3>
                <div style="height:400px;overflow:auto;">
                    <table width="80%">
                        <tr>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Phone Number</th>
                            <th>Operation</th>
                        </tr>
                        <c:forEach var="customer" items="${customers}">
                            <tr>
                                <td>${customer.name}</td>
                                <td>${customer.email}</td>
                                <td>${customer.phoneNo}</td>
                                <td>
                                    <form action="managecustomers" method="POST" >
                                        <input type="submit" value="Delete">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="selectedCustomer" value="${customer.userId}">
                                    </form>
                                </td>
                                <td>
                                    <form action="managecustomers" method="POST" >
                                        <input type="submit" value="Details">
                                        <input type="hidden" name="action" value="view">
                                        <input type="hidden" name="selectedCustomer" value="${customer.userId}">
                                    </form>
                                </td>
                                <td>
                                    <form action="managecustomers" method="POST" >
                                        <input type="submit" value="Edit">
                                        <input type="hidden" name="action" value="edit">
                                        <input type="hidden" name="selectedCustomer" value="${customer.userId}">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>

            <div class="col-sm-3">
                <h3>Add Staff</h3>
                <form action="managecustomers" method="POST">
                    <table>
                        <tr>
                            <th>Name</th>
                            <td>
                                <input type="text" name="staffname">
                            </td>
                        </tr>
                        <tr>
                            <th>Address</th>
                            <td>
                                <input type="text" name="staffaddress">
                            </td>
                        </tr>
                        <tr>
                            <th>Postal Code</th>
                            <td>
                                <input type="text" name="staffpostal">
                            </td>
                        </tr>
                        <tr>
                            <th>Email</th>
                            <td>
                                <input type="email" name="staffemail">
                            </td>
                        </tr>
                        <tr>
                            <th>Phone Number</th>
                            <td>
                                <input type="text" name="staffphone">
                            </td>
                        </tr>
                        <tr>
                            <th>Username</th>
                            <td>
                                <input type="text" name="staffusername">
                            </td>
                        </tr>
                        <tr>
                            <th>Password</th>
                            <td>
                                <input type="password" name="staffpassword">
                            </td>
                        </tr>
                        <tr>
                            <th>Name</th>
                            <td>
                                <input type="text" name="staffname">
                            </td>
                        </tr>
                        <tr>
                            <th>Name</th>
                            <td>
                                <input type="text" name="staffname">
                            </td>
                        </tr>
                    </table>
                    <br>
                    <input type="hidden" name="action" value="addstaff">
                    <input type="submit" value="Add Staff" name="addstaff">
                </form>
            </div>
        </div>
    </body>
</html>
