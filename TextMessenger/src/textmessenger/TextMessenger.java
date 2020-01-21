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

    public static void main(String[] args) {
        TextWindow TW = new TextWindow();
        TextListener TL = new TextListener();
        
        TW.start();
        TL.start();
    }
}
