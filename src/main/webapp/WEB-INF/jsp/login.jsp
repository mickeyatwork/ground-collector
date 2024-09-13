<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <title>Login</title>
    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" integrity="9IVa20McObhMr6JXg6G9MW6nh7IgLsddsd7zslDrJuIhyu15CCWzSx/uB57RaFQ8" crossorigin="anonymous">

    <meta name="theme-color" content="#563d7c">


    <style>
        .error {
            color: #ff0000;
        }

        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>
    <!-- Custom styles for this template -->
    <link href="navbar-top.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div>
        <h1>Login</h1>
    </div>

    <c:if test="${not empty param.logout}" >
        <div class="alert alert-success" role="alert">
            Logout was successful!
        </div>
    </c:if>

    <div class="error">
        <c:if test="${not empty param.error}" >Invalid username and password.</c:if>
    </div>


    <form:form action="perform_login" method="post">
        <form:errors path="*" cssClass="errorblock" element="div" />
        <div><label> Username: <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <div><label> Remember Me: <input type="checkbox" name="remember-me" /> </label></div>
        <input type="submit" class="btn btn-lg btn-primary" role="button" value="Login"/>
    </form:form>

    <div class="control-group">
    </div>
</div>
</body>
</html>