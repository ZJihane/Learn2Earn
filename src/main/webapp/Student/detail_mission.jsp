<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.Date" %>
<%@ page import="Beans.project" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../Default/head.jsp" %>
    <title>Edit Project</title>
    <style>
        body {
            background-image: url(../assets/img/hero-bg.png);
            background-size: cover;
            color: #012970;
            font-family: 'Arial', sans-serif;
        }

        .container {
            margin-top: 150px;
            margin-bottom: 100px;
        }

        .card {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border: none;
        }

        .card-body {
            padding: 30px;
        }

        h1 {
            font-size: 36px;
            margin-bottom: 30px;
            color: #012970;
        }

        h4 {
            font-size: 20px;
            margin-bottom: 10px;
            color: #012970;
        }

        

        .text-center {
            text-align: center;
        }

        .divider {
            border-top: 2px solid #012970;
            margin: 20px 0;
        }
  
   .btn-show-details {
    background-color: #012970;
    color: #fff;
    border: 1px solid #012970;
    border-radius: 5px;
    transition: background-color 0.3s; /* Add a smooth transition effect */

}

.btn-show-details:hover {
    background-color: #012970; 
     color: #fff;/* Keep the same color on hover */
}
      
    </style>
</head>
<body>
    <%@ include file="header2.jsp" %>
    
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                 <c:if test="${param.existence == 'true'}">
    <script>
        var alertMessage = "You have already applied to this mission!";
        alert(alertMessage);
    </script>
</c:if>
                    <div class="card-body">
                        <h1 class="text-center">Mission Details</h1>
                        <div class="divider"></div>
                        
                        <h4>Mission:</h4>
                        <div class="text-center"><b>${mission.name}</b></div><br>
                        
                         <h4>Mission's Description:</h4>
                        <div class="text-center">${mission.description}</div>

                        <div class="divider"></div>
                        

                        <h4>Posted by:</h4>
                        <div class="text-center"><b>${recruiter.name}</b></div> 

                        <h4>Company:</h4>
                        <div class="text-center"><b>${recruiter.company}</b></div> <br>
                        
                       
                        
<div class="text-center">
    <a href="${contextPath}/Student/Company_details.jsp?recruiter=${recruiter}"  style="color: #012970;">
        <i class="bi bi-info-circle"></i>
        Check the company's infos
    </a>
</div>

                        <div class="divider"></div>

                      
                        <h4>Remuneration:</h4>
                        <div class="text-center remuneration" style="color: #012970;"><b>${mission.remuneration}</b></div>

                        <h4>Required Skills:</h4>
                        <div class="text-center"><b>${mission.mission_skill}</b></div><br><br>
                        <div class="text-center">
                        
                         <form action="${contextPath}/gestionApplications?action=create" method="post" style="display: inline;">
                                    <input type="hidden" name="id_mission" value="${mission.id_mission}">
                                    <input type="hidden" name="id_student" value="${loggedInStudent.id_student}">
                                    <button type="submit" class="btn btn-show-details">Apply for this mission </button>
                        </form>
                        </div>
                       
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
