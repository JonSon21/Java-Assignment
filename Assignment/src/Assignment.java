import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class Assignment{

    // Use Array to store username for logout
    private static String[] userName = new String[999];

    public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		
        // Initialize variables and objects
        int staffOrManager;
        int count = 0;
        boolean loginSuccess;
        String username = null;
        String password = null;
        
        // Use ArrayList because it is mutable
        Manager manager = new Manager();
        Branch branch = new Branch();
        
        ArrayList<UserDetails> person = new ArrayList<>();
        ArrayList<Staff> staff = new ArrayList<>();
        ArrayList<Product> product = new ArrayList<>();
        ArrayList<OrderItem> orderItem = new ArrayList<>();
        ArrayList<OrderList> orderLists = new ArrayList<>();

        // Initialize all filepath
        String[] filepath = new String[7];
        filepath[0] = "../obj/person.bin";
        filepath[1] = "../obj/manager.bin";
        filepath[2] = "../obj/branch.bin";
        filepath[3] = "../obj/staff.bin";
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
            
            person = (ArrayList<UserDetails>) ois[0].readObject();
            manager = (Manager) ois[1].readObject();
            branch = (Branch) ois[2].readObject();
            staff = (ArrayList<Staff>) ois[3].readObject();
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
        Staff.setNextStaffID(staff.size() + 1);
        Product.setNextProductId(product.size() + 1);

        bootupScreen();

        do {
            System.out.println("Employee or Manager");
            System.out.println("-------------------");
            System.out.println("1. Employee");
            System.out.println("2. Manager");
            System.out.println("0. Exit and update files");
            System.out.print("> ");

            try {
                staffOrManager = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                scanner.nextLine();
                staffOrManager = -1;
            }

            if (staffOrManager == 1 || staffOrManager == 2) {
                System.out.println("Login Page");
                System.out.println("----------");
                System.out.print("Username: ");
                username = scanner.nextLine();
                count++;

                System.out.print("Password: ");
                password = scanner.nextLine();
            }

            for (int i = 0; i < count; i++) {
                userName[i] = username;
            }

            switch (staffOrManager) {
            case 1:
                Login stLogin = new Login(username, password);
                loginSuccess = stLogin.staffLogin(staff);
                
                if (loginSuccess) {
                    System.out.println("Log in successful.\n");
                    System.out.println("Username: " + stLogin.getUsername() + "\nEmployee Name: "
                            + staff.get(stLogin.getIndex()).getFirstName() + " "
                            + staff.get(stLogin.getIndex()).getLastName());
                    System.out.println(stLogin.getUsername() + " has login at " + stLogin.currentTime() + "\n");
                    staffMenuOptions(orderItem, orderLists, product);
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
                            + staff.get(mngrLogin.getIndex()).getFirstName() + " "
                            + staff.get(mngrLogin.getIndex()).getLastName());
                    managerMenuOptions(product, staff, orderItem, orderLists);
                } 
                	
                else {
                    System.out.println("Login failed.\n");
                }
                break;
                
            case 0:
            	
                // Update all data into files
                WriteObjectToFile(person, filepath[0]);
                WriteObjectToFile(manager, filepath[1]);
                WriteObjectToFile(branch, filepath[2]);
                WriteObjectToFile(staff, filepath[3]);
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
    	
    	Scanner scan = new Scanner(System.in);

        try {

            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Staff Menu
    private static void staffMenuOptions(ArrayList<OrderItem> orderItem, ArrayList<OrderList> orderLists, ArrayList<Product> products) {
		
		Scanner scanner = new Scanner(System.in);
        String productCode;
        Staff staff = new Staff();

        int menuOption;
        int count = 0;
		
        do {
            System.out.println("Menu Options");
            System.out.println("------------");
            System.out.println("1. Sales Order");
            System.out.println("2. Transaction History");
            System.out.println("3. Logout");
            System.out.print("> ");

            try {
                menuOption = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                scanner.nextLine();
                menuOption = -1;
            }
            
            switch (menuOption) {
            case 1:
                // Adds elements of multipleOrderLists array
                orderLists.add(new OrderList());
                do {
                    do {
                        System.out.print("\nEnter Item ID(1 to checkout, 2 to edit order list, 3 to display products)> ");
                        productCode = scanner.nextLine();

                        // If the item list in order list is empty, it will show this output 
                        // (Validate error)
                        
                        if (("1".equals(productCode) || "2".equals(productCode))
                                && orderLists.get(orderLists.size() - 1).getItemCount() == 0) {
                            System.out.println("Please add at least an item!");
                        }
                    } while (("1".equals(productCode) || "2".equals(productCode))
                            && orderLists.get(orderLists.size() - 1).getItemCount() == 0);

                    // Modify the current element of multipleOrderLists array
                    orderLists.set(orderLists.size() - 1,
                            staff.modifyOrderList(productCode, orderItem, orderLists, orderLists.size() - 1));
                            
                } while (!"1".equals(productCode)); // Loop if user is not checking out

                // After checking out, it goes to payment
                staff.payment(orderLists.get(orderLists.size() - 1));
                
                //Update products details with order items details
                for(int i = 0; i < products.size(); i++){
                	
                //Equivalent to products[i] = orderItem[i].getProduct();
                products.set(i, orderItem.get(i).getProduct());
                }
                break;
                
            case 2:
                if (orderLists.size() != 0) {
                    staff.displayTransactionHistory(orderLists);
                } else {
                    System.out.println("No Transaction History!\n");
                }
                break;
                
            case 3:
                Logout stLogout = new Logout();
                count++;
                for(int i = 0; i < count; i++) {
                    System.out.println("\n" + userName[i] + " has logged out at " + stLogout.currentTime() + "\n");
                }
                break;
            default:
                System.out.println("No such option!\n");
                break;
            }
        } while (menuOption != 3);
    }

    private static void managerMenuOptions(ArrayList<Product> products, ArrayList<Staff> staff, ArrayList<OrderItem> orderItems,
            ArrayList<OrderList> orderLists) {
        
        Scanner scanner = new Scanner(System.in);
        
        Manager manager = new Manager();
        int menuOption;

        do {
            System.out.println("Menu Options");
            System.out.println("------------");
            System.out.println("1. Add or edit Product Detail");
            System.out.println("2. Add or edit Employee Detail");
            System.out.println("3. Daily Report");
            System.out.println("4. Logout");
            System.out.print("> ");

            try {
                menuOption = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();
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
                manager.modifyStaff(staff);
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
                System.out.println(" _                   _____                 _             ");
        System.out.println("| |                 /  __ \\               (_)            ");
        System.out.println("| |    _   _ _ __   | /  \\/ ___  _ __ ___  _ _ __   __ _ ");
        System.out.println("| |   | | | | '_ \\  | |    / _ \\| '_ ` _ \\| | '_ \\ / _` |");
        System.out.println("| |___| |_| | | | | | \\__/\\ (_) | | | | | | | | | | (_| |");
        System.out.println("\\_____/\\__,_|_| |_|  \\____/\\___/|_| |_| |_|_|_| |_|\\__, |");
        System.out.println("                                                    __/ |");
        System.out.println("                                                   |___/ ");
        System.out.println("Welcome to Lun Coming Karaoke !!!\n");


    }
}