<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Mission</title>
    <!-- Add Bootstrap CSS link -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<%@ page import="Beans.mission" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="container mt-5">
    <h2>Update Mission</h2>

    <c:if test="${missionToEdit != null}">
        <form action="${contextPath}/GestionMissions" method="post">
            <div class="mb-3">
                <label for="name" class="form-label">Name:</label>
                <input type="text" class="form-control" id="name" name="name" value="${missionToEdit.name}" required>
            </div>

            <div class="mb-3">
                <label for="description" class="form-label">Description:</label>
                <input type="text" class="form-control" id="description" name="description" value="${missionToEdit.description}" required>
            </div>

            <div class="mb-3">
                <label for="remuneration" class="form-label">Remuneration:</label>
                <input type="text" class="form-control" id="remuneration" name="remuneration" value="${missionToEdit.remuneration}" required>
            </div>

            <div class="mb-3">
                <label for="open_date" class="form-label">Open Date:</label>
                <input type="date" class="form-control" id="open_date" name="open_date" value="${missionToEdit.open_date}" required>
            </div>

            <div class="mb-3">
                <label for="close_date" class="form-label">Close Date:</label>
                <input type="date" class="form-control" id="close_date" name="close_date" value="${missionToEdit.close_date}" required>
            </div>

            <div class="mb-3">
                <label for="id_recruteur" class="form-label">Recruiter ID:</label>
                <input type="text" class="form-control" id="id_recruteur" name="id_recruteur" value="${missionToEdit.id_recruteur}" required>
            </div>

            <!-- Add hidden input fields to indicate the action and mission ID -->
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id_mission" value="${missionToEdit.id_mission}">

            <button type="submit" class="btn btn-primary">Update Mission</button>
        </form>
        <br>
        <a href="${contextPath}/GestionMissions">Back to Mission List</a>
    </c:if>
</div>

<!-- Add Bootstrap JS and Popper.js scripts (optional) -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>
