import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;

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
    private Item object;
    private ArrayList<Item> items;

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
        items = new ArrayList<Item>();
    }

    public void setExit(String direction, Room room){
        exits.put(direction, room);
    }

    /**
     * A�ade un item a la sala.
     */
    public void addItem(Item item){
        items.add(item);
    }

    /**
     * Elimina un item a la sala.
     */
    public void dropItem(Item item){
        items.remove(item);
    }

    /**
     * Encuentra un item de la sala.
     */
    public Item searchItem(String searchItem){
        Item theItem = null;
        for(Item currentItem : items){
            if (currentItem.getId().equals(searchItem)){
                theItem = currentItem;
            }
        }
        return theItem;
    }

    /**
     * Muestra todos los Item de la sala.
     */
    public String lookItems(){
        String allItems = "";
        for(Item currentItem : items){
            allItems += currentItem.getDescription();
        }
        return allItems;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public Room getExit(String direccion){
        return exits.get(direccion);
    }

    public String getExitString(){
        Set<String> namesExits = exits.keySet();
        String exit = "Exit: ";
        for(String direction :namesExits){
            exit += direction + " ";
        }        
        return exit;
    }

    /**
     * Devuelve un texto con la descripcion larga de la habitacion del tipo:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return Una descripcion de la habitacion incluyendo sus salidas
     */
    public String getLongDescription(){
        String text = "";
        text = "you are " + description + "\n"+ getExitString();;
        if (!items.isEmpty()){
            for(int i = 0; i < items.size(); i++){
                text += "\n" + items.get(i).toString();
            }
        }
        return text;  
    }

}
