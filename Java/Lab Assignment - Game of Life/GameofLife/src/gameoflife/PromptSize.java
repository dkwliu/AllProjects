
package gameoflife;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PromptSize extends JFrame implements ActionListener {
    
    JTextField textInput;
    JButton setButton;
    JLabel newLabel;
    private String input;
    
    public PromptSize() {
        textInput = new JTextField(1);
        textInput.setBounds(110, 0, 50, 50);
        newLabel = new JLabel("Enter Grid Size");
        newLabel.setBounds(20, 0, 200, 50);
        setButton = new JButton("Set");
        setButton.setBounds(50,80,100,50);
        
        this.add(newLabel, BorderLayout.NORTH);
        this.add(textInput, BorderLayout.CENTER);
        this.add(setButton, BorderLayout.SOUTH);
        
        this.setLayout(null);
        this.setBounds(200,200,200,200); 
        this.setVisible(true);        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setButton.addActionListener((ActionListener) this);
    }
    
    // override actionPeformed method
    public void actionPerformed(ActionEvent a) {
        input = textInput.getText();
        int intInput = Integer.parseInt(input);
        GolBoard newBoard = new GolBoard(intInput);
        dispose();
    }
}
