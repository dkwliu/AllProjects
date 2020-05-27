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
public class StockPrinter extends StockCustomer {
    public void printSteps() {
        System.out.println("Second(s): " + overallSteps + "|| Stock: " + stockSteps);
    }
}
