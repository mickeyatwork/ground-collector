<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>User Management</title>
    <base href="${pageContext.request.contextPath}/">
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" integrity="9IVa20McObhMr6JXg6G9MW6nh7IgLsddsd7zslDrJuIhyu15CCWzSx/uB57RaFQ8" crossorigin="anonymous">
    <link href="assets/css/style.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div class="container mt-4">
    <h1>User Management</h1>
    
    <table class="table table-striped table-hover mt-4">
        <thead>
            <tr>
                <th>Username</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>
                        <span class="badge ${user.enabled == 1 ? 'badge-success' : 'badge-danger'}">
                            ${user.enabled == 1 ? 'Active' : 'Inactive'}
                        </span>
                    </td>
                    <td>
                        <form action="/admin/users/toggle" method="post" style="display:inline;">
                            <input type="hidden" name="username" value="${user.username}" />
                            <input type="hidden" name="enabled" value="${user.enabled == 1 ? 0 : 1}" />
                            <button type="submit" class="btn btn-sm ${user.enabled == 1 ? 'btn-danger' : 'btn-success'}">
                                ${user.enabled == 1 ? 'Disable' : 'Enable'}
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
