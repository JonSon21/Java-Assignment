class Staff extends UserDetails {
    
    private String jobTitle;
    private String staffID;
    private String password;

	public Staff(){
	}

    public Staff(UserDetails userDetails, String staffID, String jobTitle, String password) {
        super(userDetails);
        this.jobTitle = jobTitle;
        this.staffID = staffID;
        this.password = password;
    }
    
    //Getter
    public String getStaffID() {
        return staffID;
    }

    public String getJobTitle(){
    	return jobTitle;
    };

    public String getPassword() {
        return password;
    }

    //Setter
    public void setStaffID(){
    	this.staffID = staffID;
    }
    
    public void setJobTitle(String jobTitle){
        this.jobTitle = jobTitle;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String toString() {
        return super.toString() + "Staff{" + "Staff ID='" + staffID + '\'' + ", password='" + password + '\'' + ", jobTitle='" + jobTitle + '}';
    }
}