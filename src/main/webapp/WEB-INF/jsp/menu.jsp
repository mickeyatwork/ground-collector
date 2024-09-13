<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <title>Home</title>
    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" integrity="9IVa20McObhMr6JXg6G9MW6nh7IgLsddsd7zslDrJuIhyu15CCWzSx/uB57RaFQ8" crossorigin="anonymous">

    <meta name="theme-color" content="#563d7c">


    <style>
        .error {
            color: #ff0000;
        }

        .table_borders {
            border: 1px solid #0c5460;
            border-collapse: collapse;
        }

        img[src=""] {
            display: none;
        }
<%-- Styling we could mess around with in the future
        /* Style the links inside the navigation bar */
        .topnav a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 5px 0px;
            text-decoration: none;
            font-size: 20px;
        }

        /* Change the color of links on hover */
        .topnav a:hover {
            background-color: #01efff;
            color: #000000;
        }

        /* Add a color to the active/current link */
        .topnav a.active {
            background-color: #01efff;
            color: #c2ff00;
        }

        /* Right-aligned section inside the top navigation */
        .topnav-right {
            float: right;
        }
        --%>

    </style>
    <!-- Custom styles for this template-->
    <link href="navbar-top.css" rel="stylesheet">
</head>
<body>

<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div class="container">
    <div>
        <h2>Recent Activity & Stats</h2>
        <br>
        <p><strong>Travel Log count:</strong> ${logCount.logCount}<br></p>
    </div>
    <div>
        <p><strong>Total Grounds visited:</strong> ${logCount.uniqueVisitCount}<br></p>
    </div>
    <div>
        <p> <strong>Most Visited:</strong>
        <c:forEach items="${mostVisited}" var="mostVisited" varStatus="loop">
           ${mostVisited.visitedGround} (${mostVisited.visitCount})<br>
        </c:forEach>
        </p>
    </div>
    <div><p>-------------------------------------</p></div>
    <div>
        <table>
            <tr>
                <td><h3>Most recent entry</h3><br></td>
            </tr>
            <tr>
                <td>
                <c:choose>
                <c:when test="${menu.id==0}">
                    <p>No entries available</p>
                </c:when>
                <c:otherwise>
                    <p><strong>Fixture:</strong> <img src='${menu.homeBadge}' height=30px width=30px> ${menu.homeTeam} <span style="color: black;">Vs</span> ${menu.awayTeam} <img src='${menu.awayBadge}' height=30px width=30px></p>
                    <p><strong>Score:</strong> ${menu.score}</p>
                    <p><strong>Competition:</strong> ${menu.competition}</p>
                    <p><strong>Date:</strong> ${menu.dateVisited}</p>
                    <p><strong>Venue:</strong> ${menu.ground}</p>
                    <p><strong>Notes:</strong> ${menu.notes}</p>
                </c:otherwise>
                </c:choose>
                </td>
            </tr>
        </table>
    </div>

    <div class="control-group">
    </div>
</div>
</body>
</html>



