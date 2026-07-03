<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Edit Team</title>
    <base href="${pageContext.request.contextPath}/">
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" integrity="9IVa20McObhMr6JXg6G9MW6nh7IgLsddsd7zslDrJuIhyu15CCWzSx/uB57RaFQ8" crossorigin="anonymous">
    <link href="assets/css/style.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div class="container mt-4">
    <h1>Edit Team: ${team.name}</h1>
    
    <form:form modelAttribute="team" action="${pageContext.request.contextPath}/admin/dashboard/editTeam" method="post">
        <form:hidden path="id" />
        
        <div class="form-group mb-3">
            <label for="name">Name</label>
            <form:input path="name" id="name" cssClass="form-control" />
        </div>
        
        <div class="form-group mb-3">
            <label for="country">Country</label>
            <form:input path="country" id="country" cssClass="form-control" />
        </div>
        
        <div class="form-group mb-3">
            <label for="founded">Founded</label>
            <form:input path="founded" id="founded" cssClass="form-control" type="number" />
        </div>
        
        <div class="form-group mb-3">
            <label for="leagueId">League ID</label>
            <form:input path="leagueId" id="leagueId" cssClass="form-control" type="number" />
        </div>
        
        <div class="form-group mb-3">
            <label for="groundId">Ground ID</label>
            <form:input path="groundId" id="groundId" cssClass="form-control" type="number" />
        </div>
        
        <div class="form-group mb-3">
            <label for="logo">Logo URL</label>
            <form:input path="logo" id="logo" cssClass="form-control" />
        </div>

        <button type="submit" class="btn btn-primary">Save Changes</button>
        <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn btn-secondary">Cancel</a>
    </form:form>
</div>
</body>
</html>
