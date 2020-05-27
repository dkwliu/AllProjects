
package adventure.command;

import java.util.ArrayList;
import java.util.Scanner;

public interface InputGatherInterface {

    public void gatherInput();

    
    public void printPrompt();
    
    
    public String getInputString();

    public ArrayList<String> getInputWords();
}
