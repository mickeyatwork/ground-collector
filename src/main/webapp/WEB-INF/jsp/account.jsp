<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <title>Create Account</title>
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
        <h1>Create Account</h1>
    </div>


    <div class="error">
        <c:if test="${not empty param.error}" >Invalid or duplicate details</c:if>
    </div>
    <%-- TODO Add error messages to form, mainly to alert to duplicate usernames / email addresses
    <div class="error">
        <c:if test="${not empty param.error}" >User details invalid format or already exist</c:if>
    </div>--%>

    <!-- TODO add registration form to login page-->
    <div><srong>This app is currently only available through invite only</srong><br>
        Registration will need to be verified by our team before you can log in for the first time<br></div>
    <form:form modelAttribute="account" method="post">
        <form:errors path="*" cssClass="errorblock" element="div" />
        <div><label> Username : <input type="text" name="username" required /> </label></div>
        <div><label> Email : <input type="text" name="email" required /> </label></div>
        <div><label> First Name : <input type="text" name="firstName" required /> </label></div>
        <div><label> Last Name : <input type="text" name="lastName" required /> </label></div>
        <div><label for="favouriteTeam">Favourite Team: </label>
            <select name="favouriteTeam" id="favouriteTeam" required>
                <option value="">--Select Option--</option>
                <c:forEach items="${teamName}" var="teamName" varStatus="loop">
                    <option value="${teamName.id}">
                            ${teamName.name}
                    </option>
                </c:forEach>
               <!-- <option value="Other">Other</option>-->
            </select>
        </div>
        <div><label> Password: <input type="password" name="password" required /> </label></div>
        <div><label> Confirm Password: <input type="password" name="matchingPassword" required /> </label></div>
        <input type="submit" class="btn btn-lg btn-primary" role="button" value="Submit"/>
    </form:form>




    <div class="control-group">
    </div>
</div>
</body>
</html>