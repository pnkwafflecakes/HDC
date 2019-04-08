<%-- 
    Document   : editcake
    Created on : Feb 21, 2019, 7:42:13 PM
    Author     : 775224
--%>

<%-- 
   <td>${cakes.cakeId}</td>
                <td>${cakes.name}</td>
                <td>${cakes.category_id}</td>
                <td>${cakes.size}</td>
                <td>${cakes.price}</td>
                <td>${cakes.description}</td>
                <td>${cakes.featured}</td>
                <td>${cakes.special}</td>
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <title>JSP Page</title>
    </head>
    <body>
        <form action="upload?input=${input}" method="post" enctype="multipart/form-data">
            Image: <input type="file" name="file">
            <input type="submit" value="Upload Image">
        </form>
        <form action="editcake" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <c:if test="${input == 'edit' && changed==null}">
                        <td><img src="<c:url value='${cake.image}'/>" alt="Cake Picture" width="200" height="200"/></td>
                    </c:if>
                    <c:if test="${changed == '1'}">
                        <td><img src="<c:url value='${imagePath}'/>" alt="${imagePath}" name="newImage" width="200" height="200"/></td>
                    </c:if>
                </tr>
                <tr>
                    <td>Name: <input type="text" name="name" value="${cake.name}"></td>
                    <td>Chinese Name: <input type="text" name="namecn" value="${cake.namecn}"></td>
                    <td>Category: 
                        <select name="categorySelect">
                            <c:if test="${input == 'edit'}">
                                <option value="${selectedCategory.categoryId}" selected="selected">${selectedCategory.name}</option>
                            </c:if>
                            <c:forEach var="categories" items="${categories}">
                                <option value="${categories.categoryId}">${categories.name}</option>
                            </c:forEach>
                        </select>
                        
                    </td>
                    <td>Size: <input type="number" name="size" value="${cake.size}"></td>
                    <td>Description: <input type="textbox" name="description" value="${cake.description}"></td>
                    <td>Chinese Description: <input type="textbox" name="descriptioncn" value="${cake.descriptioncn}"></td>
                    <td>Price: <input type="number" name="price" value="${cake.price}"></td>
                    <td>Featured: <input type="checkbox" <c:if test="${cake.featured==true}">checked</c:if> name="featured"></td>
                    <td>Special: <input type="checkbox" <c:if test="${cake.special==true}">checked</c:if> name="special"></td>
                </tr>
            </table>
                <c:if test="${input == 'edit'}">
                <input type="submit" value="Edit">
                <input type="hidden" name="selectedCakeId" value=${cake.cakeId}>
                <input type="hidden" name="action" value="edit">
                </c:if>
                <c:if test="${input == 'add'}">
                <input type="submit" value="Add">
                <input type="hidden" name="action" value="add">
                </c:if>
        </form>
    </body>
</html>
