import java.io.Serializable;

class OrderItem implements Serializable {
    private Product product;
    private double amount;
    private int quantity = 0;

    public OrderItem() {
    }

    public OrderItem(Product product) {
        this.product = product;
        amount = product.getPrice() * quantity;
    }
    
    public boolean roomCheck(int quantity){ //To check if there's enough rooms
        
        if(product.getRoomQuantity()> quantity){
            //Deduct the product stock quantity
            product.setRoomQuantity(product.getRoomQuantity()-quantity);
            this.quantity++;
            amount = product.getPrice()*quantity;
            return true;
        }
        else {
            System.out.println("No rooms available.");
            return false;
        }
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

    @Override
    public String toString() {
        return "OrderItem{" +
                "product=" + product +
                ", amount=" + amount +
                ", quantity=" + quantity +
                '}';
    }

}