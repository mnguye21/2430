package mnguye21_a1; 

import java.util.ArrayList; 

public class Portfolio {

    private ArrayList<Stock> stocks = new ArrayList<Stock>(); 
    private ArrayList<MutualFund> mutualFunds = new ArrayList<MutualFund>();

    public Portfolio(){

    }
    public ArrayList<Stock> getStockList() {
        return stocks;
    }
    public ArrayList<MutualFund> getMutualFundList(){
        return mutualFunds; 
    }

    public void buy (String symbol, String name, int quantity, double price, int type){

        if (type == 1) {

            Stock newStock = new Stock (symbol, name, quantity, price); 
            stocks.add(newStock); 
        } else if (type == 2) { 

            MutualFund newMutualFund = new MutualFund (symbol, name, quantity, price); 
            mutualFunds.add(newMutualFund); 
        }


    }
    public void buy (String symbol, int quantity, double price, int type){ 

        int index = -1; 

        if (type == 1) {
            
            for (int i = 0; i < stocks.size() && i!=-1; i++){
                if (stocks.get(i).getSymbol().equalsIgnoreCase(symbol)){
                    index = i; 
                }
            }

            stocks.get(index).add(quantity, price); 
        } else if (type == 2) {

            for (int i = 0; i < mutualFunds.size() && i!=-1; i++){
                if (mutualFunds.get(i).getSymbol().equalsIgnoreCase(symbol)){
                    index = i; 
                }
            }

            mutualFunds.get(index).add(quantity, price);
        }
    }
    public int sell (String symbol, int quantity, double price, int type){

        int index = -1; 
        int flag = 0; 

        if (type == 1) {
            
            for (int i = 0; i < stocks.size() && i!=-1; i++){
                if (stocks.get(i).getSymbol().equalsIgnoreCase(symbol)){
                    index = i; 
                }
            }
            if (index != -1){
                flag = stocks.get(index).remove(quantity, price); // flag = 1 if error quanitity
            } else { 
                flag = 2;
            }
        } else if (type == 2) {

            for (int i = 0; i < mutualFunds.size() && i!=-1; i++){
                if (mutualFunds.get(i).getSymbol().equalsIgnoreCase(symbol)){
                    index = i; 
                }
            }
            if (index != -1){
                flag = mutualFunds.get(index).remove(quantity, price); //flag = 1 if error quanitity
            } else {
                flag = 2; 
            }
        }

        return flag; // 1 means quantity error, 2 means symbol not found
    }
    public void update (int index, double price, int type){
        if (type ==1) {
            stocks.get(index).updatePrice(price); 
        } else if (type == 2){
            mutualFunds.get(index).updatePrice(price);
        }
    }
    public boolean contains(String symbol, int type){

        boolean contains = false; 

        if (type == 1) {
            for (int i = 0; i < stocks.size() && contains == false; i++){
                if (stocks.get(i).getSymbol().equalsIgnoreCase(symbol)){
                    contains = true; 
                }
            }
        } else if (type == 2) { 
            for (int i = 0; i < mutualFunds.size() && contains == false; i++){
                if (mutualFunds.get(i).getSymbol().equalsIgnoreCase(symbol)){
                    contains = true; 
                }
            }
        }

        return contains; 
    }
    
}