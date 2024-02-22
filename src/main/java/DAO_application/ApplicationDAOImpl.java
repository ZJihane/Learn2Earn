package DAO_application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Beans.application;
import DAO.DAOFactory;

public class ApplicationDAOImpl implements applicationDAO {
    private DAOFactory daoFactory;

    // Constructor name corrected to match the class name
    public ApplicationDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(application application) {
        String SQL_INSERT = "INSERT INTO application (id_student, id_mission) VALUES (?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT);

            preparedStatement.setInt(1, application.getId_student());
            preparedStatement.setInt(2, application.getId_mission());
            

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    application.setId_application(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }
    }

    @Override
    public List<application> getAllApplicationsByStudent(int id_student) {
        List<application> applications = new ArrayList<>();
        String SQL_SELECT = "SELECT * FROM application WHERE id_student = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            preparedStatement.setInt(1, id_student);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                	application application = mapResultSetToApplication(resultSet);
                    applications.add(application);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }

        return applications;
    }

    @Override
    public List<application> getAllApplicationsByRecruiter(int id_recruteur) {
        List<application> applications = new ArrayList<>();
        String SQL_SELECT = "SELECT * FROM application WHERE id_mission IN (SELECT id_mission FROM mission WHERE id_recruteur = ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            preparedStatement.setInt(1, id_recruteur);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    application application = mapResultSetToApplication(resultSet);
                    applications.add(application);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }

        return applications;
    }

   @Override
     public void updateStatus(int id_application, String status) {
        String SQL_UPDATE = "UPDATE application SET status = ? WHERE id_application = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, id_application);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }
    }

    private application mapResultSetToApplication(ResultSet resultSet) throws SQLException {
        application application = new application();
        application.setId_application(resultSet.getInt("id_application"));
        application.setId_student(resultSet.getInt("id_student"));
        application.setId_mission(resultSet.getInt("id_mission"));
        application.setStatus(resultSet.getString("status"));

        return application;
    }


}
