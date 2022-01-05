package a3; 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent; 

public class BuyWindow extends JPanel {

    JComboBox<String> types;
    private JTextField symbolEntry;
    private JTextField nameEntry;
    private JTextField quantityEntry;
    private JTextField priceEntry;
    private JTextArea messageBox;

    private String typeSelected;
    private String symbolEntered;
    private String nameEntered;
    private String quantityEntered;
    private String priceEntered;
    private String message;

    /**
     * Listener for the reset button 
     */
    private class ResetValuesListener implements ActionListener {

        /**
         * Resets values in the JTextFields
         * @param e action from the reset button
         */
        public void actionPerformed (ActionEvent e) {
            symbolEntry.setText("");
            nameEntry.setText("");
            quantityEntry.setText("");
            priceEntry.setText("");
        }
    }
    /**
     * Listener to swap the type combo box
     */
    private class InvestmentTypeListener implements ActionListener {

        /**
         * Sets the value of typeSelected through combobox selection
         * @param e action from the combobox
         */
        public void actionPerformed (ActionEvent e) {
            JComboBox cb = (JComboBox)e.getSource();
            typeSelected = (String)cb.getSelectedItem();
        }
    }

    /**
     * Listnere for the buy button
     */
    private class BuyListener implements ActionListener { // 

        /**
         * Buys an investments and adds it to portfolio
         * @param a action from the buy button
         */
        public void actionPerformed (ActionEvent a){
            
            typeSelected = (String) types.getSelectedItem();
            symbolEntered = symbolEntry.getText();
            nameEntered = nameEntry.getText();
            quantityEntered = quantityEntry.getText();
            priceEntered = priceEntry.getText();

            try {
                GUI.portfolio.buy(typeSelected, symbolEntered, nameEntered, quantityEntered, priceEntered); 
                messageBox.append(typeSelected + " purchased successfully.\n");

            } catch (IllegalArgumentException e) {
                messageBox.setText(e.getMessage() + "Please try again. \n");
            }
        }
    }

    /**
     * Resets values in the window if user flips between command windows
     */
    public void setWindow(){

        symbolEntry.setText("");
        nameEntry.setText("");
        quantityEntry.setText("");
        priceEntry.setText("");

        messageBox.setText("");
    }

    /**
     * Constructor for the Buy window - sent a grid layout 
     * @param layout layout to send to the JPanel super constructor
     */
    public BuyWindow(LayoutManager layout) {

        super(layout);
        //CREATE BUY PANEL - top panel and botton (messagePanel);
        JPanel topPanel = new JPanel(new GridLayout(1,2));
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));

        //CREATE TOP PANEL - userEntryPanel and buttonPanel
        JPanel userEntryPanel = new JPanel();
        userEntryPanel.setLayout(new BoxLayout(userEntryPanel, BoxLayout.Y_AXIS));
        JPanel buttonPanel = new JPanel();

        //CREATE USER ENTRY PANEL - buyTitle, entrySpace Panel(labelPanel, valuePanel);
        JLabel buyTitle = new JLabel("Buying an investment");
        userEntryPanel.add(buyTitle);
        JPanel entrySpacePanel = new JPanel(new BorderLayout(20,30));


        JPanel labelPanel = new JPanel(new GridLayout(6,1)); 

        JLabel typeLabel = new JLabel("Type");
        labelPanel.add(typeLabel);
        JLabel symbolLabel = new JLabel("Symbol");
        labelPanel.add(symbolLabel);
        JLabel nameLabel = new JLabel("Name");
        labelPanel.add(nameLabel);
        JLabel quantityLabel = new JLabel("Quantity");
        labelPanel.add(quantityLabel);
        JLabel priceLabel = new JLabel("Price");
        labelPanel.add(priceLabel);
        
        JPanel valuePanel = new JPanel(new GridLayout(6,1));

        String[] typeStrings = {"Stock", "Mutual Fund"};
        types = new JComboBox<>(typeStrings);
        types.setSelectedIndex(0);
        types.addActionListener(new InvestmentTypeListener());
        valuePanel.add(types);
        symbolEntry = new JTextField(10); //actionEvents needed
        valuePanel.add(symbolEntry);
        nameEntry = new JTextField(25);
        valuePanel.add(nameEntry);
        quantityEntry = new JTextField(10);
        valuePanel.add(quantityEntry);
        priceEntry = new JTextField(10);
        valuePanel.add(priceEntry);

        entrySpacePanel.add(labelPanel, BorderLayout.WEST);
        entrySpacePanel.add(valuePanel, BorderLayout.CENTER);
        userEntryPanel.add(entrySpacePanel);

        topPanel.add(userEntryPanel);

        //CREATE BUTTON PANEL - reset and buy
        JPanel bpanel = new JPanel();
        bpanel.setLayout(new BoxLayout(bpanel, BoxLayout.Y_AXIS));
        JButton resetButton = new JButton("Reset");
        resetButton.setPreferredSize(new Dimension(100, 100));
        resetButton.addActionListener(new ResetValuesListener());
        bpanel.add(resetButton);

        JButton buyButton = new JButton("Buy");
        buyButton.setPreferredSize(new Dimension(100, 100));
        buyButton.addActionListener(new BuyListener());
        bpanel.add(buyButton);

        buttonPanel.add(bpanel);

        topPanel.add(buttonPanel);

        //CREATE MESSAGE PANEL - text area
        JLabel messageBoxLabel = new JLabel("Messages");
        messagePanel.add(messageBoxLabel);

        JPanel messageBoxPanel = new JPanel(new GridLayout(1,1));
        messageBox = new JTextArea(100,100);
        messageBox.setEditable(false);
        JScrollPane scrolledText = new JScrollPane(messageBox);
        scrolledText.setHorizontalScrollBarPolicy(
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolledText.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        messageBoxPanel.add(scrolledText);
        messagePanel.add(messageBoxPanel);
        //Put it all together
        add(topPanel);
        add(messagePanel);
    }
}