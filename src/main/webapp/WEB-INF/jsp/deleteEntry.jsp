<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html lang="en" >
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <title>Delete Entry</title>
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
    <h1>Delete Entry</h1>
    <br>
  </div>
  <form:form modelAttribute="deleteEntry">
    <form:errors path="*" cssClass="errorblock" element="div" />
<div>
  <p><strong>Fixture:</strong> ${deletingLog.homeTeam} vs ${deletingLog.awayTeam}</p>
  <p><strong>Score:</strong> ${deletingLog.score}</p>
  <p><strong>Competition:</strong> ${deletingLog.competition}</p>
  <p><strong>Date:</strong> ${deletingLog.dateVisited}</p>
  <p><strong>Venue:</strong> ${deletingLog.ground}</p>
  <p><strong>Notes:</strong> ${deletingLog.notes}</p>
  <br>
</div>
    <div><p>Proceeding with the below will <strong><u>permanently</u></strong> delete this record from your travel log.</p>
    </div>
    <input type="submit" class="btn btn-lg btn-secondary" style="border-color: #a71d2a; background-color: #a71d2a; color: #ffffff" role="button" value="DELETE"/>
  </form:form>
<form:form modelAttribute="deleteEntry">
  <form:errors path="*" cssClass="errorblock" element="div" />

  <div>
    <label>

    </label>
  </div>
</form:form>

  <div class="control-group">
  </div>
</div>
</body>
</html>



