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
public class PriceChangeEvent {
    private StockExchange stockE;
    private int priceChange;
    
    public PriceChangeEvent(StockExchange se, int pc) {
        stockE = se;               
        priceChange = pc;
    }
    
    public void setStockExchange(StockExchange se) {
        stockE = se;
    }
    
    public void setPriceChange(int pc) {
        priceChange = pc;
    }
    
    public StockExchange getStockExchange() {
        return stockE;
    }
    
    public int getPriceChange() {
        return priceChange;
    }
}
