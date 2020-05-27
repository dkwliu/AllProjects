package adventure;

import adventure.rooms.*;
import adventure.command.Command;
import adventure.items.*;
import java.util.*;
import java.io.*;


// The world contains the rooms, the player, and where the player is
public class World implements WorldInterface {

    private ArrayList<Room> rooms;
    private Player thePlayer;
    
    private boolean awake = false;
    
    
    private boolean isPlaying;

    
    public static void main(String[] args) {
        World theWorld = new World();
        theWorld.play();
    }
       
    
    //////
    ////// Setup (populate the world)
    //////

    
    public World() {
        rooms = new ArrayList<Room>();
        thePlayer = new Player(this);

        // create the items
        Item note = new Note("note","It appears to be a folded piece of paper", true);
        Item notetwo = new BedroomNote("notecard","It appears to be a crumpled piece of paper", true);
        Item notethree = new LivingroomNote("card","It is a clue on the notecard", true);
        Item notefour = new KitchenNote("passnote", "I believe this is the card containing my passwords", true);
        Item notesix = new RealKitchenNote("note", "It is a note with something scribbled on it", true);
        Item noteseven = new JavaExam("exam", "It is a test packet", true);
        
        //create and add rooms
        LockedRoom darkroom = new LockedRoom("darkroom",
                "The room you are in is completely dark, no more than a few square feet in size. \n" + 
                "You can barely make out a door.","remember", "read the note"        
        );
        darkroom.addItem(note);        
        addRoom(darkroom);
        
        RegularRoom realbedroom = new RegularRoom("realbedroom",
            "You wake up lying on the floor of your bedroom. There are no locked rooms or scribbled notes around the floor \n"
          + "You realize that you just had a very vivid dream after blacking out last night from your massive drinking party.\n"
          + "It is 2 AM and you realize that you are going to have your Java exam later today. You figure that you still have \n"
          + "some time left so you begin studying."         
        );
        addRoom(realbedroom);
        
        FinalRoom realoutside = new FinalRoom("realoutside", "You've left your house to go take your Java exam. After a night of \n"
            + "nightmares and studying, you feel that you are ready for whatever topics you're about to be tested on \n"
            + "Great Job! You've made it through the night before finals!");        
        addRoom(realoutside);
        
        LockedRoom bedroom = new LockedRoom("bedroom",
            "You find yourself in your bedroom. Books and notes are still strewn all over the floor. \n" +
            "You now remember that you were studying for your java exam the night before. Looking out the window, \n" +
            "the sky is still dark so you know it is only a couple hours pass midnight. You notice a trail of books \n" + 
            "and notes that leads down the stairs to the north. ", "remember", "read the note"
        );
        bedroom.addItem(notetwo);
        addRoom(bedroom);
        
        RegularRoom livingroom = new RegularRoom("livingroom", 
                "You are in your living room and the bedroom locked behind you. The paper trail leads towards your front door. Heading east will lead you to your front door. \n" + 
                "Heading north will lead you to your kitchen. Heading west will take you to the basement. South will lead you back to your bedroom."
        );
        livingroom.addItem(notethree);
        addRoom(livingroom);
        
        RegularRoom kitchen = new RegularRoom("kitchen",
                "You are in the kitchen. South will lead you back to the living room"
        );
        kitchen.addItem(notefour);
        addRoom(kitchen);
        
        RegularRoom basement = new RegularRoom("basement",
                "You are in the basement. You see three locked rooms that look exactly like the one you were just locked in. \n" +
                "The three locked rooms are to your south, west and north. Heading east will lead back to the living room"        
        );
        addRoom(basement);
        
        LockedRoom northroom = new LockedRoom("northroom", 
                "You have entered the northern-most locked room. You leave the door open behind you so that you wont 'wakeup' \n" +
                "in a dark room again. You see the words \n\n 'jav' \n\nwritten on the wall","polymorph" ,"I can be many" 
        );
        addRoom(northroom);
        
        LockedRoom westroom = new LockedRoom("westroom", 
                "You have entered the western-most locked room. You leave the door open behind you so that you wont 'wake' \n" +
                "in a dark room again. You see the words \n\n 'ife' \n\nwritten on the wall","if" ,"it is a conditional..." 
        );
        addRoom(westroom);
        
        LockedRoom southroom = new LockedRoom("southroom", 
                "You have entered the southern-most locked room. You leave the door open behind you so that you wont 'awaken' \n" +
                "in a dark room again. You see the words \n\n 'aisl' \n\nwritten on the wall ","recurse" ,"I love calling myself" 
        );
        addRoom(southroom);
        
        LockedRoom outside = new LockedRoom("front door", "Right after you step through the door, you fall into a dark and empty room"
                , "javaislife", "NORTH > SOUTH > WEST");
//        outside.addItem(notefive);
        addRoom(outside);
        
        RegularRoom driveway = new RegularRoom("driveway", "You are now outside your house. The sky now looks hazy and has an \n" +
                "unusual tint of red. You figure that its probably just the street lights causing the unusual coloration\n" +
                "You notice the paper trail leads north up the street"
        );
        addRoom(driveway);
        
        RegularRoom BCCstreet = new RegularRoom("BCCstreet",
                "You are outside Berkeley City College. The streets are completely deserted and the sky still has a red tint. \n"
              + "The paper trail goes into the building. North goes into the building. South leads back to outside your house"
        );
        addRoom(BCCstreet);
        
        LockedRoom BCC = new LockedRoom("BCC", 
                "The BCC building is completely deserted. The trail goes up the stairs to the third floor and into the computer lab. \n"
              + "You follow the trail up to the third floor. The computer lab is to the east", 
                "sleep", "be outside, and the scrap holds the answer"        
        );
        addRoom(BCC);
        
        RegularRoom complab = new RegularRoom("complab",
                "You enter the computer lab and you see the paper trail ends at a computer seat at the far corner. \n"
              + "Doors in and out of the room have all disappeared. There must be another way out of here \n"
              + "As you move closer to the seat, you notice a packet of paper"
        );
        complab.addItem(noteseven);
        addRoom(complab);
        
        RegularRoom reallivingroom = new RegularRoom("reallivingroom",
                "The bedroom is now locked. Your living room is still a mess from yesterday's party. \n"
              + "West is the basement, north is the kitchen, and east is the frontdoor"
        );
        addRoom(reallivingroom);
        
        RegularRoom realbasement = new RegularRoom("realbasement", "Nothing here of any value except a few rat traps and old furnitures.");
        addRoom(realbasement);
        
        RegularRoom realkitchen = new RegularRoom("realkitchen", "you are in your kitchen. Cups and beer bottles are dumped everywhere. \n"
                + "Go south to return to the livingroom");
        realkitchen.addItem(notesix);
        addRoom(realkitchen);   
        
        //Wakenote is an item... event occurs when current rooms matches the rooms used as parameters in the Wakenote item 
        RoomChangeNote notefive = new RoomChangeNote("scrap", "It is a note with words scribbled on it", true, outside, BCCstreet);        
        outside.addItem(notefive);

        
        //connect the rooms
        darkroom.addExit(bedroom, "north");
        bedroom.addExit(darkroom, "south");
        bedroom.addExit(livingroom, "north");
        basement.addExit(livingroom, "east");
        livingroom.addExit(basement, "west");
        livingroom.addExit(bedroom, "south");
        livingroom.addExit(kitchen, "north");
        livingroom.addExit(outside, "east");
        kitchen.addExit(livingroom, "south");
        basement.addExit(northroom, "north");
        basement.addExit(westroom, "west");
        basement.addExit(southroom, "south");
        northroom.addExit(basement, "south");
        westroom.addExit(basement, "east");
        southroom.addExit(basement, "north");
        outside.addExit(driveway, "north");
        outside.addExit(driveway, "south");
        outside.addExit(driveway, "east");
        outside.addExit(driveway, "west");
        driveway.addExit(outside, "south");
        driveway.addExit(BCCstreet, "north");
        BCCstreet.addExit(BCC, "north");
        BCCstreet.addExit(driveway, "south");
        BCC.addExit(BCCstreet, "south");
        BCC.addExit(complab, "east");
        realbedroom.addExit(reallivingroom, "north");
        reallivingroom.addExit(realkitchen, "north");
        reallivingroom.addExit(realbasement, "west");
        reallivingroom.addExit(realoutside, "east");
        realbasement.addExit(reallivingroom, "east");
        realkitchen.addExit(reallivingroom, "south");
        realoutside.addExit(reallivingroom, "west");
        
        // set current position
        setCurrentRoom(rooms.get(0));
        
    }

    
    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Room getCurrentRoom() {
        return thePlayer.getCurrentRoom();
    }
    
    public void setCurrentRoom(Room room) {
        thePlayer.setCurrentRoom(room);
    }
    
    //////
    ////// Gameplay
    //////
        
    public void play() {
        
        isPlaying = true;
        printWelcome();
        
        // ... and start playing!
        while (isPlaying) {
            
            Command turn = new Command();    // read user input
            dispatch(turn);
            
            if (rooms.get(2).isEntered == true) { // once player enters the realoutside room(the room designated 
                isPlaying = false;              // to be the last room),the game ends
            }
        }
        
        //stopped
        printGoodBye();
    }
    

    
    
    private void printWelcome() {
        System.out.println("Welcome.... ");
        System.out.println();
        
        System.out.println(
             "After a long night out studying the vargaries of Java programming, \n" +
             "you wake up in a strange room. Your head hurts and you can barely remember what happened \n" + 
             "the day before..."
        );
        
        System.out.println("Your goal is to make sense of what happened.");        
        System.out.println();
        getCurrentRoom().printDescription();
    }
    
    private void printGoodBye() {
        System.out.println("Bye!");
    }
 
    
    // called by other objects (rooms, player) when they've decided the game should end.
    public void gameOver() {
        isPlaying = false;
    }
    
    
    
    /////
    ///// Handle commands
    /////
    
    // handle administrative commands, or dispatch to player to handle gameplay commands
    private void dispatch(Command turn) {     
        if (turn.isHelp()) {
            actionHelp();
        } else if (turn.isQuit()) {
            actionQuit();
        } else if (turn.isWake() && awake==false) { // slightly modified the original dispatch() code for a relevant function in the game
            actionWake();      // player can awake from their dream state anytime during gameplay
            thePlayer.myItems.clear();
            awake = true;
        } else {
            thePlayer.dispatch(turn);
        }
    }
    
    
    private void actionWake() {
        setCurrentRoom(rooms.get(1));
        getCurrentRoom().printDescription();
    }
    
    private void actionHelp() {
        System.out.println("Try using simple verbs in order to do things. Only one or");
        System.out.println("two words will be recognized.");
        System.out.println();
        System.out.println("For example, type 'look' to look around, or 'north' to move to");
        System.out.println("the north. ");
    }
    
    private void actionQuit() {
        gameOver();
    }
    
    
    
}  // end World class

