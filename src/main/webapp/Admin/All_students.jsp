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
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>LinkedIn</th>
                    <th>CV</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td>${student.id_student}</td>
                        <td>${student.first_name}</td>
                        <td>${student.last_name}</td>
                        <td>${student.email}</td>
                        <td>${student.phone}</td>
                        <td>${student.linkedin}</td>
                        <td>
                            <c:if test="${not empty student.cv}">

                                            <a href="${contextPath}/DownloadServlet" style="text-decoration: none;">
                                                Download cv
                                                <i class="bi bi-file-pdf"></i>
                                            </a>
                                     </p>
                                  </c:if>
                        </td>
                        <td>
                        <div class="button-group d-flex justify-content-between align-items-center">
                        <form action="${contextPath}/dashboard?action=edit_student" method="post" style="display: inline;">
                        <input type="hidden" name="id_student" value="${student.id_student}">
                        <button type="submit" class="btn btn-outline-primary btn-sm" style="background-color: #012970; border-color: #012970;">
                         <i class="bi bi-pencil" style="color: white;"></i>
                         </button>
                        </form>

    &nbsp;&nbsp;
   <form action="${contextPath}/dashboard?action=delete_student" method="post" style="display: inline;"
    onsubmit="return confirm('Are you sure you want to delete this student?');">
    <input type="hidden" name="id_student" value="${student.id_student}">
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
