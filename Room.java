import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room southEastExit;
    private Room northWestExit;
    private HashMap<String, Room> exits;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     * * @param west The southwest exit.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room northWest, Room southEast) 
    {
        if(north != null)
            exits.put("north", north);
        if(east != null)
            exits.put("east", east);
        if(south != null)
            exits.put("south", south);
        if(west != null)
            exits.put("west", west);
        if(northWest != null)
            exits.put("northWest", northWest);
        if(southEast != null)
            exits.put("southEast", southEast);        
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public Room getExit(String direccion){
        Room roomReturn = null;

        if(direccion.equals("north")){
            roomReturn = exits.get("north");
        }
        if(direccion.equals("south")){
            roomReturn = exits.get("south");
        }
        if(direccion.equals("east")){
            roomReturn = exits.get("east");
        }
        if(direccion.equals("west")){
            roomReturn = exits.get("west");
        }
        if(direccion.equals("northWest")){
            roomReturn = exits.get("northWest");
        }
        if(direccion.equals("southEast")){
            roomReturn = exits.get("southEast");
        }

        return roomReturn;
    }

    public String getExitString(){
        String description = "Exit: ";

        if(exits.get("north") != null) {
            description += "north ";
        }
        if(exits.get("east") != null) {
            description += "east ";
        }
        if(exits.get("south") != null) {
            description += "south ";
        }
        if(exits.get("west") != null) {
            description += "west ";
        }
        if(exits.get("northWest") != null) {
            description += "northWest";
        }
        if(exits.get("southEast") != null) {
            description += "southEast ";
        }      

        return description;
    }

}
