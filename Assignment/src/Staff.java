package user;
import java.util.ArrayList;
import java.util.Scanner;
class Staff extends UserDetails {
    
    private String jobTitle;
    private String staffID;
    private String password;
    
    //Constructor

    public Staff(String firstName, String lastName, char gender, String phoneNo, String email, String icNo,String staffID,String jobTitle,String password){
        super(firstName,lastName,gender,phoneNo,email,icNo);
        this.jobTitle = jobTitle;
        this.staffID = staffID;
        this.password = password;                 
    }
    
    public Staff(String firstName, String lastName, char gender, String phoneNo, String email, String icNo,String jobTitle,String password){
        super(firstName,lastName,gender,phoneNo,email,icNo);
        this.jobTitle = jobTitle;
        this.staffID = "";
        this.password = password;                 
    }
    
    public Staff(UserDetails userDetails,String staffID, String jobTitle, String password) {
        super(userDetails);
        this.jobTitle = jobTitle;
        this.staffID = staffID;
        this.password = password;
    }

    Staff(Staff staff) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public void payment(OrderList orderList){
        Scanner scanner = new Scanner(System.in);
        double amount = 0;
        do {
            System.out.print("Please enter the amount of RM you want to pay > ");
            try {
                amount = scanner.nextDouble();
                if(amount < orderList.getTotalAmount()){
                    System.out.println("Please pay in full price of items.\n");
                }
            }catch (Exception e){
                System.out.println("Please enter an amount.\n");
                scanner.nextLine();
            }
        } while (amount < orderList.getTotalAmount());
        System.out.printf("RM%.2f entered.\n", amount);
        orderList.receipt(true, amount);
        
        orderListList.add(orderList);
    }
    
    
    
	public void displaySalesHistory(ArrayList<OrderList> orderListList) {
        Scanner scanner = new Scanner(System.in);
        int selection;
        boolean valid = true;

        System.out.println("Transaction History");
        System.out.println("----------------------------");
        System.out.printf("%-4s%-10s%10s\n", "No.", "Order ID", "Total (RM)");
        System.out.println("-----------------------");
        for (int i = 0; i < orderListList.size(); i++) {
            System.out.printf("%-4d%-10s%10.2f\n", i + 1, orderList.get(i).getOrderNo(), orderList.get(i).getTotalAmount());
        }
        do {
            System.out.print("Please select to display details: ");
            try{
                selection = scanner.nextInt();
                if(selection < 1 || selection > orderList.size()){
                    System.out.println("Please enter a valid number\n");
                    valid = false;
                } else {
                    valid = true;
                }
            }catch (Exception e){
                System.out.println("Please enter a valid number");
                scanner.nextLine();
                valid = false;
            }
        }while(!valid);
        orderList.get(selection - 1).receipt(true, orderList.get(selection - 1).getAmount());
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