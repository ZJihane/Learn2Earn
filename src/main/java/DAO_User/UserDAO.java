package DAO_User;

import java.util.List;

import Beans.user;
import DAO.DAOException;

public interface UserDAO {
    
	void create(user user) throws DAOException;

    List<user> getAllUsers() throws DAOException;

	user findUserByEmail(String email) throws DAOException;

	void update(user updatedUser) throws DAOException;

}
