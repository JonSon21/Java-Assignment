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
    public boolean modifyStaff(ArrayList<Staff> staff){
        boolean validation;
        String checkStaffid;
        
        Scanner input = new Scanner(System.in);
        
        do{
            System.out.print("Enter a staff ID :");
            checkStaffid = input.nextLine();
            
            if(checkStaffid.length()!=7){
               System.out.print("This is not a valid staff ID !\n");
               validation= false;
            }else if (checkStaffid.charAt(0)!='S'){
               System.out.println("This is not a valid Staff ID !\n");
               validation = false;
            }else{
                validation = true;
            }
        }while(!validation);
        
        boolean staffExist = false;
        int staffIndex = -1;
        
        for(int i = 0 ; i < staff.size() ; i++){
            if(checkStaffid.equalsIgnoreCase(staff.get(i).getStaffID())){
                staffExist = true;
                staffIndex = i;
            }            
        }
        
        if(staffExist){
            if(editStaff(staffIndex, staff)){
                System.out.print("Staff edited successfully !\n");
                return true;
            }else{
                System.out.println("Edit failed !\n");
                return false;
            }
        }else{
            if(addStaff(staff)){
                System.out.println("Staff added successfully !\n");
                return true;
            }else{
                System.out.println("Staff adding failed !\n");
                return false;
            }
        }      
    }
    
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
            System.out.print("Confirm to add staff ? (Y/N) :");
            confirmation = input.next().toUpperCase().charAt(0);
            input.nextLine();
        }while(confirmation != 'Y' && confirmation !='N');
     
        if(confirmation == 'Y'){
            staff.add(new Staff(firstName, lastName, gender, phoneNo,email ,icNo,jobTitle,password));
            return true;
        }else{
            return false;        
        }
    }
    
    
    
    private boolean editStaff(int i,ArrayList<Staff> staff){
        Scanner input = new Scanner(System.in);
        int choice = -1;
        String stringBuffer;
        
        System.out.println();
        System.out.format(
                "Staff ID: %s\nPassword: %s\nFirst Name: %s\nLast Name: %s\nGender: %s\nPhone No: %s\nEmail: %s\nIc No: %s\n",
                staff.get(i).getStaffID(), staff.get(i).getPassword(),
                staff.get(i).getFirstName(), staff.get(i).getLastName(), staff.get(i).getGender(),
                staff.get(i).getPhoneNo(), staff.get(i).getEmail(), staff.get(i).getIcNo());
        System.out.println();
        
        do{
            System.out.println("1. Password");
            System.out.println("2. First Name");
            System.out.println("3. Last Name");
            System.out.println("4. Gender");
            System.out.println("5. Phone No.");
            System.out.println("6. Email");
            choice = promptChoice(6);
        }while(choice < 1 || choice > 6);
        
        //buffer used to hold new data
        stringBuffer="";
        char charBuffer='M';
        
        switch (choice) {
            case 1:
                System.out.print("Enter the new Password: ");
                stringBuffer = input.nextLine();
                break;
            case 2:
                System.out.print("Enter the new First Name: ");
                stringBuffer = input.nextLine();
                stringBuffer = Character.toUpperCase(stringBuffer.charAt(0)) + stringBuffer.substring(1);
                break;
            case 3:
                System.out.print("Enter the new Last Name: ");
                stringBuffer = input.nextLine();
                stringBuffer = Character.toUpperCase(stringBuffer.charAt(0)) + stringBuffer.substring(1);
                break;
            case 4:
                do {
                    System.out.print("Enter the new Gender (M/F) : ");
                    charBuffer = input.next().charAt(0);
                } while (!validGender(charBuffer));
                break;
            case 5:
                do {
                    System.out.print("Enter the new Phone No. (0123456789): ");
                    stringBuffer = input.nextLine();
                } while (!validPhoneNo(stringBuffer));
                break;
            case 6:
                do {
                    System.out.print("Enter the new Email: ");
                    stringBuffer = input.nextLine();
                } while (!validEmail(stringBuffer));
                break;
            default:
                break;
        }
        //Verify confirmation
        char confirm;
        do {
            System.out.print("Do you really want to edit the detail? (Y/N): ");
            confirm = input.next().toUpperCase().charAt(0);
            input.nextLine();
        } while (confirm != 'Y' && confirm != 'N');

        if (confirm == 'Y') {
            switch (choice) {
                case 1:
                    System.out.println();
                    staff.get(i).setPassword(stringBuffer);
                    break;
                case 2:
                    System.out.println();
                    staff.get(i).setFirstName(stringBuffer);
                    break;
                case 3:
                    System.out.println();
                    staff.get(i).setLastName(stringBuffer);
                    break;
                case 4:
                    System.out.println();
                    staff.get(i).setGender(charBuffer);
                    break;
                case 5:
                    System.out.println();
                    staff.get(i).setPhoneNo(stringBuffer);
                    break;
                case 6:
                    System.out.println();
                    staff.get(i).setEmail(stringBuffer);
                    break;
                default:
                    System.out.println("Something is wrong");
            }
            return true;
        }

        // Return failed to edit staff if user entered n in veryfication
        else {
            System.out.print("Failed to edit staff's information !");
            return false;
        }
    }
    //Show all details of member
    
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
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '@') {
                atCounter++;
            }
            if (input.charAt(i) == '.') {
                dotCounter++;
            }
        }
        if (atCounter != 1 || dotCounter != 1) {
            System.out.println("\nPlease enter a valid email (address@domain.com).");
            return false;
        } else {
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
    
    private int promptChoice(int max){
        int choice = -1;
        boolean valid = false;
        Scanner input = new Scanner(System.in);

        do {
            System.out.print("Enter a number: ");
            try {
                choice = input.nextInt();
                input.nextLine();
                if(choice > max || choice < 1){
                    System.out.println("\nPlease enter a number between 1 and " + max + ".");
                    choice = -1;
                }
                valid = true;
            } catch (Exception e) {
                System.out.println("Please enter a valid number.\n");
                input.nextLine();
                choice = -1;
            }
        } while(!valid);

        return choice;
    }
    
}