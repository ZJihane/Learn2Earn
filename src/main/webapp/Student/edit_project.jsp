<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.Date" %>
<%@ page import="Beans.project" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../Default/head.jsp" %>
    <title>Edit Project</title>
</head>
<body style="background-image: url(../assets/img/hero-bg.png);">
    <%@ include file="header2.jsp" %>
    
    <div class="container" style="margin-top: 150px; margin-bottom: 100px;">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <form action="${contextPath}/gestionProjects" method="post">
                            <h1 class="text-center" style="color: #012970">Edit Project Information</h1><br>
                            
                            <!-- Hidden field for project ID -->
                            <input type="hidden" class="form-control" id="id_project" name="id_project" value="${projectToEdit.id_project}">
                            
                            <!-- Project Name -->
                            <div class="form-group">
                                <label for="projectName" style="color: #012970; border-color: #012970; font-weight:bold;">Project Name</label>
                                <input type="text" class="form-control" name="name" id="name" value="${projectToEdit.name}">
                            </div><br>
                            
                            <!-- Project Description -->
                            <div class="form-group" style="color: #012970; border-color: #012970; font-weight:bold;">
                                <label for="projectDescription">Project Description</label>
                                <textarea class="form-control" name="description" id="description">${projectToEdit.description}</textarea>
                            </div><br>
                            
                            <!-- Project Start Date -->
                            <div class="form-group">
                                <label for="startDate" style="color: #012970; border-color: #012970; font-weight:bold;">Start Date</label>
                                <input type="date" class="form-control" name="start_date" id="start_date"  value='${projectToEdit.start_date}'>
                            </div><br>
                            
                            <!-- Project End Date -->
                            <div class="form-group">
                                <label for="endDate" style="color: #012970; border-color: #012970; font-weight:bold;">End Date</label>
                                <input type="date" class="form-control" name="end_date" id="end_date"  value='${projectToEdit.end_date}'>
                            </div><br>
                            
                            <!-- Hidden field for action -->
                            <input type="hidden" name="action" value="update">
                            
                            <!-- Submit button -->
                            <div class="text-center">
                                <button type="submit" class="btn btn-primary" style="background-color: #012970; border-color: #012970;">Save Changes</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
