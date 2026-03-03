import javax.swing.ImageIcon;

/**
 * Class file for the Armor class.
 * <p>
 *     This class contains mainly getter methods as most attributes are declared upon construction.
 * </p>
 * <p>
 *     This class also contains a static attribute that keeps count of the total Armor objects generated.
 * </p>
 * @author Stefan_Martin
 */

public class Armor {

    //Private Attributes

    //String attributes
    private String name;
    private String type;
    private ImageIcon image;

    //Stat attributes
    private int defense; //Amount of defense the weapon can protect against
    private int speedPenalty; //Speed decrease when using the weapon

    //Static Attributes
    private static int totalArmor  = 0; //Stores the amount of Armor instances

    /**
     * Constructor for the Armor class
     * @param name The armor's unique name, assumes all names passed are valid
     * @param type Indicates the armor's type, assumes all types passed are valid
     * @param image Image of the armor
     * @param defense The armor's def value, assumes any value as valid
     * @param speedPenalty The armor's speed penalty, assumes any value as valid
     */
    //Method Constructor
    public Armor(String name, String type, ImageIcon image, int defense, int speedPenalty){

        this.name = name;
        this.type = type;
        this.image = image;
        this.defense = defense;
        this.speedPenalty = speedPenalty;
        
        totalArmor++; //Increment counter

    }


    //Getters

    /**
     * Getter method for the armor name
     * @return the name of the armor object
     */
    public String getName(){

        return name;

    }

    /**
     * Getter method for the armor type
     * @return the type of the armor object
     */
    public String getType(){

        return type;

    }

    /**
     * Getter to return the armor's image
     * @return image file of the armor
     */
    public ImageIcon getImage(){

        return image;
        
    }

    /**
     * Getter method for the armor's def value
     * @return the def value of the armor object
     */
    public int getDefense(){

        return defense;

    }

    /**
     * Getter method for the armor's speed penalty
     * @return speed penalty of the armorobject
     */
    public int getSpeedPenalty(){

        return speedPenalty;

    }

    /**
     * Static getter method for total armor count
     * @return Current total number of armor objects constructed
     */
    //Returns total amount of Armor instances 
    public static int getTotalArmor(){

        return totalArmor;
        
    }
}
