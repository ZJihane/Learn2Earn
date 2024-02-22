package DAO_User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.user;
import DAO.DAOException;
import DAO.DAOFactory;

public class UserdaoImpl implements UserDAO {

    private DAOFactory daoFactory;

    public UserdaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<user> getAllUsers() throws DAOException {
        final String SQL_SELECT_ALL = "SELECT id_user, email, password, role , id_role FROM user";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        user user = null;
        List<user> allUsers = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initRequestPrepare(connection, SQL_SELECT_ALL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = map(resultSet);
                allUsers.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connection);
        }

        return allUsers;
    }

    private static user map(ResultSet resultSet) throws SQLException {
        user user = new user();
        user.setId_user(resultSet.getInt("id_user"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(resultSet.getInt("role"));
        user.setId_role(resultSet.getInt("id_role"));
        return user;
    }

    private static PreparedStatement initRequestPrepare(Connection connection, String sql, Object... objects)
            throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i + 1, objects[i]);
        }
        return preparedStatement;
    }
    @Override
	public void create(user user) {
		  final String SQL_INSERT = "INSERT INTO user (email, password, role, id_role) VALUES (?, ?, ?, ?)";
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            connection = daoFactory.getConnection();
	            preparedStatement = initRequestPrepare(connection, SQL_INSERT,
	                    user.getEmail(),
	                    user.getPassword(),
	                    user.getRole(),
	                    user.getId_role()
	                   
	            );
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            throw new DAOException(e);
	        } finally {
	            // ClosingAll(preparedStatement, connection);
	        }
		
	}
    public user findUserByEmail(String email) throws DAOException {
        String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM user WHERE email = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user foundUser = new user();
                foundUser.setId_user(resultSet.getInt("id_user"));
                foundUser.setEmail(resultSet.getString("email"));
                foundUser.setPassword(resultSet.getString("password"));
                foundUser.setRole(resultSet.getInt("role"));
                foundUser.setId_role(resultSet.getInt("id_role"));
                // Set other properties as needed

                return foundUser;
            } else {
                return null; // No user found with the given email
            }
        } catch (SQLException e) {
            throw new DAOException("Error finding user by email", e);
        } finally {
            closeResources(resultSet, preparedStatement, connection);
        }
    }
    
    protected void closeResources(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            // Handle the exception or log it
            e.printStackTrace();
        }
    }
    @Override
    public void update(user updatedUser) throws DAOException {
        String SQL_UPDATE = "UPDATE user SET email=?, password=?, id_role=? WHERE id_user=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, updatedUser.getEmail());
            preparedStatement.setString(2, updatedUser.getPassword());
            preparedStatement.setInt(3, updatedUser.getId_role());
            preparedStatement.setInt(4, updatedUser.getId_user());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new DAOException("Updating user failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException("Error updating user", e);
        } finally {
            closeResources(null, preparedStatement, connection);
        }
    }

}
