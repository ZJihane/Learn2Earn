package DAO_Education;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.education;
import DAO.DAOException;
import DAO.DAOFactory;

public class EducationDaoImpl implements EducationDAO {

    private DAOFactory daoFactory;

    public EducationDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(education education) throws DAOException {
        final String SQL_INSERT = "INSERT INTO education (Institution, diploma, id_student, start_date, end_date) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT);

            preparedStatement.setString(1, education.getInstitution());
            preparedStatement.setString(2, education.getDiploma());
            preparedStatement.setInt(3, education.getIdStudent());
            preparedStatement.setDate(4, new java.sql.Date(education.getStartDate().getTime()));
            preparedStatement.setDate(5, new java.sql.Date(education.getEndDate().getTime()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error creating education", e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    @Override
    public List<education> getAlleducations(int id_st) throws DAOException {
        final String SQL_SELECT_ALL = "SELECT * FROM education WHERE id_student = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<education> allEducations = new ArrayList<>();


        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);

            // Set the parameter value
            preparedStatement.setInt(1, id_st);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                education education = map(resultSet);
                allEducations.add(education);
            }
        } catch (SQLException e) {
            throw new DAOException("Error retrieving educations", e);
        } finally {
            closeResources(resultSet, preparedStatement, connection);
        }

        return allEducations;
    }

    @Override
    public void update(education education) throws DAOException {
        final String SQL_UPDATE = "UPDATE education SET Institution=?, diploma=?, start_date=?, end_date=? WHERE id_education=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setString(1, education.getInstitution());
            preparedStatement.setString(2, education.getDiploma());
            preparedStatement.setDate(3, new java.sql.Date(education.getStartDate().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(education.getEndDate().getTime()));
            preparedStatement.setInt(5, education.getIdEducation());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error updating education", e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

  
    
    @Override
    public education find(int id_education) throws DAOException {
        final String SQL_SELECT_BY_ID = "SELECT * FROM education WHERE id_education=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        education education = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initPreparedStatement(connection, SQL_SELECT_BY_ID, id_education);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                education = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeResources(resultSet, preparedStatement, connection);
        }

        return education;
    }

    private PreparedStatement initPreparedStatement(Connection connection, String SQL_QUERY, int id_education) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY);
        preparedStatement.setInt(1, id_education);
        return preparedStatement;
    }

    
    @Override
    public void delete(int idEducation) throws DAOException {
        final String SQL_DELETE = "DELETE FROM education WHERE id_education=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, idEducation);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error deleting education", e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

	private static education map(ResultSet resultSet) throws SQLException {
        education education = new education();
        education.setIdEducation(resultSet.getInt("id_education"));
        education.setInstitution(resultSet.getString("Institution"));
        education.setDiploma(resultSet.getString("diploma"));
        education.setIdStudent(resultSet.getInt("id_student"));
        education.setStartDate(resultSet.getDate("start_date"));
        education.setEndDate(resultSet.getDate("end_date"));
        return education;
    }

    private static void closeResources(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    
                }
            }
        }
    }
}
