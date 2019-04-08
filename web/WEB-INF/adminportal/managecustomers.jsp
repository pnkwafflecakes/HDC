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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

        <title>Administration</title>
    </head>
    <body>
        <nav class="navbar navbar-dark bg-dark">
            <a class="navbar-brand" href="mainmenu">Helen's Delicious Cakes</a>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item"><a href="manageorders" class="nav-link">Orders</a></li>
                    <li class="nav-item"><a href="managecakes" class="nav-link">Cakes</a></li>
                    <li  class="nav-item active"><a href="managecustomers"class="nav-link">Customers</a></li>
                    <li class="nav-item"><a href="managepickups" class="nav-link">Pickups</a></li>
                    <li  class="nav-item"><a href="managefeedback" class="nav-link">Feedback</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="nav-item"><a href="adminhome" class="nav-link"><span class="glyphicon glyphicon-user"></span> Admin Home</a></li>
                    <li class="nav-item"><a href="login" class="nav-link"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
                </ul>

            </div>
        </nav> 

        <div class="c">
            <h1>Manage Users</h1>
            <h3>${notification}</h3>

        </div>
        <div class="container">
            <form action="managecustomers" method="post" >
                <input type="submit" value="Undo">
                <input type="hidden" name="action" value="undo">
            </form>

            <div class="row">
                <div class="col-md-3">
                    <h3>Add User</h3>
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

                <div class="col-md-6">
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


                <div class="dropdown">
                    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Select
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu">
                        <li><a href="#">Add Customer</a></li>
                        <li><a href="#">Add Staff</a></li>
                    </ul>
                </div> 
                <div class="col-md-3">
                    <h3>Add Staff</h3>
                    <form action="managecustomers" method="POST">
                        <table>
                            <tr>
                                <th>Name</th>
                                <td><input type="text" name="staffname"></td>
                            </tr>
                            <tr>
                                <th>Address</th>
                                <td><input type="text" name="staffaddress"></td>
                            </tr>
                            <tr>
                                <th>Postal Code</th>
                                <td><input type="text" name="staffpostal"></td>
                            </tr>
                            <tr>
                                <th>Email</th>
                                <td><input type="email" name="staffemail"></td>
                            </tr>
                            <tr>
                                <th>Phone Number</th>
                                <td><input type="text" name="staffphone"></td>
                            </tr>
                            <tr>
                                <th>Username</th>
                                <td><input type="text" name="staffusername"></td>
                            </tr>
                            <tr>
                                <th>Password</th>
                                <td><input type="password" name="staffpassword"></td>
                            </tr>
                            <tr>
                                <th>Name</th>
                                <td><input type="text" name="staffname"></td>
                            </tr>
                            <tr>
                                <th>Name</th>
                                <td><input type="text" name="staffname"></td>
                            </tr>
                        </table>
                        <br>
                        <input type="hidden" name="action" value="addstaff">
                        <input type="submit" value="Add Staff" name="addstaff">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
