<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Grounds Map</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" integrity="9IVa20McObhMr6JXg6G9MW6nh7IgLsddsd7zslDrJuIhyu15CCWzSx/uB57RaFQ8" crossorigin="anonymous">
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/map.js"></script>
    <style>

        #map {
            height: 100%;
            width: 100%;
        }

        html,
        body {
            height: 100%;
            margin: 0;
            padding: 0;
        }

        navbar,
        navbar-expand-md,
        navbar-dark,
        bg-dark,
        mb-4{
            padding-bottom: 0;
            margin-bottom: 0;
        }

        .gm-style-iw button:focus {
            outline: 0;
        }
    </style>
    <script>
        let map;
        let markers = [];
        let visitedMarkers = [];
        let iMarker = [];
        let imageMarkers = [];

        // Setting up a custom marker icon
        const iconBase =
            "${pageContext.request.contextPath}/images/";
        const icons = {
            stadium: {
                icon: iconBase + "stadium-icon.png",
            },
            visitedStadium: {
                icon: iconBase + "stadium-icon-visited.png",
            }
        };

        let markerLat,
            markerLong,
            groundNameArray,
            groundCityArray,
            groundCapacityArray,
            groundHomeToArray,
            homeTeamImageArray,
            allGrounds,
            visitedGrounds,
            i;

        // Get all available ground markers from the db
        markerLat = [
            <c:forEach var="s" items="${geoLocData}" varStatus="status">
            <c:out value="${s.lat}"/>,
            </c:forEach>];
        markerLong = [
            <c:forEach var="s" items="${geoLocData}" varStatus="status">
            <c:out value="${s.lng}"/>,
            </c:forEach>];

        let groundName = '';
        <c:forEach var="s" items="${geoLocData}">
        groundName += '<c:out value="${s.name}" />,'
        </c:forEach>;
        groundNameArray = groundName.split(",");

        let groundCity = '';
        <c:forEach var="s" items="${geoLocData}">
        groundCity += '<c:out value="${s.city}" />/'
        </c:forEach>;
        groundCityArray = groundCity.split("/");

        let groundCapacity = '';
        <c:forEach var="s" items="${geoLocData}">
        groundCapacity += '<c:out value="${s.capacity}"/>/'
        </c:forEach>;
        groundCapacityArray = groundCapacity.split("/");

        let groundHomeTo = '';
        <c:forEach var="s" items="${geoLocData}">
        groundHomeTo += '<c:out value="${s.homeTeam}" />,'
        </c:forEach>;
        groundHomeToArray = groundHomeTo.split(",");

      <%-- Home team image on map
        let homeTeamImage = '';
        <c:forEach var="s" items="${geoLocData}">
        console.log('<c:out value="${s.homeTeamImage}" />');
        homeTeamImage += '<c:out value="${s.homeTeamImage}" />,'
        </c:forEach>;
        homeTeamImageArray = homeTeamImage.split(",");
        --%>

        // Get visited markers from the db
        let vMarkerLat, vMarkerLong, vGroundNameArray, vGroundCityArray, vGroundCapacityArray, vGroundHomeToArray;

        vMarkerLat = [
            <c:forEach var="v" items="${geoLocVisited}" varStatus="status">
            <c:out value="${v.lat}"/>,
            </c:forEach>];
        vMarkerLong = [
            <c:forEach var="v" items="${geoLocVisited}" varStatus="status">
            <c:out value="${v.lng}"/>,
            </c:forEach>];

        let vGroundName = '';
        <c:forEach var="v" items="${geoLocVisited}">
        vGroundName += '<c:out value="${v.name}" />,'
        </c:forEach>;
        vGroundNameArray = vGroundName.split(",");

        let vGroundCity = '';
        <c:forEach var="v" items="${geoLocVisited}">
        vGroundCity += '<c:out value="${v.city}" />/'
        </c:forEach>;
        vGroundCityArray = vGroundCity.split("/");

        let vGroundCapacity = '';
        <c:forEach var="v" items="${geoLocVisited}">
        vGroundCapacity += '<c:out value="${v.capacity}"/>/'
        </c:forEach>;
        vGroundCapacityArray = vGroundCapacity.split("/");

        let vGroundHomeTo = '';
        <c:forEach var="v" items="${geoLocVisited}">
        vGroundHomeTo += '<c:out value="${v.homeTeam}" />,'
        </c:forEach>;
        vGroundHomeToArray = vGroundHomeTo.split(",");

        window.initMap = initMap;
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<div id="map"></div>
<script
        src="https://maps.googleapis.com/maps/api/js?key=${mapsKey}&callback=initMap&v=weekly"
        defer
></script>
</body>
</html>