package DAO_Recruiter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Beans.recruiter;
import DAO.DAOException;
import DAO.DAOFactory;

public class recruiterDaoImpl implements recruiterDAO {

    private DAOFactory daoFactory;

    public recruiterDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public recruiter create(recruiter recruiter) throws DAOException {
        final String SQL_INSERT = "INSERT INTO recruiter (name, email, company, description, address, city, country) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            // Specify the column names to be returned
            preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            // Set the parameters
            preparedStatement.setString(1, recruiter.getName());
            preparedStatement.setString(2, recruiter.getEmail());
            preparedStatement.setString(3, recruiter.getCompany());
            preparedStatement.setString(4, recruiter.getDescription());
            preparedStatement.setString(5, recruiter.getAddress());
            preparedStatement.setString(6, recruiter.getCity());
            preparedStatement.setString(7, recruiter.getCountry());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    // Set the generated ID to the recruiter object
                    recruiter.setId_recruiter(generatedKeys.getInt(1));

                    // Return the created recruiter object
                    return recruiter;
                }
            }

            throw new DAOException("Creating recruiter failed, no ID obtained.");
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(preparedStatement, connection);
        }
    }


    private static recruiter map(ResultSet resultSet) throws SQLException {
        recruiter recruiter = new recruiter();
        recruiter.setId_recruiter(resultSet.getInt("id_recruiter"));
        recruiter.setName(resultSet.getString("name"));
        recruiter.setEmail(resultSet.getString("email"));
        recruiter.setCompany(resultSet.getString("company"));
        recruiter.setDescription(resultSet.getString("description"));
        recruiter.setAddress(resultSet.getString("address"));
        recruiter.setCity(resultSet.getString("city"));
        recruiter.setCountry(resultSet.getString("country"));
        return recruiter;
    }

    public static PreparedStatement initRequestPrepare(Connection connection, String sql, Object... objects) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i + 1, objects[i]);
        }
        return preparedStatement;
    }

    @Override
    public recruiter find(int id_recruiter) throws DAOException {
        final String SQL_SELECT_PAR_ID = "SELECT id_recruiter, name, email, company, description, address, city, country FROM recruiter WHERE id_recruiter=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        recruiter recruiter = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initRequestPrepare(connection, SQL_SELECT_PAR_ID, id_recruiter);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                recruiter = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connection);
        }

        return recruiter;
    }

    @Override
    public List<recruiter> getAllRecruiters() {
        final String SQL_SELECT_ALL = "SELECT id_recruiter, name, email, company, description, address, city, country FROM recruiter";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        recruiter recruiter = null;
        List<recruiter> allRecruiters = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initRequestPrepare(connection, SQL_SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                recruiter = map(resultSet);
                allRecruiters.add(recruiter);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connection);
        }

        return allRecruiters;
    }

    @Override
    public void update(recruiter recruiter) throws DAOException {
        final String SQL_UPDATE = "UPDATE recruiter SET name=?, email=?, company=?, description=?, address=?, city=?, country=? WHERE id_recruiter=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initRequestPrepare(connection, SQL_UPDATE,
                    recruiter.getName(),
                    recruiter.getEmail(),
                    recruiter.getCompany(),
                    recruiter.getDescription(),
                    recruiter.getAddress(),
                    recruiter.getCity(),
                    recruiter.getCountry(),
                    recruiter.getId_recruiter()
            );
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(preparedStatement, connection);
        }
    }

    @Override
    public void delete(int idRecruiter) throws DAOException {
        final String SQL_DELETE = "DELETE FROM recruiter WHERE id_recruiter=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initRequestPrepare(connection, SQL_DELETE, idRecruiter);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(preparedStatement, connection);
        }
    }
}
