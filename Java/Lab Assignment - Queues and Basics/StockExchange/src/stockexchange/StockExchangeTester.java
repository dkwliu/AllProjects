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
public class StockExchangeTester {

    public static void main(String[] args) {
        // TODO code application logic here    

        System.out.println("Demonstration of the stock exchange code." +
                "Original specifications ask for monitor thresholds of " +
                "5,10,15,20, and 25, but these thresholds took too long to reach." +
                "Instead, thresholds of 2,4,6,8,and 10 were used.");
        
        
        StockExchange se1 = new StockExchange();
        StockMonitor sm1 = new StockMonitor(2);
        StockMonitor sm2 = new StockMonitor(4);
        StockMonitor sm3 = new StockMonitor(6);
        StockMonitor sm4 = new StockMonitor(8);
        StockMonitor sm5 = new StockMonitor(10);
        StockPrinter sp1 = new StockPrinter();
        //added printer object
        se1.addPrinterObserver(sp1);
        //added monitor objects
        se1.addMonitorObserver(sm1);
        se1.addMonitorObserver(sm2);
        se1.addMonitorObserver(sm3);
        se1.addMonitorObserver(sm4);
        se1.addMonitorObserver(sm5);
      
        se1.startStock();
    }
}
