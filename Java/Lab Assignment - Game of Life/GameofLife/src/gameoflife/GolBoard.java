
package gameoflife;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.*;
import java.util.*;

public class GolBoard extends JFrame{
    
    private int columns;
    private int rows;
    ArrayList<GolTile> tileList = new ArrayList<GolTile>();
    private int step = 0;
        
    // Constructor creates board
    public GolBoard(int size) {
        this.columns = size;
        this.rows = size;
        
        this.setLayout(null);
        this.setBounds(200,100,800,900); 
        addTiles(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        this.setVisible(true);
                
        StartButton newStartButton = new StartButton(this);
        newStartButton.setBounds(200,800,150,50);
        this.add(newStartButton, BorderLayout.SOUTH);
        
        StopButton newStopButton = new StopButton(this);
        newStopButton.setBounds(400, 800, 150, 50);
        this.add(newStopButton, BorderLayout.SOUTH);
        
        new Thread(new Runnable() {
            public void run() {
                StepAction();
            }
        }).start();

    }
    
    // adds tiles into the board depending on the board size specified
    // when GolBoard was initialized
    public void addTiles(JFrame j) {
        int boxsize = 800/columns;
        
        for (int y = 0; y < rows; y++) {
            for(int x = 0; x < columns; x++) {
                j.add(new GolTile(x * boxsize, y * boxsize, boxsize));  
                tileList.add(new GolTile(x * boxsize, y * boxsize, boxsize));
            }
        }
    }
    
    // steps each generation
    public void step() {
        int tiles = this.getContentPane().getComponents().length;
        int neighbors = 0;
        
        for (int i = 0; i < tiles; i++) {

            if (i - 1 > -1 && i % rows != 0
                    && (this.getContentPane()
                            .getComponent(i - 1).getBackground() 
                    == Color.red || this.getContentPane()
                            .getComponent(i - 1).getBackground()
                    == Color.black)) {
                neighbors = neighbors + 1;
            }

            if ((i - rows - 1) > -1 && i % rows != 0
                    && (this.getContentPane()
                            .getComponent(i - rows - 1).getBackground() 
                    == Color.red || this.getContentPane()
                            .getComponent(i - rows - 1).getBackground()
                    == Color.black)) {
                neighbors = neighbors + 1;
            }

            if (((i - rows) > -1)
                    && (this.getContentPane()
                            .getComponent(i - rows).getBackground() 
                    == Color.red || this.getContentPane()
                            .getComponent(i - rows).getBackground()
                    == Color.black)) {
                neighbors = neighbors + 1;
            }

            if ((i - rows + 1) > -1 && i % (rows - 1) != 0
                    && (this.getContentPane()
                            .getComponent(i - rows + 1).getBackground() 
                    == Color.red || this.getContentPane()
                            .getComponent(i - rows + 1).getBackground()
                    == Color.black)) {
                neighbors = neighbors + 1;
            }

            if ((i + 1) < tiles && i % (rows - 1) != 0
                    && (this.getContentPane()
                            .getComponent(i + 1).getBackground() 
                    == Color.red || this.getContentPane()
                            .getComponent(i + 1).getBackground()
                    == Color.black)) {
                neighbors = neighbors + 1;
            }

            if (((i + rows + 1) < tiles) && i % (rows - 1) != 0
                    && (this.getContentPane()
                            .getComponent(i + rows + 1).getBackground() 
                    == Color.red || this.getContentPane()
                            .getComponent(i + rows + 1).getBackground()
                    == Color.black)) {
                neighbors = neighbors + 1;
            }

            if (i + rows < tiles && (this.getContentPane()
                            .getComponent(i + rows).getBackground() 
                    == Color.red || this.getContentPane()
                            .getComponent(i + rows).getBackground()
                    == Color.black)) {
                neighbors = neighbors + 1;
            }

            if (((i + rows - 1) < tiles) && i % rows != 0
                    && (this.getContentPane()
                            .getComponent(i + rows - 1).getBackground() 
                    == Color.red || this.getContentPane()
                            .getComponent(i + rows - 1).getBackground()
                    == Color.black)) {
                neighbors = neighbors + 1;
            }

            if (this.getContentPane()
                    .getComponent(i).getBackground()
                    == Color.red) {
                if (neighbors < 2) {
                    this.getContentPane().getComponent(i)
                            .setBackground(Color.black);
                } else if (neighbors > 3) {
                    this.getContentPane().getComponent(i)
                            .setBackground(Color.black);
                } else {
                    // no action
                }
            } else {
                if (neighbors == 3) {
                    this.getContentPane().getComponent(i)
                            .setBackground(Color.gray);
                }
            }
            
            neighbors = 0;

        }

        for (int i = 0; i < tiles; i++) {
            if (this.getContentPane().getComponent(i)
                    .getBackground() == Color.black) {
                this.getContentPane().getComponent(i)
                        .setBackground(Color.white);
            } else if (this.getContentPane().getComponent(i)
                    .getBackground() == Color.gray) {
                this.getContentPane().getComponent(i)
                        .setBackground(Color.red);
            } else {
                // no action
            }
        }
    }

    // start button
    class StartButton extends JButton implements ActionListener {

        private GolBoard gb;

        public StartButton(GolBoard gb) {
            this.gb = gb;
            this.setText("Start");
            this.addActionListener((ActionListener) this);
        }

        // Start button. Switch "step" var to 1 to start stepping generations
        public void actionPerformed(ActionEvent a) {
            // Testing purpose. Checking value stored in step var
            step = 1;
            System.out.println(step);
        }
    }
    
    // Steps each generation
    public void StepAction() {
        
        do {
            // Testing purpose. Checking value stored in step var
            System.out.println(step);
            
            if (step == 1) {

                try {
                    System.out.println("Once");
                    step();
                    Thread.sleep(700);

                } catch (Exception e) {
                    System.out.println("something went wrong");
                }
            }
        } while (true);

    }
    
    // stop button
    class StopButton extends JButton implements ActionListener {

        private GolBoard gb;

        public StopButton(GolBoard gb) {
            this.gb = gb;
            this.setText("Stop");
            this.addActionListener((ActionListener) this);
        }
        
        // set step var to 0 to stop generation progression
        public void actionPerformed(ActionEvent a) {
            step = 0;            
        }
    }
}
