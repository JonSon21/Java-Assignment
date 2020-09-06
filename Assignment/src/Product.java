import java.io.Serializable;

class Product implements Serializable {
	
	// Variables
    private String prodId;
    private String prodName;
    private String prodType;
    private int stockQuantity;
    private double price;
    
    // Counting the product starting from 1
    // This is also to automatically set the product ID
    private static int nextProdId = 1; 

    // Constructors (Must have prodName and price)

	// Constructor with all data available
    public Product(String productId, String productName, String productType, double price, int itemQuantity) {
        this.prodId = productId;
        this.prodName = productName;
        this.prodType = productType;
        this.stockQuantity = itemQuantity;
        this.price = price;
    }
    
    // Constructor with all data available without prodId
    // This is for new products
    public Product(String productName, String productType, double price, int itemQuantity) {
        this.prodId = String.format("P%04d", nextProdId++);
        this.prodName = productName;
        this.prodType = productType;
        this.stockQuantity = itemQuantity;
        this.price = price;
    }

    // Constructor without stockQuantity only
    public Product(String productName, String productType, double price) {
        this(productName, productType, price, 0);
    }

    // Constructor without prodType only
    public Product(String productName, double price, int itemQuantity) {
        this(productName, "tba", price, itemQuantity);
    }

    // Constructor without prodType and stockQuantity
    public Product(String productName, double price) {
        this(productName, "tba", price, 0);
    }

    // Setter
    // Note: Necessary when the database already have some product.
    public static void setNextProdId(int nextProdId) {
        Product.nextProdId = nextProdId;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Getter
    public String getProdId() {
        return prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public String getProdType() {
        return prodType;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public double getPrice() {
        return price;
    }

    public static int getNextProdId() {
        return nextProdId;
    }

    // Override toString()
    @Override
    public String toString() {
        return String.format("%-11s%-22s%20s%15d%10.2f\n",prodId,prodName,prodType,stockQuantity,price);
    }
}