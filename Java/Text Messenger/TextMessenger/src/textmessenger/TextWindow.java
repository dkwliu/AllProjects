package textmessenger;
import java.util.*;
import java.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class TextWindow extends Thread{
    
    JFrame textUI = new JFrame("Text Window");
    private static JEditorPane textBox = new JEditorPane();
    JEditorPane inputBox = new JEditorPane();
    JScrollPane textBoxScroll = new JScrollPane(textBox);
    JScrollPane inputBoxScroll = new JScrollPane(inputBox);
    JButton sendText = new JButton("SEND");
    private static Socket socket; 
    private int oldCode = 0;
    
    // When the class is initiated, it creates the GUI for the
    // text messenger
    public TextWindow() {
        // window size
        textUI.setLayout(null);  
        textUI.setSize(700,500);
        
        textUI.setDefaultCloseOperation(textUI.EXIT_ON_CLOSE);   
               
        textBox.setBackground(Color.WHITE);
        textBox.setEditable(false);
        inputBox.setBackground(Color.WHITE);
               
        textBoxScroll.setBounds(50,50,500,250);
        textUI.add(textBoxScroll);
        inputBoxScroll.setBounds(50,310,330,100);
        textUI.add(inputBoxScroll);
        sendText.setBounds(400,310,150,100);
        textUI.add(sendText);

        textUI.show();
    }

    // The portion of this class that runs when its thread is started
    public void run() {
        while (true) {
            // Adds action listener for the button in the text window.
            sendText.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    printText();
                }
            });

            // binds the text submission action to your enter key
            EnterAction ea = new EnterAction();
            inputBox.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "print");
            inputBox.getActionMap().put("print", ea);

            // while loop that notifies the textwindow of a message
            TextRelay tr = new TextRelay();
            
            if (tr.getMsgCode() != oldCode) {
                System.out.println("old code from Window: " + oldCode);
                System.out.println("new message code from Window: " + tr.getMsgCode());
                System.out.println("*************************************************");
                setTextBox(tr.getMsgString());
                oldCode = tr.getMsgCode();
            }
        }
    }

    // This action below is created and bounded to the enter key
    class EnterAction extends AbstractAction {
        public void actionPerformed(ActionEvent enter) {
            printText();
        }
    }
    
    public void setTextBox(String msg) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        textBox.setText(textBox.getText() + msg + "\n(" + date + ")\n\n");
    }
    
    // method is used for the action of sending a message to the server
    // and having it sent back to the client
    public void printText() {

        if (!inputBox.getText().equals("")) {
            try {
                //InetAddress address = InetAddress.getByName(host);
                socket = new Socket("10.0.0.16", 5555);

                //Send the message to the server
                OutputStream outStream = socket.getOutputStream();
                OutputStreamWriter outStreamW = new OutputStreamWriter(outStream);
                BufferedWriter Bw = new BufferedWriter(outStreamW);

                String inputString = inputBox.getText();

                String sendMessage = inputString + "\n";
                Bw.write(sendMessage);
                Bw.flush();
                System.out.println("*************************************************");
                System.out.println("Message sent to the server : " + sendMessage);

                //Get the return message from the server
                InputStream inStream = socket.getInputStream();
                InputStreamReader inStreamR = new InputStreamReader(inStream);
                BufferedReader Br = new BufferedReader(inStreamR);
                String message = Br.readLine();
                System.out.println("Message received from the server : " + message);
            } catch (Exception exception) {
                exception.printStackTrace();
                System.out.print("Connection refused from TextWindow");
            } finally {
                //Closing the socket
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        inputBox.setText("");
    }
}
