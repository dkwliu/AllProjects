
package adventure.items;

import adventure.Player;
import adventure.rooms.Room;

public class Note extends Item {   
    
    public Note (String name, String description, boolean canTake) {
        super(name, description, canTake);         
    }
    
    @Override
    public boolean use(Room currentRoom, Player player) {
        System.out.println("You use " + name + ".  You proceed to read the note. ");
        System.out.println("");        
        System.out.println("It reads :");        
        System.out.println("How do I get out of this room. I need to 'remember'. I've got to " +
                "'REMEMBER' ...");
        
        return false;
    }       
}
