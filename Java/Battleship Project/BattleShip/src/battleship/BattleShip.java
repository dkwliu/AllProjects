package battleship;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BattleShip {
    
    private static int gameMode;
    private static int gameMisc;
    private static boolean gameModeChosen = false;
    private static boolean gameMiscChosen = false;
    private static JFrame startMenu;
    private static JButton vsAI;
    private static JButton vsPlayer;
    private static JButton easy;
    private static JButton makeGame;
    private static JButton joinGame;
    private static String ip = "10.0.0.16";
    
    public static void main(String[] args) {
        startingMenu();
    }

    // Shows the menu for choosing which game mode the player wants to choose
    public static void startingMenu() {
        startMenu = new JFrame("Battle Ship");
        startMenu.setLayout(null);
        startMenu.setBounds(500,500,300,500);
        
        vsAI = new JButton("Play AI");
        vsAI.setBounds(50, 50, 200, 100);
        vsPlayer = new JButton("Vs Player");
        vsPlayer.setBounds(50, 200, 200, 100);
        startMenu.add(vsAI);
        startMenu.add(vsPlayer);
        
        vsAI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameMode = 1;
                gameModeChosen = true;
                vsAI.setEnabled(false);
            }
        });
        
        vsPlayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameMode = 2;
                gameModeChosen = true;
                vsPlayer.setEnabled(false);
            }
        });
        
        startMenu.setDefaultCloseOperation(startMenu.EXIT_ON_CLOSE);
        startMenu.show();
        
        while (gameModeChosen == false) {
            startMenu.validate();
        }   
        
        // gameMode 1 is for vs AI
        if (gameMode == 1) {

            startMenu.getContentPane().removeAll();
            startMenu.repaint();

            easy = new JButton("Easy Mode");
            easy.setBounds(50, 50, 200, 100);
            startMenu.add(easy);

            easy.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    gameMisc = 2;
                    gameMiscChosen = true;
                    vsAI.setEnabled(false);
                    startMenu.dispose();
                }
            });

            while (gameMiscChosen == false) {
                startMenu.validate();
            }

            playerVs(gameMode,gameMisc);

            // gameMode 2 is for player vs player
        } else if (gameMode == 2) {
            
            startMenu.getContentPane().removeAll();
            startMenu.repaint();

            joinGame = new JButton("Join Game");
            joinGame.setBounds(50, 50, 200, 100);
            startMenu.add(joinGame);
            
            makeGame = new JButton("Make Game");
            makeGame.setBounds(50, 200, 200, 100);
            startMenu.add(makeGame);

            joinGame.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    gameMisc = 0;
                    gameMiscChosen = true;
                    joinGame.setEnabled(false);
                    startMenu.dispose();
                }
            });
            
            makeGame.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    gameMisc = 1;
                    gameMiscChosen = true;
                    makeGame.setEnabled(false);
                    startMenu.dispose();
                }
            });

            while (gameMiscChosen == false) {
                startMenu.validate();
            }

            playerVs(gameMode, gameMisc);

        }
    }

    // Shows the screen that shows the difficulty the player wants to choose
    public static void chooseDifficulty(JFrame startWindow) {
        // in construction
    }

    // method for starting up the game mode
    public static void playerVs(int mode, int misc) {
        try {
            if (mode == 1) {
                System.out.println("Starting up the Mediator");
                Mediator m = new Mediator();
                m.start();

                System.out.println("Starting up battleship");
                BattleShipBoard bsb = new BattleShipBoard(mode, misc, ip);
                bsb.start();

                System.out.println("Starting up the AI");
                AI ai = new AI();
                ai.start();

                bsb.join();
                m.join();
                ai.join();
            } else if (mode == 2) {
                System.out.println("Starting up battleship");
                BattleShipBoard bsb = new BattleShipBoard(mode, misc, ip);
                bsb.start();
//                
//                System.out.println("Starting up the Mediator");
//                Mediator m = new Mediator();
//                m.start();
//                
//                bsb.join();
//                m.join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
