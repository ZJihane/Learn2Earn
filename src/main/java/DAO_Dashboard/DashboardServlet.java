package DAO_Dashboard;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import DAO.DAOException;
import DAO.DAOFactory;
import DAO_Recruiter.recruiterDaoImpl;
import DAO_Student.studentDaoImpl;
import DAO_User.UserdaoImpl;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import Beans.mission;
import Beans.recruiter;
import Beans.skill;
import Beans.student;
import Beans.user;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DashboardDAOImpl dashboardDAO;
    private UserdaoImpl userDAO;
    private studentDaoImpl studentDAO;
    private recruiterDaoImpl recruiterDAO;
    
    public void init() throws ServletException {
     
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.dashboardDAO = new DashboardDAOImpl(daoFactory);
        this.recruiterDAO = new recruiterDaoImpl(daoFactory);
        this.studentDAO = new studentDaoImpl(daoFactory);
        this.userDAO = new UserdaoImpl(daoFactory);  
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 String action = request.getParameter("action");

         if (action != null) {
             switch (action) {
             case "dash":
            	 HttpSession session = request.getSession();
                 session.setAttribute("countStudents", dashboardDAO.countStudents());
                 session.setAttribute("countRecruiters", dashboardDAO.countRecruiters());
                 session.setAttribute("countMissions", dashboardDAO.countMissions());
                 session.setAttribute("countApplications", dashboardDAO.countApplications());
                 session.setAttribute("percentageStudents", dashboardDAO.calculatePercentageStudents());
                 session.setAttribute("percentageRecruiters", dashboardDAO.calculatePercentageRecruiters());
                 session.setAttribute("mostCommonSkills", dashboardDAO.getMostCommonSkillsInMissions());
                 response.sendRedirect(request.getContextPath() + "/Admin/Dashboard.jsp");
              break ;
              
             case "all_st":
            	 List<student> students = this.studentDAO.getAllUsers();
            	 HttpSession session1 = request.getSession();
            	 session1.setAttribute("students", students);
                 response.sendRedirect(request.getContextPath() + "/Admin/All_students.jsp");
             break;
            	 
             case "all_rec":
            	 List<recruiter> recruiters = this.recruiterDAO.getAllRecruiters();
            	 HttpSession session2 = request.getSession();
            	 session2.setAttribute("recruiters", recruiters);
            	 response.sendRedirect(request.getContextPath() + "/Admin/All_Recruiters.jsp");
             break;
             }
             
             
             }
       
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer tous les étudiants
        List<student> students = this.studentDAO.getAllUsers();
        request.setAttribute("students", students);
      

        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "delete_student":
                   
                    String studentIdToDelete = request.getParameter("id_student");
                    System.out.println("delete student number :"+ studentIdToDelete);
                    if (studentIdToDelete != null) {
                        int idStudent = Integer.parseInt(studentIdToDelete);
                        this.studentDAO.delete(idStudent);
                        response.sendRedirect("dashboard?action=all_st");
                    }
                    break;
                    
                    
                case "edit_student":
                	String studentIdToEdit = request.getParameter("id_student");
                	 int id_Student = Integer.parseInt(studentIdToEdit);
                	 student studentToEdit = this.studentDAO.find(id_Student);
                	 HttpSession session = request.getSession();
                     session.setAttribute("studentToEdit", studentToEdit);
                     response.sendRedirect(request.getContextPath() + "/Admin/edit_students.jsp");
                   break ;
                	 
                case "delete_rec":
                    
                    String recIdToDelete = request.getParameter("id_recruiter");
                    System.out.println("delete recruiter number :"+ recIdToDelete);
                    if (recIdToDelete != null) {
                        int idrec = Integer.parseInt(recIdToDelete);
                        this.studentDAO.delete(idrec);
                        response.sendRedirect("dashboard?action=all_rec");
                    }
                    break;
                    
                    
                case "edit_rec":
                	String recruiterIdToEdit = request.getParameter("id_recruiter");
                	 int id_Recruiter = Integer.parseInt(recruiterIdToEdit);
                	 recruiter recruiterToEdit = this.recruiterDAO.find(id_Recruiter);
                	 HttpSession session2 = request.getSession();
                     session2.setAttribute("recruiterToEdit", recruiterToEdit);
                     response.sendRedirect(request.getContextPath() + "/Admin/edit_recruiters.jsp");
                   break ;
                	 
                	
              




                
                case "update_student":
                    student updatedStudent = new student();
                    
                    String oldStudentString = request.getParameter("id_student");
                    
                    int idStudent = Integer.parseInt(oldStudentString);
                    student oldStudent = this.studentDAO.find(idStudent);

                    String updatedFirstName = request.getParameter("firstName");
                    String updatedLastName = request.getParameter("lastName");
                    String updatedEmail = request.getParameter("email");
                    String updatedPhone = request.getParameter("phone");
                    String updatedPassword = request.getParameter("password");
                    String oldEmail = request.getParameter("old_email");

                    // Update student information
                    updatedStudent.setId_student(idStudent);
                    updatedStudent.setFirst_name(updatedFirstName != null ? updatedFirstName : oldStudent.getFirst_name());
                    updatedStudent.setLast_name(updatedLastName != null ? updatedLastName : oldStudent.getLast_name());
                    updatedStudent.setEmail(updatedEmail != null ? updatedEmail : oldStudent.getEmail());
                    updatedStudent.setPhone(updatedPhone != null ? updatedPhone : oldStudent.getPhone());

                    // Set other fields from the old student
                    updatedStudent.setLinkedin(oldStudent.getLinkedin());
                    updatedStudent.setCv(oldStudent.getCv());
                    updatedStudent.setPicture(oldStudent.getPicture());
                    updatedStudent.setPictureBase64(oldStudent.getPictureBase64());
                    updatedStudent.setSkill(oldStudent.getSkill());

                    // Update student in the database
                    this.studentDAO.update(updatedStudent);

                    // Update corresponding user information if email is changed
                    if (!oldEmail.equals(updatedEmail)) {
                        user user_student = this.userDAO.findUserByEmail(oldEmail);
                        if (user_student != null) {
                            if (updatedPassword == null || updatedPassword.isEmpty()) {
                                updatedPassword = user_student.getPassword();
                            }
                            user_student.setEmail(updatedEmail);
                            user_student.setPassword(updatedPassword);
                            this.userDAO.update(user_student);
                        }
                    }

                    response.sendRedirect("dashboard?action=all_st");
                break;
                case "update_rec":
                    String updatedRecruiterId = request.getParameter("id_recruiter");
                    String updatedName = request.getParameter("name");
                    String updatedEmailr = request.getParameter("email");
                    String updatedCompany = request.getParameter("company");
                    String updatedDescription = request.getParameter("description");
                    String updatedAddress = request.getParameter("address");
                    String updatedCity = request.getParameter("city");
                    String updatedCountry = request.getParameter("country");

                    recruiter updatedRecruiter = new recruiter();
                    updatedRecruiter.setId_recruiter(Integer.parseInt(updatedRecruiterId));
                    updatedRecruiter.setName(updatedName);
                    updatedRecruiter.setEmail(updatedEmailr);
                    updatedRecruiter.setCompany(updatedCompany);
                    updatedRecruiter.setDescription(updatedDescription);
                    updatedRecruiter.setAddress(updatedAddress);
                    updatedRecruiter.setCity(updatedCity);
                    updatedRecruiter.setCountry(updatedCountry);

                    try {
                        this.recruiterDAO.update(updatedRecruiter);
                        response.sendRedirect("dashboard?action=all_rec");
                       
                    } catch (DAOException e) {
                        e.printStackTrace();
                        request.setAttribute("updateError", true);
                    }
                    
                   
                    break;


            }
        }

        
    }
}
