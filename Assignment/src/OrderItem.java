import java.io.Serializable;

class OrderItem implements Serializable {
    private Product product;
    private double amount;
    private int quantity = 0;

    public OrderItem() {
    }

    public OrderItem(Product p) {
        this.product = p;
        amount = p.getPrice() * quantity;
    }

	public boolean stockOut(int quantity){
    	
        //To check if the stock quantity is enough
        if(product.getStockQuantity()>= quantity){
        	
            //Deduct the product stock quantity
            product.setStockQuantity(product.getStockQuantity()- quantity); 
            this.quantity ++;
            amount = quantity * product.getPrice();
            return true;
            
        }
        else {
            System.out.println("Out of stock.");
            return false;
        }
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
    
    //Getter
    public Product getProduct() {
        return product;
    }

    public double getAmount() {
        return amount;
    }

    public int getQuantity() {
        return quantity;
    }

    // Override toString()
    @Override
    public String toString() {
        return "OrderItem{" +
                "Room=" + product +
                ", amount=" + amount +
                ", quantity=" + quantity +
                '}';
    }

}
