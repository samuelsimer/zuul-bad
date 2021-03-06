import java.util.Stack;
import java.util.ArrayList;

public class Player
{
    private Room currentRoom;
    private Stack<Room> rooms;
    private ArrayList<Item> bag;
    private int overWeight; 
    private int weightInBag;
    private boolean maxWeight;

    public Player(boolean yesOrNoMaxWeight, int fullWeight)
    {
        rooms = new Stack<Room>();
        currentRoom = null;
        bag = new ArrayList<Item>();
        weightInBag = 0;
        maxWeight = yesOrNoMaxWeight;
        overWeight = fullWeight;
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
        if(maxWeight){
            System.out.println("el maximo que puedes cargar es: " + overWeight);
            System.out.println("te queda: " + (overWeight - weightInBag) + "espacio libre en tu mochila");   
        }

    }

    /**
     * Coger un Item de una sala.
     */
    public void take(String item) {    
        if(!currentRoom.lookItems().equals("") && currentRoom.searchItem(item).getCanBePickedUp()){
            if(maxWeight){
                if( weightInBag + currentRoom.searchItem(item).getWeight() <= overWeight){
                    weightInBag += currentRoom.searchItem(item).getWeight();
                    bag.add(currentRoom.searchItem(item));
                    currentRoom.dropItem(currentRoom.searchItem(item)); 
                }
            } 
            else{
                bag.add(currentRoom.searchItem(item));
                currentRoom.dropItem(currentRoom.searchItem(item)); 
            }

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
            int allWeight = 0;
            for(Item currentItem : bag){
                if(currentItem != null){
                    System.out.println(currentItem.toString());
                    allWeight += currentItem.getWeight();
                }
                if(currentItem == null){
                    bag.remove(currentItem);
                }
            }
            System.out.println("el peso total que llevas en tu mochila es: " + allWeight);
        }
        else {
            System.out.println("you dont have items in your bag");
        }

    }

    /**
     * Devuelve todos los item que posees en ese momento.
     */
    public String itemsInString() {       
        String textItems = "";
        if(!bag.isEmpty()){
            for(Item currentItem : bag){
                if(currentItem != null){
                    textItems += currentItem.getId();
                }
                if(currentItem == null){
                    bag.remove(currentItem);
                }
            }            
        }
        return textItems;
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
            if(maxWeight){
                weightInBag -= chooseItem(item).getWeight();
            }
            currentRoom.addItem(chooseItem(item));
            bag.remove(chooseItem(item));
        }
    }

    public void openTreasure(){
        if(currentRoom.lookItems().contains("llave") && itemsInString().equals("cofre")){
            Item oro = new Item("oro", "monedas de oro del cofre pirata", 100, true);
            Item joyas = new Item("joyas", "las mejores y mas caras joyas que existen en este mundo", 100, true);
            for(int i = 0; i < bag.size(); i++){
                bag.remove(i);
            }
            bag.add(oro);
            bag.add(joyas);
            System.out.println("¡¡¡Enhorabuena!!! ahora tienes oro y joyas en tu mochila, ¡¡¡Es hora de triunfar en el mundo pirata!!!");
        }
    }

}
