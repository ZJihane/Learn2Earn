package Beans;



public class mission_skill  {
    private int idMissionSkill;
    private int idMission;
    private int idSkill;

    
    public mission_skill() {
    }

    
    public int getId_Mission_skill() {
        return idMissionSkill;
    }

   
    
    public int getId_Mission() {
        return idMission;
    }

    public void setIdMission(int idMission) {
        this.idMission = idMission;
    }

   
    public int getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(int idSkill) {
        this.idSkill = idSkill;
    }
}

