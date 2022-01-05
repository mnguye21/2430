package a3; 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent; 

public class GUI extends JFrame {

    public static final int WIDTH = 700;
    public static final int HEIGHT = 500; 

    public static Portfolio portfolio = new Portfolio();

    private JPanel mainPanel;
    private BuyWindow buyPanel; 
    private SellWindow sellPanel;
    private UpdateWindow updatePanel; 
    private GetGainWindow getGainPanel;
    private SearchWindow searchPanel;

    /**
     * Listener for the command menu bar
     */
    private class CommandListener implements ActionListener {

        /**
         * Opens the window that the user selects by setting visibility
         * @param e action from the command menu bar
         */
        public void actionPerformed (ActionEvent e) {

            String actionCommand = e.getActionCommand( );

            if (actionCommand.equals("BUY")){

                //set visibles panels
                buyPanel.setWindow();
                mainPanel.setVisible(false);
                buyPanel.setVisible(true);
                sellPanel.setVisible(false);
                updatePanel.setVisible(false);
                getGainPanel.setVisible(false);
                searchPanel.setVisible(false);

            } else if (actionCommand.equals("SELL")){

                //set visible panels
                sellPanel.setWindow();
                mainPanel.setVisible(false);
                buyPanel.setVisible(false);
                sellPanel.setVisible(true);
                updatePanel.setVisible(false);
                getGainPanel.setVisible(false);
                searchPanel.setVisible(false);

            } else if (actionCommand.equals("UPDATE")){

                //set visible panels
                updatePanel.setWindow();
                mainPanel.setVisible(false);
                buyPanel.setVisible(false);
                sellPanel.setVisible(false);
                updatePanel.setVisible(true);
                getGainPanel.setVisible(false);
                searchPanel.setVisible(false);
            } else if (actionCommand.equals("GET GAIN")){

                //set visible panels
                getGainPanel.setWindow();
                mainPanel.setVisible(false);
                buyPanel.setVisible(false);
                sellPanel.setVisible(false);
                updatePanel.setVisible(false);
                getGainPanel.setVisible(true);
                searchPanel.setVisible(false);
                //totalGain = portfolio.getTotalGain();
            } else if (actionCommand.equals("SEARCH")){

                //set visible panels
                searchPanel.setWindow();
                mainPanel.setVisible(false);
                buyPanel.setVisible(false);
                sellPanel.setVisible(false);
                updatePanel.setVisible(false);
                getGainPanel.setVisible(false);
                searchPanel.setVisible(true);
            } else if (actionCommand.equals("QUIT")){
                System.exit(0);
            } 
        }
    }
    public static void main(String[] args)
    {
        GUI ePortfolio = new GUI( );
        ePortfolio.setVisible(true);
    }

    /**
     * Constructor for GUI
     */
    public GUI(){

        //REGULAR INITIALIZATION
        super("ePortfolio");
        prepareMenu();
    }

    /**
     * Function to prepare the menu options
     */
    private void prepareMenu(){

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        setPanels();

        //Create the Menu Bar
        setMenuBar();
    }

    /**
     * Sets all panels
     */
    private void setPanels(){

        setMenuPanel();
        setBuyPanel();
        setSellPanel();
        setUpdatePanel();
        setGetGainPanel();
        setSearchPanel();
    }

    /**
     * Sets the menu panel
     */
    private void setMenuPanel(){

        mainPanel = new JPanel(new GridLayout(2,1));
        mainPanel.setVisible(true);
        mainPanel.setBackground(Color.LIGHT_GRAY);

        JPanel topPanel = new JPanel (new BorderLayout());
        JPanel bottomPanel = new JPanel (new BorderLayout());
        JLabel menuLabel1 = new JLabel ("Welcome to ePortfolio.");
        JLabel menuLabel2 = new JLabel ("<html> Choose a command from the “Commands” menu to buy or sell an investment, update prices for all investments, get gain for the portfolio, search for relevant investments, or quit the program. </html>");
        topPanel.add(menuLabel1, BorderLayout.CENTER);
        bottomPanel.add(menuLabel2, BorderLayout.NORTH);
        mainPanel.add(topPanel);
        mainPanel.add(bottomPanel);

        add(mainPanel);
    }

    /**
     * Sets the menu bar
     */
    private void setMenuBar(){

        JMenu commandMenu = new JMenu("Commands");

        JMenuItem buy = new JMenuItem("BUY");
        buy.addActionListener(new CommandListener());
        commandMenu.add(buy);

        JMenuItem sell = new JMenuItem("SELL");
        sell.addActionListener(new CommandListener());
        commandMenu.add(sell);

        JMenuItem update = new JMenuItem("UPDATE");
        update.addActionListener(new CommandListener());
        commandMenu.add(update);

        JMenuItem getGain = new JMenuItem("GET GAIN");
        getGain.addActionListener(new CommandListener());
        commandMenu.add(getGain);

        JMenuItem search = new JMenuItem("SEARCH");
        search.addActionListener(new CommandListener());
        commandMenu.add(search);

        JMenuItem quit = new JMenuItem("QUIT");
        quit.addActionListener(new CommandListener());
        commandMenu.add(quit);

        JMenuBar bar = new JMenuBar();
        bar.add(commandMenu); 
        setJMenuBar(bar);
    }

    /**
     * Sets the buy panel
     */
    private void setBuyPanel(){

        buyPanel = new BuyWindow(new GridLayout(2,1));
        buyPanel.setVisible(false);
        add(buyPanel);
    }
    /**
     * Sets the sell Panel
     */
    private void setSellPanel(){
        
        sellPanel = new SellWindow(new GridLayout(2,1));
        sellPanel.setVisible(false);
        add(sellPanel);
    }
    /**
     * Sets the update panel
     */
    private void setUpdatePanel(){
        updatePanel = new UpdateWindow(new GridLayout(2,1));
        updatePanel.setVisible(false);
        add(updatePanel);
    }
    /**
     * Sets the gain panel
     */
    private void setGetGainPanel(){
        getGainPanel = new GetGainWindow();
        getGainPanel.setVisible(false);
        add(getGainPanel);
    }
    /**
     * Sets the search panel
     */
    private void setSearchPanel(){
        searchPanel = new SearchWindow(new GridLayout(2,1));
        searchPanel.setVisible(false);
        add(searchPanel);
    }

}