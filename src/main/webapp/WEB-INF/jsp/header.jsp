<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-md navbar-dark bg-dark" style="margin-bottom: 0; padding-bottom: 5px">
    <a class="navbar-brand" href="menu">Home</a>
    <a class="navbar-brand" href="newEntry">New Entry</a>
    <a class="navbar-brand" href="travelLog">Travel Log</a>
    <a class="navbar-brand" href="groundChecklist">Ground Checklist</a>
    <a class="navbar-brand" href="map">Map</a>
    <sec:authorize access="hasRole('ADMIN')">
        <a class="navbar-brand" href="admin">ADMIN</a>
    </sec:authorize>
    <%--<a class="navbar-brand" href="entryTesting">Entry Testing</a>--%>
    <a class="navbar-brand" href="<c:out value='perform_logout'/>">Logout</a>
</nav>
