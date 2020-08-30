package user;

import java.util.ArrayList;


public class Users {
    
    public static void main(String[] args) {
    	
    	Manager m1 = new Manager("Lee","Jon Son",'M',"012-3718753","jonson@manager.com","010109-13-8471","M091132","jon88988","Inventory Manager");
    	Manager m2 = new Manager("Lim","Marvin",'M',"019-5451212","jonson@manager.com","001221-12-3124","M411123","oldma9912","Staffing Manager");
        Staff s1 = new Staff("Ling","Xin Lun",'M',"018-5566841","lingxl@staff.com","950512-13-0912","S568724","lingblackie20","Cashier");
        Staff s2 = new Staff("Wong","Ke Yi",'F',"012-6058794","keyiwong@staff.com","020923-14-0031","S547819","wky8811","Waitress");
        Staff s3 = new Staff("Tan","Hong",'M',"019-5488965","derricktan@staff.com","01010-18-1172","S895263","hong8821","Waiter");
        Staff s4 = new Staff("Ong","Jia Sin",'F',"011-5556632","ongjs@staff.com","011231-18-3311","S035204","jsong4562","Cleaner");

        ArrayList<Manager> manager = new ArrayList<Manager>();
        manager.add(m1);
        manager.add(m2);
        
        ArrayList<Staff> staff = new ArrayList<Staff>();
        staff.add(s1);
        staff.add(s2);
        staff.add(s3);
        staff.add(s4);
        
    }
}
