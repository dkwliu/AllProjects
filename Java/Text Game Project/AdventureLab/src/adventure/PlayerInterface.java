
package adventure;

import adventure.command.Command;
import adventure.items.Item;
import adventure.rooms.Room;
import java.util.ArrayList;
import testclasses.Tester;

public interface PlayerInterface {

    public void setCurrentRoom(Room room);
    
    public Room getCurrentRoom();
    
    
    
    public Item getItem(String name);
    
    public void addItem(Item itemToAdd);
    
    public void removeItem(Item itemToRemove);

    
    public void dispatch(Command turn);


    
    public void actionTravel(Command turn);


    public void actionLookRoom();
    
    
    public void actionTake(Command turn);

    
    public void actionDrop(Command turn);

    
        
    public void actionInventory();

    public void printInv();
    
    
    public void actionUse(Command turn);

    
}
