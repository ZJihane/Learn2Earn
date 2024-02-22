<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<%@ include file="head.jsp" %>
<body style=" background-color: #f4f4fc;">

<%@ include file="header.jsp" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

    <div class="container"style="margin-top :  150px ;margin-bottom :  100px ;   ">
        <div class="row justify-content-center">
            <!-- Column for the recruiter form -->
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <form  method="post" action="${contextPath}/GestionRecruiters?action=create">

                            <div class="form-group">
                                <h1 class="text-center" style="color: #012970">Create Recruiter</h1>
                                <label for="name" style="font-weight: bold;color: #012970">Name:</label>
                                <input type="text" class="form-control" id="name" name="name" placeholder="Name" required>
                            </div>
                            <br>
                            <div class="form-group">
                                <label for="email" style="font-weight: bold;color: #012970">Email:</label>
                                <input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
                            </div>
                            <br>
                            <div class="form-group">
                                <label for="company" style="font-weight: bold;color: #012970">Company:</label>
                                <input type="text" class="form-control" id="company" name="company" placeholder="Company" required>
                            </div>
                            <br>
                            <div class="form-group">
                                <label for="description" style="font-weight: bold;color: #012970">Description:</label>
                                <input type="text" class="form-control" id="description" name="description" placeholder="Description">
                            </div>
                            <br>
                            <div class="form-group">
                                <label for="address" style="font-weight: bold;color: #012970">Address:</label>
                                <input type="text" class="form-control" id="address" name="address" placeholder="Address" required>
                            </div>
                            <br>
                            <div class="form-group">
                                <label for="city" style="font-weight: bold;color: #012970">City:</label>
                                <input type="text" class="form-control" id="city" name="city" placeholder="City" required>
                            </div>
                            <br>
                            <div class="form-group">
                                <label for="country" style="font-weight: bold;color: #012970">Country:</label>
                                <input type="text" class="form-control" id="country" name="country" placeholder="Country" required>
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
                                <button type="submit" class="btn btn-primary" onclick="checkPasswords()">Create Recruiter</button>
                            </div>
                            <p class="mt-3 text-center">You already have an account? <a href="Login.jsp">Sign in here</a>.</p>
                         <p class="mt-3 text-center">Are you a student? <a href="${contextPath}/GestionStudents">Sign-up here</a>.</p>
                        </form>
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



                    </div>
                </div>
            </div>

                   

                        
                    </div>
                </div>
           

<%@ include file="footer.jsp" %>
  <%@ include file="links.jsp" %>
</body>
</html>
