package user;

public class Users {
    
    public static void main(String[] args) {
    	
    	UserDetails[] userArray ={
    	new Manager("Lee","Jon Son",'M',"012-3718753","jonson@manager.com","010109-13-8471","M091132","jon88988","Inventory Manager"),
    	new Manager("Lim","Marvin",'M',"019-5451212","jonson@manager.com","001221-12-3124","M411123","oldma9912","Staffing Manager"),
        new Staff("Ling","Xin Lun",'M',"018-5566841","lingxl@staff.com","950512-13-0912","S568724","lingblackie20","Cashier"),
        new Staff("Wong","Ke Yi",'F',"012-6058794","keyiwong@staff.com","020923-14-0031","S547819","wky8811","Waitress"),
        new Staff("Tan","Hong",'M',"019-5488965","derricktan@staff.com","01010-18-1172","S895263","hong8821","Waiter"),
        new Staff("Ong","Jia Sin",'F',"011-5556632","ongjs@staff.com","011231-18-3311","S035204","jsong4562","Cleaner")
        };
    }
}
