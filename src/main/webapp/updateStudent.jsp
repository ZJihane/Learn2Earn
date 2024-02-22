<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Student</title>
</head>
<body>
<%@ page import="Beans.student" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<h2>Update Student</h2>
<%
    student studentToEdit = (student) request.getAttribute("studentToEdit");
%>
<c:if test="${studentToEdit != null}">
    <form action="${contextPath}/GestionStudents" method="post">
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" value="${studentToEdit.first_name}" required><br>

        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" value="${studentToEdit.last_name}" required><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${studentToEdit.email}" required><br>

        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" value="${studentToEdit.phone}" required><br>

        <label for="linkedin">LinkedIn:</label>
        <input type="text" id="linkedin" name="linkedin" value="${studentToEdit.linkedin}"><br>

        <label for="cv">CV:</label>
        <input type="text" id="cv" name="cv" value="${studentToEdit.cv}"><br>

        <label for="picture">Picture:</label>
        <input type="text" id="picture" name="picture" value="${studentToEdit.picture}"><br>

        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id_student" value="${studentToEdit.id_student}">

        <input type="submit" value="update">
    </form>
    <br>
    <a href="${contextPath}/GestionStudents">Back to Student List</a>
</c:if>

</body>
</html>
