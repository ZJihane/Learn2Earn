package DAO_Recruiter;

import java.util.List;

import Beans.recruiter;
import DAO.DAOException;

public interface recruiterDAO {
    recruiter create(recruiter recruiter) throws DAOException;

    recruiter find(int id_recruiter) throws DAOException;

    List<recruiter> getAllRecruiters() throws DAOException;

    void update(recruiter recruiter) throws DAOException;

    void delete(int idRecruiter) throws DAOException;
}
