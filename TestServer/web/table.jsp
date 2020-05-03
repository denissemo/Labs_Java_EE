<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="header.jsp"/>
    <title>Table</title>
</head>
<body>
<style>
    body {
        margin: 20px;
    }

    .filters {
        padding: 10px;
        margin: 20px;
    }

    .filters form {
        border: unset !important;
    }

    .row {
        margin: 5px !important;
    }

    .radio-wrapper {
        width: 100%;
        margin: 15px auto;
    }
</style>
<p>Clients count: <span style="color: red">${count}</span></p>
<p>Servlet config: ${servletConfig}</p>
<a href="<c:url value="/city" />">Cities</a>
<div class="filters">
    <form action="<c:url value="/weather" />" method="get" id="filter-form" class="card">
        <div class="row">
            <div class="col-sm-4">
                <label for="cities">Cities</label>
                <select class="form-control" id="cities" name="city">
                    <option value=""></option>
                    <jsp:useBean id="cities" scope="request" type="java.util.List"/>
                    <c:forEach var="city" items="${cities}">
                        <c:if test="${pageContext.session.getAttribute('cityId').equals(city.id)}">
                            <option selected value="${city.id}">${city.name}</option>
                        </c:if>
                        <c:if test="${!pageContext.session.getAttribute('cityId').equals(city.id)}">
                            <option value="${city.id}">${city.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            <div class="col-sm-4">
                <label for="stations">Stations</label>
                <select class="form-control" id="stations" name="station">
                    <option value="">${pageContext.session.getAttribute('stationId')}</option>
                    <jsp:useBean id="stations" scope="request" type="java.util.List"/>
                    <c:forEach var="station" items="${stations}">
                        <c:if test="${pageContext.session.getAttribute('stationId').equals(station.id)}">
                            <option selected value="${station.id}">${station.name}</option>
                        </c:if>
                        <c:if test="${!pageContext.session.getAttribute('stationId').equals(station.id)}">
                            <option value="${station.id}">${station.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            <div class="col-sm-4">
                <label for="column">Column</label>
                <select class="form-control" name="column" id="column">
                    <option value="date">Date</option>
                    <option value="city">City</option>
                    <option value="stationName">Station name</option>
                    <option value="mintemp">Min temperature</option>
                    <option value="maxtemp">Max temperature</option>
                    <option value="wind">Wind</option>
                    <option value="cloud">Cloud</option>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-4">
                <label for="fromDate">From date</label>
                <input type="date" class="form-control" id="fromDate" name="fromDate">
            </div>
            <div class="col-sm-4">
                <label for="toDate">To date</label>
                <input type="date" class="form-control" id="toDate" name="toDate">
            </div>
            <div class="col-sm-2 radio-wrapper">
                <div class="row">
                    <div class="col-sm-2">
                        <input id="asc" class="form-check-input" type="radio" name="direction"
                               value="asc" checked>
                        <label class="form-check-label" for="asc">
                            Ascending
                        </label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2">
                        <input id="desc" class="form-check-input" type="radio" name="direction"
                               value="desc">
                        <label class="form-check-label" for="desc">
                            Descending
                        </label>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-1">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </div>
    </form>
</div>
<table class="table table-striped table-bordered">
    <thead class="thead-dark">
    <tr>
        <th scope='col'>Date</th>
        <th scope='col'>City</th>
        <th scope='col'>Station name</th>
        <th scope='col'>Min temperature °C</th>
        <th scope='col'>Max temperature °C</th>
        <th scope='col'>Wind m/s</th>
        <th scope='col'>Description</th>
        <th scope='col'>Cloud %</th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="weathers" scope="request" type="java.util.List"/>
    <c:forEach var="weather" items="${weathers}">
        <tr>
            <td>${weather.date}</td>
            <td>${weather.city}</td>
            <td>${weather.stationName}</td>
            <td>${weather.minTemp}</td>
            <td>${weather.maxTemp}</td>
            <td>${weather.wind}</td>
            <td>${weather.description}</td>
            <td>${weather.cloud}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
