package product;
import java.util.Scanner;

public class OrderConsole {
    
    private OrderList ol;
    private Scanner sc;
    private OrderItem oi;
    private double payment;
    
    public OrderConsole(){
        this.sc = new Scanner(System.in);
        this.ol = new OrderList();
        this.oi = new OrderItem();
    }
    
    
    private int menu(){
        System.out.println("---ORDER MENU---");
        System.out.println("1. Add Order Item");
        System.out.println("2. Remove Order Item");
        System.out.println("3. Show Receipt");
        System.out.println("0. Exit");
        int choice = readInt(0, 3);
        return choice;
    }
    
    public void start(){
        while (true){
            int choice = menu();
            switch (choice){
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    addOrderItem();
                    break;
                case 2: 
                    removeOrderItem();
                    break;
                case 3:
                    receipt();
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private int readInt(int min, int max) {
        int choice;
        while(true){
            try{
                choice = Integer.parseInt(sc.nextLine());
                if(choice >= min && choice <= max){
                    break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return choice;
    }
    
    private float readFloat(int min, float max) {
        float price;
        while(true){
            try {
                price = Float.parseFloat(sc.nextLine());
                if(price >= min && price <= max){
                    break;
                }
            }catch (Exception e){
                System.out.println("Invalid value.Try to enter a float value");
            }
        }
        return price;
    }
    private void addOrderItem(){
        System.out.println("Enter product ID:");
        int id = readInt(0, Integer.MAX_VALUE);
        System.out.println("Enter product name:");
        String name = sc.nextLine();
        System.out.println("Enter product price");
        float price = readFloat(0, Float.MAX_VALUE);
        Product p = new Product(id, name, price);
        this.pm.addProduct(p);
    }

    private void removeOrderItem() {
        System.out.println("Enter ID of product");
        int id = readInt(0, Integer.MAX_VALUE);
        boolean result = this.pm.removeProduct(id);
        if(result){
            System.out.println("Product was removed");
        }else{
            System.out.println("Product not found");
        }
    }
}