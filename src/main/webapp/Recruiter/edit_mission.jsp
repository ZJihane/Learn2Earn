<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.Base64" %>
<%@ page import="Beans.mission" %>
<!DOCTYPE html>
<html lang="en">
<%@ include file="../Default/head.jsp" %>

<body style="background-image: url(../assets/img/hero-bg.png);">
    <%@ include file="header3.jsp" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <div class="container" style="margin-top: 150px; margin-bottom: 100px;">
        <div class="row justify-content-center">
            <!-- Nouvelle colonne pour le formulaire -->
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <form action="${contextPath}/GestionMissions?action=update" method="post">
                            <h1 class="text-center" style="color: #012970">Update Mission Information</h1> <br>
                            <input type="hidden" class="form-control" name="id_mission" id="id_mission" value="${mission.id_mission}">
                            
                            
                            <div class="form-group">
                                <label for="name" style="color: #012970; border-color: #012970; font-weight:bold;">Mission Name</label>
                                <input type="text" class="form-control" name="name" id="name" value="${mission.name}" required>
                            </div><br>
                            
                             <div class="form-group" style="color: #012970; border-color: #012970; font-weight:bold;">
                                <label for="description">Description</label>
                              <textarea class="form-control" name="description" id="description" style=" height: 150px;" required>${mission.description}</textarea>

                            </div><br>
                            
                            <div class="form-group">
                                <label for="remuneration" style="color: #012970; border-color: #012970; font-weight:bold;">Remuneration</label>
                                <input type="text" class="form-control" name="remuneration" id="remuneration" value="${mission.remuneration}" required>
                            </div><br>
                            
                            <div class="form-group">
                                <label for="open_date" style="color: #012970; border-color: #012970; font-weight:bold;">Open Date</label>
                                <input type="date" class="form-control" name="open_date" id="open_date" value="${mission.open_date}" required>
                            </div><br>
                            
                            <div class="form-group">
                                <label for="close_date" style="color: #012970; border-color: #012970; font-weight:bold;">Close Date</label>
                                <input type="date" class="form-control" name="close_date" id="close_date" value="${mission.close_date}" required>
                            </div><br>
                            
                             <div class="form-group">
                                <label for="skill" style="font-weight: bold; color: #012970">Skill:</label>
                                <select class="form-control" id="mission_skill" name="mission_skill">
                                       <option value="" disabled>Select a skill</option>
                                      <c:forEach var="skillOption" items="${skills}">
                                      <c:choose>
                                       <c:when test="${skillOption.name eq mission.mission_skill}">
                                       <option value="${skillOption.name}" selected>${skillOption.name}</option>
                                      </c:when>
                                      <c:otherwise>
                                     <option value="${skillOption.name}">${skillOption.name}</option>
                                     </c:otherwise>
                                      </c:choose>
                                   </c:forEach>
                                </select>
                        </div><br>
                            
                            <input type="hidden" class="form-control" name="id_recruteur" id="id_recruteur" value="${loggedInRecruiter.id_recruiter}" required>
                            
                            <div class="text-center">
                                <button type="submit" class="btn btn-primary" style="background-color: #012970; border-color: #012970;">Save</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
