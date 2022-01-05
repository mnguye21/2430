package a3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent; 

public class SellWindow extends JPanel {

    private JTextField symbolEntry;
    private JTextField quantityEntry;
    private JTextField priceEntry;
    private JTextArea messageBox;

    private String symbolEntered;
    private String quantityEntered;
    private String priceEntered;
    private String message;

    
    /**
     * Listener to reset values in the JTextFields
     */
    private class ResetValuesListener implements ActionListener {

        /**
         * Resets values in the JTextfields
         * @param e Action from reset button
         */
        public void actionPerformed (ActionEvent e) {
            symbolEntry.setText("");
            quantityEntry.setText("");
            priceEntry.setText("");
        }
    }

    /**
     * Listener to sell investment
     */
    private class SellListener implements ActionListener {// 

        /**
         * Sells the investment matching the values in the textfields
         * @param a Action from sell button
         */
        public void actionPerformed (ActionEvent a) {
            
            symbolEntered = symbolEntry.getText();
            quantityEntered = quantityEntry.getText();
            priceEntered = priceEntry.getText();

            try {
                GUI.portfolio.sell(symbolEntered, Integer.valueOf(quantityEntered), Double.valueOf(priceEntered));
                messageBox.append(quantityEntered + "@" + priceEntered + " of " + symbolEntered + " sold successfully\n");

            } catch (IllegalArgumentException e){
                messageBox.append(e.getMessage() + "Please try again. \n");
            }
        }
    }

    /**
     * Sets windows for when user flips windows
     */
    public void setWindow(){

        symbolEntry.setText("");
        quantityEntry.setText("");
        priceEntry.setText("");

        messageBox.setText("");
    }

    /**
     * Constructor for the sell window
     * @param layout
     */
    public SellWindow (LayoutManager layout) {
        
        super(layout);
        //CREATE SELL PANEL - topPanel , messagePanel
        JPanel topPanel = new JPanel(new GridLayout(1,2));
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));

        //CREATE TOP PANEL - userEntryPanel and buttonPanel
        JPanel userEntryPanel = new JPanel();
        userEntryPanel.setLayout(new BoxLayout(userEntryPanel, BoxLayout.Y_AXIS));
        JPanel buttonPanel = new JPanel();

        //CREATE USER ENTRY PANEL - buyTitle, entrySpace Panel(labelPanel, valuePanel);
        JLabel sellTitle = new JLabel("Selling an investment");
        userEntryPanel.add(sellTitle);
        JPanel entrySpacePanel = new JPanel(new BorderLayout(20,30));


        JPanel labelPanel = new JPanel(new GridLayout(5,1)); 

        JLabel symbolLabel = new JLabel("Symbol");
        labelPanel.add(symbolLabel);
        JLabel quantityLabel = new JLabel("Quantity");
        labelPanel.add(quantityLabel);
        JLabel priceLabel = new JLabel("Price");
        labelPanel.add(priceLabel);
        
        JPanel valuePanel = new JPanel(new GridLayout(5,1));

        symbolEntry = new JTextField(10); //actionEvents needed
        valuePanel.add(symbolEntry);
        quantityEntry = new JTextField(10);
        valuePanel.add(quantityEntry);
        priceEntry = new JTextField(10);
        valuePanel.add(priceEntry);

        entrySpacePanel.add(labelPanel, BorderLayout.WEST);
        entrySpacePanel.add(valuePanel, BorderLayout.CENTER);
        userEntryPanel.add(entrySpacePanel);

        topPanel.add(userEntryPanel);

        //CREATE BUTTON PANEL - reset and buy
        JPanel spanel = new JPanel();
        spanel.setLayout(new BoxLayout(spanel, BoxLayout.Y_AXIS));
        JButton resetButton = new JButton("Reset");
        resetButton.setPreferredSize(new Dimension(100, 30));
        resetButton.addActionListener(new ResetValuesListener());
        spanel.add(resetButton);

        JButton sellButton = new JButton("Sell");
        sellButton.setPreferredSize(new Dimension(100, 100));
        sellButton.addActionListener(new SellListener());
        spanel.add(sellButton);

        buttonPanel.add(spanel);

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