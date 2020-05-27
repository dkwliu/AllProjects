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

// interface which the stockcustomer class implements
public interface StockObserver {
    public void priceChanged(PriceChangeEvent pce);
}
