let isCleared = false;
let activeButtonState = 'all';
let allVisitedMarkers = [];

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
                        "color": "#000000"
                    },
                    {
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
        ],
        {name: "Visited"}
    );

    map = new google.maps.Map(document.getElementById("map"), {
        zoom: 8,
        center: {lat: 52.954156, lng: -1.255423},
        mapTypeId: "terrain",
        disableDefaultUI: true,
        zoomControl: true,
        mapId: '14d32a9abef6b78c19c62124',
        mapTypeControlOptions: {
            mapTypeIds: [],
        },

    });

    map.mapTypes.set("visited", styledMapType);
    map.setMapTypeId("visited");

    function createControl(text, title, onClick) {
        const controlButton = document.createElement("button");
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
        controlButton.textContent = text;
        controlButton.title = title;
        controlButton.type = "button";
        controlButton.addEventListener("click", onClick);
        return controlButton;
    }

    const combinedControlDiv = document.createElement("div");
    combinedControlDiv.appendChild(createControl("Combined", "Click to show all grounds with visited ones marked", () => {
        activeButtonState = 'combined';
        updateMap();
    }));
    combinedControlDiv.style.padding = "10 10 10 5";
    map.controls[google.maps.ControlPosition.TOP_RIGHT].push(combinedControlDiv);

    const visitedControlDiv = document.createElement("div");
    visitedControlDiv.appendChild(createControl("Visited", "Click to highlight visited grounds", () => {
        activeButtonState = 'visited';
        updateMap();
    }));
    visitedControlDiv.style.padding = "10 5 10 5";
    map.controls[google.maps.ControlPosition.TOP_RIGHT].push(visitedControlDiv);

    const allControlDiv = document.createElement("div");
    allControlDiv.appendChild(createControl("All", "Show all available grounds", () => {
        activeButtonState = 'all';
        updateMap();
    }));
    allControlDiv.style.padding = "10 5 10 10";
    map.controls[google.maps.ControlPosition.TOP_RIGHT].push(allControlDiv);

    addMarker();
    createAllVisitedMarkers();
    createLeagueFilterControl();
    updateMap();
}

function createLeagueFilterControl() {
    const leagueFilterDiv = document.createElement("div");
    leagueFilterDiv.id = "leagueFilterDiv";
    leagueFilterDiv.style.backgroundColor = "#fff";
    leagueFilterDiv.style.border = "2px solid #fff";
    leagueFilterDiv.style.borderRadius = "3px";
    leagueFilterDiv.style.boxShadow = "0 2px 6px rgba(0,0,0,.3)";
    leagueFilterDiv.style.padding = "10px";
    leagueFilterDiv.style.margin = "8px 0 22px";
    leagueFilterDiv.style.marginRight = "10px";


    const uniqueLeagueIds = [...new Set(leagueIdArray.filter(id => id))];

    const allCheckbox = createCheckbox("All Leagues", "all", true);
    leagueFilterDiv.appendChild(allCheckbox.div);

    const clearCheckbox = createCheckbox("Clear", "clear", false);
    leagueFilterDiv.appendChild(clearCheckbox.div);

    const checkboxes = [];
    uniqueLeagueIds.forEach(leagueId => {
        const checkbox = createCheckbox(competitions.get(parseInt(leagueId)), leagueId, true);
        checkboxes.push(checkbox);
        leagueFilterDiv.appendChild(checkbox.div);
    });

    allCheckbox.input.addEventListener("change", () => {
        checkboxes.forEach(cb => cb.input.checked = allCheckbox.input.checked);
        if (allCheckbox.input.checked) {
            clearCheckbox.input.checked = false;
            isCleared = false;
        }
        updateMap();
    });

    clearCheckbox.input.addEventListener("change", () => {
        isCleared = clearCheckbox.input.checked;
        if (clearCheckbox.input.checked) {
            allCheckbox.input.checked = false;
            checkboxes.forEach(cb => cb.input.checked = false);
        }
        updateMap();
    });

    checkboxes.forEach(cb => {
        cb.input.addEventListener("change", () => {
            if (cb.input.checked) {
                clearCheckbox.input.checked = false;
                isCleared = false;
            }
            allCheckbox.input.checked = checkboxes.every(c => c.input.checked);
            if (checkboxes.every(c => !c.input.checked)) {
                clearCheckbox.input.checked = true;
                isCleared = true;
            }
            updateMap();
        });
    });

    map.controls[google.maps.ControlPosition.TOP_RIGHT].push(leagueFilterDiv);
}

function createCheckbox(text, value, checked) {
    const div = document.createElement("div");
    const input = document.createElement("input");
    input.type = "checkbox";
    input.value = value;
    input.checked = checked;
    input.style.marginRight = "5px";

    const label = document.createElement("label");
    label.textContent = text;
    label.style.fontFamily = "Roboto,Arial,sans-serif";
    label.style.fontSize = "16px";

    div.appendChild(input);
    div.appendChild(label);
    return { div, input };
}

function addMarker() {
    for (let i = 0; i < markerLat.length; i++) {
        const iconImage = document.createElement('img');
        if (homeTeamImageArray[i] && homeTeamImageArray[i].trim() !== '' && homeTeamImageArray[i] !== 'null') {
            iconImage.src = homeTeamImageArray[i];
            iconImage.style.width = '40px';
            iconImage.style.height = '40px';
        } else {
            iconImage.src = icons.stadium.icon;
        }

        let allGrounds = new google.maps.marker.AdvancedMarkerElement({
            position: new google.maps.LatLng(markerLat[i], markerLong[i]),
            content: iconImage,
            map: map,
            title: groundNameArray[i],
        });

        allGrounds.leagueId = leagueIdArray[i];

        let infoWindow = new google.maps.InfoWindow({
            content: '<div id="content">' +
                '<div id="siteNotice"></div>' +
                '<h2 id="firstHeading" class="firstHeading">' + groundNameArray[i] + '</h2>' +
                '<div id="bodyContent">' +
                "<br><b>City:</b> " + groundCityArray[i] +
                "<br><b>Capacity:</b> " + groundCapacityArray[i] +
                "<br><b>Home to:</b> " + groundHomeToArray[i] +
                "<br><b>League:</b> " + competitions.get(parseInt(leagueIdArray[i])) +
                "</div>" +
                "</div>"
        });

        allGrounds.addListener("click", () => {
            if (infoWindow.getMap()) {
                infoWindow.close();
            } else {
                infoWindow.open({
                    anchor: allGrounds,
                    map,
                });
            }

            google.maps.event.addListener(map, "click", function(event) {
                infoWindow.close();
            });
        });

        markers.push(allGrounds);
    }
}

function createAllVisitedMarkers() {
    for (let i = 0; i < vMarkerLat.length; i++) {
        const iconVisitedImage = document.createElement('img');
        if (vHomeTeamImageArray[i] && vHomeTeamImageArray[i].trim() !== '' && vHomeTeamImageArray[i] !== 'null') {
            iconVisitedImage.src = vHomeTeamImageArray[i];
            iconVisitedImage.style.width = '40px';
            iconVisitedImage.style.height = '40px';
        } else {
            iconVisitedImage.src = icons.visitedStadium.icon;
        }

        let visitedGrounds = new google.maps.marker.AdvancedMarkerElement({
            position: new google.maps.LatLng(vMarkerLat[i], vMarkerLong[i]),
            content: iconVisitedImage,
        });

        visitedGrounds.leagueId = vLeagueIdArray[i];

        let infoWindow = new google.maps.InfoWindow({
            content: '<div id="content">' +
                '<div id="siteNotice"></div>' +
                '<h2 id="firstHeading" class="firstHeading">' + vGroundNameArray[i] + '</h2>' +
                '<div id="bodyContent">' +
                "<br><b>City:</b> " + vGroundCityArray[i] +
                "<br><b>Capacity:</b> " + vGroundCapacityArray[i] +
                "<br><b>Home to:</b> " + vGroundHomeToArray[i] +
                "<br><b>League:</b> " + competitions.get(parseInt(vLeagueIdArray[i])) +
                "</div>" +
                "</div>"
        });

        visitedGrounds.addListener("click", () => {
            if (infoWindow.getMap()) {
                infoWindow.close();
            } else {
                infoWindow.open({
                    anchor: visitedGrounds,
                    map,
                });
            }
            google.maps.event.addListener(map, "click", function (event) {
                infoWindow.close();
            });
        });
        allVisitedMarkers.push(visitedGrounds);
    }
}

function getCoordString(position) {
    let lat, lng;
    if (typeof position.lat === 'function') {
        lat = position.lat();
    } else {
        lat = position.lat;
    }
    if (typeof position.lng === 'function') {
        lng = position.lng();
    } else {
        lng = position.lng;
    }
    return `${lat.toFixed(5)},${lng.toFixed(5)}`;
}

function updateMap() {
    const leagueFilterDiv = document.getElementById("leagueFilterDiv");
    if (!leagueFilterDiv) return;

    if (isCleared) {
        removeAllMarkers();
        removeAllVisitedMarkers();
        return;
    }

    const leagueCheckboxes = leagueFilterDiv.querySelectorAll('input[type="checkbox"]:not([value="all"]):not([value="clear"])');
    const selectedLeagues = [];
    leagueCheckboxes.forEach(cb => {
        if (cb.checked) {
            selectedLeagues.push(cb.value);
        }
    });

    removeAllMarkers();
    removeAllVisitedMarkers();

    if (activeButtonState === 'visited') {
        for (let i = 0; i < allVisitedMarkers.length; i++) {
            if (selectedLeagues.includes(allVisitedMarkers[i].leagueId)) {
                allVisitedMarkers[i].setMap(map);
            }
        }
    } else if (activeButtonState === 'combined') {
        const visitedCoords = new Set();
        for (let i = 0; i < allVisitedMarkers.length; i++) {
            if (selectedLeagues.includes(allVisitedMarkers[i].leagueId)) {
                visitedCoords.add(getCoordString(allVisitedMarkers[i].position));
            }
        }

        for (let i = 0; i < markers.length; i++) {
            if (selectedLeagues.includes(markers[i].leagueId)) {
                markers[i].setMap(map);
                const markerCoord = getCoordString(markers[i].position);
                const isVisited = visitedCoords.has(markerCoord);
                const iconImage = markers[i].content;

                if (isVisited) {
                    iconImage.style.filter = "";
                    iconImage.style.opacity = "1";
                } else {
                    iconImage.style.filter = "grayscale(100%)";
                    iconImage.style.opacity = "0.6";
                }
            }
        }
    } else { // 'all' state
        for (let i = 0; i < markers.length; i++) {
            if (selectedLeagues.includes(markers[i].leagueId)) {
                markers[i].setMap(map);
                const iconImage = markers[i].content;
                iconImage.style.filter = "";
                iconImage.style.opacity = "1";
            }
        }
    }
}

function setMapOnAll(map) {
    for (let i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }
}

function removeAllMarkers() {
    for (let i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
}

function removeAllVisitedMarkers() {
    for (let i = 0; i < allVisitedMarkers.length; i++) {
        allVisitedMarkers[i].setMap(null);
    }
}
