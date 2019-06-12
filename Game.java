import java.util.Stack;
import java.util.ArrayList;

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
    private Player player;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {        
        parser = new Parser();
        player = new Player();
        createRooms();
        player.setCurrentRoom(createRooms());  // start game in puerto
        
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private Room createRooms()
    {
        Room islaCalabera, puerto, islaMonos, cataratas, islaDulce, islaTortuga, islaTesoro;
        Item calabera, tesoro, huesos, caramelos, caparazon, llave;

        // create the rooms and Items
        huesos = new Item("huesos", "huesos varios", 250, false);
        calabera = new Item("calabera", "gran calabera", 300, true);
        tesoro = new Item("cofre", "cofre lleno de joyas y oro", 800, true);
        caramelos = new Item("caramelos", "bolsa llena de caramelos", 300, true);
        caparazon = new Item("caparazon", "caparazon de tortuga gigante", 500, true);
        llave = new Item("llave", "llave de algun lugar", 10, true);
        
        islaCalabera = new Room("en la isla de las calaberas");
        puerto = new Room("en el puerto");
        islaMonos = new Room("en la isla de los monos");
        cataratas = new Room("en las cataratas");
        islaDulce = new Room("en la isla de los dulces");
        islaTortuga = new Room("en la isla de las tortugas");
        islaTesoro = new Room("en a la isla del tesoro");
        islaTesoro.addItem(tesoro);
        islaCalabera.addItem(calabera);
        islaCalabera.addItem(huesos);
        islaDulce.addItem(caramelos);
        islaTortuga.addItem(caparazon);
        puerto.addItem(llave);

        // initialise room exits
        puerto.setExit("north", islaCalabera);
        puerto.setExit("east", islaMonos);
        puerto.setExit("south", islaDulce);
        puerto.setExit("southEast", islaTortuga);
        islaCalabera.setExit("south", puerto);
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

        return puerto; //retorna la sala inicial
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
        player.look();
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
            player.goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {  
            player.look();
        }
        else if (commandWord.equals("eat")) {   
            player.eat();
        }
        else if (commandWord.equals("back")) {
            player.back();
        }
        else if (commandWord.equals("take")) {
            player.take(command.getSecondWord());
        }
        else if (commandWord.equals("drop")) {
            player.drop(command.getSecondWord());
        }
        else if (commandWord.equals("items")) {
            player.items();
        }
        else if (commandWord.equals("weight")) {
            player.getWeight();
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
        System.out.println("you are in the middle of mysterious islands");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.getCommandList());
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
