import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

class Employee extends PersonDetails implements Serializable {
	
    //Variables
    private static int nextEmployeeID = 1; // Counting the employees starting from 1
    private String jobTitle;
    private String employeeID;
    private String password;
    private Branch branch;
 
    //Constructor
    public Employee(){
    	
    }

    public Employee(PersonDetails personDetails, String jobTitle, String password, Branch branch) {
        super(personDetails);
        this.jobTitle = jobTitle;
        this.employeeID = String.format("S%04d", nextEmployeeID++);
        this.password = password;
        this.branch = branch;
    }

    public OrderList modifyOrderList(String productCode, ArrayList<OrderItem> ois, ArrayList<OrderList> ols, int list) {
        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < ois.size(); i++) {

            // Compare product id with existing products
            if (ois.get(i).getProduct().getProdId().equals(productCode)) {
            	
                // To check if the addOrderItem function is functional 
                // ols.get(listNo - 1) --> ols[listNo - 1]
                if (ols.get(list).addOrderItem(ois.get(i))) {
                	
                    // Show the receipt for all the items added into the cart
                    // This is to show the updated list of items
                    ols.get(list).receipt(false, 0);
                    break;
                }
                // Show "No such barcode / product ID" if the product id is not found in the files
            } else if (i == ois.size() - 1 && !"1".equals(productCode) && !"2".equals(productCode) && !"3".equals(productCode)) {
                System.out.println("No such barcode / product ID.\n");
                break;
            }
        }
        
        int editList = -1;
        int newQuantity;

        // Edit Order List
        if ("2".equals(productCode)) {
        	  Main main = new Main();
            do {
                System.out.print("Please enter the order list number > ");
                try {
                    editList = scan.nextInt();
                    
                    //Validate Error
                    if (editList < 1 || editList > ols.get(list).getItemCount()) {
                        main.errorMessage(2);
                    }
                }catch(Exception e){
                    main.errorMessage(2);
                    scan.nextLine();
                }
            } while (editList < 1 || editList > ols.get(list).getItemCount());

            do {
                System.out.print("Please enter the quantity > ");
                newQuantity = scan.nextInt();
                if (newQuantity < 0) {
                    main.errorMessage(3);
                }
            } while (newQuantity < 0);

            ols.get(list).editQuantity(editList,newQuantity);
            ols.get(list).receipt(false, 0);
        }
        
        //Display and gives option to choose product
        if("3".equals(productCode)){
        	
            if (ols.get(list).addOrderItem(ois.get(displayProduct(ois)))) {
                //Show the receipt every items inputted
                ols.get(list).receipt(false, 0);
            }
        }

        return ols.get(list);
    }

    public void payment(OrderList ol){
        Scanner scan = new Scanner(System.in);
        double amount = 0;
        do {
            System.out.print("Please enter the amount of RM you want to pay > ");
            try {
                amount = scan.nextDouble();
                if(amount < ol.getTotalAmount()){
                    System.out.println("The amount of RM you want to pay cannot be lower than the total price.\n");
                }
            }catch (Exception e){
                System.out.println("Please enter an amount.\n");
                scan.nextLine();
            }
        } while (amount < ol.getTotalAmount());
        System.out.printf("RM%.2f entered.\n", amount);
        ol.receipt(true, amount);
    }

    public int displayProduct(ArrayList<OrderItem> oi){
    	Main main = new Main();
        Scanner scan = new Scanner(System.in);
        
        int selectProduct = -1;
        boolean valid = true;
        System.out.printf("%-3s%-11s%-20s%20s%15s%10s\n%s\n","No", "Room ID", "Room(per hour)/Product", "Pax", "Quantity", "Price","===============================================================================");    
        for(int i = 0; i < oi.size(); i++){
            System.out.printf("%-3d" + oi.get(i).getProduct().toString(), (i+1));        
        }

        do {
            try {
                System.out.print("\nSelect a product: ");
                selectProduct = scan.nextInt() - 1;
            }catch(Exception e){
                scan.nextLine();
                valid = false;
            }
            if(selectProduct < 0 || selectProduct >= oi.size()){
                main.errorMessage(4);
                valid = false;
            }
            else valid = true;
        }while(!valid);
        return selectProduct;
    }

    public void displayTransactionHistory(ArrayList<OrderList> ols) {
    	Main main = new Main();
        Scanner scan = new Scanner(System.in);
        
        int selection = -1;
        boolean valid = true;

        System.out.println("============================");
        System.out.println("|    Transaction History   |");
        System.out.println("============================");
        System.out.printf("%-4s%-10s%10s\n", "No.", "Order ID", "Total (RM)");
        System.out.println("========================\n");
        for (int i = 0; i < ols.size(); i++) {
            System.out.printf("%-4d%-10s%10.2f\n", i + 1, ols.get(i).getOrderNo(), ols.get(i).getTotalAmount());
        }
        do {
            System.out.print("\nPlease select to display details: ");
            try{
                selection = scan.nextInt();
                if(selection < 1 || selection > ols.size()){
                    main.errorMessage(4);
                    valid = false;
                } else {
                    valid = true;
                }
            }catch (Exception e){
                main.errorMessage(4);
                scan.nextLine();
                valid = false;
            }
        }while(!valid);
        ols.get(selection - 1).receipt(true, ols.get(selection - 1).getAmount());
    }

    //Getter
    public static int getNextEmployeeID() {
        return nextEmployeeID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getJobTitle(){return jobTitle; }

    public String getPassword() {
        return password;
    }

    public Branch getBranch() {
        return branch;
    }

    //Setter
    public void setJobTitle(String jobTitle){
        this.jobTitle = jobTitle;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public static void setNextEmployeeID(int nextEmployeeID) {
        Employee.nextEmployeeID = nextEmployeeID;
    }

    // Override toString()
    @Override
    public String toString() {
        return String.format("Staff ID: %s\nPassword: %s\nBranch: %s\nFirst Name: %s\nLast Name: %s\nGender: %s\nPhone No: %s\nEmail: %s\nIc No: %s\n",
                employeeID,password,branch.getBranchName(),firstName,lastName,gender,phoneNo, email, icNo);
    }
}