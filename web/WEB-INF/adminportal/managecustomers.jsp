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
            <h1>Manage Customers</h1>
            <h3>${notification}</h3>
        </div>


        <div class="row">
            <div class="col-sm-3">
                <h3>Create account</h3>
                <form action="admin" method="Post">
                    <table>
                        <tr>
                            <th>Username</th>
                            <td><input type="text" name="username"></td>
                        </tr>
                        <tr>
                            <th>Password</th>
                            <td><input type="password" name="password"></td>
                        </tr>
                        <tr>
                            <th>Email</th>
                            <td><input type="email" name="email"></td>
                        </tr>
                        <tr>
                            <th>First name</th>
                            <td><input type="text" name="firstname"></td>
                        </tr>
                        <tr>
                            <th>Last name</th>
                            <td><input type="text" name="lastname"></td>
                        </tr>
                        <tr>
                            <th>Company</th>
                            <td><select name="company" id="company">
                                    <c:forEach var="comp" items="${comps}">
                                        <option value="${comp.companyID}">${comp.companyName}</option>
                                    </c:forEach>
                                </select></td>
                        </tr>
                    </table>
                    <br>
                    <input type="hidden" name="action" value="add">
                    <input type="submit" value="Register" name="register">
                </form>
            </div>
            <div class="col-sm-6">
                <h3>Manage Accounts</h3>
                <div style="height:400px;overflow:auto;">
                    <table width="80%">
                        <tr>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Operation</th>
                        </tr>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.username}</td>
                                <td>${user.email}</td>
                                <td>
                                    <form action="admin" method="post" >
                                        <input type="submit" value="Delete">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="selectedUsername" value="${user.username}">
                                    </form>
                                </td>
                                <td>
                                    <form action="admin" method="post" >
                                        <input type="submit" value="Detail">
                                        <input type="hidden" name="action" value="view">
                                        <input type="hidden" name="selectedUsername" value="${user.username}">
                                    </form>
                                </td>
                                <td>
                                    <form action="admin" method="post" >
                                        <input type="submit" value="Edit">
                                        <input type="hidden" name="action" value="edit">
                                        <input type="hidden" name="selectedUsername" value="${user.username}">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <div class="col-sm-3">
                <h3>Add Company</h3>
                <form action="admin" method="Post">
                    <table>
                        <tr>
                            <th>Company Name</th>
                            <td>
                                <input type="text" name="newcompname">
                            </td>
                        </tr>
                    </table>
                    <input type="hidden" name="action" value="addcomp">
                    <input type="submit" value="Add Company" name="addcomp">
                </form>
                <br>
                <br>
                <h3>Manage Companies</h3>
                <table width="80%">
                    <tr>
                        <th>Company ID</th>
                        <th>Company Name</th>
                        <th>Operation</th>
                    </tr>
                    <c:forEach var="comp" items="${comps}">
                        <tr>
                            <td>${comp.companyID}</td>
                            <td>${comp.companyName}</td>   
                            <td>
                                <form action="admin" method="post" >
                                    <input type="submit" value="Edit">
                                    <input type="hidden" name="action" value="editcomp">
                                    <input type="hidden" name="selectedComp" value="${comp.companyID}">
                                </form>
                            </td>

                        </tr>

                    </c:forEach>
                </table>

            </div>
        </div>

        <br>



    </body>
</html>
