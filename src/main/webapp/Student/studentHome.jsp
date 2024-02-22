<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>


<!DOCTYPE html>
<html lang="en">
<%@ include file="../Default/head.jsp" %>
<style>
    .outer-container {
        background-color: #f9fafe;
        padding: 20px; /* Add padding for the baby blue rectangle */
    }

    .mission-container {
        border: 1px solid #ccc;
        border-radius: 10px;
        padding: 20px; /* Adjusted padding for better visual */
        background-color: white;
        margin-bottom: 20px;
    }

    .mission-details {
        font-size: 14px;
        color: #555;
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
<body style="background-color: #f4f4fc;">
    <%@ include file="header2.jsp" %>

    <div class="container outer-container" style="margin-top: 120px; margin-bottom: 100px;">
        <div class="row justify-content-center">
            <div class="col-md-14">
                <h2 style="color:#012970;"> Welcome, ${loggedInStudent.first_name}</h2>
                <h3 style="color:#012970;">Here are some missions that might interest you.</h3><br>

                <c:forEach var="mission" items="${missions}">
                    <div class="mission-container">
                        <div class="row">
                            <div class="col-md-12">
                                <h4 style="color:#012970;">${mission.name}</h4>
                                <p class="small mission-details">
                                    <span class="text-muted">openning date : ${mission.open_date} -- closing date : ${mission.close_date}</span>
                                </p>
                                <p class="mission-details">${mission.description}</p>
                                <div   style="margin-left: 900px">
                                
                                
                                
                                <form action="${contextPath}/GestionMissions?action=show_st" method="post" style="display: inline;">
                                    <input type="hidden" name="id_mission" value="${mission.id_mission}">
                                        <button type="submit" class="btn btn-show-details">Show Details</button>
                                     </form>
                                
                            </div>
                            </div>
                            
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>