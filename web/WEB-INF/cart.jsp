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
        <title>Cart</title>
    </head>
    <body>
        <div align="center"></div>
        <h1>Cart</h1>
        <table cellspacing="0">
            <c:forEach var="cake" items="${cakes}">
                <tr>
                    <td><img src="<c:url value='${cake.image}'/>" alt="Cake Picture" width="50%" height="50%"/></td>
                    <td width="100">${cake.name}</td>
                    <td width="100">${cake.cakeId}</td>
                    <td width="100">${cake.price}</td>
                </tr>
            </c:forEach>
        </table>
        </div>
    </body>
</html>