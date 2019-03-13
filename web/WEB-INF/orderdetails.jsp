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

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

        <style><%@include file="/WEB-INF/mainmenu/popper.min.js"%></style>

    </head>
    <body>

        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
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
                                <span class="badge">${fn:length(cakes)}</span></a>
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
                                <a class="nav-link" href="login?act=ch&amp;page=orderdetails">  中文 </a>
                            </li>
                        </c:if>
                        <c:if test="${language == 'ch'}">
                            <li class="nav-item">
                                <a class="nav-link" href="login?act=en&amp;page=orderdetails">  English </a>
                            </li>
                        </c:if>
                    </ul>




                </div>
            </div>
        </nav>


        <div class="container">
            <h1 class="text-center">Order Details</h1>

            <form action="orderdetails" method="post">
                <div class="form-row">

                    <div class="form-group col-md-6">
                        <label for="inputName">Name</label>
                        <input type="text" class="form-control" id="inputName" name="name" value="${userObj.name}" readonly>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputPhone">Phone Number</label>
                        <input type="text" class="form-control" id="inputPhone" name="phoneNo" value=${userObj.phoneNo} readonly>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-12">
                        <label for="inputAddress">Address</label>
                        <input type="text" class="form-control" id="inputAddress" name="address" value="${userObj.address}" readonly>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-12">
                        <label for="delivery">Delivery Method</label>               
<!--                        <select class="form-control" id="delivery">
                            <option> Train Station </option>
                            <option> Home Delivery </option>
                            </select>-->
                        <input type="text" class="form-control" id="delivery"  name="method" value="Delivery" readonly>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <label for="inputNotes">Notes</label>
                        <textarea class="form-control" rows="3" id="inputNotes"  name="notes" >Cash on delivery</textarea>
                    </div>
                </div>
                <div class="col text-center">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </form>
        </div>

        <hr>
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
