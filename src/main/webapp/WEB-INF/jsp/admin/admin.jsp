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
    <div style="padding: 10px 10px 10px 0px" id="adminDropdownContainer">
        <h3>Admin Actions</h3>
        <select id="adminDropdown" class="form-control w-50" onchange="if(this.value) window.location.href=this.value">
            <option value="">Select an admin area to work on...</option>
            <optgroup label="Data Sync Processes (Run in order)">
                <option value="groundsUpdate">1. Fetch ground data by league</option>
                <option value="teamsUpdate">2. Fetch Team data by league</option>
                <option value="mapGrounds">3. Map Teams to their home Grounds</option>
            </optgroup>
            <optgroup label="Other Functions">
                <option value="individualTeamUpdate">Individual Team Update</option>
                <option value="fieldUpdate">Fetch specific field data by league</option>
            </optgroup>
        </select>
        <br>
        <p style="font-size: 12px"><strong>Important Note on Sync: </strong> When adding a new league, you need to make sure the leagues ID and corresponding API ID has been added in the code (see anywhere marked 'ADDING_LEAGUES')</p>
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



