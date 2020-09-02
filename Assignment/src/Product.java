import java.io.*;
import java.util.*;

class Product implements Serializable {
	
	// Variables
    private String productId;
    private String productName;
    private String productType;
    private int itemQuantity;
    private double price;
    private static int nextProductId = 1;

    // Constructor with all data available execept prodId (For new items)
    public Product(String productName, String productType, double price, int itemQuantity) {
        this.productId = String.format("P%04d", nextProductId++);
        this.productName = productName;
        this.productType = productType;
        this.itemQuantity = itemQuantity;
        this.price = price;
    }

	// Constructor with all data available
    public Product(String productId, String productName, String productType, double price, int itemQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.itemQuantity = itemQuantity;
        this.price = price;
    }


    // Constructor without stockQuantity only
    public Product(String productName, String productType, double price) {
        this(productName, productType, price, 0);
    }

    // Constructor without prodType only
    public Product(String productName, double price, int itemQuantity) {
        this(productName, "n/a", price, itemQuantity);

    }

    // Constructor without prodType and stockQuantity
    public Product(String productName, double price) {
        this(productName, "n/a", price, 0);
    }

    // Setter

    // Note: Necessary when the database already have some product.
    public static void setNextProductId(int nextProductId) {
        Product.nextProductId = nextProductId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Getter
    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductType() {
        return productType;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public double getPrice() {
        return price;
    }

    public static int getNextProductId() {
        return nextProductId;
    }

    // Methods
    @Override
    public String toString() {
        return String.format(
                "Product: {Product ID: %s\nProduct Name: %s\nProduct Type: %s\nItem Quantity: %d\nPrice: RM %,.2fea\n}", productId,
                productName, productType, itemQuantity, price);
    }
}