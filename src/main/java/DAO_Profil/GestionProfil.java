package DAO_Profil;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Beans.project;
import Beans.student;
import DAO.DAOException;
import DAO.DAOFactory;
import Beans.education;
import Beans.student;
import DAO_Education.EducationDAO;
import DAO_Education.EducationDaoImpl;
import DAO_Student.studentDAO;
import DAO_Student.studentDaoImpl;
import DAO_Projet.ProjectDAO;
import DAO_Projet.ProjectDaoImpl;




@WebServlet("/gestionProfil")
public class GestionProfil extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProjectDAO projectDAO;
    private EducationDAO educationDAO;
    private studentDAO studentDAO ;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        projectDAO = new ProjectDaoImpl(daoFactory);
        educationDAO = new EducationDaoImpl(daoFactory);
        studentDAO = new studentDaoImpl(daoFactory);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Assuming loggedInStudent is a session attribute
            student loggedInStudent = (student) request.getSession().getAttribute("loggedInStudent");

            // Check if the user is authenticated and loggedInStudent is not null
            if (loggedInStudent != null) {
                List<project> projects = projectDAO.getAllprojects(loggedInStudent.getId_student());
                List<education> educations = educationDAO.getAlleducations(loggedInStudent.getId_student());
                HttpSession session = request.getSession();
                session.setAttribute("projects", projects);
                session.setAttribute("educations", educations);
                response.sendRedirect(request.getContextPath() +"/Student/profil.jsp");
               
            } else {
                // Redirect to a login page or handle authentication
                response.sendRedirect(request.getContextPath() +"/Default/Login.jsp");
            }
        } catch (DAOException e) {
            throw new ServletException("Error retrieving projects and educations", e);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("show".equals(action)) {
        	String id_std_string = request.getParameter("id_student");
        	int id_student = Integer.parseInt( id_std_string ) ;
            student show_student = studentDAO.find(id_student) ;
            List<project> projects = projectDAO.getAllprojects(show_student.getId_student());
            List<education> educations = educationDAO.getAlleducations(show_student.getId_student());
            HttpSession session = request.getSession();
            session.setAttribute("student",show_student) ;
            session.setAttribute("projects", projects);
            session.setAttribute("educations", educations);
            response.sendRedirect(request.getContextPath() +"/Recruiter/show_profile.jsp");
           
        } else {
            // Handle other actions if needed
            response.sendRedirect(request.getContextPath() + "/Default/Login.jsp");
        }
    }



    
}
