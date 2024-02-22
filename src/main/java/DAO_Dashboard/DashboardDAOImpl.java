package DAO_Dashboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DAO.DAOException;
import DAO.DAOFactory;

public class DashboardDAOImpl implements DashboardDAO {
    private DAOFactory daoFactory;

    public DashboardDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public int countStudents() {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM student")) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int countRecruiters() {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM recruiter")) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int countMissions() {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM mission")) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int countApplications() {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM application")) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public double calculatePercentageStudents() {
        int totalStudents = countStudents();
        int totalRecruiters = countRecruiters();
        double total = totalStudents + totalRecruiters;
        if (total == 0) {
            return 0;
        }
        return (totalStudents / total) * 100.0;
    }

    @Override
    public double calculatePercentageRecruiters() {
        int totalStudents = countStudents();
        int totalRecruiters = countRecruiters();
        double total = totalStudents + totalRecruiters;
        if (total == 0) {
            return 0;
        }
        return (totalRecruiters / total) * 100.0;
    }

    @Override
    public List<String> getMostCommonSkillsInMissions() {
        List<String> skills = new ArrayList<>();
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT mission_skill, COUNT(*) AS count FROM mission GROUP BY mission_skill ORDER BY count DESC LIMIT 5")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String skillName = resultSet.getString("mission_skill");
                skills.add(skillName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
    }


}
