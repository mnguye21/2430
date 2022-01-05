package ePortfolio;

/**
 * Investment
 * @author May Nguyen
 */
public class Investment {

    private static final double COMMISSION = 9.99; 
    private String symbol;
    private String name;
    private int quantity; 
    private double price; 
    private double bookValue; 
    private double gain; 

    /**
     * Constructor 
     * @param symbol
     * @param name
     * @param quantity
     * @param price
     */
    public Investment (String symbol, String name, int quantity, double price){

        this.symbol = symbol;
        this.name = name; 
        this.quantity = quantity; 
        this.price = price; 
        this.bookValue = (quantity * price) + COMMISSION; 
        gain = 0; 
    }

    /**
     * Constructor
     * @param symbol
     * @param name
     * @param quantity
     * @param price
     * @param bookValue
     */
    public Investment (String symbol, String name, int quantity, double price, double bookValue){

        this (symbol, name, quantity, price); 
        this.bookValue = bookValue; 
        setGain(0); 
    }

    /**
     * Comstructor
     * @param symbol
     * @param name
     */
    public Investment (String symbol, String name) {

        this(symbol, name, 0, 0, 0);
    }

    /**
     * Symbol accessor 
     * @return symbol of the investment
     */
    public String getSymbol(){
        return symbol; 
    }
    /**
     * Name accessor 
     * @return name of the investment
     */
    public String getName() {
        return name; 
    }
    /**
     * Quantity accessor 
     * @return quantity of the investment
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * Price accessor 
     * @return price of the investment
     */
    public double getPrice() {
        return price; 
    }
    /**
     * BookValue accessor 
     * @return bookValue of the investment
     */
    public double getBookValue() {
        return bookValue; 
    }
    /**
     * Gain accessor 
     * @return gain of the investment
     */
    public double getGain() {
        return gain; 
    }

    /**
     * Symbol mutator
     * @param symbol
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    /**
     * Name mutator
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Quantity mutator
     * @param quantity
     * @return true if successful
     */
    public boolean setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
            return true; 
        } else {
            return false;
        }
    }
    /**
     * Price mutator
     * @param price
     * @return true if successful
     */
    public boolean setPrice(double price) {
        if (price > 0) { 
            this.price = price;
            return true; 
        } else {
            return false; 
        }
    }
    /**
     * BookValue mutator
     * @param bookValue
     * @return true if successful
     */
    public boolean setBookValue(double bookValue) {
        if (bookValue > 0) {
            this.bookValue = bookValue;
            return true;
        } else {
            return false;
        }
    }
    /**
     * Gain mutator
     * @param gain
     */
    public void setGain(double gain) {
        this.gain = gain;
    }

    // OTHER FUNCTIONS 
    /**
     * Adds quantity amount to the existing quantity of an investment
     * @param quantity
     * @param price
     * @return true if successful
     */
    public boolean add(int quantity, double price) {

        boolean returnVal = false; 
        returnVal = setQuantity(this.quantity + quantity); 
        if (returnVal == true){
            returnVal = setPrice(price);
        }
        return returnVal;
    }

    /**
     * Adds Adds quantity amount to the existing quantity of an investment and adds given bookValue
     * @param quantity
     * @param price
     * @param bookValue
     * @return true if successful
     */
    public boolean addFromFile(int quantity, double price, double bookValue){

        boolean returnVal = false; 
        returnVal = setQuantity(this.quantity + quantity); 
        if (returnVal == true) {
            returnVal = setPrice(price);
        }
        if (returnVal == true){
            returnVal = setBookValue(bookValue);
        }

        return returnVal;
    }

    /**
     * Removes "quantity" amount from the quantity of an investment
     * @param quantity
     * @param price
     * @param fee
     * @return returnVal == 1 if theres an invalid input error, 0 if successful 3 if completely sold
     */
    public int remove(int quantity, double price, double fee) {

        int returnVal = -1; 
        boolean success = false;

        if (this.quantity < quantity){
            returnVal = 1; 
        } else {
            success = setPrice(price);
            if (success == true){
                this.gain = (quantity * price - fee) - (this.bookValue * (quantity/this.quantity));
                this.bookValue = this.bookValue - (this.bookValue * (quantity/this.quantity));
                this.quantity -= quantity;  
                returnVal = 0;
            } else {
                returnVal = 1;
            }
        } 

        if (this.quantity == 0) {
            returnVal = 3; 
        }
        return returnVal; 
    }

    /**
     * Updates the price, bookValue should change depending on the type of investment
     * @param price
     * @return true if successful
     */
    public boolean updatePrice(double price){

        return setPrice(price);
    }
    
    /**
     * Investment toString for printing
     * @return string containing the variables of the investment
     */
    public String toString(){

        String print = ""; 

        print = symbol + " " + name + "\n" +
                "Quantity: " + quantity + "\n" +
                "Price: " + price + "\n"; 
        return print; 
    }

    /**
     * Creates and returns a string that follows the proper format to be outputted to a file
     * @return print - investment string that follows proper format
     */
    public String toFileString() {

        String print = "";
        print = "symbol = \"" + symbol + "\"\n" +
                "name = \"" + name + "\"\n" + 
                "quantity = \"" + quantity + "\"\n" +
                "price = \"" + price + "\"\n" + 
                "bookValue = \"" + bookValue + "\n";

        return print;
    }

    
}