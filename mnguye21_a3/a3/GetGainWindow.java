package a3; 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GetGainWindow extends JPanel {

    private JTextField totalGain;
    private JTextArea messageBox;

    /**
     * Resets the window's values for when user flips between windows
     */
    public void setWindow(){
        
        totalGain.setText(String.valueOf(GUI.portfolio.getTotalGain()));
        messageBox.setText(GUI.portfolio.printIndividualGains());
    }
    /**
     * Constructor for the get gain window
     */
    public GetGainWindow () {

        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel getGainTitle = new JLabel("Getting Total Gain");
        add(getGainTitle);

        JPanel topPanel = new JPanel();

        JLabel totalGainLabel = new JLabel ("Total Gain");
        topPanel.add(totalGainLabel);
        totalGain = new JTextField(30);
        totalGain.setEditable(false);
        totalGain.setText(String.valueOf(GUI.portfolio.getTotalGain()));
        topPanel.add(totalGain);

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        
        JLabel messageBoxLabel = new JLabel("Individual Gains");
        messagePanel.add(messageBoxLabel);

        JPanel messageBoxPanel = new JPanel(new GridLayout(1,1));
        messageBox = new JTextArea(100,100);
        messageBox.setEditable(false);
        messageBox.setText(GUI.portfolio.printIndividualGains());
        JScrollPane scrolledText = new JScrollPane(messageBox);
        scrolledText.setHorizontalScrollBarPolicy(
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolledText.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        messageBoxPanel.add(scrolledText);
        messagePanel.add(messageBoxPanel);

        add(topPanel);
        add(messagePanel);
    }
}