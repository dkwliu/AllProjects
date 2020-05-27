
package adventure.items;

import adventure.Player;
import adventure.rooms.Room;

public class RoomChangeNote extends Item {
    
    private Room room1; 
    private Room room2;
            
    public RoomChangeNote (String name, String description, boolean canTake, Room room1, Room room2) {
        super(name, description, canTake);
        this.room1 = room1;
        this.room2 = room2;
    }

    @Override
    public boolean use(Room currentRoom, Player player) {

//        if (currentRoom.toString().equals("adventure.rooms.LockedRoom@33909752")) {
        if (currentRoom.equals(room1)) {

            System.out.println("You use " + name + ".  You uncrumble the scrap paper. There's something written on it.");
            System.out.println("");
            System.out.println("It reads :");
            System.out.println("'wake'\n'wakeup'\n'awaken'\n'awake'");
            return false;
//        } else if (currentRoom.toString().equals("adventure.rooms.RegularRoom@55f96302")) {             
        } else if (currentRoom.equals(room2)) {             

            System.out.println("You use " + name + ".  You uncrumble the scrap paper. There's something written on it.");
            System.out.println("");
            System.out.println("It reads :");
            System.out.println("'sleep'");
            return false;
        } else {
            super.use(currentRoom, player); // This code checks the room that this item is used in
            return false;                   // If it is used in its intended rooms, it will display a set of descriptions
        }                                   // If it is not used in intended rooms, it will display the description in the original use() method
    }     
}
