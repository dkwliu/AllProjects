
package adventure;

import adventure.command.Command;
import adventure.items.BedroomNote;
import adventure.items.Item;
import adventure.items.JavaExam;
import adventure.items.KitchenNote;
import adventure.items.LivingroomNote;
import adventure.items.Note;
import adventure.items.RealKitchenNote;
import adventure.items.RoomChangeNote;
import adventure.rooms.FinalRoom;
import adventure.rooms.LockedRoom;
import adventure.rooms.RegularRoom;
import adventure.rooms.Room;
import java.util.ArrayList;

public interface WorldInterface {
    
    public void addRoom(Room room);

    public Room getCurrentRoom();
    
    public void setCurrentRoom(Room room);
        
    public void play();
 
    public void gameOver();
    
    
        
}
