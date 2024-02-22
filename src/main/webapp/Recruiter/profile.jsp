<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<%@ include file="../Default/head.jsp" %>

<body style="background-color: #f4f4fc; text-align: center;">
    <%@ include file="header3.jsp" %>

    <div class="container" style="margin-top: 120px; margin-bottom: 100px;">
        <div class="row justify-content-center">
            <div class="col-md-12">
                <div class="card" style="background-color: #f9fafe; text-align: left; padding: 20px;">
                <div class="d-flex align-items-center">
                        <h2 class="card-title" style="color: #012970; margin-right: auto;">Recruiter Information</h2>

                        <form action="${contextPath}/GestionRecruiters?action=edit" method="post" style="margin-left: auto;">
                            <button type="submit" class="btn btn-outline-primary btn-sm" style="background-color: #012970; border-color: #012970;">
                                <i class="bi bi-pencil" style="color: white;"></i>
                            </button>
                        </form>
                    </div>
                   <br><br>
                   
                    

                    <table class="table table-striped">
                        <tbody>
                            <tr>
                                <th style="color: #012970; text-align: center;">Name</th>
                                <td style="text-align: center;">${loggedInRecruiter.name}</td>
                            </tr>
                            <tr>
                                <th style="color: #012970; text-align: center;">Email</th>
                                <td style="text-align: center;">${loggedInRecruiter.email}</td>
                            </tr>
                            <tr>
                                <th style="color: #012970; text-align: center;">Company</th>
                                <td style="text-align: center;">${loggedInRecruiter.company}</td>
                            </tr>
                            <tr>
                                <th style="color: #012970; text-align: center;">Description</th>
                                <td style="text-align: center;">${loggedInRecruiter.description}</td>
                            </tr>
                            <tr>
                                <th style="color: #012970; text-align: center;">Address</th>
                                <td style="text-align: center;">${loggedInRecruiter.address}</td>
                            </tr>
                            <tr>
                                <th style="color: #012970; text-align: center;">City</th>
                                <td style="text-align: center;">${loggedInRecruiter.city}</td>
                            </tr>
                            <tr>
                                <th style="color: #012970; text-align: center;">Country</th>
                                <td style="text-align: center;">${loggedInRecruiter.country}</td>
                            </tr>
                           
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
