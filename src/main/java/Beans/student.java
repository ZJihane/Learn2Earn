package Beans;

import java.io.Serializable;

public class student implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private int id_student;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String linkedin;
    private byte[] cv;
    private byte[] picture;
    private String pictureBase64;
    private String skill ; 
   


	public student() {
    }

    
    public int getId_student() {
		return id_student;
	}

	public void setId_student(int id_student) {
		this.id_student = id_student;
	}

	public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

   


	public byte[] getCv() {
		return cv;
	}


	public void setCv(byte[] cv) {
		this.cv = cv;
	}


	public byte[] getPicture() {
		return picture;
	}


	public void setPicture(byte[] picture) {
		this.picture = picture;
	}


	public String getPictureBase64() {
		return pictureBase64;
	}


	public void setPictureBase64(String pictureBase64) {
		this.pictureBase64 = pictureBase64;
	}

	 public String getSkill() {
			return skill;
		}


		public void setSkill(String skill) {
			this.skill = skill;
		}
	
    
}
