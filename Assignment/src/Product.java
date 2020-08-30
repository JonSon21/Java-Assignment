class Product {
    private String prodId;
    private String prodName;
    private String prodType;
    private double price;
    private static int nextProdId = 1;

    // Constructors (Must have prodName and price)

    public Product(String prodName, String prodType, double price) {
        this.prodId = String.format("P%04d", nextProdId++);
        this.prodName = prodName;
        this.prodType = prodType;
        this.price = price;
    }

    public Product(String prodId, String prodName, String prodType, double price) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.prodType = prodType;
        this.price = price;
    }

    // Constructor without prodType 
    public Product(String prodName, double price) {
        this(prodName, "n/a", price);

    }

    // Setter

    // Note: Necessary when the database already have some product.


    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
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

    public double getPrice() {
        return price;
    }

    public static int getNextProdId() {
        return nextProdId;
    }

    // Methods
    @Override
    public String toString() {
        return String.format(
                "Product: {Product ID: %s\nProduct Name: %s\nProduct Type: %s\nPrice: RM %,.2f\n}", prodId,
                prodName, prodType, price);
    }
}