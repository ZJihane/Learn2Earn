package DAO_Skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.skill;
import DAO.DAOException;
import DAO.DAOFactory;

public class SkillDaoImpl implements SkillDAO {

    private DAOFactory daoFactory;

    public SkillDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(skill skill) throws DAOException {
        final String SQL_INSERT = "INSERT INTO skill (name) VALUES (?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initPreparedStatement(connection, SQL_INSERT, skill.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error creating skill", e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    @Override
    public List<skill> getAllSkills() throws DAOException {
        final String SQL_SELECT_ALL = "SELECT id_skill, name FROM skill";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        skill skill = null;
        List<skill> allSkills = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initPreparedStatement(connection, SQL_SELECT_ALL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                skill = map(resultSet);
                allSkills.add(skill);
            }
        } catch (SQLException e) {
            throw new DAOException("Error retrieving skill", e);
        } finally {
            closeResources(resultSet, preparedStatement, connection);
        }

        return allSkills;
    }

    private static skill map(ResultSet resultSet) throws SQLException {
        skill skill = new skill();
        skill.setId_skill(resultSet.getInt("id_skill"));
        skill.setName(resultSet.getString("name"));
        return skill;
    }

    private static PreparedStatement initPreparedStatement(Connection connection, String sql, Object... objects)
            throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i + 1, objects[i]);
        }
        return preparedStatement;
    }

    private static void closeResources(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    // Log or handle the exception, if needed.
                }
            }
        }
    }
}
