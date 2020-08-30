package user;

import java.util.ArrayList;
import java.util.Scanner;

class Manager extends UserDetails {
	
    private String managerID;
    private String password;
    private String jobTitle;
    
    //Constructor
    public Manager() {
    }
    
    public Manager(String firstName, String lastName, char gender, String phoneNo, String email, String icNo,String jobTitle,String managerID ,String password){
        super(firstName,lastName,gender,phoneNo,email,icNo);
        this.jobTitle = jobTitle;
        this.managerID = managerID;
        this.password = password;                 
    }
    
    public Manager(String firstName, String lastName, char gender, String phoneNo, String email, String icNo,String jobTitle,String password){
        super(firstName,lastName,gender,phoneNo,email,icNo);
        this.jobTitle = jobTitle;
        this.password = password;                
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
    
    //Add / Edit function
    
    public boolean addStaff(ArrayList<Staff> staff){
        Scanner input = new Scanner(System.in);
        String password,firstName,lastName,phoneNo,email,icNo,jobTitle;
        char gender;
        char confirmation;
        
        System.out.println("Enter new staff details");
        //Entering all the new details
        
        System.out.print("First Name: ");
        firstName = input.nextLine();
     
        System.out.print("Last Name: ");
        lastName = input.nextLine();
      
        do{
            System.out.print("Enter phone number: ");
            phoneNo = input.nextLine();
        }while(!validPhoneNo(phoneNo));
        
        do{
            System.out.print("Enter email: ");
            email = input.nextLine();
        }while(!validEmail(email));
        
        System.out.print("Enter IC number: ");
        icNo = input.nextLine();
        
        do{
            System.out.print("Gender (M/F): ");
            gender = input.nextLine().charAt(0);
        }while(!validGender(gender));
        
        System.out.print("Enter password: ");
        password = input.nextLine();
        
        System.out.print("Job title: ");
        jobTitle = input.nextLine();
        
        do{
            System.out.print("Confirm to add staff ? (Y/N)");
            confirmation = input.next().toUpperCase().charAt(0);
            input.nextLine();
        }while(confirmation != 'Y' && confirmation !='N');
     
        if(confirmation == 'Y'){
            staff.add((Staff) new UserDetails(new Staff(firstName, lastName, gender, phoneNo,email , icNo,jobTitle,password)));
            return true;
        }else{
            return false;
        }
    }
    
    
    //Validation methods
    private boolean validPhoneNo(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(0))) {
                System.out.println("\nPlease enter digit only.");
                return false;
            } else {
                return true;
            }
        }
        System.out.println("Something went wrong!");
        return false;
    }
    
    private boolean validEmail(String input) {
        int atCounter = 0;
        int dotCounter = 0;
        for(int i = 0; i >= input.length(); i++){
            if(input.charAt(i)=='@'){
              atCounter++;  
            } 
            if(input.charAt(i)== '.'){
                dotCounter++;
            }
        }
        if(atCounter !=1 || dotCounter!=1){
            System.out.print("\nPlease enter a valid email address!");
            return false;
        } else{
            return true;
        }       
    }
    
    private boolean validGender(char input){
        if(input == 'M' || input == 'F'){
            return true;
        }else{
            System.out.print("Please only enter M or F");
            return false;
        }
        
    }
    
    
    
}