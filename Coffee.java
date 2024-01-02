package CoffeeShopApp;
import java.util.Map;

/**
 * This class is the parent class for coffee and food classes
 * Both share name and temp attributes

 * @Author: Ashley S McKinnon, S3886682
 * @Version: 17
 */

public class Coffee extends Items {
    // Attributes
    private String size;
    private String milk;
    private int sugar;
    private Double price;

    // Default constructor
    public Coffee() {
        super();
    }

    // Getters
    public String getSize() {return size;}
    public String getMilk() {return milk;}
    public int getSugar() {return sugar;}
    public double getPrice() {return price;}

    // Setters
    public void setSize(String size) {this.size = size;}
    public void setMilk(String milk) {this.milk = milk;}
    public void setSugar(int sugar) {this.sugar = sugar;}

    public void setPrice(Map SizePrices, Map MilkPrices) {
        // Get the price of the size and milk from the hashmap using stored size and milk
        double sizePrice = (double) SizePrices.get(size);
        double milkPrice = (double) MilkPrices.get(milk);
                price = sizePrice + milkPrice;
    }


    // String
    public String toString() {
        return size + " " + getTemp() + " " + milk + " " + getName() + " with " + sugar + " sugar" +
                " $" + price;
    }
}
