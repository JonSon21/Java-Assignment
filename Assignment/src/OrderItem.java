/**
 * @(#)OrderItem.java
 *
 *
 * @author 
 * @version 1.00 2020/8/23
 */


public class OrderItem extends Product{

    private int quantity = 0;
    
    public OrderItem(int quantity){ 
    	this.quantity = quantity;
    }
    
    public OrderItem(String prodId, String prodName, String prodType, double price, int quantity){ 
    	super(prodId, prodName, prodType, price);
    	this.quantity = quantity;
    }
    
    //Getter
    
    public Product getProduct(){
    	return product;
    }

    public int getQuantity() {
        return quantity;
    }
    
    //Setter
    public void setProduct(Product product) {
        this.product = product;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getAmount(){
    	return (product.getPrice()*quantity);
    }
    
    public String toString(){
    	return "\nItem: " + getProductDescription + "\nQuantity: " + quantity + "\nAmount: " + totalAmount;
    }

    
}