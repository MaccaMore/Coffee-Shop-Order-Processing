package CoffeeShopApp;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is used to connect to the SQL server and store the data in hashmaps
 * Only one instance of this class is created to stop multiple connections to the database

 * @Author: Ashley S McKinnon, S3886682
 * @Version: 17
 */

public class Database {
    private static Connection connection;
    /**
     * The SQL server contains a table with 2 columns, name and price
     * Use this to create a hashmap to link these values and store them here.
     */
    private static Map<String, Double> foodItems;
    private static Map<String, Double> coffeeSizePrices;

    private static Map<String, Double> coffeeMilkPrices;


    public Database() throws SQLException {
        if (connection == null) {
            /*
             Establish a connection to database
             Will need to change IP to assigned public IP on the EC2 instance running the database
             This will change every time I start the database server
                * Running RDS will solve this issue.
            */
            String url = "jdbc:mysql://54.174.97.78/coffee_shop";
            String username = "projectUser";
            String password = "oop";

            connection = DriverManager.getConnection(url, username, password);

            // Initialise the hashmaps
            foodItems = new HashMap<>();
            coffeeSizePrices = new HashMap<>();
            coffeeMilkPrices = new HashMap<>();
            initialiseData();
        }
    }

    // Getters and Setters
    public static String[] getFoodItems() {return foodItems.keySet().toArray(new String[0]);}
    public static String[] getMilkTypes() {return coffeeMilkPrices.keySet().toArray(new String[0]);}

    public static Map<String, Double> getFoodPrices() {return foodItems;}

    public static Map<String, Double> getSizePrices() {return coffeeSizePrices;}

    public static Map<String, Double> getMilkPrices() {return coffeeMilkPrices;}

    // We take the data from the SQL server and store it in the hashmaps

    public void initialiseData() throws SQLException {

        // Query the database for KEY and VALUE name and price from the table SizePrices
        String query = "SELECT name, price FROM SizePrices";

        // We need to create a statement to execute the query
        // Prepared statements are pre compiled SQL commands.
        try (PreparedStatement statement = connection.prepareStatement(query);
             // Run the SQL command and store the result in a ResultSet object
             ResultSet resultSet = statement.executeQuery()) {
            // Iterate for as long as there are rows in the table, .next row
            while (resultSet.next()) {
                // Get String from the column labeled name
                String name = resultSet.getString("name");
                // Get the number from table labeled price
                double price = resultSet.getDouble("price");
                // Add the name and price to the hashmap
                coffeeSizePrices.put(name, price);
            }
        }

        String query2 = "SELECT name, price FROM MilkPrices";
        try (PreparedStatement statement = connection.prepareStatement(query2);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                coffeeMilkPrices.put(name, price);
            }
        }

        String query3 = "SELECT name, price FROM FoodItems";
        try (PreparedStatement statement = connection.prepareStatement(query3);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                foodItems.put(name, price);
            }
        }

    }
    // Used for constructing the combo-box, returns an array of the keys in the hashmap
    // The key is the first column in the SQL table
}

