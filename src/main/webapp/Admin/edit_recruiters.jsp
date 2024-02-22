<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<%@ include file="../Default/head.jsp" %>

<body style="background-image: url(../assets/img/hero-bg.png);">
    <%@ include file="header4.jsp" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <div class="container" style="margin-top: 150px; margin-bottom: 100px;">
        <div class="row justify-content-center">
            <!-- New column for the form -->
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <form action="${contextPath}/dashboard?action=update_rec" method="post">
                            <h1 class="text-center" style="color: #012970;">Update Recruiter Information</h1> <br>
                            <input type="hidden" class="form-control" name="id_recruiter" id="id_recruiter" value="${recruiterToEdit.id_recruiter}">
                            
                            <div class="form-group">
                                <label for="name" style="color: #012970; border-color: #012970; font-weight:bold;">Name</label>
                                <input type="text" class="form-control" name="name" id="name" value="${recruiterToEdit.name}" required>
                            </div><br>
                            
                            <div class="form-group" style="color: #012970; border-color: #012970; font-weight:bold;">
                                <label for="email">Email</label>
                                <input type="email" class="form-control" name="email" id="email" value="${recruiterToEdit.email}" required>
                            </div><br>
                            
                            <div class="form-group">
                                <label for="company" style="color: #012970; border-color: #012970; font-weight:bold;">Company</label>
                                <input type="text" class="form-control" name="company" id="company" value="${recruiterToEdit.company}" required>
                            </div><br>
                            
                            <div class="form-group">
                                <label for="description" style="color: #012970; border-color: #012970; font-weight:bold;">Description</label>
                                <textarea class="form-control" name="description" id="description" style="height: 150px;" required>${recruiterToEdit.description}</textarea>
                            </div><br>
                            
                            <div class="form-group">
                                <label for="address" style="color: #012970; border-color: #012970; font-weight:bold;">Address</label>
                                <input type="text" class="form-control" name="address" id="address" value="${recruiterToEdit.address}" required>
                            </div><br>
                            
                            <div class="form-group">
                                <label for="city" style="color: #012970; border-color: #012970; font-weight:bold;">City</label>
                                <input type="text" class="form-control" name="city" id="city" value="${recruiterToEdit.city}" required>
                            </div><br>
                            
                            <div class="form-group">
                                <label for="country" style="color: #012970; border-color: #012970; font-weight:bold;">Country</label>
                                <input type="text" class="form-control" name="country" id="country" value="${recruiterToEdit.country}" required>
                            </div><br>
                            
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
