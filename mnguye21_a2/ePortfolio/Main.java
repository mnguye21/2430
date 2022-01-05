package ePortfolio;

import java.util.Scanner; 
import java.util.StringTokenizer; 
import java.util.ArrayList; 
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException; 
import java.io.PrintWriter;
import java.io.File; 


/**
 * Main
 * @author May Nguyen
 */
public class Main {

    /**
     * Excecutes BUY command
     * @param portfolio
     * @param keyboard
     */
    public static void commandBuy(Portfolio portfolio, Scanner keyboard){

        String input = "";
        String symbol = "";
        String name = ""; 
        int quantity = 0; 
        double price = 0;
        boolean exists = false; 
        boolean success = false;
        
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
    
    
            if (portfolio.contains(symbol)) {
    
                System.out.println("\nEnter quantity: ");
                quantity = keyboard.nextInt();
                System.out.println("\nEnter price: "); 
                price = keyboard.nextDouble(); 
                
                success = portfolio.buy(symbol, quantity, price, 1); 
                
            } else {
    
                System.out.println("\nEnter name: ");
                name = keyboard.nextLine(); 
                System.out.println("\nEnter quantity: ");
                quantity = keyboard.nextInt();
                System.out.println("\nEnter price: "); 
                price = keyboard.nextDouble(); 
    
                success = portfolio.buy(symbol, name, quantity, price, 1); 
            }

            if (success) {
                System.out.println("Successfully purchased " + name + ".\n");
            } else {
                System.out.println("Could not purchase - invalid input for investment " + name);
            }

            System.out.println("\nPress enter to return to main menu.\n");
            keyboard.nextLine();
            
        } else if (input.equalsIgnoreCase("m") || input.equalsIgnoreCase("2")){
    
            System.out.println("BUYING MUTUAL FUND\n\n"); 
    
            System.out.println("Enter symbol: ");
            symbol = keyboard.nextLine(); 
    
    
            if (portfolio.contains(symbol)) {
    
                System.out.println("\nEnter quantity: ");
                quantity = keyboard.nextInt();
                System.out.println("\nEnter price: "); 
                price = keyboard.nextDouble();
    
                success = portfolio.buy(symbol, quantity, price, 2); 
                
            } else {
    
                System.out.println("\nEnter name: ");
                name = keyboard.nextLine(); 
                System.out.println("\nEnter quantity: ");
                quantity = keyboard.nextInt();
                keyboard.nextLine();
                System.out.println("\nEnter price: "); 
                price = keyboard.nextDouble(); 
    
                success = portfolio.buy(symbol, name, quantity, price, 2); 
            }

            if (success) {
                System.out.println("Successfully purchased " + name + ".\n");
            } else {
                System.out.println("Could not purchase - invalid input for investment " + name);
            }

            System.out.println("\nPress enter to return to main menu.\n");
            keyboard.nextLine();

        } else if (input.equalsIgnoreCase("c") || input.equalsIgnoreCase("3")){
    
            System.out.println("Returning to main menu... Press enter to continue\n\n"); 
            keyboard.nextLine();
        }
    
    }

    /**
     * Excecutes the SELL command
     * @param portfolio
     * @param keyboard
     */
    public static void commandSell(Portfolio portfolio, Scanner keyboard){
    
        String symbol = ""; 
        int quantity = 0; 
        double price = 0; 
        int sell = 0;
    
        do {
    
            System.out.println("Enter the symbol of the investment you'd like to sell or enter CANCEL to exit: "); 
            symbol = keyboard.next(); 
    
            if (portfolio.contains(symbol)) { 
                System.out.println("Enter the quantity you would like to sell: ");
                quantity = keyboard.nextInt();
                System.out.println("Enter the price of the investment: ");
                price = keyboard.nextDouble(); 
                if (sell  == 0){
                    System.out.println("Stock sold.\n\n");
                } else if (sell == 1) {
                    System.out.println("Not enough stock quantity to sell " + quantity + " amount. Please try again\n\n");
                } else if (sell == 2) {
                    System.out.println("Symbol not found. Please try again and double check your entry. \n\n");
                } 
            } else if (portfolio.contains(symbol)) { 
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
        } while (!portfolio.contains(symbol)&&!portfolio.contains(symbol)&&!symbol.equalsIgnoreCase("CANCEL")); 

        System.out.println("Returning to menu. Press enter to continue.\n\n");
        keyboard.nextLine();
    }

    /**
     * Excecutes the UPDATE commamnd
     * @param portfolio
     * @param keyboard
     */
    public static void commandUpdate(Portfolio portfolio, Scanner keyboard){

        double newPrice = 0;
        boolean success = false;
        
        for (int i = 0; i < portfolio.getInvestments().size(); i++){
            System.out.println("Update price of: " + portfolio.getInvestments().get(i).toString() + "\n\n");
            System.out.println("Enter the new price: ");
            newPrice = keyboard.nextDouble();
            success = portfolio.update(i, newPrice);
            if (success) {
                System.out.println("Investment updated. Press enter to update next stock. \n\n");
            } else {
                System.out.println("Invalid input. Investment could not be updated. Press enter to update next stock. ");
            }
            keyboard.nextLine();
        }
        System.out.println("All investment prices have been updated. Press enter to return to menu\n\n");
        keyboard.nextLine();
    }

    /**
     * Excecutes the GAIN command
     * @param portfolio
     * @param keyboard
     */
    public static void commandGetGain(Portfolio portfolio, Scanner keyboard){
        System.out.println("Total gain of the portfolio: " + portfolio.getTotalGain() + "\n");
    }

    /**
     * Excecutes the SEARCH command
     * @param portfolio
     * @param keyboard
     */
    public static void commandSearch(Portfolio portfolio, Scanner keyboard){

        String symbol = "";
        String keywords = "";
        String price = "";
    
        System.out.println ("\nEnter symbol to seach: ");
        symbol = keyboard.nextLine(); 

        System.out.println ("\nEnter keywords to search ");
        keywords = keyboard.nextLine ();

        System.out.println ("\nEnter price range to search: ");
        price = keyboard.nextLine();

        System.out.println("\n\nSearching all investments... Press enter to continue\n");
        keyboard.nextLine();

        ArrayList<Investment> searchMatches = portfolio.search(symbol, keywords, price);

        if (searchMatches.isEmpty()) {
            System.out.println("Sorry, there were no matches for your search. \n");
        } else {
            System.out.println("Here are the investments that match your search: \n\n");
            for (Investment i : searchMatches) {
                System.out.println(i.toString());
            }
        }
        System.out.println("Returning to main menu. Press enter to continue.\n");
        keyboard.nextLine();
    }

    /**
     * Excecutes the HASH SEARCH command
     * @param portfolio
     * @param keyboard
     */
    public static void commandHashSearch(Portfolio portfolio, Scanner keyboard){

        System.out.println("Enter the keywords you would like to search: ");
        String line = keyboard.nextLine(); 

        System.out.println("Searching...\n");
        System.out.println("Press enter to see the list of matching entries. \n");
        keyboard.nextLine();

        String [] keywords = line.toLowerCase().split("\\s+");

        ArrayList<Integer> indices = portfolio.hashSearch(keywords);

        if (!indices.isEmpty()){

            for (Integer i: indices) {

                System.out.println(portfolio.getInvestments().get(i));
            }
        } else {
            System.out.println("Sorry, no entries matched your search. \n");
        }

        System.out.println("Returning to main menu.. Press enter to continue.\n");
        keyboard.nextLine();
    }

    /**
     * Excecutes the INPUT FILE command
     * @param portfolio
     * @param keyboard
     * @param args
     */
    public static void commandInputFile(Portfolio portfolio, Scanner keyboard, String[] args){

        System.out.println("Please confirm that the file name was provided in the command line (Y/N): ");
        String input = keyboard.nextLine().trim().substring(0,1); 
        if (input.equalsIgnoreCase("y")){
            
            Scanner inputStream = null; 
            String path = new File("").getAbsolutePath(); 

            //load input file
            if (args.length > 0) {

                path = path + "/ePortfolio/" + args[0];
                try {
                    inputStream = new Scanner (new FileInputStream(path));
                } catch (FileNotFoundException e) {
                    System.out.println("Could not open file\n");
                    System.exit(0);
                }

                String line = "";
                String trim = "";
                String type = "";
                String symbol = "";
                String name = "";
                String quantity = "";
                String price = "";
                String bookValue = "";

                while (inputStream.hasNextLine()) {
                        line = inputStream.nextLine();
                        trim = line.substring(line.indexOf("\"")+1);
                        type = trim.substring(0, trim.length() -1);
                    
                        line = inputStream.nextLine();
                        trim = line.substring(line.indexOf("\"")+1);
                        symbol = trim.substring(0, trim.length() -1);

                        line = inputStream.nextLine();
                        trim = line.substring(line.indexOf("\"")+1);
                        name = trim.substring(0, trim.length() -1);

                        line = inputStream.nextLine();
                        trim = line.substring(line.indexOf("\"")+1);
                        quantity = trim.substring(0, trim.length() -1);
                        
                        line = inputStream.nextLine();
                        trim = line.substring(line.indexOf("\"")+1);
                        price = trim.substring(0, trim.length() -1);

                        line = inputStream.nextLine();
                        trim = line.substring(line.indexOf("\"")+1);
                        bookValue = trim.substring(0, trim.length() -1);

                    if (inputStream.hasNextLine()) {
                        inputStream.nextLine();
                    }

                    boolean success = portfolio.buyFromFile(type, symbol, name, quantity, price, bookValue);
                    if (success) {
                        System.out.println(name + " successfully purchased.");
                    } else {
                        System.out.println("Invalid input error - could not purchase investment (" + name + ")\n");
                    }

                }

                System.out.println("Investments loaded.\n");
    

            } else {
                System.out.println("Error. Missing number of arguments.\n\n");
            } 

            inputStream.close();

        } 

        System.out.println("Returning to main menu... Press enter to continue\n");
        keyboard.nextLine();
    } 
    /**
     * Excecutes the OUTPUT FILE command
     * @param portfolio
     * @param keyboard
     */
    public static void commandOutputFile(Portfolio portfolio, Scanner keyboard){

        System.out.println("Enter the file name you would like to write to: ");
        String line = keyboard.nextLine(); 

        while (line.equals("")) {
            System.out.println("Enter the file name you would like to write to: ");
            line = keyboard.nextLine(); 
        }

        String path = new File("").getAbsolutePath();
        path = path + "/ePortfolio/" + line;

        PrintWriter fileWriter = null; 
                
        try {

            fileWriter = new PrintWriter(new FileOutputStream(path));
        } catch (FileNotFoundException e) {

            System.out.println("Failed to write.");
            System.exit(0);
        }

        fileWriter.println(portfolio.toFileString());
        fileWriter.close();

        System.out.println("File created. Returning to menu. Press enter to continue.\n");
        keyboard.nextLine();

    }  

    
    public static void main(String[] args){

        Scanner keyboard = new Scanner(System.in);
        String input = "";  
        Portfolio portfolio = new Portfolio(); 

        System.out.println("Welcome to your ePortfolio.\n\n");

        while (!input.equalsIgnoreCase("q") && !input.equals("9")){

            System.out.println("Please select an option below: \n");
            System.out.println("1. BUY \n2. SELL\n3. UPDATE\n4. GET GAIN\n5. SEARCH\n6. HASH SEARCH\n7. INPUT FILE\n8. OUTPUT FILE\n9. QUIT\n\n");

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

            } else if (input.equalsIgnoreCase("h")|| input.equals("6")){

                System.out.println("SELECTED HASH SEARCH\n\n");
                commandHashSearch(portfolio, keyboard);

            } else if (input.equalsIgnoreCase("i")|| input.equals("7")){

                System.out.println("SELECTED INPUT FILE\n\n");
                commandInputFile(portfolio, keyboard, args);

            } else if (input.equalsIgnoreCase("o")|| input.equals("8")){

                System.out.println("SELECTED OUTPUT FILE\n\n");
                commandOutputFile(portfolio, keyboard);

            }else if (!input.equalsIgnoreCase("q") && !input.equals("9")){

                System.out.println("Sorry, please reenter the command as one of the options available. Valid inputs include numbers 1-6 or any of the listed commands above. Try again: "); 
            } 
        }

        System.out.println("SELECTED QUIT\n\n");
        System.out.println("Exiting program...");
        keyboard.close();
        
    }
}