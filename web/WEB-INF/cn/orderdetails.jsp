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
        <link rel="shortcut icon" href="<c:url value='/images/hdclogo.png'/>">

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
                <a class="navbar-brand" href="mainmenu"><img class="icon" src="<c:url value='/images/hdclogo.png'/>" /> </a>
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
                        <li class="nav-item">
                            <a class="nav-link" href="contact">联系我们
                            </a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="cart">购物车<span class="badge badge-pill badge-secondary">${fn:length(cakes)}</span></a>
                        </li>
                        <li class="nav-item"> </li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">

<!--                        <form class="form-inline my-2 my-lg-0" action="search" method="post">
                            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="searchWord">
                            <input type="hidden" name="action" value="Search">
                        </form>-->


                        <c:if test="${userObj != null}">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-user-circle"></i> 
                                    ${userObj.name} 
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="manageaccount">我的账号</a>
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

        <div class="container">
            <br>

            <h1 class="text-center">送货信息</h1>
            ${errorMessage}
            <br>

            <form action="orderdetails" method="POST">
                <div class="form-row">

                    <div class="form-group col-md-6">
                        <label for="inputName">姓名</label>
                        <input type="text" class="form-control" id="inputName" name="name" value="${userObj.name}" required="required">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputPhone">电话号码</label>
                        <input type="text" placeholder="###-###-####" pattern="^\(?([0-9]{3})\)?[-.●]?([0-9]{3})[-.●]?([0-9]{4})$" class="form-control" id="inputPhone" name="phoneNo" value="${userObj.phoneNo}" required="required">
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-12">
                        <label for="inputAddress">地址</label>
                        <input type="text" class="form-control" id="inputAddress" name="address" value="${userObj.address}" required="required">
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="delivery">送货方式</label>               
                        <select class="form-control" id="delivery" name="deliveryList">
                            <option value="Home Delivery"> 送货上门 </option>
                            <option value="69 Street Park & Ride Surface"> 取货地点:69 Street Park & Ride Surface </option>
                            <option value="取货地点:Tom Baines School North Parking Lot"> 取货地点:Tom Baines School North Parking Lot </option>
                            <option value="取货地点:T&T North HSBC Bank Parking Lot"> 取货地点:T&T North HSBC Bank Parking Lot </option>
                            <option value="取货地点:Nickle School Parking Lot"> 取货地点:Nickle School Parking Lot </option>
                            <option value="取货地点:Somerset Station Parking Lot"> 取货地点:Somerset Station Parking Lot </option>
                        </select>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-24">
                            <label for="inputDueDate">需求时间</label>
                            <input type="date" class="form-control" id="inputDueDate" name="dueDate" >
                            <script type="text/javascript">

                                $("#inputDueDate").val(getFormattedDate(tomorrow()));

                                function tomorrow() {
                                    return today().getTime() + 24 * 60 * 60 * 1000;
                                }

                            </script>
                        </div>

                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <label for="inputNotes">备注</label>
                        <textarea class="form-control" rows="3" id="inputNotes"  name="notes" placeholder="Additional notes here..." ></textarea>
                    </div>
                </div>
                <c:if test="${userObj == null}">
                    <p>Warning: If you are not logged in you will not get to monitor order progress. You can still continue and contact Helen By: </p>
                    <p>Phone: (403) 808-3860</p>
                    <p>Email: helen@gmail.com</p>
                </c:if>
        </div>
        <div class="col text-center">
            <button type="submit" class="btn btn-outline-dark"><h3>提交订单</h3></button>
        </div>
    </form>
</div>



<hr>

        <footer class="page-footer font-small" id="bottomfooter">
            <br>
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-5 text-center">
                        <div style="margin: auto; width: 70%;">
                            <h6 class="text-uppercase font-weight-bold footertext">海燕美味蛋糕</h6>
                            <hr class="col-md-8">
                            <p class="footertext">由海燕精心制作的蛋糕松软可口、细腻绵软、甜度适中，适合所有人的口味</p>
                        </div>

                    </div>
                    <div class="col-md-2 text-center" style="margin: auto;">
                        <h6 class="text-uppercase font-weight-bold footertext">关注我们</h6>
                        <hr class="col-md-8">

                        <p>
                            <a href="#" class="fab fa-instagram footertext"> instagram</a> 
                        </p>
                        <p>
                            <a href="#" class="fab fa-weixin footertext"> wechat</a>
                        </p>

                    </div>
                    <div class="col-md-5 text-center" style="margin: auto;">
                        <h6 class="text-uppercase font-weight-bold footertext">联系方式</h6>
                        <hr class="col-md-5">
                        <p class="footertext">
                            <i class="fas fa-envelope mr-3 "></i>  <a href="mailto:helenbkf@gmail.com?Subject=Customer%20Contact" target="_top">helenbkf@gmail.com</a>
                        </p>
                        <p class="footertext">
                            <i class="fas fa-phone mr-3 "></i>(403) 603-0087
                        </p>
                    </div>
                </div>
            </div>
            <div class="footer-copyright text-center py-3 footertext">
                版权所有 © 海燕美味蛋糕 
            </div>
        </footer>


</body>
</html> 
