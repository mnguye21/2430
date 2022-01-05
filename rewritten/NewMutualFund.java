package rewritten;

import rewritten.NewInvestment;

public class NewMutualFund extends NewInvestment{
    
    private static final double REDEMPTION = 45.00; 

    /**
     * Constructor for Mutual Fund
     * @param symbol
     * @param name
     * @param quantity
     * @param price
     */
    public NewMutualFund (String symbol, String name, int quantity, double price) throws IllegalArgumentException{

        super (symbol, name, quantity, price); 
        try {
            setBookValue(calculateBookValue(quantity, price)); 
            setGain(0);
        } catch (Exception e) {
            throw new IllegalArgumentException("Bookvalue could not be updated ");
        }
    }

    public NewMutualFund (NewMutualFund other) throws IllegalArgumentException {
        this(other.getSymbol(), other.getName(), other.getQuantity(), other.getPrice());
    }

    //inherits accessors and mutator methods

    public double calculateGain (int quantity, double price){
        
        return ((quantity * price - REDEMPTION) - (getBookValue() * (quantity/getQuantity())));
    } 
      
    public double calculateBookValue(int quantity, double price){
        return (quantity*price);
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
            setBookValue(getBookValue() + (quantity * price));
        } catch (Exception e){
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

        } catch (Exception e){ 
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Mutual Fund toString for printing
     * @return string containing the variables of the investment
     */
    @Override
    public String toString(){

        String print = ""; 

        print = super.toString() +
                "Bookvalue: " + getQuantity() + " x " + getPrice() + " = " + getBookValue() ;
        
        return print; 
    }

}