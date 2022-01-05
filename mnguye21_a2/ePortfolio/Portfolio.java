package ePortfolio; 

import java.util.ArrayList; 
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Portofolio
 * @author May Nguyen
 */
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
     * @param bookValue
     */
    public boolean buyFromFile (String type, String symbol, String name, String quantity, String price, String bookValue){

        boolean returnVal = false;

        if (investments.contains(symbol)) { // the investment already exists

            int index = -1; 

            for (int i = 0; i < investments.size() && i!=-1; i++){
                if (investments.get(i).getSymbol().equalsIgnoreCase(symbol)){
                    index = i;
                    i=-1; 
                }
            }

            returnVal = investments.get(index).addFromFile(Integer.valueOf(quantity), Double.valueOf(price), Double.valueOf(bookValue));
        
        } else { //creating a new investment

            if (type.trim().equalsIgnoreCase("stock")) {

                Stock newStock = new Stock (symbol, name); 
                returnVal = newStock.setQuantity(Integer.valueOf(quantity));
                returnVal = newStock.setPrice(Double.valueOf(price));
                returnVal = newStock.setBookValue(Double.valueOf(bookValue));

                if (returnVal == true) {
                    investments.add(newStock);
                }                

            } else if (type.trim().equalsIgnoreCase("mutualfund")) { 
    
                MutualFund newMutualFund= new MutualFund (symbol, name); 
                returnVal = newMutualFund.setQuantity(Integer.valueOf(quantity));
                returnVal = newMutualFund.setPrice(Double.valueOf(price));
                returnVal = newMutualFund.setBookValue(Double.valueOf(bookValue));

                if (returnVal == true) {
                    investments.add(newMutualFund);
                }  
            }

            //update hashMap

            if (returnVal == true) {
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
        return returnVal;
    }


    /**
     * Add a new investment to the list depending on if it is a stock or mutual fund
     * @param symbol
     * @param name
     * @param quantity
     * @param price
     * @param type
     */
    public boolean buy (String symbol, String name, int quantity, double price, int type){
        
        boolean returnVal = false; 
        if (type == 1) {

            Stock newStock = new Stock (symbol, name); 
            returnVal = newStock.setQuantity(quantity);
            returnVal = newStock.setPrice(price);

            if (returnVal == true) {
                investments.add(newStock); 
            }

        } else if (type == 2) { 

            MutualFund newMutualFund = new MutualFund (symbol, name, quantity, price); 
            returnVal = newMutualFund.setQuantity(quantity);
            returnVal = newMutualFund.setPrice(price);
            
            if (returnVal == true){
                investments.add(newMutualFund); 
            }
        }

        if (returnVal == true) {

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

        return returnVal;
    }

    /**
     * Add to an existing investment 
     * @param symbol
     * @param quantity
     * @param price
     * @param type
     */
    public boolean buy (String symbol, int quantity, double price, int type){ 

        int index = -1; 
        boolean success = false;

        for (int i = 0; i < investments.size() && i!=-1; i++){
            if (investments.get(i).getSymbol().equalsIgnoreCase(symbol)){
                index = i;
                i=-1; 
            }
        }

        success = investments.get(index).add(quantity, price);
        
        return success;
    }

    /**
     * Remove an exisitng investment
     * @param symbol
     * @param quantity
     * @param price
     * @param type
     * @return flag == 0 if succesful, 1 if invalid input and 2 if invesment not found, 3 if completely sold
     */
    public int sell (String symbol, int quantity, double price, int type){

        int index = -1; 
        int flag = 0; 

            
        for (int i = 0; i < investments.size() && i!=-1; i++){
            if (investments.get(i).getSymbol().equalsIgnoreCase(symbol)){
                index = i; 
            }
        }
        
        if (index != -1){
            flag = investments.get(index).remove(quantity, price, 0); // flag = 1 if error quanitity

            if (flag == 3) {

                String[] keywords = investments.get(index).getName().toLowerCase().split("\\s+"); //update HashMap

                for (int i = 0; i < keywords.length; i++) {

                    Iterator <HashMap.Entry<String, ArrayList<Integer>>> iter = searchHashMap.entrySet().iterator();

                    while (iter.hasNext()) {
                        HashMap.Entry<String, ArrayList<Integer>> entry = iter.next(); 

                        if (entry.getKey().equalsIgnoreCase(keywords[i])){
                            entry.getValue().remove(index);
                        }
                    }
                }
                investments.remove(index);
            }
        } else { 
            flag = 2;
        }

        return flag; // 1 means quantity error, 2 means symbol not found
    }


    /**
     * Update the price of an investment in the portfolio
     * @param index
     * @param price
     */
    public boolean update (int index, double price){
        
        return investments.get(index).updatePrice(price);   
    }

    /**
     * Checks if the investment list of the portfolio contains an investment with the given symbol
     * @param symbol
     * @return true if investment list contains an investment with the given symbol, false if not
     */
    public boolean contains(String symbol){

        boolean contains = false; 
        
        for (int i = 0; i < investments.size() && contains == false; i++){
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
     * Searches the investment list using the given parameters in a loop
     * @param symbol
     * @param keywords
     * @param price
     * @return searchMatches (list of all investments that match the given search keys)
     */
    public ArrayList<Investment> search (String symbol, String keywords, String price) {

        ArrayList<Investment> searchMatches = new ArrayList<Investment>();

        boolean minSearch = false;
        boolean maxSearch = false;
        boolean regSearch = false; 

        double min = 0;
        double max = 0;

        String [] splitKeys = keywords.split("\\s+");

        if (!price.equals("")){

            StringTokenizer tokens = new StringTokenizer(price, "-", true);
            ArrayList<String> priceStrings = new ArrayList<String>();


            while (tokens.hasMoreTokens()){
                priceStrings.add(tokens.nextToken());
            }

            //determine the kind of price search 
            if (priceStrings.size() == 1) {

                regSearch = true; 
                min = Double.valueOf(priceStrings.get(0));
                max = Double.valueOf(priceStrings.get(0));

            } else if (priceStrings.size() == 2) {
                
                if (priceStrings.get(0).equals("-")){
                    maxSearch = true; 
                    max = Double.valueOf(priceStrings.get(1));
                } else {
                    minSearch = true; 
                    min = Double.valueOf(priceStrings.get(0));
                }

            } else if (priceStrings.size() == 3) {

                regSearch = true; 
                min = Double.valueOf(priceStrings.get(0));
                max = Double.valueOf(priceStrings.get(0));

            } 
        }

        for (int i = 0; i < splitKeys.length; i++) {

            for (Investment match : investments) { 

                if ( ( symbol.equals(match.getSymbol()) || symbol.equals("") ) &&

                     ( match.getName().indexOf(splitKeys[i]) != -1 ) && 

                    (( regSearch && (match.getPrice() >= min) && (match.getPrice() <= max) ) ||
                     ( minSearch && (match.getPrice() >= min) )  ||
                     ( maxSearch && (match.getPrice() >= max) ) ) ) {

                        searchMatches.add(match);
                    }
            } 
        }

    return searchMatches; 
    }

    public ArrayList<Integer> hashSearch(String [] keywords) {

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

        ArrayList<Integer> indices = lists.get(0);
        for (int i = 1; i < lists.size(); i++) {

            indices.retainAll(lists.get(i));
        }

        return indices;
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

    /**
     * Creates and returns a string that follows the proper format to be outputted to a file
     * @return print - string that is a full list of the investments in the portfolio
     */
    public String toFileString() {

        String print = "";

        for (Investment i: investments) {

            print += i.toFileString() + "\n";
        }

        return print; 
    }
}