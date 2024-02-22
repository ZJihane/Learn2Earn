package DAO_Login;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import Beans.student;
import Beans.user ;
import Beans.application;
import Beans.mission ;
import Beans.recruiter;
import DAO.DAOException;
import DAO.DAOFactory;
import DAO_Dashboard.DashboardDAOImpl;
import DAO_Mission.MissionDAO;
import DAO_Mission.MissionDaoImpl;
import DAO_Student.studentDAO;
import DAO_Student.studentDaoImpl;
import DAO_Recruiter.recruiterDAO ;
import DAO_Recruiter.recruiterDaoImpl; 
import DAO_Mission.MissionDaoImpl;
import DAO_Mission.MissionDAO ;
import DAO_User.UserDAO;
import DAO_User.UserdaoImpl;
import DAO_application.ApplicationDAOImpl;
import DAO_application.applicationDAO;



@WebServlet("/LoginServlet")
public class loginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DashboardDAOImpl dashboardDAO;
    private UserDAO userDAO; 
    private studentDAO studentDAO ;
    private recruiterDAO recruiterDAO ;
    private MissionDAO missionDAO ;
    private applicationDAO applicationDAO;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.dashboardDAO = new DashboardDAOImpl(daoFactory);
        this.userDAO = new UserdaoImpl(daoFactory);
        this.studentDAO=new studentDaoImpl(daoFactory);
        this.recruiterDAO = new recruiterDaoImpl(daoFactory);
        this.missionDAO = new MissionDaoImpl(daoFactory);
         applicationDAO = new ApplicationDAOImpl(daoFactory);
       
           }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            List<user> users = userDAO.getAllUsers();

            for (user user : users) {
                if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                    int role = user.getRole();
                    int id_role = user.getId_role();
                    HttpSession session1 = request.getSession();
                    session1.setAttribute("loggedInUser", user);
                    redirectToHomePage(role, response, id_role ,request);
                    return;
                }
            }

            response.sendRedirect(request.getContextPath() +"/Default/Login.jsp?error=true");
        } catch (DAOException e) {
        	
        	 response.sendRedirect(request.getContextPath() +"/Default/Login.jsp?error=true");
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("Default/Login.jsp");
        dispatcher.forward(request, response);
    }
    
    private void redirectToHomePage(int role, HttpServletResponse response, int id_role ,HttpServletRequest request ) throws IOException {
        switch (role) {
        case 0:
        	
            
       	 HttpSession session0 = request.getSession();
         session0.setAttribute("countStudents", dashboardDAO.countStudents());
         session0.setAttribute("countRecruiters", dashboardDAO.countRecruiters());
         session0.setAttribute("countMissions", dashboardDAO.countMissions());
         session0.setAttribute("countApplications", dashboardDAO.countApplications());
         session0.setAttribute("percentageStudents", dashboardDAO.calculatePercentageStudents());
         session0.setAttribute("percentageRecruiters", dashboardDAO.calculatePercentageRecruiters());
         session0.setAttribute("mostCommonSkills", dashboardDAO.getMostCommonSkillsInMissions());
             response.sendRedirect(request.getContextPath() + "/Admin/Dashboard.jsp");
            
            break;
   
            case 1:
                List<recruiter> recruiters = recruiterDAO.getAllRecruiters() ;
                for (recruiter recruiter : recruiters) {
                    if (recruiter.getId_recruiter() == id_role) {
                        HttpSession session = request.getSession();
                        session.setAttribute("loggedInRecruiter", recruiter);
                        List<mission> missions = this.missionDAO.getAllMissionsForRecruiter(recruiter.getId_recruiter());
                        List<application> applications = this.applicationDAO.getAllApplicationsByRecruiter(recruiter.getId_recruiter()) ;
                        
                        Set<Integer> studentIds = new HashSet<>();
                        for (application app : applications) {
                            studentIds.add(app.getId_student());
                        }

                        
                        List<student> students = new ArrayList<>();
                        for (Integer studentId : studentIds) {
                            student student = this.studentDAO.find(studentId);
                            students.add(student);
                            System.out.println("Student ID: " + studentId + ", Student: " + student);
                        }



                        session.setAttribute("missions", missions);
                        session.setAttribute("applications", applications);
                        session.setAttribute("students", students);  // Set the list of students in the session
                        response.sendRedirect(request.getContextPath() +"/Recruiter/recruiterHome.jsp");
                        return;
                    }
                }
                break;
            case 2:
                List<student> students = studentDAO.getAllUsers();
                
                for (student student : students) {
                    if (student.getId_student() == id_role) {
                        HttpSession session = request.getSession();
                        List<mission> missions = this.missionDAO.getAllMissionsForStudent(student.getSkill());
                        session.setAttribute("loggedInStudent", student);
                        session.setAttribute("missions", missions);
                        response.sendRedirect(request.getContextPath() + "/Student/studentHome.jsp");
                   
                        return;
                    }
                }
                break;
            default:
               
                response.sendRedirect(request.getContextPath() +"/Default/Login.jsp");
        }
    }


}
