function initMap() {

    const styledMapType = new google.maps.StyledMapType(
        [
            {
                "featureType": "administrative",
                "stylers": [
                    {
                        "visibility": "off"
                    }
                ]
            },
            {
                "featureType": "administrative.country",
                "stylers": [
                    {
                        "visibility": "off"
                    }
                ]
            },
            {
                "featureType": "administrative.locality",
                "stylers": [
                    {
                        "color": "#ffffff"
                    },
                    {
                        //"visibility": "simplified"
                        "visibility": "off"
                    }
                ]
            },
            {
                "featureType": "administrative.province",
                "stylers": [
                    {
                        "visibility": "off"
                    }
                ]
            },
            {
                "featureType": "landscape",
                "stylers": [
                    {
                        "color": "#b12f88"
                    }
                ]
            },
            {
                "featureType": "poi",
                "stylers": [
                    {
                        "visibility": "off"
                    }
                ]
            },
            {
                "featureType": "road",
                "stylers": [
                    {
                        "visibility": "off"
                    }
                ]
            },
            {
                "featureType": "transit",
                "stylers": [
                    {
                        "visibility": "off"
                    }
                ]
            },
            {
                "featureType": "water",
                "stylers": [
                    {
                        "color": "#89cff0"
                    }
                ]
            }
        ],
        {name: "Visited"}
    );

    map = new google.maps.Map(document.getElementById("map"), {
        zoom: 7,
        center: {lat: 52.954156, lng: -1.255423},
        mapTypeId: "terrain",
        disableDefaultUI: true,
        zoomControl: true,
        mapTypeControlOptions: {
            mapTypeIds: [],
        },

    });

    //Associate the styled map with the MapTypeId and set it to display.
    map.mapTypes.set("visited", styledMapType);
    map.setMapTypeId("visited");

    function createControl(map) {
        const controlButton = document.createElement("button");

        // Set CSS for the control.
        controlButton.style.backgroundColor = "#fff";
        controlButton.style.border = "2px solid #fff";
        controlButton.style.borderRadius = "3px";
        controlButton.style.boxShadow = "0 2px 6px rgba(0,0,0,.3)";
        controlButton.style.color = "rgb(25,25,25)";
        controlButton.style.cursor = "pointer";
        controlButton.style.fontFamily = "Roboto,Arial,sans-serif";
        controlButton.style.fontSize = "16px";
        controlButton.style.lineHeight = "38px";
        controlButton.style.margin = "8px 0 22px";
        controlButton.style.padding = "0 5px";
        controlButton.style.textAlign = "center";
        //controlButton.textContent = "Visited";
        //controlButton.title = "Click to highlight visited grounds";
        controlButton.type = "button";
        return controlButton;
    }

    // Create the DIV to hold the control.
    const combinedControlDiv = document.createElement("div");
    // Create the control.
    const combinedControl = createControl(map);
    combinedControl.textContent = "Combined";
    combinedControl.title = "Click to show all grounds with visited ones marked";
    combinedControl.addEventListener("click", () => {
        removeAllMarkers();
        removeVisitedMarkers();
        addVisitedMarker();
        setMapOnAll(map);
        setMapOnVisited(map);
    });

    // Append the control to the DIV.
    combinedControlDiv.appendChild(combinedControl);
    combinedControlDiv.style.padding = "10 10 10 5";
    map.controls[google.maps.ControlPosition.TOP_RIGHT].push(combinedControlDiv);

    // Create the DIV to hold the control.
    const visitedControlDiv = document.createElement("div");
    // Create the control.
    const visitedControl = createControl(map);
    visitedControl.textContent = "Visited";
    visitedControl.title = "Click to highlight visited grounds";
    visitedControl.addEventListener("click", () => {
        removeAllMarkers();
        addVisitedMarker();
        setMapOnVisited(map);
    });

    // Append the control to the DIV.
    visitedControlDiv.appendChild(visitedControl);
    visitedControlDiv.style.padding = "10 5 10 5";
    map.controls[google.maps.ControlPosition.TOP_RIGHT].push(visitedControlDiv);

    // Create the DIV to hold the control.
    const allControlDiv = document.createElement("div");
    // Create the control.
    const allControl = createControl(map);
    allControl.textContent = "All";
    allControl.title = "Show all available grounds";
    allControl.addEventListener("click", () => {
        removeVisitedMarkers();
        setMapOnAll(map);
    });

    allControlDiv.appendChild(allControl);
    allControlDiv.style.padding = "10 5 10 10";
    map.controls[google.maps.ControlPosition.TOP_RIGHT].push(allControlDiv);

  /* Adding a tab in the map for Home team image markers
    // Create the DIV to hold the control.

    const teamImageDiv = document.createElement("div");
    // Create the control.
    const teamImage = createControl(map);
    teamImage.textContent = "Team View";
    teamImage.title = "Show grounds by home team";
    teamImage.addEventListener("click", () => {
        removeAllMarkers();
        addHomeTeamImageMarker();
        setMapOnImage(map);
    });

    teamImageDiv.appendChild(teamImage);
    teamImageDiv.style.padding = "10 5 10 10";
    map.controls[google.maps.ControlPosition.TOP_RIGHT].push(teamImageDiv);
   */
    addMarker();
}

function addMarker() {

    for (i = 0; i < markerLat.length; i++) {

        let allGrounds = new google.maps.Marker({
            position: new google.maps.LatLng(markerLat[i], markerLong[i]),
            icon: icons.stadium.icon,
            map: map,
            title: groundNameArray[i],
        });

        let infoWindow = new google.maps.InfoWindow(
            {
                content: '<div id="content">' +
                    '<div id="siteNotice">' +
                    "</div>" +
                    '<h2 id="firstHeading" class="firstHeading">' + groundNameArray[i] + '</h2>' +
                    '<div id="bodyContent">' +
                    "<br><b>City:</b> " + groundCityArray[i] +
                    "<br><b>Capacity:</b> " + groundCapacityArray[i] +
                    "<br><b>Home to:</b> " + groundHomeToArray[i] +
                    "</div>" +
                    "</div>"
            }
        );

        allGrounds.addListener("click", () => {
            infoWindow.open({
                anchor: allGrounds,
                map,
            });

            google.maps.event.addListener(map, "click", function(event) {
                infoWindow.close();
            });

        });

        markers.push(allGrounds);
    }
}

function addVisitedMarker() {

    for (i = 0; i < vMarkerLat.length; i++) {
        let visitedGrounds = new google.maps.Marker({
            position: new google.maps.LatLng(vMarkerLat[i], vMarkerLong[i]),
            icon: icons.visitedStadium.icon,
            map: map,
        });

        let infoWindow = new google.maps.InfoWindow(
            {
                content: '<div id="content">' +
                    '<div id="siteNotice">' +
                    "</div>" +
                    '<h2 id="firstHeading" class="firstHeading">' + vGroundNameArray[i] + '</h2>' +
                    '<div id="bodyContent">' +
                    "<br><b>City:</b> " + vGroundCityArray[i] +
                    "<br><b>Capacity:</b> " + vGroundCapacityArray[i] +
                    "<br><b>Home to:</b> " + vGroundHomeToArray[i] +
                    "</div>" +
                    "</div>"
            }
        );
        visitedGrounds.addListener("click", callback => {
            infoWindow.open({
                anchor: visitedGrounds,
                map,
            });
            google.maps.event.addListener(map, "click", function (event) {
                infoWindow.close();
            });
        });
        visitedMarkers.push(visitedGrounds);
    }
}

function addHomeTeamImageMarker() {

    for (i = 0; i < markerLat.length; i++) {

        console.log("Just before creating marker: " + homeTeamImage.icon[i] )
        let homeTeamImages = new google.maps.Marker({
            position: new google.maps.LatLng(markerLat[i], markerLong[i]),
            icon: homeTeamImage.icon[i],
            map: map,
        });

        let infoWindow = new google.maps.InfoWindow(
            {
                content: '<div id="content">' +
                    '<div id="siteNotice">' +
                    "</div>" +
                    '<h2 id="firstHeading" class="firstHeading">' + vGroundNameArray[i] + '</h2>' +
                    '<div id="bodyContent">' +
                    "<br><b>City:</b> " + vGroundCityArray[i] +
                    "<br><b>Capacity:</b> " + vGroundCapacityArray[i] +
                    "<br><b>Home to:</b> " + vGroundHomeToArray[i] +
                    "</div>" +
                    "</div>"
            }
        );
        homeTeamImages.addListener("click", () => {
            infoWindow.open({
                anchor: homeTeamImages,
                map,
            });
            google.maps.event.addListener(map, "click", function(event) {
                infoWindow.close();
            });
        });

        imageMarkers.push(homeTeamImages);
    }
}

// Sets the map on all markers in the array.
function setMapOnAll(map) {

    for (i = 0; i < markers.length; i++) {

        markers[i].setMap(map);
    }
}

// Remove markers.
function removeAllMarkers() {

    for (i = 0; i < markers.length; i++) {

        markers[i].infoWindow = null;
        markers[i].setMap(null);
    }
}

// Add visited markers
function setMapOnVisited(map) {

    for (i = 0; i < visitedMarkers.length; i++) {

        visitedMarkers[i].setMap(map);
    }
}

// Remove visited markers.
function removeVisitedMarkers() {

    for (i = 0; i < visitedMarkers.length; i++) {

        visitedMarkers[i].infoWindow = null;
        visitedMarkers[i].setMap(null);
    }
}

// Add visited markers
function setMapOnImage(map) {

    for (i = 0; i < imageMarkers.length; i++) {
        console.log("Setting image marker as: " + imageMarkers[i])
        imageMarkers[i].setMap(map);
    }
}

