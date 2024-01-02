package CoffeeShopApp;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class is the driver class for the Coffee Shop program.
 * It contains the main method, and a method for adding dummy data.
 * Creates a database object, a new order object, and a new GUI object.

 * @Author: Ashley S McKinnon, S3886682
 * @Version: 17
 */


public class TesterDriver {
    // Dummy data to pass to GUI
    private static ArrayList<Order> dummyOrders;
    /*
     Dummy data needs an instance of the Database class to get the prices
     So we create a new Database object, this will be passed to the GUI
    */
    private static Database db;


    // Main method
    public static void main(String[] args) throws SQLException {
        /*
         Create a new object of the Database class
         The first time a database object is created, it will initialise the data
         Previous iteration created a new database everytime data was needed, which was inefficient
         because the SQL server would need to be contacted constantly.
        */
        db = new Database();
        // Create new ArrayList to store dummy data
        dummyOrders = new ArrayList<>();
        // Output to ensure that database works correctly
        System.out.println(db.getSizePrices());
        System.out.println(db.getFoodItems());
        System.out.println(db.getMilkTypes());
        // Pass the List to the addDummyData method
        addDummyData(dummyOrders);
        // Create the UI, passing the dummy orders and database object
        CoffeeShopGUI gui = new CoffeeShopGUI(dummyOrders,db);
        gui.setVisible(true);
    }


    // Add dummy data to the list
    private static void addDummyData(ArrayList<Order> dummyOrders) {
        Order order1 = new Order();
        order1.setName("Mahmud");
        Coffee coffee = new Coffee();
        coffee.setName("Latte");
        coffee.setMilk("Soy");
        coffee.setSugar(2);
        coffee.setTemp("Hot");
        coffee.setSize("Small");
        coffee.setPrice(db.getSizePrices(), db.getMilkPrices());
        order1.addCoffee(coffee);

        Order order2 = new Order();
        order2.setName("Ashley");
        Coffee coffee2 = new Coffee();
        coffee2.setName("Cappuccino");
        coffee2.setMilk("Almond");
        coffee2.setSugar(1);
        coffee2.setTemp("Hot");
        coffee2.setSize("Medium");
        coffee2.setPrice(db.getSizePrices(), db.getMilkPrices());
        Coffee coffee3 = new Coffee();
        coffee3.setName("Hot Chocolate");
        coffee3.setMilk("Soy");
        coffee3.setSugar(2);
        coffee3.setTemp("Hot");
        coffee3.setSize("Medium");
        coffee3.setPrice(db.getSizePrices(), db.getMilkPrices());
        order2.addCoffee(coffee2);

        Order order3 = new Order();
        order3.setName("Ashley");
        Coffee coffee4 = new Coffee();
        coffee4.setName("Cappuccino");
        coffee4.setMilk("Almond");
        coffee4.setSugar(1);
        coffee4.setTemp("Hot");
        coffee4.setSize("Medium");
        coffee4.setPrice(db.getSizePrices(), db.getMilkPrices());
        Food food = new Food();
        food.setName("Carrot Cake");
        food.setTemp("Cold");
        food.setPrice(db.getFoodPrices());
        order3.addCoffee(coffee4);
        order3.addFood(food);

        Order order4 = new Order();
        order4.setName("Ashley");
        Food food2 = new Food();
        food2.setName("Chocolate Cake");
        food2.setTemp("Cold");
        food2.setPrice(db.getFoodPrices());
        Food food3 = new Food();
        food3.setName("Cheese Cake");
        food3.setTemp("Cold");
        food3.setPrice(db.getFoodPrices());
        order4.addFood(food2);

        dummyOrders.add(order1);
        dummyOrders.add(order2);
        dummyOrders.add(order3);
        dummyOrders.add(order4);

    }

}
