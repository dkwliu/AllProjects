
package adventure.items;

import adventure.Player;
import adventure.rooms.Room;

public class BedroomNote extends Item {
    
    public BedroomNote (String name, String description, boolean canTake) {
        super(name, description, canTake);         
    }
    
    @Override
    public boolean use(Room currentRoom, Player player) {
        System.out.println("You use " + name + ".  You proceed to unfold the note. ");
        System.out.println("");
        System.out.println("It reads :");       
        System.out.println("Note to self: DO SOMETHING ABOUT ALL THE LOCKED ROOMS \n" +
                "AROUND THE HOUSE BEFORE SOMEBODY ACTUALLY GETS HURT. Remember to \n" +
                "place one copy of your password note cards into each of your password \n" +
                "-locked rooms. I've placed them on the kitchen table. Go check in the morning "
                + "when you 'wake' up"
        );
        
        return false;
    }      
}
