package CoffeeShopApp;
import java.util.Map;

/**
 * This class is used for adding food objects to the order
 * It is a subclass of Items and inherits name and temp

 * @Author: Ashley S McKinnon, S3886682
 * @Version: 17
 */

public class Food extends Items {

    // Default constructor
    double price;
    public Food() {
        super();
    }
    public double getPrice() {return price;}

    // Pass the map that contains the food items and their prices
    // We then compare the KEY which is the food variable to the VALUE price
    public void setPrice(Map foodItems) {price = (double) foodItems.get(getName());}


    public String toString() {return getTemp() + " " + getName() + " $" + getPrice();}

}
