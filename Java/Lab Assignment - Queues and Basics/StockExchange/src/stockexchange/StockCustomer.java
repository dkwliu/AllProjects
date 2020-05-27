/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexchange;

/**
 *
 * @author doger
 */
public class StockCustomer implements StockObserver {
    
    protected static int stockSteps; 
    protected static int overallSteps = 0;
    protected static StockExchange se;

    // reflects the stock price change and documents it
    public void priceChanged(PriceChangeEvent pce) {
        overallSteps += 1;
        stockSteps += pce.getPriceChange();
        se = pce.getStockExchange();
    }
    
    public int getStockSteps() {
        return stockSteps;
    }
}
