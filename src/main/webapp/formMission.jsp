<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mission Form</title>
</head>
<body>
 
 
<%@ page import="Beans.mission" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<h2>Create Mission</h2>

<form action="${contextPath}/GestionMissions" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>

    <label for="description">Description:</label>
    <input type="text" id="description" name="description" required><br>

    <label for="remuneration">Remuneration:</label>
    <input type="text" id="remuneration" name="remuneration" required><br>

    <label for="open_date">Open Date:</label>
    <input type="date" id="open_date" name="open_date" required><br>

    <label for="close_date">Close Date:</label>
    <input type="date" id="close_date" name="close_date" required><br>

    <label for="id_recruteur">Recruiter ID:</label>
    <input type="text" id="id_recruteur" name="id_recruteur" required><br>

    <!-- Add a hidden input field to indicate the action -->
    <input type="hidden" name="action" value="create">

    <input type="submit" value="Create Mission">
</form>

</body>
</html>
