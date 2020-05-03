<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="header.jsp"/>
    <title>Cities</title>
</head>
<body>
<style>
    body {
        margin: 20px;
    }

    .create {
        padding: 10px;
        margin: 20px;
    }

    .create form {
        border: unset !important;
    }

    .row {
        margin: 5px !important;
    }

    .form-control.table_ {
        max-width: 200px;
        display: unset;
    }
</style>
<a href="<c:url value="/weather" />">Weather</a>
<div class="create">
    <form action="<c:url value="/city" />" method="post">
        <div class="row">
            <div class="col-sm-4">
                <label for="cityName">Name</label>
                <input type="text" class="form-control" id="cityName" name="cityName">
            </div>
        </div>
        <input type="hidden" name="_method" value="post">
        <div class="row">
            <div class="col-sm-1">
                <button type="submit" class="btn btn-primary">Add city</button>
            </div>
        </div>
    </form>
</div>
<table class="table table-striped table-bordered">
    <thead class="thead-dark">
    <tr>
        <th scope='col'>Id</th>
        <th scope='col'>Name</th>
        <th scope='col'>Action</th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="cities" scope="request" type="java.util.List"/>
    <c:forEach var="city" items="${cities}">
            <tr>
                <td>${city.id}</td>
                <td>
                    <form action="<c:url value="/city" />" method="post">
                        <input type="hidden" name="_method" value="put">
                        <input type="hidden" name="cityId" value="${city.id}">
                        <input type="text" class="form-control table_" name="cityName" placeholder="${city.name}">
                        <button type="submit" class="btn btn-primary">Edit</button>
                    </form>
                </td>
                <td>
                    <form action="<c:url value="/city" />" method="post">
                        <input type="hidden" name="_method" value="delete">
                        <input type="hidden" name="cityId" value="${city.id}">
                        <button type="submit" class="btn btn-primary">Remove</button>
                    </form>
                </td>
            </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
