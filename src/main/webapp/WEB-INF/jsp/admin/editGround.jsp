<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Edit Ground</title>
    <base href="${pageContext.request.contextPath}/">
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" integrity="9IVa20McObhMr6JXg6G9MW6nh7IgLsddsd7zslDrJuIhyu15CCWzSx/uB57RaFQ8" crossorigin="anonymous">
    <link href="assets/css/style.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div class="container mt-4">
    <h1>Edit Ground: ${ground.name}</h1>
    
    <form:form modelAttribute="ground" action="${pageContext.request.contextPath}/admin/dashboard/editGround" method="post">
        <form:hidden path="id" />
        
        <div class="form-group mb-3">
            <label for="name">Name</label>
            <form:input path="name" id="name" cssClass="form-control" />
        </div>
        
        <div class="form-group mb-3">
            <label for="aliases">Aliases (comma-separated)</label>
            <form:input path="aliases" id="aliases" cssClass="form-control" />
        </div>
        
        <div class="form-group mb-3">
            <label for="city">City</label>
            <form:input path="city" id="city" cssClass="form-control" />
        </div>
        
        <div class="form-group mb-3">
            <label for="capacity">Capacity</label>
            <form:input path="capacity" id="capacity" cssClass="form-control" />
        </div>
        
        <div class="form-group mb-3">
            <label for="built">Year Built</label>
            <form:input path="built" id="built" cssClass="form-control" type="number" />
        </div>

        <div class="form-group mb-3">
            <label for="lat">Latitude</label>
            <form:input path="lat" id="lat" cssClass="form-control" />
        </div>

        <div class="form-group mb-3">
            <label for="lng">Longitude</label>
            <form:input path="lng" id="lng" cssClass="form-control" />
        </div>
        
        <div class="form-group mb-3">
            <label for="image">Image URL</label>
            <form:input path="image" id="image" cssClass="form-control" />
        </div>

        <div class="form-group mb-3">
            <div class="form-check">
                <form:checkbox path="active" id="active" cssClass="form-check-input" />
                <label class="form-check-label" for="active">Active</label>
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Save Changes</button>
        <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn btn-secondary">Cancel</a>
    </form:form>
</div>
</body>
</html>
