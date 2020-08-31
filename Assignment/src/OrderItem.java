/**
 * @(#)OrderItem.java
 *
 *
 * @author 
 * @version 1.00 2020/8/23
 */


public class OrderItem{

	private Product product;
    private int quantity = 0;
    
    
    public OrderItem(Product product){ 
    	this.product = product;
    }
    
    public double getAmount(){
    	return (product.getPrice() * quantity);
    }
    
    //Getter

    public int getQuantity() {
        return quantity;
    }
    
    public Product getProduct() {
        return product;
    }
    
    //Setter
    
    public void setProduct (Product product){
    	this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString(){
    	return String.format(product.toString() + "Item: \n" + product.getProdName() + "Quantity: \n" + quantity);
    }

    
}