package DAO_Dashboard;
import java.util.List;


public interface DashboardDAO {
    int countStudents();
    int countRecruiters();
    int countMissions();
    int countApplications();
    double calculatePercentageStudents();
    double calculatePercentageRecruiters();
    List<String> getMostCommonSkillsInMissions();
}
