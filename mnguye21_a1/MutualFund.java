package mnguye21_a1; 

public class MutualFund {
    
    private static final double REDEMPTION = 45.00; 
    private String symbol;
    private String name;
    private int quantity; 
    private double price; 
    private double bookValue; 
    private double gain; 

    // Constructors
    public MutualFund (){

    }

    public MutualFund (String symbol, String name, int quantity, double price){

        this.symbol = symbol;
        this.name = name; 
        this.quantity = quantity; 
        this.price = price; 
        this.bookValue = (quantity * price); 
        gain = 0; 
    }

    // Accessors
    public String getSymbol(){
        return symbol; 
    }
    public String getName() {
        return name; 
    }
    public int getQuantity() {
        return quantity;
    }
    public double getPrice() {
        return price; 
    }
    public double getBookValue() {
        return bookValue; 
    }
    public double getGain() {
        return gain; 
    }

    // Mutators
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setBookValue(double bookValue) {
        this.bookValue = bookValue;
    }
    public void setGain(double gain) {
        this.gain = gain;
    }

    public void add(int quantity, double price) {

        this.quantity += quantity; 
        this.price = price; 
        bookValue += (quantity * price); 
    }
    public int remove(int quantity, double price) {

        if (this.quantity < quantity) {
            return 1;
        } else {
            this.price = price; 
            gain = (quantity * price - REDEMPTION) - (bookValue * (quantity/this.quantity)); 
            bookValue = bookValue - (bookValue * (quantity/this.quantity));
            this.quantity -= quantity; 
            return 0;
        }
    }
    public String toString(){

        String print = ""; 

        print = symbol + " " + name + "\n" +
                "Quantity: " + quantity + "\n" +
                "Price: " + price + "\n" + 
                "Bookvalue: " + quantity + " x " + price + " = " + bookValue ;
        
        return print; 
    }
}