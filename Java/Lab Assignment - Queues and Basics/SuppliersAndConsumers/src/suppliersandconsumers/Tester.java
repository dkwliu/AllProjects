/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suppliersandconsumers;
import java.util.*;
/**
 *
 * @author doger
 */
public class Tester {

    public static void main(String[] args) {
        
        // Consumer Objects
        
        System.out.println("Demonstration of 10 Producers and 10 Consumers running " +
                "simultaneously. Output shows only a few seconds of events.");
        new Thread(new Runnable() {
            public void run() {
                Consumer c1 = new Consumer();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                Consumer c2 = new Consumer();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                Consumer c3 = new Consumer();
            }
        }).start();
        
        new Thread(new Runnable() {
            public void run() {
                Consumer c4 = new Consumer();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                Consumer c5 = new Consumer();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                Consumer c6 = new Consumer();
            }
        }).start();        

        new Thread(new Runnable() {
            public void run() {
                Consumer c7 = new Consumer();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                Consumer c8 = new Consumer();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                Consumer c9 = new Consumer();
            }
        }).start();   
        
        new Thread(new Runnable() {
            public void run() {
                Consumer c10 = new Consumer();
            }
        }).start();         
        
        // Producer Objects
        new Thread(new Runnable() {
            public void run() {
                Producer p1 = new Producer();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                Producer p2 = new Producer();
            }
        }).start();        
    
        new Thread(new Runnable() {
            public void run() {
                Producer p3 = new Producer();
            }
        }).start();    
        
                new Thread(new Runnable() {
            public void run() {
                Producer p4 = new Producer();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                Producer p5 = new Producer();
            }
        }).start();        
    
        new Thread(new Runnable() {
            public void run() {
                Producer p6 = new Producer();
            }
        }).start();   
        
                new Thread(new Runnable() {
            public void run() {
                Producer p7 = new Producer();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                Producer p8 = new Producer();
            }
        }).start();        
    
        new Thread(new Runnable() {
            public void run() {
                Producer p9 = new Producer();
            }
        }).start();   
        
                new Thread(new Runnable() {
            public void run() {
                Producer p10 = new Producer();
            }
        }).start();
    
    }

}
