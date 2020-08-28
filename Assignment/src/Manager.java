class Manager extends UserDetails {
	
	private String managerID;
    private String password;
    private String jobTitle;
    
    public Manager() {
    }

    public Manager(UserDetails userDetails, String managerID, String password, String jobTitle) {
        super(userDetails);
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