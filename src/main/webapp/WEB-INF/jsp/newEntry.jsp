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
    <title>New Entry</title>
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

        .hidden{
            display: none;
        }

        /* Popup container - can be anything you want */
        .popup {
            position: relative;
            display: inline-block;
            cursor: pointer;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        /* The actual popup */
        .popup .popuptext {
            visibility: hidden;
            width: 160px;
            background-color: #555;
            color: #fff;
            text-align: center;
            border-radius: 6px;
            padding: 8px 0;
            position: absolute;
            z-index: 1;
            bottom: 125%;
            left: 50%;
            margin-left: -80px;
        }

        /* Popup arrow */
        .popup .popuptext::after {
            content: "";
            position: absolute;
            top: 100%;
            left: 50%;
            margin-left: -5px;
            border-width: 5px;
            border-style: solid;
            border-color: #555 transparent transparent transparent;
        }

        /* Toggle this class - hide and show the popup */
        .popup .show {
            visibility: visible;
            -webkit-animation: fadeIn 1s;
            animation: fadeIn 1s;
        }

        /* Add animation (fade in the popup) */
        @-webkit-keyframes fadeIn {
            from {opacity: 0;}
            to {opacity: 1;}
        }

        @keyframes fadeIn {
            from {opacity: 0;}
            to {opacity:1 ;}
        }
    </style>
    <script>
        const button = document.getElementById("submission")
        const submittedEntry = document.getElementById("submittedEntry")

        //whenever the button is clicked, toggle the ".hidden" class
        button.addEventListener("submit", ()=>{
            submittedEntry.classList.toggle("hidden")
        })
    </script>
    <!-- Custom styles for this template -->
    <link href="navbar-top.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div class="container">
    <div>
        <h1>New Entry</h1>
    </div>
<%--TODO format dropdowns to be more user friendly--%>
    <form:form modelAttribute="newEntry">
        <form:errors path="*" cssClass="errorblock" element="div" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <div><label for="homeTeamId">Home Team: </label>
            <select name="homeTeamId" id="homeTeamId" onchange="document.getElementById('homeBadge').src=$(this).find('option:selected').data('img')" required>
                <option value="" data-img="">--Select Option--</option>
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
        <div><label for="awayTeamId">Away Team:  </label>
            <select name="awayTeamId" id="awayTeamId" onchange="document.getElementById('awayBadge').src=$(this).find('option:selected').data('img')" required>
                <option value="" data-img="">--Select Option--</option>
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
            <option value="">--Select Option--</option>
            <c:forEach items="${groundsName}" var="groundsName" varStatus="loop">
                <option value="${groundsName.id}">
                        ${groundsName.name} (${groundsName.homeTeam})
                </option>
            </c:forEach>
        </select>
        </div>
        <div><label for="competitionId">Competition: </label>
            <select name="competitionId" id="competitionId" required>
                <option value="">--Select Option--</option>
                <c:forEach items="${competitionNames}" var="competitionName" varStatus="loop">
                    <option value="${competitionName.id}">
                            ${competitionName.name}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div><label> Date Visited: <input type="date" name="dateVisited" required/> </label></div>
        <div><label> Score: <input type="text" name="homeScore" required style="width: 25px" min="0" /> - <input type="text" name="awayScore" required style="width: 25px" min="0"/> </label></div>
        <div><label> Notes: <input type="text" name="notes"/> </label></div>
        <br/>
        <input type="submit" id=submission class="btn btn-lg btn-primary" role="button" value="Add Entry"/>
    <div class="popup" on="myFunction()">
        <span class="popuptext" id="myPopup">Entry has been recorded!</span>
    </div>
    </form:form>


    <div class="control-group">
    </div>
</div>
<script>
    // When the user clicks on div, open the popup
    function myFunction() {
        var popup = document.getElementById("myPopup");
        popup.classList.toggle("show");
    }
</script>
</body>
</html>



