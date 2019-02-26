<%-- 
    Document   : editcake
    Created on : Feb 21, 2019, 7:42:13 PM
    Author     : 775224
--%>

<%-- 
   <td>${cakes.cakeId}</td>
                <td>${cakes.name}</td>
                <td>${cakes.price}</td>
                <td>${cakes.description}</td>
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="editcake" method="post">
            <table>
                <tr>
                    <td><img src="<c:url value='${cake.image}'/>" alt="Cake Picture" width="200" height="200"/></td>
                    <td>Image: <input type="file" name="picture" accept="image/*"></td>
                </tr>
                <tr>
                    <td>Name: <input type="text" name="name" value="${cake.name}"></td>
                    <td>Description: <input type="text" name="description" value="${cake.description}"></td>
                    <td>Price: <input type="text" name="price" value="${cake.price}"></td>
                </tr>
            </table>
                <input type="submit" value="Add">
                <input type="hidden" name="action" value="add">
        </form>
                
    </body>
</html>
