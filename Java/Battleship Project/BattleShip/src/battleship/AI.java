package battleship;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

public class AI extends Thread {

    private int thisPlayerNum = 0;
    private boolean shotMade;

    ArrayList<Integer> xCoord;
    ArrayList<ArrayList<Integer>> yCoord = new ArrayList<ArrayList<Integer>>();
    
    private boolean coordStoreSet;
    private int randx;
    private int randy;
    private int index;
    MessageRelay mr = new MessageRelay();

    public void run() {

        for (int i = 0; i < 26; i++) {
            xCoord = new ArrayList<Integer>();
            yCoord.add(xCoord);
            
            for (int x = 0; x < 26; x++ ) {
                yCoord.get(i).add(x);
            }
        }
        coordStoreSet = true;
        
        System.out.println("AI successfully started.");

        while (true) {

            if (mr.getGameStarted() == true) {
                if (thisPlayerNum != mr.getAINum()) {
                    thisPlayerNum = mr.getAINum();
                    System.out.println("AI number is " + thisPlayerNum);
                }

                if (mr.getAINum() == 1) {
                    if (mr.getPlayer1Editable() == true && mr.getP1TurnMade() == false) {
                        try {
                            // sleep to make ai program seem like it is processing a thought
//                            Thread.sleep(1000);

                            while (mr.getCoordSet() == false) {

                                shotMade = mr.getAIShotMade();
                                if (shotMade == false) {
                                    Random rand = new Random();
                                    randy = rand.nextInt(yCoord.size());
                                    
                                    while (yCoord.get(randy).isEmpty()) {
                                        randy = rand.nextInt(yCoord.size());
                                    }
                                    
                                    index = rand.nextInt(yCoord.get(randy).size());
                                    randx = yCoord.get(randy).get(index);
                                    
                                    mr.setXYAttack(randx, randy);
                                    System.out.println("Player " + thisPlayerNum + " attacked " 
                                            + randx + "|" + randy);
                                    
                                    yCoord.get(randy).remove(index);
                                    
                                    if (coordStoreSet == true) {
                                        coordStoreSet = false;
                                    }
                                    
                                    mr.setAIShotMade(true);
                                }
                            }
                            
                            mr.setCoordSet(false);
                            mr.setAIShotMade(false);
                            mr.setP1TurnMade(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else if (mr.getAINum() == 2) {
                    if (mr.getPlayer2Editable() == true && mr.getP2TurnMade() == false) {
                        try {
                            // sleep to make ai program seem like it is processing a thought
//                            Thread.sleep(1000);

                            while (mr.getCoordSet() == false) {

                                shotMade = mr.getAIShotMade();
                                if (shotMade == false) {
                                    Random rand = new Random();
                                    randy = rand.nextInt(yCoord.size());
                                    
                                    while (yCoord.get(randy).isEmpty()) {
                                        randy = rand.nextInt(yCoord.size());
                                    }
                                    
                                    index = rand.nextInt(yCoord.get(randy).size());
                                    randx = yCoord.get(randy).get(index);
                                    
                                    mr.setXYAttack(randx, randy);
                                    System.out.println("Player " + thisPlayerNum + " attacked " 
                                            + randx + "|" + randy);
                                    
                                    yCoord.get(randy).remove(index);
                                    
                                    if (coordStoreSet == true) {
                                        coordStoreSet = false;
                                    }
                                    
                                    mr.setAIShotMade(true);
                                }
                            }

                            mr.setCoordSet(false);
                            mr.setAIShotMade(false);
                            mr.setP2TurnMade(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                if (coordStoreSet == false) {

                    System.out.println("Refilling cell store in AI");
                    yCoord.clear();

                    for (int i = 0; i < 26; i++) {
                        xCoord = new ArrayList<Integer>();
                        yCoord.add(xCoord);

                        for (int x = 0; x < 26; x++) {
                            yCoord.get(i).add(x);
                        }
                    }
                    coordStoreSet = true;
                }
            }
        }
    }
}
