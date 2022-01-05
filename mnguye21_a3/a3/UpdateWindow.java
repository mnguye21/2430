package a3; 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent; 

public class UpdateWindow extends JPanel {

    private JTextField symbolEntry;
    private JTextField nameEntry;
    private JTextField priceEntry;
    private JTextArea messageBox;

    private String symbolEntered;
    private String priceEntered;
    private String message;
    private int index = 0; 

    /**
     * Listener for the prev button
     */
    private class PrevListener implements ActionListener {

        /**
         * Flips to the previous investment
         * @param e action from the prev button
         */
        public void actionPerformed (ActionEvent e) {
            
            if (index == 0){
                index = GUI.portfolio.getInvestments().size() - 1;
            } else {
                index--;
            }
            symbolEntry.setText(GUI.portfolio.getInvestments().get(index).getSymbol());
            nameEntry.setText(GUI.portfolio.getInvestments().get(index).getName());
            priceEntry.setText(String.valueOf(GUI.portfolio.getInvestments().get(index).getPrice()));
        }
    }
    
    /**
     * Listener for the next button
     */
    private class NextListener implements ActionListener {

        /**
         * Flips to the next investment
         * @param e action from the next button
         */
        public void actionPerformed (ActionEvent e) {
            if (index == GUI.portfolio.getInvestments().size() - 1){
                index = 0;
            } else {
                index++;
            }
            symbolEntry.setText(GUI.portfolio.getInvestments().get(index).getSymbol());
            nameEntry.setText(GUI.portfolio.getInvestments().get(index).getName());
            priceEntry.setText(String.valueOf(GUI.portfolio.getInvestments().get(index).getPrice()));
        }
    }
    
    /**
     * Listener for the update button
     */
    private class UpdateListener implements ActionListener { 

        /**
         * Updates the price of the current index's investment
         * @param a action from the update button
         */
        public void actionPerformed (ActionEvent a) {
            
            priceEntered = priceEntry.getText();

            try {
                GUI.portfolio.update(index, Double.valueOf(priceEntered));
                messageBox.append("Investment updated succesfully.\n");
            } catch (IllegalArgumentException e) {
                messageBox.append(e.getMessage() + "\n");
            }
        }
    }

    /**
     * Sets window values for when the user flips windows
     */
    public void setWindow(){
        
        if (GUI.portfolio.getInvestments().size() > 0){

            symbolEntry.setText(GUI.portfolio.getInvestments().get(0).getSymbol());
            nameEntry.setText(GUI.portfolio.getInvestments().get(0).getName());
            priceEntry.setText(String.valueOf(GUI.portfolio.getInvestments().get(0).getPrice()));

        } else {
            symbolEntry.setText("");
            nameEntry.setText("");
            priceEntry.setText("");
        }
    }

    /**
     * Constructor for the UpdateWindow
     * @param layout
     */
    public UpdateWindow (LayoutManager layout) {
        
        super(layout);
        JPanel topPanel = new JPanel(new GridLayout(1,2));
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));

        //CREATE TOP PANEL - userEntryPanel and buttonPanel
        JPanel userEntryPanel = new JPanel();
        userEntryPanel.setLayout(new BoxLayout(userEntryPanel, BoxLayout.Y_AXIS));
        JPanel buttonPanel = new JPanel();

        //CREATE USER ENTRY PANEL - buyTitle, entrySpace Panel(labelPanel, valuePanel);
        JLabel updateTitle = new JLabel("Updating investments");
        userEntryPanel.add(updateTitle);
        JPanel entrySpacePanel = new JPanel(new BorderLayout(20,30));

        JPanel labelPanel = new JPanel(new GridLayout(5,1)); 

        JLabel symbolLabel = new JLabel("Symbol");
        labelPanel.add(symbolLabel);
        JLabel nameLabel = new JLabel("Name");
        labelPanel.add(nameLabel);
        JLabel priceLabel = new JLabel("Price");
        labelPanel.add(priceLabel);
        
        JPanel valuePanel = new JPanel(new GridLayout(5,1));

        symbolEntry = new JTextField(10); //actionEvents needed
        symbolEntry.setEditable(false);
        valuePanel.add(symbolEntry);

        nameEntry = new JTextField(10);
        nameEntry.setEditable(false);
        valuePanel.add(nameEntry);

        priceEntry = new JTextField(10);
        valuePanel.add(priceEntry);

        entrySpacePanel.add(labelPanel, BorderLayout.WEST);
        entrySpacePanel.add(valuePanel, BorderLayout.CENTER);
        userEntryPanel.add(entrySpacePanel);

        topPanel.add(userEntryPanel);

        //CREATE BUTTON PANEL - reset and buy
        JPanel upanel = new JPanel();
        upanel.setLayout(new BoxLayout(upanel, BoxLayout.Y_AXIS));
        JButton prevButton = new JButton("Prev");
        prevButton.setPreferredSize(new Dimension(100, 30));
        prevButton.addActionListener(new PrevListener());
        upanel.add(prevButton);

        JButton nextButton = new JButton("Next");
        nextButton.setPreferredSize(new Dimension(100, 100));
        nextButton.addActionListener(new NextListener());
        upanel.add(nextButton);

        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(100, 100));
        saveButton.addActionListener(new UpdateListener());
        upanel.add(saveButton);

        buttonPanel.add(upanel);

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