
package adventure.rooms;

import adventure.items.Item;
import java.util.ArrayList;

public interface RoomInterface {

    public void setDescription(String newDesc);
    
    public void addExit(Room target, String dir);

    public void printDescription();

    public boolean enterRoom();

    public boolean exitRoom();

    public Room tryToExit(String direction);

    public Path getExit (String possibleExit);
 
    public void printExits();

    public void addItem(Item item);

    public Item getItem(String possibleName);

    public void removeItem(Item itemToRemove);

     
}
