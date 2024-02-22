package DAO_application;

import java.util.List;

import Beans.application;

public interface applicationDAO {
	
    void create(application application);

    List<application> getAllApplicationsByStudent(int id_student);

    List<application> getAllApplicationsByRecruiter(int id_recruteur);

    void updateStatus(int id_application, String status);
}
