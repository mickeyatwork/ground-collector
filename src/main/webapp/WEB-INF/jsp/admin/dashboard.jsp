<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Admin Dashboard</title>
    <base href="${pageContext.request.contextPath}/">
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" integrity="9IVa20McObhMr6JXg6G9MW6nh7IgLsddsd7zslDrJuIhyu15CCWzSx/uB57RaFQ8" crossorigin="anonymous">
    <link href="assets/css/style.css" rel="stylesheet">
    <style>
        .toggle-btn { margin-bottom: 20px; }
        .hidden { display: none; }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div class="container mt-4">
    <h1>Admin Dashboard</h1>
    
    <div class="btn-group toggle-btn">
        <button class="btn btn-primary" id="btnTeams" onclick="showTab('teams')">Teams</button>
        <button class="btn btn-secondary" id="btnGrounds" onclick="showTab('grounds')">Grounds</button>
    </div>

    <!-- Filters -->
    <div class="row mb-3">
        <div class="col-md-6">
            <input type="text" id="searchInput" class="form-control" placeholder="Search across all columns..." onkeyup="filterTable()">
        </div>
        <div class="col-md-6" id="leagueFilterContainer">
            <select id="leagueFilter" class="form-control" onchange="filterTable()">
                <option value="">All Leagues</option>
                <c:forEach items="${competitionNames}" var="comp">
                    <option value="${comp.id}">${comp.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <!-- Teams Table -->
    <div id="teamsTab">
        <table class="table table-striped table-hover" id="teamsTable">
            <thead>
                <tr>
                    <th onclick="sortTable('teamsTable', 0)" style="cursor: pointer;">ID &#x21D5;</th>
                    <th onclick="sortTable('teamsTable', 1)" style="cursor: pointer;">Name &#x21D5;</th>
                    <th onclick="sortTable('teamsTable', 2)" style="cursor: pointer;">Country &#x21D5;</th>
                    <th onclick="sortTable('teamsTable', 3)" style="cursor: pointer;">Founded &#x21D5;</th>
                    <th onclick="sortTable('teamsTable', 4)" style="cursor: pointer;">League ID &#x21D5;</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${teams}" var="team">
                    <tr style="cursor: pointer;" onclick="window.open('${pageContext.request.contextPath}/admin/dashboard/editTeam/${team.id}', '_blank')">
                        <td>${team.id}</td>
                        <td>${team.name}</td>
                        <td>${team.country}</td>
                        <td>${team.founded}</td>
                        <td>${team.leagueId}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Grounds Table -->
    <div id="groundsTab" class="hidden">
        <table class="table table-striped table-hover" id="groundsTable">
            <thead>
                <tr>
                    <th onclick="sortTable('groundsTable', 0)" style="cursor: pointer;">ID &#x21D5;</th>
                    <th onclick="sortTable('groundsTable', 1)" style="cursor: pointer;">Name &#x21D5;</th>
                    <th onclick="sortTable('groundsTable', 2)" style="cursor: pointer;">City &#x21D5;</th>
                    <th onclick="sortTable('groundsTable', 3)" style="cursor: pointer;">Capacity &#x21D5;</th>
                    <th onclick="sortTable('groundsTable', 4)" style="cursor: pointer;">Aliases &#x21D5;</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${grounds}" var="ground">
                    <tr style="cursor: pointer;" onclick="window.open('${pageContext.request.contextPath}/admin/dashboard/editGround/${ground.id}', '_blank')">
                        <td>${ground.id}</td>
                        <td>${ground.name}</td>
                        <td>${ground.city}</td>
                        <td>${ground.capacity}</td>
                        <td>${ground.aliases}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<script>
    var currentTab = 'teams';

    function showTab(tabId) {
        currentTab = tabId;
        document.getElementById('teamsTab').classList.add('hidden');
        document.getElementById('groundsTab').classList.add('hidden');
        document.getElementById('btnTeams').classList.replace('btn-primary', 'btn-secondary');
        document.getElementById('btnGrounds').classList.replace('btn-primary', 'btn-secondary');

        if (tabId === 'teams') {
            document.getElementById('leagueFilterContainer').style.display = 'block';
        } else {
            document.getElementById('leagueFilterContainer').style.display = 'none';
        }

        document.getElementById(tabId + 'Tab').classList.remove('hidden');
        document.getElementById('btn' + tabId.charAt(0).toUpperCase() + tabId.slice(1)).classList.replace('btn-secondary', 'btn-primary');
        filterTable();
    }

    function filterTable() {
        var input, filter, table, tr, td, i, j, txtValue, match;
        input = document.getElementById("searchInput");
        filter = input.value.toUpperCase();
        var leagueInput = document.getElementById("leagueFilter").value;

        table = document.getElementById(currentTab + "Table");
        tr = table.getElementsByTagName("tr");

        for (i = 1; i < tr.length; i++) {
            tr[i].style.display = "none";
            td = tr[i].getElementsByTagName("td");
            
            // Check League ID filter if we're on the teams tab
            var matchesLeague = true;
            if (currentTab === 'teams' && leagueInput !== "") {
                var leagueCell = td[4];
                if (leagueCell) {
                    var leagueVal = leagueCell.textContent || leagueCell.innerText;
                    if (leagueVal.trim() !== leagueInput) {
                        matchesLeague = false;
                    }
                }
            }

            if (!matchesLeague) continue;

            if (filter === "") {
                tr[i].style.display = "";
            } else {
                for (j = 0; j < td.length; j++) {
                    if (td[j]) {
                        txtValue = td[j].textContent || td[j].innerText;
                        if (txtValue.toUpperCase().indexOf(filter) > -1) {
                            tr[i].style.display = "";
                            break;
                        }
                    }
                }
            }
        }
    }

    function sortTable(tableId, n) {
        var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
        table = document.getElementById(tableId);
        switching = true;
        dir = "asc"; 
        while (switching) {
            switching = false;
            rows = table.rows;
            for (i = 1; i < (rows.length - 1); i++) {
                shouldSwitch = false;
                x = rows[i].getElementsByTagName("TD")[n];
                y = rows[i + 1].getElementsByTagName("TD")[n];
                
                var cmpX = isNaN(x.innerHTML) ? x.innerHTML.toLowerCase() : parseFloat(x.innerHTML);
                var cmpY = isNaN(y.innerHTML) ? y.innerHTML.toLowerCase() : parseFloat(y.innerHTML);

                if (dir == "asc") {
                    if (cmpX > cmpY) {
                        shouldSwitch = true;
                        break;
                    }
                } else if (dir == "desc") {
                    if (cmpX < cmpY) {
                        shouldSwitch = true;
                        break;
                    }
                }
            }
            if (shouldSwitch) {
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
                switchcount ++;      
            } else {
                if (switchcount == 0 && dir == "asc") {
                    dir = "desc";
                    switching = true;
                }
            }
        }
    }

    // Default sort by name (column 1) on load
    window.onload = function() {
        sortTable('teamsTable', 1);
        sortTable('groundsTable', 1);
    };
</script>
</body>
</html>
