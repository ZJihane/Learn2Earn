<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@ page import="java.util.Base64" %>
<%@ page import="Beans.student" %>
<!DOCTYPE html>
<html lang="en">
<%@ include file="../Default/head.jsp" %>

  <body style="background-color: #f4f4fc;">
      <%@ include file="header2.jsp" %>
 <!-- Modal creation education --> 
 <div class="modal" id="m_create_education" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" style=" color: #012970; border-color: #012970; font-weight:bold ;">Add Education</h4>
                <button class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                
                <form action="${contextPath}/gestionEducation?action=create" method="post">
                    <div class="form-group">
                        <label for="institution" style="color: #012970; border-color: #012970; font-weight:bold ;">Institution</label>
                        <input type="text" class="form-control" name="institution" id="institution" placeholder="Enter Institution">
                    </div><br>
                    <div class="form-group" style="color: #012970; border-color: #012970; font-weight:bold ;">
                        <label for="diploma">Diploma</label>
                        <input type="text" class="form-control" name="diploma" id="diploma" placeholder="Enter Diploma">
                    </div><br>
                    <div class="form-group">
                        <label for="startDate" style="color: #012970; border-color: #012970; font-weight:bold ;">Start Date</label>
                        <input type="date" class="form-control" name="startDate" id="startDate">
                    </div><br>
                    <div class="form-group">
                        <label for="endDate" style="color: #012970; border-color: #012970; font-weight:bold ;">End Date</label>
                        <input type="date" class="form-control" name="endDate" id="endDate">
                    </div><br>
                      <input type="text" class="form-control" name="id_student" id="id_student" value="${loggedInStudent.id_student}" hidden>
                    <div class="text-center">
                    <button type="submit" class="btn btn-primary" style="background-color: #012970; border-color: #012970;" >Save</button>
                    </div>
                    
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal creation project -->
<div class="modal" id="m_create_project">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" style="color: #012970; border-color: #012970; font-weight: bold;">Add Project</h4>
                <button class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">

                <form action="${contextPath}/gestionProjects?action=create" method="post">
                    <div class="form-group">
                        <label for="projectName" style="color: #012970; border-color: #012970; font-weight: bold;">Project Name</label>
                        <input type="text" class="form-control" name="projectName" id="projectName" placeholder="Enter Project Name">
                    </div><br>
                    <div class="form-group" style="color: #012970; border-color: #012970; font-weight: bold;">
                        <label for="projectDescription">Project Description</label>
                        <textarea class="form-control" name="projectDescription" id="projectDescription" placeholder="Enter Project Description"></textarea>
                    </div><br>
                    <div class="form-group">
                        <label for="startDate" style="color: #012970; border-color: #012970; font-weight: bold;">Start Date</label>
                        <input type="date" class="form-control" name="startDate" id="startDate">
                    </div><br>
                    <div class="form-group">
                        <label for="endDate" style="color: #012970; border-color: #012970; font-weight: bold;">End Date</label>
                        <input type="date" class="form-control" name="endDate" id="endDate">
                    </div><br>
                    <input type="text" class="form-control" name="id_student" id="id_student" value="${loggedInStudent.id_student}" hidden>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary" style="background-color: #012970; border-color: #012970;">Save</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>


   

<div class="container" style="margin-top: 120px; margin-bottom: 100px;">
 <div class="row justify-content-center">
            <div class="col-md-14">
                <div class="card" style="background-color: #f9fafe;">
                    <div class="card-body">
                        <%
                            student loggedInStudent = (student) session.getAttribute("loggedInStudent");
                            if (loggedInStudent != null) {
                                byte[] pictureData = loggedInStudent.getPicture();
                                if (pictureData != null) {
                                    String pictureBase64 = Base64.getEncoder().encodeToString(pictureData);
                                    loggedInStudent.setPictureBase64(pictureBase64);
                                } else {
                                    loggedInStudent.setPictureBase64(""); // Set an empty string or handle it according to your needs
                                }
                            }
                        %>

                        <div class="section">
                            <div class="row justify-content-center">
                                <div class="col-md-4 text-center">
                                    <img src="data:image/jpeg;base64,${loggedInStudent.pictureBase64}" alt="picture" class="img-fluid rounded-circle" style="width: 9em; height: 9em; margin-right: 5px;">
                                    <br><br>
                                    <form action="${contextPath}/GestionStudents?action=edit" method="post" style="display: inline;">
                                    <input type="hidden" name="id_student" value="${loggedInStudent.id_student}">
                                        <button type="submit" class="btn btn-outline-primary btn-sm" style="background-color: #012970; border-color: #012970; color: white;">
                                                 Edit Profile
                                        </button>
                                     </form>
                                    
                                  
                                </div>
                                <div class="col-md-4 text-left">
                                    <h2 style="color: #012970;"> ${loggedInStudent.first_name} ${loggedInStudent.last_name} </h2>
                                    <p><strong style="color:#012970;">Email:</strong> ${loggedInStudent.email}</p>
                                    <p><strong style="color: #012970;">Phone:</strong> ${loggedInStudent.phone}</p>
                                    <p><strong style="color: #012970;">Skill:</strong> ${loggedInStudent.skill}</p>
                                   <p>
    <strong style="color: #012970;">LinkedIn:</strong>
   
    <a href="${loggedInStudent.linkedin}" target="_blank" style="text-decoration: none;">
        Go to LinkedIn
        <i class="bi bi-linkedin"></i>
    </a>
</p>

                                   <c:if test="${not empty loggedInStudent.cv}">
                                    <p><strong style="color: #012970;"> CV:</strong>
                                            <a href="${contextPath}/DownloadServlet" style="text-decoration: none;">
                                                Download cv
                                                <i class="bi bi-file-pdf"></i>
                                            </a>
                                     </p>
                                  </c:if>
                                </div>
                            </div>
                        </div>
                        <br>
                        
                     <div class="section">
    <h2 style="color: #012970;">Education &nbsp;&nbsp;&nbsp;&nbsp;
        <button class="btn btn-outline-primary btn-sm ml-auto" style="background-color: #012970; border-color: #012970;" data-bs-toggle="modal" data-bs-target="#m_create_education">
            <i class="bi bi-plus" style="color: white;"></i>
        </button>
    </h2>

    <c:forEach var="education" items="${educations}">
        <div class="card">
            <div class="card-body">
                <div class="d-flex justify-content-between align-items-center">
                    <h3 style="color: #012970;">${education.institution}</h3>
                    <div class="button-group">
    <!-- Edit Form -->
    <form action="${contextPath}/gestionEducation?action=edit" method="post" style="display: inline;">
        <input type="hidden" name="id_education" value="${education.idEducation}">
        <button type="submit" class="btn btn-outline-primary btn-sm" style="background-color: #012970; border-color: #012970;">
            <i class="bi bi-pencil" style="color: white;"></i>
        </button>
    </form>

    <!-- Delete Form -->
   <form action="${contextPath}/gestionEducation?action=delete" method="post" style="display: inline;"
    onsubmit="return confirm('Are you sure you want to delete this education entry?');">
    <input type="hidden" name="id_education" value="${education.idEducation}">
    <button type="submit" class="btn btn-outline-danger btn-sm" style="background-color: #012970; border-color: #012970;">
        <i class="bi bi-trash" style="color: white;"></i>
    </button>
</form>

</div>

                </div>
                
                <p><strong style="color: #012970;">Institution:</strong> ${education.institution}</p>
                <p><strong style="color: #012970;">Diploma:</strong> ${education.diploma}</p>
                <p><strong style="color: #012970;">Start Date:</strong> ${education.startDate}</p>
                <p><strong style="color: #012970;">End Date:</strong> ${education.endDate}</p>
            </div>
        </div>
        <br>
    </c:forEach>
</div>

<div class="section">
    <h2 style="color: #012970;">Projects &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button class="btn btn-outline-primary btn-sm ml-auto" style="background-color: #012970; border-color: #012970;" data-bs-toggle="modal" data-bs-target="#m_create_project">
            <i class="bi bi-plus" style="color: white;"></i>
        </button>
    </h2>

    <c:forEach var="project" items="${projects}">
        <div class="card">
            <div class="card-body">
                <div class="d-flex justify-content-between align-items-center">
                    <h3 style="color: #012970;">${project.name}</h3>
                     <div class="button-group">
    <!-- Edit Form -->
    <form action="${contextPath}/gestionProjects?action=edit" method="post" style="display: inline;">
        <input type="hidden" name="id_project" value="${project.id_project}">
        <button type="submit" class="btn btn-outline-primary btn-sm" style="background-color: #012970; border-color: #012970;">
            <i class="bi bi-pencil" style="color: white;"></i>
        </button>
    </form>

    <!-- Delete Form -->
<form action="${contextPath}/gestionProjects?action=delete" method="post" style="display: inline;"
    onsubmit="return confirm('Are you sure you want to delete this project?');">
    <input type="hidden" name="id_project" value="${project.id_project}">
    <button type="submit" class="btn btn-outline-danger btn-sm" style="background-color: #012970; border-color: #012970;">
        <i class="bi bi-trash" style="color: white;"></i>
    </button>
</form>

</div>

                    </div>
                
                
                <p><strong style="color: #012970;">Description:</strong> ${project.description}</p>
                <p><strong style="color: #012970;">Start Date:</strong> ${project.start_date}</p>
                <p><strong style="color: #012970;">End Date:</strong> ${project.end_date}</p>
            </div>
        </div>
        <br>
    </c:forEach>
</div>
                     
                     
                     
                     
                         <br>
                        </div>
                    </div>
                </div>
            </div>
        </div>

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>

</body>
</html>
