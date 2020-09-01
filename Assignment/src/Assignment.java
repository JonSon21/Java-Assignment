    package user;
    import java.util.ArrayList;
	import java.util.Scanner;
	
	class Assignment {
	
	    private OrderList orderList = new OrderList; // Get OrderList
	    private OrderItem orderItem = new OrderItem; // Choose OrderItem
	    
	    private ArrayList<OrderList> orderListList = new ArrayList<OrderList>();
	    private listCount = 0;
	    
	    private Product product; // Choose Product
	   
	   	int list; 
	    
	     private int staffMenu(){
	        System.out.println("---STAFF MENU---");
	        System.out.println("1. Sales Menu");
	        System.out.println("2. Transaction History");
	        System.out.println("0. Exit");
	        int choice = readInt(0, 3);
	        return choice;
	    }
	    
	    public void staffStart(){
	        while (true){
	            int choice = menu();
	            switch (choice){
	                case 0:
	                    System.exit(0);
	                    break;
	                case 1:
	                    salesMenu();
	                    break;
	                case 2: 
	                    displayTransactionHistory();
	                    break;
	                default:
	                    throw new AssertionError();
	            }
	        }
	    }
	    
	    public void salesMenu(){
	    	System.out.println("---SALES MENU---");
	        System.out.println("1. Add Order Item");
	        System.out.println("2. Remove Order Item");
	        System.out.println("3. Make Payment");
	        System.out.println("0. Exit");
	        int choice = readInt(0, 3);
	        return choice;
	    }
	    
	    public void salesStart(){
	        while (true){
	            int choice = menu();
	            switch (choice){
	                case 0:
	                    System.exit(0);
	                    break;
	                case 1:
	                    orderList.addOrderItem(orderItem);
	                    break;
	                case 2: 
	                    orderList.removeOrderItem(list);
	                    break;
	                case 3:
	                    orderList.payment(orderList);
	                    break;
	                default:
	                    throw new AssertionError();
	            }
	        }
	    }
	}