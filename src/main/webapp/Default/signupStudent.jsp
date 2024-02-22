<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">

<%@ include file="head.jsp" %>

<body style="background-color: #f4f4fc;">
 <%@ include file="header.jsp" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
  <div class="container"style="margin-top :  150px ;margin-bottom :  100px ;   ">
    <div class="row justify-content-center">
      <!-- Nouvelle colonne pour le formulaire -->
      <div class="col-md-6">
        <div class="card">
          <div class="card-body">
          
            <form action="${contextPath}/GestionStudents" method="post" enctype="multipart/form-data">

              <input type="hidden" name="action" value="create">
              <div class="form-group">
                <h1 class="text-center" style="color: #012970">Student Sign Up</h1>
                <label for="firstName" style="font-weight: bold; color: #012970">First Name:</label>
                <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First Name"   required>
              </div>
              <br>
              <div class="form-group">
                <label for="lastName" style="font-weight: bold;color: #012970">Last Name:</label>
                <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name" required>
              </div>
              <br>
              <div class="form-group">
                <label for="email" style="font-weight: bold;color: #012970">Email:</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
              </div>
              <br>
              <div class="form-group">
                <label for="phone" style="font-weight: bold;color: #012970">Phone:</label>
                <input type="text" class="form-control" id="phone" name="phone" placeholder="Phone" required>
              </div>
              <br>
              <div class="form-group">
                <label for="picture" style="font-weight: bold;color: #012970">Picture:</label>
                 <input type="file" class="form-control" id="picture" name="picture" placeholder="picture" accept="image/*">
              </div>
              
              
              <br>
             <div class="form-group">
                                <label for="skill" style="font-weight: bold; color: #012970">Skill:</label>
                                <select class="form-control" id="skill" name="skill">
                                    <option value="" selected disabled>Select a skill</option>
                                    <c:forEach var="skill" items="${skills}">
                                        <option value="${skill.name}">${skill.name}</option>
                                    </c:forEach>
                                </select>
          </div>
              <br>
              <div class="form-group">
              <label for="cv" style="font-weight: bold;color: #012970">CV (PDF only):</label>
             <input type="file" class="form-control" id="cv" name="cv" accept=".pdf" placeholder="CV">
              </div>

              <br>
              <div class="form-group">
                <label for="linkedin" style="font-weight: bold;color: #012970">LinkedIn:</label>
                <input type="text" class="form-control" id="linkedin" name="linkedin" placeholder="LinkedIn">
              </div>
              <br>
              <div class="form-group">
                  <label for="password" style="font-weight: bold; color: #012970">Password:</label>
                  <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
              </div>
             <br>
              <div class="form-group">
                 <label for="confirmPassword" style="font-weight: bold; color: #012970">Confirm Password:</label>
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Re-enter the password" required>
                 </div>
              <br>
              
              <div class="text-center">
                <button type="submit" class="btn btn-primary" onclick="checkPasswords()">Create</button>
              </div>
           
              
                     <script>
    function checkPasswords() {
        var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("confirmPassword").value;

        if (password === confirmPassword) { 
           
            
        } else {
            alert("Passwords do not match. Please re-enter.");

            
            document.getElementById("password").value = "";
            document.getElementById("confirmPassword").value = "";
        }
    }
</script>
            
   </form>
             <p class="mt-3 text-center">You already have an account? <a href="Login.jsp">Sign in here</a>.</p>
            <p class="mt-3 text-center">Are you a recruiter? <a href="signupRecruiter.jsp">Sign-up here</a>.</p>
          </div>
        </div>
      </div>
    </div>
  </div>
<%@ include file="footer.jsp" %>
<%@ include file="links.jsp" %>

  <!-- Vos balises de script et liens vers les scripts restent inchangés -->
</body>
</html>
