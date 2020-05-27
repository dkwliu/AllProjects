
package adventure.rooms;

import adventure.items.Item;
import java.util.ArrayList;

public class FinalRoom extends Room{
    private ArrayList<Path> exits;
    private ArrayList<Item> items;
    public boolean isEntered = false;   // this variable indicates the presence of the player in the final room.
    
    // constructor.
    public FinalRoom(String name, String desc) {
        super(name, desc);
    }      
    
    public void printExits() {
        return;
    }    
  
}
