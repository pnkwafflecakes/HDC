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
                                    <a class="dropdown-item" href="#">My Profile</a>
                                    <a class="dropdown-item" href="#">My Orders</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="login?act=logout"><span class="glyphicon glyphicon-log-out"></span> Log Out</a>
                                </div>
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

                        <!--button toggle ch/en-->
                        <c:if test="${(language == null)||(language == 'en') }">
                            <li class="nav-item">
                                <a class="nav-link" href="login?act=ch&amp;page=cakeinfo">  中文 </a>
                            </li>
                        </c:if>
                        <c:if test="${language == 'ch'}">
                            <li class="nav-item">
                                <a class="nav-link" href="login?act=en&amp;page=cakeinfo">  English </a>
                            </li>
                        </c:if>
                    </ul>




                </div>
            </div>
        </nav>


        <div class="container">

            <div class="title">
                <h1>Helen's Delicious Cakes</h1>
            </div>

            <div class="cakeinfo">
                <div class="image">
                    <img src="<c:url value='${currCake.image}'/>" alt="Cake Picture" height="240" width="240">
                </div>

                <div class="info">
                    <ul>
                        <li>${currCake.name}</li>
                        <li>$${currCake.price}</li>
                        <li>${currCake.size}"</li>
                    </ul>

                    <p><strong>Description:</strong> ${currCake.description}</p>

                    <div class="button">
                        <form action="cakeinfo" method="POST">
                            <!--cake quantity between 1 to 20-->
                            <p><input type="number" name="quantity" value="1" min="1" max="20" style="width: 20%;"></p>
                            <input type="hidden" name="cakeId" value="${currCake.cakeId}">
                            <input type="submit" value="Add To Cart" style="width: 20%;">
                        </form>

                        <form action="mainmenu" method="get">
                        <input type="submit" value="Continue Shopping" name="data" style="width: 20%;">
                        &nbsp
                        </form>
                    </div>
                </div>
            </div>

            <div class="clearfix"></div>
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
                                <li class="btn-link"> <a>About us</a> </li>
                                <li class="btn-link"> <a>Contact</a> </li>
                                <li class="btn-link"> <a>Map</a> </li>
                            </ul>
                        </div>

                    </div>
                </div>
                <div class="col-md-4 col-lg-5 col-6">
                    <address>
                        <strong>Hellen Delicious Cakes, Inc.</strong><br>
                        Indian Treasure Link<br>
                        Quitman, WA, 99110-0219<br>
                        <abbr title="Phone">P:</abbr> (123) 456-7890
                    </address>
                    <address>
                        <strong>Full Name</strong><br>
                        <a href="mailto:#">first.last@example.com</a>
                    </address>
                </div>
            </div>
        </div>
        <footer class="text-center">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <p>Copyright © MyWebsite. All rights reserved.</p>
                    </div>
                </div>
            </div>
        </footer>

    </body>
</html> 
