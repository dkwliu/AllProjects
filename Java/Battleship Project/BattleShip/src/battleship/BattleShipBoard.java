package battleship;
import java.awt.Color;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.text.DefaultCaret;

public class BattleShipBoard extends Thread {
    
    private int gameMode;
    private int gameMisc;
    private static Socket socket;
    private static ServerSocket ss;
    private int serverPriority;
    private int clientPriority;
    private String coord;
    private String msg;
    private String ip;
    
    JFrame Window = new JFrame("Battle Ship");
    JInternalFrame endFrame;
    JLabel endLabel;
    JButton endButton;
    JInternalFrame playerWindow = new JInternalFrame("Player Board");
    JInternalFrame oppWindow = new JInternalFrame("Opponent Board");
    JButton startGame;
    BoardTile tile;
    ArrayList<Ship> shipList = new ArrayList<Ship>();
    ArrayList<Ship> oppShipList = new ArrayList<Ship>();
    ArrayList<BoardTile> playerCols;
    ArrayList<ArrayList<BoardTile>> playerRows = new ArrayList<ArrayList<BoardTile>>();   
    ArrayList<BoardTile> oppCols;
    ArrayList<ArrayList<BoardTile>> oppRows = new ArrayList<ArrayList<BoardTile>>();    
    JButton randBut = new JButton("Randomize");
    JTextArea ta = new JTextArea();
    JScrollPane sp = new JScrollPane(ta);
    JLabel boardLabel;
    int turnPriority = -1;
    MessageRelay mr = new MessageRelay();
    
    public BattleShipBoard(int mode, int misc, String ip) {

        this.ip = ip;
        
        gameMode = mode;
        gameMisc = misc;
        
        if (gameMode == 1) {
            Random rand = new Random();
            turnPriority = rand.nextInt(2);

            if (turnPriority == 1) {
                mr.setHumanPlayerNum(1);
                mr.setAINum(2);
            } else {
                mr.setHumanPlayerNum(2);
                mr.setAINum(1);
            }

            System.out.println("Player number is " + mr.getHumanPlayerNum());

            createWindows(gameMode, gameMisc);
            createShips(shipList, playerRows, Color.black);
            createShips(oppShipList, oppRows, Color.cyan);
        } else if (gameMode == 2) {

            try {
                if (gameMisc == 1) {
                    Random rand = new Random();
                    turnPriority = rand.nextInt(2);
                    
                    createWindows(gameMode, gameMisc);
                    createShips(shipList, playerRows, Color.black);

                } else if (gameMisc == 0) {
                    createWindows(gameMode, gameMisc);
                    createShips(shipList, playerRows, Color.black);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Start the BattleShipBoard Thread
    public void run() {
        // gameMode 1 is for AI
        if (gameMode == 1) {
            while (true) {
                if (mr.getXAttack() != -1 && mr.getYAttack() != -1) {

                    if (playerRows.get(mr.getXAttack()).get(mr.getYAttack()).getShipPart() == true) {
                        playerRows.get(mr.getXAttack()).get(mr.getYAttack()).setBackground(Color.red);
                        playerRows.get(mr.getXAttack()).get(mr.getYAttack()).setTileHit(true);

                        ta.setText(ta.getText() + "The AI has hit the " + playerRows.get(mr.getXAttack()).get(mr.getYAttack()).getTileName() + " at coordinates "
                            + playerRows.get(mr.getXAttack()).get(mr.getYAttack()).getTileCoord() + "\n");

                        for (int i = (shipList.size() - 1); i >= 0; i--) {
                            if (shipList.get(i).getName().equals(playerRows.get(mr.getXAttack()).get(mr.getYAttack()).getTileName())) {
                                shipList.get(i).setHealth(shipList.get(i).getHealth() - 1);
                                ta.setText(ta.getText() + "Your " + shipList.get(i).getName() + " was hit, and has " + shipList.get(i).getHealth() + " health left.\n");
                                if (shipList.get(i).getHealth() == 0) {
                                    ta.setText(ta.getText() + "Your " + shipList.get(i).getName() + " was sunk.\n");
                                    shipList.remove(i);
                                }
                            }
                        }

                        if (shipList.size() == 0) {
                            createEndWindow(2);
                        }

                        mr.setXYAttack(-1, -1);
                        mr.setCoordSet(true);
                    } else {
                        ta.setText(ta.getText() + "The AI has hit the " + playerRows.get(mr.getXAttack()).get(mr.getYAttack()).getTileName() + " at coordinates "
                                + playerRows.get(mr.getXAttack()).get(mr.getYAttack()).getTileCoord() + "\n");
                        playerRows.get(mr.getXAttack()).get(mr.getYAttack()).setBackground(Color.gray);
                        playerRows.get(mr.getXAttack()).get(mr.getYAttack()).setTileHit(true);
                        mr.setXYAttack(-1, -1);
                        mr.setCoordSet(true);
                    }
                } else if (!mr.getHitShipName().equals("nothing")) {
                    String hSName = mr.getHitShipName();
                    mr.setHitShipName("nothing");
                    for (int i = (oppShipList.size() - 1); i >= 0; i--) {
                        if (oppShipList.get(i).getName().equals(hSName)) {
                            oppShipList.get(i).setHealth(oppShipList.get(i).getHealth() - 1);
                            ta.setText(ta.getText() + "You hit a ship.\n");
                            if (oppShipList.get(i).getHealth() == 0) {
                                oppShipList.remove(i);
                            }                          
                        }
                    }

                    if (oppShipList.size() == 0) {
                        createEndWindow(1);
                    }
                }
            }
            
            // gameMode 2 is for player vs player 
            // gameMisc 0 is for joining a game
        } else if (gameMode == 2 && gameMisc == 0) {

            while (true) {
                try {

                    if (mr.getGameStarted()!= true) {
                        socket = new Socket(ip, 6666);
                        if (mr.getGameStarted() == false) {

                            OutputStream os = socket.getOutputStream();
                            OutputStreamWriter osw = new OutputStreamWriter(os);
                            BufferedWriter bw = new BufferedWriter(osw);
                            bw.write("checkGame\n");
                            bw.flush();

                            InputStream is = socket.getInputStream();
                            InputStreamReader isr = new InputStreamReader(is);
                            BufferedReader br = new BufferedReader(isr);
                            msg = br.readLine();

                            if (msg.equals("yes")) {
                                mr.setGameStarted(true);
                                ta.setText(ta.getText() + "Game has started. \n");
                                System.out.println("Received message that the game has started");
                            }

                        } else {
                            OutputStream os = socket.getOutputStream();
                            OutputStreamWriter osw = new OutputStreamWriter(os);
                            BufferedWriter bw = new BufferedWriter(osw);
                            bw.write("gameSet\n");
                            bw.flush();
                        }
                        socket.close();

                    } else if (mr.getGameStarted() == true) {
                        
                        socket = new Socket(ip, 6666);
                        if (turnPriority == -1) {

                            OutputStream os = socket.getOutputStream();
                            OutputStreamWriter osw = new OutputStreamWriter(os);
                            BufferedWriter bw = new BufferedWriter(osw);
                            bw.write("checkTurn\n");
                            bw.flush();

                            InputStream is = socket.getInputStream();
                            InputStreamReader isr = new InputStreamReader(is);
                            BufferedReader br = new BufferedReader(isr);
                            msg = br.readLine();
                            System.out.println("Player turn msg is " + msg + "\n");

                            if (msg.equals("P1")) {
                                turnPriority = 1;
                                ta.setText(ta.getText() + "You are player 1. \n");
                                System.out.println("Received message indicating player priority");
                            } else if (msg.equals("P2")) {
                                turnPriority = 0;
                                ta.setText(ta.getText() + "You are player 2. \n");
                                System.out.println("Received message indicating player priority");
                            }

                            for (int i = 0; i < 26; i++) {
                                for (int z = 0; z < 26; z++) {
                                    if (turnPriority == 1) {
                                        oppRows.get(i).get(z).setTilePriority(1);
                                    } else {
                                        oppRows.get(i).get(z).setTilePriority(2);
                                    }
                                }
                            }

                        } else {
                            OutputStream os = socket.getOutputStream();
                            OutputStreamWriter osw = new OutputStreamWriter(os);
                            BufferedWriter bw = new BufferedWriter(osw);
                            bw.write("turnSet\n");
                            bw.flush();
                        }
                        socket.close();
                        
                        socket = new Socket(ip, 6666);
                        OutputStream os = socket.getOutputStream();
                        OutputStreamWriter osw = new OutputStreamWriter(os);
                        BufferedWriter bw = new BufferedWriter(osw);
                        bw.write("checkAttack\n");
                        bw.flush();

                        InputStream is = socket.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        BufferedReader br = new BufferedReader(isr);
                        msg = br.readLine();
                        socket.close();

                        if (!msg.equals("noUpdate")) {
                            ta.setText(ta.getText() + "Your opponent attacked " + msg + ".\n");

                            socket = new Socket(ip, 6666);
                            for (int i = 0; i < 26; i++) {
                                for (int z = 0; z < 26; z++) {
                                    if (playerRows.get(i).get(z).getTileCoord().equals(msg)) {
                                        if (!playerRows.get(i).get(z).getTileName().equals("water")) {
                                            playerRows.get(i).get(z).setTileHit(true);
                                            playerRows.get(i).get(z).setBackground(Color.red);

                                            for (int y = (shipList.size() - 1); y >= 0; y--) {
                                                if (shipList.get(y).getName().equals(playerRows.get(i).get(z).getTileName())) {
                                                    shipList.get(y).setHealth(shipList.get(y).getHealth() - 1);
                                                    ta.setText(ta.getText() + "Your " + shipList.get(y).getName() + " was hit, and has " + shipList.get(y).getHealth() + " health left.\n");
                                                    if (shipList.get(y).getHealth() == 0) {
                                                        ta.setText(ta.getText() + "Your " + shipList.get(y).getName() + " was sunk.\n");
                                                        shipList.remove(y);
                                                    }
                                                }
                                            }

                                            if (shipList.size() == 0) {
                                                ta.setText(ta.getText() + "**************************************************************\n");
                                                ta.setText(ta.getText() + "Your ships have been sunk. You lose.\n");
                                                mr.setGameStarted(false);
                                                os = socket.getOutputStream();
                                                osw = new OutputStreamWriter(os);
                                                bw = new BufferedWriter(osw);
                                                bw.write("lose\n");
                                                bw.flush();
                                                break;
                                            } else {
                                                os = socket.getOutputStream();
                                                osw = new OutputStreamWriter(os);
                                                bw = new BufferedWriter(osw);
                                                bw.write("hit\n");
                                                bw.flush();
                                                break;
                                            }
                                        } else {
                                            playerRows.get(i).get(z).setTileHit(true);
                                            playerRows.get(i).get(z).setBackground(Color.gray);

                                            os = socket.getOutputStream();
                                            osw = new OutputStreamWriter(os);
                                            bw = new BufferedWriter(osw);
                                            bw.write("miss\n");
                                            bw.flush();
                                            break;
                                        }
                                    }
                                }
                            }

                            if (turnPriority == 1) {
                                mr.setPlayerEditable(true, false);
                            } else {
                                mr.setPlayerEditable(false, true);
                            }
                            if (mr.getGameStarted() == true) {
                                ta.setText(ta.getText() + "**************************************************************\n");
                                ta.setText(ta.getText() + "It is now your turn.\n");
                                System.out.println(msg);
                                socket.close();
                            }
                        }

                        if (mr.getCoordSet() == true) {
                            socket = new Socket(ip, 6666);
                            os = socket.getOutputStream();
                            osw = new OutputStreamWriter(os);
                            bw = new BufferedWriter(osw);
                            bw.write(mr.getCoordAttack() + "\n");
                            bw.flush();

                            is = socket.getInputStream();
                            isr = new InputStreamReader(is);
                            br = new BufferedReader(isr);
                            msg = br.readLine();
                            socket.close();

                            if (mr.getGameStarted() == true) {
                                if (msg.equals("hit") || msg.equals("miss") || msg.equals("lose")) {
                                    ta.setText(ta.getText() + "You attacked " + mr.getCoordAttack() + ".");
                                    for (int i = 0; i < 26; i++) {
                                        for (int z = 0; z < 26; z++) {
                                            if (oppRows.get(i).get(z).getTileCoord().equals(mr.getCoordAttack())) {
                                                if (msg.equals("hit") || msg.equals("lose")) {
                                                    oppRows.get(i).get(z).setBackground(Color.red);
                                                    ta.setText(ta.getText() + " You hit a ship.\n");
                                                    if (msg.equals("lose")) {
                                                        ta.setText(ta.getText() + "**************************************************************\n");
                                                        ta.setText(ta.getText() + "You have sunk all over your opponent's ships. You win.\n");
                                                        mr.setGameStarted(false);
                                                    }
                                                    break;
                                                } else {
                                                    oppRows.get(i).get(z).setBackground(Color.gray);
                                                    ta.setText(ta.getText() + " You missed.\n");
                                                    break;
                                                }
                                            }
                                        }
                                    }

                                    if (turnPriority == 1) {
                                        mr.setPlayerEditable(false, true);
                                    } else {
                                        mr.setPlayerEditable(true, false);
                                    }
                                    if (mr.getGameStarted() == true) {
                                        ta.setText(ta.getText() + "**************************************************************\n");
                                        ta.setText(ta.getText() + "It is now your opponent's turn.\n");
                                    }
                                }
                            }

                            mr.setCoordSet(false);
                            mr.setCoordAttack("--");
                        }
                        socket.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        Thread.sleep(50);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            // 1 is for making a game
        } else if (gameMode == 2 && gameMisc == 1) {

            try {
                ss = new ServerSocket(6666);
                System.out.println("Game room created");

                while (true) {
                    socket = ss.accept();
                    InputStream is = socket.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);
                    msg = br.readLine();

                    if (msg.equals("checkGame")) {
                        if (mr.getGameStarted() == false) {
                            OutputStream os = socket.getOutputStream();
                            OutputStreamWriter osw = new OutputStreamWriter(os);
                            BufferedWriter bw = new BufferedWriter(osw);
                            bw.write("no\n");
                            bw.flush();
                        } else {
                            OutputStream os = socket.getOutputStream();
                            OutputStreamWriter osw = new OutputStreamWriter(os);
                            BufferedWriter bw = new BufferedWriter(osw);
                            bw.write("yes\n");
                            bw.flush();
                        }
                    } else if (msg.equals("checkTurn")) {
                        if (turnPriority == 1) {
                            OutputStream os = socket.getOutputStream();
                            OutputStreamWriter osw = new OutputStreamWriter(os);
                            BufferedWriter bw = new BufferedWriter(osw);
                            bw.write("P2\n");
                            bw.flush();
                        } else if (turnPriority == 0) {
                            OutputStream os = socket.getOutputStream();
                            OutputStreamWriter osw = new OutputStreamWriter(os);
                            BufferedWriter bw = new BufferedWriter(osw);
                            bw.write("P1\n");
                            bw.flush();
                        }
                    } else if (msg.equals("checkAttack")) {
                        if (mr.getCoordSet() == true) {
                            OutputStream os = socket.getOutputStream();
                            OutputStreamWriter osw = new OutputStreamWriter(os);
                            BufferedWriter bw = new BufferedWriter(osw);
                            bw.write(mr.getCoordAttack() + "\n");
                            ta.setText(ta.getText() + "You attacked " + mr.getCoordAttack() + ".");
                            if (turnPriority == 1) {
                                mr.setPlayerEditable(false, true);
                            } else {
                                mr.setPlayerEditable(true, false);
                            }                            
                            
                            mr.setCoordSet(false);
                            bw.flush();
                        } else {
                            OutputStream os = socket.getOutputStream();
                            OutputStreamWriter osw = new OutputStreamWriter(os);
                            BufferedWriter bw = new BufferedWriter(osw);
                            bw.write("noUpdate\n");
                            bw.flush();
                        }
                    } else if (msg.equals("hit") || msg.equals("miss") || msg.equals("lose")) {
                        for (int i = 0; i < 26; i++) {
                            for (int z = 0; z < 26; z++) {
                                if (oppRows.get(i).get(z).getTileCoord().equals(mr.getCoordAttack())) {
                                    if (msg.equals("hit") || msg.equals("lose")) {
                                        ta.setText(ta.getText() + " You hit a ship.\n");
                                        oppRows.get(i).get(z).setBackground(Color.red);
                                        oppRows.get(i).get(z).setTileHit(true);
                                        if (msg.equals("lose")) {
                                            ta.setText(ta.getText() + "**************************************************************\n");
                                            ta.setText(ta.getText() + "You have sunk all over your opponent's ships. You win.\n");
                                            mr.setGameStarted(false);
                                        }

                                        break;
                                    } else {
                                        ta.setText(ta.getText() + " You missed.\n");
                                        oppRows.get(i).get(z).setBackground(Color.gray);
                                        oppRows.get(i).get(z).setTileHit(true);
                                        break;
                                    }
                                }
                            }
                        }

                        if (mr.getGameStarted() == true) {
                            ta.setText(ta.getText() + "**************************************************************\n");
                            ta.setText(ta.getText() + "It is now your opponent's turn.\n");
                            mr.setCoordAttack("--");
                        }

                    } else if (!msg.equals("turnSet")) {
                        for (int i = 0; i < 26; i++) {
                            for (int z = 0; z < 26; z++) {
                                if (playerRows.get(i).get(z).getTileCoord().equals(msg)) {
                                    ta.setText(ta.getText() + "Your opponent attacked " + msg + "\n");
                                    playerRows.get(i).get(z).setTileHit(true);
                                    if (!playerRows.get(i).get(z).getTileName().equals("water")) {
                                        playerRows.get(i).get(z).setBackground(Color.red);
                                        
                                        for (int y = (shipList.size() - 1); y >= 0; y--) {
                                            if (shipList.get(y).getName().equals(playerRows.get(i).get(z).getTileName())) {
                                                shipList.get(y).setHealth(shipList.get(y).getHealth() - 1);
                                                ta.setText(ta.getText() + "Your " + shipList.get(y).getName() + " was hit, and has " + shipList.get(y).getHealth() + " health left.\n");
                                                if (shipList.get(y).getHealth() == 0) {
                                                    ta.setText(ta.getText() + "Your " + shipList.get(y).getName() + " was sunk.\n");
                                                    shipList.remove(y);
                                                }
                                            }
                                        }

                                        if (shipList.size() == 0) {
                                            ta.setText(ta.getText() + "**************************************************************\n");
                                            ta.setText(ta.getText() + "Your ships have been sunk. You lose.\n");
                                            mr.setGameStarted(false);
                                            OutputStream os = socket.getOutputStream();
                                            OutputStreamWriter osw = new OutputStreamWriter(os);
                                            BufferedWriter bw = new BufferedWriter(osw);
                                            bw.write("lose\n");
                                            bw.flush();
                                            break;
                                        } else {
                                            OutputStream os = socket.getOutputStream();
                                            OutputStreamWriter osw = new OutputStreamWriter(os);
                                            BufferedWriter bw = new BufferedWriter(osw);
                                            bw.write("hit\n");
                                            bw.flush();
                                        }                                                                                
                                    } else {
                                        playerRows.get(i).get(z).setBackground(Color.gray);
                                        OutputStream os = socket.getOutputStream();
                                        OutputStreamWriter osw = new OutputStreamWriter(os);
                                        BufferedWriter bw = new BufferedWriter(osw);
                                        bw.write("miss\n");
                                        bw.flush();
                                    }
                                    if (turnPriority == 1) {
                                        mr.setPlayerEditable(true, false);
                                    } else {
                                        mr.setPlayerEditable(false, true);
                                    }
                                    ta.setText(ta.getText() + "**************************************************************\n");
                                    ta.setText(ta.getText() + "It is now your turn.\n");
                                    break;

                                }
                            }
                        }
                    }

                    socket.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    // Method that creates the end game window after a player wins

    /**
     * ********************************************************************************************
         */
    public void createEndWindow(int winner) {
        endFrame = new JInternalFrame();
        endFrame.setSize(500, 300);
        endFrame.setLayout(null);
        
        if (winner == 1) {
            endLabel = new JLabel("You win!");
        } else {
            endLabel = new JLabel("Your opponent won the match");
        }
        endLabel.setBounds(90, 10, 300, 100);
        endFrame.add(endLabel);
        endFrame.setDefaultCloseOperation(endFrame.EXIT_ON_CLOSE);
        endFrame.show();
        mr.setGameStarted(false);
        endButton = new JButton("Restart");
        endButton.setBounds(90, 150, 150, 70);
        endFrame.add(endButton);
        Window.add(endFrame);
        endFrame.toFront();

        endButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Random rand = new Random();
                turnPriority = rand.nextInt(2);

                if (turnPriority == 1) {
                    mr.setHumanPlayerNum(1);
                    mr.setAINum(2);
                } else {
                    mr.setHumanPlayerNum(2);
                    mr.setAINum(1);
                }

                System.out.println("Player number is " + mr.getHumanPlayerNum());
                
                Window.getContentPane().removeAll();
                Window.repaint();
                
                clearBoard();
                clearOppBoard();
                        
                createWindows(gameMode, gameMisc);  
                
                createShips(shipList, playerRows, Color.black);
                createShips(oppShipList, oppRows, Color.cyan);

                endFrame.dispose();
            }
        });
    }

    // Method that creates the game board
    /**
     * ********************************************************************************************
     */
    public void createWindows(int mode, int misc) {

        Window.setLayout(null);
        Window.setSize(1600, 1000);
        playerWindow.setLayout(null);
        playerWindow.setBounds(50, 50, 750, 700);
        oppWindow.setLayout(null);
        oppWindow.setBounds(800, 50, 750, 700);
        Window.add(playerWindow);
        Window.add(oppWindow);

        if ((gameMode == 2 && gameMisc == 1) || (gameMode == 1)) {
            startGame = new JButton("Start Game");
            startGame.setBounds(600, 800, 200, 100);
            Window.add(startGame);
            startGame.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    System.out.println("Pinging opponent player to start game.\n");
                    ta.setText(ta.getText() + "Game has started.\n");
                    
                    if (turnPriority == 1) {
                        ta.setText(ta.getText() + "You are player 1.\n");
                    } else {
                        ta.setText(ta.getText() + "You are player 2.\n");
                    }
                    
                    startGame.setEnabled(false);
                    mr.setGameStarted(true);

                }
            });
        }

        randBut.setBounds(300, 600, 100, 50);
        randBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mr.getGameStarted() == false) {
                    clearBoard();
                    createBoard(mode, misc);
                    createShips(shipList, playerRows, Color.black);
                    System.out.println("shipList contains: " + shipList.size() + " ships.");
                }
            }
        });
      
        sp.setBounds(850, 760, 500, 180);
        ta.setEditable(false);
        DefaultCaret caret = (DefaultCaret)ta.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        Window.add(sp);
        
        createBoard(mode, misc);

        Window.show();
        playerWindow.show();
        oppWindow.show();
        Window.setDefaultCloseOperation(Window.EXIT_ON_CLOSE);
    }
    
    // Method for creating the board tiles
    /**********************************************************************************************/
    public void createBoard(int mode, int misc) {
        // add a randomize button to the board
        playerWindow.add(randBut);
        
        // Add tiles and labels on the board
        char colLabel = 'A';
        int rowLabel = 1;
        
        char colCoord = 'A';
        int rowCoord = 1;
        
        for (int i = 0; i <= 26; i++) {

            // this if statement checks whether the first row of the ship board
            // is being printed. If it is the first row, then it will print out 
            // A - Z instead of tiles
            if (i == 0) {
                for (int z = 0; z < 26; z++) {
                    boardLabel = new JLabel(Character.toString(colLabel));
                    boardLabel.setBounds(z * 19 + 209, 50, 18, 18);
                    playerWindow.add(boardLabel);
                    
                    boardLabel = new JLabel(Character.toString(colLabel));
                    boardLabel.setBounds(z * 19 + 209, 50, 18, 18);
                    oppWindow.add(boardLabel);
                     
                    colLabel++;
                }

            } else {
                // tiles below prints out each row of tiles, as well as leading
                // each row with a number from 1 -26
                playerCols = new ArrayList<BoardTile>();
                oppCols = new ArrayList<BoardTile>();

                for (int j = 0; j <= 26; j++) {
                    if (j == 0) {
                        boardLabel = new JLabel(Integer.toString(rowLabel));
                        boardLabel.setBounds(189, i * 19 + 50, 18, 18);
                        playerWindow.add(boardLabel);                     
                        boardLabel = new JLabel(Integer.toString(rowLabel));
                        boardLabel.setBounds(189, i * 19 + 50, 18, 18);
                        oppWindow.add(boardLabel);
                        
                        rowLabel ++;
                    } else {
                        if (turnPriority == 1) {
                            tile = new BoardTile((j - 1) * 19 + 205, i * 19 + 50, 18, 18, true, false, 2, 
                                    "water", Character.toString(colCoord)+Integer.toString(rowCoord), mode, misc);
                            playerCols.add(tile);
                            playerWindow.add(playerCols.get(j - 1));

                            tile = new BoardTile((j - 1) * 19 + 205, i * 19 + 50, 18, 18, false, false, 1, 
                                    "water", Character.toString(colCoord)+Integer.toString(rowCoord), mode, misc);
                            oppCols.add(tile);
                            oppWindow.add(oppCols.get(j - 1));
                            
                            colCoord++;
                        } else {
                            tile = new BoardTile((j - 1) * 19 + 205, i * 19 + 50, 18, 18, true, false, 1, 
                                    "water", Character.toString(colCoord)+Integer.toString(rowCoord), mode, misc);
                            playerCols.add(tile);
                            playerWindow.add(playerCols.get(j - 1));

                            tile = new BoardTile((j - 1) * 19 + 205, i * 19 + 50, 18, 18, false, false, 2, 
                                    "water", Character.toString(colCoord)+Integer.toString(rowCoord), mode, misc);
                            oppCols.add(tile);
                            oppWindow.add(oppCols.get(j - 1));
                            
                            colCoord++;
                        }
                    }
                }
                colCoord = 'A';
                rowCoord++;
                playerRows.add(playerCols);
                oppRows.add(oppCols);
            }
        }
    }
    
    // Method that creates the ships
    /**********************************************************************************************/
    public void createShips(ArrayList<Ship> sL, ArrayList<ArrayList<BoardTile>> board, Color color) {
        boolean shipSet;
        int xyconstraint = 0;
        int orientation = 0;
        int rand1 = 0;
        int rand2 = 0;
        
        // add new ships below
        addShip(sL, "Carrier",5);
        addShip(sL, "Battle Ship",4);
        addShip(sL, "Destroyer",3);
        addShip(sL, "Submarine",3);
        addShip(sL, "Patrol Boat",2);
        
        // determines the locations to put the ships
        for (int i = 0; i < sL.size(); i++) {
            shipSet = false;
            
            while (shipSet == false) {
                shipSet = true;
                Random rand = new Random();
                xyconstraint = sL.get(i).getHealth() - 1;
                rand1 = rand.nextInt(26 - xyconstraint);
                rand2 = rand.nextInt(26 - xyconstraint);
                orientation = rand.nextInt(2);
                
                for (int j = 0; j <= xyconstraint; j++) {
                    if (orientation == 1) {
                        if (board.get(rand1).get(rand2 + j).getShipPart() == true) {
                            shipSet = false;
                        }
                    } else if (orientation == 0) {
                        if (board.get(rand1 + j).get(rand2).getShipPart() == true) {
                            shipSet = false;
                        }
                    }
                }
            }

            if (orientation == 1) {
                
                for (int z = 0; z <= xyconstraint; z++) {
                    board.get(rand1).get(rand2 + z).setShipPart(true);
                    board.get(rand1).get(rand2 + z).setBackground(color);
                    board.get(rand1).get(rand2 + z).setTileName(sL.get(i).getName());
                }
            } else if (orientation == 0) {
                for (int z = 0; z <= xyconstraint; z++) {
                    board.get(rand1 + z).get(rand2).setShipPart(true);
                    board.get(rand1 + z).get(rand2).setBackground(color);
                    board.get(rand1 + z).get(rand2).setTileName(sL.get(i).getName());
                }
            }
        }
    }
    
    // Method that adds a single ship to the game board
    /**********************************************************************************************/
    public void addShip(ArrayList<Ship> sL, String name, int health) {
        Ship ship = new Ship(name, health);
        sL.add(ship);
    }
    
    // Method for clearing the player board
    /**********************************************************************************************/
    public void clearBoard() {
        playerRows.clear();
        shipList.clear();
        playerWindow.getContentPane().removeAll();
        playerWindow.repaint();
    }
    
    // Method for clearing the opponent board
    /**********************************************************************************************/
    public void clearOppBoard() {
        oppRows.clear();
        oppShipList.clear();
        oppWindow.getContentPane().removeAll();
        oppWindow.repaint();
    }
}
