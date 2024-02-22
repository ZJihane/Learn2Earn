<!DOCTYPE html>
<html lang="en">
<%@ include file="../Default/head.jsp" %>
<style>
    .profile-button {
        background-color: #012970;
        color: #fff;
        border: 1px solid #012970;
        border-radius: 5px;
        padding: 5px 10px;
        text-decoration: none;
        display: inline-block;
        transition: background-color 0.3s;
    }

    .profile-button:hover {
        background-color: #012970;
        color: #fff;
    }

    .decision-buttons {
        display: flex;
        gap: 10px;
        
    }

    .decision-button {
        padding: 5px 10px;
       margin-left: 10px;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    .approve-button {
        background-color: #009933 ;
         border:  #009933 ;
        color: #fff;
    }

    .reject-button {
        
        background-color: #ff1a1a ;
         border:#ff1a1a ;
          color: #fff;
    }

    .outer-container {
        background-color: #f9fafe;
        padding: 20px;
    }

    table {
        width: 70%;
        margin: auto;
        border-collapse: collapse;
        margin-top: 20px;
    }

    th, td {
        border: 1px solid #ddd;
        padding: 12px;
        text-align: left;
    }

    th {
        background-color: #012970 !important;
        color: #fff !important;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2;
    }
</style>
</head>
<body style="background-color: #f4f4fc;">
    <%@ include file="header3.jsp" %>

    <div class="container outer-container" style="margin-top: 120px; margin-bottom: 100px;">
        <div class="row justify-content-center">
            <div class="col-md-14">
                <h2 style="color:#012970;"> Welcome, ${loggedInRecruiter.name}</h2>
                <h3 style="color:#012970;">Applications Overview</h3><br>

                <c:forEach var="mission" items="${missions}">
                    <div class="mission-container">
                        <h3>${mission.name}</h3>
                        <table class="table table-striped">
                            <thead >
                                <tr>
                                    <th class="text-center">First Name</th>
                                    <th class="text-center">Last Name</th>
                                    <th class="text-center">Phone</th>
                                    <th class="text-center">Email</th>
                                    <th class="text-center">Profile</th>
                                    <th class="text-center">Status</th>
                                    <th class="text-center">Decision</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="application" items="${applications}">
                                    <c:if test="${mission.id_mission == application.id_mission}">
                                        <c:forEach var="student" items="${students}">
                                            <c:if test="${student.id_student == application.id_student}">
                                                <tr>
    <td>${student.first_name}</td>
    <td>${student.last_name}</td>
    <td>${student.phone}</td>
    <td>${student.email}</td>
    <td>
       <form action="${contextPath}/gestionProfil" method="post" style="display: inline;">
           <input type="hidden" name="action" value="show">
           <input type="hidden" name="id_student" value="${student.id_student}">
           <button type="submit" class="profile-button btn-sm">Go to Profile</button>
      </form>

    <td>${application.status}</td>
    <td class="decision-buttons">
        
            <div class="">
               
            </div> 
            
           <form action="${contextPath}/gestionApplications" method="post" style="display: inline;">
           <input type="hidden" name="action" value="update">
                <input type="hidden" name="status" value="Accepted">
           <input type="hidden" name="id_application" value="${application.id_application}">
           <button type="submit" class="decision-button approve-button"> <i class="bi bi-check"></i></button>
           </form>
           
           
           <form action="${contextPath}/gestionApplications" method="post" style="display: inline;">
           <input type="hidden" name="action" value="update">
                <input type="hidden" name="status" value="Rejected">
           <input type="hidden" name="id_application" value="${application.id_application}">
           <button type="submit" class="decision-button reject-button"> <i class="bi bi-x"></i></button>
           </form>
            
           
            
            
       
    </td>
</tr>

                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>
