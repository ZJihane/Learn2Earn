package Beans;


import java.util.Date;

public class mission {
    private int id_mission;
    private String name;
    private String description;
    private String remuneration;
    private Date open_date;
    private Date close_date;
    private int id_recruteur;
    private String mission_skill ;

    public mission() {
    }

    public int getId_mission() {
        return id_mission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(String remuneration) {
        this.remuneration = remuneration;
    }

    public Date getOpen_date() {
        return open_date;
    }

    public void setOpen_date(Date openDate) {
        this.open_date = openDate;
    }

    public Date getClose_date() {
        return close_date;
    }

    public void setClose_date(Date closeDate) {
        this.close_date = closeDate;
    }

    public int getId_recruteur() {
        return id_recruteur;
    }

    public void setId_recruteur(int id_recruteur) {
        this.id_recruteur = id_recruteur;
    }

	public void setId_mission(int id_mission) {
		this.id_mission = id_mission;
	}

	public String getMission_skill() {
		return mission_skill;
	}

	public void setMission_skill(String mission_skill) {
		this.mission_skill = mission_skill;
	}

	
}

