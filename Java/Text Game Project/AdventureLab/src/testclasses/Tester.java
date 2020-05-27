
package testclasses;

import adventure.*;
import adventure.Player;
import adventure.World;
import adventure.rooms.*;
import adventure.command.Command;
import adventure.items.*;
import java.util.*;
import java.io.*;

public class Tester implements WorldInterface{

    private ArrayList<Room> TestRooms;
    private Player TestPlayer;
    private boolean isPlaying;

    
    // This class is only meant to do what the World class can do, with a few minor adjustments, so that
    // one can freely create new objects and quickly test them out without needing to change anything
    // from the actual World class. Use this class as you would use the World class.
    
    public static void main(String[] args) {   
        Tester myTest = new Tester();
        
    // add testRoom() methods here               
        myTest.testRoom1();
        
        
    // add testRoomChangeItems() methods here
        myTest.testRoomChangeItem();
        
        
    //*********************************************************************
        myTest.play();
    }    
    
    public Tester() {
        TestRooms = new ArrayList<Room>();
        TestPlayer = new Player(this);
        
        RegularRoom testroom1 = new RegularRoom("testroom1", 
                    "This is the first test room. It's default exit is to testroom2"
                  );       
        
        TestRooms.add(testroom1);
        
        setCurrentRoom(TestRooms.get(0));

    }
    
    
    // Use these methods to test the additional classes
    // This is for testing LockedRooms and RegularRooms. FinalRoom will not be tested due to 
    // limitations in my codes that will not allow it to be tested in the testRoom() method format
    
    public void testRoom1() {
        LockedRoom lock = new LockedRoom("lock",
                "You have entered the locked room. The code works!", "password", "it is 'password'");
        TestRooms.add(lock);
        getPreviousRoom().addExit(lock,"north");
    }

    // This is for testing the RoomChangeNote class. This is the only item object in my game that does anything significantly unique
    // so this is the only item that will be tested.

    public void testRoomChangeItem() {
        RoomChangeNote testitem = new RoomChangeNote("testitem", "this is a test item", true, TestRooms.get(0), TestRooms.get(1));
        TestRooms.get(0).addItem(testitem);
    }
    

    
    //add more test methods here
    
    
    
    // format for RoomTest methods should be formatted as follow
    
    // --- testRoom + 'test trial #' + () {
    // --- create the room object > ex. LockedRoom lock = new LockedRoom("blah blah blah","blah blah blah","blahb labh", "blabhlabh");
    //            > My game only has two types of rooms - RegularRoom and LockedRoom.
    //            > RegularRoom requires two arguments ( String (name) , String (description of room) )
    //            > LockedRoom orequires four parameters ( String (name), String(description of room), String (password), String (hint for password) )
    // --- add the room to the TestRooms arraylist with TestRooms.add(_____);
    // --- add exit paths from previous room to the room youre creating with the getPreviousRoom() method
    //            > example:      getPreviousRoom().addExit(lock,"north");
    // }

    
    
    // format for testItem methods should be formatted as follow
    
    // --- testItem + 'test trial #' + () {
    // --- create the RoomChangeNote object > ex. RoomChangeNote lock = new RoomChangeNote("blah blah blah","blah blah blah", true, blah, blah);
    //            > RoomChangeNote requires five arguments
    //            > ( String (name) , String (description of room), Boolean (true/false - item able/not able to be picked/), Room (object name of room 1), Room (object name of room 2) )
    //            > reason for the two room arguments is because this item only functions within the rooms that are used as arguments
    //            > this means that if there isn't two rooms, this test will not work.
    // --- add the item to a specific room with <name/reference of room>.addItem(name of the RoomChangeNote);
    //            > example:      room1.addItem(testnote);
    // }           
    // While ingame, go to room location where you placed the item object, then "take" the item and "use" it.
    // PLEASE NOTE: you need to place the testItem() method after all your RoomTest() methods. You need the rooms to exist first before your item can identify which room 
    // it can function in
    
    
//******************************************************************************        
    public void addRoom(Room room) {
        return;
    }
    
    public Room getPreviousRoom() {
        if (TestRooms.size() >= 2) {
        return TestRooms.get((TestRooms.size() - 2));
        } else {
            return TestRooms.get(0);
        }
    }
    
    public Room getCurrentRoom() {
        return TestPlayer.getCurrentRoom();
    }    
    
    public void setCurrentRoom(Room room) {
        TestPlayer.setCurrentRoom(room);
    }

    public void play() {
        
        isPlaying = true;
        printWelcome();
        
        // ... and start playing!
        while (isPlaying) {
            
            Command turn = new Command();    // read user input
            dispatch(turn);     
            
//            if (realoutside.isEntered == true) { // once player enters the realoutside room(the last room),the game ends
//                isPlaying = false;
//            }
        }
    }
       
    private void printWelcome() {
        TestRooms.get(0).printDescription();
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
        } else {
            TestPlayer.dispatch(turn);
        }
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
}
