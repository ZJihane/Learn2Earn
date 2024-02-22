package DAO_Skill;

import java.util.List;

import Beans.skill;
import DAO.DAOException;

public interface SkillDAO {
    
	void create(skill skill) throws DAOException;

    List<skill> getAllSkills() throws DAOException;

}