package DAO_Mission;

import java.util.List;
import Beans.mission;
import DAO.DAOException;

public interface MissionDAO {
    void create(mission mission) throws DAOException;

    mission find(int id_mission) throws DAOException;

    List<mission> getAllMissions() throws DAOException;

    void update(mission mission) throws DAOException;

    void delete(int idMission) throws DAOException;

	List<mission> getAllMissionsForRecruiter(int recruiterId);

	List<mission> getAllMissionsForStudent(String skill);

	
}
