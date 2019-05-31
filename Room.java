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
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
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
    public void setExits(Room north, Room east, Room south, Room west, Room northeast, Room southeast) 
    {
        if(north != null)
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
        if(southeast != null)
            southEastExit = southeast;
        if(northeast != null)
            northWestExit = northeast;
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
            roomReturn = northExit;
        }
        if(direccion.equals("south")){
            roomReturn = southExit;
        }
        if(direccion.equals("east")){
            roomReturn = eastExit;
        }
        if(direccion.equals("west")){
            roomReturn = westExit;
        }
        if(direccion.equals("northeast")){
            roomReturn = northWestExit;
        }
        if(direccion.equals("southeast")){
            roomReturn = southEastExit;
        }
        
        return roomReturn;
    }
    
    public String getExitString(){
        String description = "Exit: ";
        
        if(northExit != null) {
           description += "north ";
        }
        if(eastExit != null) {
            description += "east ";
        }
        if(southExit != null) {
            description += "south ";
        }
        if(westExit != null) {
            description += "west ";
        }
        if(northWestExit != null) {
            description += "northEast ";
        }
        if(southEastExit != null) {
            description += "southEast ";
        }      
        
        return description;
    }

}
