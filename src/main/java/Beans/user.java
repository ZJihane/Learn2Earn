package Beans;



public class user  {
    
	
	private int id_user;
    private String email;
    private String password;
    private int id_role ;
    
    public int getId_role() {
		return id_role;
	}

	public void setId_role(int id_role) {
		this.id_role = id_role;
	}

	private int role;

    public user() {
    }

    public int getId_user() {
        return id_user;
    }

   

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	
}

