/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;

public class FlowLayoutJFrame extends JFrame
{
    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    public FlowLayoutJFrame( )
    {
        super("FlowLayout Demonstration" );
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout( ));

        JLabel label1 = new JLabel("First label");
        add(label1); 

        JLabel label2 = new JLabel("Second label");
        add(label2); 

        JLabel label3 = new JLabel("Third label");
        add(label3); 
        
        //JLabel label4 = new JLabel("Fourth label");
        //add(label4); 
        
        //JLabel label5 = new JLabel("Fifth label");
        //add(label5);
    }
    
    public static void main(String[] args) 
    {
        FlowLayoutJFrame gui = new FlowLayoutJFrame( );
        gui.setVisible(true);
    }

}
