<%-- 
    Document   : managecustomers
    Created on : March 1st, 2019
    Author     : Adam Schlinker, Kim Nguyen
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">        
        <style>


            #headerButton{
                margin-right: 5em;
            }

            #buttons{
                padding: 0 5px;
            }
        </style>

        <style><%@include file="/WEB-INF/styles/adminhome.css"%></style>
        <style><%@include file="/WEB-INF/styles/navbar.css"%></style>
        <link rel="shortcut icon" href="<c:url value='/images/hdclogo.png'/>">

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clH="TMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">


        <title>Manage Cakes</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-custom">
            <div class="container">
                <a class="navbar-brand mb-0 h1" href="adminhome"><img class="icon" src="<c:url value='/images/hdclogo.png'/>" /></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fas fa-bars"></i>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="manageorders">Orders</a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="managecakes">Cakes</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="managecustomers">Users</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="managepickups">Pickups</a>
                        </li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <li class="nav-item"><a class="nav-link" href="login?act=logout"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
                    </ul>
                </div>
            </div>
        </nav> 

        <br>
        <div class="card">
            <div class="card-body"> 
                <div class="row">
                    <div class="col-md-4">
                        <p id="headertitle"><strong>Manage Cakes</strong></p>
                    </div>
                    <div class="col-md-4 text-md-center">${notification}</div>

                    <div class="col-md-4">


                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-row float-right">

                                    <form action="managecakes" method="post" class="ml-2 mr-5">
                                        <button type="button submit" class="btn btn-secondary btn-sm" aria-pressed="true">Add New Cake</button>
                                        <input type="hidden" name="action" value="add">

                                    </form>
                                    <form action="managecakes" method="post" class="mr-3">
                                        <button type="button submit" class="btn btn-danger btn-sm" aria-pressed="true">Undo Delete</button>
                                        <input type="hidden" name="action" value="undo">
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>




                </div>
                <hr>


                <div class="container-fluid">

                    <table class="table table-bordered">
                        <thead>
                        <th style="width: 15%">Image</th>
                        <th>Name</th>
                        <th>Size</th>
                        <th>Price</th>
                        <th>Description</th>                       
                        <th></th>
                        <th></th>
                        </thead>
                        <tbody>

                            <c:forEach var="cake" items="${cakes}">
                                <tr>
                                    <td><img src="<c:url value='${cake.image}'/>" alt="Cake Picture" width="100%" height="50%"/></td>

                                    <td>${cake.name}</td>
                                    <td>${cake.size}</td>
                                    <td>${cake.price}</td>
                                    <td>${cake.description}</td>

                                    <td>
                                        <form action="changecake" method="get">
                                            <button type="button submit" class="btn btn-outline-secondary btn-sm" aria-pressed="true">Edit</button>
                                            <input type="hidden" name="selectedCakeId" value="${cake.cakeId}">
                                        </form>
                                    </td>
                                    <td>
                                        <form action="managecakes" method="post" >
                                            <button type="button submit" class="btn btn-outline-danger btn-sm" aria-pressed="true">Delete</button>
                                            <input type="hidden" name="action" value="delete">
                                            <input type="hidden" name="selectedCakeId" value="${cake.cakeId}">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>
                </div>
            </div>
        </div>

    </body>
</html>
