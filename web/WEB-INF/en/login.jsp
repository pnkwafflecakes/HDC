<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clH="TMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

        <style><%@include file="/WEB-INF/styles/login.css"%></style>
        <style><%@include file="/WEB-INF/styles/navbar.css"%></style>

    </head>

    <body>
        <nav class="navbar navbar-expand-lg navbar-custom">
            <div class="container">
                <a class="navbar-brand" href="mainmenu"><img class="icon" src="<c:url value='/images/hdclogo.png'/>" />
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fas fa-bars"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="mainmenu">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="browse">Browse</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Contact
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="cart">Cart <span class="badge badge-pill badge-secondary">${fn:length(cakes)}</span></a>
                        </li>
                        <li class="nav-item"> </li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">

                        <form class="form-inline my-2 my-lg-0" action="search" method="post">
                            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="searchWord">
                            <input type="hidden" name="action" value="Search">
                        </form>


                        <li class="nav-item active">
                            <a class="nav-link" href="login">
                                Login/Register
                            </a>
                        </li>

                        <!--button toggle ch/en-->
                        <li class="nav-item">
                            <a class="nav-link" href="lang?act=cn"><i class="fas fa-globe-americas"></i>  中文 </a>
                        </li>


                    </ul>
                </div>
            </div>
        </nav>
        <hr>

        <div class="container">

            <div class="row">

                <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">

                    <div class="card card-signin my-5">
                        <div class="card-body">
                            <h5 class="card-title text-center">Welcome to Helen's Delicious Cakes</h5>
                            <h5 class="card-title text-center" style="font-style: italic;">Please log in</h5>
                            <h5 class="card-title text-center" style="color: orange">${errorMessage}</h5>


                            <form class="form-signin" action="login" method="POST">
                                <div class="form-label-group">
                                    <input type="text" id="inputUser" name="user" class="form-control" placeholder="Username" required autofocus>
                                    <label for="inputUser">Username</label>
                                </div>

                                <div class="form-label-group">
                                    <input type="password" id="inputPassword" name="pass" class="form-control" placeholder="Password" required>
                                    <label for="inputPassword">Password</label>
                                </div>

                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Sign in</button>
                                <hr class="my-4">
                                <a href="register" class="btn btn-lg btn-google btn-block text-uppercase" role="button">Register</a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>


</html>
