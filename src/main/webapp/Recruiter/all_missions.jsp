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
</style>
<body style="background-color: #f4f4fc;">
<%@ include file="header3.jsp" %>

<div class="modal fade" id="testModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Add New Mission</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="${contextPath}/GestionMissions?action=create" method="post">
                    <div class="form-group">
                        <label for="name" style="color: #012970; border-color: #012970; font-weight: bold;">Mission Title</label>
                        <input type="text" class="form-control" name="name" id="name" placeholder="Enter Mission Name" required>
                    </div><br>
                    <div class="form-group">
                        <label for="description" style="color: #012970; border-color: #012970; font-weight: bold;">Description</label>
                        <textarea class="form-control" name="description" id="description" placeholder="Enter Mission Description" required></textarea>
                    </div><br>
                    <div class="form-group">
                        <label for="remuneration" style="color: #012970; border-color: #012970; font-weight:bold;">Remuneration</label>
                        <input type="text" class="form-control" name="remuneration" id="remuneration" placeholder="Enter Remuneration" required>
                    </div><br>
                    <div class="form-group">
                        <label for="open_date" style="color: #012970; border-color: #012970; font-weight:bold;">Open Date</label>
                        <input type="date" class="form-control" name="open_date" id="open_date" required>
                    </div><br>
                    <div class="form-group">
                        <label for="close_date" style="color: #012970; border-color: #012970; font-weight:bold;">Close Date</label>
                        <input type="date" class="form-control" name="close_date" id="close_date" required>
                    </div><br>
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
                    <input type="hidden" class="form-control" name="id_recruteur" id="id_recruteur" value="${loggedInRecruiter.id_recruiter}" required>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary" style="background-color: #012970; border-color: #012970;">Save</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="container outer-container" style="margin-top: 120px; margin-bottom: 100px;">
    <div class="row justify-content-center">
        <div class="col-md-14">
            <h2 style="color: #012970;">Greetings, ${loggedInRecruiter.name}!</h2>
            <h3 style="color: #012970;">Explore and manage your missions, and feel free to add a new mission</h3><br>

            <button type="button" class="btn btn-primary btn-lg ml-auto" style="background-color: #012970;" data-bs-toggle="modal" data-bs-target="#testModal">
                Add a new mission <i class="bi bi-plus" style="color: white;"></i>
            </button>

            <br><br>

            <c:forEach var="mission" items="${missions}">
                <div class="mission-container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="d-flex justify-content-between align-items-center">
                                <h4 style="color:#012970;">${mission.name}</h4>
                                <div class="button-group">
                                    <!-- Edit Form -->
                                    <form action="${contextPath}/GestionMissions?action=edit" method="post" style="display: inline;">
                                        <input type="hidden" name="id_mission" value="${mission.id_mission}">
                                        <button type="submit" class="btn btn-outline-primary btn-sm" style="background-color: #012970; border-color: #012970;">
                                            <i class="bi bi-pencil" style="color: white;"></i>
                                        </button>
                                    </form>

                                    <!-- Delete Form -->
                                    <form action="${contextPath}/GestionMissions?action=delete" method="post" style="display: inline;"
                                          onsubmit="return confirm('Are you sure you want to delete this mission entry?');">
                                        <input type="hidden" name="id_mission" value="${mission.id_mission}">
                                        <button type="submit" class="btn btn-outline-danger btn-sm" style="background-color: #012970; border-color: #012970;">
                                            <i class="bi bi-trash" style="color: white;"></i>
                                        </button>
                                    </form>
                                </div>
                            </div>
                            <p class="small mission-details">
                                <span class="text-muted">opening date : ${mission.open_date} -- closing date : ${mission.close_date}</span>
                            </p>
                            <p class="mission-details">${mission.description}</p>
                            <p class="mission-details">Remuneration: ${mission.remuneration}</p>
                            <p class="mission-details">Mission Skill: ${mission.mission_skill}</p>
                            <div style="margin-left: 900px">
                                <!-- Additional details or actions if needed -->
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.4.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
