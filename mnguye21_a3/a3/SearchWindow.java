package a3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchWindow extends JPanel {

    private JTextField symbolEntry;
    private JTextField keywordsEntry;
    private JTextField lowpriceEntry;
    private JTextField highpriceEntry;
    private JTextArea messageBox;

    private String symbolEntered;
    private String keywordsEntered;
    private String lowpriceEntered;
    private String highpriceEntered;
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
            keywordsEntry.setText("");
            lowpriceEntry.setText("");
            highpriceEntry.setText("");
        }
    }

    /**
     * Listener to search the portfolio 
     */
    private class SearchListener implements ActionListener {

        /**
         * Searches the portfolio given the entries in textfields
         * @param a action from the search button
         */
        public void actionPerformed (ActionEvent a) {
            symbolEntered = symbolEntry.getText();
            keywordsEntered = keywordsEntry.getText();
            lowpriceEntered = lowpriceEntry.getText();
            highpriceEntered = highpriceEntry.getText();

            try {
                String print = GUI.portfolio.searchPrint(symbolEntered, keywordsEntered, lowpriceEntered, highpriceEntered);
                messageBox.setText(print);
            } catch (Exception e) {
                messageBox.setText(e.getMessage());
            }
        }
    }

    /**
     * Sets the window values for when user flips windows
     */
    public void setWindow(){

        symbolEntry.setText("");
        keywordsEntry.setText("");
        lowpriceEntry.setText("");
        highpriceEntry.setText("");

        messageBox.setText("");
    }

    /**
     * Constructor for the SearchWindow
     * @param layout
     */
    public SearchWindow (LayoutManager layout ) {

        super(layout);
        //CREATE SEACRH PANEL - top panel and botton (messagePanel);
        JPanel topPanel = new JPanel(new GridLayout(1,2));
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));

        //CREATE TOP PANEL - userEntryPanel and buttonPanel
        JPanel userEntryPanel = new JPanel();
        userEntryPanel.setLayout(new BoxLayout(userEntryPanel, BoxLayout.Y_AXIS));
        JPanel buttonPanel = new JPanel();

        //CREATE USER ENTRY PANEL - searchTitle, entrySpace Panel(labelPanel, valuePanel);
        JLabel searchTitle = new JLabel("Searching investments");
        userEntryPanel.add(searchTitle);
        JPanel entrySpacePanel = new JPanel(new BorderLayout(20,30));


        JPanel labelPanel = new JPanel(new GridLayout(4,1)); 

        JLabel symbolLabel = new JLabel("Symbol");
        labelPanel.add(symbolLabel);
        JLabel nameLabel = new JLabel("Name keywords");
        labelPanel.add(nameLabel);
        JLabel lowLabel = new JLabel("Low price");
        labelPanel.add(lowLabel);
        JLabel highLabel = new JLabel("High price");
        labelPanel.add(highLabel);
        
        JPanel valuePanel = new JPanel(new GridLayout(4,1));

        symbolEntry = new JTextField(10); //actionEvents needed
        valuePanel.add(symbolEntry);
        keywordsEntry = new JTextField(25);
        valuePanel.add(keywordsEntry);
        lowpriceEntry = new JTextField(10);
        valuePanel.add(lowpriceEntry);
        highpriceEntry = new JTextField(10);
        valuePanel.add(highpriceEntry);

        entrySpacePanel.add(labelPanel, BorderLayout.WEST);
        entrySpacePanel.add(valuePanel, BorderLayout.CENTER);
        userEntryPanel.add(entrySpacePanel);

        topPanel.add(userEntryPanel);

        //CREATE BUTTON PANEL - reset and search
        JPanel bpanel = new JPanel();
        bpanel.setLayout(new BoxLayout(bpanel, BoxLayout.Y_AXIS));
        JButton resetButton = new JButton("Reset");
        resetButton.setPreferredSize(new Dimension(100, 100));
        resetButton.addActionListener(new ResetValuesListener());
        bpanel.add(resetButton);

        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 100));
        searchButton.addActionListener(new SearchListener());
        bpanel.add(searchButton);

        buttonPanel.add(bpanel);

        topPanel.add(buttonPanel);

        //CREATE MESSAGE PANEL - text area
        JLabel messageBoxLabel = new JLabel("Search Results");
        messagePanel.add(messageBoxLabel);

        JPanel messageBoxPanel = new JPanel(new GridLayout(1,1));
        messageBox = new JTextArea(100,100);
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