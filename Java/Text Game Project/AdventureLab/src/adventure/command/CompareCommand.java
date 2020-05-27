package adventure.command;

// This class gathers players input, and lets you match to it.

import java.util.ArrayList;
import java.util.Scanner;

public class CompareCommand extends InputGather {

    //a scanner object to process the particular input string
    Scanner stringProcessor;

    // This contains the full user entry, as a string or arraylist of strings (words).
    // Subclasses can access these.
    protected String inputString;
    protected ArrayList<String> inputWords;    
    // we'll just use the parent class' instance variables. 

    // The constructor
    public CompareCommand() {
        super();
     
    }
    
    // ignores case
    public boolean isInput(String possibleInput) {
        return (inputString.equalsIgnoreCase(possibleInput));
    }
    
    // ignores case
    public boolean contains(String possibleInput) {
        return (inputString.toLowerCase().contains(possibleInput.toLowerCase()));
    }

} // end class