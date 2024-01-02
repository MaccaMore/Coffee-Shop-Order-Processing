package CoffeeShopApp;

/**
 * This class is the parent class for coffee and food classes
 * Both share name and temp attributes

 * @Author: Ashley S McKinnon, S3886682
 * @Version: 17
 */

public class Items {
    // Attributes
    private String name = "";
    private String temp = "";

    // Default constructor
    public Items() {}


    // Getters and setters
    public String getName() {return name;}
    public String getTemp() {return temp;}

    public void setName(String name) {this.name = name;}

    public void setTemp(String temp) {this.temp = temp;}
}