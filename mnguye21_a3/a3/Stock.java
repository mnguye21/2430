package a3;

import java.lang.IllegalArgumentException; 

public class Stock extends Investment {
    
    private static final double COMMISSION = 9.99; 

    /**
     * Constructor for Stock
     * @param symbol
     * @param name
     * @param quantity
     * @param price
     * @throws IllegalArgumentException
     */
    public Stock (String symbol, String name, int quantity, double price) throws IllegalArgumentException {
        
        super (symbol, name, quantity, price); 
        try {
            setBookValue(calculateBookValue(quantity, price)); 
            setGain(0);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Bookvalue could not be updated. ");
        }
    }

    /**
     * Copy constructor for stock
     * @param other
     * @throws IllegalArgumentException
     */
    public Stock (Stock other) throws IllegalArgumentException {
        this(other.getSymbol(), other.getName(), other.getQuantity(), other.getPrice());
    }

    //inherits accessors and mutator methods

    /** Calculates gain
     * @param quantity
     * @param price
     */
    public double calculateGain (int quantity, double price){
        
        return ((quantity * price - COMMISSION) - (getBookValue() * (quantity/getQuantity())));
    } 
      
    /** Calculates book value
     * @param quantity
     * @param price
     */
    public double calculateBookValue(int quantity, double price) {
        return ((quantity*price) + COMMISSION);
    }  

    /**
     * Adds quantity amount to the existing quantity of an investment
     * @param quantity
     * @param price
     */
    @Override
    public void add(int quantity, double price) throws IllegalArgumentException {
        
        super.add(quantity,price);
        try {
            setBookValue(getBookValue() + (quantity * price) + COMMISSION);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
        
    }

    /**
     * Updates the price, bookValue should change depending on the type of investment
     * @param price
     * @return void
     */
    @Override
    public void updatePrice(double price) throws IllegalArgumentException {

        try {
            super.updatePrice(price);
            setBookValue(calculateBookValue(getQuantity(), price));

        } catch (IllegalArgumentException e){ 
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Stock toString for printing
     * @return string containing the variables of the investment
     */
    @Override
    public String toString(){

        String print = ""; 

        print = super.toString() +
                "Bookvalue: (" + getQuantity() + " x " + getPrice() + ") + " + COMMISSION + " = " + getBookValue()+"\n" ;
        
        return print; 
    }
}