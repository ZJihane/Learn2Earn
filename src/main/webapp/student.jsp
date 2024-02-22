<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student List</title>
    
    <!-- Add Bootstrap CSS link -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />

  

        <!-- Move the "Add" button to the left -->
        <h1>Gestion Etudiants</h1>
        
        <div class="float-left">
            <form action="${contextPath}/GestionStudents?action=add" method="post">
                <button type="submit" class="btn btn-success">Add</button>
            </form>
        </div>

        <!-- Center the table horizontally -->
        
        <div>
       
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Email</th>
                        <th>Téléphone</th>
                        <th>LinkedIn</th>
                        <th>CV</th>
                        <th>Image</th>
                        <th>Delete</th>
                        <th>Edit</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${students}" var="student" varStatus="boucle">
                        <tr>
                            <td>${student.id_student}</td>
                            <td>${student.first_name}</td>
                            <td>${student.last_name}</td>
                            <td>${student.email}</td>
                            <td>${student.phone}</td>
                            <td>${student.linkedin}</td>
                            <td>${student.cv}</td>
                            <td>${student.picture}</td>
                            <td>
                                <form action="${contextPath}/GestionStudents?id_student=${student.id_student}&action=delete" method="post">
                                    <button type="submit" class="btn btn-danger">Delete</button>
                                </form>
                            </td>
                            <td>
                                <form action="${contextPath}/GestionStudents?id_student=${student.id_student}&action=edit" method="post">
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
