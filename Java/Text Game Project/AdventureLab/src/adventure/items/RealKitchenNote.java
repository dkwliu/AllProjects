
package adventure.items;

import adventure.Player;
import adventure.rooms.Room;

public class RealKitchenNote extends Item{

    public RealKitchenNote (String name, String description, boolean canTake) {
        super(name, description, canTake);         
    }
    
    @Override
    public boolean use(Room currentRoom, Player player) {
        
        System.out.println("You use " + name + ".  You read the note. ");
        System.out.println("");
        System.out.println("It reads :");       
        System.out.println("Recursive methods call on themselves\n\n"
                + "'if' is a conditional statement\n\n "
                + "polymorphism is the ability of an object to take on many forms\n\n"
                + ".... it looks like a study note...."
        );
        
        return false;
    }      
}
