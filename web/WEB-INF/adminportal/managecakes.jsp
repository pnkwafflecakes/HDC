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
        <style>

            div.c
            {
                text-align: center;
            } 
            h1
            {
                color: #ba7823;
            }
        </style>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Administration</title>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="mainmenu">Helen's Delicious Cakes</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="manageorders">Orders</a></li>
                    <li class="active"><a href="managecakes">Cakes</a></li>
                    <li><a href="managecustomers">Customers</a></li>
                    <li><a href="managepickups">Pickups</a></li>
                    <li><a href="managefeedback">Feedback</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="adminhome"><span class="glyphicon glyphicon-user"></span> Admin Home</a></li>
                    <li><a href="login"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
                </ul>
            </div>
        </nav> 

        <div class="c">
            <h1>Manage Cakes</h1>
            <h3>${notification}</h3>
            
        </div>

        <div class="container-fluid">
            <form action="managecakes" method="post" >
                <input type="submit" value="Undo">
                <input type="hidden" name="action" value="undo">
            </form>
            
            <table class="table table-bordered table-striped table-hover">
                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
                <thead>
                <th>Image</th>
                <th>Name</th>
                <th>Category</th>
                <th>Size</th>
                <th>Price</th>
                <th>Description</th>
                <th>Featured</th>
                <th>Special</th>
                <th>Edit</th>
                <th>Delete</th>
                </thead>
                <tbody>
                    <tr>
                        <td COLSPAN=10 align="center">
                            <form action="managecakes" method="post" >
                                <input type="submit" value="Add New Cake">
                                <input type="hidden" name="action" value="add">
                            </form>
                        </td>
                    </tr>
                    <c:forEach var="cake" items="${cakes}">
                        <tr>
                            <td><img src="<c:url value='${cake.image}'/>" alt="Cake Picture" width="50%" height="50%"/></td>
                            
                            <td>${cake.name}</td>
                            <td>${cake.categoryId.description}</td>
                            <td>${cake.size}</td>
                            <td>${cake.price}</td>
                            <td>${cake.description}</td>
                            <td>
                                <input type="checkbox" name="featuredCheck" disabled="disabled"
                                    <c:if test="${cake.featured == true}">checked="checked"</c:if>
/>                          </td>
                            <td>
                                <input type="checkbox" name="specialCheck" disabled="disabled"
                                    <c:if test="${cake.special == true}">checked="checked"</c:if>
/>                          </td>
                            <td>
                                <form action="managecakes" method="post" >
                                    <input type="submit" value="Edit">
                                    <input type="hidden" name="action" value="edit">
                                    <input type="hidden" name="selectedCakeId" value="${cake.cakeId}">
                                </form>
                            </td>
                            <td>
                                <form action="managecakes" method="post" >
                                    <input type="submit" value="Delete">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="selectedCakeId" value="${cake.cakeId}">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>
        </div>

    </body>
</html>
