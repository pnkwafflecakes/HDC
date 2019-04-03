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
                                主页
                            </a>
                            <span class="sr-only">(current)</span>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="browse">
                                浏览
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                联系我们
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="cart">
                                购物车
                                <span class="badge badge-pill badge-secondary">${fn:length(cakes)}</span></a>
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
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="manageaccount">我的账号</a>
                                    <a class="dropdown-item" href="orders">我的订单</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="login?act=logout"><span class="glyphicon glyphicon-log-out"></span> 登出</a>
                                </div>
                            </li>
                        </c:if>
                        <c:if test="${userObj == null}">
                            <li class="nav-item">
                                <a class="nav-link" href="login">
                                    登录/注册
                                </a>
                            </li>
                        </c:if>

                        <li class="nav-item">
                            <a class="nav-link" href="lang?act=en"><i class="fas fa-globe-americas"></i>  English </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container mt-3">
            <div class="row">
                <div class="col-12">
                    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#carouselExampleControls" data-slide-to="0" class="active"></li>
                            <li data-target="#carouselExampleControls" data-slide-to="1"></li>
                            <li data-target="#carouselExampleControls" data-slide-to="2"></li>
                        </ol>
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img class="img-responsive" src="<c:url value='${cake.image}'/>" />
                                <img alt="First slide" width="900" height="500" class="d-block w-100" src="<c:url value='/images/home1.jpg'/>" />
                                <div class="carousel-caption d-none d-md-block">
                                    <h5>
                                        ${cake1.namecn}
                                    </h5>
                                    <p>
                                        ${cake1.descriptioncn}
                                    </p>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <img src="images/home2.jpg" alt="Second slide" width="749" height="499" class="d-block w-100">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5>
                                        ${cake2.namecn}
                                    </h5>
                                    <p>
                                        ${cake2.descriptioncn}
                                    </p>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <img src="images/home3.jpg" alt="Third slide" width="891" height="500" class="d-block w-100">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5>
                                        ${cake3.namecn}
                                    </h5>
                                    <p>
                                        ${cake3.descriptioncn}
                                    </p>
                                </div>
                            </div>
                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <hr>
        <h2 class="text-center">
            精选蛋糕
        </h2>
        <hr>
        <div class="container">
            <div class="row text-center">
                <div class="col-md-4 pb-1 pb-md-0">
                    <div class="card">
                        <img class="card-img-top" alt="Card image cap" src="<c:url value='${cake1.image}'/>" />
                        <div class="card-body">
                            <h5 class="card-title">
                                ${cake1.namecn}
                            </h5>
                            <p class="card-text">
                                ${cake1.descriptioncn}
                            </p>
                            <a href="cakeinfo?cakeid=${cake1.cakeId}" class="btn btn-outline-secondary">加入购物车</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 pb-1 pb-md-0">
                    <div class="card">
                        <img class="card-img-top" alt="Card image cap" src="<c:url value='${cake2.image}'/>" />
                        <div class="card-body">
                            <h5 class="card-title">
                                ${cake2.namecn}
                            </h5>
                            <p class="card-text">                                
                                ${cake2.descriptioncn}
                            </p>
                            <a href="cakeinfo?cakeid=${cake2.cakeId}" class="btn btn-outline-secondary">加入购物车</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 pb-1 pb-md-0">
                    <div class="card">
                        <img class="card-img-top" alt="Card image cap" src="<c:url value='${cake3.image}'/>" />
                        <div class="card-body">
                            <h5 class="card-title">
                                ${cake3.namecn}
                            </h5>
                            <p class="card-text">
                                ${cake3.descriptioncn}
                            </p>
                            <a href="cakeinfo?cakeid=${cake3.cakeId}" class="btn btn-outline-secondary">加入购物车</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row text-center mt-4">
                <div class="col-md-4 pb-1 pb-md-0">
                    <div class="card">
                        <img class="card-img-top" alt="Card image cap" src="<c:url value='${cake4.image}'/>" />
                        <div class="card-body">
                            <h5 class="card-title">$
                                ${cake4.namecn}
                            </h5>
                            <p class="card-text">
                                ${cake4.descriptioncn}
                            </p>
                            <a href="cakeinfo?cakeid=${cake4.cakeId}" class="btn btn-outline-secondary">加入购物车</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 pb-1 pb-md-0">
                    <div class="card">
                        <img class="card-img-top" alt="Card image cap" src="<c:url value='${cake5.image}'/>" />
                        <div class="card-body">
                            <h5 class="card-title">
                                ${cake5.namecn}
                            </h5>
                            <p class="card-text">
                                ${cake5.descriptioncn}
                            </p>
                            <a href="cakeinfo?cakeid=${cake5.cakeId}" class="btn btn-outline-secondary">加入购物车</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 pb-1 pb-md-0">
                    <div class="card">
                        <img class="card-img-top" alt="Card image cap" src="<c:url value='${cake6.image}'/>" />
                        <div class="card-body">
                            <h5 class="card-title">
                                ${cake6.namecn}
                            </h5>
                            <p class="card-text">
                                ${cake6.descriptioncn}
                            </p>
                            <a href="cakeinfo?cakeid=${cake6.cakeId}" class="btn btn-outline-secondary">加入购物车</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
                            <h6 class="text-uppercase font-weight-bold footertext">海燕美味蛋糕</h6>
                            <hr class="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
                            <p class="footertext">Here you can use rows and columns here to organize your footer content. Lorem ipsum dolor sit amet, consectetur
                                adipisicing elit.</p>

                        </div>
                        <!-- Grid column -->

                        <!-- Grid column -->
                        <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">

                            <!-- Links -->
                            <h6 class="text-uppercase font-weight-bold footertext">关注我们</h6>
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
                            <h6 class="text-uppercase font-weight-bold footertext">联系方式</h6>
                            <hr class="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
                            <p class="footertext">
                                <i class="fas fa-home mr-3 "></i>188 Springbluff Blvd SW <br>Calgary, AB</p>
                            <p class="footertext">
                                <i class="fas fa-envelope mr-3 "></i>  <a href="mailto:#">helen@gmail.com</a></p>
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
