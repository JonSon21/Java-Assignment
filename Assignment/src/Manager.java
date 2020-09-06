import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

class Manager extends PersonDetails implements Serializable {
	
	// Variables
    private String managerID;
    private String password;
    private String jobTitle;
    
    // Counting the managers starting from 1
    // This is also to automatically set the manager ID
    private static int nextManagerID = 1;

    public Manager() {
    }

	// Constructor
    public Manager(PersonDetails personDetails, String password, String jobTitle) {
        super(personDetails);
        this.managerID = String.format("M%04d", nextManagerID++);
        this.password = password;
        this.jobTitle = jobTitle;
    }

    // Add / Edit Products
    public boolean modifyProduct(ArrayList<Product> p, ArrayList<OrderItem> ois) {
    	
    	Main main = new Main();
        Scanner input = new Scanner(System.in);
        
        boolean validProductId = false;
        String inputProductId;

        do {
        	
            // Prompt user to enter a product ID
            System.out.print("Enter a product ID: ");
            inputProductId = input.nextLine();

            // Validate the entered product ID
            if (inputProductId.length() != 5 || inputProductId.charAt(0) != 'P') {
                main.errorMessage(5);
                validProductId = false;
                
            } else validProductId = true;
            
        } while (!validProductId);

        // Check if the product exists within the files
        boolean productExist = false;
        int productIndex = -1;
        
        for (int i = 0; i < p.size(); i++) {
            if (inputProductId.equalsIgnoreCase(p.get(i).getProdId())) {
                productExist = true;
                productIndex = i;
            }
        }

        // If product exists then edit the product
        if (productExist) {
            if (editProduct(productIndex, p)) {
                System.out.println("Product edited successfully.\n");
                return true;
            } else {
                System.out.println("Failed to edit product.\n");
                return false;
            }
        }

        // If product does not exist, add product using the new given product ID
        else {
            if (addProduct(p, ois)) {
                System.out.println("Product added successfully.\n");
                return true;
            } else {
                System.out.println("Failed to add product.\n");
                return false;
            }
        }
    }

    // Child method for modifyProduct method, return true if product is edited successfully
    private boolean editProduct(int i, ArrayList<Product> p) {
    	
    	Main main = new Main();
        Scanner input = new Scanner(System.in);
        
        int choice = -1;
        String strBuf = "";
        double doubleBuf = 0;

        // Show all details of the product
        System.out.println();
        System.out.format(
                "Product ID: %s\nRoom/Product Name: %s\nPax: %s\nQuantity: %d\nPrice: RM %,.2fea\n\n",
                p.get(i).getProdId(), p.get(i).getProdName(), p.get(i).getProdType(),
                p.get(i).getStockQuantity(), p.get(i).getPrice());

        do {
            // Prompt user to select a part to edit
            System.out.println("1. Room/Product Name");
            System.out.println("2. Pax");
            System.out.println("3. Quantity");
            System.out.println("4. Product Price");
            choice = promptChoice(4);
        } while (choice < 1 || choice > 4);

        // Use a buffer to hold the new data

        switch (choice) {
            case 1:
                System.out.print("Enter the new product name: ");
                strBuf = input.nextLine();
                break;
            case 2:
                System.out.print("Enter the new product type: ");
                strBuf = input.nextLine();
                break;
            case 3:
                do {
                    System.out.print("Enter the new total quantity: ");
                    try {
                        doubleBuf = input.nextDouble();
                        break;
                    } catch (Exception e) {
                        main.errorMessage(4);
                        input.nextLine();
                    }
                } while (true);
                break;
            case 4:
                do {
                    System.out.print("Enter the new product price: RM ");
                    try {
                        doubleBuf = input.nextDouble();
                        break;
                    } catch (Exception e) {
                        main.errorMessage(4);
                        input.nextLine();
                    }
                } while (true);
                break;
            default:
                break;
        }

        // Get confirmation from user then write the data into the binary files
        char confirmation;
        
        do {
            System.out.print("Do you really want to edit the detail? (Y/N): ");
            confirmation = input.next().toUpperCase().charAt(0);
            input.nextLine();
        } while (confirmation != 'Y' && confirmation != 'N');

		
        if (strBuf != "") {
            strBuf = Character.toUpperCase(strBuf.charAt(0)) + strBuf.substring(1);
        }

        if (confirmation == 'Y') {
            switch (choice) {
                case 1:
                    System.out.println();
                    p.get(i).setProdName(strBuf);
                    break;
                case 2:
                    System.out.println();
                    p.get(i).setProdType(strBuf);
                    break;
                case 3:
                    System.out.println();
                    p.get(i).setStockQuantity((int) doubleBuf);
                    break;
                case 4:
                    System.out.println();
                    p.get(i).setPrice((int) doubleBuf);
                    break;
                default:
                    System.out.println("Something went wrong.");
            }
            return true;
        }

        // Show "Failed to edit item" if user rejected the confirmation
        else {
            return false;
        }
    }

    // Sub-method for modifyProduct method, return true if added successfully
    private boolean addProduct(ArrayList<Product> p, ArrayList<OrderItem> ois) {
    	
    	Main main = new Main();
        Scanner input = new Scanner(System.in);
        
        String prodName, prodType;
        int stockQuantity;
        double price;

        // Prompt user to enter the details of a new product
        System.out.println("Product does not exist, adding a new product.");
        System.out.println("Product ID: " + String.format("P%04d",Product.getNextProdId()));

        System.out.print("Product Name: ");
        prodName = input.nextLine();
        prodName = Character.toUpperCase(prodName.charAt(0)) + prodName.substring(1);

        System.out.print("Product Type: ");
        prodType = input.nextLine();
        prodType = Character.toUpperCase(prodType.charAt(0)) + prodType.substring(1);

        do {
            System.out.print("Total Quantity: ");
            try {
                stockQuantity = input.nextInt();
                break;
            } catch (Exception e) {
                main.errorMessage(4);
                input.nextLine();
            }
        } while (true);

        do {
            System.out.print("Single Price: ");
            try {
                price = input.nextDouble();
                break;
            } catch (Exception e) {
                main.errorMessage(4);
                input.nextLine();
            }
        } while (true);

        // Get confirmation from user then write the data into the files
        char confirmation;
        do {
            System.out.print("Do you really want to add the product? (Y/N): ");
            confirmation = input.next().toUpperCase().charAt(0);
            input.nextLine();
        } while (confirmation != 'Y' && confirmation != 'N');

        if (confirmation == 'Y') {
            p.add(new Product(prodName, prodType, price, stockQuantity));
            ois.add(new OrderItem(p.get(p.size() - 1)));
            return true;
        }

        // Show "Failed to add item" if user rejected the confirmation 
        else {
            return false;
        }
    }

    // Modify the Staff List (Add and Edit)
    public boolean modifyStaff(ArrayList<Employee> e) {
    	
    	Main main = new Main();
    	
        boolean valid;
        String inputStaffId;
        
        Scanner input = new Scanner(System.in);

        do {
            // Prompt user to enter a product ID
            System.out.print("Enter an employee ID: ");
            inputStaffId = input.nextLine();

            // Validate the entered product ID
            if (inputStaffId.length() != 5) {
                main.errorMessage(6);
                valid = false;
                
            } else if (inputStaffId.charAt(0) != 'S'){
                main.errorMessage(6);
                valid = false;
                
            }else valid = true;
        } while (!valid);

        // Check if the employee exists within the files
        boolean staffExist = false;
        int staffIndex = -1;
        
        for (int i = 0; i < e.size(); i++) {
            if (inputStaffId.equalsIgnoreCase(e.get(i).getEmployeeID())) {
                staffExist = true;
                staffIndex = i;
            }
        }

        // If employee exists then edit the employee details
        if (staffExist) {
            if (editStaff(staffIndex, e)) {
                System.out.println("Staff edited successfully.\n");
                return true;
            } else {
                System.out.println("Staff not edited.\n");
                return false;
            }
        }

        // If employee does not exist, add a new employee with new details
        else {
            if (addStaff(e)) {
                System.out.println("Staff added successfully.\n");
                return true;
            } else {
                System.out.println("Staff not added.\n");
                return false;
            }
        }
    }

	// Edit Staff
    private boolean editStaff(int i, ArrayList<Employee> s) {
    	
        Scanner input = new Scanner(System.in);
        int choice = -1;
        String strBuf;

        // Show all details of the staff
        System.out.println();
        System.out.println(s.get(i).toString());

        // Prompt user to select which part to be edited
        do {
            System.out.println("1. Password");
            System.out.println("2. First Name");
            System.out.println("3. Last Name");
            System.out.println("4. Gender");
            System.out.println("5. Phone No.");
            System.out.println("6. Email");
            choice = promptChoice(6);
        } while (choice < 1 || choice > 6);

        // Use buffers to hold the new data
        strBuf = "";
        char charBuf = 'M';

        switch (choice) {
            case 1:
                System.out.print("Enter the new Password: ");
                strBuf = input.nextLine();
                break;
            case 2:
                System.out.print("Enter the new First Name: ");
                strBuf = input.nextLine();
                strBuf = Character.toUpperCase(strBuf.charAt(0)) + strBuf.substring(1);
                break;
            case 3:
                System.out.print("Enter the new Last Name: ");
                strBuf = input.nextLine();
                strBuf = Character.toUpperCase(strBuf.charAt(0)) + strBuf.substring(1);
                break;
            case 4:
                do {
                    System.out.print("Enter the new Gender (M/F) : ");
                    charBuf = input.next().charAt(0);
                } while (!validGender(charBuf));
                break;
            case 5:
                do {
                    System.out.print("Enter the new Phone No. (0123456789): ");
                    strBuf = input.nextLine();
                } while (!validPhoneNo(strBuf));
                break;
            case 6:
                do {
                    System.out.print("Enter the new Email: ");
                    strBuf = input.nextLine();
                } while (!validEmail(strBuf));
                break;
            default:
                break;
        }

        // Get confirmation from user then put the data into the database
        char confirm;
        do {
            System.out.print("Do you really want to edit the detail? (Y/N): ");
            confirm = input.next().toUpperCase().charAt(0);
            input.nextLine();
        } while (confirm != 'Y' && confirm != 'N');

        System.out.println();
        if (confirm == 'Y') {
            switch (choice) {
                case 1:
                    s.get(i).setPassword(strBuf);
                    break;
                case 2:
                    s.get(i).setFirstName(strBuf);
                    break;
                case 3:
                    s.get(i).setLastName(strBuf);
                    break;
                case 4:
                    s.get(i).setGender(charBuf);
                    break;
                case 5:
                    s.get(i).setPhoneNo(strBuf);
                    break;
                case 6:
                    s.get(i).setEmail(strBuf);
                    break;
                default:
                    System.out.println("Something went wrong.");
            }
            return true;
        }

        // Show "Failed to edit item" if user rejected the confirmation
        else {
            return false;
        }
    }
	
	// Add Staff
    private boolean addStaff(ArrayList<Employee> s) {
        Scanner input = new Scanner(System.in);
        String password, firstName, lastName, phoneNo, email, icNo, jobTitle;
        char gender;

        // Prompt user to enter the detail of the new employee
        System.out.println("Staff does not exist, adding new staff.");
        System.out.println("Staff ID: " + String.format("S%04d", s.size()+1));

        System.out.print("First Name: ");
        firstName = input.nextLine();
        firstName = Character.toUpperCase(firstName.charAt(0)) + firstName.substring(1);

        System.out.print("Last Name: ");
        lastName = input.nextLine();
        lastName = Character.toUpperCase(lastName.charAt(0)) + lastName.substring(1);

        do {
            System.out.print("Phone No.: ");
            phoneNo = input.nextLine();
        } while (!validPhoneNo(phoneNo));

        do {
            System.out.print("Email: ");
            email = input.nextLine();
        } while (!validEmail(email));

        System.out.print("IC No.: ");
        icNo = input.nextLine();

        do {
            System.out.print("Gender (M/F): ");
            gender = input.nextLine().charAt(0);
        } while (!validGender(gender));

        System.out.print("Password: ");
        password = input.nextLine();

        System.out.print("Job Title: ");
        jobTitle = input.nextLine();
        jobTitle = Character.toUpperCase(jobTitle.charAt(0)) + jobTitle.substring(1);

        // Get confirmation from user then put the data into the binary files
        char confirmation;
        do {
            System.out.print("Do you really want to add the staff? (Y/N): ");
            confirmation = input.next().charAt(0);
            confirmation = Character.toUpperCase(confirmation);
            input.nextLine();
        } while (confirmation != 'Y' && confirmation != 'N');

        if (confirmation == 'Y') {
            s.add(new Employee(new PersonDetails(firstName, lastName, gender, phoneNo, email, icNo), jobTitle,
                    password, new Branch()));
            return true;
        }

        // Show "Failed to add item" if user rejected the confirmation
        else {
            return false;
        }
    }
	
	// Printing of the Daily Report
    public void dailyReport(ArrayList<OrderList> ol, ArrayList<Product> p) {
        int soldQuantity[] = new int[p.size()];

        for (int i = 0; i < p.size(); i++) {
            soldQuantity[i] = 0;
        }

        if (ol.size() < 1 ){
            System.out.println("No existing orders.\n");
            return;
        }

        for (int i = 0; i < p.size(); i++) {
            for (int j = 0; j < ol.size(); j++) {
                for (int k = 0; k < ol.get(j).getOrderItem().size(); k++) {
                    if (ol.get(j).getOrderItem().get(k).getProduct().getProdId()
                            .equals(p.get(i).getProdId())) {
                        soldQuantity[i] += ol.get(j).getOrderItem().get(k).getQuantity();
                    }
                }
            }
        }

        double totalAmount = 0;

		System.out.println("============");
        System.out.println("Daily Report");
        System.out.println("Date: " + ol.get(0).getFormattedDate());
        System.out.println("============");
        System.out.printf("%-4s%-20s%-30s%-20s%-20s%-12s%11s\n", "No.", "Product ID", "Product Name", "Stock Quantity",
                "Sold Out", "Unit Price", "Amount");
        System.out.println("=====================================================================================================================");
        for (int i = 0; i < p.size(); i++) {
            System.out.format("%-4d%-20s%-30s%-20d%-20d%-12.2f%11.2f\n", i + 1, p.get(i).getProdId(),
                    p.get(i).getProdName(), p.get(i).getStockQuantity(), soldQuantity[i],
                    p.get(i).getPrice(), p.get(i).getPrice()*soldQuantity[i]);
            totalAmount += (p.get(i).getPrice()*soldQuantity[i]);
        }
        System.out.println("=====================================================================================================================");
        System.out.printf("Total: %110.2f\n",totalAmount);
        System.out.println("=====================================================================================================================");
    }

    // Getter
    public String getManagerID() {
        return managerID;
    }

    public String getPassword() {
        return password;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public static int getNextManagerID() {
        return nextManagerID;
    }

    // Setter
    public void setPassword(String password) {
        this.password = password;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "Manager{" + "managerID='" + managerID + '\'' + ", password='" + password + '\'' + ", jobTitle='"
                + jobTitle + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", gender="
                + gender + ", phoneNo='" + phoneNo + '\'' + ", email='" + email + '\'' + ", icNo='" + icNo + '\'' + '}';
    }

    // This is to check if the gender is correct
    // Only 'M' and 'F' can be entered
    private boolean validGender(char input) {
        if (input == 'M' || input == 'F') {
            return true;
        } else {
            System.out.println("\nPlease enter \'M\' or \'F\'.");
            return false;
        }
    }
	
	// This is to check if phone number only has numbers 
	// Only numbers are needed, symbols like dashes are not needed
    private boolean validPhoneNo(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(0))) {
                System.out.println("\nPlease enter numbers only. Dashes are not needed.");
                return false;
            } else {
                return true;
            }
        }
        System.out.println("Something went wrong.");
        return false;
    }

	// This is to check if the format of the email is correct
	// Email must contain '@' and '.'
    private boolean validEmail(String input) {
        int aCounter = 0;
        int dotCounter = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '@') {
                aCounter++;
            }
            if (input.charAt(i) == '.') {
                dotCounter++;
            }
        }
        if (aCounter != 1 || dotCounter != 1) {
            System.out.println("\nPlease enter a valid email (address@domain.com).");
            return false;
        } else {
            return true;
        }
    }

	// This is to check if there's any exceptions 
	// When user enters anything other than a number into the choice field
    private int promptChoice(int m){
        int choice = -1;
        boolean valid = false;
        Scanner input = new Scanner(System.in);

        do {
            System.out.print("Enter a number: ");
            try {
                choice = input.nextInt();
                input.nextLine();
                if(choice > m || choice < 1){
                    System.out.println("\nPlease enter a number between 1 and " + m + ".");
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
