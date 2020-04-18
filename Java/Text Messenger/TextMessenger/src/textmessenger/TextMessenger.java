package textmessenger;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import javax.swing.*;

public class TextMessenger {
    private static String ip = "10.0.0.16";
    
    public static void main(String[] args) {
        
        TextWindow TW = new TextWindow(ip);
        TextListener TL = new TextListener(ip);
        
        TW.start();
        TL.start();
    }
}
