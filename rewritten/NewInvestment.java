package rewritten;

import java.io.IllegalArgumentException; 

public abstract class NewInvestment {
    
    private String symbol;
    private String name;
    private int quantity; 
    private double price; 
    private double bookValue; 
    private double gain; 

    //Constructor
    public NewInvestment (String symbol, String name, int quantity, double price) throws IllegalArgumentException{

        try {
            setSymbol(symbol); 
            setName(name); 
            setQuantity(quantity); 
            setPrice(name);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage() + "Investment could not be created. ");
        }
    }
    public NewInvestment (NewInvestment other) throws IllegalArgumentException {
        super(other.getSymbol(), other.getName(), other.getQuantity(), other.getPrice());
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
    public void setSymbol(String symbol) throws IllegalArgumentException{
        if (symbol.equals("")){
            throw new IllegalArgumentException("Symbol field empty. ");
        } else {
            this.symbol = symbol;
        }
    }
    /**
     * Name mutator
     * @param name
     */
    public void setName(String name) throws IllegalArgumentException{
        if (name.equals("")){
            throw new IllegalArgumentException("Name fied empty. ");
        } else {
            this.name = name;
        }
    }
    /**
     * Quantity mutator
     * @param quantity
     * @return true if successful
     */
    public void setQuantity(int quantity) throws IllegalArgumentException {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity field must be positive. ");
        } else {
            this.quantity = quantity;
        }
    }
    /**
     * Price mutator
     * @param price
     * @return true if successful
     */
    public void setPrice(double price) throws IllegalArgumentException {
        if (price < 0) { 
            throw new IllegalArgumentException("Price field must be negative. ");
        } else {
            this.price = price;
        }
    }
    /**
     * BookValue mutator
     * @param bookValue
     * @return true if successful
     */
    public void setBookValue(double bookValue) throws IllegalArgumentException {
        if (bookValue < 0) {
            throw new IllegalArgumentException("Bookvalue cannot be negative. ");
        } else {
            this.bookValue = bookValue;
        }
    }
    /**
     * Gain mutator
     * @param gain
     */
    public void setGain(double gain) {
        this.gain = gain;
    }

    public abstract double calculateGain (int quantity, double price);
    public abstract double calculateBookValue(int quantity, double price);

    /**
     * Adds quantity amount to the existing quantity of an investment
     * @param quantity
     * @param price
     */
    public void add(int quantity, double price) throws IllegalArgumentException {
        
        try {
            setQuantity(this.quantity + quantity);
            setPrice(price);
        } catch (Exception e){
            throw new IllegalArgumentException (e.getMessage() + "Could not add to investment. ");
        }
    }

    /**
     * Removes "quantity" amount from the quantity of an investment
     * @param quantity
     * @param price
     * @param fee
     * @return sold == true if completely sold
     */
    public boolean remove(int quantity, double price, double fee) throws IllegalArgumentException {

        boolean sold = false; 
        if (this.quantity < quantity){
            throw new IllegalArgumentException("Quantity wanting to sell is less than actual quantity. ");
        } else if (this.quantity == quantity) {
            sold = true;
        } else {
            try { 
                setPrice(price);
            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage);
            }
            
            setGain(calculateGain(quantity, price));
            setBookValue(this.bookValue - (this.bookValue * (quantity/this.quantity)));
            this.quantity -= quantity;  
        } 
        return sold;
    }
    /**
     * Updates the price, bookValue should change depending on the type of investment
     * @param price
     * @return void
     */
    public void updatePrice(double price) throws IllegalArgumentException {

        try {
            setPrice(price);
        } catch (Exception e){ 
            throw new IllegalArgumentException(e.getMessage());
        }
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

}