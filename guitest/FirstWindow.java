package guitest;

import javax.swing.JFrame;
//import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;


public class FirstWindow extends JFrame {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 400;

    public FirstWindow(){
        //super();
        super("Border Layout Demonstration");
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setLayout(new BorderLayout());

        JLabel label1 = new JLabel("First label");
        add(label1, BorderLayout.NORTH);

        JLabel label2 = new JLabel("Second label");
        add(label2, BorderLayout.SOUTH);

        JLabel label3 = new JLabel("Third label");
        add(label3, BorderLayout.CENTER);


        //JButton endButton = new JButton ("Click to end program");
        //endButton.addActionListener(new EndingListener());
        //add(endButton);
    }
}