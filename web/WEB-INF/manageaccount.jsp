<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Helen Delicious Cakes</title>

        <!--bootstrap heading-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

        <style><%@include file="/WEB-INF/styles/navbar.css"%></style>
        <style><%@include file="/WEB-INF/styles/mainmenu.css"%></style>


    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-custom">
            <div class="container">
                <a class="navbar-brand" href="#"> H D C </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fas fa-bars"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="mainmenu">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="browse">Browse</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="contact">Contact
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="cart">Cart<span class="badge badge-pill badge-secondary">${fn:length(cakes)}</span></a>
                        </li>
                        <li class="nav-item"> </li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">

                        <form class="form-inline my-2 my-lg-0" action="search" method="post">
                            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="searchWord">
                            <input type="hidden" name="action" value="Search">
                        </form>


                        <c:if test="${userObj != null}">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-user-circle"></i> 
                                    ${userObj.name} 
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="manageaccount">My Profile</a>
                                    <a class="dropdown-item" href="orders">My Orders</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="login?act=logout"><i class="fas fa-sign-out-alt"></i> Log Out</a>
                                </div>
                            </li>
                        </c:if>
                        <c:if test="${userObj == null}">
                            <li class="nav-item">
                                <a class="nav-link" href="login">
                                    Login/Register
                                </a>
                            </li>
                        </c:if>

                        <!--button toggle ch/en-->
                        <li class="nav-item">
                            <a class="nav-link" href="lang?act=cn"><i class="fas fa-globe-americas"></i>  中文 </a>
                        </li>


                    </ul>
                </div>
            </div>
        </nav>


        <div class="container">
            <h1>${prompt}</h1>
            <form action="manageaccount" method="post" >
                <div class="form-label-group">
                    <label for="inputUser">Username</label>
                    <input type="text" id="inputUser" name="username" class="form-control" value="${sessionScope.userObj.username}" contenteditable="false">
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

            <hr>
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
