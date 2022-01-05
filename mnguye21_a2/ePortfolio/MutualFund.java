package ePortfolio; 

/**
 * Mutual Fund
 * @author May Nguyen
 */
public class MutualFund extends Investment{
    
    private static final double REDEMPTION = 45.00; 

    /**
     * Constructor for Mutual Fund
     * @param symbol
     * @param name
     * @param quantity
     * @param price
     */
    public MutualFund (String symbol, String name, int quantity, double price){

        super (symbol, name, quantity, price); 
        this.setBookValue(quantity * price); 
        setGain(0);
    }

    /**
     * Constructor for Stock
     * @param symbol
     * @param name
     */
    public MutualFund (String symbol, String name){
        super (symbol, name); 
        setGain(0); 
    }

    /**
     * Constructor for mutual fund that sets bookValue
     * @param symbol
     * @param name
     * @param quantity
     * @param price
     * @param bookValue
     */
    public MutualFund (String symbol, String name, int quantity, double price, double bookValue){
        super (symbol, name, quantity, price, bookValue); 
    }



    /** Accessors inherited from investment */

    /** Mutators inherited from Investment */
    
    
    /**
     * Adds quantity amount to the existing quantity of the mutual fund 
     * Updates bookValue
     * @param quantity
     * @param price
     * @return true if successful
     */
    @Override
    public boolean add(int quantity, double price) {

        boolean returnVal = super.add(quantity,price);
        if (returnVal == true){
            setBookValue(getBookValue() + (quantity * price)); 
        } 

        return returnVal;
    }

    /**
     * Removes "quantity" amount from the quantity of mutual fund
     * @param quantity
     * @param price
     * @param fee
     * @return returnVal == 1 if theres an invalid input error, 0 if successful
     */
    @Override
    public int remove(int quantity, double price, double fee) {

        return super.remove(quantity, price, REDEMPTION);
    }

    /**
     * Updates the price, bookValue updated
     * @param price
     * @return true if successful
     */
    @Override
    public boolean updatePrice(double price){

        boolean returnVal = false;
        returnVal = super.updatePrice(price);
        if (returnVal == true){
            returnVal = setBookValue(getQuantity() * price);
        } 
        return returnVal;
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

    /**
     * Creates and returns a string that follows the proper format to be outputted to a file
     * @return print - mutualfund string that follows proper format
     */
    @Override
    public String toFileString (){

        String print = "";
        print = "type = \"mutualfund\"\n" + super.toFileString();
        return print; 
    }
}