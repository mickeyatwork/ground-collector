<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <title>Team Update</title>
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
<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div class="container">
    <div>
        <h1>Individual Team Update</h1>
    </div>
    <form:form modelAttribute="teamUpdate">
        <form:errors path="*" cssClass="errorblock" element="div" />
        <label ><br>
            <p>Update an individual team by name </p>
            <p>If the team name doesn't exist yet, they will be added to the database<br>
                (Only as long as applicable information can be found from the search API)</p>
        </label>
        <div><label> Search team name: <input type="text" name="teamName" required /></label></div>
        <div><br>Choose a League to add them to:</div>
        <div><label for="leagueId">Available Leagues: </label>
            <select name="leagueId" id="leagueId" >
                <option value="">--Select Option--</option>
                <c:forEach items="${competitionNames}" var="competitionNames" varStatus="loop">
                    <option value="${competitionNames.id}">
                            ${competitionNames.name}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div><br>Select home ground:</div>
        <div><label for="groundId">Available grounds: </label>
            <select name="groundId" id="groundId" required>
                <option value="">--Select Option--</option>
                <c:forEach items="${groundsName}" var="groundsName" varStatus="loop">
                    <option value="${groundsName.id}">
                            ${groundsName.name} (${groundsName.homeTeam})
                    </option>
                </c:forEach>
            </select>
        </div>
        <br>
        <input type="submit" class="btn btn-lg btn-primary" role="button" value="Update"/>
    </form:form>
    <div class="control-group">
    </div>
</div>
</body>
</html>



