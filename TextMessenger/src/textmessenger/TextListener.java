package textmessenger;

import java.util.*;
import java.io.*;
import java.net.*;

public class TextListener extends Thread{    
    
    private Socket socket;
    private int code = 0;
    private int newCode = 1;
    private int msgCode = -1;
    private int recCode;
    
    public void run() {

        try {
            socket = new Socket("10.0.0.16", 5555);

            // send message to server
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            String sendMsg = "/checkcode\n";
            bw.write(sendMsg);
            bw.flush();

            // receive message
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            recCode = Integer.parseInt(br.readLine());
            msgCode = recCode;
            
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true) {
            try {

                socket = new Socket("10.0.0.16", 5555);

                // send message to server
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);

                String sendMsg = "/checkcode\n";
                bw.write(sendMsg);
                bw.flush();

                // receive message
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                recCode = Integer.parseInt(br.readLine());
                //System.out.println(recMsg);
                socket.close();
                
                if (recCode != msgCode) {
                    msgCode = recCode;

                    // send message to server
                    socket = new Socket("10.0.0.16", 5555);
                    os = socket.getOutputStream();
                    osw = new OutputStreamWriter(os);
                    bw = new BufferedWriter(osw);

                    sendMsg = "/checkmsg\n";
                    bw.write(sendMsg);
                    bw.flush();

                    // receive message
                    is = socket.getInputStream();
                    isr = new InputStreamReader(is);
                    br = new BufferedReader(isr);
                    String recMsg = br.readLine();
                    //System.out.println(recMsg);

                    if (!recMsg.equals("There are no new messages in server")) {

                        Random rand = new Random();
                        newCode = rand.nextInt(1000);

                        while (code == newCode) {
                            newCode = rand.nextInt(1000);
                        }

                        System.out.println("old code from Listener: " + code);
                        System.out.println("new message code Listener: " + newCode);

                        TextRelay tr = new TextRelay();
                        tr.setMsgCode(newCode);
                        tr.setMsgString(recMsg);
                        code = newCode;
                    }
                }
                /*************************************************************************************/
                
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Connection Refused from TextListener");
            } finally {
                try {
                    socket.close();
                    Thread.sleep(50);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
    }
}
