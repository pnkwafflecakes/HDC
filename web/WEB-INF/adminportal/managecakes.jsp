<%-- 
    Document   : managecakes
    Created on : Feb 21, 2019, 3:50:20 PM
    Author     : 775224
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
            <c:forEach var="item" items="${cakes}">
            <tr>
                <td><img src="<c:url value='${cake.image}'/>" alt="Cake Picture" width="50%" height="50%"/></td>
                <td>${cakes.cakeId}</td>
                <td>${cakes.name}</td>
                <td>${cakes.price}</td>
                <td>${cakes.description}</td>
                <td>
                    <form action="admin" method="post" >
                    <input type="submit" value="Edit">
                    <input type="hidden" name="action" value="edit">
                    <input type="hidden" name="selectedCakeId" value="${cakes.cakeId}">
                     </form>
                </td>
                <td>
                    <form action="admin" method="post" >
                    <input type="submit" value="Delete">
                    <input type="hidden" name="action" value="Delete">
                    <input type="hidden" name="selectedCakeId" value="${cakes.cakeId}">
                     </form>
                </td>
            </tr>
        </table>
    </body>
</html>
