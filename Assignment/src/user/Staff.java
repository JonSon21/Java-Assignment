package user;

class Staff extends UserDetails {
    
    private String jobTitle;
    private String staffID;
    private String password;
    
    //Constructor
    public Staff(){
    }
    
    public Staff(String firstName, String lastName, char gender, String phoneNo, String email, String icNo,String staffID,String jobTitle,String password){
        super(firstName,lastName,gender,phoneNo,email,icNo);
        this.jobTitle = jobTitle;
        this.staffID = staffID;
        this.password = password;                 
    }
    
    public Staff(String firstName, String lastName, char gender, String phoneNo, String email, String icNo,String jobTitle,String password){
        super(firstName,lastName,gender,phoneNo,email,icNo);
        this.jobTitle = jobTitle;
        this.password = password;
                   
    }
    
    public Staff(UserDetails userDetails,String staffID, String jobTitle, String password) {
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