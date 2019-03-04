<%-- 
    `cake_id` int(4) NOT NULL,
    `category_id` int(4) NOT NULL,
    `name` VARCHAR(99) NOT NULL,
    -- Category variable is redundant
    `size` int(2), -- Change from char, will be in inches THE DI
    `price` double (6,2) NOT NULL,
    `description` VARCHAR(300) NOT NULL, -- it be here
    `image` VARCHAR(99) NOT NULL,
    `featured` boolean, --Added
    `special` boolean, --Added
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
            <tr>
                <td>Image on file</td>
                <td>Cake Id</td>
                <td>Name</td>
                <td>Category_id</td>
                <td>Size</td>
                <td>Price</td>
                <td>Description</td>
                <td>Featured</td>
                <td>Special</td>
                <td>Edit</td>
                <td>Delete</td>
            </tr>
            <c:forEach var="cake" items="${cakes}">
            <tr>
                <td><img src="<c:url value='${cake.image}'/>" alt="Cake Picture" width="50%" height="50%"/></td>
                <td>${cake.cakeId}</td>
                <td>${cake.name}</td>
                <td>${cake.categoryId.categoryId}</td>
                <td>${cake.size}</td>
                <td>${cake.price}</td>
                <td>${cake.description}</td>
                <td>${cake.featured}</td>
                <td>${cake.special}</td>
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
                    <input type="hidden" name="action" value="Delete">
                    <input type="hidden" name="selectedCakeId" value="${cake.cakeId}">
                     </form>
                </td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
