package DAO_Mission;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Date;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import Beans.mission ;
import Beans.skill ;
import Beans.recruiter;
import DAO.DAOException;
import DAO.DAOFactory;
import DAO_Recruiter.recruiterDaoImpl; 
import DAO_Skill.SkillDaoImpl;
@WebServlet("/GestionMissions")
public class GestionMissions extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MissionDAO missionDAO;
    private recruiterDaoImpl recruiterDAO;
    private SkillDaoImpl skillDAO;

    public void init() throws ServletException {
            DAOFactory daoFactory = DAOFactory.getInstance();
            this.missionDAO = new MissionDaoImpl(daoFactory);
            this.recruiterDAO = new recruiterDaoImpl(daoFactory);
            this.skillDAO = new SkillDaoImpl(daoFactory);
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
            case "home" :
            	HttpSession session = request.getSession();
                recruiter recruiter = (recruiter) session.getAttribute("loggedInRecruiter");
                List<mission> missions = this.missionDAO.getAllMissionsForRecruiter(recruiter.getId_recruiter());
                session.setAttribute("missions", missions);
                List<skill> skills = this.skillDAO.getAllSkills();
                session.setAttribute("skills", skills);
                response.sendRedirect(request.getContextPath() + "/Recruiter/all_missions.jsp");
                break ;
                
            case "profile" :
            	
            	response.sendRedirect(request.getContextPath() + "/Recruiter/profile.jsp");
                break ;
            }
            
           
            	
    	
        
            }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
            List<mission> missions;

            String action = request.getParameter("action");

            if (action != null) {
                switch (action) {
                    case "delete":
                        String missionIdToDelete = request.getParameter("id_mission");
                        if (missionIdToDelete != null) {
                            int idMission = Integer.parseInt(missionIdToDelete);
                            this.missionDAO.delete(idMission);
                            HttpSession session = request.getSession();
                            recruiter recruiter = (recruiter) session.getAttribute("loggedInRecruiter");
                            List<mission> missions2 = this.missionDAO.getAllMissionsForRecruiter(recruiter.getId_recruiter());
                            session.setAttribute("missions", missions2);
                            response.sendRedirect(request.getContextPath() + "/Recruiter/all_missions.jsp");
                            
                        }
                        break;

                    case "add":
                        RequestDispatcher addDispatcher = request.getRequestDispatcher("/formMission.jsp");
                        addDispatcher.forward(request, response);
                        break;
                        
                        

                    case "update":
                        try {
                            String idMissionToUpdate = request.getParameter("id_mission");
                            int idMission = Integer.parseInt(idMissionToUpdate);
                            String name = request.getParameter("name");
                            String description = request.getParameter("description");
                            String remuneration = request.getParameter("remuneration");
                            String openDateString = request.getParameter("open_date");
                            Date openDate = new SimpleDateFormat("yyyy-MM-dd").parse(openDateString);
                            String closeDateString = request.getParameter("close_date");
                            Date closeDate = new SimpleDateFormat("yyyy-MM-dd").parse(closeDateString);
                            String missionSkill = request.getParameter("mission_skill");
                            

                            mission missionToEdit = new mission();
                            missionToEdit.setId_mission(idMission);
                            missionToEdit.setName(name);
                            missionToEdit.setDescription(description);
                            missionToEdit.setRemuneration(remuneration);
                            missionToEdit.setOpen_date(openDate);
                            missionToEdit.setClose_date(closeDate);
                            missionToEdit.setMission_skill(missionSkill);

                            missionDAO.update(missionToEdit);
                            HttpSession session = request.getSession();
                            recruiter recruiter = (recruiter) session.getAttribute("loggedInRecruiter");
                            List<mission> missions2 = this.missionDAO.getAllMissionsForRecruiter(recruiter.getId_recruiter());
                            session.setAttribute("missions", missions2);
                            response.sendRedirect(request.getContextPath() + "/Recruiter/all_missions.jsp");
                        } catch (DAOException | ParseException | NumberFormatException e) {
                            
                        }

                        break;

                        
                        
                        
                    case "edit":
                        String missionIdToEdit = request.getParameter("id_mission");
                        if (missionIdToEdit != null) {
                        int idMission = Integer.parseInt(missionIdToEdit);
                        mission missionToEdit = this.missionDAO.find(idMission);
                            if (missionToEdit != null) {
                            	HttpSession session = request.getSession();
                                session.setAttribute("mission", missionToEdit);
                                List<skill> skills = this.skillDAO.getAllSkills();
                                session.setAttribute("skills", skills);
                                response.sendRedirect(request.getContextPath() + "/Recruiter/edit_mission.jsp");
                            } 
                        }
                        break;
                        
                    case "show_st" :
                    	
                    	String missionIdToShow = request.getParameter("id_mission");
                    	if (missionIdToShow != null) {
                    		 int idMission = Integer.parseInt(missionIdToShow);
                    		 mission missionToShow = this.missionDAO.find(idMission);
                    		 recruiter recruiter_mission = this.recruiterDAO.find(missionToShow.getId_recruteur());
                    		 HttpSession session = request.getSession();
                             session.setAttribute("mission", missionToShow);
                             session.setAttribute("recruiter", recruiter_mission);
                             response.sendRedirect(request.getContextPath() +"/Student/detail_mission.jsp");
                    	}
                    	
                    	
                    	break ;


                    case "create":
                        String missionId = request.getParameter("id_mission");
                        String name = request.getParameter("name");
                        String description = request.getParameter("description");
                        String remuneration = request.getParameter("remuneration");
                        String openDateString = request.getParameter("open_date");
                        String closeDateString = request.getParameter("close_date");
                        String skill = request.getParameter("skill");
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date openDate = null; 
                        Date closeDate = null; 
                        try {
                            openDate = dateFormat.parse(openDateString);
                            closeDate = dateFormat.parse(closeDateString);
                        } catch (ParseException e) {
                            request.setAttribute("error", "Error parsing dates");
                            RequestDispatcher errorDispatcher = request.getRequestDispatcher("/error.jsp");
                            errorDispatcher.forward(request, response);
                            return;
                        }

                        String recruiterIdString = request.getParameter("id_recruteur");
                        int recruiterId = Integer.parseInt(recruiterIdString);
                        
                        mission mission = new mission();
                        mission.setName(name);
                        mission.setDescription(description);
                        mission.setRemuneration(remuneration);
                        mission.setOpen_date(openDate);
                        mission.setClose_date(closeDate);
                        mission.setId_recruteur(recruiterId);
                        mission.setMission_skill(skill);
                        if (missionId != null && !missionId.isEmpty()) {
                            mission.setId_mission(Integer.parseInt(missionId));
                            this.missionDAO.update(mission);
                        } else {
                            this.missionDAO.create(mission);
                        }

                    	HttpSession session = request.getSession();
                        recruiter recruiter = (recruiter) session.getAttribute("loggedInRecruiter");
                        List<mission> missions2 = this.missionDAO.getAllMissionsForRecruiter(recruiter.getId_recruiter());
                        session.setAttribute("missions", missions2);
                        response.sendRedirect(request.getContextPath() + "/Recruiter/all_missions.jsp");
                }
            }

        
    }
}
