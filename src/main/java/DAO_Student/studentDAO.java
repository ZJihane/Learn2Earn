package DAO_Student;

import java.util.List;

import Beans.student;
import DAO.DAOException;

public interface studentDAO {
	 student create( student student ) throws DAOException;

	 student find( int id_student ) throws DAOException;
	 
	 List<student> getAllUsers() throws DAOException;

	void update(student student) throws DAOException;

	

	void delete(int idStudent) throws DAOException;
	}
