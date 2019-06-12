import java.util.Stack;
import java.util.ArrayList;

public class Player
{
    private Room currentRoom;
    private Stack<Room> rooms;
    private ArrayList<Item> bag;
    private static int overWeight = 800; 
    private int weightInBag;   

    public Player()
    {
        rooms = new Stack<Room>();
        currentRoom = null;
        bag = new ArrayList<Item>();
        weightInBag = 0;
    }

    public void setCurrentRoom(Room room){
        currentRoom = room;
    }

    public Room getCurrentRoom(Room room){
        return currentRoom;
    }

    public void look() {	
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Muestra el peso qu ellevas en tu mochila actualmente, el peso maximo que puedes cargar y el peso que te queda para llegar a tu maximo.
     */
    public void getWeight() {   
        System.out.println("el peso de tu mochila es actualmente: " + weightInBag);
        System.out.println("el maximo que puedes cargar es: " + overWeight);
        System.out.println("te queda: " + (overWeight - weightInBag) + "espacio libre en tu mochila");        
    }

    /**
     * Coger un Item de una sala.
     */
    public void take(String item) {    
        if(!currentRoom.lookItems().equals("") && weightInBag + currentRoom.searchItem(item).getWeight() <= overWeight && currentRoom.searchItem(item).getCanBePickedUp()){
            weightInBag += currentRoom.searchItem(item).getWeight();
            bag.add(currentRoom.searchItem(item));
            currentRoom.dropItem(currentRoom.searchItem(item));
        }
    }

    public void eat() {	
        System.out.println("You have eaten now and you are not hungry any more");
    }    

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
    {
        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);  

        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {

            rooms.push(currentRoom);
            currentRoom = nextRoom;            
            look();
        } 
    }
    
        /**
     * Devuelve todos los item que posees en ese momento.
     */
    public void items() {       
        if(!bag.isEmpty()){
            for(Item currentItem : bag){
                if(currentItem != null){
                    System.out.println(currentItem.toString());
                }            
            }  
        }
        else {
            System.out.println("you dont have items in your bag");
        }

    }


    public void back(){
        if(!rooms.isEmpty()){
            currentRoom = rooms.pop();
        }

        look();
    }
    
        /**
     * Encuentra un item en la mochila.
     */
    public Item chooseItem(String searchItem){
        Item theItem = null;        
        for(Item currentItem : bag){
            if (currentItem.getId().equals(searchItem)){                            
                    theItem = currentItem;                                  
            }
        }
        return theItem;
    }

    /**
     * Dejar un Item en una sala.
     */
    public void drop(String item) {    
        if(!bag.isEmpty()){
            weightInBag -= chooseItem(item).getWeight();
            currentRoom.addItem(chooseItem(item));
            bag.remove(chooseItem(item));
        }
    }

}
