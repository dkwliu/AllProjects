
package adventure.items;

import adventure.Player;
import adventure.rooms.Room;

public class LivingroomNote extends Item {

    public LivingroomNote(String name, String description, boolean canTake) {
        super(name, description, canTake);
    }

    @Override
    public boolean use(Room currentRoom, Player player) {

        System.out.println("You use " + name + ".  You proceed to read the note on the card. ");
        System.out.println("");
        System.out.println("It reads :");
        System.out.println("The password locked rooms down in the basement are a marvel! \n"
                + "I am finally putting my knowledge of Java to good and practical use!.... \n"
                + "Yes. I know.. the idea of locked rooms in the basement doesn't sit well with \n"
                + "most people...."
        );
        return false;

    }
}
