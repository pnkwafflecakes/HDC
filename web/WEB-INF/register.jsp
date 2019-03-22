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
        <p style="color:red" align="center">${error}</p>
        <p style="color:green">${status}</p>
        <div class="container">
            <div class="row">
                <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                    <div class="card card-signin my-5">
                        <div class="card-body">
                            <h5 class="card-title text-center">Sign Up</h5>
                            <form class="form-signin" method="POST">
                                <div class="form-label-group">
                                    <input type="text" id="inputUser" name="username" class="form-control" placeholder="Email address" required autofocus>
                                    <label for="inputUser">Username</label>
                                </div>

                                <div class="form-label-group">
                                    <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
                                    <label for="inputPassword">Password</label>
                                </div>

                                <div class="form-label-group">
                                    <input type="text" id="inputName" name="name" class="form-control" placeholder="Full Name" required autofocus>
                                    <label for="inputName">Full name</label>
                                </div>

                                <div class="form-label-group">
                                    <input type="text" id="inputAddress" name="address" class="form-control" placeholder="Address" required autofocus>
                                    <label for="inputAddress">Address</label>
                                </div>

                                <div class="form-label-group">
                                    <input type="text" id="inputPostal" name="postal" class="form-control" placeholder="Postal Code" required autofocus>
                                    <label for="inputPostal">Postal Code</label>
                                </div>

                                <div class="form-label-group">
                                    <input type="text" id="inputEmail" name="email" class="form-control" placeholder="Email Address" required autofocus>
                                    <label for="inputEmail">Email Address</label>
                                </div>

                                <div class="form-label-group">
                                    <input type="text" id="inputPhone" name="phone" class="form-control" placeholder="Email Address" required autofocus>
                                    <label for="inputPhone">Phone Number</label>
                                </div>

                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Register</button>
                                <hr class="my-4">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>


</html>
