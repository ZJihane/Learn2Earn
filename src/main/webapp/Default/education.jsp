<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<%@ include file="head.jsp" %>

<body style="background-image: url(../assets/img/hero-bg.png);">
    <%@ include file="header.jsp" %>
    <div class="container" style="margin-top: 150px; margin-bottom: 100px;">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <form action="${contextPath}/GestionEducation" method="post">
                            <input type="hidden" name="action" value="create">
                            <div class="form-group">
                                <h1 class="text-center" style="color: #012970">Education Information</h1>
                                <label for="institution" style="font-weight: bold; color: #012970">Institution:</label>
                                <input type="text" class="form-control" id="institution" name="institution"
                                    placeholder="Institution" required>
                            </div>
                            <br>
                            <div class="form-group">
                                <label for="diploma" style="font-weight: bold;color: #012970">Diploma:</label>
                                <input type="text" class="form-control" id="diploma" name="diploma"
                                    placeholder="Diploma" required>
                            </div>
                            
                            <div class="form-group">
                                <input type="text" class="form-control" id="id_student" name="id_student"
                                    placeholder="Student ID" required hidden>
                            </div>
                            <br>
                            <div class="form-group">
                                <label for="start_date" style="font-weight: bold;color: #012970">Start Date:</label>
                                <input type="date" class="form-control" id="start_date" name="start_date" required>
                            </div>
                            <br>
                            <div class="form-group">
                                <label for="end_date" style="font-weight: bold;color: #012970">End Date:</label>
                                <input type="date" class="form-control" id="end_date" name="end_date" required>
                            </div>
                            <br>
                            <div class="text-center">
                                <button type="submit" class="btn btn-primary">Create</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="footer.jsp" %>
    <%@ include file="links.jsp" %>
</body>
</html>
