
package adventure.items;

import adventure.Player;
import adventure.rooms.Room;

public class KitchenNote extends Item{
    
    public KitchenNote (String name, String description, boolean canTake) {
        super(name, description, canTake);         
    }
    
    @Override
    public boolean use(Room currentRoom, Player player) {
        
        System.out.println("You use " + name + ".  You read the note. ");
        System.out.println("");
        System.out.println("It reads :");       
        System.out.println("Put these notes in your lock-rooms when you 'wakeup'\n\n"
                + "recurse   if   polymorph");
        
        return false;
    }      
}
