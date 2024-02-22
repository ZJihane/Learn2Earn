<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recruiter List</title>
    
    <!-- Add Bootstrap CSS link -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />

    

        <!-- Move the "Add" button to the left -->
        <h1>Gestion Recruteurs</h1>
        
        <div class="float-left">
            <form action="${contextPath}/GestionRecruiters?action=add" method="post">
                <button type="submit" class="btn btn-success">Add</button>
            </form>
        </div>

        <!-- Center the table horizontally -->
        
        <div>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Company</th>
                        <th>Description</th>
                        <th>Address</th>
                        <th>City</th>
                        <th>Country</th>
                        <th>Delete</th>
                        <th>Edit</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${recruiters}" var="recruiter" varStatus="boucle">
                        <tr>
                            <td>${recruiter.id_recruiter}</td>
                            <td>${recruiter.name}</td>
                            <td>${recruiter.email}</td>
                            <td>${recruiter.company}</td>
                            <td>${recruiter.description}</td>
                            <td>${recruiter.address}</td>
                            <td>${recruiter.city}</td>
                            <td>${recruiter.country}</td>
                            <td>
                                <form action="${contextPath}/GestionRecruiters?id_recruiter=${recruiter.id_recruiter}&action=delete" method="post">
                                    <button type="submit" class="btn btn-danger">Delete</button>
                                </form>
                            </td>
                            <td>
                                <form action="${contextPath}/GestionRecruiters?id_recruiter=${recruiter.id_recruiter}&action=edit" method="post">
                                    <button type="submit" class="btn btn-primary">Edit</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
  

    <!-- Add Bootstrap JS and Popper.js scripts (optional) -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
