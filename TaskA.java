import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 1. Abstract Base Class
abstract class Item {
    private String name;
    private double price;
    private int availableCopies;

    public Item(String name, double price, int availableCopies) {
        this.name = name;
        this.price = price;
        this.availableCopies = availableCopies;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void sell() {
        if (availableCopies > 0) {
            availableCopies--;
        } else {
            System.out.println("Sorry, " + name + " is out of stock!");
        }
    }

    public void returnItem() {
        availableCopies++;
        System.out.println(name + " returned successfully. Stock is now: " + availableCopies);
    }

    public abstract String getCategory();

    public void printDetails() {
        System.out.println("[" + getCategory() + "] " + name + " | Price: $" + price + " | In Stock: " + availableCopies);
    }
}

// 2. Abstract Clothing Class
abstract class Clothing extends Item {
    private String size;
    private String color;

    public Clothing(String name, double price, int availableCopies, String size, String color) {
        super(name, price, availableCopies);
        this.size = size;
        this.color = color;
    }

    public String getSize() { return size; }
    public String getColor() { return color; }
}

// Concrete Clothing Classes
class Shirt extends Clothing {
    private String material;

    public Shirt(String name, double price, int availableCopies, String size, String color, String material) {
        super(name, price, availableCopies, size, color);
        this.material = material;
    }

    @Override
    public String getCategory() {
        return "Clothing - Shirt";
    }
}

class Socks extends Clothing {
    private int pairsInPack;

    public Socks(String name, double price, int availableCopies, String size, String color, int pairsInPack) {
        super(name, price, availableCopies, size, color);
        this.pairsInPack = pairsInPack;
    }

    @Override
    public String getCategory() {
        return "Clothing - Socks";
    }
}

class Hat extends Clothing {
    private String style;

    public Hat(String name, double price, int availableCopies, String size, String color, String style) {
        super(name, price, availableCopies, size, color);
        this.style = style;
    }

    @Override
    public String getCategory() {
        return "Clothing - Hat";
    }
}

// 3. Abstract Device Class
abstract class Device extends Item {
    private String brand;
    private int warrantyMonths;

    public Device(String name, double price, int availableCopies, String brand, int warrantyMonths) {
        super(name, price, availableCopies);
        this.brand = brand;
        this.warrantyMonths = warrantyMonths;
    }

    public String getBrand() { return brand; }
    public int getWarrantyMonths() { return warrantyMonths; }
}

// Concrete Device Classes
class Printer extends Device {
    private boolean isColor;

    public Printer(String name, double price, int availableCopies, String brand, int warrantyMonths, boolean isColor) {
        super(name, price, availableCopies, brand, warrantyMonths);
        this.isColor = isColor;
    }

    @Override
    public String getCategory() {
        return "Device - Printer";
    }
}

class Laptop extends Device {
    private int ramGB;

    public Laptop(String name, double price, int availableCopies, String brand, int warrantyMonths, int ramGB) {
        super(name, price, availableCopies, brand, warrantyMonths);
        this.ramGB = ramGB;
    }

    @Override
    public String getCategory() {
        return "Device - Laptop";
    }
}

class Projector extends Device {
    private int lumens;

    public Projector(String name, double price, int availableCopies, String brand, int warrantyMonths, int lumens) {
        super(name, price, availableCopies, brand, warrantyMonths);
        this.lumens = lumens;
    }

    @Override
    public String getCategory() {
        return "Device - Projector";
    }
}

// 4. Cart Class
class Cart {
    private List<Item> items;

    public Cart() {
        items = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        if (item.getAvailableCopies() > 0) {
            items.add(item);
            System.out.println(item.getName() + " added to cart.");
        } else {
            System.out.println("Item out of stock!");
        }
    }

    public void removeItem(Item item) {
        if (items.remove(item)) {
            System.out.println(item.getName() + " removed from cart.");
        } else {
            System.out.println("Item not found in cart.");
        }
    }

    public double calculateTotal() {
        double total = 0.0;
        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).getPrice();
        }
        return total;
    }

    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("\n--- Current Cart Items ---");
        for (int i = 0; i < items.size(); i++) {
            Item it = items.get(i);
            System.out.println((i + 1) + ". [" + it.getCategory() + "] " + it.getName() + " - $" + it.getPrice());
        }
        System.out.println("Total Amount: $" + calculateTotal());
    }

    public void checkout() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty. Nothing to checkout.");
            return;
        }
        System.out.println("\n--- Processing Checkout ---");
        for (int i = 0; i < items.size(); i++) {
            items.get(i).sell();
        }
        System.out.println("Receipt: Total paid = $" + calculateTotal());
        items.clear();
        System.out.println("Checkout complete. Cart cleared.");
    }
}

// 5. Main CLI Console Application
public class TaskA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Item> inventory = new ArrayList<Item>();
        Cart cart = new Cart();

        // Sample Inventory Items
        inventory.add(new Shirt("Casual Cotton Shirt", 25.0, 5, "L", "Blue", "Cotton"));
        inventory.add(new Socks("Ankle Sports Socks", 8.0, 10, "M", "White", 3));
        inventory.add(new Hat("Classic Baseball Cap", 15.0, 4, "One-Size", "Black", "Sport"));
        inventory.add(new Laptop("Dell Latitude", 750.0, 3, "Dell", 12, 16));
        inventory.add(new Printer("HP LaserJet", 200.0, 2, "HP", 24, true));
        inventory.add(new Projector("Epson Home Cinema", 450.0, 2, "Epson", 12, 3000));

        boolean running = true;
        while (running) {
            System.out.println("\n================ STORE MENU ================");
            System.out.println("1. View Store Inventory");
            System.out.println("2. Add Item to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Return Item");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Store Inventory ---");
                    for (int i = 0; i < inventory.size(); i++) {
                        System.out.print((i + 1) + ". ");
                        inventory.get(i).printDetails();
                    }
                    break;

                case 2:
                    System.out.println("\nSelect item number to add (1-" + inventory.size() + "): ");
                    int addIndex = scanner.nextInt() - 1;
                    if (addIndex >= 0 && addIndex < inventory.size()) {
                        cart.addItem(inventory.get(addIndex));
                    } else {
                        System.out.println("Invalid selection!");
                    }
                    break;

                case 3:
                    cart.displayCart();
                    break;

                case 4:
                    cart.checkout();
                    break;

                case 5:
                    System.out.println("\nSelect item to return to inventory (1-" + inventory.size() + "): ");
                    int retIndex = scanner.nextInt() - 1;
                    if (retIndex >= 0 && retIndex < inventory.size()) {
                        inventory.get(retIndex).returnItem();
                    } else {
                        System.out.println("Invalid selection!");
                    }
                    break;

                case 6:
                    running = false;
                    System.out.println("Exiting store application. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please select from 1 to 6.");
            }
        }
        scanner.close();
    }
}
