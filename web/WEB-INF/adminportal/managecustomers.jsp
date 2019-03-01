<%-- 
    Document   : manacustomers.jsp
    Created on : March 1st, 2019
    Author     : Adam Schlinker
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile</title>
        <style>
            h1 {
                color: #ba7823;
            }

        </style>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="login">NotesKeepr</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="login">Administration</a></li>
                    <li><a href="notes">Notes</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <div id="google_translate_element"></div>

                        <script type="text/javascript">
                            function googleTranslateElementInit() {
                                new google.translate.TranslateElement({pageLanguage: 'en'}, 'google_translate_element');
                            }
                        </script>

                        <script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
                    </li>
                    <li><a href="account"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
                    <li><a href="account?act=logout"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
                </ul>
            </div>
        </nav> 

        <h1 align="center">User Profile</h1>
        <table align="center">
            <tr>
                <th>Username:</th>
                <td>${auser.username}</td>
            </tr>
            <tr>
                <th>password:</th>
                <td>${auser.password}</td>
            </tr>
            <tr>
                <th>Email:</th>
                <td>${auser.email}</td>
            </tr>
            <tr>
                <th>Active: </th>
                <td>${auser.active}</td>
            </tr>
            <tr>
                <th>First name:</th>
                <td>${auser.firstname}</td>
            </tr>
            <tr>
                <th>Last name:</th>
                <td>${auser.lastname}</td>
            </tr>
            <tr>
                <th>Role:</th>
                <td>${auser.role.roleName}</td>
            </tr>
            <tr>
                <th>Company:</th>
                <td>${auser.company.companyName}</td>
            </tr>
        </table>
        <br>
        <div align="center">
            <form action="admin" method="post">
                <input type="submit" value="Edit Profile">
                <input type="hidden" name="action" value="edit">
                <input type="hidden" name="selectedUsername" value="${auser.username}">
            </form>
        </div>


    </body>
</html>
