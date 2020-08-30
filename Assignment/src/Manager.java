package user;

class Manager extends UserDetails {
	
	private String managerID;
    private String password;
    private String jobTitle;
    
    public Manager() {
    }

    public Manager(String firstName, String lastName, char gender, String phoneNo, String email, String icNo,String managerID, String password, String jobTitle) {
        super(firstName,lastName,gender,phoneNo,email,icNo);
        this.managerID = managerID;
        this.password = password;
        this.jobTitle = jobTitle;
    }
    
    //Getter
    public String getManagerID() {
        return managerID;
    }

    public String getPassword() {
        return password;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    // Setter
    public void setPassword(String password) {
        this.password = password;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    
    public String toString() {
        return super.toString() + "Manager{" + "managerID='" + managerID + '\'' + ", password='" + password + '\'' + ", jobTitle='" + jobTitle + '}';
    }
}