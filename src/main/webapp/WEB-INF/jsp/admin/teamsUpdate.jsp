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
<link href="assets/css/style.css" rel="stylesheet">

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
        <h1>Team Updates</h1>
    </div>
    <form:form modelAttribute="adminTeams" action="teamsUpdate" method="post">
        <form:errors path="*" cssClass="errorblock" element="div" />
        
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success mt-3 mb-3">${successMessage}</div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger mt-3 mb-3">${errorMessage}</div>
        </c:if>
        <label >
            Bulk update <Strong>teams</Strong> by league:
        </label>
        <div><label for="leagueId">League: </label>
            <select name="leagueId" id="leagueId" >
                <option value="0">*** UPDATE ALL LEAGUES ***</option>
                <c:forEach items="${competitionNames}" var="competitionNames" varStatus="loop">
                    <option value="${competitionNames.apiId}">
                            ${competitionNames.name}
                    </option>
                </c:forEach>
            </select></div>
        <div>
            <label for="season">Season: </label>
            <input type="number" name="season" id="season" value="2026" min="2000" max="2100" />
        </div>
        <input type="submit" class="btn btn-lg btn-primary" role="button" value="Run Update"/>
    </form:form>
    <div class="control-group">
    </div>
</div>
</body>
</html>



