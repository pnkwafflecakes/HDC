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
                    <i class="fas fa-bars"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="mainmenu">主页</a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="browse">浏览</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="contact">联系我们
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
                                    <a class="dropdown-item" href="manageaccount">我的账户</a>
                                    <a class="dropdown-item" href="orders">我的订单</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="login?act=logout"><i class="fas fa-sign-out-alt"></i> 登出</a>
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

                        <!--button toggle ch/en-->
                        <li class="nav-item">
                            <a class="nav-link" href="lang?act=cn"><i class="fas fa-globe-americas"></i>  中文 </a>
                        </li>


                    </ul>
                </div>
            </div>
        </nav>


        <br>
        <div class="container">
            <h1 class="text-center">订单汇总</h1>
            <br>
            <div class="row">
                <div class="col-md-6">
                    <table class="table-bordered">
                        <tr>
                            <th>订单号</th>
                            <td>${selectedOrder.orderNo}</td>
                        </tr>
                        <tr>
                            <th>下单时间</th>
                            <td>${selectedOrder.orderDatetime}</td>
                        </tr>
                        <tr>
                            <th>需求时间</th>
                            <td>${selectedOrder.dueDatetime}</td>
                        </tr>
                        <tr>
                            <th>总金额</th>
                            <td>${selectedOrder.totalPrice}</td>
                        </tr>
                        <tr>
                            <th>送货单号</th>
                            <td>${selectedOrder.deliveryNo.deliveryNo}</td>
                        </tr>
                        <tr>
                            <th>用户名</th>
                            <td>${user.username}</td>
                        </tr>
                        <tr>
                            <th>电话号码</th>
                            <td>${user.phoneNo}</td>
                        </tr>
                        <tr>
                            <th>Email</th>
                            <td>${user.email}</td>
                        </tr>
                        <tr>
                            <th>送货方式</th>
                            <td>${delivery.method}</td>
                        </tr>
                        <tr>
                            <th>地址</th>
                            <td>${delivery.address}</td>
                        </tr>
                        <tr>
                            <th>电话号码</th>
                            <td>${delivery.phoneNo}</td>
                        </tr>
                        <tr>
                            <th>备注</th>
                            <td>${delivery.notes}</td>
                        </tr>
                    </table>
                </div>

                <div class="col-md-6">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th class="col-md-2"></th>
                                <th class="col-md-4">Name</th>
                                <th class="col-md-3">Price</th>
                                <th class="col-md-3">Qty</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="cakeOrder" items="${cakeOrders}" > 
                            <tr>
                                <td><img src="<c:url value='${cakeOrder.cake.image}'/>" alt="Cake Picture" width="80dp" height="80dp"/></td>
                                <td width="20%">${cakeOrder.cake.namecn}</td>
                                <td >${cakeOrder.cake.price}</td>
                                <td>${cakeOrder.quantity}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <hr>

                    <div>
                        <h2>请选择付款方式:</h2>

                        <div class="row">
                            <div class="col-md-6">
                                <form>
                                    <input type="radio" name="payment" value="paypal"> Paypal<br>
                                    <input type="radio" name="payment" value="etransfer"> INTERAC e-Transfer<br>
                                    <input type="radio" name="payment" value="cod"> Cash On Delivery
                                </form> 
                            </div>

                            <div class="col-md-6">
                                <div id="paypaytab" style="display: none;" class="align-middle">
                                    <form action="https://www.paypal.com/cgi-bin/webscr" method="post" target="_top">
                                        <input type="hidden" name="cmd" value="_xclick">
                                        <input type="hidden" name="business" value="YMPFXKHT6YJTC">
                                        <input type="hidden" name="lc" value="CA">
                                        <input type="hidden" name="item_name" value="Cake">
                                        <input type="hidden" name="button_subtype" value="services">
                                        <input type="hidden" name="no_note" value="1">
                                        <input type="hidden" name="no_shipping" value="1">
                                        <input type="hidden" name="rm" value="1">
                                        <input type="hidden" name="return" value="http://localhost:8084/HDCProject/payment?payment=success">
                                        <input type="hidden" name="cancel_return" value="http://localhost:8084/HDCProject/payment?payment=fail">
                                        <input type="hidden" name="currency_code" value="CAD">
                                        <input type="hidden" name="bn" value="PP-BuyNowBF:btn_buynowCC_LG.gif:NonHosted">
                                        <input type="hidden" name="amount" value="${totalPrice}">
                                        <input type="image" src="https://www.paypalobjects.com/en_US/i/btn/btn_buynowCC_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
                                        <img alt="" border="0" src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1">
                                    </form>
                                </div>

                                <div id="etranstertab" style="display: none;" class="align-middle">
                                    <form action="payment" method="GET">                                      
                                        <input type="submit" value="Place Your Order">
                                        <input type="hidden" name="payment" value="etrasfer">  
                                    </form> 
                                    <p>Please send to:helenbkf@gmail.com</p>
                                </div>

                                <div id="codtab" style="display: none;" class="align-middle">
                                    <form action="payment" method="GET">                                    
                                        <input type="submit" value="Place Your Order">
                                        <input type="hidden" name="payment" value="cash">  
                                    </form> 
                                </div>
                            </div>
                        </div>


                        <script>
                            $(document).ready(function () {
                                $('input:radio[name=payment]').change(function () {
                                    if (this.value == 'paypal') {
                                        $("#paypaytab").show();
                                        $("#etranstertab").hide();
                                        $("#codtab").hide();
                                    } else if (this.value == 'etransfer') {
                                        $("#paypaytab").hide();
                                        $("#etranstertab").show();
                                        $("#codtab").hide();
                                    } else if (this.value == 'cod') {
                                        $("#paypaytab").hide();
                                        $("#etranstertab").hide();
                                        $("#codtab").show();
                                    }
                                });
                            });
                        </script>


                    </div>

                </div>
            </div>


        </div>

        <br>
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
