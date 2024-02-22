package DAO_application;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import Beans.application;
import Beans.mission;
import Beans.student;
import DAO_application.applicationDAO;
import DAO_application.ApplicationDAOImpl;
import DAO.DAOFactory;
import DAO_Mission.MissionDAO;
import DAO_Mission.MissionDaoImpl;
import DAO_Student.studentDaoImpl;

@WebServlet("/gestionApplications")
public class GestionApplications extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private applicationDAO applicationDAO;
    private studentDaoImpl studentDAO;
    private MissionDAO missionDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        applicationDAO = new ApplicationDAOImpl(daoFactory);
        this.studentDAO = new studentDaoImpl(daoFactory);
        this.missionDAO = new MissionDaoImpl(daoFactory);
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "all_st":
                	 student loggedInStudent = (student) request.getSession().getAttribute("loggedInStudent");
                     List<application> applicationsByStudent = applicationDAO.getAllApplicationsByStudent(loggedInStudent.getId_student());
                     HttpSession session = request.getSession();
                     session.setAttribute("applicationsByStudent", applicationsByStudent);
                     response.sendRedirect(request.getContextPath() + "/Student/StudentMissions.jsp");
                     
                    break;

                    
            }
        } else {
            response.sendRedirect("your_default_page.jsp"); // Change this to your default page
        }
    }



    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
            case "create":
                try {
                    int idStudent = Integer.parseInt(request.getParameter("id_student"));
                    int idMission = Integer.parseInt(request.getParameter("id_mission"));

                    List<application> existingApplications = applicationDAO.getAllApplicationsByStudent(idStudent);
                    boolean alreadyApplied = false;

                    for (application existingApplication : existingApplications) {
                        if (existingApplication.getId_mission() == idMission) {
                            alreadyApplied = true;
                            break;
                        }
                    }

                    if (alreadyApplied) {
                        response.sendRedirect(request.getContextPath() + "/Student/detail_mission.jsp?existence=true");
                    } else {
                        application newApplication = new application();
                        newApplication.setId_student(idStudent);
                        newApplication.setId_mission(idMission);
                        applicationDAO.create(newApplication);

                        List<application> applicationsByStudent = applicationDAO.getAllApplicationsByStudent(idStudent);
                        HttpSession session = request.getSession();
                        session.setAttribute("applicationsByStudent", applicationsByStudent);
                        response.sendRedirect(request.getContextPath() + "/Student/StudentMissions.jsp");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;


                case "update":
                	 try {
                         int idApplication = Integer.parseInt(request.getParameter("id_application"));
                         String status = request.getParameter("status");

                         applicationDAO.updateStatus(idApplication, status);
                         response.sendRedirect("GestionRecruiters");
                     } catch (Exception e) {
                         e.printStackTrace();
                         response.sendRedirect("your_error_page.jsp"); 
                     }
                    break;

                
            }
        } else {
            response.sendRedirect("your_default_page.jsp"); 
        }
    }
}
