
package adventure.rooms;

import adventure.items.Item;
import java.util.ArrayList;

public class RegularRoom extends Room {
    private ArrayList<Path> exits;
    private ArrayList<Item> items;
    
    
    // constructor.
    public RegularRoom(String name, String desc) {
        super(name, desc);
    }    
}
