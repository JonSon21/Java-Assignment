
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

class OrderList implements Serializable {

    private String orderNo;
    private static int NoOfOrder = 1;
    private ArrayList<OrderItem> orderItem = new ArrayList<>();
    private double totalAmount = 0;
    
    //itemCounter represents number of item listed within the order list
    private int itemCounter = 0;
    private double amount;
    
    //Initialize local date time object
    private String formattedDate;


    public OrderList() {
        LocalDateTime dateObj = LocalDateTime.now();
        DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        
        //Changes the date time format to dd-MM-yyyy HH:mm:ss
        formattedDate = dateObj.format(formatObj);
        this.orderNo = String.format("ITEM #%06d", NoOfOrder++);
    }

    // Add an order item into the OrderList array
    public boolean addOrderItem(OrderItem oi) {
        for (int i = 0; i < itemCounter; i++) {
        	
            //Compare product with the database
            if (orderItem.get(i).getProduct() == oi.getProduct() && orderItem.get(i).roomCheck(1)) {
            	
                //Adds amount of i element of orderItem to the totalAmount
                totalAmount += orderItem.get(i).getAmount();

                return true;
            }
        }

        try{
            //Adds element to the orderItem Array
            orderItem.add(oi);
            orderItem.get(itemCounter).roomCheck(1);
            orderItem.get(itemCounter).setQuantity(1);
            totalAmount += orderItem.get(itemCounter).getAmount();

            itemCounter++;
            
        }catch(Exception e){
        	
            System.out.format("Failed to add item #%s to order #%s.\n", itemCounter + 1, orderNo);
            return false;
        }
        return true;
    }

	// Edit the quantity of an order item
    public void editQuantity(int index, int quantity){ 
        index--; // Setting it according to the array elements.
        if(quantity != 0) {
            if(orderItem.get(index).roomCheck(quantity - orderItem.get(index).getQuantity())) {
            	
                orderItem.get(index).setQuantity(quantity);
                totalAmount += orderItem.get(index).getAmount();
                
            }
        }else {
            if(orderItem.get(index).roomCheck(quantity - orderItem.get(index).getQuantity())) {
            	
                orderItem.get(index).setQuantity(quantity);
                totalAmount += orderItem.get(index).getAmount();
                
                //Remove the list element of the orderItem Array
                orderItem.remove(index);
                itemCounter--;
            }
        }
    }

	// Print receipt
    public void receipt(boolean paid, double amount){ 
        this.amount = amount;
        if(paid) {
            System.out.println("Receipt");
            System.out.println("=======");
        }
        System.out.printf("%-4s%-30s%-9s%-7s%-8s\n\n","No.","Product Name","Quantity","Price","SubTotal");
        for(int i = 0; i < itemCounter; i++) {
            System.out.printf("%-4d%-30s%-9d%-7.2f%-8.2f\n",
            i+1,orderItem.get(i).getProduct().getProdName(), orderItem.get(i).getQuantity(), 
            orderItem.get(i).getProduct().getPrice(),orderItem.get(i).getProduct().getPrice() * orderItem.get(i).getQuantity());
        }
        System.out.printf("%-50s%-8.2f\n","Total", totalAmount);
        if(paid){
            System.out.printf("%-50s%-8.2f\n","Ringgit Malaysia", this.amount);
            System.out.printf("%-50s%-8.2f\n\n","Change", this.amount - totalAmount);
            System.out.println("Paid: " + formattedDate + "\n");
        }
    }

    //Setter
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public static void setNoOfOrder(int noOfOrder) {
        OrderList.noOfOrder = noOfOrder;
    }

    public void setOrderItem(ArrayList<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setItemCounter(int itemCounter) {
        this.itemCounter = itemCounter;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    //Getter
    public String getOrderNo() {
        return orderNo;
    }

    public static int getNoOfOrder() {
        return noOfOrder;
    }

    public ArrayList<OrderItem> getOrderItem() {
        return orderItem;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public int getItemCounter() {
        return itemCounter;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public double getAmount() {
        return amount;
    }
}