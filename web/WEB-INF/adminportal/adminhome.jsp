<%-- 
    Document   : adminhome
    Created on : Feb 21, 2019, 1:57:55 PM
    Author     : 744916
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style><%@include file="/WEB-INF/styles/adminhome.css"%></style>
        <title>Admin Portal</title>
    </head>
    <body>
        <h1>Administration</h1>

        <a class="button" href="managecakes">Manage Cakes</a><br>
        <a class="button" href="managecustomers?choiceCustomers=true">Manage Customers</a><br>
        <a class="button" href="managefeatured">Manage Featured Cakes</a><br>
        <a class="button" href="managefeedback">Manage Feedback</a><br>
        <a class="button" href="manageorders">Manage Orders</a><br>
        <a class="button" href="managepickups">Manage Pickup Locations</a>
    </body>
</html>
