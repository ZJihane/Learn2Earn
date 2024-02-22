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
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <form action="${contextPath}/GestionStudents" method="post" enctype="multipart/form-data"  onsubmit="return checkPasswords();">
                            <h1 class="text-center" style="color: #012970">Edit Student Informations</h1><br>
                            <!-- Hidden field for student ID -->
                            <input type="hidden" class="form-control" name="id_student" value="${studentToEdit.id_student}">

                            <!-- Picture -->
                            <div class="form-group">
                                <label for="picture" style="color: #012970; border-color: #012970; font-weight:bold;">Picture</label>
                                <div class="image-container text-center">
                                    <img src="data:image/png;base64, ${studentToEdit.pictureBase64}" alt="picture"
                                        class="img-fluid rounded-circle" style="width: 9em; height: 9em; margin-right: 5px;">
                                </div>
                            </div><br>
                            <div class="form-group">
                                <label for="newPicture" style="color: #012970; border-color: #012970; font-weight:bold;">New Picture</label>
                                <input type="file" class="form-control" name="newPicture" id="newPicture">
                            </div><br>

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

                           <!-- Skill -->
                           
                          <div class="form-group">
                                <label for="skill" style="font-weight: bold; color: #012970">Skill:</label>
                                <select class="form-control" id="skill" name="skill">
                                       <option value="" disabled>Select a skill</option>
                                      <c:forEach var="skillOption" items="${skills}">
                                      <c:choose>
                                       <c:when test="${skillOption.name eq studentToEdit.skill}">
                                       <option value="${skillOption.name}" selected>${skillOption.name}</option>
                                      </c:when>
                                      <c:otherwise>
                                     <option value="${skillOption.name}">${skillOption.name}</option>
                                     </c:otherwise>
                                      </c:choose>
                                   </c:forEach>
                                </select>
                        </div><br>






                            <!-- Linkedin -->
                            <div class="form-group">
                                <label for="linkedin" style="color: #012970; border-color: #012970; font-weight:bold;">Linkedin</label>
                                <input type="text" class="form-control" name="linkedin" id="linkedin"
                                    value="${studentToEdit.linkedin}">
                            </div><br>

                            <!-- CV -->
                            <div class="form-group">
                                <label for="cv" style="color: #012970; border-color: #012970; font-weight:bold;">CV</label><br>

                                <c:if test="${not empty loggedInStudent.cv}">
                                    <div class="text-center">
                                        <a href="${contextPath}/DownloadServlet" style="text-decoration: none;"
                                            class="text-center">
                                            Download CV
                                            <i class="bi bi-file-pdf"></i>
                                        </a>
                                    </div>
                                </c:if><br>

                                <div class="form-group">
                                    <label for="newCv" style="color: #012970; border-color: #012970; font-weight:bold;">New CV (PDF only)</label>
                                    <input type="file" accept=".pdf" class="form-control" name="newCv" id="newCv">
                                </div>
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
