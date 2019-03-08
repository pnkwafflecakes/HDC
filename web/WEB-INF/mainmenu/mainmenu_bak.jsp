<%-- 
    Document   : mainmenu
    Created on : Feb 7, 2019, 2:45:09 PM
    Author     : 703842
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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


<!--<link type="text/css" rel="stylesheet" href="<c:url value="${pagecontext.request.contextPath}/WEB-INF/mainmenu/sidebar.css" />" />--> 
        <!--<link rel="stylesheet" type="text/css" href="../indexeffect.css">-->
        <style><%@include file="/WEB-INF/mainmenu/sidebar.css"%></style>
        <style><%@include file="/WEB-INF/mainmenu/effect.css"%></style>


    </head>
    <body>

        <div class="sidenav">
            <a href="mainmenu"><i class="material-icons">home</i>  Home</a>
            <a href="#explore"><i class="material-icons">explore</i>  Explore</a>
            <a href="#favorite"><i class="material-icons">favorite</i>  Favourite</a>
            <a href="cart"><i class="material-icons">shopping_cart</i><i class="material-icons" style="font-size: 0.30em;">wb_sunny</i>  Cart</a>
            <a href="#contact"><i class="material-icons">help</i>  Contact</a>
        </div>

        <div class="main">
            <!-- cakes containers-->
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-12">
                                <h3>
                                    Feature Cakes 
                                </h3>
                                <c:forEach var="cake" items="${cakes}">
                                    <div class="container" style="margin-top:30px;">
                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                                            <div class="hovereffect">
                                                <img class="img-responsive" src="<c:url value='${cake.image}'/>" />
                                                <div class="overlay">
                                                    <h2>${cake.name}</h2>
                                                    <p><a href="cakeinfo?cakeid=${cake.cakeId}">Detail</a></p> 
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </c:forEach>

                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-md-12">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <h3>
                                    Feature Cakes 
                                </h3>
                                
<!--                                <div class="container" style="margin-top:30px;">
                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                                            <div class="hovereffect">
                                                <img class="img-responsive" src="/WEB-INF/mainmenu/cake1.jpg" alt="">
                                                <div class="overlay">
                                                    <h2>Cake Sample 1</h2>
                                                    <p><a href="#">Detail</a></p> 
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="container" style="margin-top:30px;">
                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                                            <div class="hovereffect">
                                                <img class="img-responsive" src="/WEB-INF/mainmenu/cake2.jpg" alt="">
                                                <div class="overlay">
                                                    <h2>Cake Sample 2</h2>
                                                    <p><a href="#">Detail</a></p> 
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>-->


                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </div>

    </body>
</html> 
