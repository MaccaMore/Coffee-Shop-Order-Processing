package CoffeeShopApp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/** Project 2 OOP - Ashley S McKinnon
 * This is the GUI for the Coffee Shop, it is split into 2 tabs;

 * Tab 1: View Orders
     * Displays information to the user about the orders in the system

 * Tab 2: Add Orders
 * Split into 3 panels:
     * Panel 1: Greeting
         * Displays a greeting to the user
     * Panel 2: Order
         * Allows the user to add coffees and food to the order
     * Panel 3: Payment
         * Displays total cost of the order
         * Allows the user to pay for the order
 * @Author: Ashley S McKinnon, S3886682
 * @Version: 17
 */

public class CoffeeShopGUI extends JFrame implements ActionListener {
    private ArrayList<Order> processedOrders;
    private Database db;
    private Font boldFont = new Font("Default", Font.BOLD, 20);
    private Font titleFont = new Font("Georgia", Font.BOLD, 40);



    // Constructor
    public CoffeeShopGUI(ArrayList dummyOrders, Database db) {
        // Take the argument passed from driver class to set the object databas
        this.db = db;
        // Take argument from driver class to set the object processedOrders
        this.processedOrders = dummyOrders;
        // Set the title, size, close operation and make the window visible
        setTitle("Coffee in the wall");
        setSize(810, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        // Create the tabs as the second highest level of the GUI
        JTabbedPane tabPane = new JTabbedPane();
        JPanel viewOrderPane = viewOrderPane();
        JPanel addOrderPane = addOrderPane();
        tabPane.addTab("View Orders", null, viewOrderPane);
        tabPane.addTab("Add Order", null, addOrderPane);
        add(tabPane);
    }


    /*
     This method is used to display Tab 1, View Orders
     This Panel is responsible for displaying information about the orders in the system
     By passing the processedOrders arraylist to the methods to do various things
    */
    private JPanel viewOrderPane() {
        // Create the main JPanel for viewing orders
        JPanel viewOrderPane = new JPanel();
            viewOrderPane.setLayout(new BorderLayout());
            // Create JPanel for text output
            JTextArea outputTextArea = new JTextArea();
                outputTextArea.setText("Output will appear here");
                outputTextArea.setEditable(false);
            // Create JPanel for buttons
            JPanel buttonsPanel = new JPanel();
                buttonsPanel.setLayout(new GridLayout(2, 4));
                JButton listAllButton = new JButton("List all orders");
                JButton listIncompleteOrders = new JButton("List Incomplete");
                JButton markCompleteButton = new JButton("Mark Order Complete");
                JButton removeOrderButton = new JButton("Remove Order");
                JButton showOrderButton = new JButton("Show specific Order");
                JButton clearButton = new JButton("Clear");
            // Input panel to pass to view order methods
            JPanel orderIDPanel = new JPanel();
                JTextField inputTextField = new JTextField(4);
                // Add button components to buttons panel
                orderIDPanel.add(new JLabel("Order ID: "));
                orderIDPanel.add(inputTextField);
                buttonsPanel.add(orderIDPanel);
                buttonsPanel.add(showOrderButton);
                buttonsPanel.add(markCompleteButton);
                buttonsPanel.add(removeOrderButton);
                buttonsPanel.add(new JLabel(""));
                buttonsPanel.add(listAllButton);
                buttonsPanel.add(listIncompleteOrders);
                buttonsPanel.add(clearButton);
                // Make the buttons do things, we pass arguments to the methods in utility class
                showOrderButton.addActionListener(e ->
                        ViewUtility.showOrder(inputTextField.getText(), outputTextArea, processedOrders));

                markCompleteButton.addActionListener(e ->
                        ViewUtility.markComplete(inputTextField.getText(), outputTextArea, processedOrders));

                removeOrderButton.addActionListener(e ->
                        ViewUtility.removeOrder(inputTextField.getText(), outputTextArea, processedOrders));

                listAllButton.addActionListener(e ->
                        ViewUtility.listAllOrders(outputTextArea, processedOrders));

                listIncompleteOrders.addActionListener(e ->
                        ViewUtility.listIncompleteOrders(outputTextArea, processedOrders));

                clearButton.addActionListener(e ->
                        outputTextArea.setText("Cleared"));

            viewOrderPane.add(buttonsPanel, BorderLayout.SOUTH);
            viewOrderPane.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);

        return viewOrderPane;
    }
    // Method for adding order page, all 3 panels will be displayed inside here
    private JPanel addOrderPane() {
        JPanel addOrderPanel = new JPanel();
            addOrderPanel.setLayout(new BorderLayout());
            greetingPanel(addOrderPanel);
            return addOrderPanel;
    }
    // Initial greeting panel in order page, this is the first panel displayed
    // It is responsible for taking the user's name, creating an order, and passing it to the next panel
    private JPanel greetingPanel(JPanel addOrderPanel) {
        // Create panel for first screen
        JPanel greetingPanel = new JPanel();
            greetingPanel.setLayout(new BorderLayout());

            // Create sub panel for Heading
            JPanel headingPanel = new JPanel();
                JLabel greetingLabel = new JLabel("Welcome to Coffee in the Wall!");
                greetingLabel.setFont(titleFont);
                headingPanel.add(greetingLabel);

            // Sub panel for input name and order
            JPanel inputPanel = new JPanel();
                JTextField nameTextField = new JTextField(20);
                JButton createOrderButton = new JButton("Create new Order");
                inputPanel.add(new JLabel("Name"));
                inputPanel.add(nameTextField);
                inputPanel.add(createOrderButton);

            // Sub panel for logo
            JPanel imagePanel = new JPanel();
                imagePanel.setLayout(new FlowLayout());
                JLabel imageLabel = new JLabel();
                ImageIcon logo = new ImageIcon("src/CoffeeShopApp/logo.png");
                imageLabel.setIcon(logo);
                imagePanel.add(imageLabel);

            greetingPanel.add(headingPanel, BorderLayout.NORTH);
            greetingPanel.add(imagePanel, BorderLayout.CENTER);
            greetingPanel.add(inputPanel, BorderLayout.SOUTH);



            createOrderButton.addActionListener(e -> {
                // Create a new order to pass to orderPanel
                Order newOrder = new Order();
                newOrder.setName(nameTextField.getText());
                orderingPanel(newOrder, addOrderPanel);
            });

            /* As addOrderPane is being passed as parameter, greeting panel will become addOrderPane
               We remove whatever is in addOrderPane, and add the current greetingPanel
               We then revalidate the addOrderPane to update the screen
             */

            addOrderPanel.removeAll();
            addOrderPanel.add(greetingPanel);
            addOrderPanel.revalidate();
            return greetingPanel;
    }
    // Method for ordering panel will allow user to add
    private JPanel orderingPanel(Order newOrder, JPanel addOrderPanel) {
        // Create panel for order screen
        JPanel orderingPanel = new JPanel();
            orderingPanel.setLayout(new BorderLayout());
            JPanel orderHeading = new JPanel();
                orderHeading.setLayout(new BorderLayout());
                JLabel orderCoffeeHeading = new JLabel("      Order Coffee            ");
                JLabel orderFoodHeading = new JLabel("Order Food");
                orderCoffeeHeading.setFont(titleFont);
                orderFoodHeading.setFont(titleFont);
                orderHeading.add(orderCoffeeHeading, BorderLayout.WEST);
                orderHeading.add(orderFoodHeading, BorderLayout.CENTER);
                orderHeading.add(new JSeparator(), BorderLayout.SOUTH);

            // Left Panel for coffee selections
            JPanel coffeeSelections = new JPanel();
            coffeeSelections.setLayout(new GridLayout(5, 1));

            // Sub Panel for Coffee Temps
                JPanel orderTemp = new JPanel();
                    orderTemp.setLayout(new GridLayout(2, 5));
                    JLabel orderTempHeading = new JLabel("Temp");
                    orderTempHeading.setFont(boldFont);
                    // Create a button group so they are exclusive
                    ButtonGroup orderTempButtonGroup = new ButtonGroup();
                    JRadioButton orderTempIced = new JRadioButton("Iced");
                    JRadioButton orderTempHot = new JRadioButton("Hot");
                    JRadioButton orderTempVeryHot = new JRadioButton("Very Hot");
                    orderTempIced.setActionCommand("Iced");
                    orderTempHot.setActionCommand("Hot");
                    orderTempVeryHot.setActionCommand("Very Hot");
                    orderTempHot.setSelected(true);
                    orderTempButtonGroup.add(orderTempIced);
                    orderTempButtonGroup.add(orderTempHot);
                    orderTempButtonGroup.add(orderTempVeryHot);
                    // Add to orderTemp panel
                    orderTemp.add(orderTempHeading);
                    orderTemp.add(new JLabel(""));
                    orderTemp.add(new JLabel(""));
                    orderTemp.add(orderTempIced);
                    orderTemp.add(orderTempHot);
                    orderTemp.add(orderTempVeryHot);

                // Sub Panel for Coffee Sizes
                JPanel orderSize = new JPanel();
                    orderSize.setLayout(new GridLayout(2, 3));
                    JLabel orderSizeHeading = new JLabel("Size");
                    orderSizeHeading.setFont(boldFont);
                    ButtonGroup orderSizeButtonGroup = new ButtonGroup();
                    JRadioButton orderSizeSmall = new JRadioButton("Small $1.50");
                    JRadioButton orderSizeMedium = new JRadioButton("Medium $2.00");
                    JRadioButton orderSizeLarge = new JRadioButton("Large $2.50");
                    orderSizeSmall.setActionCommand("Small");
                    orderSizeMedium.setActionCommand("Medium");
                    orderSizeLarge.setActionCommand("Large");
                    orderSizeMedium.setSelected(true);
                    orderSizeButtonGroup.add(orderSizeSmall);
                    orderSizeButtonGroup.add(orderSizeMedium);
                    orderSizeButtonGroup.add(orderSizeLarge);
                    // Add to orderSize panel
                    orderSize.add(orderSizeHeading);
                    orderSize.add(new JLabel(" "));
                    orderSize.add(new JLabel(" "));
                    orderSize.add(orderSizeSmall);
                    orderSize.add(orderSizeMedium);
                    orderSize.add(orderSizeLarge);

                // Sub Panel for Coffee Types
                JPanel orderType = new JPanel();
                    orderType.setLayout(new GridLayout(2, 5));
                    JLabel orderTypeHeading = new JLabel("Type");
                    orderTypeHeading.setFont(boldFont);
                    String[] coffeeTypes = {"Espresso", "Latte", "Cappuccino", "Mocha", "Hot Chocolate", "Tea"};
                    JComboBox<String> orderTypeInput = new JComboBox<>(coffeeTypes);

                    orderType.add(orderTypeHeading);
                    orderType.add(orderTypeInput);

                // Sub Panel for Milk Types
                JPanel orderMilk = new JPanel();
                    orderMilk.setLayout(new GridLayout(2, 5));
                    JLabel orderMilkHeading = new JLabel("Milk");
                    orderMilkHeading.setFont(boldFont);
                    // Populate milkTypes from database class
                    String[] milkTypes = db.getMilkTypes();
                    JComboBox<String> orderMilkInput = new JComboBox<>(milkTypes);

                    orderMilk.add(orderMilkHeading);
                    orderMilk.add(orderMilkInput);

                // Sub Panel for sugar
                JPanel orderSugar = new JPanel();
                    orderSugar.setLayout(new GridLayout(2, 3));
                    JLabel orderSugarHeading = new JLabel("Sugar");
                    orderSugarHeading.setFont(boldFont);
                    // Create a model to determine step count of JSpinner
                    SpinnerNumberModel sugarModel = new SpinnerNumberModel(0, 0, 10, 1);
                    JSpinner orderSugarInput = new JSpinner(sugarModel);
                    JSpinner.NumberEditor orderSugarEditor = new JSpinner.NumberEditor(orderSugarInput);
                    orderSugarInput.setEditor(orderSugarEditor);

                    orderSugar.add(orderSugarHeading);
                    orderSugar.add(new JLabel(" "));
                    orderSugar.add(new JLabel(" "));
                    orderSugar.add(orderSugarInput);

                coffeeSelections.add(orderTemp);
                coffeeSelections.add(orderSize);
                coffeeSelections.add(orderType);
                coffeeSelections.add(orderMilk);
                coffeeSelections.add(orderSugar);

            // Right Panel for food selections
            JPanel foodSelections = new JPanel();
                foodSelections.setLayout(new GridLayout(5, 1));

                // Panel for ordering sweets
                JPanel orderSweets = new JPanel();
                    orderSweets.setLayout(new GridLayout(2, 5));
                    JLabel orderSweetsHeading = new JLabel("Sweets");
                    orderSweetsHeading.setFont(boldFont);
                    // getFoodItems returns all keys in the hashmap stored inside database object
                    String[] sweetsTypes = db.getFoodItems();
                    JComboBox<String> orderSweetsInput = new JComboBox<>(sweetsTypes);

                    orderSweets.add(orderSweetsHeading);
                    orderSweets.add(orderSweetsInput);

                // Panel for food temp
                JPanel orderFoodTemp = new JPanel();
                    orderFoodTemp.setLayout(new GridLayout(2, 3));
                    JLabel orderFoodTempHeading = new JLabel("Temperature:");
                    orderFoodTempHeading.setFont(boldFont);

                    ButtonGroup orderFoodTempButtonGroup = new ButtonGroup();
                    JRadioButton foodTempHot = new JRadioButton("Hot");
                    JRadioButton foodTempCold = new JRadioButton("Cold");
                    foodTempHot.setActionCommand("Hot");
                    foodTempCold.setActionCommand("Cold");
                    orderFoodTempButtonGroup.add(foodTempHot);
                    orderFoodTempButtonGroup.add(foodTempCold);
                    foodTempCold.setSelected(true);

                    orderFoodTemp.add(orderFoodTempHeading);
                    orderFoodTemp.add(new JLabel(""));
                    orderFoodTemp.add(new JLabel(""));
                    orderFoodTemp.add(foodTempHot);
                    orderFoodTemp.add(foodTempCold);

                // Area for text output
                JLabel orderAddedToCart = new JLabel("No items added to order");
                    orderAddedToCart.setFont(new Font("", Font.ITALIC, 12));

                foodSelections.add(orderSweets);
                foodSelections.add(orderFoodTemp);
                foodSelections.add(new JSeparator());
                foodSelections.add(orderAddedToCart);


            // Bottom panel for buttons
            JPanel orderButtonBar = new JPanel();
                orderButtonBar.setLayout(new BorderLayout());
                JPanel orderButtons = new JPanel();
                    orderButtons.setLayout(new GridLayout(1, 4));
                    JButton addCoffeeButton = new JButton("Add Coffee");
                    JButton addFoodButton = new JButton("Add Food");
                    JButton backButton = new JButton("Back");
                    JButton checkoutButton = new JButton("Checkout");

                    orderButtons.add(backButton);
                    orderButtons.add(addCoffeeButton);
                    orderButtons.add(addFoodButton);
                    orderButtons.add(checkoutButton);
                    orderButtonBar.add(new JSeparator(), BorderLayout.NORTH);
                    orderButtonBar.add(orderButtons, BorderLayout.SOUTH);


        // Finally, add all panels to orderingPanel
        orderingPanel.add(orderHeading, BorderLayout.NORTH);
        orderingPanel.add(orderButtonBar, BorderLayout.SOUTH);
        orderingPanel.add(new JSeparator(SwingConstants.VERTICAL), BorderLayout.CENTER);
        orderingPanel.add(coffeeSelections, BorderLayout.WEST);
        orderingPanel.add(foodSelections, BorderLayout.EAST);


        // Action Listeners
        backButton.addActionListener(e -> {
            greetingPanel(addOrderPanel);
        });

        // Create a food object with details from panel and add to order
        addFoodButton.addActionListener(e -> {
            Food food = new Food();
            food.setName(orderSweetsInput.getSelectedItem().toString());
            food.setTemp(orderFoodTempButtonGroup.getSelection().getActionCommand());
            food.setPrice(db.getFoodPrices());
            orderAddedToCart.setText(" + " + food);
            newOrder.addFood(food);
        });

        // Create a coffee object with details from panel and add to order
        addCoffeeButton.addActionListener(e -> {
            Coffee coffee = new Coffee();
            coffee.setSize(orderSizeButtonGroup.getSelection().getActionCommand());
            coffee.setTemp(orderTempButtonGroup.getSelection().getActionCommand());
            coffee.setName(orderTypeInput.getSelectedItem().toString());
            coffee.setMilk(orderMilkInput.getSelectedItem().toString());
            coffee.setSugar((Integer) orderSugarInput.getValue());
            // Pass the hashmaps for price to the coffee object method
            // These hashmaps store key NAME and value PRICE, ie Small = 1.50
            // Because coffee type and size are set on previous line, we can use sizePrices.get(size)
            coffee.setPrice(db.getSizePrices(), db.getMilkPrices());
            orderAddedToCart.setText(" + " + coffee);
            newOrder.addCoffee(coffee);
        });

        // Pass the order to the payment panel
        checkoutButton.addActionListener(e -> {
            paymentPanel(addOrderPanel, newOrder);
        });

        // Clear the panel and add the ordering panel
        addOrderPanel.removeAll();
        addOrderPanel.add(orderingPanel);
        addOrderPanel.revalidate();
        return orderingPanel;
    }

    // Payment Panel
    private JPanel paymentPanel(JPanel addOrderPanel, Order newOrder) {
        JPanel paymentPanel = new JPanel();
            paymentPanel.setLayout(new BorderLayout());

            // Top Panel to display order
            JTextArea receiptTextArea = new JTextArea();
                receiptTextArea.setText(newOrder.toString());
                receiptTextArea.setEditable(false);

            // Bottom Panel for buttons
            JPanel buttonsPanel = new JPanel();
                buttonsPanel.setLayout(new GridLayout(1, 4));
                JLabel totalLabel = new JLabel("Total: $" + newOrder.calculateOrderPrice());
                totalLabel.setFont(boldFont);
                JButton backButton = new JButton("Back/ add More");
                JButton payButton = new JButton("Confirm Order");
                JButton cancelButton = new JButton("Cancel Order");

            buttonsPanel.add(totalLabel);
            buttonsPanel.add(cancelButton);
            buttonsPanel.add(backButton);
            buttonsPanel.add(payButton);

        paymentPanel.add(new JScrollPane(receiptTextArea), BorderLayout.CENTER);
        paymentPanel.add(buttonsPanel, BorderLayout.SOUTH);

        // Action listeners
        // Go back to greeting panel, greeting panel creates a new Order object
        cancelButton.addActionListener(e -> {
            greetingPanel(addOrderPanel);
        });

        // Pass the Order object back to the ordering panel
        backButton.addActionListener(e -> {
            orderingPanel(newOrder, addOrderPanel);
        });

        // Add the order to the processed orders list and go back to greeting panel
        payButton.addActionListener(e -> {
            processedOrders.add(newOrder);
            greetingPanel(addOrderPanel);
        });


        addOrderPanel.removeAll();
        addOrderPanel.add(paymentPanel);
        addOrderPanel.revalidate();
        return paymentPanel;
    }



    // Required for action listener lambdas
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
