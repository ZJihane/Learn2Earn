package DAO_Projet;

import java.util.List;
import Beans.project;
import DAO.DAOException;

public interface ProjectDAO {
    void create(project project) throws DAOException;

    List<project> getAllprojects(int i) throws DAOException;

    void update(project project) throws DAOException;

    void delete(int idproject) throws DAOException;

	project find(int idProject);

	

	
}
