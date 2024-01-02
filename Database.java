package CoffeeShopApp;
import java.util.HashMap;
import java.util.Map;


/**
 * This class is used to connect to the SQL server and store the data in hashmaps
 * Only one instance of this class is created to stop multiple connections to the database

 * @Author: Ashley S McKinnon, S3886682
 * @Version: 17
 */

public class Database {

    private static Map<String, Double> foodItems;
    private static Map<String, Double> coffeeSizePrices;

    private static Map<String, Double> coffeeMilkPrices;


    public Database(){
        // Initialise the hashmaps
        foodItems = new HashMap<>();
        coffeeSizePrices = new HashMap<>();
        coffeeMilkPrices = new HashMap<>();
        // Switched from using database to hardcoded hashmaps. The following method populates hashmaps.
        initialiseData();

    }

    // Getters and Setters
    public static String[] getFoodItems() {return foodItems.keySet().toArray(new String[0]);}
    public static String[] getMilkTypes() {return coffeeMilkPrices.keySet().toArray(new String[0]);}

    public static Map<String, Double> getFoodPrices() {return foodItems;}

    public static Map<String, Double> getSizePrices() {return coffeeSizePrices;}

    public static Map<String, Double> getMilkPrices() {return coffeeMilkPrices;}


    public void initialiseData() {
        foodItems.put("Chocolate Cake", 3.0);
        foodItems.put("Carrot Cake", 2.5);
        foodItems.put("Cheese Cake", 2.5);
        foodItems.put("Pane Au Chocolat", 4.0);

        coffeeSizePrices.put("Small", 1.50);
        coffeeSizePrices.put("Medium", 2.0);
        coffeeSizePrices.put("Large", 2.5);

        coffeeMilkPrices.put("None", .0);
        coffeeMilkPrices.put("Full Cream", .5);
        coffeeMilkPrices.put("Skim", .5);
        coffeeMilkPrices.put("Soy", 1.0);
        coffeeMilkPrices.put("Almond", 1.0);
        coffeeMilkPrices.put("Oat", 1.0);



    }

}

