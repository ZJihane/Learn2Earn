package DAO_Student;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import Beans.student;
import Beans.user;
import Beans.mission;
import Beans.recruiter;
import Beans.skill;
import DAO.DAOException;
import DAO.DAOFactory;
import DAO_Mission.MissionDaoImpl;
import DAO_Skill.SkillDaoImpl;
import DAO_User.UserdaoImpl;

@WebServlet("/GestionStudents")
@MultipartConfig
public class GestionStudents extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    
    private studentDaoImpl studentDAO;
    private UserdaoImpl userDAO;
    private SkillDaoImpl skillDAO ;
    private MissionDaoImpl missionDAO ;
    
    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.studentDAO = new studentDaoImpl(daoFactory);
        this.userDAO = new UserdaoImpl(daoFactory);    
        this.skillDAO = new SkillDaoImpl(daoFactory);
        this.missionDAO = new MissionDaoImpl(daoFactory);
    }

    
    public GestionStudents() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    		 List<skill> skills = this.skillDAO.getAllSkills();
     	    request.getSession().setAttribute("skills", skills);
     	    response.sendRedirect(request.getContextPath() + "/Default/signupStudent.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer tous les étudiants
        List<student> students = this.studentDAO.getAllUsers();
        request.setAttribute("students", students);
      

        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "delete":
                    // Supprimer un étudiant
                	
                    String studentIdToDelete = request.getParameter("id_student");
                    System.out.println("delete student number :"+ studentIdToDelete);
                    if (studentIdToDelete != null) {
                        int idStudent = Integer.parseInt(studentIdToDelete);
                        this.studentDAO.delete(idStudent);
                        students = this.studentDAO.getAllUsers();
                        request.setAttribute("students", students);
                        RequestDispatcher dispatcher1 = request.getRequestDispatcher("/student.jsp");
                        dispatcher1.forward(request, response);
                    }
                    break;
                    
                    
                    
                
                case "edit":
                	
                	List<skill> skills = this.skillDAO.getAllSkills();
            	    request.getSession().setAttribute("skills", skills);
                    String studentIdToEdit = request.getParameter("id_student");

                    if (studentIdToEdit != null) {
                        int idStudent = Integer.parseInt(studentIdToEdit);
                        student studentToEdit = this.studentDAO.find(idStudent);

                        byte[] pictureData = studentToEdit.getPicture();
                        if (pictureData != null) {
                            String pictureBase64 = Base64.getEncoder().encodeToString(pictureData);
                            studentToEdit.setPictureBase64(pictureBase64);
                        } else {
                        	studentToEdit.setPictureBase64(""); 
                        }

                        student loggedInStudent = (student) request.getSession().getAttribute("loggedInStudent");
                        if (loggedInStudent != null) {
                            HttpSession session = request.getSession();
                            session.setAttribute("studentToEdit", studentToEdit);
                           response.sendRedirect(request.getContextPath() +"/Student/edit_student.jsp");
                       }else {
                           // Handle the case where the education is not found
                          
                       }
                       

                        
                    }
                    break;
                    
                
                    
                   

                case "add":
                    // Afficher la page d'ajout d'un nouvel étudiant
                    RequestDispatcher addDispatcher = request.getRequestDispatcher("/formStudent.jsp");
                    addDispatcher.forward(request, response);
                    break;
                    
                    
                    
                    
                case "create":
                    String firstName = request.getParameter("firstName");
                    String lastName = request.getParameter("lastName");
                    String email = request.getParameter("email");
                    String phone = request.getParameter("phone");
                    String skill = request.getParameter("skill");
                    String linkedin = request.getParameter("linkedin");
                    Part cvPart = request.getPart("cv");
                    Part picturePart = request.getPart("picture");
                    String password = request.getParameter("password");
                    

                    // Create an object student with the data from the form
                    student newStudent = new student();
                    user newUser = new user();  // Assuming you have a User class

                    newStudent.setFirst_name(firstName);
                    newStudent.setLast_name(lastName);
                    newStudent.setEmail(email);
                    newStudent.setPhone(phone);
                    newStudent.setLinkedin(linkedin);
                    newStudent.setSkill(skill);

                    // Process CV file
                    if (cvPart != null && cvPart.getSize() > 0) {
                        byte[] cvBytes = new byte[(int) cvPart.getSize()];
                        cvPart.getInputStream().read(cvBytes);
                        newStudent.setCv(cvBytes);
                    }

                    // Process picture file
                    if (picturePart != null && picturePart.getSize() > 0) {
                        byte[] pictureBytes = new byte[(int) picturePart.getSize()];
                        picturePart.getInputStream().read(pictureBytes);
                        newStudent.setPicture(pictureBytes);
                    }

                    student createdStudent = this.studentDAO.create(newStudent);

                    newUser.setEmail(email);
                    newUser.setRole(2);
                    newUser.setPassword(password);
                    newUser.setId_role(createdStudent.getId_student());  // Assuming there's a method getIdStudent()

                    this.userDAO.create(newUser);
                    request.setAttribute("createSuccess", true);

                    HttpSession session1 = request.getSession();
                    session1.setAttribute("loggedInStudent", createdStudent);
                    
                    List<mission> missions = this.missionDAO.getAllMissionsForStudent(createdStudent.getSkill());
                    
                    session1.setAttribute("missions", missions);

                    response.sendRedirect(request.getContextPath() + "/Student/studentHome.jsp");
                    break;




                
                case "update":
                    String updatedFirstName = request.getParameter("firstName");
                    String updatedLastName = request.getParameter("lastName");
                    String updatedEmail = request.getParameter("email");
                    String updatedPhone = request.getParameter("phone");
                    String updatedSkill = request.getParameter("skill");
                    String updatedLinkedin = request.getParameter("linkedin");
                    Part newCvPart = request.getPart("newCv");
                    Part newPicturePart = request.getPart("newPicture");
                    String updatedPassword = request.getParameter("password");
                    
                   

                    // Retrieve the current student information from the session
                    student loggedInStudent = (student) request.getSession().getAttribute("loggedInStudent");
                    String oldEmail = loggedInStudent.getEmail();
                    

                    // Update student information
                    loggedInStudent.setFirst_name(updatedFirstName);
                    loggedInStudent.setLast_name(updatedLastName);
                    loggedInStudent.setEmail(updatedEmail);
                    loggedInStudent.setPhone(updatedPhone);
                    loggedInStudent.setLinkedin(updatedLinkedin);
                    loggedInStudent.setSkill(updatedSkill);
                   

                    byte[] updatedCvBytes = loggedInStudent.getCv();
                    byte[] updatedPictureBytes = loggedInStudent.getPicture();

                    // Processer le nouveau CV s'il est présent
                    if (newCvPart != null && newCvPart.getSize() > 0) {
                        updatedCvBytes = new byte[(int) newCvPart.getSize()];
                        newCvPart.getInputStream().read(updatedCvBytes);
                    }

                    // Processer la nouvelle image si elle est présente
                    if (newPicturePart != null && newPicturePart.getSize() > 0) {
                        updatedPictureBytes = new byte[(int) newPicturePart.getSize()];
                        newPicturePart.getInputStream().read(updatedPictureBytes);
                    }
                    loggedInStudent.setCv(updatedCvBytes);
                    loggedInStudent.setPicture(updatedPictureBytes);
                    // Update student in the database
                    this.studentDAO.update(loggedInStudent);

                    // Update corresponding user information
                    user loggedInUser = this.userDAO.findUserByEmail(oldEmail);
                    if (loggedInUser != null) {
                    	 if (updatedPassword == null || updatedPassword.isEmpty()) {
                             updatedPassword = loggedInUser.getPassword();
                         }
                    	loggedInUser.setEmail(updatedEmail);
                        loggedInUser.setPassword(updatedPassword);
                        this.userDAO.update(loggedInUser);
                    }

                    // Set updated student in the session
                    request.getSession().setAttribute("loggedInStudent", loggedInStudent);

                    request.setAttribute("updateSuccess", true);

                    response.sendRedirect(request.getContextPath() + "/Student/profil.jsp");
                    break;

            }
        }

        
    }
}
