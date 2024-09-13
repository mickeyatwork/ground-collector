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
    <title>Admin</title>
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

    <script> let updateObject = {
        "Teams": {
            "Name": [],
            "Home Ground": [],
            "League": [],
            "Logo": [],
            "Nickname": [],
        },
        "Grounds": {
            "Name": [],
            "Aliases": [],
            "Active": [],
            "Capacity": []
        }
    }
    window.onload = function() {
        let dbSel = document.getElementById("db");
        let actionSel = document.getElementById("action");
        let fieldSel = document.getElementById("field");
        for (let x in updateObject) {
            dbSel.options[dbSel.options.length] = new Option(x, x);
        }
        dbSel.onchange = function() {
            //empty action- and field- dropdowns
            fieldSel.length = 1;
            // actionSel.length = 1;
            //display correct values
            for (let y in updateObject[this.value]) {
                actionSel.options[actionSel.options.length] = new Option(y, y);
            }
        }
        actionSel.onchange = function() {
            //empty action dropdown
            fieldSel.length = 1;
            //display correct values
            let z = updateObject[dbSel.value][this.value];
            for (let i = 0; i < z.length; i++) {
                fieldSel.options[fieldSel.options.length] = new Option(z[i], z[i]);
            }
        }
    }
    </script>

    <!-- Custom styles for this template -->
    <link href="navbar-top.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div class="container">
    <div>
        <h1>Admin</h1>
    </div>
    <div><h3><strong>Update various databases here</strong></h3>
    </div>
    <div style="padding: 10px 10px 10px 0px" id="showTeamUpdates" class="container <%--d-none--%>">
        <p><strong>Process to add a new leagues set of grounds and teams (run in link order below)</strong></p>
        <p style="font-size: 12px"><strong>Important: </strong> When adding a new league, you need to make sure the leagues ID and corresponding API ID has been added in the code (see anywhere marked 'ADDING_LEAGUES')</p>
        <p><a href="groundsUpdate">Fetch ground data by league</a></p>
        <p><a href="teamsUpdate">Fetch Team data by league</a></p>
        <p><a href="mapGrounds">Map Teams to their home Grounds</a></p>
    </div>
        <div>-----------------------------------------------------</div>

<div>
    <p><strong>Other functions:</strong></p>
        <p><a href="individualTeamUpdate">Individual Team Update</a></p>
        <p><a href="fieldUpdate">Fetch specific field data by league</a></p>
        <%--<p><a href="createGround">Add a new ground to the database</a></p>--%>
        <%--<p><a href="updateField">Manually update existing team data</a></p>--%>
    </div>
    <div>
        <br><br><br>
        <%--<div>-----------------------------------------------------</div>
        <p><strong>Still in testing:</strong></p>
        <p><a href="createGround">Add a new ground to the database</a></p>

        <form name="form1" id="form1" action="updateField">
            Databases: <select name="db" id="db" required>
            <option value="" selected="selected">Choose a database</option>
        </select>
            <br>
            Fields: <select name="action" id="action" required>
            <option value="" selected="selected">What do you want to do</option>
        </select>
            <%--<br>
            Field: <select name="field" id="field">
            <option value="" selected="selected">field to edit</option>
        </select>
            <br><br>
            <input type="submit" value="Submit">
        </form>--%>
    </div>
</div>
<script src="./assets/js/home.js"></script>
</body>
</html>



