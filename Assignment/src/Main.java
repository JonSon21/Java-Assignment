import java.io.*;
import java.util.*;

public class Main {
	
    // Used to store username for logout message
    private static String userName = new String();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Variables
        int employeeOrManager; // For login
        
        boolean loginSuccess;
           
        String username = null;
        String password = null;
        
        // Class Declaration
        Manager manager = new Manager();
        Branch branch = new Branch();
        
   		// ArrayLists
        ArrayList<PersonDetails> person = new ArrayList<>();
        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<Product> product = new ArrayList<>();
        ArrayList<OrderItem> orderItem = new ArrayList<>();
        ArrayList<OrderList> orderLists = new ArrayList<>();

        // Initialize all filepath
        String[] filepath = new String[7];
        filepath[0] = "../obj/person.bin";
        filepath[1] = "../obj/managers.bin";
        filepath[2] = "../obj/branch.bin";
        filepath[3] = "../obj/employees.bin";
        filepath[4] = "../obj/product.bin";
        filepath[5] = "../obj/orderItem.bin";
        filepath[6] = "../obj/orderLists.bin";

        try {
            FileInputStream[] fis = new FileInputStream[filepath.length];
            ObjectInputStream[] ois = new ObjectInputStream[filepath.length];
            
            for (int i = 0; i < filepath.length; i++) {
                fis[i] = new FileInputStream(filepath[i]);
                ois[i] = new ObjectInputStream(fis[i]);
            }
            
            // File reading
            person = (ArrayList<PersonDetails>) ois[0].readObject();
            manager = (Manager) ois[1].readObject();
            branch = (Branch) ois[2].readObject();
            employees = (ArrayList<Employee>) ois[3].readObject();
            product = (ArrayList<Product>) ois[4].readObject();
            orderItem = (ArrayList<OrderItem>) ois[5].readObject();
            orderLists = (ArrayList<OrderList>) ois[6].readObject();
            
            //Object Input Stream Close
            for (int i = 0; i < filepath.length; i++) {
                ois[i].close();
            }
            
        // To trace error within the files / code
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        // Reassign static value
        // This is to set the counters (starting from 1) in the respective classes
        
        // So the Product ID, OrderNo, Employee ID will be +1 compared to 
        // the existing items within the files
        
        OrderList.setNextOrderNo(orderLists.size() + 1);
        Employee.setNextEmployeeID(employees.size() + 1);
        Product.setNextProdId(product.size() + 1);

		// Starting Point
        bootupScreen();

		// Employee or Manager
        do {
        	System.out.println("=====================");
            System.out.println("|Employee or Manager|");
            System.out.println("=====================");
            System.out.println("1. Employee");
            System.out.println("2. Manager");
            System.out.println("0. Exit and update files");
            System.out.print("> ");

            try {
                employeeOrManager = scan.nextInt();
                scan.nextLine();
            } catch (Exception e) {
                scan.nextLine();
                employeeOrManager = -1;
            }
			
            if (employeeOrManager == 1 || employeeOrManager == 2) {

            	 
           		System.out.println("============");
                System.out.println("|Login Page|");
                System.out.println("============");

            		
         		
                System.out.print("Username: ");
                username = scan.nextLine();

                System.out.print("Password: ");
                password = scan.nextLine();
            }
				userName = username;

            switch (employeeOrManager) {
            case 1:
            	
            	// Employee Login
                Login eLogin = new Login(username, password);
                loginSuccess = eLogin.employeeLogin(employees);
                if (loginSuccess) {
                    System.out.println("Log in successful.\n");
                    System.out.println("Username: " + eLogin.getUsername() + "\nEmployee Name: "
                            + employees.get(eLogin.getIndex()).getFirstName() + " "
                            + employees.get(eLogin.getIndex()).getLastName());
                    System.out.println("\n" + eLogin.getUsername() + " has logged in at " + eLogin.currentTime() + "\n");
                    employeeMenuOptions(orderItem, orderLists, product);
                } else {
                    System.out.println("Wrong username or password, please login again.\n");
                }
                break;
            case 2:
            	
            	// Manager Login
                Login mLogin = new Login(username, password);
                loginSuccess = mLogin.managerLogin(manager);
                if (loginSuccess) {
                    System.out.println("Log in successful.\n");
                    System.out.println("Username: " + mLogin.getUsername() + "\nManager Name: "
                            + employees.get(mLogin.getIndex()).getFirstName() + " "
                            + employees.get(mLogin.getIndex()).getLastName());
                    System.out.println("\n" + mLogin.getUsername() + " has logged in at " + mLogin.currentTime() + "\n");
                    managerMenuOptions(product, employees, orderItem, orderLists);
                } else {
                    System.out.println("Wrong username or password, please login again.\n");
                }
                break;
            case 0:
            	
                // Update all data into files
                WriteObjectToFile(person, filepath[0]);
                WriteObjectToFile(manager, filepath[1]);
                WriteObjectToFile(branch, filepath[2]);
                WriteObjectToFile(employees, filepath[3]);
                WriteObjectToFile(product, filepath[4]);
                WriteObjectToFile(orderItem, filepath[5]);
                WriteObjectToFile(orderLists, filepath[6]);
                
                // Terminates the program
                System.exit(0);
                break;
            default:
                errorMessage(1);
                break;
            }
        } while (true);
    }

    private static void WriteObjectToFile(Object obj, String filepath) {

        try {

            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            
            objectOut.writeObject(obj);
            objectOut.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Menu Options for employees
    private static void employeeMenuOptions(ArrayList<OrderItem> oi, ArrayList<OrderList> ols, ArrayList<Product> p) {
    	
        Scanner scan = new Scanner(System.in);
        
        String productCode;
        Employee employee = new Employee();

        int option;

        do {
        	System.out.println("===============");
            System.out.println("|Menu Options|");
            System.out.println("===============");
            System.out.println("1. Sales Order");
            System.out.println("2. Transaction History");
            System.out.println("3. Logout");
            System.out.print("> ");

            try {
                option = scan.nextInt();
                scan.nextLine();
            } catch (Exception e) {
                scan.nextLine();
                option = -1;
            }
            switch (option) {
            case 1:
                // Adds elements of ols array
                ols.add(new OrderList());
                do {
                    do {
                        System.out.print("\nEnter Item ID(1 to checkout, 2 to edit order list, 3 to display products)> ");
                        productCode = scan.nextLine();

                        // If the item list in order list is empty, it will show this output (Validate
                        // error)
                        if (("1".equals(productCode) || "2".equals(productCode))
                                && ols.get(ols.size() - 1).getItemCount() == 0) {
                            System.out.println("Please add some items!");
                        }
                    } while (("1".equals(productCode) || "2".equals(productCode))
                            && ols.get(ols.size() - 1).getItemCount() == 0);

                    // Modify the current element of ols array
                    ols.set(ols.size() - 1,
                            employee.modifyOrderList(productCode, oi, ols, ols.size() - 1));
                            
                } while (!"1".equals(productCode)); // Loop if user is not checking out

                // After checking out, it goes to payment
                employee.payment(ols.get(ols.size() - 1));
                
                //Update products details with order items details
                for(int i = 0; i < p.size(); i++){
                	
                    // p[i] = oi[i].getProduct(); 
                    p.set(i, oi.get(i).getProduct());
                }
                break;
                
            case 2:
                if (ols.size() != 0) {
                    employee.displayTransactionHistory(ols);
                } else {
                    System.out.println("No Transaction History!\n");
                }
                break;
                
            case 3:
                Logout eLogout = new Logout(); 
   				System.out.println("\n" + userName + " has logged out at " + eLogout.currentTime() + "\n");
                break;
            default:
                errorMessage(1);
                break;
            }
        } while (option != 3);
    }

    private static void managerMenuOptions(ArrayList<Product> p, ArrayList<Employee> employees, ArrayList<OrderItem> ois, ArrayList<OrderList> ols) {
        Scanner scan = new Scanner(System.in);
        Manager manager = new Manager();

        int menuOption;

        do {
        	System.out.println("===============");
            System.out.println("|Menu Options|");
            System.out.println("===============");
            System.out.println("1. Add or edit Product Detail");
            System.out.println("2. Add or edit Employee Detail");
            System.out.println("3. Daily Report");
            System.out.println("4. Logout");
            System.out.print("> ");

            try {
                menuOption = scan.nextInt();
            } catch (Exception e) {
                scan.nextLine();
                menuOption = -1;
            }
            switch (menuOption) {
            case 1:
                manager.modifyProduct(p, ois);
                for(int i = 0; i < p.size(); i++){
                    ois.get(i).setProduct(p.get(i));
                }
                break;
            case 2:
                manager.modifyStaff(employees);
                break;
            case 3:
                manager.dailyReport(ols, p);
                break;
            case 4:
            	Logout mLogout = new Logout(); 
            	System.out.println("\n" + userName + " has logged out at " + mLogout.currentTime() + "\n");
                break;
            default:
                errorMessage(1);
                break;
            }
        } while (menuOption != 4);
    }

	// Bootup Screen
    private static void bootupScreen() {
    	System.out.println("***********************************************************");
        System.out.println("* _                   _____                 _             *");
        System.out.println("*| |                 /  __ \\               (_)            *");
        System.out.println("*| |    _   _ _ __   | /  \\/ ___  _ __ ___  _ _ __   __ _ *");
        System.out.println("*| |   | | | | '_ \\  | |    / _ \\| '_ ` _ \\| | '_ \\ / _` |*");
        System.out.println("*| |___| |_| | | | | | \\__/\\ (_) | | | | | | | | | | (_| |*");
        System.out.println("*\\_____/\\__,_|_| |_|  \\____/\\___/|_| |_| |_|_|_| |_|\\__, |*");
        System.out.println("*                                                    __/ |*");
        System.out.println("*         Welcome to Lun Coming Karaoke !!!         |___/ *");
        System.out.println("***********************************************************");
        System.out.println("\n");

    }
    
    public static void errorMessage(int type){
    	switch (type){
    		case 1:
    			System.out.println("No such option, please enter again!\n");
    			break;
    		case 2:
    			System.out.println("Invalid order list number!\n");
    			break;
    		case 3:
    			System.out.println("Invalid quantity!\n");
    			break;
    		case 4:
    			System.out.println("Please enter a valid number\n");
    		    break;
    		case 5:
    			System.out.println("Invalid product ID.\n");
    			break;
    			
    		case 6:
    			System.out.println("Not a valid employee ID.\n");
    			break;
    			
    	}	 
    }
}