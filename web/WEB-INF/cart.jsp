<%-- 
    Document   : cakeinfo
    Created on : Feb 4, 2019, 3:37:48 PM
    Author     : 744916
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <title>Cart</title>
    </head>
    <body>
        <h1>Cake info</h1>
        <p>${cart}</p>
        <h1>Hello Cart!</h1>

        Current Cake: ${currCake.name}
    </body>
</html>
