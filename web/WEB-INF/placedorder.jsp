<%-- 
    Document   : mainmenu
    Created on : Feb 7, 2019, 2:45:09 PM
    Author     : 703842
--%>

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
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="mainmenu">
                                <c:if test="${(language == null)||(language == 'en') }">
                                    Home
                                </c:if>
                                <c:if test="${language == 'ch'}">
                                    主页
                                </c:if>
                                <span class="sr-only">(current)</span></a>
                        </li>
                        
                        


                        <li class="nav-item"> </li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">

                        <c:if test="${userObj != null}">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="glyphicon glyphicon-user">
                                    </span> ${userObj.name} 
                                </a>
                                
                            </li>
                        </c:if>
                        <c:if test="${userObj == null}">
                            <li class="nav-item">
                                <a class="nav-link" href="login"><span class="glyphicon glyphicon-user"></span>
                                    <c:if test="${(language == null)||(language == 'en') }">
                                        Login/Register
                                    </c:if>
                                    <c:if test="${language == 'ch'}">
                                        登录/注册
                                    </c:if>
                                </a>
                            </li>
                        </c:if>

                        
                    </ul>




                </div>
            </div>
        </nav>


        <div class="container-fluid">
            <br>
            <h2 class="text-center">Congratulations!</h2>
            <h2 class="text-center">Order placed successful! Your order is being processed.</h2>
            <br>
            <div class="text-center">
<!--                <i>You're being direct to home page in <span id="countdowntimer">5 </span> Seconds</i>-->
                <br>
                <a href="mainmenu" class="btn btn-outline-dark">Take me to home page</a>
            </div>

        </div>
<!--        <script type="text/javascript">
            var timeleft = 5;
            var downloadTimer = setInterval(function () {
                timeleft--;
                document.getElementById("countdowntimer").textContent = timeleft;
                if (timeleft <= 0)
                    clearInterval(downloadTimer);
            }, 500);

            function leave() {
                window.location = "mainmenu";
            }
            setTimeout("leave()", 5000);
        </script>-->


        <br>
        <br>
        <hr>

        <div class="containter" id="bottomfooter">
            <!-- Footer -->
            <br>
            <footer class="page-footer font-small unique-color-dark">


                <!-- Footer Links -->
                <div class="container text-center text-md-left mt-5">

                    <!-- Grid row -->
                    <div class="row mt-3">

                        <!-- Grid column -->
                        <div class="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">

                            <!-- Content -->
                            <h6 class="text-uppercase font-weight-bold footertext">Hellen Delicious Cakes, Inc.</h6>
                            <hr class="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
                            <p class="footertext">Here you can use rows and columns here to organize your footer content. Lorem ipsum dolor sit amet, consectetur
                                adipisicing elit.</p>

                        </div>
                        <!-- Grid column -->

                        <!-- Grid column -->
                        <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">

                            <!-- Links -->
                            <h6 class="text-uppercase font-weight-bold footertext">Follow Us</h6>
                            <hr class="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
                            <p>
                                <a href="#" class="fab fa-facebook footertext"> facebook</a>  
                            </p>
                            <p>
                                <a href="#" class="fab fa-instagram footertext"> instagram</a> 
                            </p>
                            <p>
                                <a href="#" class="fab fa-weixin footertext"> wechat</a>                            </p>
                            </p>


                        </div>
                        <!-- Grid column -->

                        <!-- Grid column -->
                        <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">

                            <!-- Links -->
                            <h6 class="text-uppercase font-weight-bold footertext">Contact</h6>
                            <hr class="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
                            <p class="footertext">
                                <i class="fas fa-home mr-3 "></i>188 Springbluff Blvd SW <br>Calgary, AB</p>
                            <p class="footertext">
                                <i class="fas fa-envelope mr-3 "></i>  <a href="mailto:helenbkf@gmail.com?Subject=Customer%20Contact" target="_top">helenbkf@gmail.com</a></p>
                            <p class="footertext">
                                <i class="fas fa-phone mr-3 "></i>(403) 808-3860</p>

                        </div>
                        <!-- Grid column -->

                    </div>
                    <!-- Grid row -->

                </div>
                <!-- Footer Links -->

                <!-- Copyright -->
                <div class="footer-copyright text-center py-3 footertext">
                    Copyright © Helen's Delicious Cakes. All rights reserved
                </div>
                <!-- Copyright -->

            </footer>
            <!-- Footer -->
        </div>

    </body>
</html> 
