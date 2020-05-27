/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexchange;
import java.util.*;

/**
 *
 * @author doger
 */
public class StockExchange {

    // Two lists for holding the two types of stock observers
    ArrayList<StockMonitor> MonitorList = new ArrayList<StockMonitor>();
    ArrayList<StockPrinter> PrinterList = new ArrayList<StockPrinter>();
    
    // starts stock exchange. Exchange happens every 1 second
    public void startStock() {
        
        Random rand = new Random();
        int changeBy;

        while (MonitorList.size() != 0) {
            if (rand.nextBoolean()) {
                changeBy = -1;
                PriceChangeEvent pceObj = new PriceChangeEvent(this, changeBy);
                updateCustomer(pceObj);
            } else {
                changeBy = 1;
                PriceChangeEvent pceObj = new PriceChangeEvent(this, changeBy);
                updateCustomer(pceObj);
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Stock update interrupted");
            }
        }
    }
    
    // method for adding observer objects
    public void addMonitorObserver(StockMonitor StockM) {
        MonitorList.add(StockM);
    }
    
        public void addPrinterObserver(StockPrinter StockP) {
        PrinterList.add(StockP);
    }
    
    // method for removing observer objects
    public void removeMonitorObserver(StockMonitor StockM) {
        MonitorList.remove(StockM);
    }
    
       public void removePrinterObserver(StockPrinter StockP) {
        PrinterList.remove(StockP);
    }
    
    // method for sending info to the observers
    public void updateCustomer(PriceChangeEvent priceChange) {
        StockCustomer sc = new StockCustomer();
        sc.priceChanged(priceChange);
        for (int i = 0; i < MonitorList.size(); i++) {
            MonitorList.get(i).reachedThreshold();
        }
        for (int i = 0; i < PrinterList.size(); i++) {
            PrinterList.get(i).printSteps();
        }
    }
}
