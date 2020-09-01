import java.io.Serializable;

class Product implements Serializable {
	
	private static int nextProductId = 1; // To count the amount of products starting from 1
	
    private String productId;
    private String productName;
    private String productType;
    
    private int roomQuantity;
    private double price;

    // Constructors (Must have prodName and price)

    // Constructor without prodId
    public Product(String productName, String productType, double price, int roomQuantity) {
        this.productId = String.format("P%04d", nextProdId++);
        this.productName = productName;
        this.productType = productType;
        this.roomQuantity = roomQuantity;
        this.price = price;
    }

	// Constructor with prodId
    public Product(String prodId, String prodName, String prodType, double price, int stockQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.roomQuantity = roomQuantity;
        this.price = price;
    }

    // Constructor without stockQuantity only
    public Product(String productName, String productType, double price) {
        this(productName, productType, price, 0);
    }

    // Constructor without prodType only
    public Product(String productName, double price, int roomQuantity) {
        this(productName, "n/a", price, roomQuantity);

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

    public void setRoomQuantity(int stockQuantity) {
        this.roomQuantity = roomQuantity;
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

    public int getRoomQuantity() {
        return roomQuantity;
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
                "Product: {Product ID: %s\nProduct Name: %s\nProduct Type: %s\nStock Quantity: %d\nPrice: RM %,.2fea\n}", productId,
                productName, productType, roomQuantity, price);
    }
}