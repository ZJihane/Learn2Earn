<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Base64" %>
<%@ page import="Beans.student" %>

<!DOCTYPE html>
<html lang="en">
<%@ include file="../Default/head.jsp" %>

<body style="background-color: #f4f4fc;">
    <%@ include file="header3.jsp" %>

    <div class="container" style="margin-top: 120px; margin-bottom: 100px;">
        <div class="row justify-content-center">
            <div class="col-md-14">
                <div class="card" style="background-color: #f9fafe;">
                    <div class="card-body">
                        <%
                            student showStudent = (student) session.getAttribute("student");
                            if (showStudent != null) {
                                byte[] pictureData = showStudent.getPicture();
                                if (pictureData != null) {
                                    String pictureBase64 = Base64.getEncoder().encodeToString(pictureData);
                                    showStudent.setPictureBase64(pictureBase64);
                                } else {
                                    showStudent.setPictureBase64(""); // Set an empty string or handle it according to your needs
                                }
                            }
                        %>

                        <div class="section">
                            <div class="row justify-content-center">
                                <div class="col-md-4 text-center">
                                    <img src="data:image/jpeg;base64,${student.pictureBase64}" alt="picture" class="img-fluid rounded-circle" style="width: 9em; height: 9em; margin-right: 5px;">
                                    <br><br>
                                </div>
                                <div class="col-md-4 text-left">
                                    <h2 style="color: #012970;"> ${student.first_name} ${student.last_name} </h2>
                                    <p><strong style="color:#012970;">Email:</strong> ${student.email}</p>
                                    <p><strong style="color: #012970;">Phone:</strong> ${student.phone}</p>
                                    <p><strong style="color: #012970;">Skill:</strong> ${student.skill}</p>
                                    <p>
                                        <strong style="color: #012970;">LinkedIn:</strong>
                                        <a href="${student.linkedin}" target="_blank" style="text-decoration: none;">
                                            Go to LinkedIn
                                            <i class="bi bi-linkedin"></i>
                                        </a>
                                    </p>
                                    <c:if test="${not empty student.cv}">
                                        <p>
                                            <strong style="color: #012970;"> CV:</strong>
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
                            <h2 style="color: #012970;">Education &nbsp;&nbsp;&nbsp;&nbsp;</h2>

                            <c:forEach var="education" items="${educations}">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <h3 style="color: #012970;">${education.institution}</h3>
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
                            <h2 style="color: #012970;">Projects &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h2>

                            <c:forEach var="project" items="${projects}">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <h3 style="color: #012970;">${project.name}</h3>
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
</body>

</html>
