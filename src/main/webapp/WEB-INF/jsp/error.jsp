<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Error</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" integrity="9IVa20McObhMr6JXg6G9MW6nh7IgLsddsd7zslDrJuIhyu15CCWzSx/uB57RaFQ8" crossorigin="anonymous">

    <style>
        .outer {
            display: table;
            position: absolute;
            top: 0;
            left: 0;
            height: 100%;
            width: 100%;
        }

        .middle {
            display: table-cell;
            vertical-align: middle;
        }

        .inner {
            margin-left: auto;
            margin-right: auto;
            width: 600px;
        }
    </style>
</head>
<body>
<div class="outer">
    <div class="middle">
        <div class="inner">
            <h1 style="font-size: 32px">Something went wrong!</h1>
            <br>
            <button class="btn btn-lg btn-primary" style="font-size: 32px" onclick="history.back()">Go Back</button>
        </div>
    </div>
</div>
<%--<a href="${pageContext.request.contextPath}/WEB-INF/jsp/menu.jsp">Go Home</a>--%>
</body>
</html>
