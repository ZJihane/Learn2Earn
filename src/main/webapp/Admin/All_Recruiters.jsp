<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../Default/head.jsp" %>
    <style>
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
        .button-group {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .button-group form {
            display: inline;
        }
        .button-group button {
            background-color: #012970;
            border-color: #012970;
            color: white;
            border-radius: 5px;
            padding: 5px 10px;
            margin-left: 5px;
        }
        .button-group button:hover {
            background-color: #0a3e69;
            border-color: #0a3e69;
        }
    </style>
</head>
<body style="background-color: #f4f4fc;">
<%@ include file="header4.jsp" %>

<div class="container outer-container" style="margin-top: 120px; margin-bottom: 100px;">
    <div class="row justify-content-center">
        <div class="col-md-12">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Company</th>
                    <th>Description</th>
                    <th>Address</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="recruiter" items="${recruiters}">
                    <tr>
                        <td>${recruiter.id_recruiter}</td>
                        <td>${recruiter.name}</td>
                        <td>${recruiter.email}</td>
                        <td>${recruiter.company}</td>
                        <td>${recruiter.description}</td>
                        <td>${recruiter.address}, ${recruiter.city}, ${recruiter.country}</td>
                         <td>
                        <div class="button-group d-flex justify-content-between align-items-center">
                        <form action="${contextPath}/dashboard?action=edit_rec" method="post" style="display: inline;">
                        <input type="hidden" name="id_recruiter" value="${recruiter.id_recruiter}">
                        <button type="submit" class="btn btn-outline-primary btn-sm" style="background-color: #012970; border-color: #012970;">
                         <i class="bi bi-pencil" style="color: white;"></i>
                         </button>
                        </form>

    &nbsp;&nbsp;
   <form action="${contextPath}/dashboard?action=delete_student" method="post" style="display: inline;"
    onsubmit="return confirm('Are you sure you want to delete this recruiter?');">
    <input type="hidden" name="id_recruiter" value="${recruiter.id_recruiter}">
    <button type="submit" class="btn btn-outline-danger btn-sm" style="background-color: #012970; border-color: #012970;">
        <i class="bi bi-trash" style="color: white;"></i>
    </button>
</form>
   </div>                     
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
