
public class Item
{
    private String id;
    private String description;
    private int weight;
    private boolean canBePickedUp;

    /**
     * Constructor for objects of class Item
     */
    public Item(String id, String desc, int weight, boolean can)
    {
        this.id = id;
        description = desc;
        this.weight = weight;
        canBePickedUp = can;
    }
    
    public String getId()
    {
        return id;
    }
    
    public boolean getCanBePickedUp()
    {
        return canBePickedUp;
    }

    public String getDescription()
    {
        return description;
    }
    
    public int getWeight()
    {
        return weight;
    }
    
    public String toString(){
        return id + ": " + description + "--> peso:" + weight;
    }
}
