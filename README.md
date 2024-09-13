# Ground Collector
A Java based application designed for football enthusiasts who want to keep a record of the 
matches they have attended. This application allows users to log information about each match,
including teams, date, score, competition, and the ground visited.

## Key Features 
- <strong>Entries</strong>: Add a match entry to be recorded 
- <strong>Travel Log</strong>: Log of all match entries
- <strong>Ground Map</strong>: provides a visual of all the available grounds (currently only 
England from Premier League to National League). The user can cycle between All grounds, 
visited only, or a combination of both. Clicking on a ground icon brings up that grounds details
- <strong>Ground Checklist</strong>: Still a work in progress, Creating a checklist to show the
grounds that have been collected so far

## Setup
Private information is stored in an application.properties file (not stored on the repo). This
includes login details for the database. There is an outline on what values are used in this properties 
file in: <i>application.properties.example</i>

### Local Server Setup
<strong>Server</strong>: Tomcat 8.5.81<br>
<strong>Version</strong>: Java 11<br>
<strong>HTTP Port</strong>: 5000<br>
<strong>Application Context</strong>: GroundCollector





