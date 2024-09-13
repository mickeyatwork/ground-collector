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
        <h1>Ground Checklist</h1>
    </div>
    <div>
        <table>
            <tr>
                <td style="vertical-align: top; padding-right: 30px;">
                    <h2><strong><u>Visited</u></strong></h2>
                    <table>
                    <c:forEach items="${visitedGrounds}" var="visitedGrounds" varStatus="loop">
                        <tr>
                            <td>
                                <p> <img src="${pageContext.request.contextPath}/images/tick.png"/></p>
                            </td>
                            <td>
                                <p> ${visitedGrounds.name} (${visitedGrounds.homeTeam})</p>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                </td>
                <td style="vertical-align: top; padding-left: 30px;">
                    <h2><strong><u>Remaining</u></strong></h2>
                    <table>
                        <c:forEach items="${allGrounds}" var="allGrounds" varStatus="loop">
<%-- TODO if statement needs to refrence the visited values <c:if test="${ allGrounds.name  == "> --%>
                            <tr>
                                <td>
                                    <p> ${allGrounds.name} (${allGrounds.homeTeam})</p>
                                </td>
                            </tr>
                            <%--</c:if>--%>
                        </c:forEach>
                    </table>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>



