<%-- 
    Document   : admin
    Created on : Feb 21, 2019, 1:57:55 PM
    Author     : 744916
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Portal</title>
    </head>
    <body>
        <h1>Administration</h1>

        <strong>Current User: </strong>${currUser.username}<br>
        <br>

        <c:if test="${currUser.getRole().getRoleID() == 1}">
            <a href="index?logout">Logout | </a>
            <a href="account?edit">Account | </a>
            <a href="company">Companies | </a>
            <a href="notes">Notes</a>
        </c:if>

        <c:if test="${currUser.getRole().getRoleID() == 3}">
            <a href="index?logout">Logout | </a>
            <a href="account?edit">Account | </a>
            <a href="notes">Notes</a>
        </c:if>
        ${errorMessage}

        <table>
            <tr>
                <th>Username</th>
                <th>Password</th>
                <th>Email</th>
                <th>Active</th>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Company</th>
                <th>Role</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.email}</td>
                    <td>${user.active}</td>
                    <td>${user.firstname}</td>
                    <td>${user.lastname}</td>
                    <td>${user.getCompany().getCompanyName()}</td>
                    <td>${user.getRole().getRoleName()}</td>
                    <td>
                        <form action="admin" method="POST" >
                            <input type="submit" value="Delete">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="selectedUsername" value="${user.username}">
                        </form>
                    </td>
                    <td>
                        <form action="admin" method="GET">
                            <input type="submit" value="Edit">
                            <input type="hidden" name="action" value="view">
                            <input type="hidden" name="selectedUsername" value="${user.username}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <c:if test="${selectedUser == null}">
            <h2>Add User</h2>

            <form action="admin" method="POST">
                <strong>Username: </strong><input type="text" name="username"><br>
                <strong>Password: </strong><input type="text" name="password"><br>
                <strong>Email: </strong><input type="text" name="email"><br>
                <strong>Firstname: </strong><input type="text" name="firstname"><br>
                <strong>Lastname: </strong><input type="text" name="lastname"><br>

                <c:if test="${currUser.getRole().getRoleID() == 1}">
                    Company:
                    <select name="company">
                        <option value="1">SAIT</option>
                        <option value="2">Star Trek</option>
                        <option value="3">My Little Pony</option>
                    </select><br>
                </c:if>

                <c:if test="${currUser.getRole().getRoleID() == 1}">
                    Role:
                    <select name="role">
                        <option value="1">System Admin</option>
                        <option value="2">Regular User</option>
                        <option value="3">Company Admin</option>
                    </select><br>
                </c:if> 

                <c:if test="${currUser.getRole().getRoleID() == 3}">
                    Role:
                    <select name="role">
                        <option value="2">Regular User</option>
                        <option value="3">Company Admin</option>
                    </select><br>
                </c:if>

                <input type="hidden" name="action" value="add">
                <input type="submit" value="Add">
            </form>
        </c:if>

        <c:if test="${selectedUser != null}">
            <h2>Edit User</h2>

            <form action="admin" method="POST">
                <strong>Username: ${selectedUser.username}</strong><input type="hidden" name="username" value="${selectedUser.username}"><br>
                <strong>Password: </strong><input type="text" name="password" value="${selectedUser.password}"><br>
                <strong>Email: </strong><input type="text" name="email" value="${selectedUser.email}"><br>
                <strong>Firstname: </strong><input type="text" name="firstname" value="${selectedUser.firstname}"><br>
                <strong>Lastname: </strong><input type="text" name="lastname" value="${selectedUser.lastname}"><br>

                <c:if test="${currUser.getRole().getRoleID() == 1}">
                    Company:
                    <select name="company">
                        <option value="1">SAIT</option>
                        <option value="2">Star Trek</option>
                        <option value="3">My Little Pony</option>
                    </select><br>
                </c:if>

                <c:if test="${currUser.getRole().getRoleID() == 1}">
                    Role:
                    <select name="role">
                        <option value="1">System Admin</option>
                        <option value="2">Regular User</option>
                        <option value="3">Company Admin</option>
                    </select><br>
                </c:if> 

                <c:if test="${currUser.getRole().getRoleID() == 3}">
                    Role:
                    <select name="role">
                        <option value="2">Regular User</option>
                        <option value="3">Company Admin</option>
                    </select><br>
                </c:if>

                <input type="hidden" name="action" value="edit">
                <input type="submit" value="Save">
            </form>
        </c:if>
    </body>
</html>
