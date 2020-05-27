
package adventure.items;

import adventure.Player;
import adventure.rooms.Room;

public interface ItemInterface {

    public String getDescription();
    
    public String getName();
    
    public boolean take();

    public boolean drop();

    public boolean isReference(String possibleName);
        
    public boolean equal(Item itemToCompare);
        
    public boolean use(Room currentRoom, Player player);
    
    public void printInventoryItem();
 
}
