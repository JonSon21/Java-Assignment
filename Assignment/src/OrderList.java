/**
 * @(#)OrderList.java
 *
 *
 * @author 
 * @version 1.00 2020/8/14
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.*;

public class OrderList {

	private String orderNo;
	private static int nextOrderNo = 1;
	private ArrayList<OrderItem> orderItem = new ArrayList<>();
	private static int itemCount = 0;
	private double totalAmount = 0;
	private double amountPaid = 0; // Amount Paid
	
	private String formattedDate;

    public OrderList() {
    	LocalDateTime dateObj = LocalDateTime.now();
    	DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    	
    	formattedDate = dateObj.format(formatObj);
        orderNo = String.format("I%06d", nextOrderNo++);
    }
    
    public boolean addOrderItem (OrderItem item){
    	try{
    		orderItem.add(item);
    		totalAmount += orderItem.get(itemCount).getAmount();
    		
    		itemCount++;
    	}catch (Exception e){
    		System.out.format("Failed to add item #%s to order #%s.\n", itemCount+1, orderNo);
    		return false;
    	}
    	return true;
    }
    
    public void removeOrderItem (int list){
    		list--;
            //Remove the list element of the orderItem Array
            orderItem.remove(list);
            itemCount--;
    }
    
    public void receipt(boolean paid, double amountPaid){
        this.amountPaid = amountPaid;
        if(paid) {
            System.out.println("Receipt");
            System.out.println("=======");
        }
        System.out.printf("%-4s%-30s%-9s%-7s%-8s\n\n","No.","Product Name","Quantity","Price","Subtotal");
        for(int i = 0; i < itemCount; i++) {
        System.out.printf("%-4d%-30s%-9d%-7.2f%-8.2f\n", i+1, orderItem.get(i).getProduct().getProdName(), orderItem.get(i).getQuantity(), orderItem.get(i).getProduct().getPrice(),orderItem.get(i).getAmount());
        }
        System.out.printf("%-50s%-8.2f\n","Total", totalAmount);
        if(paid){
            System.out.printf("%-50s%-8.2f\n","Ringgit Malaysia", this.amountPaid);
            System.out.printf("%-50s%-8.2f\n\n","Change", this.amountPaid - totalAmount);
            System.out.println("Paid: " + formattedDate + "\n");
        }
    }
    
    //Setter
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public void setNextOrderNo(int nextOrderNo) {
        this.nextOrderNo = nextOrderNo;
    }

    public void setOrderItemList(ArrayList<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public void setAmountPaid (double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    //Getter
    public String getOrderNo() {
        return orderNo;
    }

    public static int getNextOrderNo() {
        return nextOrderNo;
    }

    public ArrayList<OrderItem> getOrderItem() {
        return orderItem;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public int getItemCount() {
        return itemCount;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public double getAmountPaid() {
        return amountPaid;
    }
    
    
}