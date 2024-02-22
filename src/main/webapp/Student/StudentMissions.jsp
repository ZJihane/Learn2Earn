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
        color: #fff; /* Keep the same color on hover */
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
        background-color: #012970;
        color: #fff;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    .status-accepted {
        color: green;
    }

    .status-rejected {
        color: red;
    }

    .status-under-processing {
        color: #012970;
    }
</style>
<body style="background-color: #f4f4fc;">
    <%@ include file="header2.jsp" %>

    <div class="container outer-container" style="margin-top: 120px; margin-bottom: 100px;">
        <div class="row justify-content-center">
            <div class="col-md-14">
                <h2 style="color:#012970;"> Welcome, ${loggedInStudent.first_name}</h2>
                <h3 style="color:#012970;">Here are your applications , wishing you best of luck !</h3><br>

                <c:if test="${not empty applicationsByStudent}">
                    <table>
                        <thead>
                            <tr>
                                <th class="text-center">Mission Name</th>
                                <th class="text-center">Mission Details</th>
                                <th class="text-center">Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="application" items="${applicationsByStudent}">
                                <tr>
                                    <c:forEach var="mission" items="${missions}">
                                        <c:if test="${mission.id_mission eq application.id_mission}">
                                            <td>${mission.name}</td>
                                        </c:if>
                                    </c:forEach>

                                    <td>
                                        <div class="text-center">
                                            
                                        <form action="${contextPath}/GestionMissions?action=show_st" method="post" style="display: inline;">
                                    <input type="hidden" name="id_mission" value="${application.id_mission}">
                                        <button type="submit" class="btn btn-show-details">Show Details</button>
                                     </form>
                                        </div>
                                        
                                    </td>

                                    <td class="<c:choose>
                                        <c:when test="${application.status eq 'Accepted'}">status-accepted</c:when>
                                        <c:when test="${application.status eq 'Rejected'}">status-rejected</c:when>
                                        <c:when test="${application.status eq 'Under Processing'}">status-under-processing</c:when>
                                    </c:choose>">
                                        <div class="text-center">${application.status}</div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>