May Nguyen
mnguye21@uoguelph.ca
1051760

To compile and run (while in a3):
chmod +x run.sh
./run.sh

To clean (while in a3): 
chmod +x clean.sh
./clean.sh

Problem statement:
 - Create an interatcive ePortfolio where the user can enter values to buy investments, sell investments, get the total gain of the portfolio, update prices of the portfolio and search investments within the portfolio.

Assumptions/limitations:
 - My program assumes that the search low price and high price values are upper and lower bounds
 - Assumes that the symbol entered is also searching for results that contain the entered symbol (not exact match only)

Further improvements:
 - Add margins 
 - Add more detail in the messages

TEST PLAN: 

FUNCTIONALITY major conditions to consider: 
1. Flipping between the command panels
2. Editable/non-editable textfields
3. Scrollable text areas
4. Button functionality

BUY FUNCTION major conditions to consider: 
1. New Investment (stock/mutualfund)
2. Existing Investment
3. Invalid inputs (negative values for quantity/price)

SELL FUNCTION major conditions to consider
1. Investment does not exist
2. Invalid input (sell quantity > existing quantity)
3. Sell quantity == Existing Quantity (remove investment)

UPDATE FUNCTION major conditions to consider
1. Invalid price (negative)
2. Prev/Next shows and updates correct stock

SEARCH FUNCTION major conditions to consider
1. No existing Investment
2. Investment at start of the list
3. Investment at end of the list
4. Investment end of the list

For all types of SEARCH (16 types listed in comments of the switch statement in Portfolio.java)