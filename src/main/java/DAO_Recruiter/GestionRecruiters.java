package DAO_Recruiter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Beans.application;
import Beans.mission;
import Beans.recruiter;
import Beans.user;
import Beans.student;
import DAO.DAOException;
import DAO.DAOFactory;
import DAO_Mission.MissionDaoImpl;
import DAO_User.UserdaoImpl;
import DAO_application.ApplicationDAOImpl;
import DAO_application.applicationDAO;
import DAO_Student.studentDaoImpl ;

@WebServlet("/GestionRecruiters")
public class GestionRecruiters extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private recruiterDaoImpl recruiterDAO;
    private UserdaoImpl userDAO;
    private MissionDaoImpl missionDAO ;
    private ApplicationDAOImpl applicationDAO;
    private studentDaoImpl studentDAO ;
 

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.recruiterDAO = new recruiterDaoImpl(daoFactory);
        this.userDAO = new UserdaoImpl(daoFactory); 
        this.missionDAO = new MissionDaoImpl(daoFactory);
        this.applicationDAO = new ApplicationDAOImpl(daoFactory);
        this.studentDAO = new studentDaoImpl(daoFactory);
        
    }
    

    public GestionRecruiters() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session2 = request.getSession();
        recruiter recruiter = (recruiter) session2.getAttribute("loggedInRecruiter");
        List<mission> missions = this.missionDAO.getAllMissionsForRecruiter(recruiter.getId_recruiter());
        List<application> applications = this.applicationDAO.getAllApplicationsByRecruiter(recruiter.getId_recruiter());
        Set<Integer> studentIds = new HashSet<>();
        for (application app : applications) {
            studentIds.add(app.getId_student());
        }
        List<student> students = new ArrayList<>();
        for (Integer studentId : studentIds) {
            student student = this.studentDAO.find(studentId);
            System.out.println("Student ID: " + studentId + ", Student: " + student);
            students.add(student);
            
        }

        session2.setAttribute("missions", missions);
        session2.setAttribute("applications", applications);
        session2.setAttribute("students", students);  // Set the list of students in the session
        response.sendRedirect(request.getContextPath() + "/Recruiter/recruiterHome.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer tous les recruteurs
        List<recruiter> recruiters = this.recruiterDAO.getAllRecruiters();
        request.setAttribute("recruiters", recruiters);
        HttpSession session = request.getSession();
        List<mission> missions ;
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "delete":
                    // Supprimer un recruteur
                    String recruiterIdToDelete = request.getParameter("id_recruiter");
                    System.out.println("delete recruiter number :" + recruiterIdToDelete);
                    if (recruiterIdToDelete != null) {
                        int idRecruiter = Integer.parseInt(recruiterIdToDelete);
                        this.recruiterDAO.delete(idRecruiter);
                        recruiters = this.recruiterDAO.getAllRecruiters();
                        request.setAttribute("recruiters", recruiters);
                        RequestDispatcher dispatcher1 = request.getRequestDispatcher("/recruiter.jsp");
                        dispatcher1.forward(request, response);
                    }
                    break;
                    
               


                case "edit":
                	response.sendRedirect(request.getContextPath() + "/Recruiter/edit_recruiter.jsp");
                    break;

                case "add":
                    // Afficher la page d'ajout d'un nouveau recruteur
                    RequestDispatcher addDispatcher = request.getRequestDispatcher("/formRecruiter.jsp");
                    addDispatcher.forward(request, response);
                    break;

                case "create":
                    String name = request.getParameter("name");
                    String email = request.getParameter("email");
                    String company = request.getParameter("company");
                    String description = request.getParameter("description");
                    String address = request.getParameter("address");
                    String city = request.getParameter("city");
                    String country = request.getParameter("country");
                    String password = request.getParameter("password");

                    
                    recruiter newRecruiter = new recruiter();
                    user newuser = new user ();
                    
                    newRecruiter.setName(name);
                    newRecruiter.setEmail(email);
                    newRecruiter.setCompany(company);
                    newRecruiter.setDescription(description);
                    newRecruiter.setAddress(address);
                    newRecruiter.setCity(city);
                    newRecruiter.setCountry(country);
                    recruiter createdRecruiter = this.recruiterDAO.create(newRecruiter);
                    newuser.setEmail(email);
                    newuser.setRole(1);
                    newuser.setId_role(createdRecruiter.getId_recruiter());
                    newuser.setPassword(password);
                    this.userDAO.create(newuser);
                    request.setAttribute("createSuccess", true);
                    HttpSession session1 = request.getSession();
                    session1.setAttribute("loggedInRecruiter", createdRecruiter);
                    
                    missions = this.missionDAO.getAllMissionsForRecruiter(createdRecruiter.getId_recruiter());
                    session1.setAttribute("missions", missions);
                    response.sendRedirect(request.getContextPath() + "/Recruiter/recruiterHome.jsp");
              
                   
                    break;

                case "update":
                    String updatedRecruiterId = request.getParameter("id_recruiter");
                    String updatedName = request.getParameter("name");
                    String updatedEmail = request.getParameter("email");
                    String updatedCompany = request.getParameter("company");
                    String updatedDescription = request.getParameter("description");
                    String updatedAddress = request.getParameter("address");
                    String updatedCity = request.getParameter("city");
                    String updatedCountry = request.getParameter("country");

                    recruiter updatedRecruiter = new recruiter();
                    updatedRecruiter.setId_recruiter(Integer.parseInt(updatedRecruiterId));
                    updatedRecruiter.setName(updatedName);
                    updatedRecruiter.setEmail(updatedEmail);
                    updatedRecruiter.setCompany(updatedCompany);
                    updatedRecruiter.setDescription(updatedDescription);
                    updatedRecruiter.setAddress(updatedAddress);
                    updatedRecruiter.setCity(updatedCity);
                    updatedRecruiter.setCountry(updatedCountry);

                    try {
                        this.recruiterDAO.update(updatedRecruiter);
                        HttpSession session2 = request.getSession();
                        session2.setAttribute("loggedInRecruiter", updatedRecruiter);
                        response.sendRedirect(request.getContextPath() + "/Recruiter/profile.jsp");
                    } catch (DAOException e) {
                        e.printStackTrace();
                        request.setAttribute("updateError", true);
                    }
                    
                   
                    break;

                case "cancel":
                    // Handle cancel action if needed
                    List<recruiter> recruiters3 = this.recruiterDAO.getAllRecruiters();
                    request.setAttribute("recruiters", recruiters3);
                    request.getRequestDispatcher("/recruiter.jsp").forward(request, response);
                    break;
            }
        }
    }
}

	