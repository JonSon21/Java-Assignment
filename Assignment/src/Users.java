package user;

import java.util.ArrayList;
import java.util.Scanner;


public class Users {
    
    public static void main(String[] args){
    
        ArrayList<Manager> manager = new ArrayList<>();
        manager.add(new Manager("Lee","Jon Son",'M',"012-3718753","jonson@manager.com","010109-13-8471","M091132","jon88988","Inventory Manager"));
        manager.add(new Manager("Lim","Marvin",'M',"019-5451212","jonson@manager.com","001221-12-3124","M411123","oldma9912","Staffing Manager"));
        
        ArrayList<Staff> staff = new ArrayList<Staff>();
        staff.add(new Staff("Ling","Xin Lun",'M',"018-5566841","lingxl@staff.com","950512-13-0912","S568724","lingblackie20","Cashier"));
        staff.add(new Staff("Wong","Ke Yi",'F',"012-6058794","keyiwong@staff.com","020923-14-0031","S547819","wky8811","Waitress"));
        staff.add(new Staff("Tan","Hong",'M',"019-5488965","derricktan@staff.com","01010-18-1172","S895263","hong8821","Waiter"));
        staff.add(new Staff("Ong","Jia Sin",'F',"011-5556632","ongjs@staff.com","011231-18-3311","S035204","jsong4562","Cleaner"));
        
        managerMenuOptions(staff);
    }
    
    private static void managerMenuOptions(ArrayList<Staff> staff) {
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
                //manager.modifyProduct(products, orderItems);
                //for(int i = 0; i < products.size(); i++){
                //    orderItems.get(i).setProduct(products.get(i));
                //}
                break;
            case 2:
                manager.modifyStaff(staff);
                break;
            case 3:
                //manager.dailyReport(orderLists, products);
                break;
            case 4:
                break;
            default:
                System.out.println("No such option!\n");
                break;
            }
        } while (menuOption != 4);
    }
}
    
                
