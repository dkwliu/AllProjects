package battleship;
import java.util.*;

public class Mediator extends Thread{
    
    private static int turn = 1;
    MessageRelay checkRelay = new MessageRelay();
    private static boolean p1MadeATurn;
    private static boolean p2MadeATurn;
    private int turnCount = 0;
    private boolean gameStarted;

    public void run() {

        System.out.println("Mediator successfully started.");

        while (true) {

            if (turn == 1) {

                turnCount = turnCount + 1;
                System.out.println("********************" + turnCount + "***********************");
                System.out.println("Waiting for player 1 input");

                checkRelay.setPlayerEditable(true, false);
                checkRelay.setP1TurnMade(false);
                
                p1MadeATurn = checkRelay.getP1TurnMade();

                while (p1MadeATurn == false) {

                    p1MadeATurn = checkRelay.getP1TurnMade();

                }
                System.out.println("Player 1's turn has ended");
                System.out.println("*******************************************");
                turn = 2;
            } else if (turn == 2) {
                
                turnCount = turnCount + 1;
                System.out.println("********************" + turnCount + "***********************");
                System.out.println("Waiting for player 2 input");
                
                checkRelay.setPlayerEditable(false, true);
                checkRelay.setP2TurnMade(false);

                p2MadeATurn = checkRelay.getP2TurnMade();

                while (p2MadeATurn == false) {

                    p2MadeATurn = checkRelay.getP2TurnMade();

                }
                System.out.println("Player 2's turn has ended");
                System.out.println("*******************************************");
                turn = 1;
            }
        }

    }

    public void setPlayerTurn(int turn) {
        this.turn = turn;
    }
    
    public int getPlayerTurn() {
        return turn;
    }
}
