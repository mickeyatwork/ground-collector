<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <!-- <meta name="viewport" content="width=device-width, initial-scale=1">-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


    <style>
        .dropdown-submenu {
            position: relative;
        }
        .dropdown-submenu > .dropdown-menu {
            top: 0;
            left: 100%;
            margin-top: -6px;
            margin-left: -1px;
            border-radius: 0 6px 6px 6px;
        }
        .dropdown-submenu > a:after {
            border-color: transparent transparent transparent #333;
            border-style: solid;
            border-width: 5px 0 5px 5px;
            content: " ";
            display: block;
            float: right;
            height: 0;
            margin-right: -10px;
            margin-top: 5px;
            width: 0;
        }
        .dropdown-submenu:hover > a:after {
            border-left-color: #555;
        }
        @media (max-width: 767px) {
            .navbar-default .dropdown-nav,
            .navbar-inverse .dropdown-nav {
                display: inline;
            }
            .navbar-default .dropdown-nav .open .dropdown-menu > li > a,
            .navbar-inverse .dropdown-nav .open .dropdown-menu > li > a {
                color: #ffffff;
            }
            .navbar-default .navbar-brand,
            .navbar-inverse .navbar-brand {
                display: inline;
            }
            .navbar-default .navbar-toggle .icon-bar,
            .navbar-inverse .navbar-toggle .icon-bar {
                background-color: #fff;
            }
            .navbar-default .dropdown-menu > li > a,
            .navbar-inverse .dropdown-menu > li > a {
                color: red;
            }
            .navbar-default .dropdown-menu > li > a:hover,
            .navbar-inverse .dropdown-menu > li > a:hover {
                background-color: #ccc;
            }
            .navbar-default .dropdown-menu > li > a:focus,
            .navbar-inverse .dropdown-menu > li > a:focus {
                background-color: #ccc;
            }
            .dropdown-nav .open .dropdown-menu {
                border-bottom: 1px solid white;
                border-radius: 0;
            }
            .dropdown-menu {
                padding-left: 10px;
            }
            .dropdown-menu .dropdown-menu {
                padding-left: 20px;
            }
            .dropdown-menu .dropdown-menu .dropdown-menu {
                padding-left: 30px;
            }
            li.dropdown.open {
                border: 0px solid red;
            }
        }
        @media (min-width: 768px) {
            ul.nav li:hover > ul.dropdown-menu {
                display: block;
            }
            #navbar {
                text-align: center;
            }
        }

        img[src=""] {
            display: none;
        }
    </style>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <title>ENTRY TESTING</title>
    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" integrity="9IVa20McObhMr6JXg6G9MW6nh7IgLsddsd7zslDrJuIhyu15CCWzSx/uB57RaFQ8" crossorigin="anonymous">

    <meta name="theme-color" content="#563d7c">

    <!-- Custom styles for this template -->
    <link href="navbar-top.css" rel="stylesheet">

   <%-- <script>

        const leagues = {

        <c:forEach items="${competitionNames}" var="competitionName" varStatus="loop">
            <c:if test="${ competitionName.type  == 'League'}">
            <c:out value="${competitionName.name}"/>,



            : {
            <c:forEach items="${teamName}" var="teamNames" varStatus="loop">
                <c:if test="${ teamNames.leagueId == competitionName.id}">
                    ${teamNames.name},
                    ${teamNames.logo},
                    ${teamNames.id}
                </c:if>
            </c:forEach>
        }

            </c:if>
        </c:forEach>}


        var leagueObject = {

            leagues : {},
            "Front-end" : {
                "HTML": ["Links", "Images", "Tables", "Lists"],
                "CSS": ["Borders", "Margins", "Backgrounds", "Float"],
                "JavaScript": ["Variables", "Operators", "Functions", "Conditions"]
            },
            "Back-end": {
                "PHP": ["Variables", "Strings", "Arrays"],
                "SQL": ["SELECT", "UPDATE", "DELETE"]
            }
        }
        window.onload = function() {
            var subjectSel = document.getElementById("league");
            var homeTeamSel = document.getElementById("homeTeam");
            var chapterSel = document.getElementById("chapter");
            for (var x in leagueObject) {
                subjectSel.options[subjectSel.options.length] = new Option(x, x);
            }
            subjectSel.onchange = function() {
                //empty Chapters- and homeTeams- dropdowns
                chapterSel.length = 1;
                homeTeamSel.length = 1;
                //display correct values
                for (var y in leagueObject[this.value]) {
                    homeTeamSel.options[homeTeamSel.options.length] = new Option(y, y);
                }
            }
            homeTeamSel.onchange = function() {
                //empty Chapters dropdown
                chapterSel.length = 1;
                //display correct values
                var z = leagueObject[subjectSel.value][this.value];
                for (var i = 0; i < z.length; i++) {
                    chapterSel.options[chapterSel.options.length] = new Option(z[i], z[i]);
                }
            }
        }


        console.log(leagues);

    </script>--%>
    <script>
        const leagues = {
            <c:forEach items="${competitionNames}" var="competitionName" varStatus="loop">
                <c:if test="${ competitionName.type  == 'League'}">
                <c:out value="${competitionName.name}"/>,
                </c:if>
            </c:forEach>}

        const teams =  {
            <c:forEach items="${teamName}" var="teamNames" varStatus="loop">
            {
                name : <c:out value="${teamNames.name}"/>,
                teamId : <c:out value="${teamNames.id}"/>,
                logo : <c:out value="${teamNames.logo}"/>,
                leagueId : <c:out value="${teamNames.leagueId}"/>
            }
            </c:forEach>
        }
    </script>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div class="container">
    <div>
        <h1>New Entry</h1>
    </div>

<form:form modelAttribute="entryTesting">
    <form:errors path="*" cssClass="errorblock" element="div" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <div class="collapse navbar-collapse" id="dropdown-collapse-1">
        <div><label for="homeTeamId">Home Team: </label>
            <select name="homeTeamId" id="homeTeamId" onchange="document.getElementById('homeBadge').src=$(this).find('option:selected').data('img')" required>
                <c:set var="sel" value='selected = "selected"'>
                <option value="${version.versionName}" "${versions[0].versionName == version.versionName ? sel : ''}"> ${version.versionName}</option>
                </c:set>
                <option value="" data-img="">--Select Option--</option>
                <c:forEach items="${teamName}" var="teamName" varStatus="loop">
                    <option value="${teamName.id}" data-img="${teamName.logo}">
                            ${teamName.name}
                    </option>
                </c:forEach>
            </select>
            <img id=homeBadge src="" height=40px width=40px>
        </div>
    <div><label for="groundId">Away Team: </label>
        <ul class="nav dropdown-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">--Select Option--</a>
                <ul class="dropdown-menu">

                    <c:forEach items="${competitionNames}" var="competitionName" varStatus="loop">
                        <c:if test="${ competitionName.type  == 'League'}">
                            <li class="dropdown dropdown-submenu" value="" ><a href="#" class="dropdown-toggle" data-toggle="dropdown">${competitionName.name} </a>
                                <ul class="dropdown-menu">
                                    <c:forEach items="${teamName}" var="teamNames" varStatus="loop">
                                        <c:if test="${ teamNames.leagueId == competitionName.id}">
                                            <li value="${teamNames.id}"><a href="#">${teamNames.name}   <img height=30px width=30px src="${teamNames.logo}"></a></li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </li>
        </ul>
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
    <div><label> Score: <input type="text" name="homeScore" style="width: 25px" min="0"/> - <input type="text" name="awayScore" style="width: 25px" min="0"/> </label></div>
    <div><label> Notes: <input type="text" name="notes"/> </label></div>
    <br/>
    <input type="submit" class="btn btn-lg btn-primary" role="button" value="Add Entry"/>
    </form:form>

    <div class="control-group">
    </div>


    </body>
</html>
