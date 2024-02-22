 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <header id="header" class="header fixed-top">
        <div class="container-fluid container-xl d-flex align-items-center justify-content-between">
            <a href="${contextPath}/Student/studentHome.jsp" class="logo d-flex align-items-center" style="text-decoration: none;">
                <span>Learn2Earn</span>
            </a>
            <nav id="navbar" class="navbar">
                <ul>
                    <li><a class="nav-link  " href="${contextPath}/Student/studentHome.jsp">Home</a></li>
                    <li><a class="nav-link " href="${contextPath}/gestionApplications?action=all_st">My Applications</a></li>
                    <li><a class="nav-link " href="${contextPath}/gestionProfil">Profil</a></li>
                    <li><a class="getstarted " href="${contextPath}/LogoutServlet" style="text-decoration: none;">Log out<i class="bi bi-box-arrow-in-right"></i> <!-- Icône de connexion (Sign In) -->
                    </a></li>

                </ul>
                <i class="bi bi-list mobile-nav-toggle"></i>
            </nav>
        </div>
    </header>