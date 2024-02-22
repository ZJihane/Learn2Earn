<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Recruiter</title>
</head>
<body>

<%@ page import="Beans.recruiter" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<h2>Update Recruiter</h2>

<%
    recruiter recruiterToEdit = (recruiter) request.getAttribute("recruiterToEdit");
%>

<c:if test="${recruiterToEdit != null}">
    <form action="${contextPath}/GestionRecruiters" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${recruiterToEdit.name}" required><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${recruiterToEdit.email}" required><br>

        <label for="company">Company:</label>
        <input type="text" id="company" name="company" value="${recruiterToEdit.company}" required><br>

        <label for="description">Description:</label>
        <input type="text" id="description" name="description" value="${recruiterToEdit.description}"><br>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="${recruiterToEdit.address}" required><br>

        <label for="city">City:</label>
        <input type="text" id="city" name="city" value="${recruiterToEdit.city}" required><br>

        <label for="country">Country:</label>
        <input type="text" id="country" name="country" value="${recruiterToEdit.country}" required><br>

        <!-- Add hidden input fields to indicate the action and recruiter ID -->
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id_recruiter" value="${recruiterToEdit.id_recruiter}">

        <input type="submit" value="Update Recruiter">
    </form>
    <br>
    <a href="${contextPath}/GestionRecruiters">Back to Recruiter List</a>
</c:if>

</body>
</html>
