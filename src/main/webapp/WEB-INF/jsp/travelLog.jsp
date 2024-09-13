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
    <title>Travel Log</title>
    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" integrity="9IVa20McObhMr6JXg6G9MW6nh7IgLsddsd7zslDrJuIhyu15CCWzSx/uB57RaFQ8" crossorigin="anonymous">

    <meta name="theme-color" content="#563d7c">


    <style>
        .error {
            color: #ff0000;
        }

    </style>
    <!-- Custom styles for this template -->
    <link href="navbar-top.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div class="container">
    <div>
        <h1>Travel Log</h1>
    </div>

    <table>
        <tr>
            <td>
                <h4>Your travel log.
                    <br> Entries are ordered by latest</h4>
                <p>-------------------------------------</p>
            </td>
        </tr>
        <%-- TODO Pagination needed for long lists--%>
        <c:forEach items="${travelLogList}" var="travelLogList" varStatus="loop">
        <tr>
            <td>
                <p><strong><a style="text-underline: #000000; color: #000000" href="editEntry?entId=${travelLogList.id}">Edit</a></strong> | <strong><a style="color: #000000;" href="deleteEntry?entId=${travelLogList.id}">Delete</a></strong></p>
                <p><strong>Fixture:</strong> <img src='${travelLogList.homeBadge}' height=30px width=30px> ${travelLogList.homeTeam} vs ${travelLogList.awayTeam} <img src='${travelLogList.awayBadge}' height=30px width=30px></p>
                <p><strong>Score:</strong> ${travelLogList.score}</p>
                <p><strong>Competition:</strong> ${travelLogList.competition}</p>
                <p><strong>Date:</strong> ${travelLogList.dateVisited}</p>
                <p><strong>Venue:</strong> ${travelLogList.ground}</p>
                <p><strong>Notes:</strong> ${travelLogList.notes}</p>
                <p>-------------------------------------</p>

            </td>
        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>



