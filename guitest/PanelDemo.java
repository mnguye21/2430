import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout; 
import java.awt.Color; 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelDemo {
    
    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;
    private JPanel redPanel;
    private JPanel whitePanel; 
    private JPanel bluePanel;

    public static void main(String[] args){
        PanelDemo gui = new PanelDemo();
        gui.setVisible(); 
    }

    public PanelDemo(){
        super("Panel Demonstration");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); 

        JPanel biggerPanel = new JPanel();
        biggerPanel.setLayout(setGridLayout(new GridLayout (1,3)));
        
        redPanel = new JPanel();
        redPanel.setBackground(Color.LIGHT_GRAY);
        biggerPanel.add(redPanel);

        whitePanel = new JPanel();
        whitePanel.setBackground(Color.LIGHT_GRAY);
        biggerPanel.add(whitePanel);

        bluePanel = new JPanel();
        bluePanel.setBackground(Color.LIGHT_GRAY);
        biggerPanel.add(bluePanel);

        add(biggerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setLayout(new FlowLayout());

        JButton redButton = new JButton("Red");
        redButton.setBackground(Color.RED);
        redButton.addActionListener(this); //object of class PanelDemo is the action listener
        buttonPanel.add(redButton);

        JButton whiteButton = new JButton("White");
        whiteButton.setBackground(Color.WHITE);
        whiteButton.addActionListener(this); //object of class PanelDemo is the action listener
        buttonPanel.add(whiteButton);

        JButton blueButton = new JButton("Blue");
        blueButton.setBackground(Color.BLUE);
        blueButton.addActionListener(this); //object of class PanelDemo is the action listener
        buttonPanel.add(blueButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}