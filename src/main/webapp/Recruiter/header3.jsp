 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <header id="header" class="header fixed-top">
        <div class="container-fluid container-xl d-flex align-items-center justify-content-between">
            <a href="${contextPath}/Recruiter/recruiterHome.jsp" class="logo d-flex align-items-center" style="text-decoration: none;">
                <span>Learn2Earn</span>
            </a>
            <nav id="navbar" class="navbar">
                <ul>
                    <li><a class="nav-link  " href="${contextPath}/GestionRecruiters">Home</a></li>
                    <li><a class="nav-link " href="${contextPath}/GestionMissions?action=home">Missions</a></li>
                    <li><a class="nav-link " href="${contextPath}/GestionMissions?action=profile">Profil</a></li>
                    <li><a class="getstarted " href="${contextPath}/LogoutServlet" style="text-decoration: none;">Log out<i class="bi bi-box-arrow-in-right"></i> <!-- Icône de connexion (Sign In) -->
                    </a></li>

                </ul>
                <i class="bi bi-list mobile-nav-toggle"></i>
            </nav>
        </div>
    </header>