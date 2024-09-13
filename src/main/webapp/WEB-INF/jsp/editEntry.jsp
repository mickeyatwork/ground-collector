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
  <title>Edit Entry</title>
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

    img[src=""] {
      display: none;
    }
  </style>
  <!-- Custom styles for this template -->
  <link href="navbar-top.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div class="container">
  <div>
    <h1>Edit Entry</h1>
    <br>
  </div>
  <form:form modelAttribute="editEntry">
    <form:errors path="*" cssClass="errorblock" element="div" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <div><label for="homeTeamId">Home Team: </label>
      <select name="homeTeamId" id="homeTeamId" onchange="document.getElementById('homeBadge').src=$(this).find('option:selected').data('img')" required>
        <option value="${editingEntry.homeTeamId}" data-img="${editingLog.homeBadge}">${editingLog.homeTeam}</option>
        <c:forEach items="${competitionNames}" var="competitionName" varStatus="loop">
          <c:if test="${ competitionName.type  == 'League'}">
            <optgroup label="${competitionName.name}">
        <c:forEach items="${teamName}" var="teamName" varStatus="loop">
          <c:if test="${ teamName.leagueId == competitionName.id}">
          <option value="${teamName.id}" data-img="${teamName.logo}">
              ${teamName.name}
          </option>
          </c:if>
        </c:forEach>
        </optgroup>
        </c:if>
        </c:forEach>
      </select>
      <img id=homeBadge src="" height=40px width=40px>
    </div>
    <div><label for="awayTeamId">Away Team: </label>
      <select name="awayTeamId" id="awayTeamId" onchange="document.getElementById('awayBadge').src=$(this).find('option:selected').data('img')" required>
        <option value="${editingEntry.awayTeamId}" data-img="${editingLog.awayBadge}">${editingLog.awayTeam}</option>
        <c:forEach items="${competitionNames}" var="competitionName" varStatus="loop">
        <c:if test="${ competitionName.type  == 'League'}">
        <optgroup label="${competitionName.name}">
          <c:forEach items="${teamName}" var="teamName" varStatus="loop">
            <c:if test="${ teamName.leagueId == competitionName.id}">
            <option value="${teamName.id}" data-img="${teamName.logo}">
              ${teamName.name}
          </option>
          </c:if>
          </c:forEach>
        </optgroup>
        </c:if>
        </c:forEach>
      </select>
      <img id=awayBadge src="" height=40px width=40px>
    </div>
    <div><label for="groundId">Venue: </label>
      <select name="groundId" id="groundId" required>
        <option value="${editingEntry.groundId}">${editingLog.ground}</option>
        <c:forEach items="${groundsName}" var="groundsName" varStatus="loop">
          <option value="${groundsName.id}">
              ${groundsName.name} (${groundsName.homeTeam})
          </option>
        </c:forEach>
      </select>
    </div>
    <div><label for="competitionId">Competition: </label>
      <select name="competitionId" id="competitionId" required>
        <option value="${editingEntry.competitionId}">${editingLog.competition}</option>
        <c:forEach items="${competitionNames}" var="competitionName" varStatus="loop">
          <option value="${competitionName.id}">
              ${competitionName.name}
          </option>
        </c:forEach>
      </select>
    </div>
    <div><label> Date Visited: <input type="date" value=${editingLog.dateVisited} name="dateVisited" required /></label></div>
    <div><label> Score: <input type="text" name="homeScore" value=${editHomeScore} style="width: 25px" min="0"/> - <input type="text" name="awayScore" value=${editAwayScore} style="width: 25px" min="0"/> </label></div>
    <div><label> Notes: <input type="text" name="notes" value="${editingLog.notes}"/></label></div>
    <br/>
    <input type="submit" class="btn btn-lg btn-primary" role="button" value="Save Changes"/>
  </form:form>
<form:form modelAttribute="editEntry">
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



