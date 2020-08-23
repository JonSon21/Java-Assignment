/**
 * @(#)OrderList.java
 *
 *
 * @author 
 * @version 1.00 2020/8/14
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OrderList {

	private String orderNo;
	private static int nextOrderNo = 1;
	private OrderItem[] orderItem;
	private static int itemCount = 0;
	private double totalAmount = 0;
	
	private String formattedDate;

    public OrderList() {
    	LocalDateTime dateObj = LocalDateTime.now();
    	DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    	
    	formattedDate = dateObj.format(formatObj);
        orderNo = String.format("I%06d", nextOrderNo++);
    }
    
    public void addOrderItem (OrderItem item){
    	
    	orderItem[itemCount] = item;
    	totalAmount += orderItem[itemCount].getAmount();
    		
    	itemCount++;
    }
    
    
    
    
    
}