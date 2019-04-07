<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Pickups</title>
       <style><%@include file="/WEB-INF/styles/manageorders.css"%></style>
        <!-- Bootstrap - Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!--  Bootstrap - Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

        <!--  Bootstrap - Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </head>
    <body>
        
        
        <!--start header-->
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="mainmenu">Helen's Delicious Cakes</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="manageorders">Orders</a></li>
                    <li><a href="managecakes">Cakes</a></li>
                    <li><a href="managecustomers">Customers</a></li>
                    <li class="active"><a href="managepickups">Pickups</a></li>
                    <li><a href="managefeedback">Feedback</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="adminhome"><span class="glyphicon glyphicon-user"></span> Admin Home</a></li>
                    <li><a href="login"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
                </ul>
            </div>
        </nav> 
        <!--end header-->
       
        
    
        <p>${errorMessage}</p>
        
         <div class = "col-sm-3">
             <!--can admin add cake--> 
                <c:if test="${selectedPickup == null}">
                    <h3>New Pickup</h3>
                    <form action="managepickups" method="POST">
                         <table>
                                <tr><td>Pickup Description</td><td><input type="text" name="selectedPickupName" value=""></td></tr>
                                <tr><td>Pickup Address</td><td><input type="text" name="selectedPickupAddress" value=""></td></tr>   
                                <input type="hidden" name="action" value="add">
                        </table>
                        <input type="submit" value="Save">
                    </form>
                </c:if>
                <c:if test="${selectedPickup != null}">
                    <h3>Edit Pickup</h3>
                    <form action="managepickups" method="POST">

                        <table>

                                <tr><td>Pickup Description</td><td><input type="text" name="pickupName" value="${selectedPickup.pickupName}"></td></tr>
                                <tr><td>Pickup Address</td><td><input type="text" name="pickupAddress" value="${selectedPickup.pickupAddress}"></td></tr>  
                                <input type="hidden" name="selectedPickupId" value="${selectedPickup.pickupId}">
                                <input type="hidden" name="action" value="edit">
                       
                                <tr><td><input type="submit" value="Save"></td></tr>
                    <!--table to show cakes info in this order-->
                        </table>
                                 
                    </form>  
                </c:if> 
         </div>
             
        <div class="col-sm-9">
            <h3>Pickup Locations</h3>
            
            <form action="managepickups" method="post" >
                <input type="submit" value="Undo">
                <input type="hidden" name="action" value="undo">
            </form>
            <table>
           
                   <th>Pickup Id</th>
                   <th>Pickup Notes</th>
                   <th>Pickup Address</th>
                   <th>Delete</th>
                   <th>Edit</th>

               <c:forEach var="pickups" items="${pickups}">               
                   <tr>
                       <td>${pickups.pickupId}</td>
                       <td>${pickups.pickupName}</td>
                       <td>${pickups.pickupAddress}</td>
                       <td>
                           <form action="managepickups" method="post" >
                               <input type="submit" value="Delete">
                               <input type="hidden" name="action" value="delete">
                               <input type="hidden" name="selectedPickupId" value="${pickups.pickupId}">
                           </form>
                       </td>
                       <td>
                           <form action="managepickups" method="post">
                               <input type="submit" value="Edit">
                               <input type="hidden" name="action" value="view">
                               <input type="hidden" name="selectedPickupId" value="${pickups.pickupId}">
                           </form>
                       </td>
                   </tr>
                   
               </c:forEach>
           </table>
            
        </div>
           
        

       
    </body>
</html>
