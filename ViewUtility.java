package CoffeeShopApp;
import javax.swing.*;
import java.util.ArrayList;

/**
 * This class is used to perform the actions on the GUI view panel
 * It is used to show, remove, mark complete, list all and list incomplete orders
 * @Author: Ashley S McKinnon, S3886682
 * @Version: 17
 */

public class ViewUtility {
    // private constructor so that no objects of this class can be created
    private ViewUtility() {}

        /*
         Show a specific order in the list
         Passes the text from the text field which will be an order number
         Passes the textarea so that it can be changed within the GUI
        */
        public static void showOrder(String inputText, JTextArea outputTextArea, ArrayList<Order> processedOrders) {
            // Clear the screen
            outputTextArea.setText("");
            // Convert the text in to int
            int orderNumber = Integer.parseInt(inputText);
            for (Order i : processedOrders)
                if (i.getOrderNumber() == orderNumber) {
                    // Rather than outputting println, we will use setText on the JTextArea
                    outputTextArea.setText(i.toString());
                }
        }

        // Removes an order from the list
        public static void removeOrder(String inputText, JTextArea outputTextArea, ArrayList<Order> processedOrders) {
            outputTextArea.setText("");
            int orderNumber = Integer.parseInt(inputText);
            for (Order i : processedOrders) {
                if (i.getOrderNumber() == orderNumber) {
                    processedOrders.remove(i);
                    outputTextArea.setText("Order " + orderNumber + " has been removed.");
                    // break out of the loop to avoid crashing, it will want to keep going after list gets smaller.
                    break;
                }
            }
        }

        // Mark an order as complete
        public static void markComplete(String inputText, JTextArea outputTextArea, ArrayList<Order> processedOrders) {
            outputTextArea.setText("");
            int orderNumber = Integer.parseInt(inputText);
            for (Order i : processedOrders) {
                if (i.getOrderNumber() == orderNumber) {
                    i.setStatus("complete");
                    outputTextArea.setText(i.toString());
                }
            }
        }

        // List all orders in the list
        public static void listAllOrders(JTextArea outputTextArea, ArrayList<Order> processedOrders) {
            outputTextArea.setText("");
            for (Order i : processedOrders) {
                outputTextArea.append(i.toString() + "\n");
            }
        }

        // List all orders marked as incomplete
        public static void listIncompleteOrders(JTextArea outputTextArea, ArrayList<Order> processedOrders) {
            outputTextArea.setText("");
            for (Order i : processedOrders) {
                if (i.getStatus().equals("incomplete")) {
                    outputTextArea.append(i + "\n");
                }
            }
        }

    }

