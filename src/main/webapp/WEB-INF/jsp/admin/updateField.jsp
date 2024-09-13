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

    <script>
        function redirectUrl() {
            location.replace("http://localhost:8080/GroundsCollector/updateTeamFields")
        }
    </script>
    <!-- Custom styles for this template -->
    <link href="navbar-top.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div class="container">

    <div>
        <c:choose>
            <c:when test="${param.db=='Teams'}">
                pizza.
                <br />
            </c:when>
            <c:otherwise>
                pizzas.
                <br />
            </c:otherwise>
        </c:choose>
    </div>

    <div>
        <h1>Update Team Data</h1>
    </div>
    <div>
        <form:form modelAttribute="updateField" id="teamFieldUpdate">
        <form:errors path="*" cssClass="errorblock" element="div" />
            <div><label for="teamId"> Find team to update: </label>
                <select name="teamId" id="teamId" required>
                    <option value="">--Select Option--</option>
                    <c:forEach items="${teamName}" var="teamName" varStatus="loop">
                        <option value="${teamName.id}">
                                ${teamName.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <button href="admin/updateTeamFields?teamId=${teamName.id}>">Next</button>
            </div>
        </form:form>
    </div>
    <%--
    <form:form modelAttribute="updateField">
        <form:errors path="*" cssClass="errorblock" element="div" />
                <div><label for="teamId"> Find team to update: </label>
                    <select name="teamId" id="teamId" required>
                        <option value="">--Select Option--</option>
                        <c:forEach items="${teamName}" var="teamName" varStatus="loop">
                            <option value="${teamName.id}">
                                    ${teamName.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
        <br>
        <input type="submit" class="btn btn-lg btn-primary" role="button" value="Next">
    </form:form>
    --%>
    <div class="control-group">
    </div>
</div>
</body>
</html>



