/*
BUG REPORT
*******************************************************************************
  --->47-56
*/

package textserver;
import java.net.*;
import java.io.*;
import java.util.*;

public class TextServer {

    private static Socket socket;
    private static int count = 0;
    private static ArrayList<String> Messages = new ArrayList<String>();
    private static ArrayList<Socket> clients = new ArrayList<Socket>();
    private static String outMsg;
    private static int oldMsgCode = 0;
    private static int msgCode = 1;

    public static void main(String[] args) {
        
        try
        {
            ServerSocket serverSocket = new ServerSocket(5555);
            System.out.println("Server Started and listening to the port 5555");

            //Server is running always.
            while (true) {
                //Reading the message from the client
                socket = serverSocket.accept();

                InputStream inStream = socket.getInputStream();
                InputStreamReader inStreamR = new InputStreamReader(inStream);
                BufferedReader Br = new BufferedReader(inStreamR);

                String receiveString = Br.readLine();

                System.out.println("Message received from client is " + receiveString);

                // outputting messsages
                if (receiveString.equals("/checkcode")) {
                    OutputStream outStream = socket.getOutputStream();
                    OutputStreamWriter outStreamW = new OutputStreamWriter(outStream);
                    BufferedWriter Bw = new BufferedWriter(outStreamW);

                    outMsg = Integer.toString(msgCode);
                    Bw.write(outMsg);
                    //System.out.println("Received a checkcode");
                    Bw.flush();
                } else if (receiveString.equals("/checkmsg")) {

                    // Fix codes below so that checkNewMsg does not switch
                    // to false until after all the clients have received the 
                    // new rmessage
                    //UPDATE
                    // should be fixed
                    if (count > 0) {
                        OutputStream outStream = socket.getOutputStream();
                        OutputStreamWriter outStreamW = new OutputStreamWriter(outStream);
                        BufferedWriter Bw = new BufferedWriter(outStreamW);
                        
                        outMsg = Messages.get(count - 1) + "\n";
                        Bw.write(outMsg);
                        System.out.println("Received a checkmsg");
                        Bw.flush();
                    } else {
                        OutputStream outStream = socket.getOutputStream();
                        OutputStreamWriter outStreamW = new OutputStreamWriter(outStream);
                        BufferedWriter Bw = new BufferedWriter(outStreamW);
                    
                        Bw.write("There are no new messages in server\n");
                        Bw.flush();
                    }
                } else {
                    Messages.add(receiveString);
                    count++;
                    
                    oldMsgCode = msgCode;
                    Random rand = new Random();                        
                    msgCode = rand.nextInt(1000);
                    
                    // ensures the message code isnt similar to the previous code
                    // to prevent duplicate messages being recorded
                    while (oldMsgCode == msgCode) {                      
                        msgCode = rand.nextInt(1000);
                    }

                    //Sending the response back to the client.
                    String returnMessage = Messages.get(count - 1) + "\n";
                    OutputStream outStream = socket.getOutputStream();
                    OutputStreamWriter outStreamW = new OutputStreamWriter(outStream);
                    BufferedWriter Bw = new BufferedWriter(outStreamW);
                    Bw.write(returnMessage);
                    System.out.println("Message sent to the client is " + returnMessage);
                    Bw.flush();
                }
                
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
            }
        }
    }

}
