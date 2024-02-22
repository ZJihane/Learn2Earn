package DAO_Mission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.mission;
import DAO.DAOException;
import DAO.DAOFactory;

public class MissionDaoImpl implements MissionDAO {

    private DAOFactory daoFactory;

    public  MissionDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(mission mission) throws DAOException {
        final String SQL_INSERT = "INSERT INTO mission (name, description, remuneration, open_date, close_date, id_recruteur, mission_skill) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initPreparedStatement(connection, SQL_INSERT,
                    mission.getName(),
                    mission.getDescription(),
                    mission.getRemuneration(),
                    mission.getOpen_date(),
                    mission.getClose_date(),
                    mission.getId_recruteur(),
                    mission.getMission_skill()
                    
            );
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    @Override
    public mission find(int id_mission) throws DAOException {
        final String SQL_SELECT_BY_ID = "SELECT * FROM mission WHERE id_mission=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        mission mission = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initPreparedStatement(connection, SQL_SELECT_BY_ID, id_mission);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                mission = mapResultSetToMission(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeResources(resultSet, preparedStatement, connection);
        }

        return mission;
    }

    @Override
    public List<mission> getAllMissions() throws DAOException {
        final String SQL_SELECT_ALL = "SELECT * FROM mission";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<mission> allMissions = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initPreparedStatement(connection, SQL_SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	mission mission = mapResultSetToMission(resultSet);
                allMissions.add(mission);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeResources(resultSet, preparedStatement, connection);
        }

        return allMissions;
    }

    @Override
    public void update(mission mission) throws DAOException {
        final String SQL_UPDATE = "UPDATE mission SET name=?, description=?, remuneration=?, open_date=?, close_date=?, mission_skill=?  WHERE id_mission=? ";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initPreparedStatement(connection, SQL_UPDATE,
                    mission.getName(),
                    mission.getDescription(),
                    mission.getRemuneration(),
                    mission.getOpen_date(),
                    mission.getClose_date(),
                    mission.getMission_skill(),
                    mission.getId_mission()
            );
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    @Override
    public void delete(int idMission) throws DAOException {
        final String SQL_DELETE = "DELETE FROM mission WHERE id_mission=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initPreparedStatement(connection, SQL_DELETE, idMission);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    // Helper method to map ResultSet to Mission object
    private mission mapResultSetToMission(ResultSet resultSet) throws SQLException {
    	mission mission = new mission();
        mission.setId_mission(resultSet.getInt("id_mission"));
        mission.setName(resultSet.getString("name"));
        mission.setDescription(resultSet.getString("description"));
        mission.setRemuneration(resultSet.getString("remuneration"));
        mission.setOpen_date(resultSet.getDate("open_date"));
        mission.setClose_date(resultSet.getDate("close_date"));
        mission.setId_recruteur(resultSet.getInt("id_recruteur"));
        mission.setMission_skill(resultSet.getString("mission_skill"));
        return mission;
    }
    @Override
    public List<mission> getAllMissionsForRecruiter(int recruiterId) {
        List<mission> missions = new ArrayList<>();

        final String SQL_ALL = "SELECT * FROM mission WHERE id_recruteur = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_ALL);
            preparedStatement.setInt(1, recruiterId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                	mission mission = mapResultSetToMission(resultSet);
                    missions.add(mission);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        } finally {
            // Close resources in the reverse order of their creation to avoid potential leaks
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception properly in your application
            }
        }

        return missions;
    }

@Override
    public List<mission> getAllMissionsForStudent(String skill) {
        List<mission> missions = new ArrayList<>();

        final String SQL_ALL = "SELECT * FROM mission WHERE mission_skill = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
        	connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_ALL);
            preparedStatement.setString(1, skill);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    mission mission = mapResultSetToMission(resultSet);
                    missions.add(mission);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        } finally {
            // Close resources in the reverse order of their creation to avoid potential leaks
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception properly in your application
            }
        }

        return missions;
    }

   

    // Helper method to initialize a PreparedStatement with parameters
    private PreparedStatement initPreparedStatement(Connection connection, String sql, Object... objects) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i + 1, objects[i]);
        }
        return preparedStatement;
    }

    // Helper method to close resources (ResultSet, PreparedStatement, Connection)
    private void closeResources(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    // Log the exception or handle it as needed
                }
            }
        }
    }

	
}
