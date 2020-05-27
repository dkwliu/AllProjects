
package adventure.items;

import adventure.Player;
import adventure.rooms.Room;

public class JavaExam extends Item {
    
    public JavaExam (String name, String description, boolean canTake) {
        super(name, description, canTake);         
    }
    
    @Override
    public boolean use(Room currentRoom, Player player) {
        
        System.out.println("You use " + name + ".  You look at the exam. ");
        System.out.println("");
        System.out.println("The exam packet is completey blank... all except the last page which says \n" +
                "\n to leave... you  must 'wake' up"                  // simple item that disappears after its used
                );       
        
        return true;
    }    
}
