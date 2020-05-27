
package concurrentprogramming;
import java.util.*;

public class ConcurrentProgramming {

    private int index = 0;
    private ArrayList<Double> array1;
    private ArrayList<Double> array2;   
    private ArrayList<Double> powerArray;
    
    public void populateArrays() {
        array1 = new ArrayList<Double>();
        array2 = new ArrayList<Double>();
        powerArray = new ArrayList<Double>();
        
        Random rnum = new Random();
        double rand;
        
        for (int i = 0; i < 10000000; i++) {
            rand = 0 + 20 * rnum.nextDouble();
            array1.add(rand);
            
            rand = 0 + 20 * rnum.nextDouble();
            array2.add(rand);

        }
    }

    public void calculateArray(int subArraySize) {
        int startIndex = index;

        index = index + subArraySize;
        double result;
        for (int i = startIndex; i < index; i++) {
            // computing the index
            result = Math.pow(array1.get(i), array2.get(i));
        }
    }

    public void runThread(int subArraySize) {        
        if (index < 10000000) {
            calculateArray(subArraySize);
            runThread(subArraySize);
        } 
    }

    public void oneThread(int subArraySize) {
        index = 0;        
        long startTime = System.nanoTime();
        new Thread(new Runnable() {
            public void run() {
                runThread(subArraySize);
            }
        }).start();
        long stopTime = System.nanoTime();
        System.out.println( "Running time for 1 thread "
                + "and subarray size of " + subArraySize +  
                ": " + (stopTime - startTime + " nanoseconds"));
    }

    public void twoThread(int subArraySize) {       
        index = 0;        
        long startTime = System.nanoTime();        
        new Thread(new Runnable() {
            public void run() {
                runThread(subArraySize);
            }
        }).start();    
     
        new Thread(new Runnable() {
            public void run() {
                runThread(subArraySize);
            }
        }).start();         
        long stopTime = System.nanoTime();
        System.out.println( "Running time for 2 threads "
                + "and subarray size of " + subArraySize + 
                ": " + (stopTime - startTime + " nanoseconds"));        
    } 
    
    public void fourThread(int subArraySize) {       
        index = 0;        
        long startTime = System.nanoTime();        
        new Thread(new Runnable() {
            public void run() {
                runThread(subArraySize);
            }
        }).start();   
        
        new Thread(new Runnable() {
            public void run() {
                runThread(subArraySize);
            }
        }).start();  
        
        new Thread(new Runnable() {
            public void run() {
                runThread(subArraySize);
            }
        }).start();          
     
        new Thread(new Runnable() {
            public void run() {
                runThread(subArraySize);
            }
        }).start();         
        long stopTime = System.nanoTime();
        System.out.println( "Running time for 4 threads "
                + "and subarray size of " + subArraySize + 
                ": " + (stopTime - startTime + " nanoseconds"));        
    }     

    public void eightThread(int subArraySize) {       
        index = 0;        
        long startTime = System.nanoTime();        
        new Thread(new Runnable() {
            public void run() {
                runThread(subArraySize);
            }
        }).start();   
        
        new Thread(new Runnable() {
            public void run() {
                runThread(subArraySize);
            }
        }).start();  
        
        new Thread(new Runnable() {
            public void run() {
                runThread(subArraySize);
            }
        }).start();        
        
        new Thread(new Runnable() {
            public void run() {
                runThread(subArraySize);
            }
        }).start();
        
        new Thread(new Runnable() {
            public void run() {
                runThread(subArraySize);
            }
        }).start();
        
        new Thread(new Runnable() {
            public void run() {
                runThread(subArraySize);
            }
        }).start();
        
        new Thread(new Runnable() {
            public void run() {
                runThread(subArraySize);
            }
        }).start();        
     
        new Thread(new Runnable() {
            public void run() {
                runThread(subArraySize);
            }
        }).start();         
        long stopTime = System.nanoTime();
        System.out.println( "Running time for 8 threads "
                + "and subarray size of " + subArraySize + 
                ": " + (stopTime - startTime + " nanoseconds"));        
    }         
    
    public static void main(String[] args) {
        ConcurrentProgramming cp = new ConcurrentProgramming();

        cp.populateArrays();

        cp.oneThread(10000);   
        cp.oneThread(100000);
        cp.oneThread(1000000);          
    
        cp.twoThread(10000);
        cp.twoThread(100000);
        cp.twoThread(1000000);       
        
        cp.fourThread(10000);
        cp.fourThread(100000);
        cp.fourThread(1000000);  
         
        cp.eightThread(10000);
        cp.eightThread(100000);
        cp.eightThread(1000000);

        
    }

}
