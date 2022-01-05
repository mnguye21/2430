package a3;

import java.lang.IllegalArgumentException; 
import java.util.ArrayList; 
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Iterator;

public class Portfolio {

    private ArrayList<Investment> investments = new ArrayList<Investment>(); 
    private HashMap<String, ArrayList<Integer>> searchHashMap = new HashMap<String, ArrayList<Integer>>();

    /**
     * Accessor for the investments array list
     * @return investments array list
     */
    public ArrayList<Investment> getInvestments() {
        return investments; 
    }

    /**
     * Adds an investment to the list read from a file
     * @param type
     * @param symbol
     * @param name
     * @param quantity
     * @param price
     */

    public void buy (String type, String symbol, String name, String quantity, String price) throws IllegalArgumentException {

        if (this.exists(symbol)) { // the investment already exists

            int index = -1; 

            for (int i = 0; i < investments.size() && i!=-1; i++){
                if (investments.get(i).getSymbol().equalsIgnoreCase(symbol)){
                    index = i;
                    i=-2; 
                }
            }
            try {
                investments.get(index).add(Integer.valueOf(quantity), Double.valueOf(price));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
            //returnVal = investments.get(index).addFromFile(Integer.valueOf(quantity), Double.valueOf(price), Double.valueOf(bookValue));
        
        } else { //creating a new investment

            if (type.trim().equalsIgnoreCase("Stock")) {

                Stock newStock;

                try {
                    newStock = new Stock (symbol, name, Integer.valueOf(quantity), Double.valueOf(price)); 
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException ("Stock could not be added.\n");
                }
                
                investments.add(newStock);

            } else if (type.trim().equalsIgnoreCase("Mutual Fund")) { 
    
                MutualFund newMutualFund;
                
                try {
                    newMutualFund = new MutualFund (symbol, name, Integer.valueOf(quantity), Double.valueOf(price)); 
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException ("Mutual Fund could not be added.\n");
                }
                
                investments.add(newMutualFund);   
            }

            //update hashMap
            String [] keywords = name.toLowerCase().split("\\s+");

            for (int i = 0; i < keywords.length; i ++){ //put all keywords into the hash map

                if (searchHashMap.containsKey(keywords[i])) { //existing keyword
                    
                    searchHashMap.get(keywords[i]).add(investments.size()-1);
                } else {

                    searchHashMap.put(keywords[i], new ArrayList<Integer>()); // new keyword
                    searchHashMap.get(keywords[i]).add(investments.size()-1);
                }
            }
        }
    }

    /**
     * Remove an exisitng investment
     * @param symbol
     * @param quantity
     * @param price
     * @return flag == 0 if succesful, 1 if invalid input and 2 if invesment not found, 3 if completely sold
     */
    public void sell (String symbol, int quantity, double price) throws IllegalArgumentException {

        int index = -1; 

        //System.out.println("Selling : " + symbol + " " + quantity + " " + price + "\n");

        for (int i = 0; i < investments.size() && i!=-1; i++){
            if (investments.get(i).getSymbol().equalsIgnoreCase(symbol)){
                index = i; 
            }
        }
        System.out.println(index);
        
        if (index == -1){
            throw new IllegalArgumentException("Could not find an existing investment with that symbol");
        } else if (index != -1){
            boolean sold = false;
            try {
                sold = investments.get(index).remove(quantity, price);
            } catch (IllegalArgumentException e){
                throw new IllegalArgumentException (e.getMessage());
            }

            if (sold==true) {

                String[] keywords = investments.get(index).getName().toLowerCase().split("\\s+"); //update HashMap
                
                for (int i = 0; i < keywords.length; i++) {

                    Iterator <HashMap.Entry<String, ArrayList<Integer>>> iter = searchHashMap.entrySet().iterator();

                    while (iter.hasNext()) {
                        HashMap.Entry<String, ArrayList<Integer>> entry = iter.next(); 

                        if (entry.getKey().equalsIgnoreCase(keywords[i])){
                            entry.getValue().remove(Integer.valueOf(index));
                        }
                    }
                }
            
                investments.remove(index);
            }
        } 
    }

    /**
     * Update the price of an investment in the portfolio
     * @param index
     * @param price
     */
    public void update (int index, double price) throws IllegalArgumentException { 
        
        try { 
            investments.get(index).updatePrice(price);  
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        } 
    }

    /**
     * Checks if the investment list of the portfolio contains an investment with the given symbol
     * @param symbol
     * @return true if investment list contains an investment with the given symbol, false if not
     */
    public boolean exists(String symbol){

        boolean contains = false; 
        
        for (int i = 0; i < investments.size() && contains == false; i++){
            System.out.println(i);
            if (investments.get(i).getSymbol().equalsIgnoreCase(symbol)){
                contains = true; 
            }
        }

        return contains; 
    }

    /**
     * Sums the total gain of the porfolio
     * @return total gain (sum gains of the investments)
     */
    public double getTotalGain(){
        double sum = 0;
        for (Investment i : investments) {
            sum += i.getGain();
        }
        return sum;
    }

    /**
     * Prints the individual gains of each investment
     * @return
     */
    public String printIndividualGains() {
        String print = "";
        for (Investment i : investments) {
            print += "For investment: \n" + i.toString() + "Gain of :" + i.getGain() + "\n\n";
        }
        return print;
    }

    /**
     * Creates a string for printing search results
     * @param symbol
     * @param keywords
     * @param lowprice
     * @param highprice
     * @return printable search results
     * @throws IllegalArgumentException
     */
    public String searchPrint (String symbol, String keywords, String lowprice, String highprice)throws IllegalArgumentException { 
        ArrayList<Investment> foundInvestments; 

        try {
            foundInvestments = search (symbol, keywords, lowprice, highprice);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        String finalPrint = "Here are the investments we found using the entered values: \n";

        if (foundInvestments.size() > 0) {
            for (Investment i: foundInvestments){
                finalPrint += i.toString();
            }
        } else {
            finalPrint += "Sorry, we couldn't find any investments using the entered values.\n";
        }

        return finalPrint;
    }

    /**
     * Searches the investment list using the given parameters in a loop
     * @param symbol
     * @param keywords
     * @param price
     * @return searchMatches (list of all investments that match the given search keys)
     */

    public ArrayList<Investment> search (String symbol, String keywords, String lowprice, String highprice) throws IllegalArgumentException{

        int typeSearch = 0; 
        //this variable will indicate what kind of search to perform
        ArrayList<Investment> searchMatches = new ArrayList<Investment>();
        ArrayList<Investment> symbolMatches;
        ArrayList<Investment> keywordMatches;
        ArrayList<Investment> lowpriceMatches;
        ArrayList<Investment> highpriceMatches;

        if (!symbol.equals("")){
            typeSearch += 1;
        }
        if (!keywords.equals("")){
            typeSearch += 10;
        }
        if (!lowprice.equals("")){
            typeSearch += 100;
        }
        if (!highprice.equals("")){
            typeSearch += 1000;
        }

        switch (typeSearch) {
            case 1:
                // Search just symbol
                symbolMatches = findSymbolMatches(symbol); 
                searchMatches = symbolMatches;
                break;
            case 10:
                // Search just keywords
                keywordMatches = hashSearch(keywords);
                searchMatches = keywordMatches;
                break;
            case 100:
                // Search just low price
                lowpriceMatches =  findHigherMatches(lowprice);
                searchMatches = lowpriceMatches;
                break;
            case 1000:
                // Search just high price
                highpriceMatches =  findLowerMatches(highprice);
                searchMatches = highpriceMatches;
                break;
            case 11:
                // Search symbol and keywords
                symbolMatches = findSymbolMatches(symbol); 
                searchMatches = symbolMatches;
                keywordMatches = hashSearch(keywords);
                searchMatches.retainAll(keywordMatches);
                break;
            case 101:
                // Search symbol and low price boundary
                symbolMatches = findSymbolMatches(symbol); 
                searchMatches = symbolMatches;
                lowpriceMatches =  findHigherMatches(lowprice);
                searchMatches.retainAll(lowpriceMatches);
                break;
            case 1001:
                // Search symbol and highprice boundary
                symbolMatches = findSymbolMatches(symbol); 
                searchMatches = symbolMatches;
                highpriceMatches =  findLowerMatches(highprice);
                searchMatches.retainAll(highpriceMatches);
                break;
            case 110:
                // Search keywords and lowprice boundary
                keywordMatches = hashSearch(keywords); 
                searchMatches = keywordMatches;
                lowpriceMatches =  findHigherMatches(lowprice);
                searchMatches.retainAll(lowpriceMatches);
                break;
            case 1010:
                // Search keywords and highprice boundary
                keywordMatches = hashSearch(keywords); 
                searchMatches = keywordMatches;
                highpriceMatches =  findLowerMatches(highprice);
                searchMatches.retainAll(highpriceMatches);
                break;
            case 1100:
                // Search between high and low price
                highpriceMatches =  findLowerMatches(highprice);
                searchMatches = highpriceMatches;
                lowpriceMatches = findHigherMatches(lowprice);
                searchMatches.retainAll(lowpriceMatches);
                break;
            case 111:
                // Search symbol, keywords and low price
                symbolMatches = findSymbolMatches(symbol); 
                searchMatches = symbolMatches;
                keywordMatches = hashSearch(keywords);
                searchMatches.retainAll(keywordMatches);
                lowpriceMatches =  findHigherMatches(lowprice);
                searchMatches.retainAll(lowpriceMatches);
                break;
            case 1011:
                // Search symbol, keywords and high price
                symbolMatches = findSymbolMatches(symbol); 
                searchMatches = symbolMatches;
                keywordMatches = hashSearch(keywords);
                searchMatches.retainAll(keywordMatches);
                highpriceMatches =  findLowerMatches(highprice);
                searchMatches.retainAll(highpriceMatches);
                break;
            case 1101: 
                // Search symbol, lowprice and high price
                symbolMatches = findSymbolMatches(symbol); 
                searchMatches = symbolMatches;
                highpriceMatches =  findLowerMatches(highprice);
                searchMatches.retainAll(highpriceMatches);
                lowpriceMatches = findHigherMatches(lowprice);
                searchMatches.retainAll(lowpriceMatches);
                break;
            case 1110:
                // Search keywords, low price and high price
                keywordMatches = hashSearch(keywords); 
                searchMatches = keywordMatches;
                highpriceMatches =  findLowerMatches(highprice);
                searchMatches.retainAll(highpriceMatches);
                lowpriceMatches = findHigherMatches(lowprice);
                searchMatches.retainAll(lowpriceMatches);
                break;
            case 1111:
                // Search for all
                symbolMatches = findSymbolMatches(symbol); 
                searchMatches = symbolMatches;
                keywordMatches = hashSearch(keywords); 
                searchMatches.retainAll(keywordMatches);
                highpriceMatches =  findLowerMatches(highprice);
                searchMatches.retainAll(highpriceMatches);
                lowpriceMatches = findHigherMatches(lowprice);
                searchMatches.retainAll(lowpriceMatches);
                break;
            case 0: 
                throw new IllegalArgumentException("Cannot search with all empty fields. ");

        }

        return searchMatches;
    }

    /**
     * Hash searches given key words
     * @param userEntry
     * @return
     */
    public ArrayList<Investment> hashSearch(String userEntry) {

        String [] keywords = userEntry.toLowerCase().split("\\s+");

        ArrayList<Investment> keywordMatches = new ArrayList<Investment>(); 
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < keywords.length; i++) {

            Iterator <HashMap.Entry<String, ArrayList<Integer>>> iter = searchHashMap.entrySet().iterator();

            while (iter.hasNext()) {
                HashMap.Entry<String, ArrayList<Integer>> entry = iter.next(); 

                if (entry.getKey().equalsIgnoreCase(keywords[i])){ //match key
                    lists.add(entry.getValue());
                }
            }

            //now you have a list for the lists that contain the keywords
            //for however many keywords MATCHED, you will have that many lists in the list (list.size())
        }

        if (lists.size() > 0){

            ArrayList<Integer> indices = lists.get(0);
            for (int i = 1; i < lists.size(); i++) {

                indices.retainAll(lists.get(i));
            }

            for (Integer i: indices) {
                if (investments.get(i) instanceof Stock) {
                    keywordMatches.add(new Stock((Stock)investments.get(i)));
                } else if (investments.get(i) instanceof MutualFund) {
                    keywordMatches.add(new MutualFund((MutualFund) investments.get(i)));
                }
            }
        }
        return keywordMatches;
    }
    /**
     * Searches all matches that contain symbol
     * @param symbol
     * @return symbolMatches (an ArrayList of all matching symbols)
     */
    public ArrayList<Investment> findSymbolMatches (String symbol) {
        ArrayList<Investment> symbolMatches = new ArrayList<Investment>();

        for (Investment i : investments) {
            if (i.getSymbol().contains(symbol)){
                
                if (i instanceof Stock) {
                    symbolMatches.add(new Stock((Stock)i));
                } else if (i instanceof MutualFund) {
                    symbolMatches.add(new MutualFund((MutualFund)i));
                }
            }
        }
        return symbolMatches;
    }
    /**
     * Searches all price matches that are lower than high price
     * @param highprice
     * @return highpricerMatches (ArrayList of all investments with a lower price)
     */
    public ArrayList<Investment> findLowerMatches (String highprice) {
        ArrayList<Investment> highpriceMatches = new ArrayList<Investment>();

        for (Investment i : investments) { 
            if (i.getPrice() < Double.valueOf(highprice)) {

                if (i instanceof Stock) {
                    highpriceMatches.add(new Stock((Stock)i));
                } else if (i instanceof MutualFund) {
                    highpriceMatches.add(new MutualFund((MutualFund)i));
                }
            }
        }
        return highpriceMatches;
    }
    /**
     * Searches all price matches that are higher than low price
     * @param lowprice
     * @return lowpriceMatches (ArrayList of all investments with a higher price)
     */
    public ArrayList<Investment> findHigherMatches (String lowprice) { 
        ArrayList<Investment> lowpriceMatches = new ArrayList<Investment>();

        for (Investment i : investments) { 
            if (i.getPrice() > Double.valueOf(lowprice)) {

                if (i instanceof Stock) {
                    lowpriceMatches.add(new Stock((Stock)i));
                } else if (i instanceof MutualFund) {
                    lowpriceMatches.add(new MutualFund((MutualFund)i));
                }
            }
        }
        return lowpriceMatches;
    }

    /**
     * Creates a string of all the portfolio investments
     * @return portfolio string
     */

    public String toString() {

        String print = "";

        for (Investment i: investments) {

            print += i.toString() + "\n";
        }

        return print; 
    }

}