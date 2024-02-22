<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ include file="head.jsp" %>

<body style="background-image: url(../assets/img/hero-bg.png);">
    <%@ include file="header.jsp" %>

    <div class="container" style="margin-top: 200px; margin-bottom: 200px;">
        <div class="row justify-content-center">
            <!-- New column for the form -->
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                       
                        <form action="${contextPath}/LoginServlet" method="post">
                         

                            <div class="form-group">
                                <h1 class="text-center" style="color: #012970">Sign in </h1>
                                <c:if test="${param.error == 'true'}">
                            <p class="mt-3 text-center" style="color: red;">Invalid email or password. Please try
                                again.</p>
                        </c:if>
                                <label for="email" style="font-weight: bold;color: #012970">Adresse e-mail:</label>
                                <input type="text" class="form-control" id="email" name="email"
                                    placeholder="Adresse e-mail" required>
                            </div>
                            <br>
                            <div class="form-group">
                                <label for="password" style="font-weight: bold;color: #012970">Mot de passe:</label>
                                <input type="password" class="form-control" id="password" name="password"
                                    placeholder="Mot de passe" required>
                            </div>
                            <br>
                            <div class="text-center">
                                <button type="submit" class="btn btn-primary ">Login</button>
                            </div>

                        </form>

                        <!-- Display red error message inside the card body when error is true -->
                        

                        <p class="mt-3 text-center">Don't have an account? <a
                                href="${contextPath}/GestionStudents">Sign up here</a>.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="footer.jsp" %>
    <%@ include file="links.jsp" %>

</body>

</html>
