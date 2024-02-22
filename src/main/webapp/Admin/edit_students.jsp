<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Base64" %>
<%@ page import="Beans.student" %>

<!DOCTYPE html>
<html lang="en">
<%@ include file="../Default/head.jsp" %>

<body style="background-image: url(../assets/img/hero-bg.png);">
    <%@ include file="header4.jsp" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <div class="container" style="margin-top: 150px; margin-bottom: 100px;">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <form action="${contextPath}/dashboard?action=update_student" method="post"   onsubmit="return checkPasswords();">
                            <h1 class="text-center" style="color: #012970">Edit Student Informations</h1><br>
                            <!-- Hidden field for student ID -->
                            <input type="hidden" class="form-control" name="id_student" id="id_student" value="${studentToEdit.id_student}">
                            <input type="hidden" class="form-control" name="old_email" id="old_email" value="${studentToEdit.email}">

                            
                           

                            <!-- First Name -->
                            <div class="form-group">
                                <label for="firstName" style="color: #012970; border-color: #012970; font-weight:bold;">First Name</label>
                                <input type="text" class="form-control" name="firstName" id="firstName"
                                    value="${studentToEdit.first_name}">
                            </div><br>

                            <!-- Last Name -->
                            <div class="form-group" style="color: #012970; border-color: #012970; font-weight:bold;">
                                <label for="lastName">Last Name</label>
                                <input type="text" class="form-control" name="lastName" id="lastName"
                                    value="${studentToEdit.last_name}">
                            </div><br>

                            <!-- Email -->
                            <div class="form-group">
                                <label for="email" style="color: #012970; border-color: #012970; font-weight:bold;">Email</label>
                                <input type="email" class="form-control" name="email" id="email"
                                    value="${studentToEdit.email}">
                            </div><br>

                            <!-- Phone -->
                            <div class="form-group">
                                <label for="phone" style="color: #012970; border-color: #012970; font-weight:bold;">Phone</label>
                                <input type="tel" class="form-control" name="phone" id="phone"
                                    value="${studentToEdit.phone}">
                            </div><br>

                           







                            <!-- Password -->
                            <div class="form-group">
                                <label for="password" style="font-weight: bold; color: #012970">Password:</label>
                                <input type="password" class="form-control" id="password" name="password"
                                    placeholder="Password">
                            </div><br>
                            <div class="form-group">
                                <label for="confirmPassword" style="font-weight: bold; color: #012970">Confirm Password:</label>
                                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword"
                                    placeholder="Re-enter the password">
                            </div><br>

                        <script>
    function checkPasswords() {
        var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("confirmPassword").value;

        if (password === confirmPassword) {
            return true; // Allow form submission
        } else {
            alert("Passwords do not match. Please re-enter.");
            document.getElementById("password").value = "";
            document.getElementById("confirmPassword").value = "";
            return false; // Prevent form submission
        }
    }
</script>



                            <!-- Hidden field for action -->
                            <input type="hidden" name="action" value="update">

                            <!-- Submit button -->
                            <div class="text-center">
                                <button type="submit" class="btn btn-primary"
                                    style="background-color: #012970; border-color: #012970;" >Save Changes</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
