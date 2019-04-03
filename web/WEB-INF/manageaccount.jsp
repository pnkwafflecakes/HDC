<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

        <style><%@include file="/WEB-INF/styles/navbar.css"%></style>

        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <title>Navigation Frame</title>

    </head>
    <body>

                            <div class="main">
                                <h1>${prompt}</h1>
                                <form action="manageaccount" method="post" >
                                    <div class="form-label-group">
                                        <label for="inputUser">Username</label>
                                        <input type="text" id="inputUser" name="username" class="form-control" value="${sessionScope.userObj.username}">
                                    </div>

                                    <div class="form-label-group">
                                        <label for="inputName">Name</label>
                                        <input type="text" id="inputName" name="name" class="form-control" value="${sessionScope.userObj.name}">

                                    </div>

                                    <div class="form-label-group">
                                        <label for="inputAddress">Address</label>
                                        <input type="text" id="inputAddress" name="address" class="form-control" value="${sessionScope.userObj.address}">
                                    </div>
                                    <div class="form-label-group">
                                        <label for="inputPostal">Postal Code</label>
                                        <input type="text" id="inputPostal" name="postal" class="form-control" value="${sessionScope.userObj.postalCode}">

                                    </div>

                                    <div class="form-label-group">
                                        <label for="inputEmail">Email Address</label>
                                        <input type="text" id="inputEmail" name="email" class="form-control" value="${sessionScope.userObj.email}">

                                    </div>

                                    <div class="form-label-group">
                                        <label for="inputPhone">Phone Number</label>
                                        <input type="text" id="inputPhone" name="phone" class="form-control" value="${sessionScope.userObj.phoneNo}">
                                    </div>
                                   
                                    <input type="submit" value="Confirm Change">
                                    <input type="hidden" name="action" value="change">
                                </form>

                                 
                                <form action="manageaccount" method="post" >
                                    <h1>Change Password</h1>
                                    <div class="form-label-group">
                                        <label for="inputPhone">Current Password: </label>
                                        <input text="password" name="currentPassword">
                                    </div>
                                    <br>
                                    <div class="form-label-group">
                                        <label for="inputPhone">New Password: </label>
                                        <input text="password" name="newPassword">
                                    </div>
                                    
                                    <div class="form-label-group">
                                        <label for="inputPhone">New Password Confirm: </label>
                                        <input text="password" name="newPasswordConfirm">
                                    </div>
                                        
                                    <input type="submit" value="Change Password">
                                    <input type="hidden" name="action" value="changePassword">
                                </form>
                                    
                                

        </div>

    </body>
</html>
