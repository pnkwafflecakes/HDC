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
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <c:if test="${(language == null)||(language == 'en') }">
                                    Browse
                                </c:if>
                                <c:if test="${language == 'ch'}">
                                    浏览
                                </c:if>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <c:if test="${(language == null)||(language == 'en') }">
                                    Contact
                                </c:if>
                                <c:if test="${language == 'ch'}">
                                    联系我们
                                </c:if>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="cart">
                                <c:if test="${(language == null)||(language == 'en') }">
                                    Cart
                                </c:if>
                                <c:if test="${language == 'ch'}">
                                    购物车
                                </c:if>
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
                                    <a class="dropdown-item" href="#">我的账号</a>
                                    <a class="dropdown-item" href="#">我的订单</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="login?act=logout"><span class="glyphicon glyphicon-log-out"></span> 登出</a>
                                </div>
                            </li>
                        </c:if>
                        <c:if test="${userObj == null}">
                            <li class="nav-item">
                                <a class="nav-link" href="login">
                                    <c:if test="${(language == null)||(language == 'en') }">
                                        Login/Register
                                    </c:if>
                                    <c:if test="${language == 'ch'}">
                                        登录/注册
                                    </c:if>
                                </a>
                            </li>
                        </c:if>

                        <!--button toggle ch/en-->
                        <c:if test="${(language == null)||(language == 'en') }">
                            <li class="nav-item">
                                <a class="nav-link" href="login?act=ch">  中文 </a>
                            </li>
                        </c:if>
                        <c:if test="${language == 'ch'}">
                            <li class="nav-item">
                                <a class="nav-link" href="login?act=en">  English </a>
                            </li>
                        </c:if>
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
                                        <c:if test="${(language == null)||(language == 'en') }">
                                            ${cake1.name}
                                        </c:if>
                                        <c:if test="${language == 'ch'}">
                                            ${cake1.namecn}
                                        </c:if>
                                    </h5>
                                    <p>
                                        <c:if test="${(language == null)||(language == 'en') }">
                                            ${cake1.description}
                                        </c:if>
                                        <c:if test="${language == 'ch'}">
                                            ${cake1.descriptioncn}
                                        </c:if>
                                    </p>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <img src="images/home2.jpg" alt="Second slide" width="749" height="499" class="d-block w-100">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5>
                                        <c:if test="${(language == null)||(language == 'en') }">
                                            ${cake2.name}
                                        </c:if>
                                        <c:if test="${language == 'ch'}">
                                            ${cake2.namecn}
                                        </c:if>
                                    </h5>
                                    <p>
                                        <c:if test="${(language == null)||(language == 'en') }">
                                            ${cake2.description}
                                        </c:if>
                                        <c:if test="${language == 'ch'}">
                                            ${cake2.descriptioncn}
                                        </c:if>
                                    </p>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <img src="images/home3.jpg" alt="Third slide" width="891" height="500" class="d-block w-100">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5>
                                        <c:if test="${(language == null)||(language == 'en') }">
                                            ${cake3.name}
                                        </c:if>
                                        <c:if test="${language == 'ch'}">
                                            ${cake3.namecn}
                                        </c:if>
                                    </h5>
                                    <p>
                                        <c:if test="${(language == null)||(language == 'en') }">
                                            ${cake3.description}
                                        </c:if>
                                        <c:if test="${language == 'ch'}">
                                            ${cake3.descriptioncn}
                                        </c:if>
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

            <c:if test="${(language == null)||(language == 'en') }">
                RECOMMENDED CAKES
            </c:if>
            <c:if test="${language == 'ch'}">
                精选蛋糕
            </c:if>
        </h2>
        <hr>
        <div class="container">
            <div class="row text-center">
                <div class="col-md-4 pb-1 pb-md-0">
                    <div class="card">
                        <img class="card-img-top" alt="Card image cap" src="<c:url value='${cake1.image}'/>" />
                        <div class="card-body">
                            <h5 class="card-title">
                                <c:if test="${(language == null)||(language == 'en') }">
                                    ${cake1.name}
                                </c:if>
                                <c:if test="${language == 'ch'}">
                                    ${cake1.namecn}
                                </c:if>
                            </h5>
                            <p class="card-text">
                                <c:if test="${(language == null)||(language == 'en') }">
                                    ${cake1.description}
                                </c:if>
                                <c:if test="${language == 'ch'}">
                                    ${cake1.descriptioncn}
                                </c:if>
                            </p>
                            <a href="cakeinfo?cakeid=${cake1.cakeId}" class="btn btn-outline-secondary">Add to Cart</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 pb-1 pb-md-0">
                    <div class="card">
                        <img class="card-img-top" alt="Card image cap" src="<c:url value='${cake2.image}'/>" />
                        <div class="card-body">
                            <h5 class="card-title">
                                <c:if test="${(language == null)||(language == 'en') }">
                                    ${cake2.name}
                                </c:if>
                                <c:if test="${language == 'ch'}">
                                    ${cake2.namecn}
                                </c:if>
                            </h5>
                            <p class="card-text">                                
                                <c:if test="${(language == null)||(language == 'en') }">
                                    ${cake2.description}
                                </c:if>
                                <c:if test="${language == 'ch'}">
                                    ${cake2.descriptioncn}
                                </c:if>
                            </p>
                            <a href="cakeinfo?cakeid=${cake2.cakeId}" class="btn btn-outline-secondary">Add to Cart</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 pb-1 pb-md-0">
                    <div class="card">
                        <img class="card-img-top" alt="Card image cap" src="<c:url value='${cake3.image}'/>" />
                        <div class="card-body">
                            <h5 class="card-title">
                                <c:if test="${(language == null)||(language == 'en') }">
                                    ${cake3.name}
                                </c:if>
                                <c:if test="${language == 'ch'}">
                                    ${cake3.namecn}
                                </c:if>
                            </h5>
                            <p class="card-text">
                                <c:if test="${(language == null)||(language == 'en') }">
                                    ${cake3.description}
                                </c:if>
                                <c:if test="${language == 'ch'}">
                                    ${cake3.descriptioncn}
                                </c:if>
                            </p>
                            <a href="cakeinfo?cakeid=${cake3.cakeId}" class="btn btn-outline-secondary">Add to Cart</a>
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
                                <c:if test="${(language == null)||(language == 'en') }">
                                    ${cake4.name}
                                </c:if>
                                <c:if test="${language == 'ch'}">
                                    ${cake4.namecn}
                                </c:if>
                            </h5>
                            <p class="card-text">
                                <c:if test="${(language == null)||(language == 'en') }">
                                    ${cake4.description}
                                </c:if>
                                <c:if test="${language == 'ch'}">
                                    ${cake4.descriptioncn}
                                </c:if>
                            </p>
                            <a href="cakeinfo?cakeid=${cake4.cakeId}" class="btn btn-outline-secondary">Add to Cart</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 pb-1 pb-md-0">
                    <div class="card">
                        <img class="card-img-top" alt="Card image cap" src="<c:url value='${cake5.image}'/>" />
                        <div class="card-body">
                            <h5 class="card-title">
                                <c:if test="${(language == null)||(language == 'en') }">
                                    ${cake5.name}
                                </c:if>
                                <c:if test="${language == 'ch'}">
                                    ${cake5.namecn}
                                </c:if>
                            </h5>
                            <p class="card-text">
                                <c:if test="${(language == null)||(language == 'en') }">
                                    ${cake5.description}
                                </c:if>
                                <c:if test="${language == 'ch'}">
                                    ${cake5.descriptioncn}
                                </c:if>
                            </p>
                            <a href="cakeinfo?cakeid=${cake5.cakeId}" class="btn btn-outline-secondary">Add to Cart</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 pb-1 pb-md-0">
                    <div class="card">
                        <img class="card-img-top" alt="Card image cap" src="<c:url value='${cake6.image}'/>" />
                        <div class="card-body">
                            <h5 class="card-title">
                                <c:if test="${(language == null)||(language == 'en') }">
                                    ${cake6.name}
                                </c:if>
                                <c:if test="${language == 'ch'}">
                                    ${cake6.namecn}
                                </c:if>
                            </h5>
                            <p class="card-text">
                                <c:if test="${(language == null)||(language == 'en') }">
                                    ${cake6.description}
                                </c:if>
                                <c:if test="${language == 'ch'}">
                                    ${cake6.descriptioncn}
                                </c:if>
                            </p>
                            <a href="cakeinfo?cakeid=${cake6.cakeId}" class="btn btn-outline-secondary">Add to Cart</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr>

        <div class="container text-white bg-dark p-4">
            <div class="row">
                <div class="col-6 col-md-8 col-lg-7">
                    <div class="row text-center">
                        <div class="col-sm-6 col-md-4 col-lg-4 col-12">
                            <ul class="list-unstyled">

                                <li class="btn-link"> <a href="#" class="fab fa-facebook"> facebook</a> </li>
                                <li class="btn-link"> <a href="#" class="fab fa-instagram"> instagram</a> </li>
                                <li class="btn-link"> <a href="#" class="fab fa-weixin"> wechat</a> </li>

                            </ul>
                        </div>
                        <div class="col-sm-6 col-md-4 col-lg-4 col-12">
                            <ul class="list-unstyled">
                                <li class="btn-link">                                   
                                    <c:if test="${(language == null)||(language == 'en') }">
                                        <a>About us</a> 
                                    </c:if>
                                    <c:if test="${language == 'ch'}">
                                        <a>关于我们</a> 
                                    </c:if>
                                </li>
                                <li class="btn-link"> 
                                    <c:if test="${(language == null)||(language == 'en') }">
                                        <a>Contact</a> 
                                    </c:if>
                                    <c:if test="${language == 'ch'}">
                                        <a>联系方式</a> 
                                    </c:if>
                                </li>
                                <li class="btn-link"> 
                                    <c:if test="${(language == null)||(language == 'en') }">
                                        <a>Map</a>  
                                    </c:if>
                                    <c:if test="${language == 'ch'}">
                                        <a>地图</a> 
                                    </c:if>
                                </li>
                            </ul>
                        </div>

                    </div>
                </div>
                <div class="col-md-4 col-lg-5 col-6">
                    <address>
                        <strong>Hellen Delicious Cakes, Inc.</strong><br>
                        188 Springbluff Blvd SW<br>
                        Clagary, AB, T3H 5R6<br>
                        <abbr title="Phone">P:</abbr> (403) 808-3860
                    </address>
                    <address>
                        <strong>Email</strong><br>
                        <a href="mailto:#">helen@gmail.com</a>
                    </address>
                </div>
            </div>
        </div>
        <footer class="text-center">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <p>Copyright © Helen's Delicious Cakes. All rights reserved.</p>
                    </div>
                </div>
            </div>
        </footer>

    </body>
</html> 
