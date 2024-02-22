<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.Date" %>
<%@ page import="Beans.project" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../Default/head.jsp" %>
    <title>Company Details</title>
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
            text-align: center;
        }
        
        h3{
        font-size: 25px;
            color: #012970;
            margin-bottom: 10px;
        
        }

        h4, h5 {
            font-size: 20px;
            color: #012970;
            margin-bottom: 10px;
        }

        h5 {
            font-size: 18px;
            margin-top: 10px;
            margin-left: 20px;
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
            transition: background-color 0.3s;
        }

        .btn-show-details:hover {
            background-color: #012970;
            color: #fff;
        }
    </style>
</head>
<body>
    <%@ include file="header2.jsp" %>
    
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    
                    <div class="card-body">
                        <h1 class="text-center">Company Details</h1>
                        <div class="divider"></div>
                        
                        <h4>Company's Name :</h4>
                        <div class="text-center"><b>${recruiter.company}</b></div><br>
                        
                        <h4>Company's Description:</h4>
                        <div class="text-center">${recruiter.description}</div>

                        <div class="divider"></div>
                        
                        <h3 class="text-center">Contact Information</h3>
                        
                        <h4>Recruiter's Name:</h4>
                        <div class="text-center"><b>${recruiter.name}</b></div> 

                        <h4>Recruiter's Email:</h4>
                        <div class="text-center"><b>${recruiter.email}</b></div> <br>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
