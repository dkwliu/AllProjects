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
public class StockMonitor extends StockCustomer {
        
    private int threshold;
    
    public StockMonitor(int th) {
        threshold = th;
    }

    // tracks the steps the monitor takes until it reaches its specified threshold
    // it then removes itself
    public void reachedThreshold() {
        if (stockSteps == threshold || stockSteps == (-threshold)) {
            se.removeMonitorObserver(this);
            System.out.println("Stock has reached the threshold of " + threshold + " in " + overallSteps + " seconds");
        }
    }
    
}
