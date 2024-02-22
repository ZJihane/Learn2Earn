package DAO_Education;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Beans.education;

import Beans.student;
import DAO.DAOException;
import DAO.DAOFactory;


@WebServlet("/gestionEducation")
public class GestionEducation extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private EducationDAO educationDAO;

    public void init() {
    	 DAOFactory daoFactory = DAOFactory.getInstance();
        educationDAO = new EducationDaoImpl(daoFactory);
    }

  

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                
                case "create":
                    try {
                        String institution = request.getParameter("institution");
                        String diploma = request.getParameter("diploma");
                        String idStudentString = request.getParameter("id_student");
                        int idStudent = Integer.parseInt(idStudentString);
                        
                        String startDateString = request.getParameter("startDate");
                        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateString);

                        String endDateString = request.getParameter("endDate");
                        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateString);

                        education newEducation = new education();
                        newEducation.setInstitution(institution);
                        newEducation.setDiploma(diploma);
                        newEducation.setIdStudent(idStudent);
                        newEducation.setStartDate(startDate);
                        newEducation.setEndDate(endDate);

                        educationDAO.create(newEducation);
                        response.sendRedirect("gestionProfil");

                    } catch (DAOException | ParseException | NumberFormatException e) {
                       
                       
                    }
                    break;

                case "edit":
                    // Handle the edit action
                    String educationIdToEdit = request.getParameter("id_education");

                    if (educationIdToEdit != null && !educationIdToEdit.isEmpty()) {
                        int idEducation = Integer.parseInt(educationIdToEdit);
                        education educationToEdit = this.educationDAO.find(idEducation);

                        // Check if the education is found
                        if (educationToEdit != null) {
                            // Retrieve other form values
                            String updatedInstitution = educationToEdit.getInstitution();
                            String updatedDiploma = educationToEdit.getDiploma();
                            Date updatedStartDate = educationToEdit.getStartDate();
                            Date updatedEndDate = educationToEdit.getEndDate();

                            // Set updated values in the educationToEdit object
                            educationToEdit.setInstitution(updatedInstitution);
                            educationToEdit.setDiploma(updatedDiploma);
                            educationToEdit.setStartDate(updatedStartDate);
                            educationToEdit.setEndDate(updatedEndDate);
                            student loggedInStudent = (student) request.getSession().getAttribute("loggedInStudent");
                            
                            if (loggedInStudent != null) {
                                
                                HttpSession session = request.getSession();
                                session.setAttribute("educationToEdit", educationToEdit);
                                  response.sendRedirect(request.getContextPath() +"/Student/edit_education.jsp");
                               
                          
                        } else {
                            // Handle the case where the education is not found
                            request.setAttribute("error", "Education not found");
                            RequestDispatcher errorDispatcher = request.getRequestDispatcher("/error.jsp");
                            errorDispatcher.forward(request, response);
                        }
                    }
                    }
                    break;
                    
                case "update":
                    try {
                        String ided = request.getParameter("idEducation");
                        int idEducation = Integer.parseInt(ided);
                        
                        String institution = request.getParameter("institution");
                        String diploma = request.getParameter("diploma");
                        
                      
                        String startDateString = request.getParameter("startDate");
                        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateString);
                        String endDateString = request.getParameter("endDate");
                        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateString);

                        education newEducation = new education();
                        newEducation.setIdEducation(idEducation);
                        newEducation.setInstitution(institution);
                        newEducation.setDiploma(diploma);
                       
                        newEducation.setStartDate(startDate);
                        newEducation.setEndDate(endDate);
                        educationDAO.update(newEducation);
                        response.sendRedirect("gestionProfil");
                        
                    } catch (DAOException | ParseException | NumberFormatException e) {
                       
                        e.printStackTrace();
                        
                    }

                    break;



                case "delete":
                    try {
                        String educationIdToDelete = request.getParameter("id_education");
                        if (educationIdToDelete != null && !educationIdToDelete.isEmpty()) {
                            int educationId = Integer.parseInt(educationIdToDelete);
                            this.educationDAO.delete(educationId);
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
