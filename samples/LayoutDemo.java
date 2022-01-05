import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LayoutDemo extends JFrame {

   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;

   public LayoutDemo() {
      super();
      prepareGUI();
      showBorderLayout();
   }

   //This is a button listener class implemented for the South button
   private class MyListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
          statusLabel.setText("South");
      }
   }

   private void prepareGUI() {
      setTitle("Java SWING Examples");
      setSize(400, 400);
      setLayout(new GridLayout(3, 1));
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      headerLabel = new JLabel("", JLabel.CENTER);
      statusLabel = new JLabel("The status Label", JLabel.CENTER);
      statusLabel.setSize(350, 100);

      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout()); 
      // Flowlayout simply lays out components in a single row

      add(headerLabel);
      add(controlPanel);
      add(statusLabel);
   }

   private void showBorderLayout() {
      headerLabel.setText("Layout in action: BorderLayout");

      JPanel panel = new JPanel();
      panel.setBackground(Color.darkGray);
      panel.setSize(300, 300);

      //Creating the layout where the buttons are to be placed
      BorderLayout layout = new BorderLayout();
      layout.setHgap(10);
      layout.setVgap(10);

      //Creating South button
      JButton South = new JButton("South");
      //Creating an instance of the action perfomed when button is clicked
      MyListener s = new MyListener();
      //Adding action to the button
      South.addActionListener(s);

      panel.setLayout(layout);
      panel.add(new JButton("Center"), BorderLayout.CENTER);
      panel.add(new JButton("Line Start"), BorderLayout.LINE_START);
      panel.add(new JButton("Line End"), BorderLayout.LINE_END);
      panel.add(new JButton("East"), BorderLayout.EAST);
      panel.add(new JButton("West"), BorderLayout.WEST);
      panel.add(new JButton("North"), BorderLayout.NORTH);
      panel.add(South, BorderLayout.SOUTH);

      controlPanel.add(panel);
   }

   public static void main(String[] args) {
      LayoutDemo layoutDemo = new LayoutDemo();
      layoutDemo.setVisible(true);
   }
}
