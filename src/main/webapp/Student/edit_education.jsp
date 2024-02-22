<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.Base64" %>
<%@ page import="Beans.student" %>
<!DOCTYPE html>
<html lang="en">
<%@ include file="../Default/head.jsp" %>

<body style="background-image: url(../assets/img/hero-bg.png);">
    <%@ include file="header2.jsp" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <div class="container" style="margin-top: 150px; margin-bottom: 100px;">
        <div class="row justify-content-center">
            <!-- Nouvelle colonne pour le formulaire -->
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <form action="${contextPath}/gestionEducation" method="post">
                         <h1 class="text-center" style="color: #012970">Update your Education Infos </h1> <br>
                    <input  type="hidden" class="form-control" name="idEducation" id="idEducation" value="${educationToEdit.idEducation}">
                    
                    <div class="form-group">
                        <label for="institution" style="color: #012970; border-color: #012970; font-weight:bold;">Institution</label>
                        <input type="text" class="form-control" name="institution" id="institution"  value="${educationToEdit.institution}">
                    </div><br>
                    
                    <div class="form-group" style="color: #012970; border-color: #012970; font-weight:bold;">
                        <label for="diploma">Diploma</label>
                        <input type="text" class="form-control" name="diploma" id="diploma"  value="${educationToEdit.diploma}">
                    </div><br>
                    
                    <div class="form-group">
                        <label for="startDate" style="color: #012970; border-color: #012970; font-weight:bold;">Start Date</label>
                        <input type="date" class="form-control" name="startDate" id="startDate" value="${educationToEdit.startDate}">
                    </div><br>
                    
                    <div class="form-group">
                        <label for="endDate" style="color: #012970; border-color: #012970; font-weight:bold;">End Date</label>
                        <input type="date" class="form-control" name="endDate" id="endDate" value="${educationToEdit.endDate}">
                    </div><br>
                    
                   <input type="hidden" name="action" value="update">
                   
                    
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary" style="background-color: #012970; border-color: #012970;">Save</button>
                    </div>
                </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
