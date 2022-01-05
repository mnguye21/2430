package mnguye21_a1;

import java.util.Scanner; 
import java.util.StringTokenizer; 
import java.util.ArrayList; 

public class Main {

    public static void commandBuy(Portfolio portfolio, Scanner keyboard){

        String input = "";
        String symbol = "";
        String name = ""; 
        int quantity = 0; 
        double price = 0;
        boolean exists = false; 
        
        while (!(input.equalsIgnoreCase("s")
                || input.equalsIgnoreCase("1")
                ||input.equalsIgnoreCase("m")
                || input.equalsIgnoreCase("2") 
                || input.equalsIgnoreCase("c") 
                || input.equalsIgnoreCase("3"))){
    
            System.out.println("Investment Options\n\n1. STOCK\n2. MUTUAL FUND\n3. CANCEL\n\n");
    
            System.out.println("Select the kind of investment you would like to buy: ");
            input = keyboard.nextLine().trim().substring(0,1); 
    
            if (!(input.equalsIgnoreCase("s")
                || input.equalsIgnoreCase("1")
                ||input.equalsIgnoreCase("m")
                || input.equalsIgnoreCase("2") 
                || input.equalsIgnoreCase("c") 
                || input.equalsIgnoreCase("3"))){
    
                System.out.println("Invalid input. Please select one of the valid options. \n"+
                                    "Valid inputs include 1-3 or the listed commands below.\n\n" +
                                    "Press enter to continue.\n");
                keyboard.nextLine();
            }
        }
    
        if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("1")){ 
    
            System.out.println("BUYING STOCK\n\n"); 
    
            System.out.println("Enter symbol: ");
            symbol = keyboard.nextLine(); 
    
    
            if (portfolio.contains(symbol, 1)) {
    
                System.out.println("\nEnter quantity: ");
                quantity = keyboard.nextInt();
                System.out.println("\nEnter price: "); 
                price = keyboard.nextDouble(); 
                
                portfolio.buy(symbol, quantity, price, 1); 
                
            } else {
    
                System.out.println("\nEnter name: ");
                name = keyboard.nextLine(); 
                System.out.println("\nEnter quantity: ");
                quantity = keyboard.nextInt();
                System.out.println("\nEnter price: "); 
                price = keyboard.nextDouble(); 
    
                portfolio.buy(symbol, name, quantity, price, 1); 
            }

            System.out.println("\nStock purchased. Press enter to return to main menu.\n");
            keyboard.nextLine();
            
        } else if (input.equalsIgnoreCase("m") || input.equalsIgnoreCase("2")){
    
            System.out.println("BUYING MUTUAL FUND\n\n"); 
    
            System.out.println("Enter symbol: ");
            symbol = keyboard.nextLine(); 
    
    
            if (portfolio.contains(symbol, 1)) {
    
                System.out.println("\nEnter quantity: ");
                quantity = keyboard.nextInt();
                System.out.println("\nEnter price: "); 
                price = keyboard.nextDouble();
    
                portfolio.buy(symbol, quantity, price, 2); 
                
            } else {
    
                System.out.println("\nEnter name: ");
                name = keyboard.nextLine(); 
                System.out.println("\nEnter quantity: ");
                quantity = keyboard.nextInt();
                keyboard.nextLine();
                System.out.println("\nEnter price: "); 
                price = keyboard.nextDouble(); 
    
                portfolio.buy(symbol, name, quantity, price, 2); 
            }
            System.out.println("\nMutual fund purchased. Press enter to return to main menu.\n");
            keyboard.nextLine();

        } else if (input.equalsIgnoreCase("c") || input.equalsIgnoreCase("3")){
    
            System.out.println("Returning to main menu... Press enter to continue\n\n"); 
            keyboard.nextLine();
        }
    
    }
    public static void commandSell(Portfolio portfolio, Scanner keyboard){
    
        String symbol = ""; 
        int quantity = 0; 
        double price = 0; 
        int sell = 0;
    
        do {
    
            System.out.println("Enter the symbol of the investment you'd like to sell or enter CANCEL to exit: "); 
            symbol = keyboard.next(); 
    
            if (portfolio.contains(symbol, 1)) { 
                System.out.println("Enter the quantity you would like to sell: ");
                quantity = keyboard.nextInt();
                System.out.println("Enter the price of the investment: ");
                price = keyboard.nextDouble(); 
                if (sell  == 0){
                    System.out.println("Stock sold.\n\n");
                } else if (sell ==1) {
                    System.out.println("Not enough stock quantity to sell " + quantity + " amount. Please try again\n\n");
                } else if (sell == 2) {
                    System.out.println("Symbol not found. Please try again and double check your entry. \n\n");
                }
            } else if (portfolio.contains(symbol, 2)) { 
                System.out.println("Enter the quantity you would like to sell: ");
                quantity = keyboard.nextInt();
                System.out.println("Enter the price of the investment: ");
                price = keyboard.nextDouble(); 
                portfolio.sell(symbol, quantity, price, 2);
                System.out.println("Mutual Fund sold.\n\n");
                
            } else if (symbol.equalsIgnoreCase("CANCEL")){

                System.out.println("Cancelling option...");
            } else {
    
                System.out.println("Couldn't find that investment in your portfolio.\n\n");
            }
        } while (!portfolio.contains(symbol, 1)&&!portfolio.contains(symbol, 2)&&!symbol.equalsIgnoreCase("CANCEL")); 

        System.out.println("Returning to menu. Press enter to continue.\n\n");
        keyboard.nextLine();
    }
    public static void commandUpdate(Portfolio portfolio, Scanner keyboard){

        double newPrice = 0;
        
        for (int i = 0; i < portfolio.getStockList().size(); i++){
            System.out.println("Update price of: " + portfolio.getStockList().get(i).toString() + "\n\n");
            System.out.println("Enter the new price: ");
            newPrice = keyboard.nextDouble();
            portfolio.update(i, newPrice, 1);
            System.out.println("Stock updated. Press enter to update next stock. \n\n");
            keyboard.nextLine();
        }
        for (int i = 0; i < portfolio.getMutualFundList().size(); i++){
            System.out.println("Update price of: " + portfolio.getMutualFundList().get(i).toString() + "\n\n");
            System.out.println("Enter the new price: ");
            newPrice = keyboard.nextDouble();
            portfolio.update(i, newPrice, 2);
            System.out.println("Mutual fund updated. Press enter to update next mutual fund. \n\n");
            keyboard.nextLine();
        }

        System.out.println("All investment prices have been updated. Press enter to return to menu\n\n");
        keyboard.nextLine();
    }
    public static void commandGetGain(Portfolio portfolio, Scanner keyboard){
        portfolio.getGain(); 
    }
    public static void commandSearch(Portfolio portfolio, Scanner keyboard){
    
    }
    
    
    public static void main(String[] args){

        Scanner keyboard = new Scanner(System.in);
        String input = "";  
        Portfolio portfolio = new Portfolio(); 

        System.out.println("Welcome to your ePortfolio.\n\n");

        while (!input.equalsIgnoreCase("q") && !input.equals("6")){

            System.out.println("Please select an option below: \n");
            System.out.println("1. BUY \n2. SELL\n3. UPDATE\n4. GET GAIN\n5. SEARCH\n6. QUIT\n\n");

            System.out.println("Select your option: ");

            input = keyboard.nextLine().trim().substring(0,1); 

            if (input.equalsIgnoreCase("b") || input.equals("1")){

                System.out.println("SELECTED BUY\n\n");
                commandBuy(portfolio, keyboard); 
                System.out.println("BUY COMPLETED. Press enter to continue\n");
                keyboard.nextLine();

            } else if (input.equalsIgnoreCase("s")|| input.equals("2")){

                System.out.println("SELECTED SELL\n\n");
                commandSell(portfolio, keyboard); 

            } else if (input.equalsIgnoreCase("u")|| input.equals("3")){

                System.out.println("SELECTED UPDATE\n\n");
                commandUpdate(portfolio, keyboard);

            } else if (input.equalsIgnoreCase("g")|| input.equals("4")){

                System.out.println("SELECTED GET GAIN\n\n");
                commandGetGain(portfolio, keyboard);

            } else if (input.equalsIgnoreCase("s")|| input.equals("5")){

                System.out.println("SELECTED SEARCH\n\n");
                commandSearch(portfolio, keyboard);

            } else if (!input.equalsIgnoreCase("q") && !input.equals("6")){

                System.out.println("Sorry, please reenter the command as one of the options available. Valid inputs include numbers 1-6 or any of the listed commands above. Try again: "); 
            } 
        }

        System.out.println("SELECTED QUIT\n\n");
        System.out.println("Exiting program...");
        keyboard.close();
        
    }
}