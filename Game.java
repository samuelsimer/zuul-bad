/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room islaCalabera, puerto, islaMonos, cataratas, islaDulce, islaTortuga, islaTesoro;
        Item calavera, tesoro, collarHuesos;

        // create the rooms and Items
        collarHuesos = new Item("collar de huesos", 250);
        calavera = new Item("gran calabera", 300);
        tesoro = new Item("un cofre lleno de joyas y oro", 8000);
        islaCalabera = new Room("en la isla de las calaberas");
        puerto = new Room("en el puerto");
        islaMonos = new Room("en la isla de los monos");
        cataratas = new Room("en las cataratas");
        islaDulce = new Room("en la isla de los dulces");
        islaTortuga = new Room("en la isla de las tortugas");
        islaTesoro = new Room("en a la isla del tesoro");
        islaTesoro.addItem(tesoro);
        islaCalabera.addItem(calavera);
        islaCalabera.addItem(collarHuesos);
        

        // initialise room exits
        puerto.setExit("north", islaCalabera);
        puerto.setExit("east", islaMonos);
        puerto.setExit("south", islaDulce);
        puerto.setExit("southEast", islaTortuga);
        islaCalabera.setExit("south", islaMonos);
        islaCalabera.setExit("southEast", islaMonos);
        islaMonos.setExit("west", puerto);
        islaMonos.setExit("south", cataratas);
        islaMonos.setExit("northWest", islaCalabera);
        cataratas.setExit("north", islaMonos);
        cataratas.setExit("west", islaTortuga);
        islaDulce.setExit("north", puerto);
        islaDulce.setExit("east", islaTortuga);
        islaDulce.setExit("southEast", islaTesoro);
        islaTortuga.setExit("east", cataratas);
        islaTortuga.setExit("south", islaTesoro);
        islaTortuga.setExit("west", islaDulce);
        islaTortuga.setExit("northWest", puerto);
        islaTesoro.setExit("north", islaTortuga);
        islaTesoro.setExit("northWest", islaDulce);

        currentRoom = puerto;  // start game in puerto
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {	
            look();
        }
        else if (commandWord.equals("eat")) {	
            eat();
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.getCommandList());
    }

    private void look() {	
        System.out.println(currentRoom.getLongDescription());
    }

    private void eat() {	
        System.out.println("You have eaten now and you are not hungry any more");
    }    

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);        

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    private void printLocationInfo(){
        System.out.println(currentRoom.getLongDescription());
        System.out.println();
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
