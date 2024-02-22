<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<%@ include file="head.jsp" %>

<body style=" background-image: url(../assets/img/hero-bg.png);">
  <%@ include file="header.jsp" %>
  <c:set var="contextPath" value="${pageContext.request.contextPath}" />
  <div class="container" style="margin-top: 150px; margin-bottom: 100px;">
    <div class="row justify-content-center">
      <!-- New column for the project form -->
      <div class="col-md-6">
        <div class="card">
          <div class="card-body">

            <form action="${contextPath}/GestionProjects" method="post" id="projectForm">

              <input type="hidden" name="action" value="create">
              <div class="form-group">
                <h1 class="text-center" style="color: #012970">Add your Projects :</h1>

                <div class="project-container">
                  <!-- Initial project input fields -->
                  <div class="project-input">
                    <label for="name" style="font-weight: bold; color: #012970">Project Name:</label>
                    <input type="text" class="form-control" name="name" placeholder="Project Name" required>
                    <br>
                    <label for="description" style="font-weight: bold; color: #012970">Project Description:</label>
                    <textarea class="form-control" name="description" placeholder="Project Description" rows="3" required></textarea>
                    <br>
                    <label for="start_date" style="font-weight: bold; color: #012970">Start Date:</label>
                    <input type="date" class="form-control" name="start_date" required>
                    <br>
                    <label for="end_date" style="font-weight: bold; color: #012970">End Date:</label>
                    <input type="date" class="form-control" name="end_date" required>
                    <br>
                  </div>
                </div>
              </div>

              <div class="text-center">
                <!-- Button to add more projects -->
                <button type="button" class="btn btn-success" onclick="addProject()">+</button>
                <button type="submit" class="btn btn-primary">Create Projects</button>
              </div>

            </form>

          </div>
        </div>
      </div>
    </div>
  </div>
  <%@ include file="footer.jsp" %>
  <%@ include file="links.jsp" %>

  <!-- Your script tags and other links to scripts remain unchanged -->
  <script>
    function addProject() {
      // Clone the last project input fields and append to the project-container
      var lastProject = document.querySelector('.project-input:last-child');
      var newProject = lastProject.cloneNode(true);
      document.querySelector('.project-container').appendChild(newProject);
    }
  </script>
</body>

</html>
