
package adventure.rooms;

import java.util.*;
import adventure.items.Item;
import java.util.ArrayList;

public class LockedRoom extends Room {
    
    private ArrayList<Path> exits;
    private ArrayList<Item> items;
    
    private String password;
    private String hint;
    
    // constructor.
    public LockedRoom(String name, String desc, String pw, String pwhint) {
        super(name, desc);
        password = pw;
        hint = pwhint;
    }
    
    ////// Movement
    //////
    
    // This code makes it so that entering a 
    // locked room will require a password.
    // If the player enters the wrong password, the function will recurse and ask for a password again
    public boolean enterRoom() {
        System.out.println("The door requires a password. Input 'end' to end this interface.");
        System.out.println("the hint is: " + hint);
        System.out.print("> ");        
        Scanner scanpw = new Scanner(System.in);
        String answer = scanpw.next();

        if (answer.equals("end")) {
            return false;    
        } else if (answer.equals(password)) {
            System.out.println("");
            printDescription();
            return true;            
        } else {
            System.out.println("Your password is incorrect");
            // If the player enters the wrong password, the function will recurse and ask again
            return enterRoom();        
        }

    }

} // end Room
