
public class Item
{
    private String description;
    private int weight;

    /**
     * Constructor for objects of class Item
     */
    public Item(String desc, int weight)
    {
        description = desc;
        this.weight = weight;
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
        return description + " peso:" + weight;
    }
}
