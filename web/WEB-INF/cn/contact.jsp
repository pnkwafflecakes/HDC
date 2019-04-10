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

        <!--<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

        <style><%@include file="/WEB-INF/styles/navbar.css"%></style>
        <style><%@include file="/WEB-INF/styles/contact.css"%></style>


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
                        <li class="nav-item">
                            <a class="nav-link" href="browse">浏览</a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="contact">联系我们
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="cart">购物车<span class="badge badge-pill badge-secondary">${fn:length(cakes)}</span></a>
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
                                    <a class="dropdown-item" href="#">我的账号</a>
                                    <a class="dropdown-item" href="#">我的订单</a>
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
                            <a class="nav-link" href="lang?act=en"><i class="fas fa-globe-americas"></i>  English </a>
                        </li>


                    </ul>
                </div>
            </div>
        </nav>


        <hr>

        <div class="containter-fluid" id="maincontainter">
            <h1 class="text-center">海燕美味蛋糕</h1>
            <br>
            <p class="centralize">由海燕精心制作的蛋糕松软可口、细腻绵软、甜度适中，非常适合中国人的口味！ 所用原料如鸡蛋、水果等均从Costco新鲜采购，健康美味、力求完美。
            </p>
            <br>

            <br>
            <div class="centralize">
                <div class="row">
                    <div class="col-md-6">
                        <img  class="img-fluid rounded" src="<c:url value='/images/orderline.png'/>" />
                    </div>
                    <div class="col-md-6">
                        <span class="align-middle">
                            <h3 class="text-center">联系我们</h3>

                            <p><b>电话:</b> (403) 603-0087
                                <br>
                               <b>Email:</b> 
                               <a href="mailto:helenbkf@gmail.com?Subject=Customer%20Contact" target="_top">helenbkf@gmail.com</a>
                                <br>
                                需要加急服务，请马上拨打电话
                            </p>
                        </span>
                    </div>
                </div>
            </div>

            <br>
            <br>
            <h3 class="text-center">卡尔加里送货服务</h3>
            <br>
            <div class= "centralize">
                <table class="table">
                    <thead>
                    <th>短途</th>
                    <th>中途</th>
                    <th>长途</th>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Downtown</td>
                            <td>Inner city</td>
                            <td>Suburban</td>
                        </tr>
                        <tr>
                            <td>到达时间 9:30 and 12:30</td>
                            <td>到达时间 1:30 and 4:30</td>
                            <td>到达时间 1:30 and 4:30</td>
                        </tr>
                        <tr>
                            <td>$12.00</td>
                            <td>$17.00</td>
                            <td>$23.00</td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <br>
            <br>
            <h3 class="text-center">取货地点</h3>
            <div id="accordion" class="centralize">
                <div class="card">
                    <div class="card-header" id="headingOnea">
                        <h5 class="mb-0">
                            <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOnea" aria-expanded="true" aria-controls="collapseOnea">
                                69 Street Park & Ride Surface
                            </button>
                        </h5>
                    </div>

                    <div id="collapseOnea" class="collapse" aria-labelledby="headingOnea" data-parent="#accordion">
                        <div class="card-body">
                            <p>69 Street Park & Ride Surface
                            </p>
                            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2508.8562967973535!2d-114.1897035344972!3d51.03727480278851!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x8935ae0b7d60b724!2s69+Street+Park+%26+Ride+Surface!5e0!3m2!1sen!2sca!4v1554489840511!5m2!1sen!2sca" width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header" id="headingTwoa">
                        <h5 class="mb-0">
                            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwoa" aria-expanded="false" aria-controls="collapseTwoa">
                                Tom Baines School North Parking Lot
                            </button>
                        </h5>
                    </div>
                    <div id="collapseTwoa" class="collapse" aria-labelledby="headingTwoa" data-parent="#accordion">
                        <div class="card-body">
                            <p>250 Edgepark Blvd NW, Calgary, AB T3A 3S2 
                            </p>
                            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2503.6419109020003!2d-114.15920428449373!3d51.13351284579232!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x537168bd9125ea71%3A0xe55a098ea3d7e240!2s250+Edgepark+Blvd+NW%2C+Calgary%2C+AB+T3A+3S2!5e0!3m2!1sen!2sca!4v1554490083658!5m2!1sen!2sca" width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header" id="headingThreea">
                        <h5 class="mb-0">
                            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThreea" aria-expanded="false" aria-controls="collapseThreea">
                                T&T North HSBC Bank Parking Lot                       
                            </button>
                        </h5>
                    </div>
                    <div id="collapseThreea" class="collapse" aria-labelledby="headingThreea" data-parent="#accordion">
                        <div class="card-body">
                            <p>409-9650 Harvest Hills Blvd N, Calgary, AB T3K 0B3
                            </p>
                            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2503.2480556209257!2d-114.07045408449342!3d51.14077664526386!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x53716687bd59b025%3A0x85939682d8f0529e!2s9650+Harvest+Hills+Blvd+N+Unit+409%2C+Calgary%2C+AB+T3K+0B3!5e0!3m2!1sen!2sca!4v1554490150996!5m2!1sen!2sca" width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header" id="headingFoura">
                        <h5 class="mb-0">
                            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseFoura" aria-expanded="false" aria-controls="collapseFoura">
                                Nickle School Parking Lot      
                            </button>
                        </h5>
                    </div>
                    <div id="collapseFoura" class="collapse" aria-labelledby="headingFoura" data-parent="#accordion">
                        <div class="card-body">
                            <p>2500 Lake Bonavista Dr SE, Calgary, AB T2J 2Y6
                            </p>
                            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2514.07887431746!2d-114.06364788450072!3d50.94075435979567!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x537176c73fc9e7d7%3A0x2be716626afc5776!2s2500+Lake+Bonavista+Dr+SE%2C+Calgary%2C+AB+T2J+2Y6!5e0!3m2!1sen!2sca!4v1554490227438!5m2!1sen!2sca" width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header" id="headingFivea">
                        <h5 class="mb-0">
                            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseFivea" aria-expanded="false" aria-controls="collapseFivea">
                                Somerset Station Parking Lot     
                            </button>
                        </h5>
                    </div>
                    <div id="collapseFivea" class="collapse" aria-labelledby="headingFivea" data-parent="#accordion">
                        <div class="card-body">
                            <p>288 Shawville Way SE Parking</p>
                            <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d5032.591774224743!2d-114.066968!3d50.899741!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xfc0166cafe59f052!2s288+Shawville+Way+SE+Parking!5e0!3m2!1sen!2sca!4v1554618697625!5m2!1sen!2sca" width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
                        </div>
                    </div>
                </div>
            </div>




            <br>
            <br>
            <h3 class="text-center">FAQ</h3>
            <div id="accordion" class="centralize">
                <div class="card">
                    <div class="card-header" id="headingOne">
                        <h5 class="mb-0">
                            <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                怎样下订单？
                            </button>
                        </h5>
                    </div>

                    <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
                        <div class="card-body">
                            <ul>
                                <li>
                                   下订单请提供以下信息：
                                </li>
                                <li>
                                    姓名及联系方式
                                </li>
                                <li>
                                    取货或送货的日期和时间
                                </li>
                                <li>
                                    取货或送货的地点
                                </li>
                                <li>
                                    订单明细包括蛋糕的数量和名称
                                </li>
                                <li>
                                    选择INTERRAC e-transfer or PAYPAL支付
                                </li>
                            </ul>


                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header" id="headingTwo">
                        <h5 class="mb-0">
                            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                订单取消及修改
                            </button>
                        </h5>
                    </div>
                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                        <div class="card-body">
                            如需更改或取消蛋糕订单，请最少在交货时间前48小时与我们联系。
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header" id="headingThree">
                        <h5 class="mb-0">
                            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                我需要提前多久订蛋糕？
                            </button>
                        </h5>
                    </div>
                    <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
                        <div class="card-body">
                            我们非常高兴能为您提供美味的蛋糕。为了保证蛋糕的新鲜，请至少在需要蛋糕的日期前48小时下单。（周末是需求高峰期，需要更多的时间准备。特殊要求请直接和我们联系）
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header" id="headingFour">
                        <h5 class="mb-0">
                            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                蛋糕注意事项
                            </button>
                        </h5>
                    </div>
                    <div id="collapseFour" class="collapse" aria-labelledby="headingFour" data-parent="#accordion">
                        <div class="card-body">
                            <ul>
                                <li>蛋糕在烘焙的当天享用最为鲜美。原包装放入冰箱保鲜层，可以保持过夜后仍然新鲜
                                </li>
                                <li>请避免阳光直射蛋糕，可能会造成奶油融化并影响色泽。
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

        </div>




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
                            <p class="footertext">由海燕精心制作的蛋糕松软可口、细腻绵软、甜度适中，适合所有人的口味</p>

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
                     版权所有 © 海燕美味蛋糕
                </div>
                <!-- Copyright -->

            </footer>
            <!-- Footer -->
        </div>


    </body>
</html> 
