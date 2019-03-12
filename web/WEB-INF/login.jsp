<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

        <style><%@include file="/WEB-INF/styles/login.css"%></style>
    </head>

    <body>
        <p align="center">${errorMessage}</p>
        <div class="container">

            <div class="row">

                <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">

                    <div class="card card-signin my-5">
                        <div class="card-body">
                            <h5 class="card-title text-center">Sign In</h5>

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
