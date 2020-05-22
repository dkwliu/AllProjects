package battleship;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;
import java.net.*;

public class BoardTile extends JPanel {
    private boolean clicked = false;
    private boolean playerTile;
    private boolean shipPart;
    private boolean hit;
    private String tileName;
    private String coordinate;
    private int playerNum;
    private int gameMode;
    private int gameMisc;
    MessageRelay mr;
    private static Socket socket;
    
    public BoardTile(int horShift, int vertShift, int length, 
        int width, boolean playerTile, boolean shipPart, int pri,
        String tileName, String coordinate, int mode, int misc) {
        this.setBackground(Color.cyan);
        this.setBounds(horShift, vertShift, length, width);
        this.playerTile = playerTile;
        this.shipPart = shipPart;
        this.tileName = tileName;
        this.coordinate = coordinate;
        playerNum = pri;
        gameMode = mode;
        gameMisc = misc;
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clicked();
            }
        });
    }

    public void clicked() {
        if (gameMode == 1) {
            mr = new MessageRelay();

            if (mr.getGameStarted() == true) {
                if (playerNum == 1) {

                    if (playerTile == false && hit == false && mr.getPlayer1Editable() == true) {
                        if (!tileName.equals("water")) {
                            this.setBackground(Color.red);
                        } else {
                            this.setBackground(Color.gray);
                        }
                        MessageRelay msg = new MessageRelay(tileName, playerNum);
                        msg.setP1TurnMade(true);
                        mr.setHitShipName(tileName);
                        System.out.println("Player " + playerNum + " shot at coordinates " + getTileCoord());
                        hit = true;
                    }
                } else {
                    if (playerTile == false && hit == false && mr.getPlayer2Editable() == true) {
                        if (!tileName.equals("water")) {
                            this.setBackground(Color.red);
                        } else {
                            this.setBackground(Color.gray);
                        }
                        MessageRelay msg = new MessageRelay(tileName, playerNum);
                        msg.setP2TurnMade(true);
                        mr.setHitShipName(tileName);
                        System.out.println("Player " + playerNum + " shot at coordinates " + getTileCoord());
                        hit = true;
                    }
                }
            }
            // 0 is to join game
        } else if (gameMode == 2 && gameMisc == 0) {
            mr = new MessageRelay();
            if (mr.getGameStarted() == true) {
                if (playerNum == 1) {

                    if (mr.getPlayer1Editable() == true && playerTile == false && hit == false) {
                        mr.setCoordAttack(coordinate);
                        mr.setCoordSet(true);
                        hit = true;
                    }
                } else {
                    if (mr.getPlayer2Editable() == true && playerTile == false && hit == false) {
                        mr.setCoordAttack(coordinate);
                        mr.setCoordSet(true);
                        hit = true;
                    }
                }
            }
            // 1 is to make game
        } else if (gameMode == 2 || gameMisc == 1 ) {
            mr = new MessageRelay();
            if (mr.getGameStarted() == true) {
                if (playerNum == 1) {

                    if (mr.getPlayer1Editable() == true && playerTile == false && hit == false) {
                        mr.setCoordAttack(coordinate);
                        mr.setCoordSet(true);
                        hit = true;
                    }
                } else {
                    if (mr.getPlayer2Editable() == true && playerTile == false && hit == false) {
                        mr.setCoordAttack(coordinate);
                        mr.setCoordSet(true);
                        hit = true;
                    }
                }
            }
        }
    }

    public int getTilePriority() {
        return playerNum;
    }
    
    public void setTilePriority(int pri) {
        playerNum = pri;
    }
    
    public void setTileName(String name) {
        this.tileName = name;
    }
    
    public String getTileName() {
        return tileName;
    }
    
    public boolean getShipPart() {
        return shipPart;
    }
    
    public boolean getPlayerTile() {
        return playerTile;
    }
    
    public void setShipPart(boolean shipPart) {
        this.shipPart = shipPart;
    }
    
    public boolean getTileHit() {
        return hit;
    }
    
    public void setTileHit(boolean hit) {
        this.hit = hit;
    }
    
    public void setTileCoord(String coord) {
        coordinate = coord;
    }
    
    public String getTileCoord() {
        return coordinate;
    }
}
