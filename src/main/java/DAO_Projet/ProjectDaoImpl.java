package DAO_Projet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.project;
import DAO.DAOException;
import DAO.DAOFactory;

public class ProjectDaoImpl implements ProjectDAO {

    private DAOFactory daoFactory;

    public ProjectDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(project project) throws DAOException {
        final String SQL_INSERT = "INSERT INTO project (id_student, name, description, start_date, end_date) VALUES (?, ?, ?, ?, ?)";

        try (
            Connection connection = daoFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)
        ) {
            preparedStatement.setInt(1, project.getId_student());
            preparedStatement.setString(2, project.getName());
            preparedStatement.setString(3, project.getDescription());
            preparedStatement.setDate(4, new java.sql.Date(project.getStart_date().getTime()));
            preparedStatement.setDate(5, new java.sql.Date(project.getEnd_date().getTime()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error creating project", e);
        }
    }

    @Override
    public List<project> getAllprojects(int id_st) throws DAOException {
        final String SQL_SELECT_ALL = "SELECT * FROM project WHERE id_student=?";
        List<project> allProjects = new ArrayList<>();

        try (
            Connection connection = daoFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL)
        ) {
            // Set the parameter value
            preparedStatement.setInt(1, id_st);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    project project = map(resultSet);
                    allProjects.add(project);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error retrieving projects", e);
        }

        return allProjects;
    }


    @Override
    public void update(project proj) throws DAOException {
        
            final String SQL_UPDATE = "UPDATE project SET  name=?, description=?, start_date=?, end_date=? WHERE id_project=?";
            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
            	connection = daoFactory.getConnection();
                preparedStatement = connection.prepareStatement(SQL_UPDATE);
            
            preparedStatement.setString(1, proj.getName());
            preparedStatement.setString(2, proj.getDescription());
            preparedStatement.setDate(3, new java.sql.Date(proj.getStart_date().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(proj.getEnd_date().getTime()));
            preparedStatement.setInt(5, proj.getId_project());

            // Execute the update
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error updating project", e);
        } finally {
            // Close resources in the finally block
            closeResources(null, preparedStatement, connection);
        }
    }


    @Override
    public void delete(int projectId) throws DAOException {
    	String SQL_DELETE = "DELETE FROM project WHERE id_project=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
        	connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, projectId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error deleting project", e);
        } finally {
            // Close resources in the finally block
            closeResources(null, preparedStatement, connection);
        }
    }


    private static project map(ResultSet resultSet) throws SQLException {
        project project = new project();
        project.setId_project(resultSet.getInt("id_project"));
        project.setId_student(resultSet.getInt("id_student"));
        project.setName(resultSet.getString("name"));
        project.setDescription(resultSet.getString("description"));
        project.setStart_date(resultSet.getDate("start_date"));
        project.setEnd_date(resultSet.getDate("end_date"));
        return project;
    }
    
    private PreparedStatement initPreparedStatement(Connection connection, String sql, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    } 
    @Override
    public project find(int idProject) throws DAOException {
        final String SQL_SELECT_BY_ID = "SELECT * FROM project WHERE id_project=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        project project = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initPreparedStatement(connection, SQL_SELECT_BY_ID, idProject);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                project = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeResources(resultSet, preparedStatement, connection);
        }

        return project;
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
