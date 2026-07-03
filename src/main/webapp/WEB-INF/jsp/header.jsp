<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-md navbar-dark bg-dark" style="margin-bottom: 0; padding-bottom: 5px">
    <a class="navbar-brand" href="menu">Home</a>
    <a class="navbar-brand" href="newEntry">New Entry</a>
    <a class="navbar-brand" href="travelLog">Travel Log</a>
    <%--<a class="navbar-brand" href="groundChecklist">Ground Checklist</a>--%>
    <a class="navbar-brand" href="map">Map</a>
    <sec:authorize access="hasRole('ADMIN')">
        <style>
            .admin-dropdown:hover .dropdown-menu {
                display: block;
                margin-top: 0;
            }
        </style>
        <div class="dropdown admin-dropdown" style="display: inline-block; position: relative;">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/admin" style="cursor: pointer;">ADMIN &#9662;</a>
            <div class="dropdown-menu" style="position: absolute; z-index: 1000;">
                <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/users">User Management</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/admin">Other Functions</a>
            </div>
        </div>
    </sec:authorize>
    <%--<a class="navbar-brand" href="entryTesting">Entry Testing</a>--%>
    <a class="navbar-brand" href="<c:out value='perform_logout'/>">Logout</a>
</nav>
