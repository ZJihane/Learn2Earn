package DAO_Education;

import java.util.List;
import Beans.education;
import DAO.DAOException;

public interface EducationDAO {
    void create(education education) throws DAOException;

    List<education> getAlleducations(int i) throws DAOException;

    void update(education education) throws DAOException;

    void delete(int idEducation) throws DAOException;

	education find(int id_education) throws DAOException;

	
}
