import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.io.IOException;

public class Main {
    // Use Array to store username for logout
    private static String[] userName = new String[999];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Initialize variables and objects
        int employeeOrManager;
        int count = 0;
        
        boolean loginSuccess;
        
        

        
        String username = null;
        String password = null;
        
        Manager manager = new Manager();
        Branch branch = new Branch();
        
   		// Use ArrayList because it is mutable
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
            
            person = (ArrayList<PersonDetails>) ois[0].readObject();
            manager = (Manager) ois[1].readObject();
            branch = (Branch) ois[2].readObject();
            employees = (ArrayList<Employee>) ois[3].readObject();
            product = (ArrayList<Product>) ois[4].readObject();
            orderItem = (ArrayList<OrderItem>) ois[5].readObject();
            orderLists = (ArrayList<OrderList>) ois[6].readObject();
            
            for (int i = 0; i < filepath.length; i++) {
                ois[i].close();
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        //Reassign static value
        OrderList.setNextOrderNo(orderLists.size() + 1);
        Employee.setNextEmployeeID(employees.size() + 1);
        Product.setNextProdId(product.size() + 1);

        bootupScreen();

        do {
        	System.out.println("===================");
            System.out.println("Employee or Manager");
            System.out.println("===================");
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
            	
           		
         		System.out.println("==========");
                System.out.println("Login Page");
                System.out.println("==========");
                System.out.print("Username: ");
                username = scan.nextLine();
                count++;

                System.out.print("Password: ");
                password = scan.nextLine();
            }

            for (int i = 0; i < count; i++) {
                userName[i] = username;
            }

            switch (employeeOrManager) {
            case 1:
                Login eLogin = new Login(username, password);
                loginSuccess = eLogin.employeeLogin(employees);
                if (loginSuccess) {
                    System.out.println("Log in successful.\n");
                    System.out.println("Username: " + eLogin.getUsername() + "\nEmployee Name: "
                            + employees.get(eLogin.getIndex()).getFirstName() + " "
                            + employees.get(eLogin.getIndex()).getLastName());
                    System.out.println(eLogin.getUsername() + " has login at " + eLogin.currentTime() + "\n");
                    employeeMenuOptions(orderItem, orderLists, product);
                } else {
                    System.out.println("Login failed.\n");
                }
                break;
            case 2:
                Login mngrLogin = new Login(username, password);
                loginSuccess = mngrLogin.managerLogin(manager);
                if (loginSuccess) {
                    System.out.println("Log in successful.\n");
                    System.out.println("Username: " + mngrLogin.getUsername() + "\nManager Name: "
                            + employees.get(mngrLogin.getIndex()).getFirstName() + " "
                            + employees.get(mngrLogin.getIndex()).getLastName());
                    managerMenuOptions(product, employees, orderItem, orderLists);
                } else {
                    System.out.println("Login failed.\n");
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
                System.out.println("No such option!\n");
                break;
            }
        } while (true);
    }

    private static void WriteObjectToFile(Object serObj, String filepath) {

        try {

            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Menu Options for employees
    private static void employeeMenuOptions(ArrayList<OrderItem> orderItem, ArrayList<OrderList> orderLists, ArrayList<Product> products) {
        Scanner scan = new Scanner(System.in);
        String productCode;
        Employee employee = new Employee();

        int menuOption;
        int count = 0;

        do {
        	System.out.println("============");
            System.out.println("Menu Options");
            System.out.println("============");
            System.out.println("1. Sales Order");
            System.out.println("2. Transaction History");
            System.out.println("3. Logout");
            System.out.print("> ");

            try {
                menuOption = scan.nextInt();
                scan.nextLine();
            } catch (Exception e) {
                scan.nextLine();
                menuOption = -1;
            }
            switch (menuOption) {
            case 1:
                // Adds elements of multipleOrderLists array
                orderLists.add(new OrderList());
                do {
                    do {
                        System.out.print("\nEnter Item ID(1 to checkout, 2 to edit order list, 3 to display products)> ");
                        productCode = scan.nextLine();

                        // If the item list in order list is empty, it will show this output (Validate
                        // error)
                        if (("1".equals(productCode) || "2".equals(productCode))
                                && orderLists.get(orderLists.size() - 1).getItemCount() == 0) {
                            System.out.println("Please add some items!");
                        }
                    } while (("1".equals(productCode) || "2".equals(productCode))
                            && orderLists.get(orderLists.size() - 1).getItemCount() == 0);

                    // Modify the current element of multipleOrderLists array
                    orderLists.set(orderLists.size() - 1,
                            employee.modifyOrderList(productCode, orderItem, orderLists, orderLists.size() - 1));
                            
                } while (!"1".equals(productCode)); // Loop if user is not checking out

                // After checking out, it goes to payment
                employee.payment(orderLists.get(orderLists.size() - 1));
                
                //Update products details with order items details
                for(int i = 0; i < products.size(); i++){
                	
                    //Equivalent to products[i] = orderItem[i].getProduct();
                    products.set(i, orderItem.get(i).getProduct());
                }
                break;
                
            case 2:
                if (orderLists.size() != 0) {
                    employee.displayTransactionHistory(orderLists);
                } else {
                    System.out.println("No Transaction History!\n");
                }
                break;
                
            case 3:
                Logout empLogout = new Logout();
                count++;
                for(int i = 0; i < count; i++) {
                    System.out.println("\n" + userName[i] + " has logged out at " + empLogout.currentTime() + "\n");
                }
                break;
            default:
                System.out.println("No such option!\n");
                break;
            }
        } while (menuOption != 3);
    }

    private static void managerMenuOptions(ArrayList<Product> products, ArrayList<Employee> employees, ArrayList<OrderItem> orderItems,
            ArrayList<OrderList> orderLists) {
        Scanner scan = new Scanner(System.in);
        Manager manager = new Manager();

        int menuOption;

        do {
        	System.out.println("============");
            System.out.println("Menu Options");
            System.out.println("============");
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
                manager.modifyProduct(products, orderItems);
                for(int i = 0; i < products.size(); i++){
                    orderItems.get(i).setProduct(products.get(i));
                }
                break;
            case 2:
                manager.modifyStaff(employees);
                break;
            case 3:
                manager.dailyReport(orderLists, products);
                break;
            case 4:
                break;
            default:
                System.out.println("No such option!\n");
                break;
            }
        } while (menuOption != 4);
    }

    private static void bootupScreen() {
    	System.out.println("***********************************************************");
        System.out.println("* _                   _____                 _             *");
        System.out.println("*| |                 /  __ \\               (_)            *");
        System.out.println("*| |    _   _ _ __   | /  \\/ ___  _ __ ___  _ _ __   __ _ *");
        System.out.println("*| |   | | | | '_ \\  | |    / _ \\| '_ ` _ \\| | '_ \\ / _` |*");
        System.out.println("*| |___| |_| | | | | | \\__/\\ (_) | | | | | | | | | | (_| |*");
        System.out.println("*\\_____/\\__,_|_| |_|  \\____/\\___/|_| |_| |_|_|_| |_|\\__, |*");
        System.out.println("*                                                    __/ |*");
        System.out.println("*                                                   |___/ *");
        System.out.println("***********************************************************");
        System.out.println("Welcome to Lun Coming Karaoke !!!\n");

    }
    
      
    
   


}