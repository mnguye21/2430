May Nguyen
mnguye21@uoguelph.ca
1051760

To compile:

cd ePortfolio
javac Main.java Portfolio.java Investment.java Stock.java MutualFund.java

To run: 

cd ..
java ePortfolio.Main

Problem statement:

Assumptions/limitations:

Further improvements:


TEST PLAN: 

BUY FUNCTINON major conditions to consider: 
1. New Investment (stock/mutualfund)
2. Existing Investment
3. Invalid inputs (negative values for quantity/price)

SELL FUNCTION major conditions to consider
1. Investment does not exist
2. Invalid input (sell quantity > existing quantity)
3. Sell quantity == Existing Quantity (remove investment)

UPDATE FUNCTION major conditions to consider
1. Invalid price (negative)

SEARCH FUNCTION major conditions to consider
1. No existing Investment
2. Investment at start of the list
3. Investment at end of the list
4. Investment end of the list

For all types of SEARCH

A. Symbol only
B. Name only
C. Single price
D. Min price
E. Max price
F. Price range

^ Power set of the above set 

HASH FUNCTION major conditions to consider
1. Single keyword
2. Multiple keywords
3. No matches
4. A single match 
5. Multiple matches

INPUT FILE FUNCTION major conditions to consider
1. Empty file
2. Same conditions as BUY

OUTPUT FILE FUNCTION major conditions to consider
1. Empty file name