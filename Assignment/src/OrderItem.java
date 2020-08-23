/**
 * @(#)OrderItem.java
 *
 *
 * @author 
 * @version 1.00 2020/8/23
 */


public class OrderItem extends Product{

	private Product product;
    private int quantity = 0;
    
    public OrderItem(Product product, int quantity){ 
    	this.product = product;
    	this.quantity = quantity;
    }
    
    public OrderItem(String productId, String productDescription, double price, int quantity){ 
    	product = new Product(productId, productDescription, price);
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