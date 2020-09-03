import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

class Manager extends PersonDetails implements Serializable {
	
	// Variables
    private static int nextManagerID = 1;
    private String managerID;
    private String password;
    private String jobTitle;

    public Manager() {
    }

    public Manager(PersonDetails personDetails, String password, String jobTitle) {
        super(personDetails);
        this.managerID = String.format("M%04d", nextManagerID);
        this.password = password;
        this.jobTitle = jobTitle;
    }

    // Add / Edit Products
    public boolean modifyProduct(ArrayList<Product> product, ArrayList<OrderItem> ois) {
    	
        Scanner input = new Scanner(System.in);
        
        boolean valid;
        String inputProductId;

        do {
        	
            // Prompt user to enter a product ID
            System.out.print("Enter a product ID: ");
            inputProductId = input.nextLine();

            // Validate the entered product ID
            if (inputProductId.length() != 5) {
                System.out.println("Not a valid product ID.\n");
                valid = false;
                
            } else if(inputProductId.charAt(0) != 'P') {
                System.out.println("Not a valid product ID.\n");
                valid = false;
                
            } else valid = true;
            
        } while (!valid);

        // Check if the product exists
        boolean productExist = false;
        int productIndex = -1;
        
        for (int i = 0; i < product.size(); i++) {
            if (inputProductId.equalsIgnoreCase(product.get(i).getProdId())) {
                productExist = true;
                productIndex = i;
            }
        }

        // If product exists then edit the product
        if (productExist) {
            if (editProduct(productIndex, product)) {
                System.out.println("Product edited successfully.\n");
                return true;
            } else {
                System.out.println("Product not edited.\n");
                return false;
            }
        }

        // If product does not exist, add product using the new given product ID
        else {
            if (addProduct(product, ois)) {
                System.out.println("Product added successfully.\n");
                return true;
            } else {
                System.out.println("Product not added.\n");
                return false;
            }
        }
    }

    // Sub-method for modifyProduct method, return true if product is edited successfully
    private boolean editProduct(int i, ArrayList<Product> product) {
    	
        Scanner input = new Scanner(System.in);
        
        int choice = -1;
        String stringBuf;
        double doubleBuf = 0;

        // Show all detail of the product
        System.out.println();
        System.out.format(
                "Product ID: %s\nProduct Name: %s\nProduct Type: %s\nStock Quantity: %d\nPrice: RM %,.2fea\n\n",
                product.get(i).getProdId(), product.get(i).getProdName(), product.get(i).getProdType(),
                product.get(i).getStockQuantity(), product.get(i).getPrice());

        do {
            // Prompt user to select a part to edit
            System.out.println("1. Product Name");
            System.out.println("2. Product Type");
            System.out.println("3. Stock Quantity");
            System.out.println("4. Product Price");
            choice = promptChoice(4);
        } while (choice < 1 || choice > 4);

        // Use a buffer to hold the new data
        stringBuf = "";

        switch (choice) {
            case 1:
                System.out.print("Enter the new product name: ");
                stringBuf = input.nextLine();
                break;
            case 2:
                System.out.print("Enter the new product type: ");
                stringBuf = input.nextLine();
                break;
            case 3:
                do {
                    System.out.print("Enter the new total quantity: ");
                    try {
                        doubleBuf = input.nextDouble();
                        break;
                    } catch (Exception e) {
                        System.out.println("\nPlease enter a digit.");
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
                        System.out.println("\nPlease enter a digit.");
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

		
        if (stringBuf != "") {
            stringBuf = Character.toUpperCase(stringBuf.charAt(0)) + stringBuf.substring(1);
        }

        if (confirmation == 'Y') {
            switch (choice) {
                case 1:
                    System.out.println();
                    product.get(i).setProdName(stringBuf);
                    break;
                case 2:
                    System.out.println();
                    product.get(i).setProdType(stringBuf);
                    break;
                case 3:
                    System.out.println();
                    product.get(i).setStockQuantity((int) doubleBuf);
                    break;
                case 4:
                    System.out.println();
                    product.get(i).setPrice((int) doubleBuf);
                    break;
                default:
                    System.out.println("Something went wrong.");
            }
            return true;
        }

        // Return failed to edit item if user rejected the confirmation
        else {
            return false;
        }
    }

    // Sub-method for modifyProduct method, return true if added successfully
    private boolean addProduct(ArrayList<Product> p, ArrayList<OrderItem> ois) {
        Scanner input = new Scanner(System.in);
        String prodName, prodType;
        int stockQuantity;
        double price;

        // Prompt user to enter the detail of the new product
        System.out.println("Product does not exist, adding new product.");
        System.out.println("Product ID: " + String.format("P%04d",Product.getNextProdId()));

        System.out.print("Product Name: ");
        prodName = input.nextLine();
        prodName = Character.toUpperCase(prodName.charAt(0)) + prodName.substring(1);

        System.out.print("Product Type: ");
        prodType = input.nextLine();
        prodType = Character.toUpperCase(prodType.charAt(0)) + prodType.substring(1);

        do {
            System.out.print("Stock Quantity: ");
            try {
                stockQuantity = input.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("\nPlease enter a digit.");
                input.nextLine();
            }
        } while (true);

        do {
            System.out.print("Single Price: ");
            try {
                price = input.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("\nPlease enter a digit.");
                input.nextLine();
            }
        } while (true);

        // Get confirmation from user then put the data into the database
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

        // Return failed to add item if user rejected the confirmation
        else {
            return false;
        }
    }

    // Add or edit employees...
    public boolean modifyStaff(ArrayList<Employee> e) {
    	
        boolean valid;
        String inputStaffId;
        
        Scanner input = new Scanner(System.in);

        do {
            // Prompt user to enter a product ID
            System.out.print("Enter an employee ID: ");
            inputStaffId = input.nextLine();

            // Validate the entered product ID
            if (inputStaffId.length() != 5) {
                System.out.println("Not a valid employee ID.\n");
                valid = false;
                
            } else if (inputStaffId.charAt(0) != 'S'){
                System.out.println("Not a valid employee ID.\n");
                valid = false;
                
            }else valid = true;
        } while (!valid);

        // Check if the employee exists
        boolean staffExist = false;
        int staffIndex = -1;
        
        for (int i = 0; i < e.size(); i++) {
            if (inputStaffId.equalsIgnoreCase(e.get(i).getEmployeeID())) {
                staffExist = true;
                staffIndex = i;
            }
        }

        // If employee exists then edit the employee
        if (staffExist) {
            if (editStaff(staffIndex, e)) {
                System.out.println("Staff edited successfully.\n");
                return true;
            } else {
                System.out.println("Staff not edited.\n");
                return false;
            }
        }

        // If employee does not exist, add employee using the next employee ID
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

    private boolean editStaff(int i, ArrayList<Employee> s) {
        Scanner input = new Scanner(System.in);
        int choice = -1;
        String stringBuf;

        // Show all details of the product
        System.out.println();
        System.out.format(
                "Staff ID: %s\nPassword: %s\nBranch: %s\nFirst Name: %s\nLast Name: %s\nGender: %s\nPhone No: %s\nEmail: %s\nIc No: %s\n",
                s.get(i).getEmployeeID(), s.get(i).getPassword(), s.get(i).getBranch().getBranchName(),
                s.get(i).getFirstName(), s.get(i).getLastName(), s.get(i).getGender(),
                s.get(i).getPhoneNo(), s.get(i).getEmail(), s.get(i).getIcNo());
        System.out.println();

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
        stringBuf = "";
        char charBuf = 'M';

        switch (choice) {
            case 1:
                System.out.print("Enter the new Password: ");
                stringBuf = input.nextLine();
                break;
            case 2:
                System.out.print("Enter the new First Name: ");
                stringBuf = input.nextLine();
                stringBuf = Character.toUpperCase(stringBuf.charAt(0)) + stringBuf.substring(1);
                break;
            case 3:
                System.out.print("Enter the new Last Name: ");
                stringBuf = input.nextLine();
                stringBuf = Character.toUpperCase(stringBuf.charAt(0)) + stringBuf.substring(1);
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
                    stringBuf = input.nextLine();
                } while (!validPhoneNo(stringBuf));
                break;
            case 6:
                do {
                    System.out.print("Enter the new Email: ");
                    stringBuf = input.nextLine();
                } while (!validEmail(stringBuf));
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

        if (confirm == 'Y') {
            switch (choice) {
                case 1:
                    System.out.println();
                    s.get(i).setPassword(stringBuf);
                    break;
                case 2:
                    System.out.println();
                    s.get(i).setFirstName(stringBuf);
                    break;
                case 3:
                    System.out.println();
                    s.get(i).setLastName(stringBuf);
                    break;
                case 4:
                    System.out.println();
                    s.get(i).setGender(charBuf);
                    break;
                case 5:
                    System.out.println();
                    s.get(i).setPhoneNo(stringBuf);
                    break;
                case 6:
                    System.out.println();
                    s.get(i).setEmail(stringBuf);
                    break;
                default:
                    System.out.println("Something went wrong.");
            }
            return true;
        }

        // Return failed to edit item if user rejected the confirmation
        else {
            return false;
        }
    }

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
            confirmation = input.next().toUpperCase().charAt(0);
            input.nextLine();
        } while (confirmation != 'Y' && confirmation != 'N');

        if (confirmation == 'Y') {
            s.add(new Employee(new PersonDetails(firstName, lastName, gender, phoneNo, email, icNo), jobTitle,
                    password, new Branch()));
            return true;
        }

        // Return failed to add item if user rejected the confirmation
        else {
            return false;
        }
    }

    public void dailyReport(ArrayList<OrderList> ol, ArrayList<Product> p) {
        int soldQuantity[] = new int[p.size()];

        for (int i = 0; i < p.size(); i++) {
            soldQuantity[i] = 0;
        }

        if (ol.size() < 1 ){
            System.out.println("No order is exist.\n");
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

        System.out.println("Daily Report");
        System.out.println("Date: " + ol.get(0).getFormattedDate());
        System.out.println("============================");
        System.out.printf("%-4s%-20s%-30s%-20s%-20s%-10s%-11s\n", "No.", "Product ID", "Product Name", "Stock Quantity",
                "Sold Out", "Unit Price", "Amount");
        System.out.println(
                "================================================================================================================");
        for (int i = 0; i < p.size(); i++) {
            System.out.format("%-4d%-20s%-30s%-20d%-20d%-10.2f%-11.2f\n", i + 1, p.get(i).getProdId(),
                    p.get(i).getProdName(), p.get(i).getStockQuantity(), soldQuantity[i],
                    p.get(i).getPrice(), p.get(i).getPrice()*soldQuantity[i]);
            totalAmount += (p.get(i).getPrice()*soldQuantity[i]);
        }
        System.out.printf("Total: %-17s%-30s%-20s%-20s%-10s%-10.2f\n", "","","","","",totalAmount);
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

    // Validation
    private boolean validGender(char input) {
        if (input == 'M' || input == 'F') {
            return true;
        } else {
            System.out.println("\nPlease enter \'M\' or \'F\'.");
            return false;
        }
    }

    private boolean validPhoneNo(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(0))) {
                System.out.println("\nPlease enter digit only.");
                return false;
            } else {
                return true;
            }
        }
        System.out.println("Something went wrong.");
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
