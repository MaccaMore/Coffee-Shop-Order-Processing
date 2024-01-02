package CoffeeShopApp;
import java.util.ArrayList;
import java.util.List;
/**
 * This class is used to create an order object
 * It contains all coffees and food items in the order
 * It also contains the order number, status, name, and price

 * @Author: Ashley S McKinnon, S3886682
 * @Version: 17
 */
public class Order {
    private static int nextOrderNumber = 0; //This goes up every time a new order is made

    // Attributes
    private int orderNumber;
    private String status = "incomplete";
    private String name = "N/A";
    // Can I use polymorphism to contain a list of items rather than two separate lists?
    private final List<Coffee> coffees;
    private final List<Food> foods;

    // Constructor
    public Order() {
        coffees = new ArrayList<>();
        foods = new ArrayList<>();
        nextOrderNumber++;
        this.orderNumber = nextOrderNumber;
    }

    // Getters and setters
    public int getOrderNumber() {return orderNumber;}
    public String getStatus() {return status;}
    public String getName() {return name;}
    public List<Coffee> getCoffee() {return coffees;}
    public List<Food> getFood() {return foods;}
    public void setOrderNumber(int orderNumber) {this.orderNumber = orderNumber;}
    public void setStatus(String status) {this.status = status;}
    public void setName(String name) {this.name = name;}

    // Functions
    public void addCoffee(Coffee coffee) {coffees.add(coffee);}
    public void addFood(Food foodObject) {foods.add(foodObject);}

    public double calculateOrderPrice() {
        double finalPrice = 0;
        for (Coffee i: coffees) {
            finalPrice += i.getPrice();
        }
        for (Food i: foods) {
            finalPrice += i.getPrice();
        }
        return finalPrice;
    }
    // Because I have created foods and coffee separately
    // I need to check if it contains anything before printing
    private String getFoodString() {
        if (foods.isEmpty()) {return "";}
        else {return "Food: " + foods + "\n";}
    }

    private String getCoffeeString() {
        if (coffees.isEmpty()) {return "";}
        else {return "Coffees: " + coffees + "\n";}
    }

    public String toString() {
        return "Order Number: " + orderNumber + "\n" +
                "Status: " + status + "\n" +
                "Name: " + name + "\n" +
                getCoffeeString() +
                getFoodString() +
                "Order Total: $" + calculateOrderPrice() + "\n";
    }
}
