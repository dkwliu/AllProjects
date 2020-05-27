
package gameoflife;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GolTile extends JPanel implements MouseListener {
    
    public GolTile(int locx, int locy, int size) {
        
        this.setLocation(locx,locy);        
        this.setSize(size,size);
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.addMouseListener(this);
        this.setVisible(true);
    }
    
    // Mouse events
    public void mouseClicked(MouseEvent e) {
        this.setBackground(Color.red);
    }    
    
    public void mousePressed(MouseEvent e) {
        
    }
    
    public void mouseEntered(MouseEvent e) {
    
    }
    
    public void mouseExited(MouseEvent e) {
        
    }
    
    public void mouseReleased(MouseEvent e) {
        
    }
}
