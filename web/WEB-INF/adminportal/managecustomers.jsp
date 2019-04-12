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
        <meta name="viewport" content="width=device-width, initial-scale=1">        
        <style>
            #filter { 
                text-align: center;
            }



        </style>

        <link rel="shortcut icon" href="<c:url value='/images/hdclogo.png'/>">



        <style><%@include file="/WEB-INF/styles/adminhome.css"%></style>
        <style><%@include file="/WEB-INF/styles/navbar.css"%></style>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clH="TMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.0/jquery.validate.min.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.0/additional-methods.js"></script>

        <script>
            function unhideUserList() {
                document.getElementById('userList').style = "display: block;";
                document.getElementById('addUser').style = "display: none;";
            }
            function unhideAddUser() {
                document.getElementById('userList').style = "display: none;";
                document.getElementById('addUser').style = "display: block;";
            }
        </script>



        <title>HDC - Manage Users</title>
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
                        <p id="headertitle"><strong>Manage Users</strong></p>
                    </div>
                    <div class="col-md-3 text-md-center">${notification}</div>

                    <div class="col-md-5">
                        <div class="row">

                            <div class="col-md-12">
                                <div class="form-row float-right">
                                    <form method="post" class="">
                                        <button type="button" onClick='unhideUserList()' class="btn btn-secondary btn-sm" aria-pressed="true">User List</button>
                                    </form>
                                    <form method="post" class="ml-2 mr-5">
                                        <button type="button" onClick='unhideAddUser()' class="btn btn-secondary btn-sm" aria-pressed="true">Add User</button>
                                    </form>
                                    <form action="managecustomers" method="post" class="mr-3">
                                        <button type="button submit" class="btn btn-danger btn-sm" aria-pressed="true">Undo Delete</button>
                                        <input type="hidden" name="action" value="undo">
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <hr>

                <div class="container-fluid" id="userList" style="display: block">
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th scope="col" style="width: 25%">Name</th>
                                        <th scope="col" style="width: 25%">Email</th>
                                        <th scope="col" style="width: 25%">Phone Number</th>
                                        <th colspan="3" style="width: 25%" id="filter">
                                            <form action="managecustomers" method="POST">
                                                <select name="action" onchange="this.form.submit()">
                                                    <option value="default">Filter User List</option>
                                                    <option value="allusers">All Users</option>
                                                    <option value="client">Customers</option>
                                                    <option value="staff">Staff</option>
                                                </select>

                                            </form>
                                        </th>
                                    </tr>
                                </thead>

                                <c:forEach var="customer" items="${customers}">
                                    <tbody>
                                        <tr>
                                            <td>${customer.name}</td>
                                            <td>${customer.email}</td>
                                            <td>${customer.phoneNo}</td>
                                            <td style="text-align:center">
                                                <form action="managecustomers" method="POST" >
                                                    <button type="button submit" class="btn btn-outline-secondary btn-sm">Details</button>
                                                    <input type="hidden" name="action" value="view">
                                                    <input type="hidden" name="selectedCustomer" value="${customer.userId}">
                                                </form>
                                            </td>
                                            <td style="text-align:center">
                                                <form action="managecustomers" method="POST" >
                                                    <button type="button submit" class="btn btn-outline-secondary btn-sm">Edit</button>
                                                    <input type="hidden" name="action" value="edit">
                                                    <input type="hidden" name="selectedCustomer" value="${customer.userId}">
                                                </form>
                                            </td>
                                            <td style="text-align:center">
                                                <form action="managecustomers" method="POST" >
                                                    <button type="button submit" class="btn btn-outline-danger btn-sm">Delete</button>
                                                    <input type="hidden" name="action" value="delete">
                                                    <input type="hidden" name="selectedCustomer" value="${customer.userId}">
                                                </form>
                                            </td>
                                        </tr>
                                    </tbody>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="container" id="addUser" style="display: none">
                    <div class="row">
                        <div class="col-md-12">
                            <h3>Add User</h3>
                            <form action="managecustomers" name="registration" method="POST">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <div id="inputHeader">Name</div>
                                        <input type="text" class="form-control" name="name" required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <div id="inputHeader">Address</div>
                                        <input type="text" class="form-control" name="address" required>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-3">
                                        <div id="inputHeader">Postal Code</div>
                                        <input type="text" class="form-control" name="postal" required>
                                    </div>

                                    <div class="form-group col-md-3">
                                        <div id="inputHeader">Account Type</div>
                                        <select class="form-control" name="account" name="accounttype">
                                            <option value="1">Regular User</option>
                                            <option value="2">Admin User</option>                                                  
                                        </select>
                                    </div>

                                    <div class="form-group col-md-6">
                                        <div id="inputHeader">Email Address</div>
                                        <input type="email" class="form-control" name="email" required>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-4">
                                        <label for="inputCity">Phone Number</label>
                                        <input type="text" class="form-control" name="phone" required>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="inputState">Username</label>
                                        <input type="text" class="form-control" name="username" required>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="inputZip">Password</label>
                                        <input type="password" class="form-control" name="password" required>
                                    </div>
                                </div>
                                <br>
                                <div class="col-md-12 text-md-right">
                                    <button type="button submit" class="btn btn-success">Register User</button>
                                    <input type="hidden" name="action" value="add">
                                </div>
                            </form>    
                            <br>

                        </div>
                    </div>
                </div>
            </div>


        </div>
        <script>

            $(function () {
                $("form[name='registration']").validate({
                    rules: {
                        password: {
                            required: true,
                            minlength: 8
                        }, phone: {
                            required: true,
                            phoneUS: true
                        }, postal: {
                            required: true,
                            pattern: '^[a-zA-z]{1}[0-9]{1}[a-zA-z]{1}[0-9]{1}[a-zA-Z]{1}[0-9]{1}'
                        }
                    },
                    messages: {
                        password: {
                            required: "Please provide a password",
                            minlength: "Your password must be at least 8 characters long"
                        },
                        phone: {
                            required: "Please enter a phone number",
                            phoneUS: "Please format as 111-222-3333"
                        }, postal: {
                            required: "Please enter your postal code",
                            pattern: "Please format as A1B2D3"
                        }
                    },
                    errorElement: 'div',
                    errorLabelContainer: '.errorTxt'
                });
            });
        </script>
    </body>
</html>

