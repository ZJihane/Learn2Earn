package DAO_Projet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;

import Beans.project;
import Beans.student;
import DAO.DAOException;
import DAO.DAOFactory;

@WebServlet("/gestionProjects")
public class GestionProjects extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProjectDAO projectDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        projectDAO = new ProjectDaoImpl(daoFactory);
    }

   

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                
                case "create":
                    try {
                        String projectName = request.getParameter("projectName");
                        String projectDescription = request.getParameter("projectDescription");
                        String idStudentString = request.getParameter("id_student");
                        int idStudent = Integer.parseInt(idStudentString);
                        
                        String startDateString = request.getParameter("startDate");
                        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateString);

                        String endDateString = request.getParameter("endDate");
                        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateString);

                        project newProject = new project();
                        newProject.setName(projectName);
                        newProject.setDescription(projectDescription);
                        newProject.setId_student(idStudent);
                        newProject.setStart_date(startDate);
                        newProject.setEnd_date(endDate);

                        projectDAO.create(newProject);
                        
                        response.sendRedirect("gestionProfil");

                    } catch (DAOException | ParseException | NumberFormatException e) {
                        // Handle exceptions
                        e.printStackTrace();
                    }
                    break;

                case "edit":
                	String projectIdToEdit = request.getParameter("id_project");

                	if (projectIdToEdit != null && !projectIdToEdit.isEmpty()) {
                	    int idProject = Integer.parseInt(projectIdToEdit);
                	    project projectToEdit = this.projectDAO.find(idProject);

                	    // Check if the project is found
                	    if (projectToEdit != null) {
                	        // Retrieve other form values
                	        String updatedName = projectToEdit.getName();
                	        String updatedDescription = projectToEdit.getDescription();
                	        Date updatedStartDate = projectToEdit.getStart_date();
                	        Date updatedEndDate = projectToEdit.getEnd_date();

                	        // Set updated values in the projectToEdit object
                	        projectToEdit.setName(updatedName);
                	        projectToEdit.setDescription(updatedDescription);
                	        projectToEdit.setStart_date(updatedStartDate);
                	        projectToEdit.setEnd_date(updatedEndDate);
                	        
                	        // Assuming you have a session attribute for the loggedInStudent
                	        student loggedInStudent = (student) request.getSession().getAttribute("loggedInStudent");

                	        if (loggedInStudent != null) {
                	            HttpSession session = request.getSession();
                	            session.setAttribute("projectToEdit", projectToEdit);
                	            response.sendRedirect(request.getContextPath() + "/Student/edit_project.jsp");
                	        } else {
                	            // Handle the case where the student is not found
                	            request.setAttribute("error", "Student not found");
                	            RequestDispatcher errorDispatcher = request.getRequestDispatcher("/error.jsp");
                	            errorDispatcher.forward(request, response);
                	        }
                	    } else {
                	        // Handle the case where the project is not found
                	        request.setAttribute("error", "Project not found");
                	        RequestDispatcher errorDispatcher = request.getRequestDispatcher("/error.jsp");
                	        errorDispatcher.forward(request, response);
                	    }
                	}

                    break;
                case "update":
                    try {
                        // Retrieve values from the form
                        String updatedProjectId = request.getParameter("id_project");
                        String updatedProjectName = request.getParameter("name");
                        String updatedProjectDescription = request.getParameter("description");
                        String updatedStartDateString = request.getParameter("start_date");
                        String updatedEndDateString = request.getParameter("end_date");

                        // Validate and parse the date strings
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date updatedStartDate = dateFormat.parse(updatedStartDateString);
                        Date updatedEndDate = dateFormat.parse(updatedEndDateString);

                        // Create a project object with the updated values
                        project updatedProject = new project();
                        updatedProject.setId_project(Integer.parseInt(updatedProjectId));
                        updatedProject.setName(updatedProjectName);
                        updatedProject.setDescription(updatedProjectDescription);
                        updatedProject.setStart_date(updatedStartDate);
                        updatedProject.setEnd_date(updatedEndDate);

                        
                        projectDAO.update(updatedProject);
                        response.sendRedirect("gestionProfil");
                       
                    } catch (DAOException | ParseException | NumberFormatException e) {
                        e.printStackTrace();
                        
                    }

                    
                    break;
                case "delete":
                    try {
                        String projectIdToDelete = request.getParameter("id_project");
                        if (projectIdToDelete != null && !projectIdToDelete.isEmpty()) {
                            int projectId = Integer.parseInt(projectIdToDelete);
                            this.projectDAO.delete(projectId);
                        } else {
                            request.setAttribute("deleteError", true);
                        }
                    } catch (DAOException e) {
                        e.printStackTrace();
                        request.setAttribute("deleteError", true);
                    }
                    response.sendRedirect("gestionProfil");
                    break;

                default:
                    response.sendRedirect("gestionProfil");
            }
        } else {
            response.sendRedirect("gestionProfil");
        }
    }

}
